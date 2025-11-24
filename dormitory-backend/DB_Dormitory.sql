/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : DB_Dormitory

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 08/01/2024 09:15:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for adjust_room
-- ----------------------------
DROP TABLE IF EXISTS `adjust_room`;
CREATE TABLE `adjust_room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `currentroom_id` int NOT NULL COMMENT '当前房间',
  `currentbed_id` int NOT NULL COMMENT '当前床位号',
  `towardsroom_id` int NOT NULL COMMENT '目标房间',
  `towardsbed_id` int NOT NULL COMMENT '目标床位号',
  `state` enum('未处理','通过','驳回') CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT '未处理' COMMENT '申请状态',
  `apply_time` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '申请时间',
  `finish_time` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of adjust_room
-- ----------------------------
BEGIN;
INSERT INTO `adjust_room` VALUES (2, 'stu1', '张三', 1101, 1, 1102, 4, '通过', '2022-02-17 14:35:02', '2022-03-19 23:07:21');
INSERT INTO `adjust_room` VALUES (5, 'stu2', '田田', 1101, 2, 1104, 1, '未处理', '2022-03-19 23:05:25', NULL);
INSERT INTO `adjust_room` VALUES (6, 'stu3', '吉安', 1101, 3, 1104, 2, '驳回', '2022-03-19 23:05:52', '2022-03-19 23:07:37');
INSERT INTO `adjust_room` VALUES (7, 'stu9', '德萨', 1103, 2, 1105, 1, '未处理', '2022-03-19 23:06:18', NULL);
INSERT INTO `adjust_room` VALUES (8, 'stu6', '泡泡', 1102, 2, 1104, 4, '未处理', '2022-03-19 23:06:52', NULL);
COMMIT;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `gender` enum('男','女') CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT '男' COMMENT '性别',
  `age` int NOT NULL COMMENT '年龄',
  `phone_num` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES ('admin', '123456', '大强', '男', 18, '14785412478', NULL, '8b2f049504164550918aa1972178e0e3.jpg');
COMMIT;

-- ----------------------------
-- Table structure for dorm_build
-- ----------------------------
DROP TABLE IF EXISTS `dorm_build`;
CREATE TABLE `dorm_build` (
  `dormbuild_id` int NOT NULL COMMENT '宿舍楼号码',
  `dormbuild_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '宿舍楼名称',
  `dormbuild_detail` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '宿舍楼备注',
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of dorm_build
-- ----------------------------
BEGIN;
INSERT INTO `dorm_build` VALUES (1, '一号楼', '男宿舍', 1);
INSERT INTO `dorm_build` VALUES (2, '二号楼', '女宿舍', 2);
INSERT INTO `dorm_build` VALUES (3, '三号楼', '男宿舍', 3);
INSERT INTO `dorm_build` VALUES (4, '四号楼', '女宿舍', 4);
COMMIT;

