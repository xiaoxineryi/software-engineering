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
  UNIQUE KEY `entrustID_UNIQUE` (`entrustID`),
  UNIQUE KEY `subTask_UNIQUE` (`subTask`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Entrust`
--

LOCK TABLES `Entrust` WRITE;
/*!40000 ALTER TABLE `Entrust` DISABLE KEYS */;
INSERT INTO `Entrust` VALUES (10,'已完成','2020-10-29 01:01:46','2020-10-29 04:14:00','cc7-4677-9c86-0_1','1a8-445b-9');
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
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Notice`
--

LOCK TABLES `Notice` WRITE;
/*!40000 ALTER TABLE `Notice` DISABLE KEYS */;
INSERT INTO `Notice` VALUES (25,'您在项目编号为27d-4378-88a6-a，项目名为测试工程的项目中有等待执行的任务      2020-10-28 07:07:36','551808','2020-10-28 19:07:37',0),(26,'您在项目编号为27d-4378-88a6-a，项目名为测试工程的项目被中止。您任务编号为27d-4378-88a6-a_1的任务被中止。       2020-10-28 07:43:53','220101','2020-10-28 19:43:53',1),(27,'您在项目编号为27d-4378-88a6-a，项目名为测试工程的项目被中止。您任务编号为27d-4378-88a6-a_2的任务被中止。       2020-10-28 07:43:53','1a8-445b-9','2020-10-28 19:43:53',0),(28,'您在项目编号为27d-4378-88a6-a，项目名为测试工程的项目被中止。您任务编号为27d-4378-88a6-a_3的任务被中止。       2020-10-28 07:43:53','220103','2020-10-28 19:43:53',0),(29,'您所参与的项目编号为27d-4378-88a6-a，项目名称为测试工程的任务被重新启动。       2020-10-28 07:45:15','220101','2020-10-28 19:45:15',1),(30,'您所参与的项目编号为27d-4378-88a6-a，项目名称为测试工程的任务被重新启动。       2020-10-28 07:45:15','1a8-445b-9','2020-10-28 19:45:15',0),(31,'您所参与的项目编号为27d-4378-88a6-a，项目名称为测试工程的任务被重新启动。       2020-10-28 07:45:15','220103','2020-10-28 19:45:15',0),(32,'您在项目编号为27d-4378-88a6-a，项目名为测试工程的项目中有等待执行的任务      2020-10-28 07:56:40','1a8-445b-9','2020-10-28 19:56:41',0),(33,'您在项目编号为27d-4378-88a6-a，项目名为测试工程的项目中有等待执行的任务      2020-10-28 08:00:29','220103','2020-10-28 20:00:30',0),(34,'您在项目编号为cc7-4677-9c86-0，项目名为希望工程的项目中有等待执行的任务      2020-10-28 08:05:28','551808','2020-10-28 20:05:28',0),(35,'您被委托执行项目编号为cc7-4677-9c86-0，项目名称为希望工程的项目中的任务cc7-4677-9c86-0_1       2020-10-28 08:12:27','220102','2020-10-28 20:12:27',0),(36,'您被委托的项目编号为cc7-4677-9c86-0，项目名称为希望工程的项目中的任务cc7-4677-9c86-0_1被收回       2020-10-28 08:13:48','220102','2020-10-28 20:13:49',0),(37,'您在项目编号为cc7-4677-9c86-0，项目名为希望工程的项目被中止。您任务编号为cc7-4677-9c86-0_1的任务被中止。       2020-10-28 09:06:04','fc2-4203-9','2020-10-28 21:06:04',0),(38,'您在项目编号为cc7-4677-9c86-0，项目名为希望工程的项目被中止。您任务编号为cc7-4677-9c86-0_2的任务被中止。       2020-10-28 09:06:04','220103','2020-10-28 21:06:04',0),(39,'项目编号为cc7-4677-9c86-0，项目名为希望工程的任务被重新编辑，请注意查看。      2020-10-28 09:14:46','220102','2020-10-28 21:14:46',0),(40,'项目编号为cc7-4677-9c86-0，项目名为希望工程的任务被重新编辑，请注意查看。      2020-10-28 09:14:46','220103','2020-10-28 21:14:46',0),(41,'项目编号为cc7-4677-9c86-0，项目名为希望工程的任务被重新编辑，请注意查看。      2020-10-28 09:14:46','fc2-4203-9','2020-10-28 21:14:46',0),(42,'您所参与的项目编号为cc7-4677-9c86-0，项目名称为希望工程的任务被重新启动。       2020-10-28 09:15:10','220102','2020-10-28 21:15:10',0),(43,'您所参与的项目编号为cc7-4677-9c86-0，项目名称为希望工程的任务被重新启动。       2020-10-28 09:15:10','220103','2020-10-28 21:15:10',0),(44,'您所参与的项目编号为cc7-4677-9c86-0，项目名称为希望工程的任务被重新启动。       2020-10-28 09:15:10','fc2-4203-9','2020-10-28 21:15:10',0),(45,'您在项目编号为cc7-4677-9c86-0，项目名为希望工程的项目被中止。您任务编号为cc7-4677-9c86-0_0的任务被中止。       2020-10-28 09:15:54','220102','2020-10-28 21:15:54',0),(46,'您在项目编号为cc7-4677-9c86-0，项目名为希望工程的项目被中止。您任务编号为cc7-4677-9c86-0_1的任务被中止。       2020-10-28 09:15:54','220103','2020-10-28 21:15:54',0),(47,'您在项目编号为cc7-4677-9c86-0，项目名为希望工程的项目被中止。您任务编号为cc7-4677-9c86-0_2的任务被中止。       2020-10-28 09:15:54','fc2-4203-9','2020-10-28 21:15:54',0),(48,'项目编号为cc7-4677-9c86-0，项目名为希望工程的任务被重新编辑，请注意查看。      2020-10-28 09:17:09','fc2-4203-9','2020-10-28 21:17:09',0),(49,'项目编号为cc7-4677-9c86-0，项目名为希望工程的任务被重新编辑，请注意查看。      2020-10-28 09:17:09','220103','2020-10-28 21:17:09',0),(50,'项目编号为cc7-4677-9c86-0，项目名为希望工程的任务被重新编辑，请注意查看。      2020-10-28 09:17:09','220102','2020-10-28 21:17:09',0),(51,'您所参与的项目编号为cc7-4677-9c86-0，项目名称为希望工程的任务被重新启动。       2020-10-28 09:17:26','fc2-4203-9','2020-10-28 21:17:26',0),(52,'您所参与的项目编号为cc7-4677-9c86-0，项目名称为希望工程的任务被重新启动。       2020-10-28 09:17:26','220103','2020-10-28 21:17:26',0),(53,'您所参与的项目编号为cc7-4677-9c86-0，项目名称为希望工程的任务被重新启动。       2020-10-28 09:17:26','220102','2020-10-28 21:17:26',0),(54,'您在项目编号为cc7-4677-9c86-0，项目名为希望工程的项目中有等待执行的任务      2020-10-28 09:20:16','220103','2020-10-28 21:20:16',0),(55,'您在项目编号为cc7-4677-9c86-0，项目名为希望工程的项目被中止。您任务编号为cc7-4677-9c86-0_0的任务被中止。       2020-10-28 09:20:55','fc2-4203-9','2020-10-28 21:20:55',0),(56,'您在项目编号为cc7-4677-9c86-0，项目名为希望工程的项目被中止。您任务编号为cc7-4677-9c86-0_1的任务被中止。       2020-10-28 09:20:55','220103','2020-10-28 21:20:55',0),(57,'您在项目编号为cc7-4677-9c86-0，项目名为希望工程的项目被中止。您任务编号为cc7-4677-9c86-0_2的任务被中止。       2020-10-28 09:20:55','220102','2020-10-28 21:20:55',0),(58,'项目编号为cc7-4677-9c86-0，项目名为希望工程的任务被重新编辑，请注意查看。      2020-10-28 09:20:57','fc2-4203-9','2020-10-28 21:20:57',0),(59,'项目编号为cc7-4677-9c86-0，项目名为希望工程的任务被重新编辑，请注意查看。      2020-10-28 09:20:57','220103','2020-10-28 21:20:57',0),(60,'项目编号为cc7-4677-9c86-0，项目名为希望工程的任务被重新编辑，请注意查看。      2020-10-28 09:20:57','220101','2020-10-28 21:20:57',0),(61,'您所参与的项目编号为cc7-4677-9c86-0，项目名称为希望工程的任务被重新启动。       2020-10-28 09:21:14','fc2-4203-9','2020-10-28 21:21:14',0),(62,'您所参与的项目编号为cc7-4677-9c86-0，项目名称为希望工程的任务被重新启动。       2020-10-28 09:21:14','220103','2020-10-28 21:21:14',0),(63,'您所参与的项目编号为cc7-4677-9c86-0，项目名称为希望工程的任务被重新启动。       2020-10-28 09:21:14','220101','2020-10-28 21:21:14',0),(64,'您被委托执行项目编号为cc7-4677-9c86-0，项目名称为希望工程的项目中的任务cc7-4677-9c86-0_1       2020-10-29 01:00:11','220102','2020-10-29 01:00:11',0),(65,'您被委托的项目编号为cc7-4677-9c86-0，项目名称为希望工程的项目中的任务cc7-4677-9c86-0_1被收回       2020-10-29 01:00:44','220102','2020-10-29 01:00:44',0),(66,'您被委托执行项目编号为cc7-4677-9c86-0，项目名称为希望工程的项目中的任务cc7-4677-9c86-0_1       2020-10-29 01:01:46','1a8-445b-9','2020-10-29 01:01:46',0),(67,'您委托给1a8-445b-9的项目编号为cc7-4677-9c86-0，项目名为希望工程的任务已经完成。      2020-10-29 01:05:10','220103','2020-10-29 01:05:10',0),(68,'您在项目编号为cc7-4677-9c86-0，项目名为希望工程的项目中有等待执行的任务      2020-10-29 01:05:10','220101','2020-10-29 01:05:10',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Permission`
--

LOCK TABLES `Permission` WRITE;
/*!40000 ALTER TABLE `Permission` DISABLE KEYS */;
INSERT INTO `Permission` VALUES (1,'read','project'),(2,'start','project'),(3,'end','project'),(4,'restart','project'),(5,'create','project'),(6,'read','user'),(7,'add','user'),(8,'edit','user'),(9,'changePosition','user'),(10,'changePwd','user'),(11,'add','role'),(12,'edit','role'),(13,'add','permission'),(14,'edit','permission'),(15,'save','project'),(16,'edit','project'),(17,'delete','BeDelete'),(18,'downloadFile','project');
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
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PermissionRole`
--

LOCK TABLES `PermissionRole` WRITE;
/*!40000 ALTER TABLE `PermissionRole` DISABLE KEYS */;
INSERT INTO `PermissionRole` VALUES (1,1,1),(1,6,2),(1,10,3),(2,1,4),(2,6,5),(2,10,6),(3,1,7),(3,6,8),(3,10,9),(4,1,10),(4,2,11),(4,3,12),(4,4,13),(4,5,14),(4,6,15),(4,10,16),(5,6,17),(5,7,18),(5,8,19),(5,9,20),(5,10,21),(5,1,22),(5,11,26),(5,12,27),(5,13,32),(5,14,33),(4,16,34),(6,17,35),(7,1,39),(7,6,40),(7,10,41);
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
INSERT INTO `Project` VALUES ('27d-4378-88a6-a','测试工程','551808','已完成','2020-10-28 19:07:37',NULL,'测试工程'),('cc7-4677-9c86-0','希望工程','551808','正在进行','2020-10-28 20:05:28',NULL,'希望工程');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RoleD`
--

LOCK TABLES `RoleD` WRITE;
/*!40000 ALTER TABLE `RoleD` DISABLE KEYS */;
INSERT INTO `RoleD` VALUES (1,'A类员工'),(2,'B类员工'),(3,'C类员工'),(4,'项目管理员'),(5,'人事管理员'),(6,'被删除'),(7,'D类究极旁观者');
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
INSERT INTO `SubTask` VALUES ('27d-4378-88a6-a_1',1,'已完成','220101','27d-4378-88a6-a',0),('27d-4378-88a6-a_2',3,'已完成','1a8-445b-9','27d-4378-88a6-a',1),('27d-4378-88a6-a_3',3,'已完成','220103','27d-4378-88a6-a',2),('cc7-4677-9c86-0_0',2,'已完成','fc2-4203-9','cc7-4677-9c86-0',0),('cc7-4677-9c86-0_1',3,'已完成','220103','cc7-4677-9c86-0',1),('cc7-4677-9c86-0_2',1,'进行中','220101','cc7-4677-9c86-0',2);
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
  `operationName` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `projectID` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`operationID`),
  UNIQUE KEY `operationID_UNIQUE` (`operationID`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation`
--

LOCK TABLES `operation` WRITE;
/*!40000 ALTER TABLE `operation` DISABLE KEYS */;
INSERT INTO `operation` VALUES (28,'551808','2020-10-28 19:07:37','创建','27d-4378-88a6-a'),(29,'551808','2020-10-28 19:43:53','中止','27d-4378-88a6-a'),(30,'551808','2020-10-28 19:45:15','重启','27d-4378-88a6-a'),(31,'220101','2020-10-28 19:53:41','执行并保存A类任务','27d-4378-88a6-a'),(32,'220101','2020-10-28 19:55:37','撤销执行A类任务','27d-4378-88a6-a'),(33,'220101','2020-10-28 19:56:33','执行并保存A类任务','27d-4378-88a6-a'),(34,'220101','2020-10-28 19:56:41','提交A类任务','27d-4378-88a6-a'),(35,'1a8-445b-9','2020-10-28 20:00:17','执行并保存C类任务','27d-4378-88a6-a'),(36,'1a8-445b-9','2020-10-28 20:00:30','提交C类任务','27d-4378-88a6-a'),(37,'220103','2020-10-28 20:01:24','执行并保存C类任务','27d-4378-88a6-a'),(38,'220103','2020-10-28 20:01:27','提交C类任务','27d-4378-88a6-a'),(39,'551808','2020-10-28 20:05:28','创建项目','cc7-4677-9c86-0'),(40,'fc2-4203-9','2020-10-28 20:12:27','将任务B委托给220102','cc7-4677-9c86-0'),(41,'fc2-4203-9','2020-10-28 20:13:49','将子任务B委托收回','cc7-4677-9c86-0'),(42,'551808','2020-10-28 21:06:04','中止','cc7-4677-9c86-0'),(43,'551808','2020-10-28 21:15:10','重启','cc7-4677-9c86-0'),(44,'551808','2020-10-28 21:15:54','中止','cc7-4677-9c86-0'),(45,'551808','2020-10-28 21:17:26','重启','cc7-4677-9c86-0'),(46,'fc2-4203-9','2020-10-28 21:19:47','执行并保存B类任务','cc7-4677-9c86-0'),(47,'fc2-4203-9','2020-10-28 21:20:16','提交B类任务','cc7-4677-9c86-0'),(48,'551808','2020-10-28 21:20:55','中止','cc7-4677-9c86-0'),(49,'551808','2020-10-28 21:21:14','重启','cc7-4677-9c86-0'),(50,'220103','2020-10-29 01:00:11','将任务C委托给220102','cc7-4677-9c86-0'),(51,'220103','2020-10-29 01:00:44','将子任务C委托收回','cc7-4677-9c86-0'),(52,'220103','2020-10-29 01:01:46','将任务C委托给1a8-445b-9','cc7-4677-9c86-0'),(53,'1a8-445b-9','2020-10-29 01:02:27','受到委托，执行并保存C类任务','cc7-4677-9c86-0'),(54,'1a8-445b-9','2020-10-29 01:05:10','被委托，提交C类任务','cc7-4677-9c86-0');
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
INSERT INTO `user` VALUES ('1a8-445b-9','C类添加员工','123456',3,'221100',0),('220101','A类打工人','11',1,'111111',0),('220102','B类打工人','1',2,'222222',0),('220103','C类打工人','123456',3,'333333',0),('47e-498c-9','A类添加员工','111111',1,'e6bd93c4-840d-488c-8572-2a234d9a0ac5',0),('551801','kaito','kaito',5,'123456',0),('551808','xwk','xwk',4,'444444',0),('d9c-4b9a-9','测试员','123456',6,NULL,1),('fc2-4203-9','B类添加员工','123456',2,'222211',0);
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

-- Dump completed on 2020-10-29  1:06:32
