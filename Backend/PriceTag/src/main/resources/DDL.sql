/*
User can be an admin or just a regular user. Admins have to be added by other admins.
*/
CREATE TABLE user_type (
	user_type_ID BIT AUTO_INCREMENT PRIMARY KEY,
	user_type_description VARCHAR(10)
);

CREATE TABLE user(
	user_ID SMALLINT AUTO_INCREMENT PRIMARY KEY,
	user_name VARCHAR(30) NOT NULL,
	user_password VARCHAR(30) NOT NULL,
	user_mail VARCHAR(30) NOT NULL,
	signup_date DATE NOT NULL,
	points SMALLINT DEFAULT 0,
	user_type_ID BIT DEFAULT 1,

	CONSTRAINT unique_user_name UNIQUE (user_name),
	CONSTRAINT unique_user_mail UNIQUE (user_mail),
	CONSTRAINT fk_user_user_type FOREIGN KEY (user_type_ID) REFERENCES user_type (user_type_ID)
	);

CREATE TABLE store(
	store_ID TINYINT AUTO_INCREMENT PRIMARY KEY,
	store_name VARCHAR(30)
	);

/*
The connection between store name and the location of a specific store.
*/
CREATE TABLE store_specific(
	store_specific_ID SMALLINT AUTO_INCREMENT PRIMARY KEY,
	store_ID TINYINT ,
	store_address VARCHAR(30),

	CONSTRAINT fk_store_specific_store FOREIGN KEY (store_ID) REFERENCES store (store_ID)
	);

CREATE TABLE sector(
	sector_ID TINYINT AUTO_INCREMENT PRIMARY KEY,
	sector_name VARCHAR(50),

	CONSTRAINT unique_sector_name UNIQUE (sector_name)
	);

CREATE TABLE category(
	category_ID TINYINT AUTO_INCREMENT PRIMARY KEY,
	category_name VARCHAR(50)
	);

CREATE TABLE subcategory(
	subcategory_ID SMALLINT AUTO_INCREMENT PRIMARY KEY,
	subcategory_name VARCHAR(50)
	);
/*
A table containing different product sizes.
E.g. l = liter, g = gram, mg = miligram ...
*/
CREATE TABLE product_size(
	product_size_ID TINYINT AUTO_INCREMENT PRIMARY KEY,
	size_type VARCHAR(5) NOT NULL
	);

CREATE TABLE producer(
	producer_ID SMALLINT AUTO_INCREMENT PRIMARY KEY,
	producer_name VARCHAR(30)
	);

/*
Cointains producer and product name.
*/
CREATE TABLE product(
	product_ID SMALLINT AUTO_INCREMENT PRIMARY KEY,
	producer_ID SMALLINT ,
	product_name VARCHAR(30) NOT NULL,	

	CONSTRAINT unique_product UNIQUE (product_name, producer_ID),
	CONSTRAINT fk_product_producer FOREIGN KEY (producer_ID) references producer(producer_ID)		
	);

/*
Each product can come in multiple sizes, barcodes and photos so this table contains the specifics
for each size of the product.
*/
CREATE TABLE product_specific(
	product_specific_ID SMALLINT AUTO_INCREMENT PRIMARY KEY,
	product_ID SMALLINT NOT NULL,
	barcode VARCHAR(15) NOT NULL,
	product_description VARCHAR(30),
	photo_URI VARCHAR(50),
	product_size DECIMAL(6,3) NOT NULL,
	product_size_ID TINYINT NOT NULL,

	CONSTRAINT unique_product_specific UNIQUE (product_ID, product_size),
	CONSTRAINT fk_product_specific_product FOREIGN KEY (product_ID) REFERENCES product (product_ID)
		ON UPDATE CASCADE,	
	CONSTRAINT fk_product_specific_product_size FOREIGN KEY (product_size_ID) REFERENCES product_size(product_size_ID)
	);

/*
This table contains information on which products can be found in a certain store.
*/
CREATE TABLE product_store (
	product_store_ID SMALLINT AUTO_INCREMENT PRIMARY KEY,
	product_specific_ID SMALLINT NOT NULL,
	store_specific_ID SMALLINT NOT NULL,
	
	CONSTRAINT unique_product_store UNIQUE (product_specific_ID, store_specific_ID),
	CONSTRAINT fk_product_store_product_specific_ID FOREIGN KEY (product_specific_ID) REFERENCES product_specific (product_specific_ID),
	CONSTRAINT fk_product_store_store_specific FOREIGN KEY (store_specific_ID) REFERENCES store_specific (store_specific_ID)
);

/*
This table contains information about the prices added by users in specific stores:
	ID of a product in a store, user id, its price date of price change and validity.
	@Validity specifies the validity of the price. It is used for calculating user rating.
	The price is valid (1) when added. When the new price is reported as invalid by a user and
	approved as invalid by an admin, its value is changed to invalid (0) and the product price is set to
	the last valid price.
*/
CREATE TABLE price (
	price_ID INT AUTO_INCREMENT PRIMARY KEY,
	product_store_ID SMALLINT NOT NULL,
	user_ID SMALLINT NOT NULL,
	price DECIMAL(7,2) NOT NULL,
	price_change_date DATE NOT NULL,
	validity BIT DEFAULT 1,

	CONSTRAINT fk_price_user FOREIGN KEY (user_ID) REFERENCES user (user_ID),
	CONSTRAINT fk_price_product_store FOREIGN KEY (product_store_ID) REFERENCES product_store (product_store_ID)
	);

CREATE TABLE sector_category(
	sector_category_ID TINYINT AUTO_INCREMENT PRIMARY KEY,
	sector_ID TINYINT NOT NULL,
	category_ID TINYINT NOT NULL,

	CONSTRAINT fk_sector_category_sector FOREIGN KEY (sector_ID) REFERENCES sector (sector_ID)
		ON UPDATE CASCADE,
	CONSTRAINT fk_sector_category_category FOREIGN KEY (category_ID) REFERENCES category (category_ID)
		ON UPDATE CASCADE
	);

CREATE TABLE category_subcategory(
	category_subcategory_ID SMALLINT AUTO_INCREMENT PRIMARY KEY,
	category_ID TINYINT NOT NULL,
	subcategory_ID SMALLINT NOT NULL,

	CONSTRAINT fk_category_subcategory_category FOREIGN KEY (category_ID) REFERENCES category (category_ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT fk_category_subcategory_subcategory FOREIGN KEY (subcategory_ID) REFERENCES subcategory (subcategory_ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
	);

CREATE TABLE subcategory_product(
	subcategory_product_ID SMALLINT AUTO_INCREMENT PRIMARY KEY,
	subcategory_ID SMALLINT ,
	product_ID SMALLINT ,

	CONSTRAINT fk_subcategory_product_subcategory FOREIGN KEY (subcategory_ID) REFERENCES subcategory (subcategory_ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT fk_subcategory_product_product FOREIGN KEY (product_ID) REFERENCES product (product_ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
	);