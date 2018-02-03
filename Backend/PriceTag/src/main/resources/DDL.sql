CREATE TABLE user(
	user_ID SERIAL PRIMARY KEY,
	user_name VARCHAR(30) NOT NULL,
	user_password VARCHAR(30) NOT NULL,
	user_mail VARCHAR(30) NOT NULL,
	signup_date DATE NOT NULL,
	points INTEGER DEFAULT 0,
	user_type_ID SMALLINT DEFAULT 1,
	CONSTRAINT unique_user_name UNIQUE (user_name)
	);

CREATE TABLE store(
	store_ID SERIAL PRIMARY KEY,
	store_name VARCHAR(30),
	store_address VARCHAR(30),
	CONSTRAINT unique_store UNIQUE (store_address)
	);

CREATE TABLE store_unvalidated(
	store_ID SERIAL PRIMARY KEY,
	store_name VARCHAR(30),
	store_address VARCHAR(30),
	CONSTRAINT unique_store UNIQUE (store_address)
	);

CREATE TABLE sector(
	sector_ID SERIAL PRIMARY KEY,
	sector_name VARCHAR(50),
	CONSTRAINT unique_sector_name UNIQUE (sector_name)
	);

CREATE TABLE sector_unvalidated(
	sector_ID SERIAL PRIMARY KEY,
	sector_name VARCHAR(50),
	CONSTRAINT unique_sector_name UNIQUE (sector_name)
	);

CREATE TABLE subcategory(
	subcategory_ID SERIAL PRIMARY KEY,
	subcategory_name VARCHAR(50)
	);

CREATE TABLE subcategory_unvalidated(
	subcategory_ID SERIAL PRIMARY KEY,
	subcategory_name VARCHAR(50)
	);

CREATE TABLE category(
	category_ID SERIAL PRIMARY KEY,
	category_name VARCHAR(50),
	CONSTRAINT unique_category_name UNIQUE (category_name)
	);

CREATE TABLE category_unvalidated(
	category_ID SERIAL PRIMARY KEY,
	category_name VARCHAR(50),
	CONSTRAINT unique_category_name UNIQUE (category_name)
	);

CREATE TABLE photo (
	photo_ID SERIAL PRIMARY KEY,
	photo LONGBLOB
	);

CREATE TABLE sector_category(
	sector_category_ID SERIAL PRIMARY KEY,
	sector_ID BIGINT UNSIGNED NOT NULL,
	category_ID BIGINT UNSIGNED NOT NULL,
	CONSTRAINT fk_sector_category_sector FOREIGN KEY (sector_ID) REFERENCES sector (sector_ID)
		ON UPDATE CASCADE,
	CONSTRAINT fk_sector_category_category FOREIGN KEY (category_ID) REFERENCES category (category_ID)
		ON UPDATE CASCADE
	);

CREATE TABLE product_size(
	product_size_ID SERIAL PRIMARY KEY,
	size_type VARCHAR(5) NOT NULL
);

CREATE TABLE product_size_unvalidated(
	product_size_ID SERIAL PRIMARY KEY,
	size_type VARCHAR(5) NOT NULL
);

CREATE TABLE product(
	product_ID SERIAL PRIMARY KEY,	
	product_name VARCHAR(30) NOT NULL,
	producer VARCHAR(30) NOT NULL	
	
	);


CREATE TABLE product_specific(
	product_specific_ID SERIAL PRIMARY KEY,
	product_ID BIGINT UNSIGNED NOT NULL,
	
	barcode VARCHAR(15) NOT NULL,
	product_description VARCHAR(30),
	photo_ID BIGINT UNSIGNED NOT NULL,
	product_size DECIMAL(4,2) NOT NULL,
	product_size_ID BIGINT UNSIGNED NOT NULL,

	CONSTRAINT unique_product_specific UNIQUE (product_ID, product_size),

	CONSTRAINT fk_product_specific_photo FOREIGN KEY (photo_ID) REFERENCES photo (photo_ID)
		ON UPDATE CASCADE,
	
	CONSTRAINT fk_product_specific_product FOREIGN KEY (product_ID) REFERENCES product (product_ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	
	CONSTRAINT fk_product_store_product_size FOREIGN KEY (product_size_ID) REFERENCES product_size(product_size_ID)
	);

CREATE TABLE product_store (
	product_store_ID SERIAL PRIMARY KEY,
	product_specific_ID BIGINT UNSIGNED NOT NULL,
	store_ID BIGINT UNSIGNED NOT NULL,
	user_ID BIGINT UNSIGNED NOT NULL,
	price DECIMAL(7,2) NOT NULL,
	price_change_date DATE NOT NULL,
	average_price DECIMAL(7,2),
	product_updates BIGINT DEFAULT 1,

	CONSTRAINT unique_product_store UNIQUE (product_specific_ID, store_ID),

	CONSTRAINT fk_product_store_store FOREIGN KEY (store_ID) REFERENCES store (store_ID)
		ON UPDATE CASCADE,
	CONSTRAINT fk_product_store_user FOREIGN KEY (user_ID) REFERENCES user (user_ID)
		ON UPDATE CASCADE
);

CREATE TABLE category_subcategory(
	category_subcategory_ID SERIAL PRIMARY KEY,
	category_ID BIGINT UNSIGNED NOT NULL,
	subcategory_ID BIGINT UNSIGNED NOT NULL,
	CONSTRAINT fk_category_subcategory_category FOREIGN KEY (category_ID) REFERENCES category (category_ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT fk_category_subcategory_subcategory FOREIGN KEY (subcategory_ID) REFERENCES subcategory (subcategory_ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
	);

CREATE TABLE subcategory_product(
	subcategory_product_ID SERIAL PRIMARY KEY,
	subcategory_ID BIGINT UNSIGNED,
	product_ID BIGINT UNSIGNED,

	CONSTRAINT fk_subcategory_product_subcategory FOREIGN KEY (subcategory_ID) REFERENCES subcategory (subcategory_ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT fk_subcategory_product_product FOREIGN KEY (product_ID) REFERENCES product (product_ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
	);

-- User can be an admin or just a regular user. Admins have to be added by other admins.
CREATE TABLE user_type (
	user_type_ID SERIAL PRIMARY KEY,
	user_type_description VARCHAR(10)
)


