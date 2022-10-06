package org.example.server.server.connect.handler;

import com.google.protobuf.Message;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.example.common.po.MessageTypeEnum;
import org.example.server.server.connect.util.ChannelPropertiesUtil;
import org.example.server.server.connect.util.SessionConnectPool;
import org.example.server.server.mq.MQService;
import org.example.server.server.redis.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.06.07
 */
@Component
@Slf4j
@ChannelHandler.Sharable
public class IMServerProtoMessageHandler extends SimpleChannelInboundHandler<Message> {
    @Autowired
    private ProtoMessageHandlerFactory protoMessageHandlerFactory;
    @Resource
    private IRedisService iRedisService;
    @Resource
    private MQService mqService;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("IMServerMessageHandler is active");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        //log.info("[server get message]:{}",message.toString());
        MessageTypeEnum messageTypeEnum = MessageTypeEnum.getByClass(message.getClass());
        MessageHandler messageHandler = protoMessageHandlerFactory.getMessageHandlerByType(messageTypeEnum);
        messageHandler.dealMessage(message,channelHandlerContext);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("IMServerMessageHandler 处理消息错误",cause);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Integer userId = ChannelPropertiesUtil.getUserIdByCtx(ctx);
        SessionConnectPool.removeConnectMessage(String.valueOf(userId));
        iRedisService.deleteKey(String.valueOf(userId));
        ctx.fireChannelInactive();
    }


}
