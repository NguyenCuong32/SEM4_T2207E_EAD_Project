/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
--
-- Create database
--
CREATE DATABASE BrokefreeDB
--
--
-- Use database
--
USE BrokefreeDB

--
-- CREATE TABLE
--

--
-- Table structure for table `branchs`
--

CREATE TABLE IF NOT EXISTS `branchs` (
	`id` varchar(255) NOT NULL  UNIQUE,
	`name` varchar(255) NOT NULL,
	`address` varchar(255) NOT NULL,
	`phone` varchar(255) NOT NULL UNIQUE,
	`email` varchar(255) NOT NULL,
`status` tinyint(1) NOT NULL,
	PRIMARY KEY (`id`)
);

 

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

 

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `id` binary(16) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


--
-- Table structure for table `accounts_roles`
--

CREATE TABLE `accounts_roles` (
  `account_id` binary(16) NOT NULL,
  `role_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Table structure for table  `formulas`
--


CREATE TABLE IF NOT EXISTS `formulas` (
	`id` varchar(255) NOT NULL   UNIQUE,
	`name_category` varchar(255) NOT NULL,
        `description` text NOT NULL,
        `status` tinyint(1) NOT NULL,
	PRIMARY KEY (`id`)
);

--
-- Table structure for table   `category`
--


CREATE TABLE IF NOT EXISTS `category` (
	`id` varchar(255) NOT NULL   UNIQUE,
	`name_category` varchar(255) NOT NULL,
`status` tinyint(1) NOT NULL,

	PRIMARY KEY (`id`)
);

--
-- Table structure for table `services`   
--


CREATE TABLE IF NOT EXISTS `services` (
	`id` varchar(255) NOT NULL   UNIQUE,
	`name` varchar(255) NOT NULL,
	`description` text NOT NULL,
	`category` varchar(255) NOT NULL,
	`price` decimal(12,6) NOT NULL,
`status` tinyint(1) NOT NULL,

	PRIMARY KEY (`id`)
);

--
-- Table structure for table `feepercent`  
--

CREATE TABLE IF NOT EXISTS `feepercent` (
	`id` int AUTO_INCREMENT NOT NULL   UNIQUE,
	`level` int NOT NULL,
	`ratio` float NOT NULL,
	`bonus` int NOT NULL,
	`servicesid` varchar(255) NOT NULL,
`status` tinyint(1) NOT NULL,

	PRIMARY KEY (`id`)
);
--
-- Table structure for table   `policies`
--

CREATE TABLE IF NOT EXISTS `policies` (
	`id` int AUTO_INCREMENT NOT NULL  UNIQUE,
	`name` varchar(255) NOT NULL,
	`time_start` datetime NOT NULL,
	`time_ending` datetime NOT NULL,
	`formula_id` varchar(255) NOT NULL,  
	`servicesid` varchar(255) NOT NULL,
	`status` tinyint(1) NOT NULL,
	`branchsid` varchar(255) NOT NULL,
        `vat_tax` float NOT NULL,
        `tax_ratio` float NOT NULL,
        `bonus` float NOT NULL,  
	PRIMARY KEY (`id`)
);

--
-- Table structure for table   `users` 
--

CREATE TABLE IF NOT EXISTS `users` (
	`id` varchar(255) NOT NULL  UNIQUE,
	`fullname` varchar(255) NOT NULL,
	`phone` varchar(20) NOT NULL UNIQUE,
	`email` varchar(255) NOT NULL,
	`address` varchar(255) NOT NULL,
	`feedbackfee` int NOT NULL,
	`gender` tinyint(1) NOT NULL,
        `total_amount` decimal(12,6) NOT NULL,
        `parent_phone` varchar(20)   UNIQUE,
	`accountsid` binary(16) NOT NULL,

`status` tinyint(1) NOT NULL,

	PRIMARY KEY (`id`)
);

--
-- Table structure for table   `events`
--

CREATE TABLE IF NOT EXISTS `events` (
	`id`varchar(255) NOT NULL  UNIQUE,
	`name` varchar(255) NOT NULL,
	`desc` text NOT NULL,
	`time_start` datetime NOT NULL,
	`time_end` datetime NOT NULL,
	`banner` varchar(255) NOT NULL,
	`status` tinyint(1) NOT NULL,
	`eventcode` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

--
-- Table structure for table  `bill`
--

CREATE TABLE IF NOT EXISTS `bill` (
	`id`  varchar(255) NOT NULL   UNIQUE,
	`serviceid` varchar(255) NOT NULL,
	`customersid` varchar(255) NOT NULL,
	`time_set_start` datetime NOT NULL,
	`time_set_end` datetime NOT NULL,
	`event_id` varchar(255) NOT NULL,

`status` tinyint(1) NOT NULL,
`amount` decimal(12,6) NOT NULL,
	PRIMARY KEY (`id`)
);

--
-- Table structure for table   `eventservice`
--


CREATE TABLE IF NOT EXISTS `eventservice` (
	`id` varchar(255) NOT NULL  UNIQUE,
	`eventid`  varchar(255) NOT NULL,
	`serviceid`  varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

--
-- Table structure for table   `eventbranchs`
--

CREATE TABLE IF NOT EXISTS `eventbranchs` (
	`id` varchar(255) NOT NULL  UNIQUE,
	`name` varchar(255) NOT NULL,
	`eventid` varchar(255) NOT NULL,
	`branch_id` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

--
-- Table structure for table    `amount_eachlv`
--

CREATE TABLE IF NOT EXISTS `amount_eachlv` (
	`id` int AUTO_INCREMENT NOT NULL  UNIQUE,
	`level` int(10) NOT NULL,
	`amount` decimal(12,6) NOT NULL,
	`user_id` varchar(255) NOT NULL,
        `status` tinyint(1) NOT NULL,
        `bill_id` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `accounts_roles`
--
ALTER TABLE `accounts_roles`
  ADD KEY `FKpwest19ib22ux5gk54esw9qve` (`role_id`),
  ADD KEY `FKt44duw96d6v8xrapfo4ff2up6` (`account_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `accounts_roles`
--
ALTER TABLE `accounts_roles`
  ADD CONSTRAINT `FKpwest19ib22ux5gk54esw9qve` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKt44duw96d6v8xrapfo4ff2up6` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`);
 

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

