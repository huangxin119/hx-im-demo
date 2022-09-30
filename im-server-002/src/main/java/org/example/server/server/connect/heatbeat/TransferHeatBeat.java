package org.example.server.server.connect.heatbeat;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ScheduledFuture;
import lombok.extern.slf4j.Slf4j;
import org.example.server.server.connect.util.ServerNodeUtil;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TransferHeatBeat extends ChannelInboundHandlerAdapter {

    private  int baseNum = 4;
    private Random random = new Random();


    //连接可用后触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.ping(ctx.channel());
    }

    //每隔一段时间发送心跳包
    private void ping(Channel channel){
        //随机生成间隔时间,间隔时间范围为1-8s
        int second = Math.max(1,random.nextInt(baseNum));
        log.info("next heartbeat will send after "+second+" seconds");
        ScheduledFuture<?> future = channel.eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                if(channel.isActive()){
                    String heartbeat = JSON.toJSONString(ServerNodeUtil.getServerNode().toString());
                    channel.writeAndFlush(heartbeat);
                    log.info(heartbeat);
                }else {
                    log.error("connect is close,can not send heartbeat");
                    channel.closeFuture();
                    throw new RuntimeException();
                }
            }
        },second, TimeUnit.SECONDS);
        //添加监听器，如果心跳成功，继续执行心跳方法
        future.addListener(new GenericFutureListener(){
            @Override
            public void operationComplete(Future future){
                if(future.isSuccess()){
                    ping(channel);
                }
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
