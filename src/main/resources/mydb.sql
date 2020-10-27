-- MySQL dump 10.13  Distrib 8.0.21, for Linux (x86_64)
--
-- Host: localhost    Database: AuthControl
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `Entrust`
--

DROP TABLE IF EXISTS `Entrust`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Entrust` (
  `entrustID` int NOT NULL AUTO_INCREMENT,
  `status` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `entrustStartDate` datetime NOT NULL,
  `entrustEndDate` datetime NOT NULL,
  `subTask` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `entrustWorker` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`entrustID`),
  UNIQUE KEY `entrustID_UNIQUE` (`entrustID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Entrust`
--

LOCK TABLES `Entrust` WRITE;
/*!40000 ALTER TABLE `Entrust` DISABLE KEYS */;
INSERT INTO `Entrust` VALUES (1,'good','2020-10-23 21:13:38','2020-10-23 21:13:38','1','551801'),(2,'good','2020-10-24 10:18:10','2020-10-24 15:34:10','1','551801'),(3,'委托中','2020-10-24 10:18:10','2020-10-24 10:18:10','C3','220102');
/*!40000 ALTER TABLE `Entrust` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Notice`
--

DROP TABLE IF EXISTS `Notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Notice` (
  `noticeID` int NOT NULL AUTO_INCREMENT,
  `message` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `receiver` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `noticeDate` datetime NOT NULL,
  `isRead` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`noticeID`),
  UNIQUE KEY `noticeID_UNIQUE` (`noticeID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Notice`
--

LOCK TABLES `Notice` WRITE;
/*!40000 ALTER TABLE `Notice` DISABLE KEYS */;
INSERT INTO `Notice` VALUES (1,'你好','551801','2020-10-10 22:20:20',1),(2,'您在项目编号为3b1-4143-a156-f的项目中有等待执行的任务      Tue Oct 27 15:16:01 CST 2020','551808','2020-10-27 15:16:02',0),(5,'您在项目编号为29d-48a9-af01-6，项目名为测试工程Plus的项目被中止。您任务编号为29d-48a9-af01-6_1的任务被中止。       2020-10-27 07:36:47','220101','2020-10-27 19:36:47',0),(6,'您在项目编号为29d-48a9-af01-6，项目名为测试工程Plus的项目被中止。您任务编号为29d-48a9-af01-6_2的任务被中止。       2020-10-27 07:36:47','220103','2020-10-27 19:36:47',0),(7,'您在项目编号为29d-48a9-af01-6，项目名为测试工程Plus的项目被中止。您任务编号为29d-48a9-af01-6_3的任务被中止。       2020-10-27 07:36:47','25c-4447-8','2020-10-27 19:36:47',1),(8,'您在项目编号为29d-48a9-af01-6，项目名为测试工程Plus的项目被中止。您任务编号为29d-48a9-af01-6_4的任务被中止。       2020-10-27 07:36:47','220103','2020-10-27 19:36:47',0),(9,'您在项目编号为2d2-41a6-ad74-e，项目名为测试工程Plus4的项目被中止。您任务编号为2d2-41a6-ad74-e_1的任务被中止。       2020-10-27 07:38:47','220101','2020-10-27 19:38:47',0),(10,'您在项目编号为2d2-41a6-ad74-e，项目名为测试工程Plus4的项目被中止。您任务编号为2d2-41a6-ad74-e_2的任务被中止。       2020-10-27 07:38:47','220103','2020-10-27 19:38:47',0),(11,'您在项目编号为2d2-41a6-ad74-e，项目名为测试工程Plus4的项目被中止。您任务编号为2d2-41a6-ad74-e_3的任务被中止。       2020-10-27 07:38:47','25c-4447-8','2020-10-27 19:38:47',1),(12,'您在项目编号为2d2-41a6-ad74-e，项目名为测试工程Plus4的项目被中止。您任务编号为2d2-41a6-ad74-e_4的任务被中止。       2020-10-27 07:38:47','220103','2020-10-27 19:38:47',0),(13,'您在项目编号为3b1-4143-a156-f，项目名为测试工程Plus3的项目被中止。您任务编号为3b1-4143-a156-f_1的任务被中止。       2020-10-27 07:40:38','220101','2020-10-27 19:40:39',0),(14,'您在项目编号为3b1-4143-a156-f，项目名为测试工程Plus3的项目被中止。您任务编号为3b1-4143-a156-f_2的任务被中止。       2020-10-27 07:40:38','220103','2020-10-27 19:40:39',0),(15,'您在项目编号为3b1-4143-a156-f，项目名为测试工程Plus3的项目被中止。您任务编号为3b1-4143-a156-f_3的任务被中止。       2020-10-27 07:40:38','25c-4447-8','2020-10-27 19:40:39',1),(16,'您在项目编号为3b1-4143-a156-f，项目名为测试工程Plus3的项目被中止。您任务编号为3b1-4143-a156-f_4的任务被中止。       2020-10-27 07:40:38','220103','2020-10-27 19:40:39',0),(17,'您所参与的项目编号为29d-48a9-af01-6，项目名称为测试工程Plus的任务被重新启动。       2020-10-27 08:56:57','220101','2020-10-27 20:56:58',0),(18,'您所参与的项目编号为29d-48a9-af01-6，项目名称为测试工程Plus的任务被重新启动。       2020-10-27 08:56:58','220103','2020-10-27 20:56:58',0),(19,'您所参与的项目编号为29d-48a9-af01-6，项目名称为测试工程Plus的任务被重新启动。       2020-10-27 08:56:58','25c-4447-8','2020-10-27 20:56:58',1),(20,'您所参与的项目编号为29d-48a9-af01-6，项目名称为测试工程Plus的任务被重新启动。       2020-10-27 08:56:58','220103','2020-10-27 20:56:58',0);
/*!40000 ALTER TABLE `Notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Permission`
--

DROP TABLE IF EXISTS `Permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Permission` (
  `permissionID` int NOT NULL AUTO_INCREMENT,
  `permissionName` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `permissionTarget` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`permissionID`),
  UNIQUE KEY `permissionID_UNIQUE` (`permissionID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Permission`
--

LOCK TABLES `Permission` WRITE;
/*!40000 ALTER TABLE `Permission` DISABLE KEYS */;
INSERT INTO `Permission` VALUES (1,'read','project'),(2,'start','project'),(3,'end','project'),(4,'restart','project'),(5,'create','project'),(6,'read','user'),(7,'add','user'),(8,'edit','user'),(9,'changePosition','user'),(10,'changePwd','user'),(11,'add','role'),(12,'edit','role'),(13,'add','permission'),(14,'edit','permission'),(15,'save','project'),(16,'edit','project'),(17,'delete','BeDelete');
/*!40000 ALTER TABLE `Permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PermissionRole`
--

DROP TABLE IF EXISTS `PermissionRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PermissionRole` (
  `roleID` int NOT NULL,
  `PermissionID` int NOT NULL,
  `ID` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `fk_PermissionRole_2_idx` (`PermissionID`),
  KEY `fk_PermissionRole_2_idx1` (`roleID`),
  CONSTRAINT `fk_PermissionRole_1` FOREIGN KEY (`PermissionID`) REFERENCES `Permission` (`permissionID`),
  CONSTRAINT `fk_PermissionRole_2` FOREIGN KEY (`roleID`) REFERENCES `RoleD` (`roleID`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PermissionRole`
--

LOCK TABLES `PermissionRole` WRITE;
/*!40000 ALTER TABLE `PermissionRole` DISABLE KEYS */;
INSERT INTO `PermissionRole` VALUES (1,1,1),(1,6,2),(1,10,3),(2,1,4),(2,6,5),(2,10,6),(3,1,7),(3,6,8),(3,10,9),(4,1,10),(4,2,11),(4,3,12),(4,4,13),(4,5,14),(4,6,15),(4,10,16),(5,6,17),(5,7,18),(5,8,19),(5,9,20),(5,10,21),(5,1,22),(5,11,26),(5,12,27),(5,13,32),(5,14,33),(4,16,34),(6,17,35);
/*!40000 ALTER TABLE `PermissionRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Project`
--

DROP TABLE IF EXISTS `Project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Project` (
  `projectID` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `projectName` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `creator` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `createDate` datetime NOT NULL,
  `finishDate` datetime DEFAULT NULL,
  `path` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`projectID`),
  UNIQUE KEY `ProjectID_UNIQUE` (`projectID`),
  UNIQUE KEY `ProjectName_UNIQUE` (`projectName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Project`
--

LOCK TABLES `Project` WRITE;
/*!40000 ALTER TABLE `Project` DISABLE KEYS */;
INSERT INTO `Project` VALUES ('038-45f4-9b98-d','测试工程','551808','中止状态','2020-10-27 14:35:03',NULL,'a'),('29d-48a9-af01-6','测试工程Plus','551808','正在进行','2020-10-27 14:37:01',NULL,'a'),('2d2-41a6-ad74-e','测试工程Plus4','551808','中止状态','2020-10-27 15:18:30',NULL,'a'),('3b1-4143-a156-f','测试工程Plus3','551808','中止状态','2020-10-27 15:16:02',NULL,'a'),('441-4287-a686-3','测试工程Plus2','551808','正在进行','2020-10-27 14:39:38',NULL,'a'),('66d-4b0a-9278-5','测试工程Plus5','551808','正在进行','2020-10-27 15:19:15',NULL,'a'),('adb-4205-ab2e-8','希望工程','551808','正在进行','2020-10-26 22:07:57',NULL,'a'),('eca-4ef2-8b7c-6','小学','551808','正在进行','2020-10-26 22:10:19',NULL,'a');
/*!40000 ALTER TABLE `Project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RoleD`
--

DROP TABLE IF EXISTS `RoleD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RoleD` (
  `roleID` int NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`roleID`),
  UNIQUE KEY `roleID_UNIQUE` (`roleID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RoleD`
--

LOCK TABLES `RoleD` WRITE;
/*!40000 ALTER TABLE `RoleD` DISABLE KEYS */;
INSERT INTO `RoleD` VALUES (1,'ROLE_WORKERA'),(2,'ROLE_WORKERB'),(3,'ROLE_WORKERC'),(4,'ROLE_ADMIN'),(5,'ROLE_SUPERADMIN'),(6,'BEEN_DELETED');
/*!40000 ALTER TABLE `RoleD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SubTask`
--

DROP TABLE IF EXISTS `SubTask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SubTask` (
  `taskID` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `typeID` int NOT NULL,
  `status` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `executor` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `projectID` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `taskPosition` int NOT NULL,
  PRIMARY KEY (`taskID`),
  UNIQUE KEY `taskID_UNIQUE` (`taskID`),
  KEY `fk_SubTask_1_idx` (`typeID`),
  CONSTRAINT `fk_SubTask_1` FOREIGN KEY (`typeID`) REFERENCES `TaskType` (`typeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SubTask`
--

LOCK TABLES `SubTask` WRITE;
/*!40000 ALTER TABLE `SubTask` DISABLE KEYS */;
INSERT INTO `SubTask` VALUES ('038-45f4-9b98-d_1',1,'已完成','220101','038-45f4-9b98-d',0),('038-45f4-9b98-d_2',3,'进行中','220103','038-45f4-9b98-d',1),('038-45f4-9b98-d_3',2,'等待中','25c-4447-8','038-45f4-9b98-d',2),('038-45f4-9b98-d_4',3,'等待中','220103','038-45f4-9b98-d',3),('29d-48a9-af01-6_1',1,'进行中','220101','29d-48a9-af01-6',0),('29d-48a9-af01-6_2',3,'等待','220103','29d-48a9-af01-6',1),('29d-48a9-af01-6_3',2,'等待','25c-4447-8','29d-48a9-af01-6',2),('29d-48a9-af01-6_4',3,'等待','220103','29d-48a9-af01-6',3),('2d2-41a6-ad74-e_1',1,'中止','220101','2d2-41a6-ad74-e',0),('2d2-41a6-ad74-e_2',3,'中止','220103','2d2-41a6-ad74-e',1),('2d2-41a6-ad74-e_3',2,'中止','25c-4447-8','2d2-41a6-ad74-e',2),('2d2-41a6-ad74-e_4',3,'中止','220103','2d2-41a6-ad74-e',3),('3b1-4143-a156-f_1',1,'中止','220101','3b1-4143-a156-f',0),('3b1-4143-a156-f_2',3,'中止','220103','3b1-4143-a156-f',1),('3b1-4143-a156-f_3',2,'中止','25c-4447-8','3b1-4143-a156-f',2),('3b1-4143-a156-f_4',3,'中止','220103','3b1-4143-a156-f',3),('441-4287-a686-3_1',1,'进行中','220101','441-4287-a686-3',0),('441-4287-a686-3_2',3,'等待中','220103','441-4287-a686-3',1),('441-4287-a686-3_3',2,'等待中','25c-4447-8','441-4287-a686-3',2),('441-4287-a686-3_4',3,'等待中','220103','441-4287-a686-3',3),('66d-4b0a-9278-5_1',1,'进行中','220101','66d-4b0a-9278-5',0),('66d-4b0a-9278-5_2',3,'等待中','220103','66d-4b0a-9278-5',1),('66d-4b0a-9278-5_3',2,'等待中','25c-4447-8','66d-4b0a-9278-5',2),('66d-4b0a-9278-5_4',3,'等待中','220103','66d-4b0a-9278-5',3),('C3',3,'进行中','220103','adb-4205-ab2e-8',1);
/*!40000 ALTER TABLE `SubTask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TaskType`
--

DROP TABLE IF EXISTS `TaskType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TaskType` (
  `typeID` int NOT NULL AUTO_INCREMENT,
  `type_name` char(1) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`typeID`),
  UNIQUE KEY `typeID_UNIQUE` (`typeID`),
  UNIQUE KEY `type_name_UNIQUE` (`type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TaskType`
--

LOCK TABLES `TaskType` WRITE;
/*!40000 ALTER TABLE `TaskType` DISABLE KEYS */;
INSERT INTO `TaskType` VALUES (1,'A'),(2,'B'),(3,'C');
/*!40000 ALTER TABLE `TaskType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorize`
--

DROP TABLE IF EXISTS `authorize`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorize` (
  `authorizeID` int NOT NULL AUTO_INCREMENT,
  `projectID` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `adminID` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `authorizeDate` datetime NOT NULL,
  `operator` varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`authorizeID`),
  UNIQUE KEY `AuthoeizeID_UNIQUE` (`authorizeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorize`
--

LOCK TABLES `authorize` WRITE;
/*!40000 ALTER TABLE `authorize` DISABLE KEYS */;
/*!40000 ALTER TABLE `authorize` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operation`
--

DROP TABLE IF EXISTS `operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operation` (
  `operationID` int NOT NULL AUTO_INCREMENT,
  `operatorID` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `time` datetime NOT NULL,
  `operationName` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `projectID` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`operationID`),
  UNIQUE KEY `operationID_UNIQUE` (`operationID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation`
--

LOCK TABLES `operation` WRITE;
/*!40000 ALTER TABLE `operation` DISABLE KEYS */;
INSERT INTO `operation` VALUES (1,'551808','2020-10-10 22:20:20','启动项目','adb-4205-ab2e-8'),(2,'551808','2020-10-27 14:35:03','创建','038-45f4-9b98-d'),(3,'551808','2020-10-27 14:37:01','创建','29d-48a9-af01-6'),(4,'551808','2020-10-27 14:39:38','创建','441-4287-a686-3'),(5,'551808','2020-10-27 15:16:02','创建','3b1-4143-a156-f'),(6,'551808','2020-10-27 15:19:15','创建','66d-4b0a-9278-5'),(11,'551808','2020-10-27 19:36:47','中止','29d-48a9-af01-6'),(12,'551808','2020-10-27 19:38:47','中止','2d2-41a6-ad74-e'),(13,'551808','2020-10-27 19:40:39','中止','3b1-4143-a156-f'),(14,'551808','2020-10-27 20:56:58','重启','29d-48a9-af01-6');
/*!40000 ALTER TABLE `operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userID` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `userName` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `userPwd` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `roleID` int NOT NULL,
  `token` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `isDelete` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userID_UNIQUE` (`userID`),
  UNIQUE KEY `userName_UNIQUE` (`userName`),
  KEY `fk_user_1_idx` (`roleID`),
  CONSTRAINT `fk_user_1` FOREIGN KEY (`roleID`) REFERENCES `RoleD` (`roleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('220101','A类打工人','11',1,'111111',0),('220102','B类打工人','1',2,'222222',0),('220103','C类打工人','123456',3,'333333',0),('25c-4447-8','小红','12345678',6,'f7d4c8c3-4c2a-46bf-a87e-601e4f193333',1),('551801','kaito','kaito',5,'123456',0),('551808','xwk','xwk',4,'444444',0);
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

-- Dump completed on 2020-10-27 23:40:56
