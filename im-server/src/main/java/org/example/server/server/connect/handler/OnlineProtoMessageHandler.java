package org.example.server.server.connect.handler;

import com.google.protobuf.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.example.common.po.MessageTypeEnum;
import org.example.common.po.Online;
import org.example.server.server.connect.util.ChannelPropertiesUtil;
import org.example.server.server.connect.util.SessionConnectPool;
import org.example.server.server.connect.heatbeat.HeartBeatTimer;
import org.example.server.server.mq.MQService;
import org.example.server.server.redis.IRedisService;
import org.example.server.server.connect.util.ServerNodeUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.06.07
 */
@Component
@Slf4j
public class OnlineProtoMessageHandler implements MessageHandler {
    @Resource
    private IRedisService iRedisService;
    @Resource
    private MQService mqService;
    @Override
    public void dealMessage(Message message, ChannelHandlerContext ctx) {
        Online.OnlineMessage onlineMessage = (Online.OnlineMessage) message;
        if(onlineMessage.getMsgType()== Online.OnlineMessage.MsgType.ONLINE){
            log.info(onlineMessage.getUserId()+" is online");
            SessionConnectPool.putConnectMessage(String.valueOf(onlineMessage.getUserId()),(NioSocketChannel) ctx.channel());
            //构建用户登录消息存入redis
            iRedisService.setValue(String.valueOf(onlineMessage.getUserId()), ServerNodeUtil.getServerNode().getServerName());
        }else{
            log.info(onlineMessage.getUserId()+" is offline");
            SessionConnectPool.removeConnectMessage(String.valueOf(onlineMessage.getUserId()));
            iRedisService.deleteKey(String.valueOf(onlineMessage.getUserId()));
            ctx.close();
        }
    }

    @Override
    public MessageTypeEnum getDealMessageType() {
        return MessageTypeEnum.ONLINE;
    }
}
