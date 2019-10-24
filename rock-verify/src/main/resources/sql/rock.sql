/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.222
Source Server Version : 50723
Source Host           : 192.168.1.222:3306
Source Database       : rock

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-10-24 18:13:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `sys_name` varchar(255) NOT NULL COMMENT '业务简称(符合命名规范,数据库中唯一)',
  `sys_nickname` varchar(255) NOT NULL COMMENT '业务名称',
  `description` varchar(255) DEFAULT NULL COMMENT '系统描述信息',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of business
-- ----------------------------
INSERT INTO `business` VALUES ('191019H5RWGMF894', 'test', 'test', 'create user', 'admin', '2019-10-19 22:42:28');
INSERT INTO `business` VALUES ('1910207RXTM0TBMW', 'test1', '测试', '创建业务测试', 'admin', '2019-10-20 10:54:18');
INSERT INTO `business` VALUES ('1910207Z6H9PN63C', 'test1', '测试', '创建业务测试', 'admin', '2019-10-20 11:10:15');
INSERT INTO `business` VALUES ('1910246Z1ZZR9BF8', 'test2', '测试2', '创建业务测试', 'admin', '2019-10-24 09:45:42');
INSERT INTO `business` VALUES ('19102476Z9XFXSY8', 'test3', '测试3', '创建业务测试3', 'admin', '2019-10-24 10:09:28');

-- ----------------------------
-- Table structure for rock_log_struct
-- ----------------------------
DROP TABLE IF EXISTS `rock_log_struct`;
CREATE TABLE `rock_log_struct` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '日志集名称',
  `status` bigint(1) DEFAULT NULL COMMENT '状态: 0表示发布；1 表示未发布',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_by` datetime DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `log_struct` varchar(255) NOT NULL COMMENT '日志集字段描述，使用json字符串的形式，key表示列值，value由值类型 + “|” + 描述组成',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `business

_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rock_log_struct
-- ----------------------------

-- ----------------------------
-- Table structure for rock_metric_struct
-- ----------------------------
DROP TABLE IF EXISTS `rock_metric_struct`;
CREATE TABLE `rock_metric_struct` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '日志集名称',
  `status` bigint(1) DEFAULT NULL COMMENT '状态: 0表示发布；1 表示未发布',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_by` datetime DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `metric_struct` varchar(255) NOT NULL COMMENT '日志集字段描述，使用json字符串的形式，key表示列值，value由值类型 + “|” + 描述组成',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `business

_id` varchar(255) NOT NULL COMMENT '业务id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rock_metric_struct
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(20) NOT NULL,
  `username` varchar(60) NOT NULL COMMENT '用户名，登录名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `sex` int(1) DEFAULT NULL COMMENT '性别\r\n0：女\r\n1：男\r\n2：保密 ',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统后台用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录账号',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `sex` char(1) DEFAULT NULL COMMENT '用户性别（0男 1女 2未知)',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像路径',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '1', 'admin', 'admin', 'admin@163.com', '15123436781', '1', null, 'admin', null, null);
