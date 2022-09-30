package org.example.common.coder;

import com.google.protobuf.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.example.common.po.MessageTypeEnum;



/**
 * @desc： 自定义加密类
 * @author: huangxin
 * @date: 2022.06.07
 */
public class ProtoEncoder extends MessageToByteEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf out) throws Exception {
        byte[] bytes = message.toByteArray();
        int code = MessageTypeEnum.getByClass(message.getClass()).getCode();
        int length = bytes.length;
        ByteBuf byteBuf = Unpooled.buffer(8+length);
        byteBuf.writeInt(length);
        byteBuf.writeInt(code);
        byteBuf.writeBytes(bytes);
        out.writeBytes(byteBuf);
    }
}
