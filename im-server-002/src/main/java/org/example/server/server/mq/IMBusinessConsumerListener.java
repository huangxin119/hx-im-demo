package org.example.server.server.mq;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.example.common.po.Business;
import org.example.server.server.connect.util.SessionConnectPool;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.09.13
 */
@Slf4j
@Component
public class IMBusinessConsumerListener  implements MessageListenerConcurrently {
    @SneakyThrows
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        for (MessageExt msg : msgs) {
            byte[] content = msg.getBody();
            Business.BusinessMessage businessMessage = Business.BusinessMessage.parseFrom(content);
            long receiveId = businessMessage.getReceiveId();
            if(receiveId>0){
                if(SessionConnectPool.getChannelByUserId(String.valueOf(receiveId))!=null){
                    SessionConnectPool.getChannelByUserId(String.valueOf(receiveId)).writeAndFlush(businessMessage);
                }
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
