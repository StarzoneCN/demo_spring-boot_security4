-------------------------------------------------
-- 以下是spring-security标准表格形式
-------------------------------------------------

-- 组*权限关系表
CREATE TABLE `group_authorities` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`group_id` INT(11) NOT NULL,
	`authority` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	PRIMARY KEY (`id`)
)
COLLATE='utf8mb4_unicode_ci'
ENGINE=InnoDB
;

-- 组*用户关系表
CREATE TABLE `group_members` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`group_id` INT(10) UNSIGNED NOT NULL,
	`username` VARCHAR(100) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	PRIMARY KEY (`id`)
)
COLLATE='utf8mb4_unicode_ci'
ENGINE=InnoDB
;

CREATE TABLE `users` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(50) NOT NULL,
	`password` VARCHAR(128) NOT NULL,
	`email` VARCHAR(128) NULL DEFAULT NULL,
	`mobile` VARCHAR(11) NULL DEFAULT NULL,
	`country_code` VARCHAR(10) NULL DEFAULT 'CN',
	`address` VARCHAR(128) NULL DEFAULT NULL,
	`enabled` BIT(1) NOT NULL DEFAULT b'1',
	PRIMARY KEY (`id`)
)
	COMMENT='用户表'
	COLLATE='utf8mb4_unicode_ci'
	ENGINE=InnoDB
	AUTO_INCREMENT=1
;


CREATE TABLE `groups` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`group_name` VARCHAR(50) NOT NULL COMMENT '组名称',
	PRIMARY KEY (`id`)
)
	COMMENT='分组表'
	COLLATE='utf8mb4_unicode_ci'
	ENGINE=InnoDB
	AUTO_INCREMENT=1
;