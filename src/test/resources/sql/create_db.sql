SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';


CREATE SCHEMA IF NOT EXISTS kefir_test DEFAULT CHARACTER SET utf8;
USE kefir_test;


CREATE TABLE IF NOT EXISTS kefir_test.users
(
    `id`                INT(11)      NOT NULL AUTO_INCREMENT,
    `role`              TINYINT(4)   NOT NULL DEFAULT 2,
    /*
     * 1 - администратор (Role.ADMINISTRATOR)
     * 2 - пользователь (Role.USER)
     */
    `login`             VARCHAR(45)  NOT NULL UNIQUE,
    `password`          VARCHAR(256) NOT NULL,
    `email`             VARCHAR(256) NOT NULL UNIQUE,
    `phone`             BIGINT       NULL,
    `name`              VARCHAR(45)  NULL,
    `surname`           VARCHAR(45)  NULL,
    `status`            TINYINT(1)   NOT NULL DEFAULT 1,
    `date_registration` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 14
    DEFAULT CHARACTER SET = utf8;



CREATE TABLE IF NOT EXISTS kefir_test.category
(
    `id`   INT(11)     NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS kefir_test.products
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
            REFERENCES kefir_test.users (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_products_category1`
        FOREIGN KEY (`category_id`)
            REFERENCES kefir_test.category (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT `fk_products_location1`
        FOREIGN KEY (`location_id`)
            REFERENCES kefir_test.location (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 12
    DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS kefir_test.images
(
    `id`          INT(11)       NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(45)   NULL,
    `image_path`  VARCHAR(4096) NULL,
    `products_id` INT(11)       NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `products_id_idx` (`products_id` ASC),
    CONSTRAINT `products_id`
        FOREIGN KEY (`products_id`)
            REFERENCES kefir_test.products (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS kefir_test.comments
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
            REFERENCES kefir_test.users (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_product_id`
        FOREIGN KEY (`products_id`)
            REFERENCES kefir_test.products (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS kefir_test.location
(
    `id`   INT(11)      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NULL,
    `city` VARCHAR(45)  NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