-- ----------------------------
-- Table structure for dorm_manager
-- ----------------------------
DROP TABLE IF EXISTS `dorm_manager`;
CREATE TABLE `dorm_manager` (
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT '123456' COMMENT '密码',
  `dormbuild_id` int NOT NULL COMMENT '所管理的宿舍楼栋号',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '名字',
  `gender` enum('男','女') CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT '男' COMMENT '性别',
  `age` int NOT NULL COMMENT '年龄',
  `phone_num` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of dorm_manager
-- ----------------------------
BEGIN;
INSERT INTO `dorm_manager` VALUES ('dorm1', '123456', 1, '张三', '男', 35, '15995917873', NULL, '3b3a4d92e240495b9a22c6716cb5761a.png');
INSERT INTO `dorm_manager` VALUES ('dorm2', '123456', 2, '李四', '女', 55, '15995917874', NULL, NULL);
INSERT INTO `dorm_manager` VALUES ('dorm3', '123456', 3, '王五', '男', 38, '15896875201', NULL, NULL);
INSERT INTO `dorm_manager` VALUES ('dorm4', '123456', 4, '赵花', '女', 40, '15877535247', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for dorm_room
-- ----------------------------
DROP TABLE IF EXISTS `dorm_room`;
CREATE TABLE `dorm_room` (
  `dormroom_id` int NOT NULL COMMENT '宿舍房间号',
  `dormbuild_id` int NOT NULL COMMENT '宿舍楼号',
  `floor_num` int NOT NULL COMMENT '楼层',
  `max_capacity` int NOT NULL DEFAULT '4' COMMENT '房间最大入住人数',
  `current_capacity` int NOT NULL DEFAULT '0' COMMENT '当前房间入住人数',
  `first_bed` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '一号床位',
  `second_bed` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '二号床位',
  `third_bed` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '三号床位',
  `fourth_bed` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '四号床位',
  PRIMARY KEY (`dormroom_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of dorm_room
-- ----------------------------
BEGIN;
INSERT INTO `dorm_room` VALUES (1101, 1, 1, 4, 3, NULL, 'stu2', 'stu3', 'stu4');
INSERT INTO `dorm_room` VALUES (1102, 1, 1, 4, 4, 'stu5', 'stu6', 'stu7', 'stu1');
INSERT INTO `dorm_room` VALUES (1103, 1, 1, 4, 4, 'stu8', 'stu9', 'stu10', 'stu11');
INSERT INTO `dorm_room` VALUES (1104, 1, 1, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (1105, 1, 1, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (1201, 1, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (1202, 1, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (1203, 1, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (1204, 1, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (1205, 1, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (1301, 1, 3, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (1302, 1, 3, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (1303, 1, 3, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (1304, 1, 3, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (1305, 1, 3, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (2101, 2, 1, 4, 3, 'stu12', 'stu13', 'stu14', NULL);
INSERT INTO `dorm_room` VALUES (2102, 2, 1, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (2103, 2, 1, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (2104, 2, 1, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (2105, 2, 1, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (2201, 2, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (2202, 2, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (2203, 2, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (2204, 2, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (2205, 2, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (2301, 2, 3, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (2302, 2, 3, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (2303, 2, 3, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (2304, 2, 3, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (2305, 2, 3, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (3101, 3, 1, 4, 3, 'stu15', 'stu16', 'stu16', NULL);
INSERT INTO `dorm_room` VALUES (3102, 3, 1, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (3103, 3, 1, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (3104, 3, 1, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (3105, 3, 1, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (3201, 3, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (3202, 3, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (3203, 3, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (3204, 3, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (3205, 3, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (3301, 3, 3, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (3302, 3, 3, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (3303, 3, 3, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (3304, 3, 3, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (3305, 3, 3, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (4101, 4, 1, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (4102, 4, 1, 4, 3, 'stu17', 'stu18', 'stu19', NULL);
INSERT INTO `dorm_room` VALUES (4103, 4, 1, 4, 1, 'stu20', NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (4104, 4, 1, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (4105, 4, 1, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (4201, 4, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (4202, 4, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (4203, 4, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (4204, 4, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (4205, 4, 2, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (4301, 4, 3, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (4302, 4, 3, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (4303, 4, 3, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (4304, 4, 3, 4, 0, NULL, NULL, NULL, NULL);
INSERT INTO `dorm_room` VALUES (4305, 4, 3, 4, 0, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '主题',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `author` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `release_time` datetime NOT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
BEGIN;
INSERT INTO `notice` VALUES (18, '杭州水果捞：欢迎各位使用', '<p>杭州水果捞：欢迎各位使用</p>', '大强', '2024-01-07 19:06:51');
INSERT INTO `notice` VALUES (19, '欢迎各位同学进入新宿舍', '<p>欢迎各位同学进入新宿舍! 大家根据自己需要选择合适的房位。</p>', '大强', '2024-01-07 19:12:57');
INSERT INTO `notice` VALUES (20, '需要保修设备的同学联系宿管！', '<p>需要保修设备的同学联系宿管！</p>', '大强', '2024-01-07 19:15:39');
INSERT INTO `notice` VALUES (21, '关于放假通知', '<p>元旦起，1.1~1.3放假三天。请同学们合理安排时间。</p>', '大强', '2024-01-07 19:16:26');
COMMIT;

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `repairer` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '报修人',
  `dormbuild_id` int NOT NULL COMMENT '报修宿舍楼',
  `dormroom_id` int NOT NULL COMMENT '报修宿舍房间号',
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '表单标题',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '表单内容',
  `state` enum('完成','未完成') CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT '未完成' COMMENT '订单状态（是否维修完成）',
  `order_buildtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `order_finishtime` datetime DEFAULT NULL COMMENT '订单完成时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1413525505 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of repair
-- ----------------------------
BEGIN;
INSERT INTO `repair` VALUES (-12455934, '张三', 1, 1101, '阳台漏水', '阳台使用时会漏水请来修理', '未完成', '2022-03-14 20:37:35', NULL);
INSERT INTO `repair` VALUES (1, '强强', 1, 1101, '水龙头损坏', '水龙头损坏，请来1-1101宿舍修理', '完成', '2022-01-11 22:52:24', '2022-02-17 14:35:02');
INSERT INTO `repair` VALUES (2, '七七', 1, 1101, '门把手损坏', '门把手损坏，请来修理', '完成', '2022-01-17 23:11:13', '2022-02-17 14:31:14');
INSERT INTO `repair` VALUES (3, '丽丽', 2, 2102, '水池损坏', '水池损坏，请来修理', '完成', '2022-01-17 23:10:35', '2022-02-15 16:16:15');
INSERT INTO `repair` VALUES (4, '贝贝', 1, 1102, '阳台漏水', '宿舍阳台漏水，速来修理', '完成', '2022-01-17 23:12:16', '2022-02-17 14:32:38');
INSERT INTO `repair` VALUES (5, '哈哈', 1, 1101, '窗台损坏', '宿舍窗台损坏，速来修理', '未完成', '2022-02-18 22:35:37', NULL);
INSERT INTO `repair` VALUES (6, '张三', 1, 1101, '浴室天花板漏水', '浴室天花板漏水', '完成', '2022-03-03 21:00:21', '2022-03-10 14:39:10');
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `password` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT '123456' COMMENT '密码',
  `age` int unsigned NOT NULL COMMENT '年龄',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `gender` enum('男','女') CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT '男' COMMENT '性别',
  `phone_num` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`username`) USING BTREE,
  UNIQUE KEY `stu_num` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES ('stu1', '123456', 18, '张三', '男', '15875696511', NULL, '3bdf6d409cf24cb581b1c7c8a0db0b72.jpg');
INSERT INTO `student` VALUES ('stu10', '123456', 19, '马克', '女', '15889635874', NULL, NULL);
INSERT INTO `student` VALUES ('stu11', '123456', 16, '巧巧', '女', '18978431781', NULL, NULL);
INSERT INTO `student` VALUES ('stu12', '123456', 17, '丽丽', '女', '17986573547', NULL, NULL);
INSERT INTO `student` VALUES ('stu13', '123456', 18, '美美', '女', '15896475354', NULL, NULL);
INSERT INTO `student` VALUES ('stu14', '123456', 20, '拉拉', '女', '14896527357', NULL, NULL);
INSERT INTO `student` VALUES ('stu15', '123456', 18, '贝贝', '男', '15896745351', NULL, NULL);
INSERT INTO `student` VALUES ('stu16', '123456', 18, '力力', '男', '14596475257', NULL, NULL);
INSERT INTO `student` VALUES ('stu17', '123456', 18, '阿成', '男', '15896542147', NULL, NULL);
INSERT INTO `student` VALUES ('stu18', '123456', 19, '阿达', '女', '14785635874', NULL, NULL);
INSERT INTO `student` VALUES ('stu19', '123456', 19, '帕森斯', '男', '15889658475', NULL, NULL);
INSERT INTO `student` VALUES ('stu2', '123456', 18, '田田', '男', '15875359641', NULL, NULL);
INSERT INTO `student` VALUES ('stu20', '123456', 21, '柠檬', '男', '15874563558', NULL, NULL);
INSERT INTO `student` VALUES ('stu21', '123456', 21, '面对', '男', '15889635874', NULL, NULL);
INSERT INTO `student` VALUES ('stu22', '123456', 25, '等等', '男', '15589963321', NULL, NULL);
INSERT INTO `student` VALUES ('stu3', '123456', 18, '吉安', '男', '15798657350', NULL, NULL);
INSERT INTO `student` VALUES ('stu4', '123456', 22, '力力', '男', '15878965874', NULL, NULL);
INSERT INTO `student` VALUES ('stu5', '123456', 19, '哦哦', '男', '15897535478', NULL, NULL);
INSERT INTO `student` VALUES ('stu6', '123456', 18, '泡泡', '男', '18987554765', NULL, NULL);
INSERT INTO `student` VALUES ('stu7', '123456', 15, '刚刚', '男', '15897543854', NULL, NULL);
INSERT INTO `student` VALUES ('stu8', '123456', 18, '七七', '男', '12332143215', NULL, NULL);
INSERT INTO `student` VALUES ('stu9', '123456', 20, '德萨', '男', '15889658741', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for visitor
-- ----------------------------
DROP TABLE IF EXISTS `visitor`;
CREATE TABLE `visitor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `gender` enum('男','女') CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT '男' COMMENT '性别',
  `phone_num` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `origin_city` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '来源城市',
  `visit_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '来访时间',
  `content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '事情',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of visitor
-- ----------------------------
BEGIN;
INSERT INTO `visitor` VALUES (1, '张三', '男', '14587896544', '武汉', '2022-02-21 17:34:52', '探访孩子');
INSERT INTO `visitor` VALUES (2, '李四', '女', '15774147563', '江苏', '2022-01-24 17:08:06', '运送快递');
INSERT INTO `visitor` VALUES (3, '啊啊', '男', '14588635774', '湖北', '2022-01-27 16:41:21', '运送食品');
COMMIT;

-- ----------------------------
-- Table structure for fee
-- ----------------------------
DROP TABLE IF EXISTS `fee`;
CREATE TABLE `fee` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fee_type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '费用类型',
  `dormbuild_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '楼宇号',
  `dormroom_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '房间号',
  `amount` decimal(10,2) NOT NULL COMMENT '费用金额',
  `payment_status` enum('已缴费','未缴费') CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT '未缴费' COMMENT '缴费状态',
  `payment_time` datetime DEFAULT NULL COMMENT '缴费时间',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of fee
-- ----------------------------
BEGIN;
INSERT INTO `fee` VALUES (1, '水电费', '1', '1101', 150.00, '已缴费', '2024-01-15 10:30:00', '1月份水电费');
INSERT INTO `fee` VALUES (2, '网费', '1', '1102', 50.00, '未缴费', NULL, '1月份网费');
INSERT INTO `fee` VALUES (3, '物业费', '2', '2101', 200.00, '已缴费', '2024-01-20 14:20:00', '1月份物业费');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
