CREATE DATABASE  IF NOT EXISTS `competente` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `competente`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: competente
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `Adm_cod` int(11) NOT NULL AUTO_INCREMENT,
  `Adm_idMestre` int(11) DEFAULT NULL,
  `Usuario_Usu_rg` int(11) NOT NULL,
  PRIMARY KEY (`Adm_cod`,`Usuario_Usu_rg`),
  KEY `fk_Administrador_Usuario1_idx` (`Usuario_Usu_rg`),
  CONSTRAINT `fk_Administrador_Usuario1` FOREIGN KEY (`Usuario_Usu_rg`) REFERENCES `usuario` (`Usu_rg`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (1,1,379332644);
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `beneficiado`
--

DROP TABLE IF EXISTS `beneficiado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `beneficiado` (
  `Ben_cod` int(11) NOT NULL AUTO_INCREMENT,
  `Ben_data` datetime NOT NULL,
  `Ben_endereco` varchar(45) NOT NULL,
  `Usuario_Usu_rg` int(11) NOT NULL,
  PRIMARY KEY (`Ben_cod`,`Usuario_Usu_rg`),
  KEY `fk_Beneficiado_Usuario1_idx` (`Usuario_Usu_rg`),
  CONSTRAINT `fk_Beneficiado_Usuario1` FOREIGN KEY (`Usuario_Usu_rg`) REFERENCES `usuario` (`Usu_rg`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=216 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beneficiado`
--

LOCK TABLES `beneficiado` WRITE;
/*!40000 ALTER TABLE `beneficiado` DISABLE KEYS */;
/*!40000 ALTER TABLE `beneficiado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doacao`
--

DROP TABLE IF EXISTS `doacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doacao` (
  `Doa_cod` varchar(45) NOT NULL,
  `Doa_data` datetime NOT NULL,
  `Estoque_Est_cod` int(11) NOT NULL,
  `Voluntario_Vol_cod` int(11) NOT NULL,
  `Voluntario_Usuario_Usu_rg` int(11) NOT NULL,
  PRIMARY KEY (`Doa_cod`,`Voluntario_Vol_cod`,`Voluntario_Usuario_Usu_rg`),
  KEY `fk_Doacao_Estoque1_idx` (`Estoque_Est_cod`),
  KEY `fk_Doacao_Voluntario1` (`Voluntario_Vol_cod`,`Voluntario_Usuario_Usu_rg`),
  CONSTRAINT `fk_Doacao_Estoque1` FOREIGN KEY (`Estoque_Est_cod`) REFERENCES `estoque` (`Est_cod`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Doacao_Voluntario1` FOREIGN KEY (`Voluntario_Vol_cod`, `Voluntario_Usuario_Usu_rg`) REFERENCES `voluntario` (`Vol_cod`, `Usuario_Usu_rg`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doacao`
--

LOCK TABLES `doacao` WRITE;
/*!40000 ALTER TABLE `doacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `doacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estoque` (
  `Est_cod` int(11) NOT NULL AUTO_INCREMENT,
  `Est_dataE` datetime NOT NULL,
  `Voluntario_Vol_cod` int(11) NOT NULL,
  `Voluntario_Usuario_Usu_rg` int(11) NOT NULL,
  `Est_nome` varchar(45) NOT NULL,
  `Est_quantidade` int(11) NOT NULL,
  PRIMARY KEY (`Est_cod`),
  KEY `fk_Estoque_Voluntario1_idx` (`Voluntario_Vol_cod`,`Voluntario_Usuario_Usu_rg`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque`
--

LOCK TABLES `estoque` WRITE;
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evento` (
  `Eve_id` int(11) NOT NULL,
  `Eve_orador` varchar(45) NOT NULL,
  `Eve_data` datetime NOT NULL,
  `Eve_local` varchar(45) NOT NULL,
  `Eve_vagas` int(11) NOT NULL,
  `Eve_tipo` int(11) NOT NULL,
  PRIMARY KEY (`Eve_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lembrete`
--

DROP TABLE IF EXISTS `lembrete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lembrete` (
  `Lem_cod` int(11) NOT NULL,
  `Lem_recado` varchar(100) NOT NULL,
  `Lem_data` datetime NOT NULL,
  PRIMARY KEY (`Lem_cod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lembrete`
--

LOCK TABLES `lembrete` WRITE;
/*!40000 ALTER TABLE `lembrete` DISABLE KEYS */;
/*!40000 ALTER TABLE `lembrete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lembrete_has_beneficiado`
--

DROP TABLE IF EXISTS `lembrete_has_beneficiado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lembrete_has_beneficiado` (
  `Lembrete_Lem_cod` int(11) NOT NULL,
  `Beneficiado_Ben_cod` int(11) NOT NULL,
  `Beneficiado_Usuario_Usu_rg` int(11) NOT NULL,
  PRIMARY KEY (`Lembrete_Lem_cod`,`Beneficiado_Ben_cod`,`Beneficiado_Usuario_Usu_rg`),
  KEY `fk_Lembrete_has_Beneficiado_Beneficiado1_idx` (`Beneficiado_Ben_cod`,`Beneficiado_Usuario_Usu_rg`),
  KEY `fk_Lembrete_has_Beneficiado_Lembrete1_idx` (`Lembrete_Lem_cod`),
  CONSTRAINT `fk_Lembrete_has_Beneficiado_Beneficiado1` FOREIGN KEY (`Beneficiado_Ben_cod`, `Beneficiado_Usuario_Usu_rg`) REFERENCES `beneficiado` (`Ben_cod`, `Usuario_Usu_rg`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Lembrete_has_Beneficiado_Lembrete1` FOREIGN KEY (`Lembrete_Lem_cod`) REFERENCES `lembrete` (`Lem_cod`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lembrete_has_beneficiado`
--

LOCK TABLES `lembrete_has_beneficiado` WRITE;
/*!40000 ALTER TABLE `lembrete_has_beneficiado` DISABLE KEYS */;
/*!40000 ALTER TABLE `lembrete_has_beneficiado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `Usu_rg` int(11) NOT NULL,
  `Usu_nome` varchar(45) NOT NULL,
  `Usu_cargo` int(11) NOT NULL,
  `Usu_email` varchar(45) NOT NULL,
  `Usu_senha` varchar(45) NOT NULL,
  PRIMARY KEY (`Usu_rg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (379332644,'pacheco',3,'pachec@email.com','123');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voluntario`
--

DROP TABLE IF EXISTS `voluntario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voluntario` (
  `Vol_cod` int(11) NOT NULL AUTO_INCREMENT,
  `Vol_data` datetime NOT NULL,
  `Usuario_Usu_rg` int(11) NOT NULL,
  PRIMARY KEY (`Vol_cod`,`Usuario_Usu_rg`),
  KEY `fk_Voluntario_Usuario1_idx` (`Usuario_Usu_rg`),
  CONSTRAINT `fk_Voluntario_Usuario1` FOREIGN KEY (`Usuario_Usu_rg`) REFERENCES `usuario` (`Usu_rg`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=877 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voluntario`
--

LOCK TABLES `voluntario` WRITE;
/*!40000 ALTER TABLE `voluntario` DISABLE KEYS */;
/*!40000 ALTER TABLE `voluntario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-22 18:12:46
