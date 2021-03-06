-- MySQL Script generated by MySQL Workbench
-- seg 28 jun 2021 14:17:39
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema restaurantedb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `restaurantedb` ;

-- -----------------------------------------------------
-- Schema restaurantedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `restaurantedb` ;
USE `restaurantedb` ;

-- -----------------------------------------------------
-- Table `restaurantedb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurantedb`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NOT NULL,
  `senha` VARCHAR(90) NOT NULL,
  `dthrcriacao` DATETIME NOT NULL,
  `dthralteracao` DATETIME NULL,
  `cpf` VARCHAR(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) VISIBLE,
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restaurantedb`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurantedb`.`produto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(140) NULL,
  `tamanho` VARCHAR(45) NULL,
  `dthrcriacao` DATETIME NOT NULL,
  `dthralteracao` DATETIME NULL,
  `preco` DECIMAL(10,2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restaurantedb`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurantedb`.`pedido` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  `valortotal` DECIMAL(10,2) NULL DEFAULT 0,
  `pago` TINYINT(1) NULL DEFAULT 0,
  `idpgtocartao` VARCHAR(45) NULL,
  `aceito` TINYINT(1) NULL DEFAULT 0,
  `pronto` TINYINT(1) NULL DEFAULT 0,
  `entregue` TINYINT(1) NULL DEFAULT 0,
  `cancelado` TINYINT(1) NULL DEFAULT 0,
  `estornado` TINYINT(1) NULL DEFAULT 0,
  `dthrpedido` DATETIME NOT NULL,
  `dthrfinalizado` DATETIME NULL,
  `uuidpagamento` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_usuario_id_idx` (`id_usuario` ASC) VISIBLE,
  UNIQUE INDEX `uuidcompra_UNIQUE` (`uuidpagamento` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_id`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `restaurantedb`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restaurantedb`.`produtos_pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurantedb`.`produtos_pedido` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_pedido` INT NOT NULL,
  `id_produto` INT NOT NULL,
  `quantidade` DECIMAL(10,1) NOT NULL,
  `valor` DECIMAL(10,2) NULL,
  `observacao` VARCHAR(140) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_id_pedido_idx` (`id_pedido` ASC) VISIBLE,
  INDEX `fk_id_produto_idx` (`id_produto` ASC) VISIBLE,
  CONSTRAINT `fk_id_pedido`
    FOREIGN KEY (`id_pedido`)
    REFERENCES `restaurantedb`.`pedido` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_produto`
    FOREIGN KEY (`id_produto`)
    REFERENCES `restaurantedb`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
