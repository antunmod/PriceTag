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

CREATE TABLE sector(
	sector_ID SERIAL PRIMARY KEY,
	sector_name VARCHAR(50),
	CONSTRAINT unique_sector_name UNIQUE (sector_name)
	);

CREATE TABLE subcategory(
	subcategory_ID SERIAL PRIMARY KEY,
	subcategory_name VARCHAR(50)
	);

CREATE TABLE category(
	category_ID SERIAL PRIMARY KEY,
	category_name VARCHAR(50),
	CONSTRAINT unique_category_name UNIQUE (category_name)
	);

CREATE TABLE sector_category(
	sector_category_ID SERIAL PRIMARY KEY,
	sector_ID BIGINT UNSIGNED,
	category_ID BIGINT UNSIGNED,
	CONSTRAINT fk_sector_category_sector FOREIGN KEY (sector_ID) REFERENCES sector (sector_ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT fk_sector_category_category FOREIGN KEY (category_ID) REFERENCES category (category_ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
	);

CREATE TABLE product_size(
	product_size_ID SERIAL PRIMARY KEY,
	size_type VARCHAR(5) NOT NULL
);

CREATE TABLE product(
	product_ID SERIAL PRIMARY KEY,	
	product_name VARCHAR(30) NOT NULL,
	producer VARCHAR(30) NOT NULL,
	-- product_description VARCHAR(50),
	category_ID BIGINT UNSIGNED,
	subcategory_ID BIGINT UNSIGNED,
	
	
	
	CONSTRAINT fk_product_subcategory FOREIGN KEY (subcategory_ID) REFERENCES subcategory (subcategory_ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT fk_product_category FOREIGN KEY (category_ID) REFERENCES category (category_ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
	
	
	);

CREATE TABLE product_store(
	product_store_ID SERIAL PRIMARY KEY,
	product_ID BIGINT UNSIGNED,
	store_ID BIGINT UNSIGNED,
	user_ID BIGINT UNSIGNED,
	barcode BIGINT NOT NULL,
	product_size INTEGER NOT NULL,
	product_size_ID BIGINT UNSIGNED,
	price DECIMAL(7,2) NOT NULL,
	price_change_date DATE NOT NULL,
	average_price DECIMAL(7,2),
	product_updates BIGINT DEFAULT 0,

	CONSTRAINT unique_product_store UNIQUE (product_ID, store_ID, product_size),

	CONSTRAINT fk_product_store_user FOREIGN KEY (user_ID) REFERENCES user (user_ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT fk_product_store_product FOREIGN KEY (product_ID) REFERENCES product (product_ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT fk_product_store_store FOREIGN KEY (store_ID) REFERENCES store (store_ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,

	CONSTRAINT fk_product_store_product_size FOREIGN KEY (product_size_ID) REFERENCES product_size(product_size_ID)
	);

CREATE TABLE category_subcategory(
	category_subcategory_ID SERIAL PRIMARY KEY,
	category_ID BIGINT UNSIGNED,
	subcategory_ID BIGINT UNSIGNED,
	CONSTRAINT fk_category_subcategory_category FOREIGN KEY (category_ID) REFERENCES category (category_ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT fk_category_subcategory_subcategory FOREIGN KEY (subcategory_ID) REFERENCES subcategory (subcategory_ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
	);

CREATE TABLE suggested_categorization (
	sector_name VARCHAR(30),
	category_name VARCHAR (50),
	subcategory_name VARCHAR (50)
);

-- User can be an admin or just a regular user. Admins have to be added by other admins.
CREATE TABLE user_type (
	user_type_ID SERIAL PRIMARY KEY,
	user_type_description VARCHAR(10)
);

