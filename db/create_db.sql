-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema devdepot
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema devdepot
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `devdepot` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `devdepot` ;

-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` BIGINT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(50) NOT NULL,
  `brief_desc` VARCHAR(50) NULL DEFAULT NULL,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `permission1` TINYINT(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX `role_name` ON `role` (`role_name` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(32) NOT NULL,
  `password_hash` VARCHAR(255) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `role` BIGINT NULL DEFAULT NULL,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `last_login` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_role_roleid`
    FOREIGN KEY (`role`)
    REFERENCES `role` (`role_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX `username` ON `user` (`username` ASC) VISIBLE;

CREATE UNIQUE INDEX `email` ON `user` (`email` ASC) VISIBLE;

CREATE INDEX `fk_role_roleid` ON `user` (`role` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payment` (
  `transaction_id` BIGINT NOT NULL AUTO_INCREMENT,
  `order_id` BIGINT NOT NULL,
  `payment_method` ENUM('CREDIT_CARD', 'PAYPAL', 'STRIPE', 'APPLE_PAY') NOT NULL,
  `subtotal` DECIMAL(10,2) NOT NULL,
  `tax_total` DECIMAL(10,2) NOT NULL,
  `payment_status` ENUM('PENDING', 'COMPLETED', 'FAILED') NULL DEFAULT 'PENDING',
  `payment_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`transaction_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `fk_payments_orderid` ON `payment` (`order_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order` (
  `order_id` BIGINT NOT NULL AUTO_INCREMENT,
  `customer_id` BIGINT NOT NULL,
  `order_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `total_cost` DECIMAL(10,2) NOT NULL,
  `shipping_address` VARCHAR(255) NOT NULL,
  `order_status` ENUM('ARRIVED', 'IN_TRANSIT', 'PROCESSED') NOT NULL DEFAULT 'PROCESSED',
  `tracking_num` VARCHAR(50) NULL DEFAULT NULL,
  `discount_promotion` DECIMAL(5,2) NULL DEFAULT '0.00',
  `transaction_id` BIGINT NOT NULL,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `order_ibfk_1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE CASCADE,
  CONSTRAINT `order_ibfk_2`
    FOREIGN KEY (`transaction_id`)
    REFERENCES `payment` (`transaction_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX `tracking_num` ON `order` (`tracking_num` ASC) VISIBLE;

CREATE INDEX `customer_id` ON `order` (`customer_id` ASC) VISIBLE;

CREATE INDEX `transaction_id` ON `order` (`transaction_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `product` (
  `product_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `category` VARCHAR(25) NOT NULL,
  `brand` VARCHAR(50) NOT NULL,
  `inventory_qty` INT NOT NULL DEFAULT '0',
  `price` DECIMAL(10,2) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `sku` VARCHAR(16) NOT NULL,
  `specifications` JSON NOT NULL,
  PRIMARY KEY (`product_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX `name` ON `product` (`name` ASC) VISIBLE;

CREATE UNIQUE INDEX `sku` ON `product` (`sku` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `order_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_item` (
  `order_id` BIGINT NOT NULL,
  `product_id` BIGINT NOT NULL,
  `quantity` INT NOT NULL DEFAULT '1',
  `price_per_unit` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`order_id`, `product_id`),
  CONSTRAINT `order_item_ibfk_1`
    FOREIGN KEY (`order_id`)
    REFERENCES `order` (`order_id`)
    ON DELETE CASCADE,
  CONSTRAINT `order_item_ibfk_2`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`product_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `product_id` ON `order_item` (`product_id` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
