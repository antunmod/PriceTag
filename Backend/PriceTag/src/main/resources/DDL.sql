DROP SCHEMA test;
CREATE SCHEMA test;
USE test;

/*
User can be an admin or just a regular user. Admins have to be added by other admins.
*/
CREATE TABLE user_type (
	id TINYINT AUTO_INCREMENT PRIMARY KEY,
	description VARCHAR(10)
);

CREATE TABLE user(
	id SMALLINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(30) NOT NULL,
	password VARCHAR(30) NOT NULL,
	email VARCHAR(30) NOT NULL,
	signup_date DATE NOT NULL,
	rating DECIMAL(4,3) DEFAULT 0,
	points SMALLINT DEFAULT 0,
	user_type_id TINYINT DEFAULT 1,

	CONSTRAINT unique_user_name UNIQUE (name),
	CONSTRAINT unique_user_mail UNIQUE (email),
	CONSTRAINT fk_user_user_type FOREIGN KEY (user_type_ID) REFERENCES user_type (id)
	);

CREATE TABLE store(
	id TINYINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(30)
	);

/*
The connection between store name and the location of a specific store.
*/
CREATE TABLE store_specific(
	id SMALLINT AUTO_INCREMENT PRIMARY KEY,
	store_id TINYINT ,
	address VARCHAR(30),

	CONSTRAINT fk_store_specific_store FOREIGN KEY (store_id) REFERENCES store (id)
	);

CREATE TABLE sector(
	id TINYINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50),

	CONSTRAINT unique_sector_name UNIQUE (name)
	);

CREATE TABLE category(
	id TINYINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50)
	);

CREATE TABLE subcategory(
	id SMALLINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50)
	);

/*
A table containing different product sizes.
E.g. l = liter, g = gram, mg = miligram ...
*/
CREATE TABLE product_size(
	id TINYINT AUTO_INCREMENT PRIMARY KEY,
	type VARCHAR(5) NOT NULL
	);

CREATE TABLE producer(
	id SMALLINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(30)
	);

/*
Cointains producer and product name.
*/
CREATE TABLE product(
	id SMALLINT AUTO_INCREMENT PRIMARY KEY,
	producer_id SMALLINT,
	name VARCHAR(40) NOT NULL,	

	CONSTRAINT unique_product UNIQUE (name, producer_id),
	CONSTRAINT fk_product_producer FOREIGN KEY (producer_id) references producer(id)		
	);

/*
Each product can come in multiple sizes, barcodes and photos so this table contains the specifics
for each size of the product.
*/
CREATE TABLE product_specific(
	id SMALLINT AUTO_INCREMENT PRIMARY KEY,
	product_id SMALLINT NOT NULL,
	barcode VARCHAR(15) NOT NULL,
	description VARCHAR(30) DEFAULT '',
	photo_URI VARCHAR(50) DEFAULT '',
	size DECIMAL(7,3) NOT NULL,
	size_id TINYINT NOT NULL,

	CONSTRAINT unique_product_specific UNIQUE (id, size),
	CONSTRAINT fk_product_specific_product FOREIGN KEY (product_id) REFERENCES product (id)
		ON UPDATE CASCADE,	
	CONSTRAINT fk_product_specific_size FOREIGN KEY (size_id) REFERENCES product_size(id)
	);

/*
This table contains information on which products can be found in a certain store.
*/
CREATE TABLE product_store (
	id SMALLINT AUTO_INCREMENT PRIMARY KEY,
	product_specific_id SMALLINT NOT NULL,
	store_specific_id SMALLINT NOT NULL,
	
	CONSTRAINT unique_product_store UNIQUE (product_specific_ID, store_specific_ID),
	CONSTRAINT fk_product_store_product_specific FOREIGN KEY (product_specific_ID) REFERENCES product_specific (id),
	CONSTRAINT fk_product_store_store_specific FOREIGN KEY (store_specific_ID) REFERENCES store_specific (id)
);

/*
This table contains information about the prices added by users in specific stores:
	ID of a product in a store, user id, its price date of price change and feedback.
	@feedback specifies the feedback of the price. It is used for calculating user rating.
	The price is valid (1) when added. When the new price is reported as invalid by a user and
	approved as invalid by an admin, its value is changed to invalid (0) and the product price is set to
	the last valid price.
*/
CREATE TABLE price (
	id INT AUTO_INCREMENT PRIMARY KEY,
	product_store_id SMALLINT NOT NULL,
	user_id SMALLINT NOT NULL,
	price DECIMAL(7,2) NOT NULL,
	price_change_date DATE NOT NULL,

	CONSTRAINT fk_price_user FOREIGN KEY (user_id) REFERENCES user (id),
	CONSTRAINT fk_price_product_store FOREIGN KEY (product_store_id) REFERENCES product_store (id)
	);

