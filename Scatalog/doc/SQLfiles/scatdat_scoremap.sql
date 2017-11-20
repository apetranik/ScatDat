-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: scatdat
-- ------------------------------------------------------
-- Server version	5.7.20

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
-- Table structure for table `scoremap`
--

DROP TABLE IF EXISTS `scoremap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scoremap` (
  `scoremapID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `value_0` double DEFAULT NULL,
  `value_1` double DEFAULT NULL,
  `value_2` double DEFAULT NULL,
  `value_3` double DEFAULT NULL,
  `value_4` double DEFAULT NULL,
  `enjoyment_0` double DEFAULT NULL,
  `enjoyment_1` double DEFAULT NULL,
  `enjoyment_2` double DEFAULT NULL,
  `enjoyment_3` double DEFAULT NULL,
  `enjoyment_4` double DEFAULT NULL,
  `workload_0` double DEFAULT NULL,
  `workload_1` double DEFAULT NULL,
  `workload_2` double DEFAULT NULL,
  `workload_3` double DEFAULT NULL,
  `workload_4` double DEFAULT NULL,
  `difficulty_0` double DEFAULT NULL,
  `difficulty_1` double DEFAULT NULL,
  `difficulty_2` double DEFAULT NULL,
  `difficulty_3` double DEFAULT NULL,
  `difficulty_4` double DEFAULT NULL,
  PRIMARY KEY (`scoremapID`),
  KEY `fk1` (`userID`),
  CONSTRAINT `scoremap_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scoremap`
--

LOCK TABLES `scoremap` WRITE;
/*!40000 ALTER TABLE `scoremap` DISABLE KEYS */;
/*!40000 ALTER TABLE `scoremap` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-19 23:56:31
