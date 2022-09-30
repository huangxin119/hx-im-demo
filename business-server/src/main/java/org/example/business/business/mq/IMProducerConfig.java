package org.example.business.business.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @desc：
 * @author: huangxin
 * @date: 2022.06.01
 */
@Configuration
@Slf4j
public class IMProducerConfig {

    @Value("${rocketmq.nameServers}")
    private String webSiteNameServer;

    private DefaultMQProducer imRocketMQProducer;

    @Bean
    public DefaultMQProducer imRocketMQProducer() throws MQClientException {
        imRocketMQProducer = new DefaultMQProducer();
        imRocketMQProducer.setProducerGroup("im-business-producer");
        imRocketMQProducer.setNamesrvAddr(webSiteNameServer);
        imRocketMQProducer.start();
        log.info("【ImRocketMqProducerConfig】>>>>>>>>>>>>>>>> initImRocketMQProducer >>>>>>>>>>>>>>>>");
        return imRocketMQProducer;
    }

}
