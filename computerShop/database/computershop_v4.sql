-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: computershop
-- ------------------------------------------------------
-- Server version	8.0.12

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
-- Current Database: `computershop`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `computershop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE `computershop`;

--
-- Table structure for table `bought_logs`
--

DROP TABLE IF EXISTS `bought_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bought_logs` (
  `id_bought_logs` int(11) NOT NULL AUTO_INCREMENT,
  `id_seller` int(11) NOT NULL,
  `id_computer` int(11) NOT NULL,
  `totalprice` decimal(7,2) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id_bought_logs`),
  KEY `fk_bought_logs_sellers_idx` (`id_seller`),
  KEY `fk_bought_logs_computers_idx` (`id_computer`),
  CONSTRAINT `fk_bought_logs_computers` FOREIGN KEY (`id_computer`) REFERENCES `computers` (`id_computer`),
  CONSTRAINT `fk_bought_logs_sellers` FOREIGN KEY (`id_seller`) REFERENCES `sellers` (`id_seller`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bought_logs`
--

LOCK TABLES `bought_logs` WRITE;
/*!40000 ALTER TABLE `bought_logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `bought_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cart` (
  `id_order` int(11) NOT NULL,
  `id_order_computer` int(11) NOT NULL,
  PRIMARY KEY (`id_order`,`id_order_computer`),
  KEY `fk_cart_orders_idx` (`id_order`),
  KEY `fk_cart_order_product_idx` (`id_order_computer`),
  CONSTRAINT `fk_cart_ordered_computer` FOREIGN KEY (`id_order_computer`) REFERENCES `ordered_computer` (`id_order_computer`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_cart_orders` FOREIGN KEY (`id_order`) REFERENCES `orders` (`id_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `clients` (
  `id_client` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` char(9) NOT NULL,
  `postcode` varchar(10) NOT NULL,
  `city` varchar(45) NOT NULL,
  PRIMARY KEY (`id_client`),
  KEY `fk_clients_users_idx` (`id_user`),
  CONSTRAINT `fk_clients_users` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,1,'firstname','lastname','email','phone','postcode','city'),(2,12,'firstname','lastname','email','phone','postcode','city');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `computers`
--

DROP TABLE IF EXISTS `computers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `computers` (
  `id_computer` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('desktop','laptop') NOT NULL,
  `ram` varchar(45) NOT NULL,
  `graphic` varchar(45) NOT NULL,
  `disk` varchar(45) NOT NULL,
  `system` varchar(45) NOT NULL,
  `price` decimal(8,2) NOT NULL,
  `ammount` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_computer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computers`
--

LOCK TABLES `computers` WRITE;
/*!40000 ALTER TABLE `computers` DISABLE KEYS */;
/*!40000 ALTER TABLE `computers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordered_computer`
--

DROP TABLE IF EXISTS `ordered_computer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ordered_computer` (
  `id_order_computer` int(11) NOT NULL AUTO_INCREMENT,
  `id_computer` int(11) NOT NULL,
  `ammount` int(11) NOT NULL,
  PRIMARY KEY (`id_order_computer`),
  KEY `fk_ordered_product_computers_idx` (`id_computer`),
  CONSTRAINT `fk_ordered_computer_computers` FOREIGN KEY (`id_computer`) REFERENCES `computers` (`id_computer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordered_computer`
--

LOCK TABLES `ordered_computer` WRITE;
/*!40000 ALTER TABLE `ordered_computer` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordered_computer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) NOT NULL,
  `state` enum('open','finished') NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id_order`),
  KEY `fk_orders_clients_idx` (`id_client`),
  CONSTRAINT `fk_orders_clients` FOREIGN KEY (`id_client`) REFERENCES `clients` (`id_client`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sellers`
--

DROP TABLE IF EXISTS `sellers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sellers` (
  `id_seller` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `salary` decimal(8,2) NOT NULL,
  PRIMARY KEY (`id_seller`),
  KEY `fk_sellers_users_idx` (`id_user`),
  CONSTRAINT `fk_sellers_users` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sellers`
--

LOCK TABLES `sellers` WRITE;
/*!40000 ALTER TABLE `sellers` DISABLE KEYS */;
INSERT INTO `sellers` VALUES (1,9,'asda','asda',70.60);
/*!40000 ALTER TABLE `sellers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sold_logs`
--

DROP TABLE IF EXISTS `sold_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sold_logs` (
  `id_sold_logs` int(11) NOT NULL AUTO_INCREMENT,
  `id_seller` int(11) NOT NULL,
  `id_order` int(11) NOT NULL,
  `totalprice` decimal(8,2) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id_sold_logs`),
  KEY `fk_sold_logs_sellers_idx` (`id_seller`),
  KEY `fk_sold_logs_orders_idx` (`id_order`),
  CONSTRAINT `fk_sold_logs_orders` FOREIGN KEY (`id_order`) REFERENCES `orders` (`id_order`),
  CONSTRAINT `fk_sold_logs_sellers` FOREIGN KEY (`id_seller`) REFERENCES `sellers` (`id_seller`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sold_logs`
--

LOCK TABLES `sold_logs` WRITE;
/*!40000 ALTER TABLE `sold_logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `sold_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `level` enum('admin','seller','client') NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'login','password','client'),(2,'login','password','client'),(3,'login','password','client'),(4,'asda','asdas','client'),(5,'fj','f','client'),(6,'kgk','khgk','client'),(7,'login','password','client'),(8,'asd','asd','seller'),(9,'asd','asd','seller'),(10,'login','password','client'),(11,'login','password','client'),(12,'login','password','client');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-21 18:40:25
