package org.example.server.server.connect.starter;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.05.31
 */
@Slf4j
@Component
public class IMServer implements Runnable{
    @Autowired
    private IMServerChannelInitializer imServerChannelInitializer;

    @Value("${im.server.address}")
    private String imServerAddress;
    @Value("${im.server.port}")
    private int imServerPort;

    private void start() throws Exception{
        //初始化worker以及boss的group
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        //启动服务，开启监听
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                //添加处理器
                .childHandler(imServerChannelInitializer);
        //绑定监听端口号
        ChannelFuture f = bootstrap.bind(new InetSocketAddress(imServerPort)).addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                log.info("[im-server] start successfully at port {}, waiting for clients to connect...", imServerPort);
            } else {
                throw new Exception("[connector] start failed");
            }
        });

        //如果超时，打印超时异常
        try {
            f.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new Exception("[connector] start failed");
        }
    }


    @Override
    public void run() {
        try{
            start();
        }catch (Exception e){
            log.error("ClientServer start error",e);
        }
    }
}
