-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema underification
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `underification` ;

-- -----------------------------------------------------
-- Schema underification
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `underification` DEFAULT CHARACTER SET utf8 ;
USE `underification` ;

-- -----------------------------------------------------
-- Table `applications`
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `applications` ;

-- CREATE TABLE IF NOT EXISTS `applications` (
--   `id` INTEGER NOT NULL,
--   `name` VARCHAR(255) NOT NULL,
--   `url` VARCHAR(255) NOT NULL,
--   `description` TEXT NOT NULL,

--   `token` VARCHAR(255) NOT NULL,

--   PRIMARY KEY (`id`)
-- ) ENGINE = InnoDB;

-- -- -----------------------------------------------------
-- -- Table `badges`
-- -- -----------------------------------------------------
-- DROP TABLE IF EXISTS `badges` ;

-- CREATE TABLE IF NOT EXISTS `badges` (
--   `id` INTEGER NOT NULL,
--   `name` VARCHAR(255) NOT NULL,
--   `image` VARCHAR(255) NOT NULL,
--   `description` TEXT NOT NULL,

--   PRIMARY KEY (`id`)
-- ) ENGINE = InnoDB;