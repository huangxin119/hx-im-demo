package org.example.server.server.connect.handler;

import com.google.protobuf.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.example.common.po.Ack;
import org.example.common.po.MessageTypeEnum;
import org.example.server.server.connect.util.SessionConnectPool;
import org.example.server.server.mq.MQService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.08.30
 */
@Component
@Slf4j
public class AckMessageHandler implements MessageHandler {
    @Resource
    private MQService mqService;
    @Override
    public void dealMessage(Message message, ChannelHandlerContext ctx) {
        Ack.AckMessage ackMessage = (Ack.AckMessage) message;
        log.info("receive ackMessage :{}",ackMessage);
        Long receiveId = ackMessage.getReceiveId();
        //判断receiveId是否在本节点，是则直接推送，否则交给transfer处理
        NioSocketChannel channel = SessionConnectPool.getChannelByUserId(receiveId.toString());
        if(channel!=null){
            log.info("收到聊天信息，收信者在同一服务器，直接转发");
            channel.writeAndFlush(ackMessage);
        }else{
            log.info("收到聊天信息，收信者在不同服务器，投递给mq");
            mqService.send("", ackMessage);
        }
    }

    @Override
    public MessageTypeEnum getDealMessageType() {
        return MessageTypeEnum.ACK;
    }
}
