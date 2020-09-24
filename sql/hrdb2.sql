/*
Navicat MySQL Data Transfer

Source Server         : 123
Source Server Version : 50539
Source Host           : localhost:3306
Source Database       : hrdb2

Target Server Type    : MYSQL
Target Server Version : 50539
File Encoding         : 65001

Date: 2020-09-13 22:12:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `did` int(11) NOT NULL AUTO_INCREMENT,
  `dname` varchar(20) DEFAULT NULL,
  `dremark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', '营业部', null);
INSERT INTO `dept` VALUES ('2', '销售部', null);
INSERT INTO `dept` VALUES ('3', '广告部', null);
INSERT INTO `dept` VALUES ('9', '宣传部', '');
INSERT INTO `dept` VALUES ('12', '司法部', '');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_id` int(11) DEFAULT NULL,
  `job_id` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `card_id` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `post_code` varchar(50) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `qq_num` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `sex` enum('男','女') DEFAULT NULL,
  `party` enum('党员','团员','群众') DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `race` varchar(10) DEFAULT NULL,
  `education` varchar(5) DEFAULT NULL,
  `speciality` varchar(15) DEFAULT NULL,
  `hobby` varchar(30) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dept_id` (`dept_id`),
  KEY `job_id` (`job_id`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`did`),
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`job_id`) REFERENCES `job` (`jid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('4', '1', '1', '胖虎', '123123', '广西省', '1222222222', '12311232', '11111111111', '123', '123@qq.com', '男', '党员', '2020-07-18', '10000', '本科', '打游戏', '打游戏', '', '2020-07-18 14:31:17');
INSERT INTO `employee` VALUES ('8', '2', '1', '大雄', '1231231231', '广东省', '1231231111', '123123', '11111111111', '123123', '123123@qq.com', '男', '团员', '2020-07-19', '10000', '本科', '打篮球', '打篮球', '', '2020-07-19 12:45:09');
INSERT INTO `employee` VALUES ('15', '2', '2', '莉莉丝', '12312312312', '广东省', '1231231231', '12312312312', '11111111111', '123213', '12312312@163.com', '女', '党员', '2020-07-21', '10000', '本科', '逛街', '逛街', '', '2020-07-21 18:42:46');

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `jid` int(11) NOT NULL AUTO_INCREMENT,
  `jname` varchar(20) DEFAULT NULL,
  `jremark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`jid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES ('1', '职员', '');
INSERT INTO `job` VALUES ('2', '主管', null);
INSERT INTO `job` VALUES ('4', 'CEO', '');
INSERT INTO `job` VALUES ('5', 'CFO', '');
INSERT INTO `job` VALUES ('8', '扫地阿姨', '');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usename` varchar(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `title` varchar(250) DEFAULT NULL,
  `content` varchar(250) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('15', 'corki', '震惊！该小火竟然做出这样的事', '震惊！该小火竟然做出这样的事', '近日，某大学的三位同学在腾讯会议上。。。。', null);
INSERT INTO `notice` VALUES ('16', 'corki', '你吃了吗', '你吃了吗', '还没呢', null);
INSERT INTO `notice` VALUES ('17', 'corki', '你好吗', '你好吗', '我很好', null);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(20) DEFAULT NULL,
  `rremark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', '');
INSERT INTO `role` VALUES ('2', '超级管理员', null);
INSERT INTO `role` VALUES ('3', '最高权限', null);

-- ----------------------------
-- Table structure for uploadfile
-- ----------------------------
DROP TABLE IF EXISTS `uploadfile`;
CREATE TABLE `uploadfile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uploadName` varchar(50) DEFAULT NULL,
  `fileName` varchar(100) DEFAULT NULL,
  `uploadDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uploadfile
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `number` int(10) NOT NULL AUTO_INCREMENT,
  `password` varchar(20) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`number`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('9', '222', '3', '22222222223', '2020-07-16 19:21:31', 'corki', '');
INSERT INTO `user` VALUES ('39', '123', '1', '11111111111', '2020-07-21 22:37:46', 'cccc', '');
INSERT INTO `user` VALUES ('40', '123', '1', '22222222222', '2020-07-21 22:38:00', 'aaaaa', '');
INSERT INTO `user` VALUES ('41', '123', '2', '12121212121', '2020-07-21 22:38:20', 'dddd', '');
INSERT INTO `user` VALUES ('42', '22233', '2', '33333333333', '2020-07-21 22:38:38', 'bbbbb', '');
INSERT INTO `user` VALUES ('43', '1212', '1', '23232323233', '2020-07-21 22:39:27', 'cdcd', '');
INSERT INTO `user` VALUES ('44', '123321', '2', '32132132133', '2020-07-21 22:39:45', 'rrrrrr', '');
