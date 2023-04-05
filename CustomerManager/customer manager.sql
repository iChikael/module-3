-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: c12_customer_manager
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Laptop'),(2,'Phone');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(245) DEFAULT NULL,
  `address` varchar(245) DEFAULT NULL,
  `email` varchar(245) DEFAULT NULL,
  `createat` datetime DEFAULT NULL,
  `image` varchar(245) DEFAULT NULL,
  `customer_type` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_customer_customer_type_idx` (`customer_type`),
  CONSTRAINT `fk_customer_customer_type` FOREIGN KEY (`customer_type`) REFERENCES `customer_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Quang Dang123cscs','28 NTP','quang@gmail.com','2023-10-10 00:00:00','',1),(2,'Quang Dangxxx','28 NTP','quang@gmail.com','2023-10-10 00:00:00','',1),(4,'Quang Dang','28 NTP','quang','2023-10-10 00:00:00',NULL,NULL),(5,'Quang Dang','28 NTP','quang','2023-10-10 00:00:00',NULL,NULL),(6,'Quang Dang','28 NTP','quang','2023-10-10 00:00:00',NULL,NULL),(7,'Amir Banksaaa','Sint blanditiis omni','bywiwyvaw@mailinator.com','2023-03-27 00:00:00','Quia magna est nemo ',1),(8,'Dane Mcdaniel','Esse consectetur r','kekamu@mailinator.com','2023-03-28 00:00:00','Ex dolorem et adipis',1),(9,'Gloria Mooreqqqq','Ducimus est porro ','wojaq@mailinator.com','2023-03-28 00:00:00','Non sed voluptas eiu',2),(10,'Sarah Bradshaw','Qui dignissimos exer','gusuhip@mailinator.com','2023-03-28 00:00:00','Earum neque explicab',1),(13,'Sony v2','Ut veniam autem imp','xelirec@mailinator.com','2023-03-29 00:00:00','',1),(14,'Sony v112345','Voluptatem sit sint','xelirec@mailinator.com','2023-03-29 00:00:00','Qui voluptatem Tota',1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_type`
--

DROP TABLE IF EXISTS `customer_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_type`
--

LOCK TABLES `customer_type` WRITE;
/*!40000 ALTER TABLE `customer_type` DISABLE KEYS */;
INSERT INTO `customer_type` VALUES (1,'VIP'),(2,'Normal'),(3,'Supper VIP');
/*!40000 ALTER TABLE `customer_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `id_category` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_category_category_idx` (`id_category`),
  CONSTRAINT `fk_id_category_category` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Iphone x',11,'zzzzzzzz',12000,NULL),(2,'Odessa Stanley',870,'Aut quia in nulla et',347,NULL),(3,'Addison Sutton',551,'Doloremque quod cons',985,NULL),(4,'Allistair Wong',718,'Consequatur quia omn',352,NULL);
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

-- Dump completed on 2023-03-29 17:19:43
