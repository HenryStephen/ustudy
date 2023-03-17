/*
 Navicat Premium Data Transfer

 Source Server         : Aliyun
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : aliyun.yeli.io:4001
 Source Schema         : ustudy

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 21/09/2020 09:35:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ad_banner
-- ----------------------------
DROP TABLE IF EXISTS `ad_banner`;
CREATE TABLE `ad_banner` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '显示图片',
  `course_id` bigint unsigned DEFAULT NULL,
  `link` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `ad_banner_ibfk_1` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of ad_banner
-- ----------------------------
BEGIN;
INSERT INTO `ad_banner` VALUES (1, '就业班', '/ad_banner/2007/QuanGuo/F7F2019657532ORB.jpg', NULL, 'http://xue.ujiuye.com/zt/2020zt/xthz/', 0);
INSERT INTO `ad_banner` VALUES (2, '商家短视频', '/ad_anner/2008/QuanGuo/F8E2145460020JGF.jpg', NULL, 'http://xue.ujiuye.com/class-167859/?scode=8cQBrrglRx-wz', 0);
INSERT INTO `ad_banner` VALUES (3, '优学APP', '/ad_banner/1911/QuanGuo/EB110319475096V7.jpg', NULL, 'http://xue.ujiuye.com/zt/ityxapp/', 0);
INSERT INTO `ad_banner` VALUES (4, '设计师', '/ad_banner/2005/QuanGuo/F5J545534512ASRU.jpg', NULL, 'http://xue.ujiuye.com/class-141128/?scode=8cQBrrglRx-wz', 0);
COMMIT;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程名称',
  `preview_picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '预览图',
  `is_free` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '免费',
  `original_price` decimal(10,2) unsigned DEFAULT NULL COMMENT '原价',
  `present_price` decimal(10,2) unsigned NOT NULL COMMENT '现价',
  `category_id` bigint unsigned NOT NULL COMMENT '分类id',
  `subject_id` bigint NOT NULL COMMENT '科目id',
  `teaching_methods` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '授课方式（用+间隔） 1录播 2直播 3面授',
  `published_time` date NOT NULL COMMENT '推出时间',
  `students_number` int unsigned NOT NULL DEFAULT '0' COMMENT '学习人数',
  `valid_days` int unsigned DEFAULT NULL COMMENT '有效期',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系方式',
  `class_hour` int unsigned NOT NULL COMMENT '课时',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of course
-- ----------------------------
BEGIN;
INSERT INTO `course` VALUES (1, '【直播】淘宝商家短视频转化实操 布局公私域流量', '/course_preview/F8B1953308568YG4.jpg', 1, 200.00, 0.00, 1, 1001, '1', '2020-08-22', 1, 12, NULL, 80, 0);
INSERT INTO `course` VALUES (2, '【基础】微信视频号运营技巧 短视频赚钱新风口', '/course_preview/F7N1235351504UMS.jpg', 0, 100.00, 1.00, 1, 1003, '1', '2020-08-31', 201, 730, NULL, 1, 0);
INSERT INTO `course` VALUES (3, '【进阶】【直播】抖音自媒体电商+私域流量变现', '/course_preview/F682091147347RZY.jpg', 0, 339900.00, 239900.00, 1, 1003, '2', '2020-08-31', 104, 730, NULL, 16, 0);
INSERT INTO `course` VALUES (4, '【进阶】【直播+录播】电商运营与创业实战训练营', '/course_preview/F8C2050112761PTT.jpg', 0, 3999900.00, 2999900.00, 1, 1004, '2', '2020-08-31', 102, 730, NULL, 35, 0);
INSERT INTO `course` VALUES (5, '【直播+录播】SEM与信息流精准投放-策略班', '/course_preview/F561019341759NPY.jpg', 0, 499900.00, 399900.00, 1, 1002, '2', '2020-08-31', 102, 730, NULL, 34, 0);
INSERT INTO `course` VALUES (6, '【直播】淘宝商家短视频转化实操 布局公私域流量', '/course_preview/F8B1953308568YG4.jpg', 0, 1990.00, 990.00, 1, 1004, '2', '2020-08-31', 191, 730, NULL, 10, 0);
INSERT INTO `course` VALUES (7, '【直播+录播】SEO与网站运营实战-网站建设与优化实操', '/course_preview/F77808376452UR70.jpg', 0, 399900.00, 299900.00, 1, 1001, '2', '2020-08-31', 223, 730, NULL, 56, 0);
INSERT INTO `course` VALUES (8, '【系统】全域电商运营在线就业班', '/course_preview/F6I87435883588OS.jpg', 0, 2176000.00, 1176000.00, 1, 1004, '2', '2020-08-31', 0, 730, NULL, 107, 0);
COMMIT;

-- ----------------------------
-- Table structure for course_category
-- ----------------------------
DROP TABLE IF EXISTS `course_category`;
CREATE TABLE `course_category` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类或科目 名称',
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '等级',
  `parent_id` bigint DEFAULT NULL COMMENT '父id',
  `sorted` int unsigned DEFAULT NULL COMMENT '排序规则',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1045 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of course_category
-- ----------------------------
BEGIN;
INSERT INTO `course_category` VALUES (1, '互联网营销', '0', 0, 1, 0);
INSERT INTO `course_category` VALUES (2, 'UI/UE交互设计', '0', 0, 2, 0);
INSERT INTO `course_category` VALUES (3, 'Web前端', '0', 0, 3, 0);
INSERT INTO `course_category` VALUES (4, '程序开发', '0', 0, 4, 0);
INSERT INTO `course_category` VALUES (5, '三维可视化设计', '0', 0, 5, 0);
INSERT INTO `course_category` VALUES (6, 'office', '0', 0, 6, 0);
INSERT INTO `course_category` VALUES (7, '计算机二级', '0', 0, 7, 0);
INSERT INTO `course_category` VALUES (8, '计算机软考', '0', 0, 8, 0);
INSERT INTO `course_category` VALUES (9, 'PMP认证', '0', 0, 9, 0);
INSERT INTO `course_category` VALUES (10, '其他', '0', 0, 10, 0);
INSERT INTO `course_category` VALUES (1001, 'SEO', '1', 1, 2, 0);
INSERT INTO `course_category` VALUES (1002, 'SEM', '1', 1, 1, 0);
INSERT INTO `course_category` VALUES (1003, '新媒体营销', '1', 1, 3, 0);
INSERT INTO `course_category` VALUES (1004, '电商运营', '1', 1, 4, 0);
INSERT INTO `course_category` VALUES (1005, 'UI/UE交互设计', '1', 2, 1, 0);
INSERT INTO `course_category` VALUES (1006, '移动UI设计', '1', 2, 2, 0);
INSERT INTO `course_category` VALUES (1007, '平面设计', '1', 2, 3, 0);
INSERT INTO `course_category` VALUES (1008, '软件基础', '1', 2, 4, 0);
INSERT INTO `course_category` VALUES (1009, '系统', '1', 3, 1, 0);
INSERT INTO `course_category` VALUES (1010, 'HTML+CSS', '1', 3, 2, 0);
INSERT INTO `course_category` VALUES (1011, 'JavaScript', '1', 3, 3, 0);
INSERT INTO `course_category` VALUES (1012, 'Angular JS4', '1', 3, 4, 0);
INSERT INTO `course_category` VALUES (1013, 'Node', '1', 3, 5, 0);
INSERT INTO `course_category` VALUES (1014, 'vue', '1', 3, 6, 0);
INSERT INTO `course_category` VALUES (1015, 'React', '1', 3, 7, 0);
INSERT INTO `course_category` VALUES (1016, '小程序', '1', 3, 8, 0);
INSERT INTO `course_category` VALUES (1017, '其他', '1', 3, 9, 0);
INSERT INTO `course_category` VALUES (1018, 'Python+人工智能', '1', 4, 1, 0);
INSERT INTO `course_category` VALUES (1019, 'Java', '1', 4, 2, 0);
INSERT INTO `course_category` VALUES (1020, '软件测试', '1', 4, 3, 0);
INSERT INTO `course_category` VALUES (1021, 'Linux云计算', '1', 4, 4, 0);
INSERT INTO `course_category` VALUES (1022, 'Unity游戏开发+VR/AR', '1', 4, 5, 0);
INSERT INTO `course_category` VALUES (1023, '大数据开发', '1', 4, 6, 0);
INSERT INTO `course_category` VALUES (1024, '网络安全', '1', 4, 7, 0);
INSERT INTO `course_category` VALUES (1025, 'PHP', '1', 4, 8, 0);
INSERT INTO `course_category` VALUES (1026, '三维可视化设计', '1', 5, 1, 0);
INSERT INTO `course_category` VALUES (1027, 'PPT', '1', 6, 1, 0);
INSERT INTO `course_category` VALUES (1028, 'Word', '1', 6, 2, 0);
INSERT INTO `course_category` VALUES (1029, 'Excle', '1', 6, 3, 0);
INSERT INTO `course_category` VALUES (1030, '公共基础知识', '1', 7, 1, 0);
INSERT INTO `course_category` VALUES (1031, 'Office', '1', 7, 2, 0);
INSERT INTO `course_category` VALUES (1032, 'C语言', '1', 7, 3, 0);
INSERT INTO `course_category` VALUES (1033, 'C++', '1', 7, 4, 0);
INSERT INTO `course_category` VALUES (1034, 'Python', '1', 7, 5, 0);
INSERT INTO `course_category` VALUES (1035, 'Access', '1', 7, 6, 0);
INSERT INTO `course_category` VALUES (1036, 'VB', '1', 7, 7, 0);
INSERT INTO `course_category` VALUES (1037, '线下面授', '1', 7, 8, 0);
INSERT INTO `course_category` VALUES (1038, '软考初级', '1', 8, 1, 0);
INSERT INTO `course_category` VALUES (1039, '软考中级', '1', 8, 2, 0);
INSERT INTO `course_category` VALUES (1040, '软考高级', '1', 8, 3, 0);
INSERT INTO `course_category` VALUES (1041, 'PMP直播', '1', 9, 1, 0);
INSERT INTO `course_category` VALUES (1042, '项目管理PMP认证', '1', 9, 2, 0);
INSERT INTO `course_category` VALUES (1043, 'BIM考试', '1', 10, 1, 0);
INSERT INTO `course_category` VALUES (1044, '求职面试', '1', 10, 2, 0);
COMMIT;

-- ----------------------------
-- Table structure for course_comment
-- ----------------------------
DROP TABLE IF EXISTS `course_comment`;
CREATE TABLE `course_comment` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned DEFAULT NULL,
  `course_id` bigint unsigned NOT NULL,
  `stars` int unsigned NOT NULL COMMENT '评价星级',
  `time` datetime NOT NULL COMMENT '评论时间',
  `comment` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `course_comment_ibfk_1` (`user_id`),
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of course_comment
-- ----------------------------
BEGIN;
INSERT INTO `course_comment` VALUES (1, 1, 1, 5, '2020-09-14 11:23:19', 'test comment', 0);
COMMIT;

-- ----------------------------
-- Table structure for course_description
-- ----------------------------
DROP TABLE IF EXISTS `course_description`;
CREATE TABLE `course_description` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `course_id` bigint unsigned NOT NULL,
  `number` int unsigned DEFAULT NULL COMMENT '介绍序号',
  `type` int unsigned NOT NULL COMMENT '类型：文字或者图片',
  `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '介绍内容',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54564541564564569 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of course_description
-- ----------------------------
BEGIN;
INSERT INTO `course_description` VALUES (1, 1, 1, 1, '介绍内容1', 0);
INSERT INTO `course_description` VALUES (2, 1, 2, 1, '介绍内容2', 0);
COMMIT;

-- ----------------------------
-- Table structure for course_schedule
-- ----------------------------
DROP TABLE IF EXISTS `course_schedule`;
CREATE TABLE `course_schedule` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `course_id` bigint unsigned NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '章节或第几讲名称',
  `level` int unsigned NOT NULL COMMENT '类型（0代表章节；1代表讲）',
  `parent_id` bigint unsigned DEFAULT NULL COMMENT '章节无父id',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '授课方式（直播、面授、视频、资料）',
  `time` datetime DEFAULT NULL COMMENT '开始时间（直播、面授）',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `parent_id` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of course_schedule
-- ----------------------------
BEGIN;
INSERT INTO `course_schedule` VALUES (1, 1, '第一章', 1, NULL, NULL, NULL, 0);
INSERT INTO `course_schedule` VALUES (2, 1, '第一节', 2, 1, '1', NULL, 0);
INSERT INTO `course_schedule` VALUES (3, 1, '第二章', 1, NULL, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for favorites
-- ----------------------------
DROP TABLE IF EXISTS `favorites`;
CREATE TABLE `favorites` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `course_id` bigint unsigned NOT NULL,
  `user_id` bigint unsigned NOT NULL,
  `type` int unsigned NOT NULL COMMENT '购物车（0）或收藏车（1）',
  `added_time` datetime NOT NULL COMMENT '添加时间',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of favorites
-- ----------------------------
BEGIN;
INSERT INTO `favorites` VALUES (1, 13, 1, 1, '2020-09-14 11:25:37', 1);
INSERT INTO `favorites` VALUES (2, 14, 1, 1, '2020-09-14 11:25:55', 1);
INSERT INTO `favorites` VALUES (5, 13, 1, 1, '2020-09-14 15:02:46', 1);
INSERT INTO `favorites` VALUES (7, 1, 1, 1, '2020-09-18 09:31:57', 1);
INSERT INTO `favorites` VALUES (8, 13, 1, 0, '2020-09-18 09:33:04', 1);
INSERT INTO `favorites` VALUES (9, 14, 1, 0, '2020-09-18 09:33:07', 1);
INSERT INTO `favorites` VALUES (10, 1, 1, 1, '2020-09-18 09:33:46', 0);
INSERT INTO `favorites` VALUES (11, 1, 1, 0, '2020-09-18 09:34:32', 1);
INSERT INTO `favorites` VALUES (12, 13, 1, 1, '2020-09-20 23:49:52', 0);
INSERT INTO `favorites` VALUES (13, 14, 1, 1, '2020-09-20 23:50:25', 0);
INSERT INTO `favorites` VALUES (14, 1, 1, 0, '2020-09-21 08:20:07', 1);
INSERT INTO `favorites` VALUES (15, 13, 1, 0, '2020-09-21 08:20:10', 1);
INSERT INTO `favorites` VALUES (16, 14, 1, 0, '2020-09-21 08:22:07', 1);
INSERT INTO `favorites` VALUES (17, 1, 1, 0, '2020-09-21 08:58:52', 1);
INSERT INTO `favorites` VALUES (18, 13, 1, 0, '2020-09-21 08:58:55', 1);
INSERT INTO `favorites` VALUES (19, 14, 1, 0, '2020-09-21 08:58:58', 1);
INSERT INTO `favorites` VALUES (20, 14, 1, 0, '2020-09-21 09:03:07', 1);
INSERT INTO `favorites` VALUES (21, 1, 1, 0, '2020-09-21 09:03:11', 0);
INSERT INTO `favorites` VALUES (22, 13, 1, 0, '2020-09-21 09:10:43', 0);
INSERT INTO `favorites` VALUES (23, 14, 1, 0, '2020-09-21 09:10:46', 0);
COMMIT;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `course_id` bigint unsigned DEFAULT NULL COMMENT '课程id',
  `user_id` bigint unsigned DEFAULT NULL COMMENT '上传用户id',
  `schedule_id` bigint unsigned DEFAULT NULL COMMENT '课时安排id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件类型',
  `duration` int unsigned DEFAULT NULL COMMENT '时长  /秒（视频时长）',
  `file_size` bigint DEFAULT NULL COMMENT '文件大小  /bytes',
  `time` datetime DEFAULT NULL COMMENT '上传时间',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `user_id` (`user_id`),
  KEY `schedule_id` (`schedule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of file
-- ----------------------------
BEGIN;
INSERT INTO `file` VALUES ('6717ea94-d848-4926-8c50-c48fed176cd2', 1, 1, NULL, '1.cpp', 'avatar', NULL, 247, '2020-09-18 10:41:27', 0);
INSERT INTO `file` VALUES ('746c0fb1-ec62-49bf-8648-078dc88ec959', 1, 1, NULL, '1.cpp', '1', NULL, 247, '2020-09-14 10:46:08', 0);
INSERT INTO `file` VALUES ('9bfcbab6-cd14-4f75-8667-f4298afc8e5e', 1, 1, NULL, '1.cpp', 'avatar', NULL, 247, '2020-09-18 10:42:50', 0);
INSERT INTO `file` VALUES ('b2fa76a0-e705-4924-abd3-eb8c36c41def', 2, 1, NULL, '2.cpp', 'video', NULL, 366, '2020-09-18 09:26:24', 0);
COMMIT;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` bigint unsigned NOT NULL,
  `submit_time` datetime NOT NULL COMMENT '提交时间',
  `is_paid` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '已支付',
  `payment_time` datetime DEFAULT NULL COMMENT '付款时间',
  `payment_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '付款方式',
  `discount` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '优惠金额',
  `total` decimal(10,2) NOT NULL COMMENT '优惠之后的total',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of order
-- ----------------------------
BEGIN;
INSERT INTO `order` VALUES ('93748921-a491-4ee2-ac17-795b34dba0cc', 1, '2020-09-16 09:11:58', 1, '2020-09-16 09:15:17', 'Alipay', 0.00, 20.00, 0);
COMMIT;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `order_id` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `course_id` bigint unsigned NOT NULL,
  `course_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '课程价钱',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of order_item
-- ----------------------------
BEGIN;
INSERT INTO `order_item` VALUES (16, '93748921-a491-4ee2-ac17-795b34dba0cc', 1, 20.00, 0);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `profile_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像链接',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '真实名字',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '城市',
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, NULL, '$2a$10$TxQgwdq92nQYMcoFK/m6aO1rUVCieU6HWrqt9rtjNIjEuhfDsvlz6', 'tang', '唐', '2020-09-06', NULL, '15645740000', 0);
INSERT INTO `user` VALUES (4, NULL, '$2a$10$1I4fnkZEk6SBYngFrAShvOoJVZm2OLYSYhXVjMM3ug.6kmtS2s1je', 'zhang3', NULL, NULL, NULL, NULL, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
