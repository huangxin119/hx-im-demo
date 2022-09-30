package org.example.common.coder;

import com.google.protobuf.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.06.07
 */
public class ProtoDecoder extends ByteToMessageDecoder {
    private ParseMessageFactory parseMessageFactory;
    public ProtoDecoder(){
        parseMessageFactory = new ParseMessageFactory();
    }
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        in.markReaderIndex();
        //检验一下，由于前面加密的包全是消息长度（4个字节）+消息类型（4个字节）+消息内容，所以每个包必须大于等于8个字节
        if(in.readableBytes()<8){
            in.resetReaderIndex();
        }
        int length = in.readInt();
        if(length>in.readableBytes()-4){
            in.resetReaderIndex();
            return;
        }
        int code = in.readInt();
        ByteBuf byteBuf = Unpooled.buffer(length);
        in.readBytes(byteBuf);
        byte[] messageBytes = byteBuf.array();
        Message message = parseMessageFactory.getMsgByCode(code,messageBytes);
        out.add(message);
    }

}
