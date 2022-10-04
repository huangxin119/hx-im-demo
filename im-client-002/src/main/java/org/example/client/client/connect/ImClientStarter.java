package org.example.client.client.connect;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.example.client.client.connect.handler.ClientHeatBeatStateTrigger;
import org.example.client.client.connect.handler.IMClientMessageHandler;
import org.example.common.coder.ProtoDecoder;
import org.example.common.coder.ProtoEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.05.24
 */
@Component
@Slf4j
public class ImClientStarter {
    @Resource
    private IMClientMessageHandler imClientMessageHandler;

    public Channel connect(String imServerAddress,Integer imServerPort,Integer userId) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        ChannelFuture f = b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("idleStatHandler",new IdleStateHandler(10,5,0));
                        pipeline.addLast("clientHeatBeatStateTrigger",new ClientHeatBeatStateTrigger(userId));
                        pipeline.addLast("decoder",new ProtoDecoder());
                        pipeline.addLast("encoder",new ProtoEncoder());
                        pipeline.addLast("imClientMessageHandler",imClientMessageHandler);
                    }
                }).connect(imServerAddress, imServerPort)
                .addListener((ChannelFutureListener) future -> {
                    if (future.isSuccess()) {
                        log.info("[im-client] connect to connector successfully");
                    } else {
                        throw new Exception("[im-client] connect to connector failed!");
                    }
                });

        try {
            f.get(10, TimeUnit.SECONDS);

        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            throw new Exception("[im-client] connect to connector failed!");
        }
        return f.channel();
    }

}
