package org.example.business.business.mq;


import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.example.business.business.mapper.ChatMessageDetailMapper;
import org.example.business.business.mapper.po.ChatMessageDetailPO;
import org.example.common.po.Ack;
import org.example.common.po.Chat;
import org.example.common.po.MessageTypeEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.06.10
 */
@Slf4j
@Component
public class IMConsumerListener implements MessageListenerConcurrently {
    @Resource
    private ChatMessageDetailMapper chatMessageDetailMapper;
    @SneakyThrows
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        for (MessageExt msg : list) {
            byte[] content = msg.getBody();
            if(content!=null){
                log.info("mq消息：{}",content);
                try {
                    int type = MQUtil.getMessageType(content);
                    byte[] resByte = MQUtil.getMessageBytes(content);
                    if(type==MessageTypeEnum.CHAT.getCode()){
                        Chat.ChatMessage chatMessage = Chat.ChatMessage.parseFrom(resByte);
                        log.info("收到im的mq消息:{}",chatMessage);
                        ChatMessageDetailPO chatMessageDetailPO = new ChatMessageDetailPO();
                        chatMessageDetailPO.setMessageId(chatMessage.getId());
                        chatMessageDetailPO.setContent(chatMessage.getMsgBody().toStringUtf8());
                        chatMessageDetailPO.setUserId((int)chatMessage.getUserId());
                        chatMessageDetailPO.setReceiveId((int) chatMessage.getReceiveId());
                        chatMessageDetailPO.setSessionId("test");
                        chatMessageDetailMapper.insert(chatMessageDetailPO);
                    }
                }catch (Exception e){
                    log.error("e is {}",e);
                }

            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
