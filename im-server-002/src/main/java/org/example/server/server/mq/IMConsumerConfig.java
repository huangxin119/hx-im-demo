package org.example.server.server.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.06.01
 */
@Slf4j
@Component
public class IMConsumerConfig implements ApplicationContextAware {

    @Value("${rocketmq.nameServers}")
    private String webSiteNameServer;

    @Value("${rocketmq.producer.topic}")
    private String topic;

    @Value("${rocketmq.producer.topic3}")
    private String businessTopic;

    @Value("${im.server.address}")
    private String address;

    @Value("${im.server.port}")
    private String port;


    private DefaultMQProducer imRocketMQProducer;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void initMQConsumer() throws MQClientException {
        log.info("执行了该方法");
        initImRocketMQConsumer();
        initBusinessMQConsumer();
    }

    private void initImRocketMQConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("im-consumer");
        consumer.setNamesrvAddr(webSiteNameServer);
        consumer.subscribe(topic,"");
        consumer.setConsumeMessageBatchMaxSize(1);
        consumer.setInstanceName("im-server-consumer");
        consumer.setMessageModel(MessageModel.BROADCASTING);
        IMConsumerListener imConsumerListener = applicationContext.getBean(IMConsumerListener.class);
        consumer.registerMessageListener(imConsumerListener);
        consumer.start();
        log.info("【ImRocketMqConsumerConfig】>>>>>>>>>>>>>>>> initImRocketMQConsumer >>>>>>>>>>>>>>>>");
    }

    private void initBusinessMQConsumer()throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("business-consumer");
        consumer.setNamesrvAddr(webSiteNameServer);
        consumer.subscribe(businessTopic,"");
        consumer.setConsumeMessageBatchMaxSize(1);
        consumer.setInstanceName("im-server-business-consumer");
        consumer.setMessageModel(MessageModel.BROADCASTING);
        IMBusinessConsumerListener imConsumerListener = applicationContext.getBean(IMBusinessConsumerListener.class);
        consumer.registerMessageListener(imConsumerListener);
        consumer.start();
        log.info("【ImRocketMqConsumerConfig】>>>>>>>>>>>>>>>> initBusinessMQConsumer >>>>>>>>>>>>>>>>");
    }
}
