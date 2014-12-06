-- MySQL Script generated by MySQL Workbench
-- MySQL Script generated by MySQL Workbench
-- 12/06/14 23:12:35
-- Model: New Model    Version: 1.0
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sChOOL
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sChOOL` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `sChOOL` ;

-- -----------------------------------------------------
-- Table `sChOOL`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sChOOL`.`User` (
  `userId` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL,
  `emailAddress` VARCHAR(45) NULL,
  `homeAddress` VARCHAR(45) NULL,
  `contactNumber` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `profession` VARCHAR(45) NULL,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sChOOL`.`Subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sChOOL`.`Subject` (
  `subjectCode` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `teacherId` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`subjectCode`),
  INDEX `fk_Subject_User1_idx` (`teacherId` ASC),
  CONSTRAINT `fk_Subject_User1`
    FOREIGN KEY (`teacherId`)
    REFERENCES `sChOOL`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sChOOL`.`Clearance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sChOOL`.`Clearance` (
  `clearanceId` VARCHAR(45) NOT NULL,
  `description` LONGTEXT NULL,
  `dateIssued` DATETIME NULL,
  `dateResolved` DATETIME NULL,
  `studentId` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`clearanceId`),
  INDEX `fk_Clearance_User1_idx` (`studentId` ASC),
  CONSTRAINT `fk_Clearance_User1`
    FOREIGN KEY (`studentId`)
    REFERENCES `sChOOL`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sChOOL`.`Class`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sChOOL`.`Class` (
  `classCode` VARCHAR(45) NOT NULL,
  `level` VARCHAR(45) NULL,
  `section` VARCHAR(45) NULL,
  PRIMARY KEY (`classCode`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sChOOL`.`Grade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sChOOL`.`Grade` (
  `gradeId` VARCHAR(45) NOT NULL,
  `grade` INT NULL,
  `quarter` VARCHAR(45) NULL,
  `subjectCode` VARCHAR(45) NOT NULL,
  `studentId` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`gradeId`),
  INDEX `fk_Grade_Subject1_idx` (`subjectCode` ASC),
  INDEX `fk_Grade_User1_idx` (`studentId` ASC),
  CONSTRAINT `fk_Grade_Subject1`
    FOREIGN KEY (`subjectCode`)
    REFERENCES `sChOOL`.`Subject` (`subjectCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Grade_User1`
    FOREIGN KEY (`studentId`)
    REFERENCES `sChOOL`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sChOOL`.`Announcement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sChOOL`.`Announcement` (
  `announcementId` VARCHAR(45) NOT NULL,
  `announcement` LONGTEXT NULL,
  `dateIssued` DATETIME NULL,
  `adminId` VARCHAR(45) NOT NULL,
  INDEX `fk_Annoucement_User1_idx` (`adminId` ASC),
  PRIMARY KEY (`announcementId`),
  CONSTRAINT `fk_Annoucement_User1`
    FOREIGN KEY (`adminId`)
    REFERENCES `sChOOL`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sChOOL`.`Schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sChOOL`.`Schedule` (
  `subjectCode` VARCHAR(45) NOT NULL,
  `classCode` VARCHAR(45) NOT NULL,
  `day` VARCHAR(45) NULL,
  `time` TIME NULL,
  INDEX `fk_Schedule_has_Subject_Subject1_idx` (`subjectCode` ASC),
  INDEX `fk_Schedule_Class1_idx` (`classCode` ASC),
  CONSTRAINT `fk_Schedule_has_Subject_Subject1`
    FOREIGN KEY (`subjectCode`)
    REFERENCES `sChOOL`.`Subject` (`subjectCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Schedule_Class1`
    FOREIGN KEY (`classCode`)
    REFERENCES `sChOOL`.`Class` (`classCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sChOOL`.`Enroll`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sChOOL`.`Enroll` (
  `enrollId` VARCHAR(45) NOT NULL,
  `dateEnrolled` DATETIME NULL,
  `studentId` VARCHAR(45) NOT NULL,
  `classCode` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`enrollId`),
  INDEX `fk_Enroll_User1_idx` (`studentId` ASC),
  INDEX `fk_Enroll_Class1_idx` (`classCode` ASC),
  CONSTRAINT `fk_Enroll_User1`
    FOREIGN KEY (`studentId`)
    REFERENCES `sChOOL`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Enroll_Class1`
    FOREIGN KEY (`classCode`)
    REFERENCES `sChOOL`.`Class` (`classCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO user (userId, name, emailAddress, homeAddress, contactNumber, password, profession) VALUE
('404','Gellica','Gel@yahoo.com','10 Angeles St. Makati','5463333','404','student'),
('405','Hannah','','31 Kalamansi St. Magallanes, Makati','','405','student'),
('406','Tiff','','31 Kalamansi St. New Manila, Quezon City','1111111','406','student'),
('407','Dillan','','','09235483321','407','student'),
('408','Bryan','','','','408','student'),
('409','Bryce','','','','409','student'),
('410','Sam','','','','410','student'),
('411','Bob','','','','411','student'),
('412','Vince','McMahon@wwe.com','','','412','student'),
('413','Billy','DigsB@hotmail.com','','','413','student'),
('414','Joe','Frazer@hotmail.com','','8254322','414','student'),
('415','Michael','Michael@indra.com','Quezon','','415','student'),
('170','Cardenas','','','','170','faculty'),
('180','Marcos','','','','180','faculty'),
('190','Lim','','','','190','faculty'),
('120','Santos','','','','120','faculty'),
('121','Lamat','','','','121','faculty'),
('122','Dimaculangan','','','','122','faculty'),
('123','Aquino','','','','123','faculty'),
('124','Carreon','','','','124','faculty'),
('125','Gregorio','','','','125','faculty'),
('126','Gano','','','','126','faculty'),
('127','Chan','','','','127','faculty'),
('000','Anon','','','','000','admin');

INSERT INTO announcement (announcementId, announcement, dateIssued, adminId) VALUE
(1,'Due of enrollment payment is on September 3, 2014', '2014-08-02 09:00:00', '000'),
(2,'No class on December 3, 2014', '2014-11-25 08:00:00', '000');

INSERT INTO clearance (clearanceId, description, dateIssued, dateResolved, studentId) VALUE
(1,'Not fully paid','2014-09-12 8:00:00',null,'407'),
(2,'No medical examination','2014-09-15 9:45:00',null,'412');

INSERT INTO subject (subjectCode, description, teacherId) VALUE
('math1','Algebra','170'),
('math2','Geometry','122'),
('math3','Trigonometry','126'),
('math4','Calculus','170'),
('history1','Precolonial Philippines','180'),
('history2','Western History','123'),
('history3','World History','180'),
('history4','Economic History','180'),
('science1','Botany','190'),
('science2','Biology','190'),
('science3','Chemistry','127'),
('science4','Physics','127'),
('english1','English comprehension level 1','120'),
('english2','English comprehension level 2','124'),
('english3','World Literature level 1','124'),
('english4','World Literature level 2','120'),
('filipino1','Wika at Tula','121'),
('filipino2','Contemporary Filipino','121'),
('filipino3','Filipino Culture and History','125'),
('filipino4','Filipino History','125');

INSERT INTO class (classCode, level, section) VALUE
('HS1A','High School 1','A'),
('HS1B','High School 1','B'),
('HS2A','High School 2','A'),
('HS2B','High School 2','B'),
('HS3A','High School 3','A'),
('HS3B','High School 3','B'),
('HS4A','High School 4','A'),
('HS4B','High School 4','B');

INSERT INTO enroll (enrollId, dateEnrolled, studentId, classCode) VALUE 
('1','2014-09-03 09:00:00','404','HS1A'),
('2','2014-09-03 10:00:00','405','HS1A'),
('3','2014-09-03 10:30:00','406','HS1B'),
('4','2014-09-03 10:10:00','407','HS2B'),
('5','2014-09-04 09:00:00','408','HS2B'),
('6','2014-09-04 15:00:00','409','HS2A'),
('7','2014-09-03 14:05:00','410','HS3A'),
('8','2014-09-03 15:46:00','411','HS3B'),
('9','2014-09-05 09:00:00','412','HS3A'),
('10','2014-09-05 12:00:00','413','HS4B'),
('11','2014-09-04 09:01:00','414','HS4A'),
('12','2014-09-05 12:20:00','415','HS4B');

INSERT INTO schedule (subjectCode, classCode, day, time) VALUE
('math1','HS1A','Monday','07:30:00'),
('history1','HS1A','Monday','08:30:00'),
('science1','HS1A','Monday','09:00:00'),
('english1','HS1A','Monday','10:00:00'),
('filipino1','HS1A','Monday','11:00:00'),
('filipino1','HS1B','Monday','07:30:00'),
('english1','HS1B','Monday','08:30:00'),
('science1','HS1B','Monday','09:00:00'),
('history1','HS1B','Monday','10:00:00'),
('math1','HS1B','Monday','11:00:00'),

('filipino2','HS2B','Monday','07:30:00'),
('english2','HS2B','Monday','08:30:00'),
('science2','HS2B','Monday','09:00:00'),
('history2','HS2B','Monday','10:00:00'),
('math2','HS2B','Monday','11:00:00'),
('math2','HS2A','Monday','07:30:00'),
('history2','HS2A','Monday','08:30:00'),
('science2','HS2A','Monday','09:00:00'),
('english2','HS2A','Monday','10:00:00'),
('filipino2','HS2A','Monday','11:00:00'),

('math3','HS3A','Monday','07:30:00'),
('history3','HS3A','Monday','08:30:00'),
('science3','HS3A','Monday','09:00:00'),
('english3','HS3A','Monday','10:00:00'),
('filipino3','HS3A','Monday','11:00:00'),
('filipino3','HS3B','Monday','07:30:00'),
('english3','HS3B','Monday','08:30:00'),
('science3','HS3B','Monday','09:00:00'),
('history3','HS3B','Monday','10:00:00'),
('math3','HS3B','Monday','11:00:00'),

('filipino4','HS4A','Monday','07:30:00'),
('english4','HS4A','Monday','08:30:00'),
('science4','HS4A','Monday','09:00:00'),
('history4','HS4A','Monday','10:00:00'),
('math4','HS4A','Monday','11:00:00'),
('math4','HS4B','Monday','07:30:00'),
('history4','HS4B','Monday','08:30:00'),
('science4','HS4B','Monday','09:00:00'),
('english4','HS4B','Monday','10:00:00'),
('filipino4','HS4B','Monday','11:00:00');

INSERT INTO grade (gradeId, grade, quarter, subjectCode, studentId) VALUE
('1','78','first','math1','404'),
('2','80','first','history1','404'),
('3','90','first','science1','404'),
('4','99','first','english1','404'),
('5','85','first','filipino1','404'),

('6','95','first','math1','405'),
('7','76','first','history1','405'),
('8','88','first','science1','405'),
('9','77','first','english1','405'),
('10','75','first','filipino1','405'),

('11','100','first','math1','406'),
('12','98','first','history1','406'),
('13','89','first','science1','406'),
('14','92','first','english1','406'),
('15','93','first','filipino1','406'),


('16','75','first','math2','407'),
('17','76','first','history2','407'),
('18','77','first','science2','407'),
('19','78','first','english2','407'),
('20','79','first','filipino2','407'),

('21','82','first','math2','408'),
('22','82','first','history2','408'),
('23','84','first','science2','408'),
('24','85','first','english2','408'),
('25','88','first','filipino2','408'),

('26','82','first','math2','409'),
('27','79','first','history2','409'),
('28','83','first','science2','409'),
('29','89','first','english2','409'),
('30','90','first','filipino2','409'),


('31','95','first','math3','410'),
('32','95','first','history3','410'),
('33','94','first','science3','410'),
('34','96','first','english3','410'),
('35','92','first','filipino3','410'),

('36','75','first','math3','411'),
('37','77','first','history3','411'),
('38','76','first','science3','411'),
('39','77','first','english3','411'),
('40','76','first','filipino3','411'),

('41','81','first','math3','412'),
('42','81','first','history3','412'),
('43','79','first','science3','412'),
('44','82','first','english3','412'),
('45','75','first','filipino3','412'),


('46','87','first','math4','413'),
('47','70','first','history4','413'),
('48','72','first','science4','413'),
('49','73','first','english4','413'),
('50','78','first','filipino4','413'),

('51','78','first','math4','414'),
('52','75','first','history4','414'),
('53','81','first','science4','414'),
('54','83','first','english4','414'),
('55','85','first','filipino4','414'),

('56','96','first','math4','415'),
('57','86','first','history4','415'),
('58','94','first','science4','415'),
('59','95','first','english4','415'),
('60','82','first','filipino4','415');

