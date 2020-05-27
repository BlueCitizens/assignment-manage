/*
Navicat MySQL Data Transfer

Source Server         : localDB
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : zuoye

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2020-05-28 00:07:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(55) NOT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '软件测试');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `permissionname` varchar(45) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `permissionname_UNIQUE` (`permissionname`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('16', '部署作业', '/add_work');
INSERT INTO `permission` VALUES ('17', '下载作业', '/download');
INSERT INTO `permission` VALUES ('18', '发布公告', '/conf');
INSERT INTO `permission` VALUES ('19', '上传资料', '/get_all_user');
INSERT INTO `permission` VALUES ('20', '查询所有课程', '/get_all_course');
INSERT INTO `permission` VALUES ('21', '查看works', '/sys/work_list');
INSERT INTO `permission` VALUES ('22', '查看成员', '/sys/stu_list');
INSERT INTO `permission` VALUES ('23', '查看路径下文件', '/peek_path_filename');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(45) NOT NULL,
  PRIMARY KEY (`rid`),
  UNIQUE KEY `rolename_UNIQUE` (`rolename`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('17', '管理');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `rpid` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL,
  `permissionid` int(11) NOT NULL,
  PRIMARY KEY (`rpid`),
  KEY `a_idx` (`roleid`),
  KEY `b_idx` (`permissionid`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('45', '17', '19');
INSERT INTO `role_permission` VALUES ('46', '17', '17');
INSERT INTO `role_permission` VALUES ('47', '17', '18');
INSERT INTO `role_permission` VALUES ('48', '17', '16');
INSERT INTO `role_permission` VALUES ('49', '17', '20');
INSERT INTO `role_permission` VALUES ('50', '17', '21');
INSERT INTO `role_permission` VALUES ('51', '17', '22');
INSERT INTO `role_permission` VALUES ('52', '17', '23');

-- ----------------------------
-- Table structure for upload_history
-- ----------------------------
DROP TABLE IF EXISTS `upload_history`;
CREATE TABLE `upload_history` (
  `business_id` int(11) NOT NULL AUTO_INCREMENT,
  `work_id` int(11) NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `stu_id` char(20) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '1 means deprecated history',
  PRIMARY KEY (`business_id`),
  KEY `work_id` (`work_id`),
  KEY `stu_id` (`stu_id`),
  CONSTRAINT `upload_history_ibfk_1` FOREIGN KEY (`work_id`) REFERENCES `worklist` (`work_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of upload_history
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `stu_id` char(20) NOT NULL,
  `name` varchar(55) NOT NULL,
  `password` char(20) DEFAULT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('123', 'dxy', '123456');
INSERT INTO `user` VALUES ('124', 'ddd', '');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `urid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` char(20) NOT NULL,
  `roleid` int(11) NOT NULL,
  PRIMARY KEY (`urid`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('93', '123', '17');
INSERT INTO `user_role` VALUES ('94', '124', '17');

-- ----------------------------
-- Table structure for worklist
-- ----------------------------
DROP TABLE IF EXISTS `worklist`;
CREATE TABLE `worklist` (
  `work_id` int(11) NOT NULL AUTO_INCREMENT,
  `work_name` varchar(255) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  `naming_rule` varchar(255) DEFAULT NULL,
  `path` varchar(255) NOT NULL,
  `deadline` datetime DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`work_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of worklist
-- ----------------------------
