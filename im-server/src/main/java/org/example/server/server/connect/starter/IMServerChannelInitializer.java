package org.example.server.server.connect.starter;

import io.netty.channel.*;
import io.netty.handler.timeout.IdleStateHandler;
import org.example.common.coder.ProtoDecoder;
import org.example.common.coder.ProtoEncoder;
import org.example.server.server.connect.handler.IMServerProtoMessageHandler;
import org.example.server.server.connect.heatbeat.ServerHeatBeatStateTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.05.26
 */
@Component
public class IMServerChannelInitializer extends ChannelInitializer<Channel> {
    @Resource
    private IMServerProtoMessageHandler imServerProtoMessageHandler;

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        //添加心跳机制(一定要注意顺序)
        pipeline.addLast("idleStateHandler",new IdleStateHandler(10,0,0));
        pipeline.addLast("heatBeatStateTrigger",new ServerHeatBeatStateTrigger());
        pipeline.addLast("decoder",new ProtoDecoder());
        pipeline.addLast("encoder",new ProtoEncoder());
        pipeline.addLast("imServerMessageHandler",imServerProtoMessageHandler);
    }
}
