-- MySQL dump 10.13  Distrib 8.0.15, for macos10.14 (x86_64)
--
-- Host: localhost    Database: glbank
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Account` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AccName` int(11) NOT NULL,
  `money` int(11) NOT NULL,
  `IDClient` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDClient` (`IDClient`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`IDClient`) REFERENCES `client` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `card` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PIN` varchar(4) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `expireY` int(11) NOT NULL,
  `expireM` int(11) NOT NULL,
  `IDAccount` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDAccount` (`IDAccount`),
  CONSTRAINT `card_ibfk_1` FOREIGN KEY (`IDAccount`) REFERENCES `account` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CardTrans`
--

DROP TABLE IF EXISTS `CardTrans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `CardTrans` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `transDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `transAmount` int(11) NOT NULL,
  `IDCard` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDCard` (`IDCard`),
  CONSTRAINT `cardtrans_ibfk_1` FOREIGN KEY (`IDCard`) REFERENCES `card` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CardTrans`
--

LOCK TABLES `CardTrans` WRITE;
/*!40000 ALTER TABLE `CardTrans` DISABLE KEYS */;
/*!40000 ALTER TABLE `CardTrans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Client`
--

DROP TABLE IF EXISTS `Client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Client` (
  `fname` varchar(30) NOT NULL,
  `lname` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Client`
--

LOCK TABLES `Client` WRITE;
/*!40000 ALTER TABLE `Client` DISABLE KEYS */;
/*!40000 ALTER TABLE `Client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Employee` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(30) NOT NULL,
  `lname` varchar(30) NOT NULL,
  `position` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `position` (`position`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`position`) REFERENCES `positions` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FailCardLog`
--

DROP TABLE IF EXISTS `FailCardLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `FailCardLog` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FailDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `IDCard` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDCard` (`IDCard`),
  CONSTRAINT `failcardlog_ibfk_1` FOREIGN KEY (`IDCard`) REFERENCES `card` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FailCardLog`
--

LOCK TABLES `FailCardLog` WRITE;
/*!40000 ALTER TABLE `FailCardLog` DISABLE KEYS */;
/*!40000 ALTER TABLE `FailCardLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loginClient`
--

DROP TABLE IF EXISTS `loginClient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `loginClient` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `IDClient` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDClient` (`IDClient`),
  CONSTRAINT `loginclient_ibfk_1` FOREIGN KEY (`IDClient`) REFERENCES `client` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loginClient`
--

LOCK TABLES `loginClient` WRITE;
/*!40000 ALTER TABLE `loginClient` DISABLE KEYS */;
/*!40000 ALTER TABLE `loginClient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loginEmployee`
--

DROP TABLE IF EXISTS `loginEmployee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `loginEmployee` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `IDEmployee` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDEmployee` (`IDEmployee`),
  CONSTRAINT `loginemployee_ibfk_1` FOREIGN KEY (`IDEmployee`) REFERENCES `employee` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loginEmployee`
--

LOCK TABLES `loginEmployee` WRITE;
/*!40000 ALTER TABLE `loginEmployee` DISABLE KEYS */;
/*!40000 ALTER TABLE `loginEmployee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loginHistory`
--

DROP TABLE IF EXISTS `loginHistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `loginHistory` (
  `loginTIme` timestamp NOT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `success` tinyint(1) NOT NULL,
  `IDLoginClient` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDLoginClient` (`IDLoginClient`),
  CONSTRAINT `loginhistory_ibfk_1` FOREIGN KEY (`IDLoginClient`) REFERENCES `loginclient` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loginHistory`
--

LOCK TABLES `loginHistory` WRITE;
/*!40000 ALTER TABLE `loginHistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `loginHistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Positions`
--

DROP TABLE IF EXISTS `Positions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Positions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Positions`
--

LOCK TABLES `Positions` WRITE;
/*!40000 ALTER TABLE `Positions` DISABLE KEYS */;
/*!40000 ALTER TABLE `Positions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Transaction`
--

DROP TABLE IF EXISTS `Transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Transaction` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `transAmount` int(11) NOT NULL,
  `transDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `RecAccount` int(11) NOT NULL,
  `IDAccount` int(11) NOT NULL,
  `IDEmployee` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDAccount` (`IDAccount`),
  KEY `IDEmployee` (`IDEmployee`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`IDAccount`) REFERENCES `account` (`ID`),
  CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`IDEmployee`) REFERENCES `employee` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Transaction`
--

LOCK TABLES `Transaction` WRITE;
/*!40000 ALTER TABLE `Transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `Transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'glbank'
--

--
-- Dumping routines for database 'glbank'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-10 10:25:37
