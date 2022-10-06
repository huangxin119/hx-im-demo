package org.example.server.server.connect.handler;

import com.google.protobuf.Message;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.example.common.po.Business;
import org.example.common.po.MessageTypeEnum;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BusinessMessageHandler implements MessageHandler{
    @Override
    public void dealMessage(Message message, ChannelHandlerContext ctx) {
        Business.BusinessMessage businessMessage = (Business.BusinessMessage) message;
        log.info("receive BusinessMessage id is "+businessMessage.getUserId());
    }

    @Override
    public MessageTypeEnum getDealMessageType() {
        return MessageTypeEnum.BUSINESS;
    }
}
