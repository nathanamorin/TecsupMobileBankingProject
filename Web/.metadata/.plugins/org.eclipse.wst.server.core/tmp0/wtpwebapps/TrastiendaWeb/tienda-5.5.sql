SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `tienda` DEFAULT CHARACTER SET latin1 ;
USE `tienda` ;

-- -----------------------------------------------------
-- Table `tienda`.`categoria`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tienda`.`categoria` (
  `id_categoria` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(20) NULL DEFAULT NULL ,
  `descripcion` VARCHAR(64) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_categoria`) )
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tienda`.`usuario`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tienda`.`usuario` (
  `id_usuario` VARCHAR(20) NOT NULL ,
  `clave` VARCHAR(20) NULL DEFAULT NULL ,
  `nombres` VARCHAR(64) NULL DEFAULT NULL ,
  `paterno` VARCHAR(32) NULL DEFAULT NULL ,
  `materno` VARCHAR(32) NULL DEFAULT NULL ,
  `correo` VARCHAR(64) NULL DEFAULT NULL ,
  `direccion` VARCHAR(128) NULL DEFAULT NULL ,
  `telefono` VARCHAR(32) NULL DEFAULT NULL ,
  `estado` CHAR(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_usuario`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tienda`.`pedido`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tienda`.`pedido` (
  `id_pedido` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `id_usuario` VARCHAR(20) NOT NULL ,
  `fecha` DATETIME NULL DEFAULT NULL ,
  `estado` CHAR(1) NULL DEFAULT NULL ,
  `total` DOUBLE NULL DEFAULT NULL ,
  PRIMARY KEY (`id_pedido`) ,
  INDEX `id_usuario` (`id_usuario` ASC) ,
  CONSTRAINT `pedido_ibfk_1`
    FOREIGN KEY (`id_usuario` )
    REFERENCES `tienda`.`usuario` (`id_usuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tienda`.`producto`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tienda`.`producto` (
  `id_producto` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `id_categoria` INT(10) UNSIGNED NOT NULL ,
  `nombre` VARCHAR(128) NULL DEFAULT NULL ,
  `descripcion` VARCHAR(256) NULL DEFAULT NULL ,
  `precio` DECIMAL(10,0) NULL DEFAULT NULL ,
  `stock` INT(10) UNSIGNED NULL DEFAULT NULL ,
  `importancia` INT(10) UNSIGNED NULL DEFAULT NULL ,
  `imagen` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_producto`) ,
  INDEX `id_categoria` (`id_categoria` ASC) ,
  CONSTRAINT `producto_ibfk_1`
    FOREIGN KEY (`id_categoria` )
    REFERENCES `tienda`.`categoria` (`id_categoria` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tienda`.`detalle_pedido`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tienda`.`detalle_pedido` (
  `id_detalle_pedido` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `id_pedido` INT(10) UNSIGNED NOT NULL ,
  `id_producto` INT(10) UNSIGNED NOT NULL ,
  `precio` DOUBLE NULL DEFAULT NULL ,
  `cantidad` INT(10) UNSIGNED NULL DEFAULT NULL ,
  PRIMARY KEY (`id_detalle_pedido`, `id_pedido`) ,
  INDEX `id_pedido` (`id_pedido` ASC) ,
  INDEX `id_producto` (`id_producto` ASC) ,
  CONSTRAINT `detalle_pedido_ibfk_1`
    FOREIGN KEY (`id_pedido` )
    REFERENCES `tienda`.`pedido` (`id_pedido` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `detalle_pedido_ibfk_2`
    FOREIGN KEY (`id_producto` )
    REFERENCES `tienda`.`producto` (`id_producto` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tienda`.`rol`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tienda`.`rol` (
  `rol_nombre` VARCHAR(20) NOT NULL ,
  `descripcion` VARCHAR(64) NULL DEFAULT NULL ,
  PRIMARY KEY (`rol_nombre`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tienda`.`usuario_rol`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tienda`.`usuario_rol` (
  `rol_nombre` VARCHAR(20) NOT NULL ,
  `id_usuario` VARCHAR(20) NOT NULL ,
  INDEX `id_usuario` (`id_usuario` ASC) ,
  INDEX `rol_nombre` (`rol_nombre` ASC) ,
  CONSTRAINT `usuario_rol_ibfk_1`
    FOREIGN KEY (`id_usuario` )
    REFERENCES `tienda`.`usuario` (`id_usuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `usuario_rol_ibfk_2`
    FOREIGN KEY (`rol_nombre` )
    REFERENCES `tienda`.`rol` (`rol_nombre` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
