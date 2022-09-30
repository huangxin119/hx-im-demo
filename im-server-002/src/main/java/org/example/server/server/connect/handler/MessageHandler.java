package org.example.server.server.connect.handler;

import com.google.protobuf.Message;
import io.netty.channel.ChannelHandlerContext;
import org.example.common.po.MessageTypeEnum;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.06.07
 */
public interface MessageHandler {
    void dealMessage(Message message, ChannelHandlerContext ctx);
    MessageTypeEnum getDealMessageType();
}
