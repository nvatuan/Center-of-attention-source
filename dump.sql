-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: javabase
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb`
--

DROP TABLE IF EXISTS `tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb` (
  `id` int NOT NULL AUTO_INCREMENT,
  `serial` blob,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb`
--

LOCK TABLES `tb` WRITE;
/*!40000 ALTER TABLE `tb` DISABLE KEYS */;
INSERT INTO `tb` VALUES (1,_binary '¨\Ì\0sr\0main.algo.ImageCentralPixelsp9“èöú∂\0I\0queriedColor[\0depthOft\0[Ixr\0main.algo.ImageB\‰r\‘\X¢p\0I\0heightI\0width[\0pixelsq\0~\0xp\0\0\0\0\0\0ur\0[IM∫`&vÍ≤•\0\0xp\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0p','2021-08-29 17:08:28'),(2,_binary '¨\Ì\0sr\0main.algo.ImageCentralPixelsp9“èöú∂\0I\0queriedColor[\0depthOft\0[Ixr\0main.algo.ImageB\‰r\‘\X¢p\0I\0heightI\0width[\0pixelsq\0~\0xp\0\0\0\0\0\0ur\0[IM∫`&vÍ≤•\0\0xp\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0p','2021-08-29 17:09:17'),(3,_binary '¨\Ì\0sr\0main.algo.ImageCentralPixelsp9“èöú∂\0I\0queriedColor[\0depthOft\0[Ixr\0main.algo.ImageB\‰r\‘\X¢p\0I\0heightI\0width[\0pixelsq\0~\0xp\0\0\0	\0\0\0	ur\0[IM∫`&vÍ≤•\0\0xp\0\0\0Q\0\0\0\0\0\0\0\0\0 \0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0 \0\0\0\r\0\0\0É\0\0\06\0\0\0=\0\0_\0\0\0\r\0\0\0A\0\0\0\0\0\0A\0\0\0\0\0\0\0\0\0\0\09\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0P\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0G\0\0\0\0\0\0\0\0\0b\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0p','2021-08-29 17:11:18');
/*!40000 ALTER TABLE `tb` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-29 17:30:16
