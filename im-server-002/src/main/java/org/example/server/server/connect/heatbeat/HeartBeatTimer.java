package org.example.server.server.connect.heatbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;
import lombok.extern.slf4j.Slf4j;
import org.example.server.server.connect.util.ChannelPropertiesUtil;

import java.util.concurrent.TimeUnit;

/**
 * @desc： 心跳定时检测
 * @author: huangxin
 * @date: 2022.06.09
 */
@Slf4j
public class HeartBeatTimer {
    private static Timer timer = new HashedWheelTimer();
    private ChannelHandlerContext ctx;
    public HeartBeatTimer(ChannelHandlerContext ctx){
        this.ctx = ctx;
    }

    public void start(){
        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                if(timeout.isCancelled()||ctx.isRemoved()){
                    return;
                }
                if(isTimeOut()){
                    handlerTimeOut();
                }
                if(needLoop()){
                    start();
                }
            }
        },1, TimeUnit.SECONDS);
    }

    private boolean isTimeOut(){
        return System.currentTimeMillis() - ChannelPropertiesUtil.getLastHeartBeatTime(ctx)>4*900;
    }

    private void handlerTimeOut(){
        ctx.channel().close();
        log.info("超过规定心跳时间未收到数据包，断开客户端连接");
    }
    private boolean needLoop(){
        return true;
    }

    public void cancelTask(){
        timer.stop();
    }
}
