-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema cinema
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cinema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cinema` DEFAULT CHARACTER SET utf8 ;
USE `cinema` ;

-- -----------------------------------------------------
-- Table `cinema`.`complexe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`complexe` (
  `idComplexe` INT NOT NULL AUTO_INCREMENT,
  `nomComplexe` VARCHAR(45) NULL DEFAULT NULL,
  `adresseComplexe` VARCHAR(255) NULL DEFAULT NULL,
  `descriptifComplexeFR` TEXT NULL DEFAULT NULL,
  `descriptifComplexeNL` TEXT NULL DEFAULT NULL,
  `descriptifComplexeEN` TEXT NULL DEFAULT NULL,
  `urlPhotoComplexe` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`idComplexe`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`categorie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`categorie` (
  `idCategorie` INT NOT NULL AUTO_INCREMENT,
  `prixCategorie` DECIMAL(4,2) NULL DEFAULT NULL,
  `nomCategorieFR` VARCHAR(45) NULL DEFAULT NULL,
  `nomCategorieNL` VARCHAR(45) NULL DEFAULT NULL,
  `nomCategorieEN` VARCHAR(45) NULL DEFAULT NULL,
  `COMPLEXE_idComplexe` INT NOT NULL,
  PRIMARY KEY (`idCategorie`, `COMPLEXE_idComplexe`),
  INDEX `fk_CATEGORIE_COMPLEXE1_idx` (`COMPLEXE_idComplexe` ASC),
  CONSTRAINT `fk_CATEGORIE_COMPLEXE1`
    FOREIGN KEY (`COMPLEXE_idComplexe`)
    REFERENCES `cinema`.`complexe` (`idComplexe`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`film`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`film` (
  `idFilm` INT NOT NULL AUTO_INCREMENT,
  `nomFilmFR` VARCHAR(128) NULL DEFAULT NULL,
  `nomFilmNL` VARCHAR(128) NULL DEFAULT NULL,
  `nomFilmEN` VARCHAR(128) NULL DEFAULT NULL,
  `descriptionFR` TEXT NULL DEFAULT NULL,
  `descriptionNL` TEXT NULL DEFAULT NULL,
  `descriptionEN` TEXT NULL DEFAULT NULL,
  `urlPhotoFilm` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`idFilm`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`version`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`version` (
  `idVersion` INT NOT NULL AUTO_INCREMENT,
  `libelleVersion` VARCHAR(8) NULL DEFAULT NULL,
  PRIMARY KEY (`idVersion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`film_has_version`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`film_has_version` (
  `FILM_idFilm` INT NOT NULL,
  `VERSION_idVersion` INT NOT NULL,
  PRIMARY KEY (`FILM_idFilm`, `VERSION_idVersion`),
  INDEX `fk_FILM_has_VERSION_VERSION1_idx` (`VERSION_idVersion` ASC),
  INDEX `fk_FILM_has_VERSION_FILM1_idx` (`FILM_idFilm` ASC),
  CONSTRAINT `fk_FILM_has_VERSION_FILM1`
    FOREIGN KEY (`FILM_idFilm`)
    REFERENCES `cinema`.`film` (`idFilm`),
  CONSTRAINT `fk_FILM_has_VERSION_VERSION1`
    FOREIGN KEY (`VERSION_idVersion`)
    REFERENCES `cinema`.`version` (`idVersion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`genre` (
  `idGenre` INT NOT NULL AUTO_INCREMENT,
  `libelleGenreFR` VARCHAR(45) NULL DEFAULT NULL,
  `libelleGenreNL` VARCHAR(45) NULL DEFAULT NULL,
  `libelleGenreEN` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idGenre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`genre_has_film`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`genre_has_film` (
  `GENRE_idGenre` INT NOT NULL,
  `FILM_idFilm` INT NOT NULL,
  PRIMARY KEY (`GENRE_idGenre`, `FILM_idFilm`),
  INDEX `fk_GENRE_has_FILM_FILM1_idx` (`FILM_idFilm` ASC),
  INDEX `fk_GENRE_has_FILM_GENRE1_idx` (`GENRE_idGenre` ASC),
  CONSTRAINT `fk_GENRE_has_FILM_FILM1`
    FOREIGN KEY (`FILM_idFilm`)
    REFERENCES `cinema`.`film` (`idFilm`),
  CONSTRAINT `fk_GENRE_has_FILM_GENRE1`
    FOREIGN KEY (`GENRE_idGenre`)
    REFERENCES `cinema`.`genre` (`idGenre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`profil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`profil` (
  `idProfil` INT NOT NULL AUTO_INCREMENT,
  `nomUtilisateur` VARCHAR(45) NULL DEFAULT NULL,
  `prenomUtilisateur` VARCHAR(45) NULL DEFAULT NULL,
  `adresseUtilisateur` VARCHAR(255) NULL DEFAULT NULL,
  `ageUtilisateur` INT NULL DEFAULT NULL,
  `langueUtilisateur` VARCHAR(2) NULL DEFAULT 'EN',
  `pseudoUtilisateur` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idProfil`),
  UNIQUE INDEX `pseudoUtilisateur_UNIQUE` (`pseudoUtilisateur` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`utilisateur`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`utilisateur` (
  `idUtilisateur` INT NOT NULL AUTO_INCREMENT,
  `emailUtilisateur` VARCHAR(45) NOT NULL,
  `roleUtilisateur` VARCHAR(45) NULL DEFAULT 'user',
  `PROFIL_idProfil` INT NOT NULL,
  PRIMARY KEY (`idUtilisateur`, `PROFIL_idProfil`),
  UNIQUE INDEX `emailUtilisateur_UNIQUE` (`emailUtilisateur` ASC),
  INDEX `fk_UTILISATEUR_PROFIL_idx` (`PROFIL_idProfil` ASC),
  CONSTRAINT `fk_UTILISATEUR_PROFIL`
    FOREIGN KEY (`PROFIL_idProfil`)
    REFERENCES `cinema`.`profil` (`idProfil`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`reservation` (
  `idReservation` INT NOT NULL AUTO_INCREMENT,
  `dateReservation` DATETIME NULL DEFAULT NULL,
  `UTILISATEUR_idUtilisateur` INT NOT NULL,
  PRIMARY KEY (`idReservation`, `UTILISATEUR_idUtilisateur`),
  INDEX `fk_RESERVATION_UTILISATEUR1_idx` (`UTILISATEUR_idUtilisateur` ASC),
  CONSTRAINT `fk_RESERVATION_UTILISATEUR1`
    FOREIGN KEY (`UTILISATEUR_idUtilisateur`)
    REFERENCES `cinema`.`utilisateur` (`idUtilisateur`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`salle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`salle` (
  `idSalle` INT NOT NULL AUTO_INCREMENT,
  `nbSalle` INT NULL DEFAULT NULL,
  `nbPlacesSalle` INT NULL DEFAULT NULL,
  `COMPLEXE_idComplexe` INT NOT NULL,
  PRIMARY KEY (`idSalle`, `COMPLEXE_idComplexe`),
  INDEX `fk_SALLE_COMPLEXE1_idx` (`COMPLEXE_idComplexe` ASC),
  CONSTRAINT `fk_SALLE_COMPLEXE1`
    FOREIGN KEY (`COMPLEXE_idComplexe`)
    REFERENCES `cinema`.`complexe` (`idComplexe`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`seance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`seance` (
  `idSeance` INT NOT NULL AUTO_INCREMENT,
  `dateSeance` DATE NULL DEFAULT NULL,
  `heureSeance` TIME NULL DEFAULT NULL,
  `nbPlacesDisponibles` INT NULL DEFAULT NULL,
  `SALLE_idSalle` INT NOT NULL,
  `FILM_idFilm` INT NOT NULL,
  PRIMARY KEY (`idSeance`, `FILM_idFilm`, `SALLE_idSalle`),
  INDEX `fk_SEANCE_SALLE1_idx` (`SALLE_idSalle` ASC),
  INDEX `fk_SEANCE_FILM1_idx` (`FILM_idFilm` ASC),
  CONSTRAINT `fk_SEANCE_FILM1`
    FOREIGN KEY (`FILM_idFilm`)
    REFERENCES `cinema`.`film` (`idFilm`),
  CONSTRAINT `fk_SEANCE_SALLE1`
    FOREIGN KEY (`SALLE_idSalle`)
    REFERENCES `cinema`.`salle` (`idSalle`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`ticket` (
  `idTicket` INT NOT NULL AUTO_INCREMENT,
  `RESERVATION_idReservation` INT NOT NULL,
  `CATEGORIE_idCategorie` INT NOT NULL,
  `SEANCE_idSeance` INT NOT NULL,
  PRIMARY KEY (`idTicket`, `CATEGORIE_idCategorie`, `SEANCE_idSeance`),
  INDEX `fk_TICKET_RESERVATION1_idx` (`RESERVATION_idReservation` ASC),
  INDEX `fk_TICKET_CATEGORIE1_idx` (`CATEGORIE_idCategorie` ASC),
  INDEX `fk_TICKET_SEANCE1_idx` (`SEANCE_idSeance` ASC),
  CONSTRAINT `fk_TICKET_CATEGORIE1`
    FOREIGN KEY (`CATEGORIE_idCategorie`)
    REFERENCES `cinema`.`categorie` (`idCategorie`),
  CONSTRAINT `fk_TICKET_RESERVATION1`
    FOREIGN KEY (`RESERVATION_idReservation`)
    REFERENCES `cinema`.`reservation` (`idReservation`),
  CONSTRAINT `fk_TICKET_SEANCE1`
    FOREIGN KEY (`SEANCE_idSeance`)
    REFERENCES `cinema`.`seance` (`idSeance`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;