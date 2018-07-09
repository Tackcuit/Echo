/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.54 : Database - echo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`echo` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `echo`;

/*Table structure for table `t_category` */

DROP TABLE IF EXISTS `t_category`;

CREATE TABLE `t_category` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(255) DEFAULT NULL,
  `c_parent_id` int(11) DEFAULT NULL,
  `c_level` int(255) DEFAULT NULL,
  PRIMARY KEY (`c_id`),
  KEY `FKk8ytj2h42tiqbhfvwyks01cw2` (`c_parent_id`),
  CONSTRAINT `FKk8ytj2h42tiqbhfvwyks01cw2` FOREIGN KEY (`c_parent_id`) REFERENCES `t_category` (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_category` */

insert  into `t_category`(`c_id`,`c_name`,`c_parent_id`,`c_level`) values (1,'饮品',NULL,1),(2,'美食',NULL,1),(3,'咖啡产品',NULL,1),(4,'咖啡豆',3,2),(5,'免煮咖啡',3,2),(8,'手工调制浓缩咖啡',1,2),(9,'星冰乐',1,2),(10,'茶瓦纳',1,2),(11,'经典巧克力饮品',1,2),(12,'咖啡融合冰淇淋',1,2),(13,'星冰粽',2,2),(14,'烘焙',2,2),(15,'蛋糕甜品',2,2),(16,'三明治帕尼尼卷',2,2),(17,'酸奶',2,2),(18,'其他',2,2);

/*Table structure for table `t_odetail` */

DROP TABLE IF EXISTS `t_odetail`;

CREATE TABLE `t_odetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` varchar(50) NOT NULL,
  `gid` int(11) DEFAULT NULL,
  `money` double(10,2) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKde0vtyi33se9m7exccg9aj6a7` (`oid`),
  CONSTRAINT `FKde0vtyi33se9m7exccg9aj6a7` FOREIGN KEY (`oid`) REFERENCES `t_order` (`oid`),
  CONSTRAINT `t_odetail_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `t_order` (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_odetail` */

insert  into `t_odetail`(`id`,`oid`,`gid`,`money`,`num`) values (2,'20180627093531171',1,16.00,5),(3,'20180628205525372',1,16.00,5),(4,'20180703225928325',40,30.00,5),(5,'20180704084810339',40,30.00,5),(6,'20180704084952015',40,30.00,5),(7,'20180704085642725',40,30.00,5),(8,'20180704085950504',34,22.00,5),(9,'20180704090205464',34,22.00,5),(10,'20180704090717416',34,22.00,5),(11,'20180704091535280',34,22.00,5),(12,'20180704091711858',34,22.00,5),(13,'20180704091833963',34,22.00,5),(14,'20180704092001212',34,22.00,5),(15,'20180704092319665',34,22.00,5),(16,'20180704092638141',34,22.00,5),(17,'20180704092851257',34,22.00,5),(18,'20180704093004879',34,22.00,5),(19,'20180704094121390',34,22.00,5),(20,'20180704094336213',34,22.00,5),(21,'20180704094428225',34,22.00,5),(22,'20180704111723338',34,22.00,5),(23,'20180704111811859',40,30.00,5),(24,'20180704112316037',40,30.00,5),(25,'20180704144327121',40,30.00,5),(26,'20180704200752357',40,30.00,5),(27,'20180704200838148',40,30.00,5),(28,'20180704200958268',40,30.00,5),(29,'20180705142409164',40,30.00,5),(30,'20180705143918688',40,30.00,5),(31,'20180705144422032',40,30.00,5),(32,'20180705150303671',40,30.00,5),(33,'20180705150632336',40,30.00,5),(34,'20180705152431135',33,24.00,5),(35,'20180705152431135',40,30.00,5);

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` varchar(50) NOT NULL,
  `tid` int(11) DEFAULT NULL,
  `money` double(10,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `oid` (`oid`),
  UNIQUE KEY `UK_nn1nel97jwsy966lclmfdm6c8` (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_order` */

insert  into `t_order`(`id`,`oid`,`tid`,`money`,`status`,`time`) values (1,'20180626235335350',1,0.00,0,'2018-06-26 23:53:36'),(2,'20180627000105225',1,0.00,0,'2018-06-27 00:01:05'),(3,'20180627000349724',1,0.00,0,'2018-06-27 00:03:50'),(4,'20180627084725643',1,0.00,0,'2018-06-27 08:47:26'),(5,'20180627085033031',1,0.00,0,'2018-06-27 08:50:33'),(6,'20180627085135814',1,0.00,0,'2018-06-27 08:51:36'),(7,'20180627085335672',1,0.00,0,'2018-06-27 08:53:36'),(8,'20180627085435386',1,0.00,0,'2018-06-27 08:54:35'),(9,'20180627085508005',1,0.00,0,'2018-06-27 08:55:08'),(10,'20180627085644162',1,0.00,0,'2018-06-27 08:58:44'),(11,'20180627091319526',1,0.00,0,'2018-06-27 09:13:20'),(12,'20180627091523792',1,0.00,0,'2018-06-27 09:15:58'),(14,'20180627093531171',1,80.00,0,'2018-06-27 09:35:31'),(15,'20180628205525372',1,80.00,0,'2018-06-28 20:57:48'),(16,'20180703225928325',1,290.00,0,'2018-07-03 22:59:29'),(17,'20180704084810339',1,290.00,0,'2018-07-04 08:48:11'),(18,'20180704084952015',1,290.00,0,'2018-07-04 08:49:53'),(19,'20180704085642725',1,290.00,0,'2018-07-04 08:56:44'),(20,'20180704085950504',1,250.00,0,'2018-07-04 08:59:50'),(21,'20180704090205464',1,250.00,0,'2018-07-04 09:02:06'),(22,'20180704090717416',1,250.00,0,'2018-07-04 09:07:17'),(23,'20180704091535280',1,250.00,0,'2018-07-04 09:15:36'),(24,'20180704091711858',1,250.00,0,'2018-07-04 09:17:12'),(25,'20180704091833963',1,250.00,0,'2018-07-04 09:18:34'),(26,'20180704092001212',1,250.00,0,'2018-07-04 09:20:01'),(27,'20180704092319665',1,250.00,0,'2018-07-04 09:23:20'),(28,'20180704092638141',1,250.00,0,'2018-07-04 09:26:38'),(29,'20180704092851257',1,250.00,0,'2018-07-04 09:29:29'),(30,'20180704093004879',1,250.00,0,'2018-07-04 09:35:21'),(31,'20180704094121390',1,250.00,0,'2018-07-04 09:41:22'),(32,'20180704094336213',1,250.00,0,'2018-07-04 09:43:36'),(33,'20180704094428225',1,250.00,0,'2018-07-04 09:44:29'),(34,'20180704111723338',1,250.00,0,'2018-07-04 11:17:24'),(35,'20180704111811859',1,290.00,0,'2018-07-04 11:18:11'),(36,'20180704112316037',1,290.00,0,'2018-07-04 11:23:16'),(37,'20180704144327121',1,290.00,0,'2018-07-04 14:43:27'),(38,'20180704200752357',1,290.00,0,'2018-07-04 20:07:53'),(39,'20180704200838148',1,270.00,0,'2018-07-04 20:08:38'),(40,'20180704200958268',1,270.00,0,'2018-07-04 20:09:58'),(41,'20180705142409164',1,270.00,0,'2018-07-05 14:24:10'),(42,'20180705143918688',1,270.00,0,'2018-07-05 14:39:19'),(43,'20180705144422032',1,270.00,0,'2018-07-05 14:48:05'),(44,'20180705150303671',1,270.00,0,'2018-07-05 15:03:05'),(45,'20180705150632336',1,270.00,0,'2018-07-05 15:10:00'),(46,'20180705152431135',1,270.00,0,'2018-07-05 15:24:32');

/*Table structure for table `t_product` */

DROP TABLE IF EXISTS `t_product`;

CREATE TABLE `t_product` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_name` varchar(255) DEFAULT NULL,
  `p_price` decimal(10,2) DEFAULT NULL,
  `p_membershipprice` decimal(10,2) DEFAULT NULL,
  `p_size` int(10) DEFAULT '0',
  `p_salenum` int(11) DEFAULT '0',
  `p_isnew` varchar(30) DEFAULT 'n',
  `p_img` varchar(255) DEFAULT NULL,
  `p_intro` varchar(255) DEFAULT NULL,
  `c_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_product` */

insert  into `t_product`(`p_id`,`p_name`,`p_price`,`p_membershipprice`,`p_size`,`p_salenum`,`p_isnew`,`p_img`,`p_intro`,`c_id`) values (1,'美式咖啡(冷)','18.00','16.00',1,0,'y',NULL,NULL,8),(2,'美式咖啡(冷)','20.00','18.00',2,0,'n',NULL,NULL,8),(3,'美式咖啡(冷)','22.00','20.00',3,0,'n',NULL,NULL,8),(4,'拿铁(冷)','20.00','18.00',1,0,'y',NULL,NULL,8),(5,'拿铁(冷)','22.00','20.00',2,8,'n',NULL,NULL,8),(6,'拿铁(冷)','24.00','22.00',3,0,'n',NULL,NULL,8),(7,'美式咖啡(热)','18.00','16.00',1,0,'y',NULL,NULL,8),(8,'美式咖啡(热)','20.00','18.00',2,0,'n',NULL,NULL,8),(9,'美式咖啡(热)','22.00','20.00',3,0,'n',NULL,NULL,8),(10,'拿铁(热)','20.00','18.00',1,0,'n',NULL,NULL,8),(11,'拿铁(热)','20.00','20.00',2,0,'n',NULL,NULL,8),(12,'拿铁(热)','20.00','18.00',3,0,'n',NULL,NULL,8),(13,'摩卡(冷)','18.00','16.00',1,7,'y',NULL,NULL,8),(14,'摩卡(冷)','18.00','16.00',2,0,'y',NULL,NULL,8),(15,'摩卡(冷)','18.00','16.00',3,0,'y',NULL,NULL,8),(16,'摩卡(热)','18.00','16.00',1,0,'n',NULL,NULL,8),(17,'摩卡(热)','18.00','16.00',2,0,'n',NULL,NULL,8),(18,'摩卡(热)','18.00','16.00',3,0,'n',NULL,NULL,8),(19,'卡布奇诺(冷)','22.00','20.00',1,6,'n',NULL,NULL,8),(20,'卡布奇诺(冷)','22.00','20.00',2,0,'n',NULL,NULL,8),(21,'卡布奇诺(冷)','22.00','20.00',3,0,'n',NULL,NULL,8),(22,'卡布奇诺(热)','22.00','20.00',1,5,'n',NULL,NULL,8),(23,'卡布奇诺(热)','22.00','20.00',2,0,'n',NULL,NULL,8),(24,'卡布奇诺(热)','22.00','22.00',3,0,'n',NULL,NULL,8),(25,'抹茶星冰乐','24.00','22.00',1,0,'n',NULL,NULL,9),(26,'抹茶星冰乐','24.00','22.00',2,0,'n',NULL,NULL,9),(27,'抹茶星冰乐','24.00','22.00',3,0,'n',NULL,NULL,9),(28,'冷萃咖啡风味','30.00','26.00',0,0,'y',NULL,NULL,13),(29,'茶瓦纳桃桃风味','30.00','26.00',0,12,'n',NULL,NULL,13),(30,'抹茶玄米风味','30.00','26.00',0,0,'n',NULL,NULL,13),(31,'焦糖榛果玛奇朵风味','30.00','26.00',0,0,'n',NULL,NULL,13),(32,'芒果西柚风味','30.00','26.00',0,0,'y',NULL,NULL,13),(33,'美式松饼','30.00','24.00',0,6,'y',NULL,NULL,14),(34,'蓝莓麦芬','28.00','22.00',0,3,'n',NULL,NULL,14),(35,'法式香酥可颂','28.00','22.00',0,0,'n',NULL,NULL,14),(36,'层层榛子果仁酥','28.00','22.00',0,13,'n',NULL,NULL,14),(37,'香酥巧克力可颂','28.00','22.00',0,0,'n',NULL,NULL,14),(38,'全麦核桃麦芬','28.00','22.00',0,0,'y',NULL,NULL,14),(39,'蓝莓曲奇风轻乳酪蛋糕','30.00','28.00',0,5,'y',NULL,NULL,15),(40,'浓醇三重黑巧克力蛋糕','33.00','30.00',0,6,'y',NULL,NULL,15),(41,'闪电法式泡芙','26.00','24.00',0,0,'y',NULL,NULL,15),(42,'咖啡提拉米苏蛋糕','28.00','24.00',0,0,'y',NULL,NULL,15),(43,'法式马卡龙','20.00','16.00',0,16,'y',NULL,NULL,15),(44,'纽约风浓郁重芝士蛋糕','36.00','30.00',0,0,'n',NULL,NULL,15),(45,'牛油果鸡肉培果','27.00','25.00',0,0,'n',NULL,NULL,16),(46,'培根蛋可颂堡','28.00','26.00',0,0,'n',NULL,NULL,16),(47,'层层牛肉法棍','30.00','28.00',0,0,'n',NULL,NULL,16),(48,'双重芝士火腿吐司','30.00','28.00',0,1,'n',NULL,NULL,16),(49,'金枪鱼帕尼尼','24.00','20.00',0,0,'n',NULL,NULL,16),(50,'火腿培根英式麦芬','28.00','24.00',0,87,'n',NULL,NULL,16),(51,'混合莓果风味酸奶','18.00','16.00',0,0,'n',NULL,NULL,17),(52,'黄桃风味酸奶','20.00','16.00',0,0,'n',NULL,NULL,17),(53,'英伦风味黄油饼干','28.00','24.00',0,0,'n',NULL,NULL,18),(54,'混合果仁果脯','30.00','36.00',0,67,'n',NULL,NULL,18),(55,'腰果','28.00','26.00',0,0,'n',NULL,NULL,18),(56,'咖啡味蛋卷','34.00','30.00',0,0,'n',NULL,NULL,18),(57,'薄荷味口香糖','14.00','10.00',0,34,'n',NULL,NULL,18),(58,'薄荷味硬糖','10.00','8.00',0,0,'n',NULL,NULL,18),(59,'综合咖啡豆','38.00','34.00',0,0,'n',NULL,NULL,4),(60,'烘焙咖啡豆','38.00','34.00',0,0,'n',NULL,NULL,4),(61,'首选咖啡豆','34.00','30.00',0,2,'n',NULL,NULL,4),(62,'苏门答腊咖啡豆','40.00','36.00',0,0,'n',NULL,NULL,4),(63,'低因祥龙综合咖啡豆','36.00','34.00',0,0,'n',NULL,NULL,4),(64,'意式烘焙咖啡的','32.00','28.00',0,5,'n',NULL,NULL,4),(65,'香草拿铁风味免煮咖啡固体饮料','28.00','25.00',0,0,'n',NULL,NULL,5),(66,'焦糖拿铁风味免煮咖啡固体饮料','28.00','25.00',0,0,'n',NULL,NULL,5),(67,'摩卡风味绵竹固体饮料','28.00','25.00',0,0,'n',NULL,NULL,5);

/*Table structure for table `t_table` */

DROP TABLE IF EXISTS `t_table`;

CREATE TABLE `t_table` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `size` int(11) DEFAULT NULL,
  `flag` tinyint(1) DEFAULT NULL,
  `consumption` double(10,2) DEFAULT NULL,
  `orderid` varchar(155) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_table` */

insert  into `t_table`(`t_id`,`size`,`flag`,`consumption`,`orderid`) values (1,2,1,270.00,'20180705152431135'),(2,2,0,0.00,NULL),(3,2,0,0.00,NULL),(4,2,0,0.00,NULL),(5,4,0,0.00,NULL),(6,4,0,0.00,NULL),(7,4,0,0.00,NULL),(8,4,0,0.00,NULL),(9,6,0,0.00,NULL),(10,6,0,0.00,NULL),(11,6,0,0.00,NULL),(12,6,0,0.00,NULL),(13,6,0,0.00,NULL);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `u_id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `currentconsumption` double(10,2) DEFAULT NULL,
  `sumconsumption` double(10,2) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_user` */

insert  into `t_user`(`u_id`,`username`,`password`,`currentconsumption`,`sumconsumption`,`email`,`gender`,`phone`) values (1,'123','123',123.00,123.00,'123','123','123'),(2,'zhang3','pwd123',NULL,NULL,'6@qq.com',NULL,'xxx'),(3,'zhang3','pwd123',NULL,NULL,'6@qq.com',NULL,'xxx'),(4,'zhang3','pwd123',NULL,NULL,'6@qq.com',NULL,'xxx'),(5,'zhang3','pwd123',0.00,0.00,'6@qq.com','男','xxx');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
