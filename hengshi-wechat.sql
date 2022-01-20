/*
Navicat MySQL Data Transfer

Source Server         : 恒石RDS
Source Server Version : 50616
Source Host           : rm-bp11dymee5j50r794do.mysql.rds.aliyuncs.com:3306
Source Database       : hengshi-wechat

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2021-06-24 20:55:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `activity`
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` varchar(32) NOT NULL,
  `title` varchar(60) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `start_time` datetime(3) NOT NULL COMMENT '活动开始时间',
  `end_time` datetime(3) NOT NULL COMMENT '活动结束时间',
  `remark` varchar(60) DEFAULT NULL,
  `online` tinyint(3) unsigned DEFAULT '1' COMMENT '活动状态',
  `entrance_img` varchar(255) DEFAULT NULL,
  `internal_top_img` varchar(255) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('1', '优惠券活动1', '测试活动', '2021-03-22 10:38:10.000', '2021-10-01 10:38:15.000', null, '1', null, null, '');
INSERT INTO `activity` VALUES ('2', '优惠券活动2', '测试活动', '2021-03-16 13:33:11.000', '2021-10-01 13:33:16.000', null, '1', null, null, '');

-- ----------------------------
-- Table structure for `activity_coupon_template`
-- ----------------------------
DROP TABLE IF EXISTS `activity_coupon_template`;
CREATE TABLE `activity_coupon_template` (
  `id` varchar(32) NOT NULL,
  `coupon_tempalte_id` varchar(32) NOT NULL,
  `activity_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity_coupon_template
-- ----------------------------

-- ----------------------------
-- Table structure for `banner`
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `index_sort` int(11) NOT NULL,
  `img` varchar(255) NOT NULL,
  `online` int(10) DEFAULT '1',
  `extend` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES ('1', '测试001', '测试数据', '2', 'https://hengshi-website.oss-cn-chengdu.aliyuncs.com/hengshi-website/轮播图片/2020-11-30/809c38f267814c5195ee214869495ad3.jpg', '1', '', null);
INSERT INTO `banner` VALUES ('2', '测试002', '测试数据', '1', 'https://hengshi-website.oss-cn-chengdu.aliyuncs.com/hengshi-website/轮播图片/2020-11-30/809c38f267814c5195ee214869495ad3.jpg', '1', '', null);
INSERT INTO `banner` VALUES ('3', '测试003', '测试数据', '3', 'https://hengshi-website.oss-cn-chengdu.aliyuncs.com/hengshi-website/轮播图片/2020-11-30/809c38f267814c5195ee214869495ad3.jpg', '2', '', null);

-- ----------------------------
-- Table structure for `banner_item`
-- ----------------------------
DROP TABLE IF EXISTS `banner_item`;
CREATE TABLE `banner_item` (
  `id` varchar(32) NOT NULL,
  `title` varchar(100) NOT NULL,
  `img` varchar(255) NOT NULL,
  `banner_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhtdlqcpk4vq84rtuxqtj2ht9e` (`banner_id`),
  CONSTRAINT `FKhtdlqcpk4vq84rtuxqtj2ht9e` FOREIGN KEY (`banner_id`) REFERENCES `banner` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of banner_item
-- ----------------------------
INSERT INTO `banner_item` VALUES ('1', 'banner_item_1', 'http://1', '1');
INSERT INTO `banner_item` VALUES ('2', 'banner_item_2', 'http://1', '1');

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_root` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-根（一级）目录，1-二级目录',
  `parent_id` varchar(32) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL COMMENT '首页icon',
  `order_index` varchar(32) DEFAULT NULL,
  `online` varchar(32) DEFAULT '1' COMMENT '1-在线，0-下线',
  `level` varchar(32) DEFAULT NULL,
  `is_home_show` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '智能办公', '智能办公，告别低效工作模式！', '1', '', 'https://hengshi-wechat-mall.oss-cn-beijing.aliyuncs.com/project-img/智能办公.png?versionId=CAEQFhiCgIC.reb0wxciIDIwZGJkMWYzOTZhOTRlMWViYjE0YjAzZjQ2MGNjNGI3', '2', '1', null, '1');
INSERT INTO `category` VALUES ('10', '58服务', '语音聊天、求职者转发、视频面试、线上offer', '1', null, 'https://hengshi-wechat-mall.oss-cn-beijing.aliyuncs.com/project-img/58同城.png?versionId=CAEQFhiBgICxreb0wxciIDFlNjNlMzIwODUzNTQ1Y2NhYTAxNjJmN2ExN2MwMzli', '4', '1', null, '1');
INSERT INTO `category` VALUES ('2', '在线招聘', '在线面试，语言聊天', '1', '', 'https://hengshi-wechat-mall.oss-cn-beijing.aliyuncs.com/project-img/招聘会.png?versionId=CAEQFhiBgIC.reb0wxciIGFmMGE0MjMwOTViMzQ5NmVhNjg0NGM0Zjg2ZWY3OTU3', '1', '1', '', '1');
INSERT INTO `category` VALUES ('3', '钉钉服务', '工作简单高效更安全、组织沟通更加高效便捷，实现使命必达，激发组织中个体的创造创新力\r\n\r\n', '1', null, 'https://hengshi-wechat-mall.oss-cn-beijing.aliyuncs.com/project-img/社交,钉钉,dingtalk.png?versionId=CAEQFhiBgMC8reb0wxciIDRmMDI0YTBhY2Q1NjRhMDM4NzgyY2I2Y2QyMGU3ZmI4', '3', '1', null, '1');
INSERT INTO `category` VALUES ('31eb995f55084ee5b2fa197ab3165d4d', '钉钉硬件', '潮牌运动鞋', '0', '3', null, '7', '1', null, '1');
INSERT INTO `category` VALUES ('4', '咨询定制', '阿里巴巴钉钉认证专家团队为组织提供量身定制服务，保障组织需求诊断到定制方案落地，服务期间7*24小时应急响应', '0', '3', null, '1', '1', null, '0');
INSERT INTO `category` VALUES ('5', '教育培训', '1、手机人脸识别考勤机 智点B1 无线蓝牙打卡机智能上班签到 考勤机 免指纹考勤机免网络巡店巡检 2、魔点D3 智能门禁机 钉钉智连 多功能企业办公人脸云考勤机一体机 玻璃门门禁机 3、钉钉智能云打印 4、快人一步，如此简单，钉钉M1X人脸识别考勤机', '0', '3', null, '2', '1', null, '0');
INSERT INTO `category` VALUES ('6', '使用支持', '组织需要快速实时的解决产品使用问题，钉钉提供热线，在线及自学习的手册，帮助组织快速解决疑问', '0', '2', null, '3', '1', null, '1');
INSERT INTO `category` VALUES ('7', '部署服务', '组织需要快速上手钉钉，专家根据组织具体情况，提供工作流配置、全员培训，行业解决方案等服务', '0', '2', null, '4', '1', null, '0');
INSERT INTO `category` VALUES ('8', '钉钉培训', '钉钉培训是指一个移动办公系统的培训，移动办公的内容包括邮箱、工作汇报、人事考勤、行政审批及数字化统计分析，这些都是企业较为常用的办公内容。钉钉本身可以满足这些需求，但并不是所有企业都可以快输使用钉钉，也不是所有企业都可以熟练使用钉钉，其实可能还存在一种可能，很多企业都不太理解到底有多少潜力用途。因此钉钉培训大有必要。', '0', '10', null, '5', '1', null, '0');
INSERT INTO `category` VALUES ('9', '定制开发', '敏捷开发、度身定制，进行个性化开发、模块化配置是我们软件开发的特色、顾问型的实施模式，更贴心、更高效、软件质量的保障', '0', '1', null, '6', '1', null, '0');

-- ----------------------------
-- Table structure for `coupon`
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `id` varchar(32) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL COMMENT '优惠券的使用开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '优惠券的使用结束时间',
  `description` varchar(255) DEFAULT '',
  `full_money` decimal(10,2) DEFAULT NULL COMMENT '满多少-满减券',
  `minus` decimal(10,2) DEFAULT NULL COMMENT '减多少-满减券',
  `rate` decimal(10,2) DEFAULT NULL COMMENT '折扣率-折扣券',
  `type` smallint(6) NOT NULL COMMENT '1. 满减券 2.折扣券 3.无门槛券（相较于4，无消费金额限制） 4.满金额折扣券',
  `remark` varchar(255) DEFAULT NULL,
  `whole_store` tinyint(3) unsigned DEFAULT '0' COMMENT '是否全场',
  `activity_id` varchar(32) NOT NULL COMMENT '外键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coupon
-- ----------------------------
INSERT INTO `coupon` VALUES ('coupon_1', '满500减50', '2021-03-23 08:39:13', '2021-10-01 08:39:27', null, '500.00', '50.00', null, '1', null, '0', '1');
INSERT INTO `coupon` VALUES ('coupon_2', '满1000 75折', '2021-03-23 08:40:28', '2021-10-01 08:39:27', null, '1000.00', null, '0.75', '2', null, '0', '1');
INSERT INTO `coupon` VALUES ('coupon_3', '无门槛 5', '2021-03-23 08:43:25', '2021-10-01 08:39:27', null, null, '5.00', null, '3', null, '1', '1');
INSERT INTO `coupon` VALUES ('coupon_4', '满1000减200', '2021-03-23 10:04:58', '2021-10-01 08:39:27', null, '1000.00', '200.00', null, '1', null, '0', '2');

-- ----------------------------
-- Table structure for `coupon_category`
-- ----------------------------
DROP TABLE IF EXISTS `coupon_category`;
CREATE TABLE `coupon_category` (
  `id` varchar(32) NOT NULL,
  `category_id` varchar(32) NOT NULL,
  `coupon_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coupon_category
-- ----------------------------
INSERT INTO `coupon_category` VALUES ('1', '1', 'coupon_1');
INSERT INTO `coupon_category` VALUES ('2', '31eb995f55084ee5b2fa197ab3165d4d', 'coupon_2');
INSERT INTO `coupon_category` VALUES ('3', '2', 'coupon_2');
INSERT INTO `coupon_category` VALUES ('4', '31eb995f55084ee5b2fa197ab3165d4d', 'coupon_1');
INSERT INTO `coupon_category` VALUES ('5', '2', 'coupon_4');
INSERT INTO `coupon_category` VALUES ('6', '31eb995f55084ee5b2fa197ab3165d4d', 'coupon_4');

-- ----------------------------
-- Table structure for `coupon_template`
-- ----------------------------
DROP TABLE IF EXISTS `coupon_template`;
CREATE TABLE `coupon_template` (
  `id` varchar(32) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL COMMENT '描述：满1000减150',
  `type` tinyint(1) NOT NULL COMMENT '1-满减券 2-满减折扣券 3-无门槛券',
  `full_mony` decimal(10,2) DEFAULT NULL COMMENT '满减券&满减折扣券 消费最小金额限制',
  `minus` decimal(10,2) DEFAULT NULL COMMENT '满减券扣减金额',
  `rate` float(3,2) DEFAULT NULL COMMENT '满减折扣券-折扣率（75折-0.75）',
  `whole_store` tinyint(1) DEFAULT '0' COMMENT '1-全场券',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coupon_template
-- ----------------------------

-- ----------------------------
-- Table structure for `coupon_type`
-- ----------------------------
DROP TABLE IF EXISTS `coupon_type`;
CREATE TABLE `coupon_type` (
  `id` varchar(32) NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  `code` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coupon_type
-- ----------------------------
INSERT INTO `coupon_type` VALUES ('1', '满减券', '1', '测试优惠券', '2021-03-17 15:48:56', '2021-03-17 15:49:13', null);

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` varchar(32) NOT NULL,
  `order_no` varchar(20) DEFAULT NULL COMMENT '系统生成的订单号',
  `user_id` varchar(32) DEFAULT NULL COMMENT 'user表外键',
  `total_price` decimal(10,2) DEFAULT '0.00',
  `total_count` int(11) unsigned DEFAULT '0' COMMENT '订单有多少件sku',
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `expired_time` datetime(3) DEFAULT NULL COMMENT '订单支付超时时间',
  `placed_time` datetime(3) DEFAULT NULL COMMENT '下单时间',
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `snap_img` varchar(3000) DEFAULT NULL,
  `snap_title` varchar(500) DEFAULT NULL,
  `snap_items` varchar(5000) DEFAULT NULL COMMENT '子项json数据',
  `snap_address` varchar(500) DEFAULT NULL COMMENT '收货地址json数据',
  `prepay_id` varchar(255) DEFAULT NULL COMMENT '主要用来做微信支付',
  `final_total_price` decimal(10,2) DEFAULT NULL,
  `status` tinyint(3) unsigned DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_order_no` (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('3771335b38694ed5a68c7ee4851399bf', 'A192707307396', '14eca65d4b944a5f814b3514e60fc118', '8888.00', '1', '2021-04-19 10:53:47.222', null, '2021-04-19 10:54:07.073', '2021-04-19 10:53:47.073', '2021-04-19 10:54:07.340', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1110118764,2388801541&fm=26&gp=0.jpg', 'iphone13新品', '[{\"count\":1,\"hasSku\":false,\"id\":\"3\"}]', '{\"city\":\"广州市\",\"county\":\"海珠区\",\"detail\":\"新港中路397号\",\"mobile\":\"020-81167888\",\"postalCode\":\"510000\",\"province\":\"广东省\",\"userName\":\"张三\"}', null, '8883.00', '5');
INSERT INTO `order` VALUES ('4461c95408544244ac11eca583d3896c', 'A192576776794', '14eca65d4b944a5f814b3514e60fc118', '8888.00', '1', '2021-04-19 10:55:25.866', null, '2021-04-19 10:55:45.767', '2021-04-19 10:55:25.767', '2021-04-19 10:55:46.038', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1110118764,2388801541&fm=26&gp=0.jpg', 'iphone13新品', '[{\"count\":1,\"hasSku\":false,\"id\":\"3\"}]', '{\"city\":\"广州市\",\"county\":\"海珠区\",\"detail\":\"新港中路397号\",\"mobile\":\"020-81167888\",\"postalCode\":\"510000\",\"province\":\"广东省\",\"userName\":\"张三\"}', null, '8888.00', '5');
INSERT INTO `order` VALUES ('4c9ffa2225f04aca83d8133f36862b42', 'A213417017092', '14eca65d4b944a5f814b3514e60fc118', '3832.99', '3', '2021-06-21 21:23:54.391', null, '2021-06-21 21:24:14.170', '2021-06-21 21:23:54.170', '2021-06-21 21:24:14.518', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1447601552,3148578920&fm=26&gp=0.jpg|https://img.alicdn.com/tfs/TB1fgl2j7L0gK0jSZFxXXXWHVXa-387-336.png,https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1082018397,991627235&fm=26&gp=0.jpg', '钉钉投影仪,智点B1,M1 黑色 上一代芯片 安装&培训', '[{\"count\":1,\"hasSku\":false,\"id\":\"2\"},{\"count\":1,\"hasSku\":true,\"id\":\"1\"},{\"count\":1,\"hasSku\":false,\"id\":\"6\"}]', '{\"city\":\"杭州市\",\"county\":\"余杭区\",\"detail\":\"盛奥西溪铭座(杭州市余杭区)\",\"mobile\":\"13239643034\",\"postalCode\":\"000000\",\"province\":\"浙江省\",\"userName\":\"李向平\"}', null, '3782.99', '5');
INSERT INTO `order` VALUES ('599f4e4659804700b59e358bc0b67070', 'A198648848890', '14eca65d4b944a5f814b3514e60fc118', '8888.00', '1', '2021-04-19 10:54:46.603', null, '2021-04-19 10:55:06.488', '2021-04-19 10:54:46.488', '2021-04-19 10:55:06.690', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1110118764,2388801541&fm=26&gp=0.jpg', 'iphone13新品', '[{\"count\":1,\"hasSku\":false,\"id\":\"3\"}]', '{\"city\":\"广州市\",\"county\":\"海珠区\",\"detail\":\"新港中路397号\",\"mobile\":\"020-81167888\",\"postalCode\":\"510000\",\"province\":\"广东省\",\"userName\":\"张三\"}', null, '8888.00', '5');
INSERT INTO `order` VALUES ('7d278ae349d947f1b3ebedd5c3b98e96', 'A59082082095', '14eca65d4b944a5f814b3514e60fc118', '1234.00', '1', '2021-04-05 21:26:30.905', null, '2021-04-05 21:26:50.820', '2021-04-05 21:26:30.820', '2021-04-05 21:26:51.016', 'https://img.alicdn.com/tfs/TB1fgl2j7L0gK0jSZFxXXXWHVXa-387-336.png', '智点B1', '[{\"count\":1,\"hasSku\":false,\"id\":\"6\"}]', '{\"city\":\"兰州市\",\"county\":\"城关区\",\"detail\":\"暂不明确\",\"mobile\":\"13265833693\",\"postalCode\":\"730030\",\"province\":\"甘肃省\",\"userName\":\"清风客\"}', null, '1234.00', '5');
INSERT INTO `order` VALUES ('9ea5b79093ab4fec9c3e007f96c89304', 'A55044644642', '14eca65d4b944a5f814b3514e60fc118', '1234.00', '1', '2021-04-05 21:34:10.548', null, '2021-04-05 21:34:30.446', '2021-04-05 21:34:10.446', '2021-04-05 21:34:30.667', 'https://img.alicdn.com/tfs/TB1fgl2j7L0gK0jSZFxXXXWHVXa-387-336.png', '智点B1', '[{\"count\":1,\"hasSku\":false,\"id\":\"6\"}]', '{\"city\":\"兰州市\",\"county\":\"城关区\",\"detail\":\"暂不明确\",\"mobile\":\"13265833693\",\"postalCode\":\"730030\",\"province\":\"甘肃省\",\"userName\":\"清风客\"}', null, '1234.00', '5');
INSERT INTO `order` VALUES ('a59ca904ced94053ae4f9b6f6d600be5', 'A71810310375', '14eca65d4b944a5f814b3514e60fc118', '2399.00', '2', '2021-04-07 15:18:38.231', null, '2021-04-07 15:18:58.103', '2021-04-07 15:18:38.103', '2021-04-07 15:18:58.342', 'https://gw.alicdn.com/tfs/TB11ZSZGxz1gK0jSZSgXXavwpXa-1920-870.jpg,https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=435980371,3181943504&fm=26&gp=0.jpg', 'Nreal Light AR眼镜套装,M3 白色 新一代芯片 安装&培训', '[{\"count\":1,\"hasSku\":true,\"id\":\"3\"},{\"count\":1,\"hasSku\":false,\"id\":\"5\"}]', '{\"city\":\"兰州市\",\"county\":\"城关区\",\"detail\":\"暂不明确\",\"mobile\":\"13265833693\",\"postalCode\":\"730030\",\"province\":\"甘肃省\",\"userName\":\"清风客\"}', null, '2399.00', '5');
INSERT INTO `order` VALUES ('b4d03dc9658d4b739675e352571545a9', 'A59683783715', '2f073d575f18482fbc4f4b617e57801e', '10.00', '1', '2021-04-05 21:44:56.928', null, '2021-04-05 21:45:16.837', '2021-04-05 21:44:56.837', '2021-04-05 21:45:17.032', 'https://bkimg.cdn.bcebos.com/pic/dcc451da81cb39dbeb543df1de160924aa183084?x-bce-process=image/resize,m_lfit,w_268,limit_1/format,f_jpg', '小程序', '[{\"count\":1,\"hasSku\":false,\"id\":\"10\"}]', '{\"city\":\"兰州市\",\"county\":\"七里河区\",\"detail\":\"金雨大厦\",\"mobile\":\"18093207057\",\"postalCode\":\"730050\",\"province\":\"甘肃省\",\"userName\":\"苏\"}', null, '10.00', '5');
INSERT INTO `order` VALUES ('ca7a30b70b5e44c3a3b17dc693962d1f', 'A5256546545.', '2f073d575f18482fbc4f4b617e57801e', '899.00', '1', '2021-04-05 21:32:05.757', null, '2021-04-05 21:32:25.654', '2021-04-05 21:32:05.654', '2021-04-05 21:32:25.914', 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1082018397,991627235&fm=26&gp=0.jpg', 'M1 黑色 上一代芯片 安装&培训', '[{\"count\":1,\"hasSku\":true,\"id\":\"1\"}]', '{\"city\":\"兰州市\",\"county\":\"七里河区\",\"detail\":\"金雨大厦\",\"mobile\":\"18093207057\",\"postalCode\":\"730050\",\"province\":\"甘肃省\",\"userName\":\"苏\"}', null, '899.00', '5');
INSERT INTO `order` VALUES ('cd9a314e3b7043248709fc85253056a1', 'A58876876858', '14eca65d4b944a5f814b3514e60fc118', '10487.00', '2', '2021-04-05 21:39:48.900', null, '2021-04-05 21:40:08.768', '2021-04-05 21:39:48.768', '2021-04-05 21:40:09.071', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1110118764,2388801541&fm=26&gp=0.jpg,https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=435980371,3181943504&fm=26&gp=0.jpg', 'iphone13新品,M3 白色 新一代芯片 安装&培训', '[{\"count\":1,\"hasSku\":false,\"id\":\"3\"},{\"count\":1,\"hasSku\":true,\"id\":\"3\"}]', '{\"city\":\"杭州市\",\"county\":\"余杭区\",\"detail\":\"盛奥西溪铭座(杭州市余杭区)\",\"mobile\":\"13239643034\",\"postalCode\":\"000000\",\"province\":\"浙江省\",\"userName\":\"李向平\"}', null, '10487.00', '5');
INSERT INTO `order` VALUES ('f852cb549fcf4e3b88c7abba13276684', 'A51476676938', '14eca65d4b944a5f814b3514e60fc118', '10.00', '1', '2021-04-05 20:51:54.980', null, '2021-04-05 20:52:14.769', '2021-04-05 20:51:54.769', '2021-04-05 20:52:15.138', 'https://bkimg.cdn.bcebos.com/pic/dcc451da81cb39dbeb543df1de160924aa183084?x-bce-process=image/resize,m_lfit,w_268,limit_1/format,f_jpg', '小程序', '[{\"count\":1,\"hasSku\":false,\"id\":\"10\"}]', '{\"city\":\"广州市\",\"county\":\"海珠区\",\"detail\":\"新港中路397号\",\"mobile\":\"020-81167888\",\"postalCode\":\"510000\",\"province\":\"广东省\",\"userName\":\"张三\"}', null, '10.00', '5');

-- ----------------------------
-- Table structure for `order2`
-- ----------------------------
DROP TABLE IF EXISTS `order2`;
CREATE TABLE `order2` (
  `id` varchar(32) NOT NULL,
  `order_no` varchar(20) DEFAULT NULL COMMENT '系统生成的订单号',
  `user_id` varchar(32) DEFAULT NULL COMMENT 'user表外键',
  `total_price` decimal(10,2) DEFAULT '0.00',
  `total_count` int(11) unsigned DEFAULT '0' COMMENT '订单有多少件sku',
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `expired_time` datetime(3) DEFAULT NULL COMMENT '订单支付超时时间',
  `placed_time` datetime(3) DEFAULT NULL COMMENT '下单时间',
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `snap_img` varchar(3000) DEFAULT NULL,
  `snap_title` varchar(500) DEFAULT NULL,
  `snap_items` varchar(5000) DEFAULT NULL COMMENT '子项json数据',
  `snap_address` varchar(500) DEFAULT NULL COMMENT '收货地址json数据',
  `prepay_id` varchar(255) DEFAULT NULL COMMENT '主要用来做微信支付',
  `final_total_price` decimal(10,2) DEFAULT NULL,
  `status` tinyint(3) unsigned DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_order_no` (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order2
-- ----------------------------
INSERT INTO `order2` VALUES ('0e311b96b3344523af1778db06f43352', 'A304700701299', '9bdf535de48d4c51b7c88978312d8c02', '16507.00', '5', '2021-03-30 21:40:47.692', null, null, null, '2021-03-30 21:40:47.692', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1110118764,2388801541&fm=26&gp=0.jpg,https://gw.alicdn.com/tfs/TB11ZSZGxz1gK0jSZSgXXavwpXa-1920-870.jpg,https://img.alicdn.com/imgextra/i1/3081941561/O1CN014Mlzuo1NOysNc7t5Y_!!3081941561.jpg_430x430q90.jpg,https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1082018397,991627235&fm=26&gp=0.jpg,https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=435980371,3181943504&fm=26&gp=0.jpg', 'iphone13新品,Nreal Light AR眼镜套装,智能门禁机,M1 黑色 上一代芯片 安装&培训,M3 白色 新一代芯片 安装&培训', '[{\"count\":1,\"hasSku\":true,\"id\":\"3\"},{\"count\":1,\"hasSku\":false,\"id\":\"3\"},{\"count\":1,\"hasSku\":false,\"id\":\"5\"},{\"count\":1,\"hasSku\":true,\"id\":\"1\"},{\"count\":1,\"hasSku\":false,\"id\":\"8\"}]', '{\"city\":\"兰州\",\"county\":\"城关区\",\"detail\":\"未知\",\"province\":\"甘肃\",\"userName\":\"李向平\"}', null, '16507.50', '1');

-- ----------------------------
-- Table structure for `sku`
-- ----------------------------
DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku` (
  `id` varchar(32) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `price` varchar(20) NOT NULL,
  `discount_price` varchar(20) DEFAULT NULL,
  `online` tinyint(3) NOT NULL DEFAULT '1',
  `img` varchar(255) DEFAULT NULL,
  `specs` varchar(500) NOT NULL COMMENT '规格必填，json串。冗余降低数据库查询次数',
  `code` varchar(255) NOT NULL COMMENT '必填。冗余降低数据库查询次数。格式：2$1-42#3-10#4-15',
  `stock` int(255) NOT NULL DEFAULT '0',
  `spu_id` varchar(32) NOT NULL,
  `category_id` varchar(32) DEFAULT NULL,
  `root_category_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sku
-- ----------------------------
INSERT INTO `sku` VALUES ('1', 'M1 黑色 上一代芯片 安装&培训', '1099', '899', '1', 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1082018397,991627235&fm=26&gp=0.jpg', '[{\r\n	\"key_id\": \"1\",\r\n	\"key\": \"型号\",\r\n	\"value_id\": \"1\",\r\n	\"value\": \"M1\"\r\n}, {\r\n	\"key_id\": \"2\",\r\n	\"key\": \"颜色\",\r\n	\"value_id\": \"3\",\r\n	\"value\": \"黑色\"\r\n}, {\r\n	\"key_id\": \"3\",\r\n	\"key\": \"系统\",\r\n	\"value_id\": \"5\",\r\n	\"value\": \"上一代芯片\"\r\n}, {\r\n	\"key_id\": \"4\",\r\n	\"key\": \"服务\",\r\n	\"value_id\": \"7\",\r\n	\"value\": \"安装培训\"\r\n}]', '1$1-1#2-3#3-5#4-7', '10', '1', '1', null);
INSERT INTO `sku` VALUES ('2', 'M2 白色 新一代芯片 终身免服', '1599', '1299', '1', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4156179149,3476143560&fm=26&gp=0.jpg', '[{\r\n	\"key_id\": \"1\",\r\n	\"key\": \"型号\",\r\n	\"value_id\": \"2\",\r\n	\"value\": \"M2\"\r\n}, {\r\n	\"key_id\": \"2\",\r\n	\"key\": \"颜色\",\r\n	\"value_id\": \"4\",\r\n	\"value\": \"白色\"\r\n}, {\r\n	\"key_id\": \"3\",\r\n	\"key\": \"系统\",\r\n	\"value_id\": \"6\",\r\n	\"value\": \"新一代芯片\"\r\n}, {\r\n	\"key_id\": \"4\",\r\n	\"key\": \"服务\",\r\n	\"value_id\": \"8\",\r\n	\"value\": \"终身免服\"\r\n}]', '1$1-2#2-4#3-6#4-8', '10', '1', '1', null);
INSERT INTO `sku` VALUES ('3', 'M3 白色 新一代芯片 安装&培训', '1899', '1599', '1', 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=435980371,3181943504&fm=26&gp=0.jpg', '[{\r\n	\"key_id\": \"1\",\r\n	\"key\": \"型号\",\r\n	\"value_id\": \"9\",\r\n	\"value\": \"M3\"\r\n}, {\r\n	\"key_id\": \"2\",\r\n	\"key\": \"颜色\",\r\n	\"value_id\": \"4\",\r\n	\"value\": \"白色\"\r\n}, {\r\n	\"key_id\": \"3\",\r\n	\"key\": \"系统\",\r\n	\"value_id\": \"6\",\r\n	\"value\": \"新一代芯片\"\r\n}, {\r\n	\"key_id\": \"4\",\r\n	\"key\": \"服务\",\r\n	\"value_id\": \"7\",\r\n	\"value\": \"安装培训\"\r\n}]', '1$1-9#2-4#3-6#4-7', '0', '1', '1', null);
INSERT INTO `sku` VALUES ('4', '钉钉专业版 更专业的功能、更智能的搭建、更贴心的服务', '1088', '9800', '1', 'https://img.alicdn.com/imgextra/i4/O1CN01FKrxVm1Y183FhiKhq_!!6000000002998-2-tps-2136-1336.png', '[{\r\n	\"key_id\": \"5\",\r\n	\"key\": \"年限\",\r\n	\"value_id\": \"10\",\r\n	\"value\": \"一年\"\r\n}]', '9$5-10', '10', '9', '3', null);
INSERT INTO `sku` VALUES ('5', '钉钉专业版 更专业的功能、更智能的搭建、更贴心的服务', '19600', '1900', '1', 'https://img.alicdn.com/imgextra/i4/O1CN01FKrxVm1Y183FhiKhq_!!6000000002998-2-tps-2136-1336.png', '[{\r\n	\"key_id\": \"5\",\r\n	\"key\": \"年限\",\r\n	\"value_id\": \"11\",\r\n	\"value\": \"两年\"\r\n}]', '9$5-11', '10', '9', '3', null);
INSERT INTO `sku` VALUES ('7', '钉钉专业版 更专业的功能、更智能的搭建、更贴心的服务', '29400', '28000', '1', 'https://img.alicdn.com/imgextra/i4/O1CN01FKrxVm1Y183FhiKhq_!!6000000002998-2-tps-2136-1336.png', '[{\r\n	\"key_id\": \"5\",\r\n	\"key\": \"年限\",\r\n	\"value_id\": \"12\",\r\n	\"value\": \"三年\"\r\n}]\r\n', '9$5-12', '10', '9', '3', null);

-- ----------------------------
-- Table structure for `sku_spec`
-- ----------------------------
DROP TABLE IF EXISTS `sku_spec`;
CREATE TABLE `sku_spec` (
  `id` varchar(32) NOT NULL,
  `spu_id` varchar(32) NOT NULL,
  `sku_id` varchar(32) NOT NULL,
  `key_id` varchar(32) NOT NULL,
  `value_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sku_spec
-- ----------------------------
INSERT INTO `sku_spec` VALUES ('09a93c20c0104c8192dfed66749d9c35', '1', '339502f46d184c4f9a63218cb73c36d6', '2', '3');
INSERT INTO `sku_spec` VALUES ('1', '1', '1', '1', '1');
INSERT INTO `sku_spec` VALUES ('10', '1', '3', '2', '4');
INSERT INTO `sku_spec` VALUES ('11', '1', '3', '3', '6');
INSERT INTO `sku_spec` VALUES ('12', '1', '3', '4', '7');
INSERT INTO `sku_spec` VALUES ('13', '9', '4', '5', '10');
INSERT INTO `sku_spec` VALUES ('14', '9', '5', '5', '11');
INSERT INTO `sku_spec` VALUES ('15', '9', '6', '5', '12');
INSERT INTO `sku_spec` VALUES ('16', '11', '8', '6', '13');
INSERT INTO `sku_spec` VALUES ('17', '11', '8', '6', '14');
INSERT INTO `sku_spec` VALUES ('18', '11', '8', '7', '15');
INSERT INTO `sku_spec` VALUES ('19', '11', '8', '8', '16');
INSERT INTO `sku_spec` VALUES ('2', '1', '1', '2', '3');
INSERT INTO `sku_spec` VALUES ('20', '11', '8', '8', '17');
INSERT INTO `sku_spec` VALUES ('3', '1', '1', '3', '5');
INSERT INTO `sku_spec` VALUES ('3bb02b6678b3419c8c968720fbc21f75', '1', '339502f46d184c4f9a63218cb73c36d6', '1', '1');
INSERT INTO `sku_spec` VALUES ('4', '1', '1', '4', '7');
INSERT INTO `sku_spec` VALUES ('5', '1', '2', '1', '2');
INSERT INTO `sku_spec` VALUES ('6', '1', '2', '2', '4');
INSERT INTO `sku_spec` VALUES ('7', '1', '2', '3', '6');
INSERT INTO `sku_spec` VALUES ('8', '1', '2', '4', '8');
INSERT INTO `sku_spec` VALUES ('9', '1', '3', '1', '9');

-- ----------------------------
-- Table structure for `spec_key`
-- ----------------------------
DROP TABLE IF EXISTS `spec_key`;
CREATE TABLE `spec_key` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) NOT NULL,
  `unit` varchar(30) NOT NULL COMMENT '暂未用到',
  `standard` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否标准规格：0-非标准，1-标准',
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spec_key
-- ----------------------------
INSERT INTO `spec_key` VALUES ('1', '型号', '', '0', null);
INSERT INTO `spec_key` VALUES ('2', '颜色', '', '0', null);
INSERT INTO `spec_key` VALUES ('3', '系统', '', '0', null);
INSERT INTO `spec_key` VALUES ('4', '服务', '', '0', null);
INSERT INTO `spec_key` VALUES ('5', '年限', '', '0', null);
INSERT INTO `spec_key` VALUES ('6', '产品版本', '', '0', null);
INSERT INTO `spec_key` VALUES ('7', '账户有效期', '', '0', null);
INSERT INTO `spec_key` VALUES ('8', '职位数', '', '0', null);
INSERT INTO `spec_key` VALUES ('f19e29d1cc744844ab589ca83ddb63ca', '尺码', '单位', '0', '衣服尺码');

-- ----------------------------
-- Table structure for `spec_value`
-- ----------------------------
DROP TABLE IF EXISTS `spec_value`;
CREATE TABLE `spec_value` (
  `id` varchar(32) NOT NULL,
  `value` varchar(255) NOT NULL,
  `extend` varchar(255) DEFAULT NULL,
  `spec_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spec_value
-- ----------------------------
INSERT INTO `spec_value` VALUES ('0477701a66574ab99d5235eb7f02d8d6', 'M', null, 'f19e29d1cc744844ab589ca83ddb63ca');
INSERT INTO `spec_value` VALUES ('1', 'M1', null, '1');
INSERT INTO `spec_value` VALUES ('10', '一年', null, '5');
INSERT INTO `spec_value` VALUES ('11', '两年', null, '5');
INSERT INTO `spec_value` VALUES ('12', '三年', null, '5');
INSERT INTO `spec_value` VALUES ('13', '普通HR版A', null, '6');
INSERT INTO `spec_value` VALUES ('14', '普通HR版B', null, '6');
INSERT INTO `spec_value` VALUES ('15', '365天', null, '7');
INSERT INTO `spec_value` VALUES ('16', '精品60个', null, '8');
INSERT INTO `spec_value` VALUES ('17', '精品100个', null, '8');
INSERT INTO `spec_value` VALUES ('2', 'M2', null, '1');
INSERT INTO `spec_value` VALUES ('3', '黑色', null, '2');
INSERT INTO `spec_value` VALUES ('4', '白色', null, '2');
INSERT INTO `spec_value` VALUES ('6', '新一代芯片', null, '3');
INSERT INTO `spec_value` VALUES ('7', '安装&培训', null, '4');
INSERT INTO `spec_value` VALUES ('8', '终身免服', null, '4');
INSERT INTO `spec_value` VALUES ('805ea2bd204b45648908d55454ddf506', 'M', null, 'f19e29d1cc744844ab589ca83ddb63ca');
INSERT INTO `spec_value` VALUES ('9', 'M3', null, '1');

-- ----------------------------
-- Table structure for `spu`
-- ----------------------------
DROP TABLE IF EXISTS `spu`;
CREATE TABLE `spu` (
  `id` varchar(32) NOT NULL,
  `title` varchar(100) NOT NULL,
  `subtitle` varchar(800) DEFAULT NULL,
  `category_id` varchar(32) NOT NULL,
  `root_category_id` varchar(32) DEFAULT NULL,
  `online` tinyint(3) NOT NULL DEFAULT '1',
  `price` varchar(20) NOT NULL COMMENT '文本型价格，有时候SPU需要展示的是一个范围，或者自定义平均价格',
  `discount_price` varchar(20) DEFAULT NULL,
  `img` varchar(255) NOT NULL COMMENT '单规格使用',
  `description` varchar(255) DEFAULT NULL,
  `tags` varchar(30) DEFAULT NULL,
  `stock` int(8) NOT NULL COMMENT '单规格使用',
  `sketch_spec_id` varchar(32) DEFAULT NULL COMMENT '某种规格可以直接附加单品图片',
  `default_sku_id` varchar(32) DEFAULT NULL COMMENT '默认选中的sku',
  `recommend` tinyint(1) NOT NULL COMMENT '是否是推荐商品',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spu
-- ----------------------------
INSERT INTO `spu` VALUES ('1', '钉钉考勤机M系列', '新一代打卡机，就选钉钉智能打卡！。', '6', '2', '1', '2688', '2388', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3534180281,2303976598&fm=26&gp=0.jpg', '钉钉新一代打卡机，告别早高峰刷脸拥堵！', '新一代$安全稳定', '10', '1', '1', '1');
INSERT INTO `spu` VALUES ('10', '小程序', '敏捷开发、度身定制', '9', '1', '1', '3000', '10', 'https://bkimg.cdn.bcebos.com/pic/dcc451da81cb39dbeb543df1de160924aa183084?x-bce-process=image/resize,m_lfit,w_268,limit_1/format,f_jpg', '小程序是一种全新的连接用户与服务的方式，它可以在微信内被便捷地获取和传播，同时具有出色的使用体验', '定制开发$小程序', '10', null, null, '1');
INSERT INTO `spu` VALUES ('2', '钉钉投影仪', '智联未来，从心开始！', '6', '2', '1', '1999.99', '1699.99', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1447601552,3148578920&fm=26&gp=0.jpg', '2021款智能会议投影仪，连接稳定，高清投屏。', '品质保障$高清稳定', '10', null, null, '1');
INSERT INTO `spu` VALUES ('3', 'iphone13新品', 'iphone13 新一代巅峰之作！', '4', '3', '1', '8988', '8888', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1110118764,2388801541&fm=26&gp=0.jpg', '新一代iphone全新之作！', '全新发布$巅峰之作', '13', null, null, '1');
INSERT INTO `spu` VALUES ('4', 'F1视频会议一体机', '高清稳定、开会更爽', '5', '3', '1', '5698', '4999', 'https://img.alicdn.com/imgextra/i2/3081941561/O1CN01UQ4XSg1NOyrvk28cy_!!3081941561.png_430x430q90.jpg', '钉钉F1 视频会议一体机 智能降噪 高清相机支持多种画面布局 远程会议 企业培训办公教学', '智能会议$智能办公', '10', null, null, '1');
INSERT INTO `spu` VALUES ('5', 'Nreal Light AR眼镜套装', '3D虚拟会议 沉浸式远程会议体验', '31eb995f55084ee5b2fa197ab3165d4d', '3', '1', '12999', '800', 'https://gw.alicdn.com/tfs/TB11ZSZGxz1gK0jSZSgXXavwpXa-1920-870.jpg', '钉钉智连Nreal Light AR眼镜套装专业版视频会议协同办公远程协作DingTalk Work Space', '智能会议$智能办公', '10', null, null, '1');
INSERT INTO `spu` VALUES ('6', '智点B1', '实人实地 精准安全', '6', '2', '1', '69', '1234', 'https://img.alicdn.com/tfs/TB1fgl2j7L0gK0jSZFxXXXWHVXa-387-336.png', '手机人脸识别考勤机 智点B1 无线蓝牙打卡机智能上班签到 考勤机 免指纹考勤机免网络巡店巡检', '智能考勤$智能办公', '10', null, null, '1');
INSERT INTO `spu` VALUES ('7', '人脸识别考勤机', '人脸云端存储换机无需重复录入人脸 无需U盘', '7', '2', '1', '1398', '599', 'https://img.alicdn.com/imgextra/i4/3081941561/O1CN018n1QSU1NOysESW325_!!3081941561.jpg_430x430q90.jpg', '钉钉智能人脸识别考勤机 无线智能打卡机多地多店上班签到人脸机面部识别刷脸 人脸指纹一体机 指纹考勤机M1F', '智能考勤$智能办公', '10', null, null, '1');
INSERT INTO `spu` VALUES ('8', '智能门禁机', '混合光谱识别 红外验证', '8', '10', '1', '1099', '4321', 'https://img.alicdn.com/imgextra/i1/3081941561/O1CN014Mlzuo1NOysNc7t5Y_!!3081941561.jpg_430x430q90.jpg', '魔点Y2 智能门禁机 钉钉智连 多功能企业办公人脸云考勤机门禁系统一体机 玻璃门门禁机', '智能门禁', '10', null, null, '1');

-- ----------------------------
-- Table structure for `spu_detail_img`
-- ----------------------------
DROP TABLE IF EXISTS `spu_detail_img`;
CREATE TABLE `spu_detail_img` (
  `id` varchar(32) NOT NULL,
  `img` varchar(255) NOT NULL,
  `spu_id` varchar(32) NOT NULL,
  `index` varchar(32) NOT NULL COMMENT '详情图片排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spu_detail_img
-- ----------------------------
INSERT INTO `spu_detail_img` VALUES ('1', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3534180281,2303976598&fm=26&gp=0.jpg', '1', '1');
INSERT INTO `spu_detail_img` VALUES ('10', 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3877534850,3835065642&fm=26&gp=0.jpg', '3', '3');
INSERT INTO `spu_detail_img` VALUES ('11', 'https://img.alicdn.com/imgextra/i2/3081941561/O1CN01wPZNdl1NOytbg7ORK_!!3081941561.jpg', '4', '1');
INSERT INTO `spu_detail_img` VALUES ('12', 'https://img.alicdn.com/imgextra/i3/3081941561/O1CN01Mer9aY1NOyrdoG95J_!!3081941561.jpg', '4', '2');
INSERT INTO `spu_detail_img` VALUES ('13', 'https://img.alicdn.com/imgextra/i3/3081941561/O1CN01tSz0Nd1NOyrPErAb8_!!3081941561.jpg', '4', '3');
INSERT INTO `spu_detail_img` VALUES ('14', 'https://img.alicdn.com/imgextra/i2/3081941561/O1CN01V0f4Sg1NOyrKS7oxG_!!3081941561.jpg', '4', '4');
INSERT INTO `spu_detail_img` VALUES ('15', 'https://img.alicdn.com/imgextra/i3/3081941561/O1CN01nSaMIc1NOyrODyrzo_!!3081941561.jpg', '4', '5');
INSERT INTO `spu_detail_img` VALUES ('16', 'https://img.alicdn.com/imgextra/i3/3081941561/O1CN019liiSA1NOyrOxEp46_!!3081941561.jpg', '4', '6');
INSERT INTO `spu_detail_img` VALUES ('17', 'https://img.alicdn.com/imgextra/i1/3081941561/O1CN01HKXVi21NOyrLUkknE_!!3081941561.jpg', '5', '1');
INSERT INTO `spu_detail_img` VALUES ('18', 'https://img.alicdn.com/imgextra/i4/3081941561/O1CN01NbSuuL1NOyrmxmwar_!!3081941561.jpg', '5', '2');
INSERT INTO `spu_detail_img` VALUES ('19', 'https://img.alicdn.com/imgextra/i4/3081941561/O1CN01isgqem1NOyrHdIMYt_!!3081941561.jpg', '5', '3');
INSERT INTO `spu_detail_img` VALUES ('2', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2077891333,1853911666&fm=26&gp=0.jpg', '1', '2');
INSERT INTO `spu_detail_img` VALUES ('20', 'https://img.alicdn.com/imgextra/i4/3081941561/O1CN01Pwn8M51NOyrLUk9Nq_!!3081941561.jpg', '5', '4');
INSERT INTO `spu_detail_img` VALUES ('21', 'https://img.alicdn.com/imgextra/i3/3081941561/O1CN01wmii2A1NOyrHdKqQb_!!3081941561.jpg', '5', '5');
INSERT INTO `spu_detail_img` VALUES ('22', 'https://img.alicdn.com/imgextra/i2/3081941561/O1CN01nijqlZ1NOyrSYKETx_!!3081941561.jpg', '5', '6');
INSERT INTO `spu_detail_img` VALUES ('23', 'https://img.alicdn.com/imgextra/i1/3081941561/O1CN01CpiD5O1NOyrnGORpb_!!3081941561.png', '6', '1');
INSERT INTO `spu_detail_img` VALUES ('24', 'https://img.alicdn.com/imgextra/i1/3081941561/O1CN01CpiD5O1NOyrnGORpb_!!3081941561.png', '6', '2');
INSERT INTO `spu_detail_img` VALUES ('25', 'https://img.alicdn.com/imgextra/i2/3081941561/O1CN01kwzPVq1NOyqF2CYUu_!!3081941561.jpg', '6', '3');
INSERT INTO `spu_detail_img` VALUES ('26', 'https://img.alicdn.com/imgextra/i3/3081941561/O1CN01MoWqzt1NOyqCUMHse_!!3081941561.jpg', '6', '4');
INSERT INTO `spu_detail_img` VALUES ('27', 'https://img.alicdn.com/imgextra/i4/3081941561/O1CN018n1QSU1NOysESW325_!!3081941561.jpg_60x60q90.jpg', '7', '1');
INSERT INTO `spu_detail_img` VALUES ('28', 'https://img.alicdn.com/imgextra/i2/3081941561/O1CN01fXWuUW1NOyrSrUTfs_!!3081941561.png_60x60q90.jpg', '7', '2');
INSERT INTO `spu_detail_img` VALUES ('29', 'https://img.alicdn.com/imgextra/i1/3081941561/O1CN014Mlzuo1NOysNc7t5Y_!!3081941561.jpg_60x60q90.jpg', '8', '1');
INSERT INTO `spu_detail_img` VALUES ('3', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2903702452,3892593329&fm=26&gp=0.jpg', '1', '3');
INSERT INTO `spu_detail_img` VALUES ('30', 'https://img.alicdn.com/imgextra/i4/3081941561/O1CN01J6VyJu1NOysSt8yJM_!!3081941561.jpg_60x60q90.jpg', '8', '2');
INSERT INTO `spu_detail_img` VALUES ('31', 'https://img.alicdn.com/imgextra/i3/3081941561/O1CN01yjDLHZ1NOysStB3Ar_!!3081941561.jpg_60x60q90.jpg', '8', '3');
INSERT INTO `spu_detail_img` VALUES ('32', 'https://img.alicdn.com/imgextra/i2/3081941561/O1CN01hdV39m1NOysR8MuUA_!!3081941561.jpg_60x60q90.jpg', '8', '4');
INSERT INTO `spu_detail_img` VALUES ('4', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3137724329,2277756664&fm=26&gp=0.jpg', '1', '4');
INSERT INTO `spu_detail_img` VALUES ('5', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2610869250,440447699&fm=26&gp=0.jpg', '2', '1');
INSERT INTO `spu_detail_img` VALUES ('6', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3877785973,4049909781&fm=26&gp=0.jpg', '2', '2');
INSERT INTO `spu_detail_img` VALUES ('7', 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1300786455,55155095&fm=26&gp=0.jpg', '2', '3');
INSERT INTO `spu_detail_img` VALUES ('8', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2609313263,445609482&fm=26&gp=0.jpg', '3', '1');
INSERT INTO `spu_detail_img` VALUES ('9', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4165740672,2260638503&fm=11&gp=0.jpg', '3', '2');

-- ----------------------------
-- Table structure for `spu_img`
-- ----------------------------
DROP TABLE IF EXISTS `spu_img`;
CREATE TABLE `spu_img` (
  `id` varchar(32) NOT NULL,
  `img` varchar(255) NOT NULL,
  `spu_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spu_img
-- ----------------------------
INSERT INTO `spu_img` VALUES ('1', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2684827150,437648575&fm=26&gp=0.jpg', '1');
INSERT INTO `spu_img` VALUES ('10', 'https://img.alicdn.com/imgextra/i4/3081941561/O1CN01hf1y991NOyrHlvlhy_!!3081941561.png_60x60q90.jpg', '4');
INSERT INTO `spu_img` VALUES ('11', 'https://img.alicdn.com/imgextra/i3/3081941561/O1CN011CnkJ21NOyrJyuwX4_!!3081941561.png_60x60q90.jpg', '4');
INSERT INTO `spu_img` VALUES ('12', 'https://img.alicdn.com/imgextra/i2/3081941561/O1CN01UQ4XSg1NOyrvk28cy_!!3081941561.png_60x60q90.jpg', '4');
INSERT INTO `spu_img` VALUES ('13', 'https://img.alicdn.com/imgextra/i3/3081941561/O1CN01VL7bOK1NOysLcbJSy_!!0-item_pic.jpg_60x60q90.jpg', '5');
INSERT INTO `spu_img` VALUES ('14', 'https://img.alicdn.com/imgextra/i2/3081941561/O1CN01nT7lRU1NOyrLn8N11_!!3081941561.jpg_60x60q90.jpg', '5');
INSERT INTO `spu_img` VALUES ('16', 'https://img.alicdn.com/imgextra/i1/3081941561/O1CN01PLjn1N1NOyrM4g5Ni_!!3081941561.jpg_60x60q90.jpg', '5');
INSERT INTO `spu_img` VALUES ('17', 'https://img.alicdn.com/imgextra/i2/3081941561/O1CN01y6bPEW1NOyrNNNfZe_!!3081941561.jpg_60x60q90.jpg', '5');
INSERT INTO `spu_img` VALUES ('18', 'https://img.alicdn.com/imgextra/i1/3081941561/O1CN01IeTlbd1NOyuqE67fg_!!3081941561-0-picasso.jpg_60x60q90.jpg', '6');
INSERT INTO `spu_img` VALUES ('19', 'https://img.alicdn.com/imgextra/i3/3081941561/O1CN01VmmqeO1NOyq0Ed8gY_!!3081941561.png_60x60q90.jpg', '6');
INSERT INTO `spu_img` VALUES ('2', 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1014235669,1353760293&fm=26&gp=0.jpg', '1');
INSERT INTO `spu_img` VALUES ('20', 'https://img.alicdn.com/imgextra/i4/3081941561/O1CN01sb43Xz1NOypufDVbA_!!3081941561.png_60x60q90.jpg', '6');
INSERT INTO `spu_img` VALUES ('21', 'https://img.alicdn.com/imgextra/i4/3081941561/O1CN01POKRYE1NOypwMtUsb_!!3081941561.png_60x60q90.jpg', '6');
INSERT INTO `spu_img` VALUES ('22', 'https://img.alicdn.com/imgextra/i1/3081941561/O1CN01SmbWqW1NOyusSQDV8_!!3081941561-0-picasso.jpg_60x60q90.jpg', '7');
INSERT INTO `spu_img` VALUES ('23', 'https://img.alicdn.com/imgextra/i4/3081941561/O1CN018n1QSU1NOysESW325_!!3081941561.jpg_60x60q90.jpg', '7');
INSERT INTO `spu_img` VALUES ('24', 'https://img.alicdn.com/imgextra/i2/3081941561/O1CN01fXWuUW1NOyrSrUTfs_!!3081941561.png_60x60q90.jpg', '7');
INSERT INTO `spu_img` VALUES ('25', 'https://img.alicdn.com/imgextra/i1/3081941561/O1CN01U6XTOq1NOyuqE67gA_!!3081941561-0-picasso.jpg_60x60q90.jpg', '8');
INSERT INTO `spu_img` VALUES ('26', 'https://img.alicdn.com/imgextra/i1/3081941561/O1CN014Mlzuo1NOysNc7t5Y_!!3081941561.jpg_60x60q90.jpg', '8');
INSERT INTO `spu_img` VALUES ('27', 'https://img.alicdn.com/imgextra/i4/3081941561/O1CN01J6VyJu1NOysSt8yJM_!!3081941561.jpg_60x60q90.jpg', '8');
INSERT INTO `spu_img` VALUES ('28', 'https://img.alicdn.com/imgextra/i3/3081941561/O1CN01yjDLHZ1NOysStB3Ar_!!3081941561.jpg_60x60q90.jpg', '8');
INSERT INTO `spu_img` VALUES ('29', 'https://img.alicdn.com/imgextra/i2/3081941561/O1CN01hdV39m1NOysR8MuUA_!!3081941561.jpg_60x60q90.jpg', '8');
INSERT INTO `spu_img` VALUES ('3', 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=169321280,1327670022&fm=26&gp=0.jpg', '1');
INSERT INTO `spu_img` VALUES ('4', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2152843368,1825602823&fm=26&gp=0.jpg', '2');
INSERT INTO `spu_img` VALUES ('5', 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1594742833,2401825099&fm=26&gp=0.jpg', '2');
INSERT INTO `spu_img` VALUES ('6', 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3662068070,3237605837&fm=26&gp=0.jpg', '2');
INSERT INTO `spu_img` VALUES ('7', 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3644602662,2212163354&fm=26&gp=0.jpg', '3');
INSERT INTO `spu_img` VALUES ('8', 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=581747049,3648482129&fm=11&gp=0.jpg', '3');
INSERT INTO `spu_img` VALUES ('9', 'https://img.alicdn.com/imgextra/i1/3081941561/O1CN01f17jld1NOyun483lq_!!3081941561-0-picasso.jpg_60x60q90.jpg', '4');

-- ----------------------------
-- Table structure for `swiper`
-- ----------------------------
DROP TABLE IF EXISTS `swiper`;
CREATE TABLE `swiper` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `title` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `order` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of swiper
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `nick_name` varchar(255) NOT NULL COMMENT '微信用户——昵称；CMS用户——账号',
  `pwd` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `open_id` varchar(255) DEFAULT NULL,
  `union_id` varchar(255) DEFAULT NULL,
  `is_admin` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0——微信用户；1——CMS用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('123', '小李', '123456', null, '', null, null, '0');
INSERT INTO `user` VALUES ('14eca65d4b944a5f814b3514e60fc118', '%E5%BE%AE%E4%BF%A1%E7%94%A8%E6%88%B7', null, 'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132', null, 'oThf74kuZ3csD4CCNfJClxdhDknY', 'okOM55xlijqdU4E0SL-qB1ZJQphg', '0');
INSERT INTO `user` VALUES ('2f073d575f18482fbc4f4b617e57801e', '%40%E3%81%9D%E3%81%82%E3%81%86%E3%82%93', null, 'https://thirdwx.qlogo.cn/mmopen/vi_32/7sXiczpzibRaeia0vHb6QS80UdiaZWLt5UlgcD4ibicZmPxXxlj81aBzmsXfSaeCLWoKRP4SuHuHymw93oyziaIyTMpWw/132', null, 'oThf74vCaq1utrHrpbnno1eDzPxk', 'okOM550ibbjhqkU7WiuEJnyxrSC4', '0');
INSERT INTO `user` VALUES ('94da244330b24c7fbf850def542bc990', '%E4%BD%BF%E8%80%85', null, 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLsfvygE7BM0m7uJUNAU1AyO5SUhnc8AUNOMxKicyq8THkS6THXsM27UpiczeJsPqWnYYjlTmeB05VQ/132', null, 'oThf74v4I7JigSeD2a1L-lvxDTx4', 'okOM550YXa6TXf_Rxfm2Y3TkqCz0', '0');
INSERT INTO `user` VALUES ('99ddab7fce6b4daebf0d1711fa74218c', 'decisi%7E', null, 'https://thirdwx.qlogo.cn/mmopen/vi_32/Pw1aRTZO43Rf5ribeD0ObYiclVULmAAuqJuPWsPqsU2F33cCcZTuJWccQJxd0bZukiahxqwTvjM5pAGGLul2jRP1g/132', null, 'oThf74ih5x2F-_ft6eC-rja28eX8', 'okOM550HXRUv8CH8-Oj-t2FL4U2c', '0');

-- ----------------------------
-- Table structure for `user_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `user_coupon`;
CREATE TABLE `user_coupon` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `coupon_id` varchar(32) NOT NULL,
  `status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '1:已领取未使用 2：已领取已使用 3：已领取已过期',
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '实际意义：领取优惠券时间（特别适合新人券模式）',
  `order_id` varchar(32) DEFAULT NULL COMMENT '记录优惠券在哪个订单使用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_user_coupon` (`user_id`,`coupon_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user_coupon
-- ----------------------------
INSERT INTO `user_coupon` VALUES ('03c00e352b0344c687ad6e1f6491c2e6', '94da244330b24c7fbf850def542bc990', 'coupon_3', '1', '2021-04-03 16:42:02.952', null);
INSERT INTO `user_coupon` VALUES ('25568a23f1b14b8dbc32dd3660a9793f', '94da244330b24c7fbf850def542bc990', 'coupon_4', '1', '2021-04-03 16:42:06.871', null);
INSERT INTO `user_coupon` VALUES ('28ca67271eba41ed91ba58668da4495f', '94da244330b24c7fbf850def542bc990', 'coupon_2', '1', '2021-04-03 16:42:01.077', null);
INSERT INTO `user_coupon` VALUES ('6683230c59c343efbab70a31b2725d2b', '14eca65d4b944a5f814b3514e60fc118', 'coupon_1', '2', '2021-04-19 10:49:50.970', '4c9ffa2225f04aca83d8133f36862b42');
INSERT INTO `user_coupon` VALUES ('7b6b888a11e34783893bae2929c4cdd3', '94da244330b24c7fbf850def542bc990', 'coupon_1', '1', '2021-04-03 16:41:58.742', null);
INSERT INTO `user_coupon` VALUES ('88273cbad5ec43799ac9c47b073336b9', '14eca65d4b944a5f814b3514e60fc118', 'coupon_3', '2', '2021-04-19 10:53:38.513', '3771335b38694ed5a68c7ee4851399bf');
INSERT INTO `user_coupon` VALUES ('8aa9d4d7574c492fb5b09ae7f8ba77f6', '9bdf535de48d4c51b7c88978312d8c02', 'coupon_1', '2', '2021-04-03 23:39:14.130', '57eeefb101a54467a8563c35ac8c4a0d');
INSERT INTO `user_coupon` VALUES ('fb51b3ea47c64f0b968230e15fbed7d5', '9bdf535de48d4c51b7c88978312d8c02', 'coupon_3', '2', '2021-04-03 13:07:35.167', 'd56871d43cf5462d92e55ca4094a573f');
