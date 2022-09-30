package org.example.server.server.connect.handler;

import org.example.common.po.MessageTypeEnum;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.06.07
 */
@Component
public class ProtoMessageHandlerFactory implements InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Map<MessageTypeEnum,MessageHandler> messageHandlerMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String,MessageHandler> messageHandlers = applicationContext.getBeansOfType(MessageHandler.class);
        for(MessageHandler messageHandler:messageHandlers.values()){
            messageHandlerMap.put(messageHandler.getDealMessageType(),messageHandler);
            System.out.println(messageHandler.getClass());
        }
        System.out.println(getMessageHandlerByType(MessageTypeEnum.ACK));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public MessageHandler getMessageHandlerByType(MessageTypeEnum messageTypeEnum){
        return messageHandlerMap.get(messageTypeEnum);
    }
}
