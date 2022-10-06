package org.example.client.client.connect.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.example.common.po.HeartBeat;

import java.util.Date;

/**
 * @desc： 客户端心跳触发器
 * @author: huangxin
 * @date: 2022.05.25
 */
@Slf4j
public class ClientHeatBeatStateTrigger extends ChannelInboundHandlerAdapter {
    private int userId;

    public ClientHeatBeatStateTrigger(int userId){
        this.userId = userId;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //如果指定时间里未发数据，会收到一个IdleState.WRITER_IDLE事件，触发发送心跳包逻辑
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.WRITER_IDLE) {
                // write heartbeat to server
                HeartBeat.HeartbeatMessage heartbeat = HeartBeat.HeartbeatMessage.newBuilder()
                        .setId("test")
                        .setUserId(userId)
                        .setTimeStamp(new Date().getTime()).build();
                //log.info("客户端发送心跳");
                ctx.channel().writeAndFlush(heartbeat);
            }else if(state==IdleState.READER_IDLE){
                ctx.close();
                log.info("服务端不可用，重连其他服务");
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
