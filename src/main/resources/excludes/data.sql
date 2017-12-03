INSERT INTO `spring_security_test`.`user`(`username`, `password`) VALUES ('admin', '$2a$10$Nnwx70r6csJlAX1.oNgbVOSN8JO5wFZmsubzXlxoXv9eUNlMU6buq'); --密码是‘admin’加密形式

-- 开启group时，插入数据
INSERT INTO `spring_security_test`.`group` (`group_name`) VALUES ('管理员');
INSERT INTO `spring_security_test`.`group_user` (`group_id`, `user_id`) VALUES ('1', '1');
INSERT INTO `spring_security_test`.`authority` (`authority`) VALUES ('ROLE_ADMIN');
INSERT INTO `spring_security_test`.`authority` (`authority`) VALUES ('ROLE_USER');
INSERT INTO `spring_security_test`.`group_authority` (`group_id`, `authority_id`) VALUES ('1', '1');
INSERT INTO `spring_security_test`.`group_authority` (`group_id`, `authority_id`) VALUES ('1', '2');

-- 关闭group模式时
INSERT INTO `spring_security_test`.`authority` (`user_id`, `authority`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `spring_security_test`.`authority` (`user_id`, `authority`) VALUES ('1', 'ROLE_USER');