--
-- Indexes for table `policies`
--
ALTER TABLE `policies` ADD CONSTRAINT `policies_fk5` FOREIGN KEY (`servicesid`) REFERENCES `services`(`id`);
ALTER TABLE `policies` ADD CONSTRAINT `policies_fk7` FOREIGN KEY (`branchsid`) REFERENCES `branchs`(`id`);

ALTER TABLE `policies` ADD CONSTRAINT `policies_fk8` FOREIGN KEY (`formula_id`) REFERENCES `formulas`(`id`);

 --
-- Indexes for table `feepercent`
--
ALTER TABLE `feepercent` ADD CONSTRAINT `feepercent_fk5` FOREIGN KEY (`servicesid`) REFERENCES `services`(`id`);

 --
-- Indexes for table `services`
--

ALTER TABLE `services` ADD CONSTRAINT `services_fk3` FOREIGN KEY (`category`) REFERENCES `category`(`id`);

 --
-- Indexes for table `eventservice`
--
ALTER TABLE `eventservice` ADD CONSTRAINT `eventservice_fk1` FOREIGN KEY (`eventid`) REFERENCES `events`(`id`);
ALTER TABLE `eventservice` ADD CONSTRAINT `eventservice_fk2` FOREIGN KEY (`serviceid`) REFERENCES `services`(`id`);

 --
-- Indexes for table `users`
--
ALTER TABLE `users` ADD CONSTRAINT `users_fk7` FOREIGN KEY (`accountsid`) REFERENCES `accounts`(`id`);

 --
-- Indexes for table `eventbranchs`
--
ALTER TABLE `eventbranchs` ADD CONSTRAINT `eventbranchs_fk2` FOREIGN KEY (`eventid`) REFERENCES `events`(`id`);

ALTER TABLE `eventbranchs` ADD CONSTRAINT `eventbranchs_fk3` FOREIGN KEY (`branch_id`) REFERENCES `branchs`(`id`);

 --
-- Indexes for table `bill`
--
ALTER TABLE `bill` ADD CONSTRAINT `transitions_fk1` FOREIGN KEY (`serviceid`) REFERENCES `services`(`id`);

ALTER TABLE `bill` ADD CONSTRAINT `transitions_fk2` FOREIGN KEY (`customersid`) REFERENCES `users`(`id`);

ALTER TABLE `bill` ADD CONSTRAINT `transitions_fk5` FOREIGN KEY (`event_id`) REFERENCES `events`(`id`);


ALTER TABLE `amount_eachlv` ADD CONSTRAINT `moneyeachlv_fk5` FOREIGN KEY (`bill_id`) REFERENCES `bill`(`id`);
ALTER TABLE `amount_eachlv` ADD CONSTRAINT `moneyeachlv_fk2` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`);



