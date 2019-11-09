-- -----------------------------------------------------
-- Schema scrumlab
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `scrumlab` DEFAULT CHARACTER SET utf8 ;
USE `scrumlab` ;

-- -----------------------------------------------------
-- Table `scrumlab`.`admins`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `scrumlab`.`admins` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'Klucz główny tabeli.',
  `first_name` VARCHAR(245) NULL COMMENT 'Imię',
  `last_name` VARCHAR(245) NULL COMMENT 'Nazwisko',
  `email` VARCHAR(245) NULL COMMENT 'Adres email',
  `password` VARCHAR(60) NULL COMMENT 'Hasło',
  `superadmin` TINYINT(1) NULL COMMENT 'Czy użytkownik jest Super Administratorem.',
  `enable` TINYINT(1) NOT NULL DEFAULT '1' COMMENT 'Czy jest aktywny i może się logować.',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = 'Tabela zawierająca dane użytkowników';


-- -----------------------------------------------------
-- Table `scrumlab`.`recipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `scrumlab`.`recipe` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'Klucz główny tabeli.',
  `name` VARCHAR(255) NULL COMMENT 'Nazwa przepisu.',
  `ingredients` TEXT NULL COMMENT 'Składniki przepisu.',
  `description` TEXT NULL COMMENT 'Opis przepisu.',
  `created` DATETIME NULL COMMENT 'Data dodania.',
  `updated` DATETIME NULL COMMENT 'Data edycji.',
  `preparation_time` INT NULL COMMENT 'Czas przygotowania w minutach.',
  `preparation` TEXT NULL COMMENT 'Sposób przygotowania.',
  `admin_id` INT NOT NULL COMMENT 'Klucz obcy tabeli admins.',
  PRIMARY KEY (`id`),
  INDEX `fk_recipie_admins1_idx` (`admin_id` ASC),
  CONSTRAINT `fk_recipie_admins1`
    FOREIGN KEY (`admin_id`)
    REFERENCES `scrumlab`.`admins` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela zawierająca przepisy';


-- -----------------------------------------------------
-- Table `scrumlab`.`plan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `scrumlab`.`plan` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'Klucz główny tabeli',
  `name` VARCHAR(45) NULL COMMENT 'Nazwa planu.',
  `description` TEXT NULL COMMENT 'Opis planu',
  `created` DATETIME NULL COMMENT 'Data utworzenia.',
  `admin_id` INT NOT NULL COMMENT 'Klucz obcy tabeli admins.',
  PRIMARY KEY (`id`),
  INDEX `fk_plan_admins1_idx` (`admin_id` ASC),
  CONSTRAINT `fk_plan_admins1`
    FOREIGN KEY (`admin_id`)
    REFERENCES `scrumlab`.`admins` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela zawierająca informacje na temat planów.';


-- -----------------------------------------------------
-- Table `scrumlab`.`day_name`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `scrumlab`.`day_name` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL COMMENT 'Nazwa dnia.',
  `display_order` INT NULL COMMENT 'Kolejność wyświetlania.',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = 'Tabela zawierająca nazwy dni.';


-- -----------------------------------------------------
-- Table `scrumlab`.`recipe_plan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `scrumlab`.`recipe_plan` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'Klucz główny tabeli.',
  `recipe_id` INT NOT NULL COMMENT 'Klucz obcy tabeli przepisów.',
  `meal_name` VARCHAR(245) NULL COMMENT 'Nazwa posiłku',
  `display_order` INT NULL COMMENT 'Kolejność wyświetlania posiłku w ciągu dnia.',
  `day_name_id` INT NOT NULL COMMENT 'Klucz obcy tabeli dni.',
  `plan_id` INT NOT NULL COMMENT 'Klucz obcy tabeli plany.',
  PRIMARY KEY (`id`),
  INDEX `fk_recipe_plan_recipe1_idx` (`recipe_id` ASC),
  INDEX `fk_recipe_day_day_name1_idx` (`day_name_id` ASC),
  INDEX `fk_recipe_plan_plan1_idx` (`plan_id` ASC),
  CONSTRAINT `fk_recipe_plan_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `scrumlab`.`recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipe_day_day_name1`
    FOREIGN KEY (`day_name_id`)
    REFERENCES `scrumlab`.`day_name` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipe_plan_plan1`
    FOREIGN KEY (`plan_id`)
    REFERENCES `scrumlab`.`plan` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela zawierająca informacje o połączeniu przepisu oraz planu.';


-- -----------------------------------------------------
-- Table `scrumlab`.`pages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `scrumlab`.`pages` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'Klucz główny tabeli',
  `title` VARCHAR(245) NULL COMMENT 'Tytuł strony.',
  `description` TEXT NULL COMMENT 'Zawartość strony.',
  `slug` VARCHAR(245) NULL COMMENT 'Unikalny identyfikator strony tworzony na podstawie tytułu.',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `slug_UNIQUE` (`slug` ASC))
