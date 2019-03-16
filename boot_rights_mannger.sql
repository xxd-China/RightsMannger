/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50037
 Source Host           : localhost:3306
 Source Schema         : boot_rights_mannger

 Target Server Type    : MySQL
 Target Server Version : 50037
 File Encoding         : 65001

 Date: 17/03/2019 03:09:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept`  (
  `DEPT_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `PARENT_ID` bigint(20) NOT NULL DEFAULT '' COMMENT '上级部门ID',
  `DEPT_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `ORDER_NUM` bigint(20) NULL DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY USING BTREE (`DEPT_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES (1, 0, '开发部', NULL, '2018-01-04 15:42:26');
INSERT INTO `t_dept` VALUES (2, 1, '开发部门1', NULL, '2018-01-04 15:42:34');
INSERT INTO `t_dept` VALUES (3, 1, '开发部门2', NULL, '2018-01-04 15:42:29');
INSERT INTO `t_dept` VALUES (4, 0, '市场部', NULL, '2018-01-04 15:42:36');
INSERT INTO `t_dept` VALUES (5, 0, '人事部', NULL, '2018-01-04 15:42:32');
INSERT INTO `t_dept` VALUES (6, 0, '测试部', NULL, '2018-01-04 15:42:38');
INSERT INTO `t_dept` VALUES (7, 0, '销售部', NULL, '2018-01-04 15:42:38');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `MENU_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单/按钮ID',
  `PARENT_ID` bigint(20) NOT NULL DEFAULT '' COMMENT '上级菜单ID',
  `MENU_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '菜单/按钮名称',
  `URL` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `PERMS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '权限标识',
  `ICON` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `TYPE` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '类型 0菜单 1按钮',
  `ORDER_NUM` bigint(20) NULL DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` datetime NOT NULL DEFAULT '' COMMENT '创建时间',
  `MODIFY_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY USING BTREE (`MENU_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, 0, '系统管理', NULL, NULL, 'zmdi zmdi-settings', '0', 1, '2017-12-27 16:39:07', NULL);
