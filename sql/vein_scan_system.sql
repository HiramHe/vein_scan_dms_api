/*
 Navicat Premium Data Transfer

 Source Server         : aliyun-hhm-docker-mysql3306
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 39.106.68.204:3306
 Source Schema         : vein_scan_system

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 25/06/2020 16:07:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for b_ultrasound
-- ----------------------------
DROP TABLE IF EXISTS `b_ultrasound`;
CREATE TABLE `b_ultrasound`  (
  `b_scan_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'B超id',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图像路径',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标志（0正常 1删除）',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modify` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` tinyint(1) NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`b_scan_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for infrared_description
-- ----------------------------
DROP TABLE IF EXISTS `infrared_description`;
CREATE TABLE `infrared_description`  (
  `description_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '描述id',
  `infrared_id` bigint(20) NOT NULL COMMENT '红外图像id',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '具体描述',
  `severity_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '严重等级',
  `x_coordinate` bigint(20) NULL DEFAULT NULL COMMENT 'x坐标',
  `y_coordinate` bigint(20) NULL DEFAULT NULL COMMENT 'y坐标',
  `b_scan_id` bigint(20) NULL DEFAULT NULL COMMENT 'B超图像id',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标志（0正常 1删除）',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modify` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` bigint(1) NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`description_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for infrared_image
-- ----------------------------
DROP TABLE IF EXISTS `infrared_image`;
CREATE TABLE `infrared_image`  (
  `infrared_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '红外图像id',
  `perspective` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视角',
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件名（带扩展）',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图像存储路径',
  `scan_time` datetime(0) NULL DEFAULT NULL COMMENT '扫描时间',
  `patient_id` bigint(20) NOT NULL COMMENT '患者id',
  `user_id` bigint(20) NOT NULL COMMENT '系统用户id',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标志（0正常 1删除）',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modify` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` tinyint(1) NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`infrared_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of infrared_image
-- ----------------------------
INSERT INTO `infrared_image` VALUES (1, NULL, 'test.png', NULL, NULL, 2, 6, 0, '2020-05-21 02:15:45', '2020-05-21 02:15:45', 1);

-- ----------------------------
-- Table structure for medical_department
-- ----------------------------
DROP TABLE IF EXISTS `medical_department`;
CREATE TABLE `medical_department`  (
  `department_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '科室编号',
  PRIMARY KEY (`department_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `menu_name_zh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单中文名',
  `menu_name_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单英文名',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单id',
  `visible` tinyint(1) NULL DEFAULT 0 COMMENT '菜单状态（0正常 1隐藏）',
  `requireAuth` tinyint(1) NULL DEFAULT 0 COMMENT '是否需要授权（0否 1是）',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由路径',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件名称',
  `hidden` tinyint(1) NULL DEFAULT 0 COMMENT '是否隐藏路由（0否 1是）',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '重定向到',
  `keepAlive` tinyint(1) NULL DEFAULT 1 COMMENT '是否缓存组件（0否 1是）',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '禁用标志（0禁用 1正常）',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标志（0正常 1删除）',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modify` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` bigint(20) NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for outpatient_medical_record
-- ----------------------------
DROP TABLE IF EXISTS `outpatient_medical_record`;
CREATE TABLE `outpatient_medical_record`  (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '门诊病历id',
  `patient_id` bigint(20) NOT NULL COMMENT '患者id',
  `department_id` int(255) NULL DEFAULT NULL COMMENT '科室id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '医生id',
  `chief_complaint` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '主诉',
  `hpi` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '现病史',
  `past_history` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '既往史',
  `physical_examination` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '体检',
  `diagnosis` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '诊断',
  `processing` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '处理',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志（0正常 1删除）',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modify` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` bigint(20) NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient`  (
  `patient_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '患者id',
  `patient_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '患者姓名',
  `sex` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `phone_number` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话号码',
  `enabled` tinyint(1) NOT NULL DEFAULT 0 COMMENT '禁用标志（0禁用，1正常）',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志（0正常，1删除）',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modify` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` bigint(20) NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`patient_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES (2, '张三', '男', NULL, NULL, NULL, 0, NULL, 0, '2020-04-27 09:17:19', '2020-04-27 09:17:45', 1);
INSERT INTO `patient` VALUES (3, '李四', '女', NULL, NULL, NULL, 0, NULL, 0, '2020-04-27 09:17:24', '2020-04-27 09:17:50', 1);
INSERT INTO `patient` VALUES (4, '王五', NULL, NULL, NULL, NULL, 0, NULL, 0, '2020-04-27 09:17:29', '2020-04-27 09:17:29', 1);
INSERT INTO `patient` VALUES (5, '赵六', NULL, NULL, NULL, NULL, 0, NULL, 0, '2020-04-27 09:17:34', '2020-04-27 09:17:34', 1);
INSERT INTO `patient` VALUES (6, 'tom', NULL, NULL, NULL, NULL, 0, NULL, 0, '2020-04-27 09:17:39', '2020-04-27 09:17:39', 1);

-- ----------------------------
-- Table structure for patient_treatment
-- ----------------------------
DROP TABLE IF EXISTS `patient_treatment`;
CREATE TABLE `patient_treatment`  (
  `patient_id` bigint(20) NOT NULL COMMENT '患者id',
  `treatment_id` bigint(20) NOT NULL COMMENT '治疗id',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标志（0正常 1删除）',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modify` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` bigint(20) NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`patient_id`, `treatment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name_zh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色中文名',
  `role_name_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '角色英文名',
  `enabled` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '禁用标志（0禁用 1正常）',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '删除标志（0正常 1删除）',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modify` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` bigint(20) NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (2, NULL, 'ROLE_ADMIN', 0, '', 0, '2020-05-07 06:21:42', '2020-06-19 09:10:53', 1);
INSERT INTO `role` VALUES (4, NULL, 'ROLE_COMMON', 1, '', 0, '2020-05-07 11:01:25', '2020-05-07 11:11:51', 1);
INSERT INTO `role` VALUES (5, NULL, 'ROLE_TEST', 1, '', 0, '2020-05-07 11:01:33', '2020-05-07 11:11:23', 1);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单id',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modify` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` bigint(20) NULL DEFAULT 1 COMMENT '乐观锁',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标志（0正常 1删除）',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for treatment
-- ----------------------------
DROP TABLE IF EXISTS `treatment`;
CREATE TABLE `treatment`  (
  `treatment_id` bigint(20) NOT NULL COMMENT '治疗方案id',
  `treatment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '方案详情',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标志（0正常 1删除）',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modify` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` bigint(20) NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`treatment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '昵称',
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '真实姓名',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '电子邮箱',
  `phone_number` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '电话号码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '头像地址',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `enabled` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '禁用标志（0禁用 1正常）',
  `deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志（0正常 1删除）',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modify` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` bigint(20) NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (4, '吴华超', '123', '', '', '男', NULL, '', '', '', NULL, 1, 0, '2020-04-27 09:13:19', '2020-05-04 08:31:37', 1);
INSERT INTO `user` VALUES (5, '何海明', '1234', '', '', '男', NULL, '', '', '', NULL, 1, 0, '2020-04-27 09:13:26', '2020-05-04 08:31:39', 1);
INSERT INTO `user` VALUES (6, 'Hiram', '$2a$10$lEc2FeH1FqIQdjjMW8GRae.lL.FnA1uNx5oD4Lbmn4QmoLD0ajdlK', '', '', NULL, NULL, '', '', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '明文密码：12345', 1, 0, '2020-04-27 09:13:32', '2020-05-19 06:40:10', 1);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '系统用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标志（0正常 1删除）',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modify` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` bigint(20) NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (6, 2, 0, '2020-05-07 06:22:32', '2020-05-07 08:21:24', 1);
INSERT INTO `user_role` VALUES (6, 4, 0, '2020-05-07 11:02:14', '2020-05-07 11:02:14', 1);
INSERT INTO `user_role` VALUES (6, 5, 0, '2020-05-07 11:02:19', '2020-05-07 11:02:19', 1);

-- ----------------------------
-- Table structure for user_treatment
-- ----------------------------
DROP TABLE IF EXISTS `user_treatment`;
CREATE TABLE `user_treatment`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `treatment_id` bigint(20) NOT NULL COMMENT '治疗方案id',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标志（0正常 1删除）',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modify` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` bigint(20) NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`user_id`, `treatment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
