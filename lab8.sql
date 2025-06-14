-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema lab8
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lab8
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lab8` DEFAULT CHARACTER SET utf8 ;
USE `lab8` ;

-- -----------------------------------------------------
-- Table `lab8`.`monitoreo_climatico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab8`.`monitoreo_climatico` (
  `id` INT NOT NULL,
  `ciudad` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `temperatura_prom` FLOAT NOT NULL,
  `cond_climatica_frecuente` VARCHAR(45) NOT NULL,
  `temperatura_max` FLOAT NOT NULL,
  `temperatura_min` FLOAT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
