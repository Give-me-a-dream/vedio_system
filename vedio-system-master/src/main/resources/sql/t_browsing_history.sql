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

 Date: 31/05/2023 14:32:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_browsing_history_copy1
-- ----------------------------
DROP TABLE IF EXISTS `t_browsing_history_copy1`;
CREATE TABLE `t_browsing_history_copy1`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `u_id` int NOT NULL,
  `vedio_id` int NOT NULL,
  `watch_time` datetime NOT NULL,
  `current_time` double NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_BHistory_uId`(`u_id` ASC) USING BTREE,
  INDEX `FK_BHistory_vedio_id`(`vedio_id` ASC) USING BTREE,
  CONSTRAINT `t_browsing_history_copy1_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `t_user_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_browsing_history_copy1_ibfk_2` FOREIGN KEY (`vedio_id`) REFERENCES `t_vedio` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
