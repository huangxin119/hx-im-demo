package org.example.business.business.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @desc：
 * @author: huangxin
 * @date: 2022.06.01
 */
@Service
@Slf4j
public class MQServiceImpl implements MQService{
    @Value("${rocketmq.producer.topic3}")
    private String topic;
    @Resource
    private DefaultMQProducer imRocketMQProducer;
    @Override
    public void send(String tag, com.google.protobuf.Message content) {
        Message message = new Message(topic,tag,content.toByteArray());
        log.info("[MQServiceImpl] message is {}", message);
        try {
            imRocketMQProducer.send(message);
        } catch (Exception e) {
            log.info("[ImServiceImpl] error is ", e);
        }
    }
}
