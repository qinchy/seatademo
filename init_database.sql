CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `test`;

/*Table structure for table `account_tbl` */

DROP TABLE IF EXISTS `account_tbl`;

CREATE TABLE `account_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `money` decimal(11,0) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `account_tbl` */

insert  into `account_tbl`(`id`,`user_id`,`money`) values 
(1,'U100001',9999979);

/*Table structure for table `order_tbl` */

DROP TABLE IF EXISTS `order_tbl`;

CREATE TABLE `order_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `commodity_code` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  `money` decimal(11,0) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

/*Data for the table `order_tbl` */

insert  into `order_tbl`(`id`,`user_id`,`commodity_code`,`count`,`money`) values 
(60,'1','product-1',1,5),
(61,'1','product-1',1,5),
(78,'1','product-1',1,5),
(80,'1','product-1',1,5),
(82,'1','product-1',1,5),
(83,'1','product-1',1,5),
(86,'1','product-1',1,5),
(87,'1','product-1',1,5),
(88,'1','product-1',1,5),
(89,'1','product-1',1,5),
(91,'1','product-2',1,5),
(94,'1','product-2',1,5),
(95,'1','product-2',1,5),
(98,'1','product-2',1,5),
(99,'1','product-2',1,5),
(100,'1','product-2',1,5),
(103,'1','product-2',1,5),
(105,'1','product-2',1,5);

/*Table structure for table `storage_tbl` */

DROP TABLE IF EXISTS `storage_tbl`;

CREATE TABLE `storage_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_code` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `commodity_code` (`commodity_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `storage_tbl` */

insert  into `storage_tbl`(`id`,`commodity_code`,`count`) values 
(4,'C00321',99);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
