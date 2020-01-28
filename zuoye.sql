/*
Navicat MySQL Data Transfer

Source Server         : localDB
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : zuoye

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2020-01-28 18:22:58
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
  PRIMARY KEY (`business_id`),
  KEY `work_id` (`work_id`),
  KEY `stu_id` (`stu_id`),
  CONSTRAINT `upload_history_ibfk_1` FOREIGN KEY (`work_id`) REFERENCES `worklist` (`work_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
