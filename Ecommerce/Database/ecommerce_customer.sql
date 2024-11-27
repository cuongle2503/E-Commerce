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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('34b5d24b-600f-4fb7-afe5-2fb26a60d583',NULL,'admin@gmail.com',NULL,'$2a$10$GQzmyRLYvoh/nyRR.3a/L.tKeectp7u5dByKI70kgUh16PqmcLlTm',NULL),('47a4ba77-e1f8-455a-abe2-ce4d8f79b7c5','Đăk Lăk','cuongcuong@gmail.com','Lê Văn Cường','$2a$10$E4rRc8VUwI.2z0Y8mOjkWuFMGzOdgRg5kNkFoVJGRQ/CiCGUBny1K','0973789787'),('57ed6fdb-e526-4f7a-8ad8-46a7278a7be8','DakLak','cuonglevan@gmail.com','le cuong','$2a$10$N95D6HzUDrK9Cdq.pU6fI.wqk/8DHccSQtUpClIsJ5J6IEe1kVoVK','0973789787'),('8e5497d7-f648-47bb-94e8-f6adc6e3aede','Đăk Lăk','cuongle@gmail.com','Lê Văn Cường','$2a$10$EmusdxDuH8fCEyIbYbOHi.SrFSs7Xos6iLuLGIambXl.OuP7PR70a','0973789787'),('d1ca7916-e890-4df7-9984-34e672035d3c','DakLak','customer@gmail.com','Lê Văn Cường','$2a$10$jbjNN1sRUCkKk.LP7buLWeGgMTnumUsITWW2QZZpiNFI68qWu64SG','0973789787');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
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
