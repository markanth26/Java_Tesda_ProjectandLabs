DROP DATABASE IF EXISTS `visitorLogin`;
CREATE DATABASE `visitorLogin`;
USE `visitorLogin`;

SET NAMES utf8 ;
SET character_set_client = utf8mb4 ;


CREATE TABLE `tblemployee` (
  `employees_id` INT(11) NOT NULL AUTO_INCREMENT,
  `employee_name` VARCHAR(50) NOT NULL,
  `position` VARCHAR(30) NOT NULL,
  `contact_no` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`employees_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


INSERT INTO `tblemployee` (`employee_name`, `position`, `contact_no`) VALUES ('Admin', 'Administrator', '09955820300');




CREATE TABLE `tbluser` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `employee_id` INT(11) NOT NULL,
  `username` VARCHAR(50) NOT NULL UNIQUE,
  `password_hash` VARCHAR(255) NOT NULL,
  `role` ENUM('ADMINISTRATOR', 'RECEPTIONIST') NOT NULL,
  `email_address` VARCHAR(100) NOT NULL UNIQUE,
  `password_reset_token` VARCHAR(255) DEFAULT NULL,
  `is_active` BOOLEAN NOT NULL DEFAULT TRUE,
  PRIMARY KEY (`user_id`),
  KEY `fk_employee_id_idx` (`employee_id`),
  CONSTRAINT `fk_user_employee_id` FOREIGN KEY (`employee_id`) REFERENCES `tblemployee` (`employees_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;




CREATE TABLE `tblvisitor` (
  `visit_id` INT(11) NOT NULL AUTO_INCREMENT,
  `visitor_name` VARCHAR(100) NOT NULL,
  `receptionist_employee_id` INT(11) NULL,
  `visited_employee_id` INT(11) NULL,
  `check_in_date` DATE NOT NULL,
  `check_in_time` DATETIME NOT NULL,
  `check_out_time` DATETIME NULL,
  `contact_no` VARCHAR(20) NOT NULL,
  `random_no` INT(11) NOT NULL,
  `is_active` BOOLEAN NOT NULL DEFAULT TRUE,
  `purpose_of_visit` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`visit_id`),
  KEY `fk_receptionist_employee_id_idx` (`receptionist_employee_id`),
  KEY `fk_visited_employee_id_idx` (`visited_employee_id`),
  CONSTRAINT `fk_visits_receptionist_employee_id` FOREIGN KEY (`receptionist_employee_id`) REFERENCES `tblemployee` (`employees_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_visits_visited_employee_id` FOREIGN KEY (`visited_employee_id`) REFERENCES `tblemployee` (`employees_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;