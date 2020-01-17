/*
SQLyog Ultimate v12.5.1 (64 bit)
MySQL - 5.5.53 : Database - meet
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`meet` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `meet`;

/*Table structure for table `m_car` */

DROP TABLE IF EXISTS `m_car`;

CREATE TABLE `m_car` (
  `bsm` varchar(100) NOT NULL,
  `carno` varchar(100) NOT NULL COMMENT '车牌号',
  `pingpai` varchar(100) NOT NULL COMMENT '品牌',
  `type` varchar(100) NOT NULL,
  `capacity` int(11) NOT NULL COMMENT '容量',
  `company` varchar(100) NOT NULL COMMENT '所属单位',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  `content` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`bsm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `m_car` */

insert  into `m_car`(`bsm`,`carno`,`pingpai`,`type`,`capacity`,`company`,`status`,`content`) values 
('0dad5571d8d44e6985fe62e20b837626','渝d.9098','奔驰x6','商务车',5,'重庆人文',1,'测试数据'),
('1','渝B.67890','长安福特','商务车',4,'重庆人文科技学院',1,'小型车'),
('2','渝A.67890','奔驰','商务车',4,'重庆人文科技学院',2,'小型车'),
('3','渝C.67890','宝马','商务车',4,'重庆人文科技学院',2,'小型车'),
('4','渝D.67890','长安','商务车',9,'重庆人文科技学院',2,'中型车'),
('5','渝H.67890','大众','商务车',9,'重庆人文科技学院',2,'中型车'),
('6','渝B.60890','海马','大巴车',35,'重庆人文科技学院',2,'大巴车'),
('7','渝B.60090','奔驰','商务车',20,'重庆人文科技学院',2,'中巴车'),
('8','渝B.678955','奔驰','商务车',9,'重庆人文科技学院',2,'中型车'),
('9','渝B.67824','长安福特','商务车',4,'重庆人文科技学院',2,'小型车');

/*Table structure for table `m_gift` */

DROP TABLE IF EXISTS `m_gift`;

CREATE TABLE `m_gift` (
  `bsm` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT '礼品名',
  `totals` int(11) NOT NULL COMMENT '总数量',
  `address` varchar(100) NOT NULL COMMENT '出产地',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `price` double NOT NULL COMMENT '单价',
  `kcsl` int(11) NOT NULL COMMENT '库存数量',
  `content` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`bsm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `m_gift` */

insert  into `m_gift`(`bsm`,`name`,`totals`,`address`,`type`,`price`,`kcsl`,`content`) values 
('1','笔记本',50,'重庆市合川区草街街道','办公用品',20,50,'大笔记本'),
('10','礼品4',10,'重庆市合川区草街街道','办公用品',10,10,'礼品4'),
('2','钢笔',10,'重庆市合川区草街街道','办公用品',10,10,'钢笔'),
('3','毛笔',10,'重庆市合川区草街街道','办公用品',10,10,'毛笔'),
('4','钥匙坠',10,'重庆市合川区草街街道','办公用品',10,10,'钥匙坠'),
('5','100礼品券',10,'重庆市合川区草街街道','办公用品',10,10,'100礼品券'),
('6','50礼品券',10,'重庆市合川区草街街道','办公用品',10,10,'50礼品券'),
('7','礼品1',10,'重庆市合川区草街街道','办公用品',10,10,'礼品1'),
('8','礼品2',10,'重庆市合川区草街街道','办公用品',10,10,'礼品2'),
('9','礼品3',10,'重庆市合川区草街街道','办公用品',10,10,'礼品3'),
('e012b27ec68547f6994899c2a936e510','礼品11',90,'重庆合川区','办公用品',10,90,'测试数据');

/*Table structure for table `m_hyxx` */

DROP TABLE IF EXISTS `m_hyxx`;

CREATE TABLE `m_hyxx` (
  `bsm` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT '会议名称主题',
  `overtime` date NOT NULL COMMENT '结束时间',
  `starttime` date NOT NULL COMMENT '开始时间',
  `totals` int(11) NOT NULL DEFAULT '0' COMMENT '参会人数',
  `mroom_bsm` varchar(100) NOT NULL COMMENT '会议室标识码',
  `u_bsm` varchar(256) NOT NULL COMMENT '参会人员标识码',
  `content` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`bsm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `m_hyxx` */

insert  into `m_hyxx`(`bsm`,`name`,`overtime`,`starttime`,`totals`,`mroom_bsm`,`u_bsm`,`content`) values 
('1e77b52456654c86aeb95eb2a49bc524','gggg','2020-01-16','2020-01-15',10,'6d8efc228a6146e5b9dd59b673b06c26','1,10,11,2,3,4,7,8,9,b95354b7de1c47808e4714759075ab5c','asda'),
('9a25044ea8e24f6e8f1bc464dce574d4','ERERER','2020-01-14','2020-01-14',3,'6d8efc228a6146e5b9dd59b673b06c26','1,10,11','DSSA'),
('f086bb6668444e4d8ad086df7dc696ba','dddd','2020-01-15','2020-01-14',3,'6d8efc228a6146e5b9dd59b673b06c26','1,10,11','DSSA');

/*Table structure for table `m_jsj` */

DROP TABLE IF EXISTS `m_jsj`;

CREATE TABLE `m_jsj` (
  `bsm` varchar(100) NOT NULL,
  `time` date NOT NULL COMMENT '时间',
  `placeb` varchar(100) NOT NULL COMMENT '目的地',
  `placea` varchar(100) NOT NULL COMMENT '出发地',
  `carno` varchar(100) NOT NULL COMMENT '车辆bsm',
  `u_bsm` varchar(256) NOT NULL COMMENT '需要接送人的标识码',
  `totals` int(11) NOT NULL COMMENT '需要接送人数',
  `content` varchar(256) NOT NULL COMMENT '备注',
  PRIMARY KEY (`bsm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `m_jsj` */

insert  into `m_jsj`(`bsm`,`time`,`placeb`,`placea`,`carno`,`u_bsm`,`totals`,`content`) values 
('cec83984d2d44b3fa84bc7f5e986504c','2020-01-15','机场xxx','学校','学校','1,10,11,2',4,'测试');

/*Table structure for table `m_meetroom` */

DROP TABLE IF EXISTS `m_meetroom`;

CREATE TABLE `m_meetroom` (
  `bsm` varchar(100) NOT NULL,
  `place` varchar(100) NOT NULL COMMENT '地点',
  `roomname` varchar(100) NOT NULL COMMENT '会议室名字',
  `capacity` int(11) NOT NULL COMMENT '容量',
  `status` int(11) NOT NULL COMMENT '状态',
  `u_bsm` varchar(100) NOT NULL COMMENT '负责人bsm',
  `content` varchar(256) NOT NULL,
  PRIMARY KEY (`bsm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `m_meetroom` */

/*Table structure for table `m_regist` */

DROP TABLE IF EXISTS `m_regist`;

CREATE TABLE `m_regist` (
  `bsm` varchar(100) NOT NULL,
  `m_title` varchar(100) NOT NULL COMMENT '会议主题',
  `u_name` varchar(100) NOT NULL COMMENT '签到人姓名',
  `u_cardid` varchar(100) NOT NULL COMMENT '签到人身份证号',
  `content` varchar(100) NOT NULL COMMENT '备注',
  `begintime` date NOT NULL COMMENT '开始时间',
  `overtime` date NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`bsm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `m_regist` */

/*Table structure for table `m_room` */

DROP TABLE IF EXISTS `m_room`;

CREATE TABLE `m_room` (
  `bsm` varchar(100) NOT NULL,
  `roomno` varchar(100) NOT NULL COMMENT '房间号',
  `place` varchar(100) NOT NULL COMMENT '地点',
  `status` int(11) NOT NULL COMMENT '状态(空闲2,打扫中3,使用中1)',
  `type` int(11) NOT NULL COMMENT '类型(1会议室,2住宿)',
  `capacity` int(11) NOT NULL COMMENT '容量',
  `roomname` varchar(100) NOT NULL COMMENT '房间名称',
  `phone` varchar(100) NOT NULL COMMENT '房间电话',
  `content` varchar(256) NOT NULL,
  PRIMARY KEY (`bsm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `m_room` */

insert  into `m_room`(`bsm`,`roomno`,`place`,`status`,`type`,`capacity`,`roomname`,`phone`,`content`) values 
('1','115','草街酒店',1,2,4,'6人套间','40824582','套房'),
('2','111','草街酒店',2,2,2,'2人套间','40824582','套房'),
('3','112','草街酒店',1,2,2,'4人套间','40824582','套房'),
('4','113','草街酒店',1,2,3,'3人套间','40824582','套房'),
('5','114','草街酒店',2,2,1,'豪华单间','40824582','套房'),
('6','202','综合楼',3,1,20,'会议室1','40824582','会议室'),
('6d8efc228a6146e5b9dd59b673b06c26','303','慧园',2,1,20,'会议室5','50221364','测试'),
('7','211','综合楼',2,1,30,'会议室2','40824582','会议室'),
('8','222','综合楼',1,1,10,'会议室3','40824582','会议室'),
('9','3101','综合楼',1,1,5,'会议室4','40824582','会议室');

/*Table structure for table `m_user` */

DROP TABLE IF EXISTS `m_user`;

CREATE TABLE `m_user` (
  `bsm` varchar(100) NOT NULL COMMENT '标识码',
  `name` varchar(100) NOT NULL,
  `pwd` varchar(100) NOT NULL,
  `cardid` varchar(100) NOT NULL COMMENT '身份证号',
  `age` int(50) NOT NULL,
  `sex` varchar(50) NOT NULL,
  `level` varchar(100) NOT NULL COMMENT '职务',
  `phone` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `acl` varchar(100) NOT NULL DEFAULT '0' COMMENT '权限(0 : 普通用户,1:工作人员,2:管理员用户)',
  `company` varchar(100) NOT NULL COMMENT '所在单位(公司)',
  `content` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`bsm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `m_user` */

insert  into `m_user`(`bsm`,`name`,`pwd`,`cardid`,`age`,`sex`,`level`,`phone`,`email`,`acl`,`company`,`content`) values 
('1','admin','1','500221199608051298',23,'男','董事长','15696049710','1475556814@qq.com','2','计算机工程学院',NULL),
('10','uqueu','1','123456667',12,'男','部门经理','456665','1475556814@qq.com','1','人文科技','哈哈'),
('11','uuutt','1','123456667',12,'男','部门经理','456665','1475556814@qq.com','1','人文科技','哈哈'),
('2','yyw','1','500221199608051297',22,'女','总经理','18716277065','1475556814@qq.com','1','计算机工程学院11','8888'),
('3','uuu','1','123456667',12,'男','部门经理','456665','1475556814@qq.com','1','人文科技','哈哈'),
('4','uuqu','1','123456667',12,'男','部门经理','456665','1475556814@qq.com','1','人文科技','哈哈'),
('5','uuru','1','123456667',12,'男','部门经理','456665','1475556814@qq.com','1','人文科技','哈哈'),
('6','utuu','1','123456667',12,'男','部门经理','456665','1475556814@qq.com','0','人文科技','哈哈'),
('7','uuyu','1','123456667',12,'男','部门经理','456665','1475556814@qq.com','0','人文科技','哈哈'),
('8','uuuu','1','123456667',12,'男','部门经理','456665','1475556814@qq.com','0','人文科技','哈哈'),
('9','uuui','1','123456667',12,'男','部门经理','456665','1475556814@qq.com','0','人文科技','哈哈'),
('b95354b7de1c47808e4714759075ab5c','ddd','1','500221199606528415',34,'男','教师q','3421423423','231231231','0','重庆人文科技','4234234');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
