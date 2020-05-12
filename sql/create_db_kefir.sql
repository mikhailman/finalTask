-- MySQL Script generated by MySQL Workbench
-- Thu Nov 14 20:13:30 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema kefir
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema kefir
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `kefir` DEFAULT CHARACTER SET utf8;
USE `kefir`;

-- -----------------------------------------------------
-- Table `kefir`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kefir`.`users`
(
    `id`                INT(11)      NOT NULL AUTO_INCREMENT,
    `role`              TINYINT(4)   NOT NULL,
    /*
     * 1 - администратор (Role.ADMINISTRATOR)
     * 2 - пользователь (Role.USER)
     */
    `login`             VARCHAR(45)  NOT NULL,
    `password`          VARCHAR(256) NOT NULL,
    `email`             VARCHAR(256) NOT NULL,
    `phone`             BIGINT       NULL,
    `name`              VARCHAR(45)  NULL,
    `surname`           VARCHAR(45)  NULL,
    `status`            TINYINT(1)   NOT NULL DEFAULT 1,
    `date_registration` DATE         NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 14
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `kefir`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kefir`.`category`
(
    `id`   INT(11)     NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;
# CREATE TABLE IF NOT EXISTS `kefir`.`category` (
#   `id` INT(11) NOT NULL AUTO_INCREMENT,
#   `name` VARCHAR(45) NULL,
#   `category_parent_id` INT(11) NULL,
#   PRIMARY KEY (`id`),
#   INDEX `fk_category_parent_id_idx` (`category_parent_id` ASC),
#   CONSTRAINT `fk_category_parent_id`
#     FOREIGN KEY (`category_parent_id`)
#     REFERENCES `kefir`.`category` (`id`)
#     ON DELETE NO ACTION
#     ON UPDATE NO ACTION)
# ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kefir`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kefir`.`products`
(
    `id`            INT(11)        NOT NULL AUTO_INCREMENT,
    `name`          VARCHAR(100)   NULL,
    `description`   VARCHAR(2000)  NULL,
    `price`         DECIMAL(10, 0) NULL,
    `date_creation` DATE           NULL,
    `users_id`      INT(11)        NOT NULL,
    `category_id`   INT(11)        NOT NULL,
    `location_id`   INT(11)        NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_products_users1_idx` (`users_id` ASC),
    INDEX `fk_products_category1_idx` (`category_id` ASC),
    INDEX `fk_products_location1_idx` (`location_id` ASC),
    CONSTRAINT `fk_products_users1`
        FOREIGN KEY (`users_id`)
            REFERENCES `kefir`.`users` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_products_category1`
        FOREIGN KEY (`category_id`)
            REFERENCES `kefir`.`category` (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT `fk_products_location1`
        FOREIGN KEY (`location_id`)
            REFERENCES `kefir`.`location` (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 12
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `kefir`.`images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kefir`.`images`
(
    `id`          INT(11)       NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(45)   NULL,
    `image_path`  VARCHAR(4096) NULL,
    `products_id` INT(11)       NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `products_id_idx` (`products_id` ASC),
    CONSTRAINT `products_id`
        FOREIGN KEY (`products_id`)
            REFERENCES `kefir`.`products` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kefir`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kefir`.`comments`
(
    `id`          INT(11)       NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(45)   NULL,
    `body`        VARCHAR(4096) NULL,
    `users_id`    INT(11)       NOT NULL,
    `products_id` INT(11)       NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `user_id_idx` (`users_id` ASC),
    INDEX `fk_product_id_idx` (`products_id` ASC),
    CONSTRAINT `fk_users_id`
        FOREIGN KEY (`users_id`)
            REFERENCES `kefir`.`users` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_product_id`
        FOREIGN KEY (`products_id`)
            REFERENCES `kefir`.`products` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kefir`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kefir`.`location`
(
    `id`   INT(11)      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NULL,
    `city` VARCHAR(45)  NULL,
    PRIMARY KEY (`id`)
)
    #   CONSTRAINT `fk_id_location`
#     FOREIGN KEY (`id`)
#     REFERENCES `kefir`.`products` (`id`)
#     ON DELETE CASCADE
#     ON UPDATE CASCADE)
    ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
