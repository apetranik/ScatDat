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
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `userID` int(11) DEFAULT NULL,
  `courseID` int(11) DEFAULT NULL,
  `term` char(1) DEFAULT NULL,
  `year` int(4) DEFAULT NULL,
  `professorID` int(11) DEFAULT NULL,
  `overallScore` double DEFAULT NULL,
  `enjoyment` double DEFAULT NULL,
  `difficulty` double DEFAULT NULL,
  `value` double DEFAULT NULL,
  `workload` double DEFAULT NULL,
  `emoji` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `comment` text,
  `date` date DEFAULT NULL,
  `reviewID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`reviewID`),
  KEY `fk1` (`userID`),
  KEY `fk2` (`courseID`),
  KEY `fk3` (`professorID`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`),
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`),
  CONSTRAINT `review_ibfk_3` FOREIGN KEY (`professorID`) REFERENCES `professor` (`professorID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,36121,'2',2017,1,5,4,3,2,1,'12213','sdfsdf','2017-12-01',1),(1,36122,'2',2017,2,3,2,3,4,5,'wdsfwer','sdkfjwlerk','2017-11-05',2),(2,36123,'3',2017,3,4,4,3,5,2,'lsdkfjleskd','alskdjflsdkfjl sfd','2016-04-03',8);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-19 19:28:28
