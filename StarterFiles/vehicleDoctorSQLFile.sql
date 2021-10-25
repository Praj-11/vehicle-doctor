-- -----------------------------------------------------
-- Schema Vehicle Doctor Test
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `vehicle-doctor-test`;

CREATE SCHEMA `vehicle-doctor-test`;
USE `vehicle-doctor-test` ;

-- -----------------------------------------------------
-- Table `vehicle-doctor-test`.`garage_owner`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vehicle-doctor-test`.`address` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(255) DEFAULT NULL,
  `country` VARCHAR(255) DEFAULT NULL,
  `state` VARCHAR(255) DEFAULT NULL,
  `street` VARCHAR(255) DEFAULT NULL,
  `zip_code` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`))
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `vehicle-doctor-test`.`spare_part` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `part_name` varchar(255) unique not null,
  `quantity` int not null default 1,
  `price` decimal(5,2) default 0.00,
  PRIMARY KEY (`id`))
AUTO_INCREMENT = 1;


CREATE TABLE IF NOT EXISTS `vehicle-doctor-test`.`car_details` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(255) DEFAULT NULL,
  `make` VARCHAR(255) DEFAULT NULL,
  `model` VARCHAR(255) DEFAULT NULL,
  `variant` VARCHAR(255) DEFAULT NULL,
  `year` int(4) DEFAULT NULL,
  `fuel_type` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`))
AUTO_INCREMENT = 1;


CREATE TABLE IF NOT EXISTS `vehicle-doctor-test`.`garage` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `garage_name` VARCHAR(255) DEFAULT NULL,
  `address_id`bigint DEFAULT NULL,
  `phone_number` bigint(10) DEFAULT NULL,
  `email` varchar(255) unique default null,
  `password` varchar(255) default null,

  PRIMARY KEY (`id`),
  CONSTRAINT `go_address_id`
    FOREIGN KEY (`address_id`)
    REFERENCES `vehicle-doctor-test`.`address` (`id`))
AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- Table `vehicle-doctor-test`.`mechanic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vehicle-doctor-test`.`mechanic` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) DEFAULT NULL,
  `address_id` bigint DEFAULT NULL,
  `phone_number` BIGINT(10) DEFAULT NULL,
  `active` BIT DEFAULT 1,
  `rating` decimal(5,1) DEFAULT NULL,
   `date_joined` DATETIME(6) DEFAULT NULL,
  `employer_id` BIGINT(20) NOT NULL,
  `email` varchar(255) unique not null,
  `password` varchar(255) not null,
  
  PRIMARY KEY (`id`),
  KEY `m_employer` (`employer_id`),
  CONSTRAINT `m_employer` FOREIGN KEY (`employer_id`) REFERENCES `garage` (`id`),
  CONSTRAINT `m_address_id`
    FOREIGN KEY (`address_id`)
    REFERENCES `vehicle-doctor-test`.`address` (`id`)
) 
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `vehicle-doctor-test`.`customer` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) DEFAULT NULL,
  `address_id` bigint DEFAULT NULL,
  `phone_number` BIGINT(10) DEFAULT NULL,
   `email_id` varchar(255) unique not null,
   `password` varchar(255) not null,
   `car_id` bigint not null,
   
  PRIMARY KEY (`id`),
  CONSTRAINT `c_address_id`
    FOREIGN KEY (`address_id`)
    REFERENCES `vehicle-doctor-test`.`address` (`id`),
	CONSTRAINT `c_car_details_id`
    FOREIGN KEY (`car_id`)
    REFERENCES `vehicle-doctor-test`.`car_details` (`id`)
) 
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `vehicle-doctor-test`.`orders` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order_tracking_number` VARCHAR(255) unique NULL DEFAULT NULL,
  `order_desc` varchar(255) null default null,
  `bill_amount` DECIMAL(19,2)DEFAULT NULL,
  `customer_id` BIGINT(20) DEFAULT NULL,
  `car_details_id` bigint default null,
  `order_created` DATETIME(6) DEFAULT NULL,
  `order_completed` DATETIME(6) DEFAULT NULL,
  `order_apointment_date` DATETIME(6) DEFAULT NULL,
  `garage_id` bigint(20) default null,
  `mechanic_id` bigint(20) default null,
  `status` char(1) default 'p',
  `payment_status` char(1) default 'p',
  `spare_parts_used` bigint,

  PRIMARY KEY (`id`),
  INDEX `o_customer_id` (`customer_id` ASC) VISIBLE,
	CONSTRAINT `o_customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `vehicle-doctor-test`.`customer` (`id`),
	CONSTRAINT `o_garage_id`
    FOREIGN KEY (`garage_id`)
    REFERENCES `vehicle-doctor-test`.`garage` (`id`),
    CONSTRAINT `o_mechanic_id`
    FOREIGN KEY (`mechanic_id`)
    REFERENCES `vehicle-doctor-test`.`mechanic` (`id`),
	CONSTRAINT `o_car_details_id`
    FOREIGN KEY (`car_details_id`)
    REFERENCES `vehicle-doctor-test`.`car_details` (`id`),
    CONSTRAINT `o_spare_part_id`
    FOREIGN KEY (`spare_parts_used`)
    REFERENCES `vehicle-doctor-test`.`spare_part` (`id`)
    )
AUTO_INCREMENT = 1;

