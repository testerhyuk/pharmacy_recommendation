DROP TABLE IF EXISTS `direction`;

CREATE TABLE `direction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `distance` double NOT NULL,
  `input_address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `input_latitude` double NOT NULL,
  `input_longitude` double NOT NULL,
  `target_address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `target_latitude` double NOT NULL,
  `target_longitude` double NOT NULL,
  `target_pharmacy_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `direction` WRITE;

UNLOCK TABLES;