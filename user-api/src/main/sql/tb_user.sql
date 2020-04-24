CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `name` varchar(64) NOT NULL COMMENT '姓名',
  `age` int(3) NOT NULL COMMENT '年龄',
  `sex` int(2) NOT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `created` datetime NOT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;