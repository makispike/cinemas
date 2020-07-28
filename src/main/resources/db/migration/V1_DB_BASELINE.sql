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
-- Table `cinema`.`utilisateur`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`utilisateur` (
  `idUtilisateur` INT NOT NULL AUTO_INCREMENT,
  `emailUtilisateur` VARCHAR(45) NOT NULL,
  `roleUtilisateur` VARCHAR(45) NULL DEFAULT 'user',
  PRIMARY KEY (`idUtilisateur`),
  UNIQUE INDEX `emailUtilisateur_UNIQUE` (`emailUtilisateur` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema`.`profil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`profil` (
  `idProfil` INT NOT NULL AUTO_INCREMENT,
  `nomUtilisateur` VARCHAR(45) NULL,
  `prenomUtilisateur` VARCHAR(45) NULL,
  `adresseUtilisateur` VARCHAR(255) NULL,
  `ageUtilisateur` INT NULL,
  `langueUtilisateur` VARCHAR(2) NULL DEFAULT 'EN',
  `pseudoUtilisateur` VARCHAR(45) NULL,
  `utilisateur_idUtilisateur` INT NOT NULL,
  PRIMARY KEY (`idProfil`, `utilisateur_idUtilisateur`),
  UNIQUE INDEX `pseudoUtilisateur_UNIQUE` (`pseudoUtilisateur` ASC),
  INDEX `fk_profil_utilisateur1_idx` (`utilisateur_idUtilisateur` ASC),
  CONSTRAINT `fk_profil_utilisateur1`
    FOREIGN KEY (`utilisateur_idUtilisateur`)
    REFERENCES `cinema`.`utilisateur` (`idUtilisateur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema`.`reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`reservation` (
  `idReservation` INT NOT NULL AUTO_INCREMENT,
  `dateReservation` DATETIME NULL,
  `utilisateur_idUtilisateur` INT NOT NULL,
  PRIMARY KEY (`idReservation`, `utilisateur_idUtilisateur`),
  INDEX `fk_reservation_utilisateur1_idx` (`utilisateur_idUtilisateur` ASC),
  CONSTRAINT `fk_reservation_utilisateur1`
    FOREIGN KEY (`utilisateur_idUtilisateur`)
    REFERENCES `cinema`.`utilisateur` (`idUtilisateur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema`.`complexe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`complexe` (
  `idComplexe` INT NOT NULL AUTO_INCREMENT,
  `nomComplexe` VARCHAR(45) NULL,
  `adresseComplexe` VARCHAR(255) NULL,
  `descriptifComplexeFR` TEXT NULL,
  `descriptifComplexeNL` TEXT NULL,
  `descriptifComplexeEN` TEXT NULL,
  `urlPhotoComplexe` VARCHAR(255) NULL,
  PRIMARY KEY (`idComplexe`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema`.`salle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`salle` (
  `idSalle` INT NOT NULL AUTO_INCREMENT,
  `nbSalle` INT NULL,
  `nbPlacesSalle` INT NULL,
  `complexe_idComplexe` INT NOT NULL,
  PRIMARY KEY (`idSalle`, `complexe_idComplexe`),
  INDEX `fk_salle_complexe1_idx` (`complexe_idComplexe` ASC),
  CONSTRAINT `fk_salle_complexe1`
    FOREIGN KEY (`complexe_idComplexe`)
    REFERENCES `cinema`.`complexe` (`idComplexe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema`.`film`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`film` (
  `idFilm` INT NOT NULL AUTO_INCREMENT,
  `nomFilmFR` VARCHAR(128) NULL,
  `nomFilmNL` VARCHAR(128) NULL,
  `nomFilmEN` VARCHAR(128) NULL,
  `descriptionFR` TEXT NULL,
  `descriptionNL` TEXT NULL,
  `descriptionEN` TEXT NULL,
  `urlPhotoFilm` VARCHAR(255) NULL,
  PRIMARY KEY (`idFilm`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema`.`seance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`seance` (
  `idSeance` INT NOT NULL AUTO_INCREMENT,
  `dateSeance` DATE NULL,
  `heureSeance` TIME NULL,
  `nbPlacesDisponibles` INT NULL,
  `salle_idSalle` INT NOT NULL,
  `salle_complexe_idComplexe` INT NOT NULL,
  `film_idFilm` INT NOT NULL,
  PRIMARY KEY (`idSeance`, `salle_idSalle`, `salle_complexe_idComplexe`, `film_idFilm`),
  INDEX `fk_seance_salle1_idx` (`salle_idSalle` ASC, `salle_complexe_idComplexe` ASC),
  INDEX `fk_seance_film1_idx` (`film_idFilm` ASC),
  CONSTRAINT `fk_seance_salle1`
    FOREIGN KEY (`salle_idSalle` , `salle_complexe_idComplexe`)
    REFERENCES `cinema`.`salle` (`idSalle` , `complexe_idComplexe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_seance_film1`
    FOREIGN KEY (`film_idFilm`)
    REFERENCES `cinema`.`film` (`idFilm`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema`.`categorie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`categorie` (
  `idCategorie` INT NOT NULL AUTO_INCREMENT,
  `prixCategorie` DECIMAL(4,2) NULL,
  `nomCategorieFR` VARCHAR(45) NULL,
  `nomCategorieNL` VARCHAR(45) NULL,
  `nomCategorieEN` VARCHAR(45) NULL,
  `complexe_idComplexe` INT NOT NULL,
  PRIMARY KEY (`idCategorie`, `complexe_idComplexe`),
  INDEX `fk_categorie_complexe1_idx` (`complexe_idComplexe` ASC),
  CONSTRAINT `fk_categorie_complexe1`
    FOREIGN KEY (`complexe_idComplexe`)
    REFERENCES `cinema`.`complexe` (`idComplexe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`ticket` (
  `idTicket` INT NOT NULL AUTO_INCREMENT,
  `reservation_idReservation` INT NOT NULL,
  `reservation_utilisateur_idUtilisateur` INT NOT NULL,
  `seance_idSeance` INT NOT NULL,
  `categorie_idCategorie` INT NOT NULL,
  PRIMARY KEY (`idTicket`, `seance_idSeance`, `categorie_idCategorie`),
  INDEX `fk_ticket_reservation1_idx` (`reservation_idReservation` ASC, `reservation_utilisateur_idUtilisateur` ASC),
  INDEX `fk_ticket_seance1_idx` (`seance_idSeance` ASC),
  INDEX `fk_ticket_categorie1_idx` (`categorie_idCategorie` ASC),
  CONSTRAINT `fk_ticket_reservation1`
    FOREIGN KEY (`reservation_idReservation` , `reservation_utilisateur_idUtilisateur`)
    REFERENCES `cinema`.`reservation` (`idReservation` , `utilisateur_idUtilisateur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_seance1`
    FOREIGN KEY (`seance_idSeance`)
    REFERENCES `cinema`.`seance` (`idSeance`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_categorie1`
    FOREIGN KEY (`categorie_idCategorie`)
    REFERENCES `cinema`.`categorie` (`idCategorie`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema`.`version`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`version` (
  `idVersion` INT NOT NULL AUTO_INCREMENT,
  `libelleVersion` VARCHAR(8) NULL,
  PRIMARY KEY (`idVersion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema`.`genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`genre` (
  `idGenre` INT NOT NULL AUTO_INCREMENT,
  `libelleGenreFR` VARCHAR(45) NULL,
  `libelleGenreNL` VARCHAR(45) NULL,
  `libelleGenreEN` VARCHAR(45) NULL,
  PRIMARY KEY (`idGenre`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema`.`film_has_version`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`film_has_version` (
  `film_idFilm` INT NOT NULL,
  `version_idVersion` INT NOT NULL,
  PRIMARY KEY (`film_idFilm`, `version_idVersion`),
  INDEX `fk_FILM_has_VERSION_VERSION1_idx` (`version_idVersion` ASC),
  INDEX `fk_FILM_has_VERSION_FILM1_idx` (`film_idFilm` ASC),
  CONSTRAINT `fk_FILM_has_VERSION_FILM1`
    FOREIGN KEY (`film_idFilm`)
    REFERENCES `cinema`.`film` (`idFilm`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FILM_has_VERSION_VERSION1`
    FOREIGN KEY (`version_idVersion`)
    REFERENCES `cinema`.`version` (`idVersion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema`.`genre_has_film`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`genre_has_film` (
  `genre_idGenre` INT NOT NULL,
  `film_idFilm` INT NOT NULL,
  PRIMARY KEY (`genre_idGenre`, `film_idFilm`),
  INDEX `fk_GENRE_has_FILM_FILM1_idx` (`film_idFilm` ASC),
  INDEX `fk_GENRE_has_FILM_GENRE1_idx` (`genre_idGenre` ASC),
  CONSTRAINT `fk_GENRE_has_FILM_GENRE1`
    FOREIGN KEY (`genre_idGenre`)
    REFERENCES `cinema`.`genre` (`idGenre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_GENRE_has_FILM_FILM1`
    FOREIGN KEY (`film_idFilm`)
    REFERENCES `cinema`.`film` (`idFilm`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE USER 'admin' IDENTIFIED BY 'admin';

GRANT SELECT, INSERT, TRIGGER ON TABLE `cinema`.* TO 'admin';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `cinema`.* TO 'admin';
CREATE USER 'user' IDENTIFIED BY 'user';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `cinema`.* TO 'user';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
