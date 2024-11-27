-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` varchar(255) NOT NULL,
  `detail` longtext,
  `images` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `brand_id` varchar(255) NOT NULL,
  `category_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs6cydsualtsrprvlf2bb3lcam` (`brand_id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKs6cydsualtsrprvlf2bb3lcam` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('00d72d03-205f-45d9-9e67-756a775580f8','Laptop Details','customer/home/img/product08.png','Vivo Book',160,'bf9ed254-0e9c-4a8f-9316-c9a1aa9d92a0','b4af7ad2-51ab-4e59-baae-6e61abb7d4d4'),('1fd29787-f8fe-4264-a433-3b01369fcc0a','Laptop Details','customer/home/img/product03.png','Asus Gamming',160,'334674c0-6e79-49cc-995b-3b725fa5112d','b4af7ad2-51ab-4e59-baae-6e61abb7d4d4'),('286485bf-e349-4ee3-a8bb-ad53abb9b944','Laptop Details','customer/home/img/product01.png','HP Victus 16',130,'1bb25ba4-2aa5-4748-9e59-52e13fbb5f51','b4af7ad2-51ab-4e59-baae-6e61abb7d4d4'),('3f79e055-497b-4893-a619-592a079477fd','Laptop Details','customer/home/img/product07.png','SAMSUM GALAXY',200,'efb3d683-a1aa-4eac-8cc6-4c8dcc705d54','cea370d4-1ef3-4b3c-acad-e5f6b3fdeafc'),('5e7df4d7-1779-4f49-aab0-621b9e54a51e','CAMERA Details','customer/home/img/product09.png','CAMERA GALAXY',210,'efb3d683-a1aa-4eac-8cc6-4c8dcc705d54','cea370d4-1ef3-4b3c-acad-e5f6b3fdeafc'),('d6fd4a4c-5a83-4079-8565-1811fe46b3b2','Laptop Details','customer/home/img/product07.png','Apple Phone',160,'efb3d683-a1aa-4eac-8cc6-4c8dcc705d54','cea370d4-1ef3-4b3c-acad-e5f6b3fdeafc');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-27 10:50:46
