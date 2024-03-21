CREATE DATABASE IF NOT EXISTS spa_website;

USE spa_website;

CREATE TABLE `user` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code varchar(50) UNIQUE,
    phone VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    address VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    avatar VARCHAR(255),
    referrer_id INT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME,
    deleted_at DATETIME,
    FOREIGN KEY (`referrer_id`) REFERENCES `user`(`id`)
);

CREATE TABLE `role` (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE `user_role` (
    user_id INT,
    role_id INT,
    PRIMARY KEY (`user_id` , `role_id`),
    FOREIGN KEY (`user_id`)
        REFERENCES `user` (`id`),
    FOREIGN KEY (`role_id`)
        REFERENCES `role` (`id`)
);

INSERT INTO `user` (id, code, phone, name, email, password, referrer_id)  VALUES
(1, '111','0988123123', 'Erica Smith', 'erica@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', NULL),
(2, '222', '0987324078', 'Cloe Macy', 'cloe@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', NULL),
(3, '333', '0966566956', 'Lily Cox', 'lily@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 1),
(4, '444', '0955433452', 'Kendal Wild', 'kendal@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 1),
(5, '555', '0966248346', 'Sarah Collin', 'sarah@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 2),
(6, '666', '0986252956', 'Michell Owen', 'michell@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 3),
(7, '777', '0943134235', 'Gretchen Smith', 'gretchen@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 3),
(8, '888', '0954023283', 'Robin Spark', 'robin@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 4),
(9, '999','0998345125', 'Monica Geller', 'monica@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 6),
(10, '123', '0998433476', 'Anna Sanchez', 'anna@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 6),
(11, '234', '0998431756', 'Dorothy Dowling', 'dorothy@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 9),
(12, '345', '0998473453', 'Iris Young', 'iris@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 9),
(13, '456', '0998393853', 'Elisabeth Chancellor', 'elisabeth@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 4),
(14, '567', '0998234976', 'Marlene Gibson', 'marlene@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 11),
(15, '678', '0998436362', 'Helen McAlexander', 'helen@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 11),
(16, '789', '0998333263', 'Rhonda Jenkins', 'rhonda@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 15),
(17, '100', '0998878252', 'Mary Johnson', 'mary@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 15),
(18, '200', '0998789234', 'Angela Lewis', 'angela@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 15),
(19, '300', '0998533453', 'Mary Jenkins', 'maryjenk@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 17),
(20, '400', '0998444742', 'Emma McNeill', 'emma@emailcom', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 17)
;

INSERT INTO `user` (id, phone, name, email, password) VALUES
(21, "0988 777 666", "John Doe", "admin@email.com", "$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS"),
(22, "0988 555 444", "Robert Fox", "employee@email.com", "$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS"),
(23, "0988 222 333", "Emma Smith", "employee2@email.com", "$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS");

INSERT INTO role (name) VALUES
('ADMIN'), ('EMPLOYEE'), ('CUSTOMER');

INSERT INTO `user_role` (user_id, role_id) VALUES
(1, 3), (2, 2), (3, 3), (4, 3), (5, 3), (6, 3), (7, 3), (8, 3), (9, 3), (10, 3),
(11, 3), (12, 3), (13, 3), (14, 3), (15, 3), (16, 3), (17, 3), (18, 3), (19, 3), (20, 3),
(21, 1), (22, 2), (23, 2);

CREATE TABLE `category` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) UNIQUE NOT NULL,
    icon VARCHAR(255),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME,
    deleted_at DATETIME
);

INSERT INTO `category` (name) VALUES 
('Hair'), ('Makeup'), ('Nails'), ('Facial'), ('Coloring'), ('Spa'), ('Waxing'), ('Massage');

CREATE TABLE `service` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    thumbnail VARCHAR(255),
    description VARCHAR(255),
    base_price DECIMAL(16, 2),
    discount DECIMAL(16, 2),
    category_id INT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME,
    deleted_at DATETIME,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

INSERT INTO `service` (name, description, base_price, category_id) VALUES 
('Standard Haircut', 'A haircut using clippers to achieve an ultra-short design. (30 min)', 300000, 1),
('Kids Haircut', 'A haircut on a child age 10 & under. (30 min)', 200000, 1),
('Clipper Cut', 'A haircut using clippers to achieve an ultra-short design. (30 min)', 300000, 1),
('Bang Trim', 'A trim on the bang area. Shampoo, conditioner and scalp massage not included. (15 min)', 190000, 1),
('Signature Haircut', 'A haircut, trim, shape on anyone over the age of 10 with a customized conditioning treatment. (1 hr 15 min)', 490000, 1),
('All-Over Color', 'An all-over application of a single hair color from roots to ends. (1 hr 45 min)', 300000, 1),
('Full Root Touch-up', 'An application of hair color to the root area only. (1 hr 45 min)', 200000, 1),
('Total Blonde', 'An all-over or root touch-up application of lightener to achieve the perfect shade of blonde. (2 hrs 15 min)', 190000, 1),

('Complimentary Touch-up', 'Complete your service with this quick touch up, or pop in and meet with an available service professional or retail beauty advisor. (15 min)', 300000, 2),
('Everyday Makeup Application', 'Put your fresh face forward. This make up application will have you looking your best. (45 min)', 390000, 2),
('Formal Makeup Application', 'A make up application that includes enhanced make up techniques for a more dramatic look. (60 min)', 490000, 2),
('Cocktail Makeup', 'A make up application that includes enhanced make up techniques for a more dramatic look. (60 min)', 550000, 2),
('Makeup Lesson', 'This informative and interactive hands on experience is designed to educate you to achieve the look you''ve always wanted such as the perfect smoky eye.(2 hr 30 min)', 1500000, 2),

('Cherie Manicure', 'Delicate cuticle work, buffing, and shaping. To finish, a relaxing hand massage, topped off with a perfect polish. (60 min)', 500000, 3),
('Buff Manicure', 'Our natural look manicure – no polish, but all of the pampering. (20 min)', 390000, 3),
('Gel Manicure', 'All the features of our Cherie manicure, but finish with nontoxic Gel Polish instead. (40 min)', 490000, 3),
('Cherie Pedicure', 'Our natural look pedi – no polish, but all of the pampering. Revitalize tired feet. (30 min)', 390000, 3),
('Kids Haircut', 'Our natural look pedi – no polish, but all of the pampering. Revitalize tired feet. (30 min)', 490000, 3),
('Gel Pedicure', 'All the features of our Cherie manicure, but finish with nontoxic Gel Polish instead. (60 min)', 490000, 3)
;

CREATE TABLE `commission_policy` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50) UNIQUE NOT NULL,
	start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    max_referral_levels INT NOT NULL
);

CREATE TABLE `commission_tier` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    policy_id INT NOT NULL,
    level INT NOT NULL,
    commission_rate DECIMAL(5, 2),
    FOREIGN KEY (policy_id) REFERENCES commission_policy(id)
);

INSERT INTO `commission_policy` (code, start_date, end_date, max_referral_levels) VALUES
(CONCAT( 'PLV-03-1023-',LEFT(UUID(), 8) ), '2023-10-01 00:00:00', '2023-11-30 23:59:59', 3),
(CONCAT( 'PLV-04-1223-',LEFT(UUID(), 8) ), '2023-12-01 00:00:00', '2023-12-31 23:59:59', 4),
(CONCAT( 'PLV-03-1223-',LEFT(UUID(), 8) ), '2023-12-01 00:00:00', '2023-12-31 23:59:59', 3),
(CONCAT( 'PLV-05-0124-',LEFT(UUID(), 8) ), '2024-01-01 00:00:00', '2024-01-31 23:59:59', 5),
(CONCAT( 'PLV-05-0224-',LEFT(UUID(), 8) ), '2024-02-01 00:00:00', '2024-02-29 23:59:59', 5),
(CONCAT( 'PLV-05-0324-',LEFT(UUID(), 8) ), '2024-03-01 00:00:00', '2024-03-31 23:59:59', 5),
(CONCAT( 'PLV-04-0324-',LEFT(UUID(), 8) ), '2024-03-01 00:00:00', '2024-03-31 23:59:59', 4)
;

INSERT INTO commission_tier (policy_id, level, commission_rate) VALUES
(1, 1, 6), (1, 2, 3), (1, 3, 1), 
(2, 1, 5), (2, 2, 3), (2, 3, 2), (2, 4, 1),
(3, 1, 4), (3, 2, 2), (3, 3, 1), 
(4, 1, 5), (4, 2, 3), (4, 3, 2), (4, 4, 1), (4, 5, 1),
(5, 1, 6), (5, 2, 4), (5, 3, 2), (5, 4, 1), (5, 5, 1),
(6, 1, 4), (6, 2, 2), (6, 3, 1), (6, 4, 1), (6, 5, 1),
(7, 1, 5), (7, 2, 3), (7, 3, 2), (7, 4, 1)
;

CREATE TABLE `service_policy_assignment` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    service_id INT,
    policy_id INT,
    active BOOL DEFAULT FALSE,
    FOREIGN KEY (service_id) REFERENCES service(id),
    FOREIGN KEY (policy_id) REFERENCES commission_policy(id)
);

INSERT INTO `service_policy_assignment` (policy_id, service_id, active)  VALUES 
(1, 1, FALSE), (1, 2, FALSE), (1, 3, FALSE), (1, 4, FALSE), (1, 5, FALSE), (1, 6, FALSE), (1, 7, FALSE), (1, 8, FALSE), (1, 9, FALSE), (1, 10, FALSE),
(1, 11, FALSE), (1, 12, FALSE), (1, 13, FALSE), (1, 14, FALSE), (1, 15, FALSE), (1, 16, FALSE), (1, 17, FALSE), (1, 18, FALSE), (1, 19, FALSE), 

(2, 1, FALSE), (2, 2, FALSE), (2, 3, FALSE), (2, 4, FALSE), (2, 5, FALSE), (2, 6, FALSE), (2, 7, FALSE), (2, 8, FALSE), (2, 9, FALSE), (2, 10, FALSE),
(2, 11, FALSE), (2, 12, FALSE), (2, 13, FALSE), (2, 14, FALSE), (2, 15, FALSE), (2, 16, FALSE), (2, 17, FALSE), (2, 18, FALSE), (2, 19, FALSE), 

(3, 1, FALSE), (3, 2, FALSE), (3, 3, FALSE), (3, 4, FALSE), (3, 5, FALSE), (3, 6, FALSE), (3, 7, FALSE), (3, 8, FALSE), (3, 9, FALSE), (3, 10, FALSE),
(3, 11, FALSE), (3, 12, FALSE), (3, 13, FALSE), (3, 14, FALSE), (3, 15, FALSE), (3, 16, FALSE), (3, 17, FALSE), (3, 18, FALSE), (3, 19, FALSE), 

(4, 1, FALSE), (4, 2, FALSE), (4, 3, FALSE), (4, 4, FALSE), (4, 5, FALSE), (4, 6, FALSE), (4, 7, FALSE), (4, 8, FALSE), (4, 9, FALSE), (4, 10, FALSE),
(4, 11, FALSE), (4, 12, FALSE), (4, 13, FALSE), (4, 14, FALSE), (4, 15, FALSE), (4, 16, FALSE), (4, 17, FALSE), (4, 18, FALSE), (4, 19, FALSE), 

(5, 1, FALSE), (5, 2, FALSE), (5, 3, FALSE), (5, 4, FALSE), (5, 5, FALSE), (5, 6, FALSE), (5, 7, FALSE), (5, 8, FALSE), (5, 9, FALSE), (5, 10, FALSE),
(5, 11, FALSE), (5, 12, FALSE), (5, 13, FALSE), (5, 14, FALSE), (5, 15, FALSE), (5, 16, FALSE), (5, 17, FALSE), (5, 18, FALSE), (5, 19, FALSE), 

(6, 1, FALSE), (6, 2, FALSE), (7, 3, FALSE), (7, 4, FALSE), (6, 5, FALSE), (6, 6, FALSE), (6, 7, FALSE), (6, 8, FALSE), (6, 9, FALSE), (6, 10, FALSE),
(7, 11, FALSE), (7, 12, FALSE), (7, 13, FALSE), (7, 14, FALSE), (6, 15, FALSE), (7, 16, FALSE), (6, 17, FALSE), (6, 18, FALSE), (6, 19, FALSE)
;

CREATE TABLE `transaction` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    transaction_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(16, 2),
    FOREIGN KEY (customer_id) REFERENCES user(id)
);

CREATE TABLE `transaction_service` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    transaction_id INT,
    service_id INT,
    price DECIMAL(16, 2),
    FOREIGN KEY (transaction_id) REFERENCES `transaction`(id),
    FOREIGN KEY (service_id) REFERENCES service(id)
);

CREATE TABLE `commission` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    recipient_id INT,
    total DECIMAL(16, 2),
    status INT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (recipient_id) REFERENCES user(id)
);

CREATE TABLE `commission_service` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    commission_id INT,
    transaction_service_id INT,
    commission_policy_id INT,
    amount DECIMAL(16, 2),
    FOREIGN KEY (commission_id) REFERENCES commission(id),
    FOREIGN KEY (transaction_service_id) REFERENCES transaction_service(id),
    FOREIGN KEY (commission_policy_id) REFERENCES commission_policy(id)
);

CREATE TABLE `event` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    description VARCHAR(255),
    max_discount DECIMAL(5, 2),
    banner VARCHAR(255),
    status INT DEFAULT 0
);

CREATE TABLE `event_service` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT,
    service_id INT,
    discount DECIMAL(5, 2),
    FOREIGN KEY (event_id) REFERENCES `event`(id),
    FOREIGN KEY (service_id) REFERENCES service(id)
);




DROP PROCEDURE IF EXISTS get_parents;
DELIMITER //
CREATE PROCEDURE get_parents (IN user_id INT)
BEGIN
	WITH RECURSIVE parents (id, name, phone, email, referrer_id, user_level)
    AS (
		SELECT id, name, phone, email, referrer_id, 0
        FROM user
        WHERE id = user_id
        
        UNION ALL
        
        SELECT u.id, u.name, u.phone, u.email, u.referrer_id, p.user_level + 1
        FROM user u INNER JOIN parents p ON u.id = p.referrer_id
        WHERE p.user_level < 10
	)
    SELECT * FROM parents;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS get_children;
DELIMITER //
CREATE PROCEDURE get_children (IN user_id INT)
BEGIN
	WITH RECURSIVE children (id, name, phone, email, referrer_id, user_level)
    AS (
		SELECT id, name, phone, email, referrer_id, 0
        FROM user
        WHERE id = user_id
        
        UNION ALL
        
        SELECT u.id, u.name, u.phone, u.email, u.referrer_id, p.user_level + 1
        FROM user u INNER JOIN children p ON u.referrer_id = p.id
        WHERE p.user_level < 10
	)
    SELECT * FROM children;
END //
DELIMITER ;

-- CALL get_parents (19);
-- CALL get_children (6);
