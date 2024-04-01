DROP DATABASE IF EXISTS spa_website;

CREATE DATABASE IF NOT EXISTS spa_website;

USE spa_website;

CREATE TABLE `user` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code varchar(50) UNIQUE,
    phone VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
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
(1, '111','0988123123', 'Erica Smith', 'erica@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', NULL),
(2, '222', '0987324078', 'Cloe Macy', 'cloe@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', NULL),
(3, '333', '0966566956', 'Lily Cox', 'lily@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 1),
(4, '444', '0955433452', 'Kendal Wild', 'kendal@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 1),
(5, '555', '0966248346', 'Sarah Collin', 'sarah@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 2),
(6, '666', '0986252956', 'Michell Owen', 'michell@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 3),
(7, '777', '0943134235', 'Gretchen Smith', 'gretchen@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 3),
(8, '888', '0954023283', 'Robin Spark', 'robin@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 4),
(9, '999','0998345125', 'Monica Geller', 'monica@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 6),
(10, '123', '0998433476', 'Anna Sanchez', 'anna@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 6),
(11, '234', '0998431756', 'Dorothy Dowling', 'dorothy@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 9),
(12, '345', '0998473453', 'Iris Young', 'iris@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 9),
(13, '456', '0998393853', 'Elisabeth Chancellor', 'elisabeth@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 4),
(14, '567', '0998234976', 'Marlene Gibson', 'marlene@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 11),
(15, '678', '0998436362', 'Helen McAlexander', 'helen@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 11),
(16, '789', '0998333263', 'Rhonda Jenkins', 'rhonda@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 15),
(17, '100', '0998878252', 'Mary Johnson', 'mary@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 15),
(18, '200', '0998789234', 'Angela Lewis', 'angela@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 15),
(19, '300', '0998533453', 'Mary Jenkins', 'maryjenk@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 17),
(20, '400', '0998444742', 'Emma McNeill', 'emma@email.com', '$2a$10$iIwyJzmFL1UQuhzm5uNXoO3XFHa0Tyx1cYKOwDjauZRVf84zZYhfS', 17)
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

INSERT INTO `user_role` (user_id, role_id) VALUES
(21, 2), (21, 3);

CREATE TABLE `category` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) UNIQUE NOT NULL,
    icon VARCHAR(255),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME,
    deleted_at DATETIME
);

INSERT INTO `category` (name, icon) VALUES 
('Hair', 'https://res.cloudinary.com/dxcyeb8km/image/upload/v1711720014/hair_qv9pdu.png'), 
('Makeup', 'https://res.cloudinary.com/dxcyeb8km/image/upload/v1711720018/makeup_bwb2lv.png'), 
('Nails', 'https://res.cloudinary.com/dxcyeb8km/image/upload/v1711720011/nail_xcr90x.png'), 
('Facial', 'https://res.cloudinary.com/dxcyeb8km/image/upload/v1711720009/facial_xcevc7.png'), 
('Coloring', 'https://res.cloudinary.com/dxcyeb8km/image/upload/v1711720022/coloring_iz1ije.png'), 
('Spa', 'https://res.cloudinary.com/dxcyeb8km/image/upload/v1711720033/spa_rjjnbj.png'), 
('Waxing', 'https://res.cloudinary.com/dxcyeb8km/image/upload/v1711720025/wax_hkuedf.png'), 
('Massage', 'https://res.cloudinary.com/dxcyeb8km/image/upload/v1711720029/massage_w2e1vw.png');

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
('Gel Pedicure', 'All the features of our Cherie manicure, but finish with nontoxic Gel Polish instead. (60 min)', 490000, 3),

('Classic Eyelash Extensions', 'A natural-looking lash enhancement with one lash extension applied to each natural lash. (1 hr 30 min)', 800000, 4),
('Hybrid Eyelash Extensions', 'A combination of classic and volume lash extensions for a fuller look. (2 hrs)', 1000000, 4),
('Volume Eyelash Extensions', 'Creates a dramatic, voluminous lash look by applying multiple lash extensions to each natural lash. (2 hrs 30 min)', 1200000, 4),
('Mega Volume Eyelash Extensions', 'The fullest lash extensions we offer, creating a dramatic, glamorous look. (3 hrs)', 1500000, 4),
('Lash Lift & Tint', 'Lifts and darkens your natural lashes for a mascara-like effect, without the extensions. (45 min)', 500000, 4),

('Brows', 'Waxing to shape and define your brows. (15 min)', 150000, 5),
('Lip', 'Precise waxing to remove unwanted hair above the lip. (10 min)', 100000, 5),
('Underarms', 'Waxing to remove hair from the underarm area. (15 min)', 180000, 5),
('Full Legs', 'Waxing to remove hair from the knees to the bikini line. (45 min)', 450000, 5),
('Brazilian Wax', 'Waxing to remove all hair from the bikini area. (30 min)', 350000, 5),

('Deep Cleansing Facial', 'A thorough cleansing facial to remove impurities and refine pores. (60 min)', 600000, 6),
('Hydrafacial', 'A non-invasive, hydrating facial treatment that cleanses, extracts, and hydrates the skin. (60 min)', 800000, 6),
('Anti-Aging Facial', 'A facial treatment targeted at reducing fine lines and wrinkles. (75 min)', 900000, 6),
('Brightening Facial', 'A facial treatment to even skin tone and reduce hyperpigmentation. (60 min)', 700000, 6),
('Chemical Peel', 'A facial treatment using a chemical solution to exfoliate the skin and improve texture. (30 min)', 500000, 6),

('Swedish Massage', 'A relaxing massage that uses long strokes to improve circulation and relieve tension. (60 min)', 600000, 7),
('Deep Tissue Massage', 'A massage that focuses on deeper muscle layers to relieve chronic pain and tension. (60 min)', 700000, 7),
('Hot Stone Massage', 'A massage that uses heated stones to warm and relax the muscles. (60 min)', 800000, 7),
('Aromatherapy Massage', 'A massage that uses essential oils to create a relaxing and therapeutic experience. (60 min)', 750000, 7),

('Brow Threading', 'A hair removal technique that uses thread to remove unwanted hair. (15 min)', 120000, 8),
('Brow Tinting', 'Dyeing your brows to enhance their color and shape. (15 min)', 100000, 8),
('Lash Tinting', 'Dyeing your lashes for a darker, more defined look. (20 min)', 150000, 8);

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

(6, 1, TRUE), (6, 2, TRUE), (7, 3, TRUE), (7, 4, TRUE), (6, 5, TRUE), (6, 6, TRUE), (6, 7, TRUE), (6, 8, TRUE), (6, 9, TRUE), (6, 10, TRUE),
(7, 11, TRUE), (7, 12, TRUE), (7, 13, TRUE), (7, 14, TRUE), (6, 15, TRUE), (7, 16, TRUE), (6, 17, TRUE), (6, 18, TRUE), (6, 19, TRUE)
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
    recipient_id INT,
    commission_id INT,
    transaction_service_id INT,
    commission_policy_id INT,
    commission_level INT,
    amount DECIMAL(16, 2),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
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

INSERT INTO `event` (code, name, start_date, end_date, description, max_discount, banner, status) VALUES
(
	'NY2024', 
    'New Year Deal', 
    '2024-04-01 00:00:00', 
    '2024-04-30 23:59:59', 
    'Aliquam hendrerit a augue insuscipit. Etiam aliquam massa quis des mauris commodo venenatis ligula commodo leez sed blandit convallis dignissim onec vel pellentesque neque.',
    30,
    'https://res.cloudinary.com/dxcyeb8km/image/upload/v1711764965/spa-special-img-1_1_hgwwep.jpg',
    1
),
(
	'WMD24', 
    'International Women Day', 
    '2024-04-01 00:00:00', 
    '2024-04-30 23:59:59', 
    'Aliquam hendrerit a augue insuscipit. Etiam aliquam massa quis des mauris commodo venenatis ligula commodo leez sed blandit convallis dignissim onec vel pellentesque neque.',
    30,
    'https://res.cloudinary.com/dxcyeb8km/image/upload/v1711764965/adam-winger-FkAZqQJTbXM-unsplash_qr1txt.jpg',
    1
);

CREATE TABLE `event_service` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT,
    service_id INT,
    discount DECIMAL(5, 2),
    FOREIGN KEY (event_id) REFERENCES `event`(id),
    FOREIGN KEY (service_id) REFERENCES service(id)
);

INSERT INTO `event_service` (event_id, service_id, discount) VALUES
(1, 1, 20), (1, 2, 10), (1, 3, 30), (1, 4, 25), (1, 5, 30), (1, 6, 15),
(2, 7, 20), (2, 8, 10), (2, 9, 30), (2, 10, 25), (2, 11, 30), (2, 12, 15);

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
-- CALL get_parents(19);


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


DROP PROCEDURE IF EXISTS get_parents_to_temp_table;
DELIMITER //
CREATE PROCEDURE get_parents_to_temp_table (IN user_id INT)
BEGIN
    -- Drop temporary table if exists
    DROP TEMPORARY TABLE IF EXISTS temp_parents;
    
    -- Create temporary table to store parent records
    CREATE TEMPORARY TABLE temp_parents (
        id INT,
        name VARCHAR(255),
        phone VARCHAR(20),
        email VARCHAR(255),
        referrer_id INT,
        user_level INT
    );

	INSERT INTO temp_parents (id, name, phone, email, referrer_id, user_level)
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
-- CALL get_parents_to_temp_table (6);
-- SELECT * FROM temp_parents;
-- SELECT id FROM temp_parents ORDER BY user_level LIMIT 1 OFFSET 3;


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
-- CALL get_children (6);


DROP PROCEDURE IF EXISTS `get_users_with_pagination`;
DELIMITER //
CREATE PROCEDURE `get_users_with_pagination`(
	IN size INT,
	IN page INT,
	IN search_term VARCHAR(255)
)
BEGIN
	DECLARE offset INT DEFAULT (page - 1) * size;
	DECLARE total_items INT;
	DECLARE total_pages INT;

	-- Calculate total items for pagination
	SELECT COUNT(*) INTO total_items
	FROM user
	WHERE (name LIKE CONCAT('%', LOWER(search_term), '%'))
		OR (phone LIKE CONCAT('%', LOWER(search_term), '%'))
		OR (email LIKE CONCAT('%', LOWER(search_term), '%'));

	-- Calculate total pages
	SET total_pages = CEIL(total_items / size);

	-- Main query with pagination
	SELECT 
		table_2.*,
		SUM(c.total) AS total_commission
	FROM ( SELECT 
		table_1.*,
		SUM(t.total) AS total_spent
	FROM (SELECT
		u.*,
		GROUP_CONCAT(r.name SEPARATOR ', ') AS role_names,
		u2.name AS referrer_name,
		u2.code AS referrer_code,
		u2.phone AS referrer_phone,
		u2.email AS referrer_email,
		COUNT(referred.id) AS total_referred_users,
		(SELECT total_items) AS total_items,
		(SELECT total_pages) AS total_pages
	FROM user u
	LEFT JOIN user_role ur ON u.id = ur.user_id
	LEFT JOIN role r ON ur.role_id = r.id
	LEFT JOIN user u2 ON u.referrer_id = u2.id
	LEFT JOIN user referred ON u.id = referred.referrer_id 
	WHERE (u.name LIKE CONCAT('%', LOWER(search_term), '%'))
		OR (u.phone LIKE CONCAT('%', LOWER(search_term), '%'))
		OR (u.email LIKE CONCAT('%', LOWER(search_term), '%'))
	GROUP BY u.id) table_1
	LEFT JOIN transaction t ON table_1.id = t.customer_id
	GROUP BY table_1.id) table_2
	LEFT JOIN commission c ON table_2.id = c.recipient_id
	GROUP BY table_2.id
	LIMIT offset, size;

END //
DELIMITER ;
-- CALL get_users_with_pagination (10, 1, '');


DROP PROCEDURE IF EXISTS `get_user_by_code`;
DELIMITER //
CREATE PROCEDURE `get_user_by_code`(
  IN code VARCHAR(255)
)
BEGIN
  SELECT
    u.*,
    GROUP_CONCAT(r.name SEPARATOR ', ') AS role_names,
    u2.name AS referrer_name,
    u2.code AS referrer_code,
    u2.phone AS referrer_phone,
    u2.email AS referrer_email,
    (SELECT COUNT(*) FROM user WHERE referrer_id = u.id) AS total_referred_users,
    (SELECT SUM(total) FROM transaction WHERE customer_id = u.id) AS total_spent,
    (SELECT SUM(total) FROM commission WHERE recipient_id = u.id) AS total_commission,
    (SELECT total_items) AS total_items,
    (SELECT total_pages) AS total_pages
  FROM user u
  LEFT JOIN user_role ur ON u.id = ur.user_id
  LEFT JOIN role r ON ur.role_id = r.id
  LEFT JOIN user u2 ON u.referrer_id = u2.id
  WHERE u.code = code
  GROUP BY u.id;
END //
DELIMITER ;


DROP PROCEDURE IF EXISTS `get_unpaid_commissions_by_user_and_time`;
DELIMITER //
CREATE PROCEDURE `get_unpaid_commissions_by_user_and_time` (
	IN user_id INT,
	IN start_date DATETIME,
	IN end_date DATETIME
)
BEGIN
    SELECT 
		c.*,
        p.code AS policy_code,
        t.commission_rate,
        ts.service_id,
        ts.price,
		s.name,
        tr.transaction_date,
        u.name AS customer_name,
        u.phone AS customer_phone,
        u.email AS customer_email
    FROM `commission_service` c
    LEFT JOIN `commission_policy` p ON c.commission_policy_id = p.id
    LEFT JOIN `commission_tier` t ON c.commission_policy_id = t.policy_id AND c.commission_level = t.level
    LEFT JOIN `transaction_service` ts ON c.transaction_service_id = ts.id
    LEFT JOIN `service` s ON ts.service_id = s.id
    LEFT JOIN `transaction` tr ON ts.transaction_id = tr.id
    LEFT JOIN `user` u ON tr.customer_id = u.id
    WHERE c.recipient_id = user_id AND c.commission_id IS NULL
    AND c.created_at BETWEEN start_date AND end_date;
END //
DELIMITER ;
-- CALL `get_unpaid_commissions_by_user_and_time` (9, '2024-03-01 00:00:00', '2024-03-31 23:59:59');


DROP PROCEDURE IF EXISTS `get_commission_services_by_commission_id`;
DELIMITER //
CREATE PROCEDURE `get_commission_services_by_commission_id` (
	IN commission_id INT
)
BEGIN
    SELECT 
		c.*,
        p.code AS policy_code,
        t.commission_rate,
        ts.service_id,
        ts.price,
		s.name,
        tr.transaction_date,
        u.name AS customer_name,
        u.phone AS customer_phone,
        u.email AS customer_email
    FROM `commission_service` c
    LEFT JOIN `commission_policy` p ON c.commission_policy_id = p.id
    LEFT JOIN `commission_tier` t ON c.commission_policy_id = t.policy_id AND c.commission_level = t.level
    LEFT JOIN `transaction_service` ts ON c.transaction_service_id = ts.id
    LEFT JOIN `service` s ON ts.service_id = s.id
    LEFT JOIN `transaction` tr ON ts.transaction_id = tr.id
    LEFT JOIN `user` u ON tr.customer_id = u.id
    WHERE c.commission_id = commission_id
    ORDER BY c.created_at DESC;
END //
DELIMITER ;
-- CALL get_commission_services_by_commission_id (1);


DROP PROCEDURE IF EXISTS `create_commission_payment_for_user`;
DELIMITER //
CREATE PROCEDURE `create_commission_payment_for_user` (
	IN user_id INT,
	IN start_date DATETIME,
	IN end_date DATETIME
)
BEGIN
	DECLARE has_unpaid_services INT DEFAULT 0;
	DECLARE new_commission_id INT;
    DECLARE total_amount DECIMAL(16, 2);
    
    -- Check for unpaid commission services within the period
	SELECT COUNT(id) INTO has_unpaid_services  
    FROM `commission_service`
    WHERE recipient_id = user_id 
		AND commission_id IS NULL
		AND created_at BETWEEN start_date AND end_date;
        
	-- If there are unpaid services, create a new commission record
    IF has_unpaid_services > 0 THEN
		-- Calculate the total commission amount
		SELECT SUM(amount) INTO total_amount
		FROM commission_service
		WHERE recipient_id = user_id
			AND commission_id IS NULL
			AND created_at BETWEEN start_date AND end_date;
			
		-- Create a new commission record
		INSERT INTO commission (recipient_id, total, status)
		VALUES (user_id, total_amount, 1);
		
		SET new_commission_id = LAST_INSERT_ID();
		
		-- Update the unpaid commission_service records with the new commission ID
		UPDATE commission_service
		SET commission_id = new_commission_id
		WHERE recipient_id = user_id
			AND commission_id IS NULL
			AND created_at BETWEEN start_date AND end_date;
		
        -- Return true to indicate success
        SELECT true;
	ELSE 
		-- Return false if there are no unpaid commission_service records
        SELECT false;
    END IF;
END //
DELIMITER ;
-- CALL create_commission_payment_for_user (6, '2024-03-01 00:00:00', '2024-03-31 23:59:59');


DROP TRIGGER IF EXISTS after_insert_transaction_service;
DELIMITER //
CREATE TRIGGER after_insert_transaction_service
AFTER INSERT ON `transaction_service`
FOR EACH ROW
BEGIN
	DECLARE service_id INT;
	DECLARE policy_id INT;
	DECLARE max_referral_levels INT;
	DECLARE parent_id INT;
	DECLARE level INT;
	DECLARE commission_rate_1 DECIMAL(5, 2);
    DECLARE user_id INT;
    
    -- Get service ID from inserted transaction_service record
    SET service_id = NEW.service_id;
    
    -- Find active policy for the service
    SELECT p.id, p.max_referral_levels 
    INTO policy_id, max_referral_levels
    FROM `service_policy_assignment` a 
    INNER JOIN `commission_policy` p ON a.policy_id = p.id
    WHERE a.service_id = service_id AND a.active = 1;
    
    IF policy_id IS NOT NULL THEN -- Active policy found
        -- Call stored procedure to get parents (up to 10 levels)
        SET user_id = (SELECT customer_id FROM `transaction` WHERE id = NEW.transaction_id);
        
		CALL get_parents_to_temp_table (user_id); 
        
        -- Method 1: Loop through parents and create commission services
        -- SET level = 1;
--         WHILE level <= max_referral_levels DO
-- 			SELECT id INTO parent_id FROM temp_parents ORDER BY user_level LIMIT 1 OFFSET level;
-- 		
--             IF parent_id IS NOT NULL THEN
--  				-- Get commission rate for this level
--                 SELECT commission_rate INTO commission_rate_1 FROM commission_tier c WHERE c.policy_id = policy_id AND c.level = level;
-- 	
--                 INSERT INTO commission_service (recipient_id, commission_id, transaction_service_id, commission_policy_id, commission_level, amount, created_at)
-- 				VALUES (parent_id, NULL, NEW.id, policy_id, level, commission_rate_1 * NEW.price / 100, NOW());
--             END IF;
--             
--             SET parent_id = NULL;
--             SET level = level + 1;
--         END WHILE;
        
        -- Method 2: Join temp_parent and commission_tier
        INSERT INTO commission_service (
			recipient_id, 
			commission_id, 
			transaction_service_id, 
			commission_policy_id, 
			commission_level, 
			amount
		)
        SELECT 
			p.id,
            NULL,
            NEW.id,
            policy_id,
            t.level,
			t.commission_rate * NEW.price / 100
        FROM temp_parents p 
        INNER JOIN commission_tier t ON p.user_level = t.level
        WHERE t.policy_id = policy_id;
	
	END IF;
END; //
DELIMITER ;

-- SELECT * FROM temp_parents
-- INNER JOIN commission_tier t ON p.user_level = level
-- WHERE t.policy_id = policy_id;




DELIMITER $$

CREATE PROCEDURE GetCategoryRevenue(
    IN pageNum INT,
    IN pageSize INT,
    IN searchKeyword VARCHAR(255)
)
BEGIN
    DECLARE startIdx INT DEFAULT 0;
    DECLARE totalRecords INT DEFAULT 0;
    DECLARE totalPages INT DEFAULT 0;

    -- Tính chỉ số bắt đầu của dữ liệu cần lấy dựa trên số trang và kích thước trang
    SET startIdx = pageNum * pageSize;

    -- Tính tổng số bản ghi trong bảng category thỏa mãn điều kiện search
    SELECT COUNT(*)
    INTO totalRecords
    FROM category c
    WHERE c.deleted_at IS NULL
    AND c.name LIKE CONCAT('%', searchKeyword, '%');

    -- Tính tổng số trang
    SET totalPages = CEIL(totalRecords / CAST(pageSize AS DECIMAL));

    -- Truy vấn dữ liệu phân trang và tìm kiếm
    SELECT c.id AS category_id,
           c.name AS category_name,
           c.icon AS category_icon,
           (SELECT COUNT(*) FROM service WHERE category_id = c.id) AS total_service,
           SUM(ts.price) AS total_revenue,
           totalPages AS total_pages
           
    FROM category c
    LEFT JOIN service s ON c.id = s.category_id
    LEFT JOIN transaction_service ts ON s.id = ts.service_id
    WHERE c.deleted_at IS NULL
    AND c.name LIKE CONCAT('%', searchKeyword, '%')
    GROUP BY c.id, c.name, c.icon
    LIMIT startIdx, pageSize;
    
    -- Trả về tổng số trang
    SELECT totalPages AS TotalPages;
END$$

DELIMITER ;


