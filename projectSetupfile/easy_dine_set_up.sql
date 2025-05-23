DROP SCHEMA IF EXISTS easy_dine;

CREATE SCHEMA easy_dine;

use easy_dine;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE IF NOT EXISTS users(
	id BIGINT NOT NULL AUTO_INCREMENT,
    name varchar(45) DEFAULT NULL,
    email varchar(45) NOT NULL,
    password varchar(70) NOT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    
    PRIMARY KEY (id),
    UNIQUE (email)
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS roles(
	id BIGINT NOT NULL AUTO_INCREMENT,
    role varchar(20) NOT NULL CHECK (role IN ('ADMIN','OWNER','CUSTOMER')),
    user_id BIGINT NOT NULL,
    
	PRIMARY KEY (id),
    UNIQUE (role, user_id),
    FOREIGN KEY (user_id) REFERENCES users (id) 
	ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1;

-- INSERT INTO roles(role) VALUES('ROLE_ADMIN'), ('ROLE_OWNER'), ('ROLE_CUSTOMER');


-- CREATE TABLE IF NOT EXISTS user_roles(
-- 	user_id int NOT NULL,
--     role_id int NOT NULL,
--     
--     PRIMARY KEY (user_id, role_id),
--     
--     FOREIGN KEY (user_id) REFERENCES users (id) 
--     ON DELETE CASCADE ON UPDATE NO ACTION,
--     
--     FOREIGN KEY (role_id) REFERENCES roles (id) 
--     ON DELETE CASCADE ON UPDATE NO ACTION
-- ) ENGINE=InnoDB AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS restaurants(
	id BIGINT NOT NULL AUTO_INCREMENT,
    name varchar(128) NOT NULL,
    image varchar(256) DEFAULT NULL,
    owner_id BIGINT NOT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    
    UNIQUE (name),
    PRIMARY KEY (id),
    
    FOREIGN KEY (owner_id) REFERENCES users (id) 
	ON DELETE CASCADE ON UPDATE NO ACTION

) ENGINE=InnoDB AUTO_INCREMENT=1;


-- CREATE TABLE IF NOT EXISTS owners(
-- 	user_id int NOT NULL,
--     restaurant_id int NOT NULL,
--     
--      PRIMARY KEY (user_id, restaurant_id),
--     
--     FOREIGN KEY (user_id) REFERENCES users (id) 
--     ON DELETE CASCADE ON UPDATE NO ACTION,
--     
--     FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) 
--     ON DELETE CASCADE ON UPDATE NO ACTION
--     
-- )ENGINE=InnoDB AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS cities(
	id BIGINT NOT NULL AUTO_INCREMENT,
    city varchar(45) NOT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    
	PRIMARY KEY (id),
    UNIQUE (city)
) ENGINE=InnoDB AUTO_INCREMENT=1;


INSERT INTO cities(city) VALUES('Hyderabad'), 
	('Banglore'), 
    ('Mumbai'), 
    ('Kolkata'),
    ('Delhi'),
    ('Kamareddy'),
    ('Pune'),
    ('Chennai'),
    ('Lucknow'),
    ('Ahmedabad'),
    ('Jaipur'),
    ('Bhopal'),
    ('Chandigarh');


CREATE TABLE IF NOT EXISTS branches(
	restaurant_id BIGINT NOT NULL,
    city_id BIGINT NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT,
    
    UNIQUE (restaurant_id, city_id),
    PRIMARY KEY (id),
    
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) 
    ON DELETE CASCADE ON UPDATE NO ACTION,
    
    FOREIGN KEY (city_id) REFERENCES cities (id) 
    ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS food_categories(
	id BIGINT NOT NULL AUTO_INCREMENT,
    name varchar(128) NOT NULL,
    image varchar(256) DEFAULT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    
    UNIQUE (name),
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1;


INSERT INTO food_categories(name) VALUES('Fried rice'),
	('Noodles'),
	('Non-veg starters'),
    ('Veg Starters'),
    ('Main course'),
    ('Burger'),
    ('Pizza'),
    ('Soup'),
    ('Non-veg curries'),
    ('Veg curries'),
    ('Roti & paratha');

CREATE TABLE IF NOT EXISTS dishes(
	id BIGINT NOT NULL AUTO_INCREMENT,
    name varchar(128) NOT NULL,
    category_id BIGINT NOT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    
    UNIQUE (name),
    PRIMARY KEY (id),
    
	FOREIGN KEY (category_id) REFERENCES food_categories (id) 
    ON DELETE CASCADE ON UPDATE NO ACTION
    
) ENGINE=InnoDB AUTO_INCREMENT=1;


-- Get the category IDs for each category
SELECT 
    id INTO @friedRiceCategoryId
FROM food_categories
WHERE name = 'Fried rice';

SELECT 
    id INTO @noodlesCategoryId
FROM food_categories
WHERE name = 'Noodles';

SELECT 
    id INTO @nonVegStartersCategoryId
FROM food_categories
WHERE name = 'Non-veg starters';

SELECT 
    id INTO @vegStartersCategoryId
FROM food_categories
WHERE name = 'Veg Starters';

SELECT 
    id INTO @mainCourseCategoryId
FROM food_categories
WHERE name = 'Main course';

SELECT 
    id INTO @burgerCategoryId
FROM food_categories
WHERE name = 'Burger';

SELECT 
    id INTO @pizzaCategoryId
FROM food_categories
WHERE name = 'Pizza';

SELECT 
    id INTO @soupCategoryId
FROM food_categories
WHERE name = 'Soup';

SELECT 
    id INTO @nonVegCurriesCategoryId
FROM food_categories
WHERE name = 'Non-veg curries';

SELECT 
    id INTO @vegCurriesCategoryId
FROM food_categories
WHERE name = 'Veg curries';

SELECT 
    id INTO @rotiParathaCategoryId
FROM food_categories
WHERE name = 'Roti & paratha';

-- Add dishes for each category
INSERT INTO dishes (name, category_id) VALUES
    ('Vegetable Fried Rice', @friedRiceCategoryId),
    ('Chicken Fried Rice', @friedRiceCategoryId),
    ('Egg Fried Rice', @friedRiceCategoryId),
    
    ('Vegetable Noodles', @noodlesCategoryId),
    ('Chicken Noodles', @noodlesCategoryId),
    ('Egg Noodles', @noodlesCategoryId),
    
    ('Chicken Wings', @nonVegStartersCategoryId),
    ('Roasted Chicken', @nonVegStartersCategoryId),
    ('Chicken Kebab', @nonVegStartersCategoryId),
    
    ('Paneer Tikka', @vegStartersCategoryId),
    ('Vegetable Spring Rolls', @vegStartersCategoryId),
    ('Crispy Corn', @vegStartersCategoryId),
    
    ('Chicken Biryani', @mainCourseCategoryId),
    ('Veg Biryani', @mainCourseCategoryId),
    ('Mutton Biryani', @mainCourseCategoryId),
    
    ('Chicken Burger', @burgerCategoryId),
    ('Mushroom Swiss Burger', @burgerCategoryId),
    ('Veggie Burger', @burgerCategoryId),
    
    ('Margherita Pizza', @pizzaCategoryId),
    ('Pepperoni Pizza', @pizzaCategoryId),
    ('BBQ Chicken Pizza', @pizzaCategoryId),
    
    ('Tomato Basil Soup', @soupCategoryId),
    ('Chicken Noodle Soup', @soupCategoryId),
    ('Mix Vegetables Soup', @soupCategoryId),
    
    ('Butter Chicken', @nonVegCurriesCategoryId),
    ('Chicken Tikka', @nonVegCurriesCategoryId),
    ('Fish Curry', @nonVegCurriesCategoryId),
    
    ('Palak Paneer', @vegCurriesCategoryId),
    ('Baingan Bharta', @vegCurriesCategoryId),
    ('Dal Makhani', @vegCurriesCategoryId),
    
    ('Roti', @rotiParathaCategoryId),
    ('Phulka', @rotiParathaCategoryId),
    ('Paratha', @rotiParathaCategoryId),
    ('Butter Naan', @rotiParathaCategoryId),
    ('Tandoori Roti', @rotiParathaCategoryId);


CREATE TABLE IF NOT EXISTS table_types(
	id BIGINT NOT NULL AUTO_INCREMENT,
    chairs int NOT NULL,
    name varchar(45) NOT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    
	PRIMARY KEY (id),
    UNIQUE (name, chairs)
) ENGINE=InnoDB AUTO_INCREMENT=1;

INSERT INTO table_types (chairs, name) VALUES 
	(2, 'Standard Table'),
    (3, 'Standard Table'),
    (4, 'Standard Table'),
    
    (2, 'Outdoor Table'),
    (3, 'Outdoor Table'),
    (4, 'Outdoor Table'),
    
    (2, 'Corner Table'),
    (3, 'Corner Table'),
    (4, 'Corner Table'),
    
    (2, 'Private Table'),
    (3, 'Private Table'),
    (4, 'Private Table'),
    
	(2, 'Round Table'),
    (3, 'Round Table'),
    (4, 'Round Table'),
    
    (2, 'Window-Side Table'),
    (3, 'Window-Side Table'),
    (4, 'Window-Side Table'),
    
    (2, 'High-Top Table'),
    (3, 'High-Top Table'),
    (4, 'High-Top Table');
    

CREATE TABLE IF NOT EXISTS branch_tables(
	branch_id BIGINT NOT NULL,
    table_id BIGINT NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT,
    
    PRIMARY KEY (id),
    
    FOREIGN KEY (branch_id) REFERENCES branches (id) 
    ON DELETE CASCADE ON UPDATE NO ACTION,
    
    FOREIGN KEY (table_id) REFERENCES table_types (id) 
    ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1;



CREATE TABLE IF NOT EXISTS menu(
	restaurant_id BIGINT NOT NULL,
    dish_id BIGINT NOT NULL,
    price int NOT NULL ,
    id BIGINT NOT NULL AUTO_INCREMENT,
    version BIGINT NOT NULL DEFAULT 0,
    
    UNIQUE (restaurant_id, dish_id),
    PRIMARY KEY(id),
    
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) 
    ON DELETE CASCADE ON UPDATE NO ACTION,
    
    FOREIGN KEY (dish_id) REFERENCES dishes (id) 
    ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS bookings(
	branch_table_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    start_timestamp DATETIME NOT NULL,
    end_timestamp DATETIME NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT, 
    
	PRIMARY KEY (id),
    
    UNIQUE(branch_table_id, start_timestamp),
    
    FOREIGN KEY (branch_table_id) REFERENCES branch_tables (id) 
    ON DELETE CASCADE ON UPDATE NO ACTION,
    
    FOREIGN KEY (customer_id) REFERENCES users (id) 
    ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS notifications(
    customer_id BIGINT NOT NULL,
    start_timestamp DATETIME NOT NULL,
    end_timestamp DATETIME NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT, 
    
	PRIMARY KEY (id),
    
    FOREIGN KEY (customer_id) REFERENCES users (id) 
    ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1;




    

SET FOREIGN_KEY_CHECKS = 1;
