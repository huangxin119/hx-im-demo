package org.example.server.server.connect.heatbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.example.server.server.redis.IRedisService;

import javax.annotation.Resource;

/**
 * @desc： 服务端断连触发器
 * @author: huangxin
 * @date: 2022.05.24
 */
@Slf4j
public class ServerHeatBeatStateTrigger extends ChannelInboundHandlerAdapter {
    @Resource
    private IRedisService iRedisService;
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //监听到超时未收到心跳数据，主动断开连接
        if(evt instanceof IdleStateEvent){
            IdleState idleState = ((IdleStateEvent)evt).state();
            if(idleState == IdleState.READER_IDLE){
                log.info("超过规定心跳时间未收到数据包，断开客户端连接");
                ctx.disconnect();
            }else {
                super.userEventTriggered(ctx, evt);
            }
        }
    }
}
