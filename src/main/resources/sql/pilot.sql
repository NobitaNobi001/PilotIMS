/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : pilot

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 20/04/2021 21:40:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for addition_field
-- ----------------------------
DROP TABLE IF EXISTS `addition_field`;
CREATE TABLE `addition_field`  (
  `id` bigint(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of addition_field
-- ----------------------------
INSERT INTO `addition_field` VALUES (1, NULL);
INSERT INTO `addition_field` VALUES (2, NULL);
INSERT INTO `addition_field` VALUES (3, NULL);
INSERT INTO `addition_field` VALUES (4, NULL);
INSERT INTO `addition_field` VALUES (5, NULL);
INSERT INTO `addition_field` VALUES (6, NULL);
INSERT INTO `addition_field` VALUES (7, NULL);
INSERT INTO `addition_field` VALUES (8, NULL);
INSERT INTO `addition_field` VALUES (9, NULL);
INSERT INTO `addition_field` VALUES (10, NULL);

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `dept_id` bigint(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `dept_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (1, '飞行部');

-- ----------------------------
-- Table structure for pilot
-- ----------------------------
DROP TABLE IF EXISTS `pilot`;
CREATE TABLE `pilot`  (
  `pilot_id` bigint(16) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pilot_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `sex` tinyint(1) UNSIGNED NOT NULL COMMENT '性别(0男1女)',
  `card` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号码',
  `dept_id` bigint(16) UNSIGNED NOT NULL COMMENT '部门id',
  `position` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位',
  `job_title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`pilot_id`) USING BTREE,
  INDEX `pilot`(`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pilot
-- ----------------------------

-- ----------------------------
-- Table structure for pilot_body
-- ----------------------------
DROP TABLE IF EXISTS `pilot_body`;
CREATE TABLE `pilot_body`  (
  `pilot_id` bigint(16) UNSIGNED NOT NULL COMMENT '飞行员编号',
  `is_deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除(1表示删除0表示未删除)',
  `created_time` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  `height` float(6, 2) NULL DEFAULT NULL COMMENT '身高',
  `weight` float(6, 2) NULL DEFAULT NULL COMMENT '体重',
  `collar_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '领围',
  `neck_root_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '颈根围',
  `bust` float(6, 2) NULL DEFAULT NULL COMMENT '胸围',
  `chest_position` float(6, 2) NULL DEFAULT NULL COMMENT '胸位',
  `under_bust` float(6, 2) NULL DEFAULT NULL COMMENT '下胸围',
  `waist_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '腰围',
  `waist_height` float(6, 2) NULL DEFAULT NULL COMMENT '腰高',
  `pant_waist_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '裤腰围',
  `pant_waist_height` float(6, 2) NULL DEFAULT NULL COMMENT '裤腰高',
  `hip_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '臀围',
  `hip_height` float(6, 2) NULL DEFAULT NULL COMMENT '臀高',
  `straight_crotch` float(6, 2) NULL DEFAULT NULL COMMENT '周裆',
  `perineal_height` float(6, 2) NULL DEFAULT NULL COMMENT '会阴高',
  `left_arm_length` float(6, 2) NULL DEFAULT NULL COMMENT '左臂长',
  `right_arm_length` float(6, 2) NULL DEFAULT NULL COMMENT '右臂长',
  `left_leg_length` float(6, 2) NULL DEFAULT NULL COMMENT '左腿长',
  `right_leg_length` float(6, 2) NULL DEFAULT NULL COMMENT '右腿长',
  `front_shoulder_width` float(6, 2) NULL DEFAULT NULL COMMENT '前肩宽',
  `back_shoulder_width` float(6, 2) NULL DEFAULT NULL COMMENT '后肩宽',
  `left_chest_height` float(6, 2) NULL DEFAULT NULL COMMENT '左胸高',
  `right_chest_height` float(6, 2) NULL DEFAULT NULL COMMENT '右胸高',
  `chest_width` float(6, 2) NULL DEFAULT NULL COMMENT '胸宽',
  `back_width` float(6, 2) NULL DEFAULT NULL COMMENT '背宽',
  `belly_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '肚围',
  `belly_circumference_height` float(6, 2) NULL DEFAULT NULL COMMENT '肚围高',
  `anterior_lumbar_section` float(6, 2) NULL DEFAULT NULL COMMENT '前腰节',
  `front_length` float(6, 2) NULL DEFAULT NULL COMMENT '前衣长',
  `back_length` float(6, 2) NULL DEFAULT NULL COMMENT '背长',
  `short_back_middle_length` float(6, 2) NULL DEFAULT NULL COMMENT '后中长(短款)',
  `long_back_middle_length` float(6, 2) NULL DEFAULT NULL COMMENT '后中长(长款)',
  `left_arm_root_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '左臂根围',
  `right_arm_root_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '右臂根围',
  `left_arm_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '左大臂围',
  `right_arm_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '右大臂围',
  `left_elbow` float(6, 2) NULL DEFAULT NULL COMMENT '左肘围',
  `right_elbow` float(6, 2) NULL DEFAULT NULL COMMENT '右肘围',
  `left_middle_arm_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '左中臂围',
  `right_middle_arm_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '右中臂围',
  `left_wrist_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '左手腕围',
  `right_wrist_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '右手腕围',
  `left_shoulder_sleeve_length` float(6, 2) NULL DEFAULT NULL COMMENT '左肩袖长',
  `right_shoulder_sleeve_length` float(6, 2) NULL DEFAULT NULL COMMENT '右肩袖长',
  `left_rise` float(6, 2) NULL DEFAULT NULL COMMENT '左立裆',
  `right_rise` float(6, 2) NULL DEFAULT NULL COMMENT '右立裆',
  `left_thigh_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '左大腿围',
  `right_thigh_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '右大腿围',
  `left_knee_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '左膝盖围',
  `right_knee_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '右膝盖围',
  `left_calf_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '左小腿围',
  `right_calf_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '右小腿围',
  `left_ankle_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '左脚腕围',
  `right_ankle_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '右脚腕围',
  `left_pant_length` float(6, 2) NULL DEFAULT NULL COMMENT '左裤长',
  `right_pant_length` float(6, 2) NULL DEFAULT NULL COMMENT '右裤长',
  `field1` float(6, 2) NULL DEFAULT NULL COMMENT '预留字段1',
  `field2` float(6, 2) NULL DEFAULT NULL COMMENT '预留字段2',
  `field3` float(6, 2) NULL DEFAULT NULL COMMENT '预留字段3',
  `field4` float(6, 2) NULL DEFAULT NULL COMMENT '预留字段4',
  `field5` float(6, 2) NULL DEFAULT NULL COMMENT '预留字段5',
  `field6` float(6, 2) NULL DEFAULT NULL COMMENT '预留字段6',
  `field7` float(6, 2) NULL DEFAULT NULL COMMENT '预留字段7',
  `field8` float(6, 2) NULL DEFAULT NULL COMMENT '预留字段8',
  `field9` float(6, 2) NULL DEFAULT NULL COMMENT '预留字段9',
  `field10` float(6, 2) NULL DEFAULT NULL COMMENT '预留字段10',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`pilot_id`, `is_deleted`, `created_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pilot_body
-- ----------------------------

-- ----------------------------
-- Table structure for scan
-- ----------------------------
DROP TABLE IF EXISTS `scan`;
CREATE TABLE `scan`  (
  `pilot_id` bigint(16) UNSIGNED NOT NULL COMMENT 'id',
  `scan_time` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扫描时间',
  `scan_location` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扫描地点',
  `data_file_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名',
  `file_storage_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件存放地址',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`pilot_id`) USING BTREE,
  CONSTRAINT `scan` FOREIGN KEY (`pilot_id`) REFERENCES `pilot` (`pilot_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scan
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint(16) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份类型(0超级管理员1部门领导)',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'aa111111' COMMENT '登陆密码',
  `sex` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '性别(0男1女)',
  `card` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号码',
  `dept_id` bigint(16) UNSIGNED NOT NULL COMMENT '部门id',
  `position` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位',
  `job_title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user`(`dept_id`) USING BTREE,
  CONSTRAINT `user` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`dept_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '0', 'user0', 'aa111111', 0, '111111111111111111', 1, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
