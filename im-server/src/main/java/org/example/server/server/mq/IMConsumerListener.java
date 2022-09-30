package org.example.server.server.mq;


import com.google.protobuf.Message;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.example.common.po.Ack;
import org.example.common.po.Chat;
import org.example.common.po.MessageTypeEnum;
import org.example.server.server.connect.util.SessionConnectPool;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.06.10
 */
@Slf4j
@Component
public class IMConsumerListener implements MessageListenerConcurrently {
    @SneakyThrows
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        for (MessageExt msg : list) {
            byte[] content = msg.getBody();
            if(content!=null){
                int type = MQUtil.getMessageType(content);
                byte[] resByte = MQUtil.getMessageBytes(content);
                if(type==MessageTypeEnum.CHAT.getCode()){
                    Chat.ChatMessage message = Chat.ChatMessage.parseFrom(resByte);
                    NioSocketChannel channel = SessionConnectPool.getChannelByUserId(String.valueOf(message.getReceiveId()));
                    if(channel!=null){
                        log.info("receive message:{}",message);
                        channel.writeAndFlush(message);
                    }
                }else if(type==MessageTypeEnum.ACK.getCode()){
                    Ack.AckMessage message = Ack.AckMessage.parseFrom(resByte);
                    NioSocketChannel channel = SessionConnectPool.getChannelByUserId(String.valueOf(message.getReceiveId()));
                    if(channel!=null){
                        log.info("receive message:{}",message);
                        channel.writeAndFlush(message);
                    }
                }

            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
