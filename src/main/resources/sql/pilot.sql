/*
 Navicat Premium Data Transfer

 Source Server         : HuaWeiCloudServerMysql
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : 114.116.243.4:3306
 Source Schema         : pilot

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 02/03/2021 15:59:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for addition_field
-- ----------------------------
DROP TABLE IF EXISTS `addition_field`;
CREATE TABLE `addition_field`  (
  `id` bigint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `dept_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名',
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
  `pilot_name` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` tinyint(1) UNSIGNED NULL DEFAULT NULL COMMENT '性别(0男1女)',
  `card` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `dept_id` bigint(16) UNSIGNED NULL DEFAULT NULL COMMENT '部门id',
  `position` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务',
  `job_title` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`pilot_id`) USING BTREE,
  INDEX `pilot`(`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pilot_body
-- ----------------------------
DROP TABLE IF EXISTS `pilot_body`;
CREATE TABLE `pilot_body`  (
  `pilot_id` bigint(16) UNSIGNED NOT NULL COMMENT '飞行员id',
  `is_deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除(1表示删除0表示未删除)',
  `height` float(6, 2) NULL DEFAULT NULL COMMENT '身高',
  `weight` float(6, 2) NULL DEFAULT NULL COMMENT '体重',
  `neck_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '颈围',
  `neck_root_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '颈根围',
  `left_shoulder_length` float(6, 2) NULL DEFAULT NULL COMMENT '左肩长',
  `right_shoulder_length` float(6, 2) NULL DEFAULT NULL COMMENT '右肩长',
  `shoulder_width` float(6, 2) NULL DEFAULT NULL COMMENT '总肩宽',
  `back_width` float(6, 2) NULL DEFAULT NULL COMMENT '背宽',
  `bust_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '胸围',
  `breast_width` float(6, 2) NULL DEFAULT NULL COMMENT '乳间宽',
  `under_bust_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '下胸围',
  `waist_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '腰围',
  `hips_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '臀围',
  `upper_left_arm_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '左上臂围',
  `upper_right_arm_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '右上臂围',
  `left_wrist_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '左手腕围',
  `right_wrist_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '右手腕围',
  `left_thigh_root_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '左大腿根围',
  `right_thigh_root_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '右大腿根围',
  `left_mid_thigh_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '左大腿中部围',
  `right_mid_thigh_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '右大腿中部围',
  `left_knee_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '左膝围',
  `right_knee_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '右膝围',
  `left_calf_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '左腿肚围',
  `right_calf_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '右腿肚围',
  `upper_left_ankle_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '左踝上围',
  `upper_right_ankle_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '右踝上围',
  `left_foot_length` float(6, 2) NULL DEFAULT NULL COMMENT '左足长',
  `right_foot_length` float(6, 2) NULL DEFAULT NULL COMMENT '右足长',
  `torso_length` float(6, 2) NULL DEFAULT NULL COMMENT '躯干长',
  `waist_height` float(6, 2) NULL DEFAULT NULL COMMENT '腰围高',
  `hip_height` float(6, 2) NULL DEFAULT NULL COMMENT '臀围高',
  `straight_crotch` float(6, 2) NULL DEFAULT NULL COMMENT '直裆',
  `left_knee_circumference_height` float(6, 2) NULL DEFAULT NULL COMMENT '左膝围高',
  `right_knee_circumference_height` float(6, 2) NULL DEFAULT NULL COMMENT '右膝围高',
  `left_lateral_ankle_height` float(6, 2) NULL DEFAULT NULL COMMENT '左外踝高',
  `right_lateral_ankle_height` float(6, 2) NULL DEFAULT NULL COMMENT '右外踝高',
  `armpit_depth` float(6, 2) NULL DEFAULT NULL COMMENT '腋窝深',
  `back_waist_length` float(6, 2) NULL DEFAULT NULL COMMENT '背腰长',
  `cervical_vertebra_point_knee_bend_length` float(6, 2) NULL DEFAULT NULL COMMENT '颈椎点至膝弯长',
  `cervical_point_height` float(6, 2) NULL DEFAULT NULL COMMENT '颈椎点高',
  `left_neck_point_nipple_point_length` float(6, 2) NULL DEFAULT NULL COMMENT '左侧颈点至乳头点长',
  `right_neck_point_nipple_point_length` float(6, 2) NULL DEFAULT NULL COMMENT '右侧颈点至乳头点长',
  `left_front_waist_length` float(6, 2) NULL DEFAULT NULL COMMENT '左前腰长',
  `right_front_waist_length` float(6, 2) NULL DEFAULT NULL COMMENT '右前腰长',
  `left_waist_hip_length` float(6, 2) NULL DEFAULT NULL COMMENT '左腰至臀长',
  `right_waist_hip_length` float(6, 2) NULL DEFAULT NULL COMMENT '右腰至臀长',
  `trunk_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '躯干围',
  `perineum_upper_front_back_length` float(6, 2) NULL DEFAULT NULL COMMENT '会阴上部前后长',
  `left_forearm_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '左前臂围',
  `right_forearm_circumference` float(6, 2) NULL DEFAULT NULL COMMENT '右前臂围',
  `left_leg_outside_length` float(6, 2) NULL DEFAULT NULL COMMENT '左腿外侧长',
  `right_leg_outside_length` float(6, 2) NULL DEFAULT NULL COMMENT '右腿外侧长',
  `left_thigh_length` float(6, 2) NULL DEFAULT NULL COMMENT '左大腿长',
  `right_thigh_length` float(6, 2) NULL DEFAULT NULL COMMENT '右大腿长',
  `perineal_height` float(6, 2) NULL DEFAULT NULL COMMENT '会阴高',
  `left_shoulder_slope` float(6, 2) NULL DEFAULT NULL COMMENT '左肩斜度',
  `right_shoulder_slope` float(6, 2) NULL DEFAULT NULL COMMENT '右肩斜度',
  `left_upper_arm_length` float(6, 2) NULL DEFAULT NULL COMMENT '左上臂长',
  `right_upper_arm_length` float(6, 2) NULL DEFAULT NULL COMMENT '右上臂长',
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
  PRIMARY KEY (`pilot_id`, `is_deleted`) USING BTREE,
  CONSTRAINT `pilot_body` FOREIGN KEY (`pilot_id`) REFERENCES `pilot` (`pilot_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
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
  `scan_time` datetime(6) NULL DEFAULT NULL COMMENT '扫描时间',
  `scan_location` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扫描地点',
  `data_file_name` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '三维数据文件名',
  `file_storage_address` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件存放地址',
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
  `user_id` bigint(16) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户类型(0超级管理员1部门领导)',
  `user_name` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆密码',
  `sex` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '性别(0男1女2未知)',
  `card` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `dept_id` bigint(16) UNSIGNED NULL DEFAULT NULL COMMENT '部门id',
  `position` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门',
  `job_title` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user`(`dept_id`) USING BTREE,
  CONSTRAINT `user` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`dept_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
