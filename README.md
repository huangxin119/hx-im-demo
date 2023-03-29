[toc]
# 项目介绍

一个IM的demo项目

# 各模块介绍

![](./image/im包结构.png)

# 各阶段目标

## im连接层目标

阶段一：模拟服务端与客户端建立长连接，并实现心跳机制。已实现----见客户端org.example.client.client.connect.ImClientStarter.connect以及服务端的org.example.server.server.connect.starter.IMServer.start

阶段二：定义通信消息格式，实现上下线功能。已实现----建议跟着客户端的org.example.client.client.api.impl.IMClientImpl.login以及服务端的org.example.server.server.connect.handler.IMServerProtoMessageHandler.channelRead0

阶段三：实现不同用户连接同一台服务器的聊天功能。已实现----客户端连接同一个sever，服务端见org.example.server.server.connect.handler.ChatMessageHandler.dealMessage

阶段四：实现不同用户连接不同服务器的聊天功能。已实现----客户端连接同一个sever，服务端见org.example.server.server.connect.handler.ChatMessageHandler.dealMessage

阶段五：使用高效率的序列化协议以及心跳方式。已实现----序列化方式见im-common包，心跳见org.example.client.client.connect.handler.ClientHeatBeatStateTrigger


## im业务层目标

阶段六：实现一些简单业务功能。已实现----见business项目，实现了加好友功能

阶段七：实现未读数功能。未实现

阶段八：实现群聊消息的推送。未实现


# 测试启动方法

（1）环境准备

需要mysql，redis，mq的依赖支持

mysql创建sql,并在配置文件application.yml替换地址
```sql
CREATE TABLE `chat_message_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `message_id` varchar(50) NOT NULL DEFAULT '0' COMMENT '消息唯一id',
  `session_id` varchar(50) NOT NULL DEFAULT '0' COMMENT '会话id',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `receive_id` int(11) NOT NULL DEFAULT '0' COMMENT '收信人id',
  `status` smallint(4) NOT NULL DEFAULT '0' COMMENT '消息状态',
  `content` varchar(200) NOT NULL DEFAULT '0' COMMENT '消息内容',
  `is_read` smallint(4) NOT NULL DEFAULT '0' COMMENT '消息是否已读',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='消息表';

CREATE TABLE `user_account` (
                                `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
                                `password` varchar(50) DEFAULT NULL COMMENT '用户密码',
                                `user_id` int(11) DEFAULT NULL COMMENT '用户id',
                                `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                `valid_status` smallint(6) DEFAULT NULL COMMENT '用户状态,1有效',
                                PRIMARY KEY (`id`),
                                KEY `idx_userId` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户账号表';

CREATE TABLE `user_apply` (
                              `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                              `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
                              `apply_type` smallint(4) NOT NULL DEFAULT '0' COMMENT '申请类型，0好友，1进组',
                              `receive_id` int(11) NOT NULL DEFAULT '0' COMMENT '接收id',
                              `apply_status` smallint(4) NOT NULL DEFAULT '0' COMMENT '申请状态，0未处理，1同意，2拒绝',
                              `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='好友关系申请';

CREATE TABLE `user_contact_list` (
                                     `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                     `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
                                     `session_id` varchar(50) NOT NULL DEFAULT '0' COMMENT '会话id',
                                     `session_type` smallint(6) NOT NULL DEFAULT '0' COMMENT '会话类型，0私聊1群组',
                                     `unRead_num` int(11) NOT NULL DEFAULT '0' COMMENT '未读消息数',
                                     `last_message_id` int(11) NOT NULL DEFAULT '0' COMMENT '最新消息',
                                     `valid_status` smallint(4) NOT NULL DEFAULT '0' COMMENT '有效状态',
                                     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户联系人列表';
```

redis服务启动,并在配置文件application.yml替换地址

mq服务启动，创建好对应的topic，并在配置文件application.yml替换地址

（2）模拟运用场景

