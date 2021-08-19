SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `administrator`
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `admin_password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of administrator
-- ----------------------------
INSERT INTO `administrator` VALUES ('1', 'admin', '123456');

-- ----------------------------
-- Table structure for `clazz`
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz` (
  `clazz_id` int NOT NULL AUTO_INCREMENT,
  `clazz_name` varchar(30) NOT NULL,
  `clazz_information` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`clazz_id`),
  KEY `clazz_name` (`clazz_name`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES ('55', '软工181', '大数据方向，熟练掌握python和java！');
INSERT INTO `clazz` VALUES ('56', '软工182', '大数据方向，熟练掌握python和java！');
INSERT INTO `clazz` VALUES ('57', '软工184', '云计算方向，精通基于java的各种企业级网络应用开发！');
INSERT INTO `clazz` VALUES ('58', '软工183', '云计算方向，精通基于java的各种企业级网络应用开发！');
INSERT INTO `clazz` VALUES ('59', '软工185', '以python为主，java为辅进行各种数据分析！');
INSERT INTO `clazz` VALUES ('60', '计科181', '网络工程方向！');
INSERT INTO `clazz` VALUES ('61', '计科182', '网络工程方向！');
INSERT INTO `clazz` VALUES ('62', '计科183', '信息安全方向！');
INSERT INTO `clazz` VALUES ('63', '计科184', '信息安全方向！');
INSERT INTO `clazz` VALUES ('64', '计科185', '大数据方向！');
INSERT INTO `clazz` VALUES ('65', '法学181', '环境保护法的应用与发展！');
INSERT INTO `clazz` VALUES ('66', '法学182', '民法的研究与发展！');
INSERT INTO `clazz` VALUES ('67', '法学183', '刑法的研究与发展！');
INSERT INTO `clazz` VALUES ('68', '环境181', '空气污染的检测与处理！');
INSERT INTO `clazz` VALUES ('69', '环境182', '地下水的检测与处理！');
INSERT INTO `clazz` VALUES ('76', '农林191', '解决土地荒漠化的研究！');
INSERT INTO `clazz` VALUES ('80', '数学191', '数值原理分析与拓展！');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `student_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `student_sex` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '男',
  `student_birthday` date NOT NULL,
  `student_mobile` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `student_email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `student_clazzName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `student_teacherName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`student_id`),
  KEY `clazzName` (`student_clazzName`),
  KEY `teacherName` (`student_teacherName`),
  CONSTRAINT `clazzName` FOREIGN KEY (`student_clazzName`) REFERENCES `clazz` (`clazz_name`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `teacherName` FOREIGN KEY (`student_teacherName`) REFERENCES `teacher` (`teacher_name`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '马哲', '男', '1997-11-14', '17879453467', '17879453467@qq.com', '法学182', '孙正义');
INSERT INTO `student` VALUES ('2', '周楚轩', '男', '2000-11-04', '15185137590', '1364052596@qq.com', '软工184', '汪强');
INSERT INTO `student` VALUES ('3', '莉莉', '女', '2000-07-21', '18798567698', '18798567698@qq.com', '计科182', '当当');
INSERT INTO `student` VALUES ('4', '寒冰', '女', '2000-09-10', '18778536462', '18778536462@163.com', '环境181', '鲁大福');
INSERT INTO `student` VALUES ('11', '高峰', '男', '1997-06-18', '15678946732', '15678946732@789.com', '计科185', '庄库');
INSERT INTO `student` VALUES ('23', '富丽', '女', '2000-05-07', '17216845672', '17216845672@163.com', '数学191', '鲁豫');
INSERT INTO `student` VALUES ('24', '赵大年', '男', '1998-05-02', '18276453521', '18276453521@qq.com', '软工183', '毛小乐');
INSERT INTO `student` VALUES ('25', '王定友', '男', '2000-01-02', '15678349801', '15678349801@987.com', '农林191', '田美');
INSERT INTO `student` VALUES ('26', '念旧', '女', '1999-07-01', '17218947390', '17218947390@qq.com', '法学182', '鲁大福');
INSERT INTO `student` VALUES ('27', '佳伟', '男', '1996-01-26', '16457890359', '16457890359@163.com', '计科183', '汪强');
INSERT INTO `student` VALUES ('28', '孙寒', '男', '1999-09-02', '17216879680', '17216879680@qq.com', '数学191', '曹康');
INSERT INTO `student` VALUES ('29', '杜超', '男', '1998-05-23', '15674539680', '15674539680@qq.com', '环境181', '雷老虎');
INSERT INTO `student` VALUES ('31', '贵州大学', '男', '2020-12-10', '18798567698', '18798567698@qq.com', '软工181', '坎波斯');
INSERT INTO `student` VALUES ('32', '猪头杰', '女', '2020-12-04', '13984842424', '18798567698@qq.com', '软工184', '坎波斯');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacher_id` int NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teacher_sex` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '男',
  `teacher_mobile` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teacher_email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`teacher_id`),
  KEY `teacher_name` (`teacher_name`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '坎波斯', '男', '15167246378', '15167246378@qq.com');
INSERT INTO `teacher` VALUES ('2', '鲁大福', '男', '18268072793', '18268072793@163.com');
INSERT INTO `teacher` VALUES ('3', '雷老虎', '男', '18798643463', '18798643463@152.com');
INSERT INTO `teacher` VALUES ('4', '当当', '女', '17216847892', '17216847892@qq.com');
INSERT INTO `teacher` VALUES ('6', '飘柔', '女', '13668072798', '13668072798@163.com');
INSERT INTO `teacher` VALUES ('15', '孙正义', '男', '16789764652', '16789764652@qq.com');
INSERT INTO `teacher` VALUES ('16', '庄库', '男', '13567864721', '13567864721@789.com');
INSERT INTO `teacher` VALUES ('17', '田美', '女', '17216852563', '17216852563@163.com');
INSERT INTO `teacher` VALUES ('18', '曹康', '女', '16178456321', '16178456321@qq.com');
INSERT INTO `teacher` VALUES ('19', '毛小乐', '男', '15675982863', '15675982863@163.com');
INSERT INTO `teacher` VALUES ('20', '汪强', '男', '17257435678', '17257435678@789.com');
INSERT INTO `teacher` VALUES ('21', '莫干溪', '女', '15674398732', '15674398732@qq.com');
INSERT INTO `teacher` VALUES ('42', '鲁豫', '女', '17987456781', '17987456781@qq.com');
