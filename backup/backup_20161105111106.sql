-- MySQL dump 10.13  Distrib 5.5.42, for Win32 (x86)
--
-- Host: localhost    Database: hardware
-- ------------------------------------------------------
-- Server version	5.5.42

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accountcol` varchar(45) DEFAULT NULL,
  `max_credit_amount` double DEFAULT NULL,
  `current_credit_amount` double DEFAULT NULL,
  `customer_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_account_customer1_idx` (`customer_id`),
  CONSTRAINT `fk_account_customer1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (0,'NaN','NaN'),(1,'0 NaN',''),(2,'0 NaN',''),(3,'0 NaN',''),(4,'0 NaN',''),(5,'0 NaN',''),(6,'0 NaN',''),(7,'0 NaN',''),(8,'0 NaN','');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expences`
--

DROP TABLE IF EXISTS `expences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expences` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `desc` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  `amount` varchar(45) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_expences_user1_idx` (`user_id`),
  CONSTRAINT `fk_expences_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expences`
--

LOCK TABLES `expences` WRITE;
/*!40000 ALTER TABLE `expences` DISABLE KEYS */;
/*!40000 ALTER TABLE `expences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grn`
--

DROP TABLE IF EXISTS `grn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grn` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `supplier_supplier_id` bigint(20) NOT NULL,
  `date` varchar(45) DEFAULT NULL,
  `item_count` int(11) DEFAULT NULL,
  `total` varchar(45) DEFAULT NULL,
  `paid_amount` varchar(45) DEFAULT NULL,
  `balance` varchar(45) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `payment_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_supplier_has_Stock_supplier1_idx` (`supplier_supplier_id`),
  KEY `fk_gr_user1_idx` (`user_id`),
  KEY `fk_grn_payment_type1_idx` (`payment_type_id`),
  CONSTRAINT `fk_grn_payment_type1` FOREIGN KEY (`payment_type_id`) REFERENCES `payment_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_gr_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_supplier_has_Stock_supplier1` FOREIGN KEY (`supplier_supplier_id`) REFERENCES `supplier` (`supplier_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grn`
--

LOCK TABLES `grn` WRITE;
/*!40000 ALTER TABLE `grn` DISABLE KEYS */;
/*!40000 ALTER TABLE `grn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grn_details`
--

DROP TABLE IF EXISTS `grn_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grn_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gr_id` bigint(20) NOT NULL,
  `Stock_stock_id` bigint(20) NOT NULL,
  `qty` varchar(45) DEFAULT NULL,
  `credit_price` double DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `sub_total` double DEFAULT NULL,
  `Item_item_code` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_gr_has_Stock_Stock1_idx` (`Stock_stock_id`),
  KEY `fk_gr_has_Stock_gr1_idx` (`gr_id`),
  KEY `fk_grn_details_Item1_idx` (`Item_item_code`),
  CONSTRAINT `fk_grn_details_Item1` FOREIGN KEY (`Item_item_code`) REFERENCES `item` (`item_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_gr_has_Stock_gr1` FOREIGN KEY (`gr_id`) REFERENCES `grn` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_gr_has_Stock_Stock1` FOREIGN KEY (`Stock_stock_id`) REFERENCES `stock` (`stock_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grn_details`
--

LOCK TABLES `grn_details` WRITE;
/*!40000 ALTER TABLE `grn_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `grn_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `invoice_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(45) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `item_count` int(11) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `payed_amount` double DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `payment_type_id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  PRIMARY KEY (`invoice_id`),
  UNIQUE KEY `invoice_id_UNIQUE` (`invoice_id`),
  KEY `fk_invoice_user1_idx` (`user_id`),
  KEY `fk_invoice_payment_type1_idx` (`payment_type_id`),
  KEY `fk_invoice_customer1_idx` (`customer_id`),
  CONSTRAINT `fk_invoice_customer1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoice_payment_type1` FOREIGN KEY (`payment_type_id`) REFERENCES `payment_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoice_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,'2016/06/02',10,1,1,10,0,1,1),(2,'2016/06/02',10,1,1,10,0,1,2),(3,'2016/06/02',10,1,1,10,0,1,3),(4,'2016/06/04',0,0,1,0,0,1,4),(5,'2016/06/04',50,1,1,50,0,1,5),(6,'2016/06/04',70,1,1,0,0,1,6),(7,'2016/06/05',10,1,1,10,0,1,7),(8,'2016/06/05',10,1,1,0,0,1,8);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_details`
--

DROP TABLE IF EXISTS `invoice_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `invoice_invoice_id` bigint(20) NOT NULL,
  `Item_item_code` bigint(20) NOT NULL,
  `qty` double DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `sub_total` double DEFAULT NULL,
  `buying_price` varchar(45) DEFAULT NULL,
  `Stock_stock_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_has_Item_Item1_idx` (`Item_item_code`),
  KEY `fk_invoice_has_Item_invoice1_idx` (`invoice_invoice_id`),
  KEY `fk_invoice_details_Stock1_idx` (`Stock_stock_id`),
  CONSTRAINT `fk_invoice_details_Stock1` FOREIGN KEY (`Stock_stock_id`) REFERENCES `stock` (`stock_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoice_has_Item_invoice1` FOREIGN KEY (`invoice_invoice_id`) REFERENCES `invoice` (`invoice_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoice_has_Item_Item1` FOREIGN KEY (`Item_item_code`) REFERENCES `item` (`item_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_details`
--

LOCK TABLES `invoice_details` WRITE;
/*!40000 ALTER TABLE `invoice_details` DISABLE KEYS */;
INSERT INTO `invoice_details` VALUES (1,1,3,1,10,0,10,'5',3),(2,2,3,1,10,0,10,'5',3),(3,3,3,1,10,0,10,'5',3),(4,5,3,5,10,0,50,'5',3),(5,6,3,2,10,0,20,'5',3),(6,7,3,1,10,0,10,'10',4),(7,8,3,1,10,0,10,'10',5);
/*!40000 ALTER TABLE `invoice_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `item_code` bigint(20) NOT NULL AUTO_INCREMENT,
  `barcode` varchar(45) DEFAULT NULL,
  `barcode1` varchar(250) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `desc` varchar(100) DEFAULT NULL,
  `manufacturer` varchar(45) DEFAULT NULL,
  `threshold_value` double DEFAULT NULL,
  `unit_fixed_id` bigint(20) NOT NULL,
  PRIMARY KEY (`item_code`),
  UNIQUE KEY `id_UNIQUE` (`item_code`),
  UNIQUE KEY `barcode_UNIQUE` (`barcode`),
  KEY `fk_Item_unit_fixed1_idx` (`unit_fixed_id`),
  CONSTRAINT `fk_Item_unit_fixed1` FOREIGN KEY (`unit_fixed_id`) REFERENCES `unit_fixed` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (3,'sd555','','jkjo','','kk',5,1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_type`
--

DROP TABLE IF EXISTS `payment_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_type`
--

LOCK TABLES `payment_type` WRITE;
/*!40000 ALTER TABLE `payment_type` DISABLE KEYS */;
INSERT INTO `payment_type` VALUES (1,'Cash'),(2,'Credit'),(3,'Cheque'),(4,'Card');
/*!40000 ALTER TABLE `payment_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `srn_details`
--

DROP TABLE IF EXISTS `srn_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `srn_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Stock_returned_id` bigint(20) NOT NULL,
  `Item_item_code` bigint(20) NOT NULL,
  `qty` varchar(45) DEFAULT NULL,
  `Stock_stock_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Stock_returned_has_Item_Item1_idx` (`Item_item_code`),
  KEY `fk_Stock_returned_has_Item_Stock_returned1_idx` (`Stock_returned_id`),
  KEY `fk_srn_details_Stock1_idx` (`Stock_stock_id`),
  CONSTRAINT `fk_srn_details_Stock1` FOREIGN KEY (`Stock_stock_id`) REFERENCES `stock` (`stock_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Stock_returned_has_Item_Item1` FOREIGN KEY (`Item_item_code`) REFERENCES `item` (`item_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Stock_returned_has_Item_Stock_returned1` FOREIGN KEY (`Stock_returned_id`) REFERENCES `stock_returned` (`Stock_returned_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `srn_details`
--

LOCK TABLES `srn_details` WRITE;
/*!40000 ALTER TABLE `srn_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `srn_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `stock_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_in` varchar(45) DEFAULT NULL,
  `exp_date` varchar(45) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  `cash_price` double DEFAULT NULL,
  `credit_price` double DEFAULT NULL,
  `Item_item_code` bigint(20) NOT NULL,
  `discount_margin` double DEFAULT NULL,
  PRIMARY KEY (`stock_id`),
  UNIQUE KEY `stock_id_UNIQUE` (`stock_id`),
  KEY `fk_Stock_Item_idx` (`Item_item_code`),
  CONSTRAINT `fk_Stock_Item` FOREIGN KEY (`Item_item_code`) REFERENCES `item` (`item_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (3,'2016/06/02','NaN',0,5,10,3,5),(4,'2016/06/02','NaN',0,10,12,3,2),(5,'2016/06/02','NaN',0,10,12,3,2);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_returned`
--

DROP TABLE IF EXISTS `stock_returned`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_returned` (
  `Stock_returned_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `desc` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  `supplier_supplier_id` bigint(20) NOT NULL,
  `item_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`Stock_returned_id`),
  UNIQUE KEY `Stock_returned_id_UNIQUE` (`Stock_returned_id`),
  KEY `fk_Stock_returned_supplier1_idx` (`supplier_supplier_id`),
  CONSTRAINT `fk_Stock_returned_supplier1` FOREIGN KEY (`supplier_supplier_id`) REFERENCES `supplier` (`supplier_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_returned`
--

LOCK TABLES `stock_returned` WRITE;
/*!40000 ALTER TABLE `stock_returned` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock_returned` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `supplier_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `tel` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`supplier_id`),
  UNIQUE KEY `supplier_id_UNIQUE` (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit_fixed`
--

DROP TABLE IF EXISTS `unit_fixed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit_fixed` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `unit` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit_fixed`
--

LOCK TABLES `unit_fixed` WRITE;
/*!40000 ALTER TABLE `unit_fixed` DISABLE KEYS */;
INSERT INTO `unit_fixed` VALUES (1,'l');
/*!40000 ALTER TABLE `unit_fixed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user` varchar(45) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `nic` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','1000:a36bda6ff9757ccca316608921a8ae70036d12808ba3d6b9:f7bfb3f14262f93ed7b140f832539456ed74aa14ca0e7336',1,'0774061783','931120255v'),(3,'admin1','1000:46a99f5e325471239509d194384e2456938889bbf817e811:0ff4cf8fc96a290209594ae84e3f24c0cf77e9d7239de7b3',1,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-05 11:11:07
