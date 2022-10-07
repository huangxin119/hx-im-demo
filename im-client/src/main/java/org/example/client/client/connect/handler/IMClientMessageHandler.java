package org.example.client.client.connect.handler;

import com.google.protobuf.Message;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.example.client.client.api.IMClient;
import org.example.common.po.Ack;
import org.example.common.po.Chat;
import org.example.common.po.MessageTypeEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.08.23
 */
@Component
@Slf4j
public class IMClientMessageHandler  extends SimpleChannelInboundHandler<Message> {
    @Resource
    private IMClient imClient;
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message){
        log.info("客户端接收到消息,message is :{}", message.toString());
        if(MessageTypeEnum.getByClass(message.getClass())==MessageTypeEnum.CHAT){
            Chat.ChatMessage chatMessage = (Chat.ChatMessage) message;
            if(chatMessage.getNeedAck()){
                Ack.AckMessage ackMessage = Ack.AckMessage.newBuilder()
                        .setId("ack")
                        .setUserId(chatMessage.getReceiveId())
                        .setReceiveId(chatMessage.getUserId()).build();
                imClient.sendAckMessage(ackMessage);
            }
        }
    }
}
