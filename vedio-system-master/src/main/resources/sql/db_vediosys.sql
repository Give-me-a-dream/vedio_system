/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80028 (8.0.28)
 Source Host           : 127.0.0.1:3306
 Source Schema         : db_vediosys

 Target Server Type    : MySQL
 Target Server Version : 80028 (8.0.28)
 File Encoding         : 65001

 Date: 29/05/2023 21:22:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_browsing_history
-- ----------------------------
DROP TABLE IF EXISTS `t_browsing_history`;
CREATE TABLE `t_browsing_history`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `u_id` int NOT NULL,
  `vedio_id` int NOT NULL,
  `watch_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_BHistory_uId`(`u_id` ASC) USING BTREE,
  INDEX `FK_BHistory_vedio_id`(`vedio_id` ASC) USING BTREE,
  CONSTRAINT `FK_BHistory_uId` FOREIGN KEY (`u_id`) REFERENCES `t_user_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_BHistory_vedio_id` FOREIGN KEY (`vedio_id`) REFERENCES `t_vedio` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_comments
-- ----------------------------
DROP TABLE IF EXISTS `t_comments`;
CREATE TABLE `t_comments`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `vedio_id` int NOT NULL,
  `comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time` datetime NOT NULL,
  `likes` int NOT NULL DEFAULT 0,
  `unlikes` int NOT NULL DEFAULT 0,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_comments_vedioId`(`vedio_id` ASC) USING BTREE,
  CONSTRAINT `FK_comments_vedioId` FOREIGN KEY (`vedio_id`) REFERENCES `t_vedio` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_favorites
-- ----------------------------
DROP TABLE IF EXISTS `t_favorites`;
CREATE TABLE `t_favorites`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `u_id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_favorites_uid`(`u_id` ASC) USING BTREE,
  CONSTRAINT `FK_favorites_uid` FOREIGN KEY (`u_id`) REFERENCES `t_user_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_favorites_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_favorites_detail`;
CREATE TABLE `t_favorites_detail`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `favorites_id` int NOT NULL,
  `vedio_id` int NOT NULL,
  `add_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_favoretes_id`(`favorites_id` ASC) USING BTREE,
  INDEX `FK_theFavorete_vedioId`(`vedio_id` ASC) USING BTREE,
  CONSTRAINT `FK_favoretes_id` FOREIGN KEY (`favorites_id`) REFERENCES `t_favorites` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_theFavorete_vedioId` FOREIGN KEY (`vedio_id`) REFERENCES `t_vedio` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_region
-- ----------------------------
DROP TABLE IF EXISTS `t_region`;
CREATE TABLE `t_region`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `prinvince_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `introduce` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sort
-- ----------------------------
DROP TABLE IF EXISTS `t_sort`;
CREATE TABLE `t_sort`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `introduce` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_user_detail`;
CREATE TABLE `t_user_detail`  (
  `id` int NOT NULL,
  `realname` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `u_id` FOREIGN KEY (`id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_vedio
-- ----------------------------
DROP TABLE IF EXISTS `t_vedio`;
CREATE TABLE `t_vedio`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `v_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `uploader_id` int NULL DEFAULT NULL,
  `uploader_time` datetime NULL DEFAULT NULL,
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `plays` int NULL DEFAULT 0,
  `likes` int NULL DEFAULT 0,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面地址',
  `region_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_region_id`(`region_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_vedio_sorts
-- ----------------------------
DROP TABLE IF EXISTS `t_vedio_sorts`;
CREATE TABLE `t_vedio_sorts`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `vedio_id` int NOT NULL,
  `sort_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_vedio_id`(`vedio_id` ASC) USING BTREE,
  INDEX `FK_sort_id`(`sort_id` ASC) USING BTREE,
  CONSTRAINT `FK_sort_id` FOREIGN KEY (`sort_id`) REFERENCES `t_sort` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_vedio_id` FOREIGN KEY (`vedio_id`) REFERENCES `t_vedio` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
