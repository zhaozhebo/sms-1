DROP TABLE IF EXISTS `phone_number`;
CREATE TABLE `phone_number` (
                                `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `type` varchar(10) NOT NULL COMMENT '类型',
                                `mobile` varchar(64) NOT NULL COMMENT '内容：手机号',
                                `remark` varchar(200) DEFAULT NULL COMMENT '备注',
                                `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除：1删除',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB COMMENT='黑白名单';

DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
                          `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
                          `name` varchar(100) NOT NULL COMMENT '名称',
                          `domain` varchar(100) NOT NULL COMMENT '域名',
                          `access_key_id` varchar(100) DEFAULT NULL,
                          `access_key_secret` varchar(100) DEFAULT NULL,
                          `is_active` tinyint NOT NULL COMMENT '是否可用：0不可用',
                          `is_enable` tinyint NOT NULL COMMENT '是否正常：0不正常',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                          `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除：1删除',
                          PRIMARY KEY (`id`) USING BTREE,
                          UNIQUE KEY `config_name` (`name`) USING BTREE
) ENGINE=InnoDB COMMENT='配置表';

DROP TABLE IF EXISTS `receive_log`;
CREATE TABLE `receive_log` (
                               `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `mobile` varchar(20) NOT NULL COMMENT '手机号',
                               `request` varchar(5000) NOT NULL COMMENT '请求参数',
                               `status` tinyint NOT NULL COMMENT '状态：0失败，1成功',
                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                               `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除：1删除',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB COMMENT='接收日志表';

DROP TABLE IF EXISTS `send_log`;
CREATE TABLE `send_log` (
                            `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `config_name` varchar(64) NOT NULL COMMENT '供应商名称',
                            `mobile` varchar(20) NOT NULL COMMENT '手机号',
                            `request` varchar(5000) NOT NULL COMMENT '请求参数',
                            `response` varchar(5000) DEFAULT NULL COMMENT '返回参数',
                            `error` text DEFAULT NULL COMMENT '错误信息',
                            `use_time` bigint NOT NULL COMMENT '耗时',
                            `status` tinyint NOT NULL COMMENT '状态：0失败，1成功',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                            `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除：1删除',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB COMMENT='发送日志表';