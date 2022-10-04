package org.example.server.server.connect.handler;

import com.google.protobuf.Message;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.example.common.po.HeartBeat;
import org.example.common.po.MessageTypeEnum;
import org.example.server.server.connect.util.ChannelPropertiesUtil;
import org.springframework.stereotype.Component;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.06.07
 */
@Component
@Slf4j
public class HeartbeatProtoMessageHandler implements MessageHandler{
    @Override
    public void dealMessage(Message message, ChannelHandlerContext ctx) {
        HeartBeat.HeartbeatMessage heartbeatMessage = (HeartBeat.HeartbeatMessage) message;
        //ctx.writeAndFlush(heartbeatMessage);
        log.info("receive heartbeat id is "+heartbeatMessage.getId());
    }

    @Override
    public MessageTypeEnum getDealMessageType() {
        return MessageTypeEnum.HEARTBEAT;
    }
}
