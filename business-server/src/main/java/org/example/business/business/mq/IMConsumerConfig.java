package org.example.business.business.mq;

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

    @Value("${rocketmq.producer.topic2}")
    private String topic;



    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void initMQConsumer() throws MQClientException {
        log.info("执行了该方法");
        initImRocketMQProducer();
    }

    private void initImRocketMQProducer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("im-busniess-consumer");
        consumer.setNamesrvAddr(webSiteNameServer);
        consumer.subscribe(topic,"");
        consumer.setConsumeMessageBatchMaxSize(1);
        consumer.setInstanceName("im-business-server-consumer");
        IMConsumerListener imConsumerListener = applicationContext.getBean(IMConsumerListener.class);
        consumer.registerMessageListener(imConsumerListener);
        consumer.start();
        log.info("【ImRocketMqConsumerConfig】>>>>>>>>>>>>>>>> initImRocketMQOnlineConsumer >>>>>>>>>>>>>>>>");
    }
}
