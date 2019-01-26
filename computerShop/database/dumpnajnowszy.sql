-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: computershop
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (4,15,'firstname','lastname','email','phone','postcode','city');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sellers`
--

LOCK TABLES `sellers` WRITE;
/*!40000 ALTER TABLE `sellers` DISABLE KEYS */;
INSERT INTO `sellers` VALUES (2,16,'firstname','lastname',3000.00);
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','admin'),(15,'client','client','client'),(16,'seller','seller','seller');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'computershop'
--

--
-- Dumping routines for database 'computershop'
--
/*!50003 DROP PROCEDURE IF EXISTS `addClient` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addClient`(_first_name VARCHAR(45), _last_name VARCHAR(45), _email VARCHAR(50), _phone CHAR(9), _postcode VARCHAR(10), _city VARCHAR(45), _login VARCHAR(45), _password VARCHAR(45))
BEGIN
	DECLARE _id_user INT;
	SET @login=_login;
    SET @passwd=_password;
    SET @localhost ='localhost';
    
    START TRANSACTION;
    
	SET @query = CONCAT('CREATE USER \'', @login,'\'@\'localhost\' IDENTIFIED BY \'', @passwd,'\'');
    PREPARE stm FROM @query;
    EXECUTE stm;
    DEALLOCATE PREPARE stm;
    
    INSERT INTO users(id_user, login, password, level)
    VALUES(
    NULL,
    _login, 
    _password, 
    "client"
    );
    
	SELECT LAST_INSERT_ID() into _id_user;
    
	INSERT INTO clients(id_user, first_name, last_name, email, phone, postcode, city)
    VALUES( 
    _id_user,
    _first_name,
    _last_name,
    _email,
    _phone,
    _postcode,
    _city
	);
    
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `addSeller` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addSeller`(_first_name VARCHAR(45), _last_name VARCHAR(45), _salary DECIMAL(8,2), _login VARCHAR(45), _password VARCHAR(45))
BEGIN
	DECLARE _id_user INT;
    SET @login=_login;
    SET @passwd=_password;
    
    START TRANSACTION;
    
    SET @query = CONCAT('CREATE USER \'', @login,'\'@\'localhost\' IDENTIFIED BY \'', @passwd,'\'');
    PREPARE stm FROM @query;
    EXECUTE stm;
    DEALLOCATE PREPARE stm;
    
    
	INSERT INTO users(id_user, login, password, level)
    VALUES(
    NULL,
    _login, 
    _password, 
    "seller"
    );
    
	SELECT LAST_INSERT_ID() into _id_user;

    
	INSERT INTO sellers(id_user, first_name, last_name, salary)
    VALUES( 
    _id_user,
    _first_name,
    _last_name,
    _salary
	);
    
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-26 17:55:01
