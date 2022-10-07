package org.example.server.server.connect.handler;

import com.google.protobuf.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.example.common.po.Chat;
import org.example.common.po.MessageTypeEnum;
import org.example.server.server.connect.util.SessionConnectPool;
import org.example.server.server.mq.MQService;
import org.example.server.server.redis.IRedisService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.06.07
 */
@Component
@Slf4j
public class ChatMessageHandler implements MessageHandler {
    @Resource
    private MQService mqService;
    @Resource
    private IRedisService iRedisService;

    @Override
    public void dealMessage(Message message, ChannelHandlerContext ctx) {
        Chat.ChatMessage chatMessage = (Chat.ChatMessage) message;
        log.info("receive chatMessage :{}",message);
        Long receiveId = chatMessage.getReceiveId();
        String res = iRedisService.getValue(receiveId.toString());
        //业务方存储消息
        mqService.notifyBusiness("",message);
        //判断receiveId是否在本节点，是则直接推送，否则交给transfer处理
        NioSocketChannel channel = SessionConnectPool.getChannelByUserId(receiveId.toString());
        if(channel!=null){
            log.info("收到聊天信息，收信者在同一服务器，直接转发");
            channel.writeAndFlush(chatMessage);
        }else{
            log.info("收到聊天信息，收信者在不同服务器，投递给mq");
            mqService.send("", chatMessage);
        }
    }

    @Override
    public MessageTypeEnum getDealMessageType() {
        return MessageTypeEnum.CHAT;
    }
}