INSERT INTO `t_menu` VALUES (2, 0, '系统监控', NULL, NULL, 'zmdi zmdi-shield-security', '0', 2, '2017-12-27 16:45:51', '2018-01-17 17:08:28');
INSERT INTO `t_menu` VALUES (3, 1, '用户管理', 'user', 'user:list', '', '0', 1, '2017-12-27 16:47:13', '2018-04-25 09:00:01');
INSERT INTO `t_menu` VALUES (4, 1, '角色管理', 'role', 'role:list', '', '0', 2, '2017-12-27 16:48:09', '2018-04-25 09:01:12');
INSERT INTO `t_menu` VALUES (5, 1, '菜单管理', 'menu', 'menu:list', '', '0', 3, '2017-12-27 16:48:57', '2018-04-25 09:01:30');
INSERT INTO `t_menu` VALUES (6, 1, '部门管理', 'dept', 'dept:list', '', '0', 4, '2017-12-27 16:57:33', '2018-04-25 09:01:40');
INSERT INTO `t_menu` VALUES (8, 2, '在线用户', 'session', 'session:list', '', '0', 1, '2017-12-27 16:59:33', '2018-04-25 09:02:04');
INSERT INTO `t_menu` VALUES (11, 3, '新增用户', NULL, 'user:add', NULL, '1', NULL, '2017-12-27 17:02:58', NULL);
INSERT INTO `t_menu` VALUES (12, 3, '修改用户', NULL, 'user:update', NULL, '1', NULL, '2017-12-27 17:04:07', NULL);
INSERT INTO `t_menu` VALUES (13, 3, '删除用户', NULL, 'user:delete', NULL, '1', NULL, '2017-12-27 17:04:58', NULL);
INSERT INTO `t_menu` VALUES (14, 4, '新增角色', NULL, 'role:add', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `t_menu` VALUES (15, 4, '修改角色', NULL, 'role:update', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `t_menu` VALUES (16, 4, '删除角色', NULL, 'role:delete', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `t_menu` VALUES (17, 5, '新增菜单', NULL, 'menu:add', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `t_menu` VALUES (18, 5, '修改菜单', NULL, 'menu:update', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `t_menu` VALUES (19, 5, '删除菜单', NULL, 'menu:delete', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `t_menu` VALUES (20, 6, '新增部门', NULL, 'dept:add', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `t_menu` VALUES (21, 6, '修改部门', NULL, 'dept:update', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `t_menu` VALUES (22, 6, '删除部门', NULL, 'dept:delete', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `t_menu` VALUES (23, 8, '踢出用户', NULL, 'user:kickout', NULL, '1', NULL, '2017-12-27 17:11:13', NULL);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `ROLE_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `CREATE_TIME` datetime NOT NULL DEFAULT '' COMMENT '创建时间',
  `MODIFY_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY USING BTREE (`ROLE_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '管理员', '管理员', '2017-12-27 16:23:11', '2018-02-24 16:01:45');
INSERT INTO `t_role` VALUES (2, '测试账号', '测试账号', '2017-12-27 16:25:09', '2018-01-23 09:11:11');
INSERT INTO `t_role` VALUES (3, '注册账户', '注册账户，只可查看，不可操作', '2017-12-29 16:00:15', '2018-02-24 17:33:45');
INSERT INTO `t_role` VALUES (23, '用户管理员', '负责用户的增删改操作', '2018-01-09 15:32:41', NULL);
INSERT INTO `t_role` VALUES (24, '系统监控员', '可查看系统监控信息，但不可操作', '2018-01-09 15:52:01', '2018-03-07 19:05:33');
INSERT INTO `t_role` VALUES (25, '用户查看', '查看用户，无相应操作权限', '2018-01-09 15:56:30', NULL);

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `ROLE_ID` bigint(20) NOT NULL DEFAULT '' COMMENT '角色ID',
  `MENU_ID` bigint(20) NOT NULL DEFAULT '' COMMENT '菜单/按钮ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES (3, 58);
INSERT INTO `t_role_menu` VALUES (3, 59);
INSERT INTO `t_role_menu` VALUES (3, 61);
INSERT INTO `t_role_menu` VALUES (3, 81);
INSERT INTO `t_role_menu` VALUES (3, 82);
INSERT INTO `t_role_menu` VALUES (3, 83);
INSERT INTO `t_role_menu` VALUES (3, 86);
INSERT INTO `t_role_menu` VALUES (3, 87);
INSERT INTO `t_role_menu` VALUES (3, 88);
INSERT INTO `t_role_menu` VALUES (3, 89);
INSERT INTO `t_role_menu` VALUES (3, 1);
INSERT INTO `t_role_menu` VALUES (3, 3);
INSERT INTO `t_role_menu` VALUES (3, 4);
INSERT INTO `t_role_menu` VALUES (3, 5);
INSERT INTO `t_role_menu` VALUES (3, 6);
INSERT INTO `t_role_menu` VALUES (3, 64);
INSERT INTO `t_role_menu` VALUES (3, 2);
INSERT INTO `t_role_menu` VALUES (3, 8);
INSERT INTO `t_role_menu` VALUES (3, 10);
INSERT INTO `t_role_menu` VALUES (3, 101);
INSERT INTO `t_role_menu` VALUES (3, 102);
INSERT INTO `t_role_menu` VALUES (3, 109);
INSERT INTO `t_role_menu` VALUES (63, 58);
INSERT INTO `t_role_menu` VALUES (63, 81);
INSERT INTO `t_role_menu` VALUES (63, 82);
INSERT INTO `t_role_menu` VALUES (63, 83);
INSERT INTO `t_role_menu` VALUES (24, 8);
INSERT INTO `t_role_menu` VALUES (24, 2);
INSERT INTO `t_role_menu` VALUES (24, 10);
INSERT INTO `t_role_menu` VALUES (65, 86);
INSERT INTO `t_role_menu` VALUES (65, 88);
INSERT INTO `t_role_menu` VALUES (65, 89);
INSERT INTO `t_role_menu` VALUES (65, 58);
INSERT INTO `t_role_menu` VALUES (65, 61);
INSERT INTO `t_role_menu` VALUES (2, 81);
INSERT INTO `t_role_menu` VALUES (2, 61);
INSERT INTO `t_role_menu` VALUES (2, 24);
INSERT INTO `t_role_menu` VALUES (2, 82);
INSERT INTO `t_role_menu` VALUES (2, 83);
INSERT INTO `t_role_menu` VALUES (2, 58);
INSERT INTO `t_role_menu` VALUES (2, 59);
INSERT INTO `t_role_menu` VALUES (2, 2);
INSERT INTO `t_role_menu` VALUES (2, 8);
INSERT INTO `t_role_menu` VALUES (2, 10);
INSERT INTO `t_role_menu` VALUES (23, 11);
INSERT INTO `t_role_menu` VALUES (23, 12);
INSERT INTO `t_role_menu` VALUES (23, 13);
INSERT INTO `t_role_menu` VALUES (23, 3);
INSERT INTO `t_role_menu` VALUES (23, 1);
INSERT INTO `t_role_menu` VALUES (25, 1);
INSERT INTO `t_role_menu` VALUES (25, 3);
INSERT INTO `t_role_menu` VALUES (1, 59);
INSERT INTO `t_role_menu` VALUES (1, 2);
INSERT INTO `t_role_menu` VALUES (1, 3);
INSERT INTO `t_role_menu` VALUES (1, 67);
INSERT INTO `t_role_menu` VALUES (1, 1);
INSERT INTO `t_role_menu` VALUES (1, 4);
INSERT INTO `t_role_menu` VALUES (1, 5);
INSERT INTO `t_role_menu` VALUES (1, 6);
INSERT INTO `t_role_menu` VALUES (1, 20);
INSERT INTO `t_role_menu` VALUES (1, 21);
INSERT INTO `t_role_menu` VALUES (1, 22);
INSERT INTO `t_role_menu` VALUES (1, 10);
INSERT INTO `t_role_menu` VALUES (1, 8);
INSERT INTO `t_role_menu` VALUES (1, 58);
INSERT INTO `t_role_menu` VALUES (1, 66);
INSERT INTO `t_role_menu` VALUES (1, 11);
INSERT INTO `t_role_menu` VALUES (1, 12);
INSERT INTO `t_role_menu` VALUES (1, 64);
INSERT INTO `t_role_menu` VALUES (1, 13);
INSERT INTO `t_role_menu` VALUES (1, 14);
INSERT INTO `t_role_menu` VALUES (1, 65);
INSERT INTO `t_role_menu` VALUES (1, 15);
INSERT INTO `t_role_menu` VALUES (1, 16);
INSERT INTO `t_role_menu` VALUES (1, 17);
INSERT INTO `t_role_menu` VALUES (1, 18);
INSERT INTO `t_role_menu` VALUES (1, 23);
INSERT INTO `t_role_menu` VALUES (1, 81);
INSERT INTO `t_role_menu` VALUES (1, 82);
INSERT INTO `t_role_menu` VALUES (1, 83);
INSERT INTO `t_role_menu` VALUES (1, 19);
INSERT INTO `t_role_menu` VALUES (1, 24);
INSERT INTO `t_role_menu` VALUES (1, 61);
INSERT INTO `t_role_menu` VALUES (1, 86);
INSERT INTO `t_role_menu` VALUES (1, 87);
INSERT INTO `t_role_menu` VALUES (1, 88);
INSERT INTO `t_role_menu` VALUES (1, 89);
INSERT INTO `t_role_menu` VALUES (1, 101);
INSERT INTO `t_role_menu` VALUES (1, 102);
INSERT INTO `t_role_menu` VALUES (1, 103);
INSERT INTO `t_role_menu` VALUES (1, 104);
INSERT INTO `t_role_menu` VALUES (1, 105);
INSERT INTO `t_role_menu` VALUES (1, 106);
INSERT INTO `t_role_menu` VALUES (1, 107);
INSERT INTO `t_role_menu` VALUES (1, 108);
INSERT INTO `t_role_menu` VALUES (1, 109);
INSERT INTO `t_role_menu` VALUES (1, 110);
INSERT INTO `t_role_menu` VALUES (64, 59);
INSERT INTO `t_role_menu` VALUES (64, 58);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `PASSWORD` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `DEPT_ID` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `EMAIL` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `MOBILE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `STATUS` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '状态 0锁定 1有效',
  `CRATE_TIME` datetime NOT NULL DEFAULT '' COMMENT '创建时间',
  `MODIFY_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `LAST_LOGIN_TIME` datetime NULL DEFAULT NULL COMMENT '最近访问时间',
  `SSEX` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别 0男 1女',
  `THEME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题',
  `AVATAR` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `DESCRIPTION` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY USING BTREE (`USER_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (4, 'xxd', '202cb962ac59075b964b07152d234b70', 5, 'mrbird@hotmail.com', '13455533222', '1', '2017-12-27 15:47:19', '2018-03-21 09:05:12', '2019-03-15 04:02:02', '0', 'green', 'default.jpg', '我是作者。');
INSERT INTO `t_user` VALUES (6, 'tester', '202cb962ac59075b964b07152d234b70', 6, 'tester@qq.com', '18959178590', '1', '2017-12-27 17:35:14', '2019-03-16 20:15:17', '2018-01-23 09:17:27', '1', 'teal', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (25, 'allen', '83baac97928a113986054efacaeec1d2', 3, 'allen@qq.com', '13427374857', '0', '2017-12-29 16:21:54', '2018-01-17 11:28:16', NULL, '1', 'indigo', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (26, 'martin', 'b26c9edca9a61016bca1f6fb042e679e', 4, 'martin@qq.com', '15562736678', '1', '2017-12-29 16:22:24', '2018-01-25 09:23:15', '2018-01-25 17:24:50', '1', 'teal', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (27, 'ford', '0448f0dcfd856b0e831842072b532141', 6, 'ford@qq.com', '15599998373', '0', '2017-12-29 16:22:52', '2018-03-13 11:19:56', '2018-03-08 16:31:59', '0', 'cyan', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (91, '系统监控员', '7c28d1cd33414ac15832f7be92668b7a', 6, 'xtjk@qq.com', '18088736652', '1', '2018-01-09 15:52:56', NULL, '2018-01-09 15:53:12', '0', 'cyan', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (92, 'bbb', '0448f0dcfd856b0e831842072b532141', 6, 'ford@qq.com', '15599998373', '0', '2017-12-29 16:22:52', '2018-03-13 11:19:56', '2018-03-08 16:31:59', '0', 'cyan', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (93, 'oooop', '0448f0dcfd856b0e831842072b532141', 6, 'ford@qq.com', '15599998373', '0', '2017-12-29 16:22:52', '2018-03-13 11:19:56', '2018-03-08 16:31:59', '0', 'cyan', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (94, 'aaaaa', 'e10adc3949ba59abbe56e057f20f883e', 4, '574965732@qq.com', '13205003087', '1', '2019-03-16 19:23:16', NULL, NULL, '1', 'green', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (95, 'ddddddd', 'e10adc3949ba59abbe56e057f20f883e', 4, '574965732@qq.com', '15080093715', '1', '2019-03-16 19:30:35', NULL, NULL, '2', 'green', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (96, 'fffffff', 'e10adc3949ba59abbe56e057f20f883e', 4, '574965732@qq.com', '13205003088', '1', '2019-03-16 20:05:56', NULL, NULL, '2', 'green', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (97, 'aaa', 'e10adc3949ba59abbe56e057f20f883e', 4, '574965555@qq.com', '13888888888', '1', '2019-03-17 01:20:39', NULL, NULL, '1', 'green', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (100, 'ford1', '0448f0dcfd856b0e831842072b532141', 6, 'ford@qq.com', '15599998373', '0', '2017-12-29 16:22:52', '2018-03-13 11:19:56', '2018-03-08 16:31:59', '0', 'cyan', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (101, 'kilp', '0448f0dcfd856b0e831842072b532141', 6, 'ford@qq.com', '15599998373', '0', '2017-12-29 16:22:52', '2018-03-13 11:19:56', '2018-03-08 16:31:59', '0', 'cyan', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (102, 'hjk', '7c28d1cd33414ac15832f7be92668b7a', 6, 'xtjk@qq.com', '18088736652', '1', '2018-01-09 15:52:56', NULL, '2018-01-09 15:53:12', '0', 'cyan', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (422, 'mrBird', '42ee25d1e43e9f57119a00d0a39e5250', 5, 'mrbird@hotmail.com', '13455533222', '1', '2017-12-27 15:47:19', '2018-03-21 09:05:12', '2019-03-17 02:17:57', '0', 'lime', '20180414165909.jpg', '我是作者。');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `USER_ID` bigint(20) NOT NULL DEFAULT '' COMMENT '用户ID',
  `ROLE_ID` bigint(20) NOT NULL DEFAULT '' COMMENT '角色ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (27, 3);
INSERT INTO `t_user_role` VALUES (24, 65);
INSERT INTO `t_user_role` VALUES (26, 3);
INSERT INTO `t_user_role` VALUES (26, 23);
INSERT INTO `t_user_role` VALUES (26, 24);
INSERT INTO `t_user_role` VALUES (25, 3);
INSERT INTO `t_user_role` VALUES (91, 24);
INSERT INTO `t_user_role` VALUES (4, 1);
INSERT INTO `t_user_role` VALUES (23, 2);
INSERT INTO `t_user_role` VALUES (23, 3);
INSERT INTO `t_user_role` VALUES (23, 23);
INSERT INTO `t_user_role` VALUES (23, 24);
INSERT INTO `t_user_role` VALUES (23, 25);
INSERT INTO `t_user_role` VALUES (96, 3);
INSERT INTO `t_user_role` VALUES (6, 1);
INSERT INTO `t_user_role` VALUES (6, 2);
INSERT INTO `t_user_role` VALUES (6, 3);
INSERT INTO `t_user_role` VALUES (6, 25);
INSERT INTO `t_user_role` VALUES (6, 63);
INSERT INTO `t_user_role` VALUES (97, 23);

SET FOREIGN_KEY_CHECKS = 1;