ENGINE = InnoDB;


CREATE TABLE `book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `author` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `isbn` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = 'Przykładowa tabela.';
INSERT INTO `day_name` (`id`, `name`, `display_order`) VALUES
(1, 'poniedziałek', 1),
(2, 'wtorek', 2),
(3, 'środa', 3),
(4, 'czwartek', 4),
(5, 'piątek', 5),
(6, 'sobota', 6),
(7, 'niedziela', 7);

INSERT INTO `admins` (`id`, `first_name`, `last_name`, `email`, `password`, `superadmin`) VALUES
(1, 'Arek', 'Józwiak', 'arkadiusz.jozwiak@coderslab.pl', '$2a$10$eCrsyeEmdBFktXP1Npubt.KZi9cotr3Bs8cmdVDnj9/OmKvPKzRvi', 1);

INSERT INTO `plan` (`id`, `name`, `description`, `created`, `admin_id`) VALUES
(null, 'Plan Jarski', 'Opis planu 1', '2018-10-17 14:27:05', 1),
(null, 'Plan Mięsny', 'Opis planu 2', '2018-10-17 14:27:05', 1),
(null, 'Plan Śniadaniowy', 'Opis planu 3', '2018-10-17 14:27:05', 1),
(null, 'Plan Redukcja', 'Opis planu 4', '2018-10-17 14:27:05', 1),
(null, 'Plan Dużo białka', 'Opis planu 5', '2018-10-17 14:27:05', 1),
(null, 'Kapuściana dieta', 'Opis planu 6', '2018-10-17 14:27:05', 1)
;

INSERT INTO `recipe` (`id`, `name`, `ingredients`, `description`, `created`, `updated`, `admin_id`, `preparation_time`, `preparation`) VALUES
(null, 'Przepis 1', 'sałata', 'Opis przepisu 1', '2018-10-16 00:00:00', '2018-10-17 14:24:44', 1, 22,'Opis przygotowania 1'),
(null, 'Przepis 2', 'mięso z indyka, pieczarki', 'Opis przepisu 2', '2018-10-16 00:00:00', '2018-10-17 14:24:44', 1, 22,'Opis przygotowania 2'),
(null, 'Przepis 3', 'soczewica, ser feta', 'Opis przepisu 3', '2018-10-24 00:00:00', '2018-10-17 14:24:44', 1, 23,'Opis przygotowania 3'),
(null, 'Przepis 4', 'ciecierzyca, ', 'Opis przepisu 4', '2018-10-24 00:00:00', '2018-10-17 14:24:44', 1, 24,'Opis przygotowania 4'),
(null, 'Przepis 5', 'schab, ziemniaki', 'Opis przepisu 5', '2018-10-24 00:00:00', '2018-10-17 14:24:44', 1, 25,'Opis przygotowania 5'),
(null, 'Przepis 6', 'kapusta, mięso mielone', 'Opis przepisu 6', '2018-10-24 00:00:00', '2018-10-17 14:24:44', 1, 26,'Opis przygotowania 6'),
(null, 'Przepis 7', 'kasza gryczana, cukinia', 'Opis przepisu 7', '2018-10-24 00:00:00', '2018-10-17 14:24:44', 1, 27,'Opis przygotowania 7'),
(null, 'Przepis 8', 'boczek, parmezan, jajka, makaron', 'Opis przepisu 8', '2018-10-24 00:00:00', '2018-10-17 14:24:44', 1, 28,'Opis przygotowania 8'),
(null, 'Przepis 9', 'lody, gruszki', 'Opis przepisu 9', '2018-10-24 00:00:00', '2018-10-17 14:24:44', 1, 29,'Opis przygotowania 9')
;

INSERT INTO `recipe_plan` (`id`, `recipe_id`,  `meal_name`, `display_order`, `day_name_id`, `plan_id`) VALUES
(null, 1,  'Kolacja', 2, 2, 6),
(null, 2,  'Śniadanie', 1, 1, 6),
(null, 1,  'Kolacja', 2, 1, 6),
(null, 3,  'Śniadanie', 1, 2, 6);