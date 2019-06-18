CREATE DATABASE  IF NOT EXISTS `gestiondocteur` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gestiondocteur`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: gestiondocteur
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admins` (
  `idAdmins` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idAdmins`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` VALUES (1,'admin','admin','admin');
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docteurs`
--

DROP TABLE IF EXISTS `docteurs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `docteurs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `adresse` varchar(45) DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docteurs`
--

LOCK TABLES `docteurs` WRITE;
/*!40000 ALTER TABLE `docteurs` DISABLE KEYS */;
INSERT INTO `docteurs` VALUES (1,'Selmi','Abdelaziz','cite elkhadhra','58084423','adzo','adzo'),(2,'Selmi','Olfa','hammamet, Nabeul','21168124','olfa','olfa'),(3,'teste00','teste','teste','teste@teste.teste','00000','testetestetestetestetestetesteteste');
/*!40000 ALTER TABLE `docteurs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fiches`
--

DROP TABLE IF EXISTS `fiches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fiches` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `taille` int(11) DEFAULT NULL,
  `poid` float DEFAULT NULL,
  `imc` float DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `observation` varchar(200) DEFAULT NULL,
  `idPatient` int(11) DEFAULT NULL,
  `dateFiche` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_fiche_patient_idx` (`idPatient`),
  CONSTRAINT `fk_fiche_patient` FOREIGN KEY (`idPatient`) REFERENCES `patients` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fiches`
--

LOCK TABLES `fiches` WRITE;
/*!40000 ALTER TABLE `fiches` DISABLE KEYS */;
INSERT INTO `fiches` VALUES (3,181,94,28.6927,'pre-obese','Un regime proteine fefefe',1,'2019-06-11'),(4,181,84.5,25.7929,'pre-obese','test id 4',1,'2019-06-11'),(5,181,79,24.114,'nomale','corpulence normale juste faut faire attention de ne pas prendre du poid',1,'2019-06-11'),(6,181,93.6,28.5706,'pre-obese',NULL,1,'2019-06-11');
/*!40000 ALTER TABLE `fiches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `dateDeNaissance` date DEFAULT NULL,
  `adresse` varchar(45) DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `idDocteur` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_patient_docteur_idx` (`idDocteur`),
  CONSTRAINT `fk_patient_docteur` FOREIGN KEY (`idDocteur`) REFERENCES `docteurs` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (1,'Fehri','Hakim','1989-05-30','sfax, Tnuisie','22502966','hakim','hakim',1);
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-18 19:38:34
