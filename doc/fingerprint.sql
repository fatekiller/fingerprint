/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : fingerprint

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-11-16 11:22:09
*/

SET FOREIGN_KEY_CHECKS=0;

use fingerprint;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `te` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_data
-- ----------------------------
DROP TABLE IF EXISTS `t_data`;
CREATE TABLE `t_data` (
  `userName` varchar(255) DEFAULT NULL,
  `deviceName` varchar(255) DEFAULT NULL,
  `data` text,
  `fingerprint` varchar(255) DEFAULT NULL,
  `date` varchar(20) NOT NULL,
  `user_agent` varchar(255) DEFAULT NULL,
  `plugins` text,
  `fonts` text,
  `video` int(1) DEFAULT NULL,
  `supercookies` varchar(255) DEFAULT NULL,
  `http_accept` varchar(255) DEFAULT NULL,
  `timezone` float(11,0) DEFAULT NULL,
  `cookie_enabled` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