CREATE TABLE sector_category(
	id TINYINT AUTO_INCREMENT PRIMARY KEY,
	sector_id TINYINT NOT NULL,
	category_id TINYINT NOT NULL,

	CONSTRAINT fk_sector_category_sector FOREIGN KEY (sector_id) REFERENCES sector (id)
		ON UPDATE CASCADE,
	CONSTRAINT fk_sector_category_category FOREIGN KEY (category_id) REFERENCES category (id)
		ON UPDATE CASCADE
	);

CREATE TABLE category_subcategory(
	id SMALLINT AUTO_INCREMENT PRIMARY KEY,
	category_id TINYINT NOT NULL,
	subcategory_id SMALLINT NOT NULL,

	CONSTRAINT fk_category_subcategory_category FOREIGN KEY (category_id) REFERENCES category (id)
		ON UPDATE CASCADE,
	CONSTRAINT fk_category_subcategory_subcategory FOREIGN KEY (subcategory_id) REFERENCES subcategory (id)
		ON UPDATE CASCADE
	);

CREATE TABLE subcategory_product(
	id SMALLINT AUTO_INCREMENT PRIMARY KEY,
	subcategory_id SMALLINT NOT NULL,
	product_id SMALLINT NOT NULL,

	CONSTRAINT fk_subcategory_product_subcategory FOREIGN KEY (subcategory_id) REFERENCES subcategory (id)
		ON UPDATE CASCADE,
	CONSTRAINT fk_subcategory_product_product FOREIGN KEY (product_id) REFERENCES product (id)
		ON UPDATE CASCADE
	);

-- Feedback stores feedback from user in form POSITIVE(P) and NEGATIVE(N).
CREATE TABLE information_feedback(
	id INT AUTO_INCREMENT PRIMARY KEY,
	information_provider_user_id SMALLINT NOT NULL,
	feedback_provider_user_id SMALLINT NOT NULL,
	feedback CHAR(1) NOT NULL,

	CONSTRAINT fk_information_feedback_information_provider_user FOREIGN KEY (information_provider_user_id) REFERENCES user (id),
	CONSTRAINT fk_information_feedback_feedback_provider_user FOREIGN KEY (feedback_provider_user_id) REFERENCES user (id)
	);


-- A trigger for setting new information_feedback feedback to NULL if the user tries to 
-- rate his own product
delimiter |
CREATE TRIGGER trig_check_information_feedback BEFORE INSERT ON information_feedback
	FOR EACH ROW 
    BEGIN
    IF NEW.information_provider_user_ID = NEW.feedback_provider_user_ID THEN
		SET NEW.feedback = NULL;
	END IF;
    END;
| delimiter ;

-- A trigger for updating user rating upon each product update rating by another user
-- DROP TRIGGER trig_update_user_rating;
delimiter |
CREATE TRIGGER trig_update_user_rating AFTER INSERT ON information_feedback
	FOR EACH ROW 
    BEGIN

    DECLARE done INT DEFAULT 0;
    DECLARE counter INT DEFAULT 0;
    DECLARE sum DECIMAL(4,3) DEFAULT 0;
    DECLARE feed CHAR(1);
    DECLARE feedback_ID SMALLINT;
    DECLARE rating DECIMAL(4,3) DEFAULT 0;
    DECLARE cur CURSOR FOR SELECT feedback_provider_user_id, feedback
		FROM information_feedback WHERE information_provider_user_id = NEW.information_provider_user_id;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
    
    OPEN cur;
		calculate_loop: LOOP
			FETCH cur into feedback_ID, feed;
            IF done THEN LEAVE calculate_loop; END IF;
			SELECT user.rating into rating FROM user where user.id = feedback_ID limit 1;
            IF feed like 'P' THEN SET sum = sum + rating;
            ELSE SET sum = sum - rating; END IF;
            SET counter = counter + 1;
		END LOOP;
	CLOSE cur;
    
	UPDATE user SET user.rating = 0.5 + sum/counter/2 WHERE user.id = NEW.information_provider_user_id;
    
	END;
| delimiter ;