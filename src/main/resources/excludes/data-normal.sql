------------------------------------------
-- spring-security 标准表格形式下数据填充
------------------------------------------

-- 用户密码明文是"admin"（即以下用户记录表中的密码字段是加密后的"admin"）
INSERT INTO `security4_normal`.`users` (`username`, `password`) VALUES ('admin', '$2a$10$Nnwx70r6csJlAX1.oNgbVOSN8JO5wFZmsubzXlxoXv9eUNlMU6buq');
INSERT INTO `security4_normal`.`users` (`username`, `password`) VALUES ('user', '$2a$10$Nnwx70r6csJlAX1.oNgbVOSN8JO5wFZmsubzXlxoXv9eUNlMU6buq');

INSERT INTO `security4_normal`.`groups` (`group_name`) VALUES ('管理员');
INSERT INTO `security4_normal`.`groups` (`group_name`) VALUES ('普通用户');

INSERT INTO `security4_normal`.`group_authorities` (`group_id`, `authority`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `security4_normal`.`group_authorities` (`group_id`, `authority`) VALUES ('2', 'ROLE_USER');

INSERT INTO `security4_normal`.`group_members` (`group_id`, `username`) VALUES ('1', 'admin');
INSERT INTO `security4_normal`.`group_members` (`group_id`, `username`) VALUES ('2', 'user');