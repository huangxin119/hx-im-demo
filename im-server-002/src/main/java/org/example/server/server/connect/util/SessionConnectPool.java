package org.example.server.server.connect.util;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 保存连接的信息
 */
public class SessionConnectPool {
    //存储用户id对应的连接信息
    private static final Map<String, NioSocketChannel> nioSocketChannelMap = new ConcurrentHashMap<>();

    public static void putConnectMessage(String usrId,NioSocketChannel nioSocketChannel){
        if(usrId!=null&&nioSocketChannel!=null){
            nioSocketChannelMap.put(usrId,nioSocketChannel);
        }
    }

    public static void removeConnectMessage(String usrId){
        if(usrId!=null&&nioSocketChannelMap.get(usrId)!=null){
            nioSocketChannelMap.remove(usrId);
        }

    }

    public static NioSocketChannel getChannelByUserId(String userId){
        return nioSocketChannelMap.get(userId);
    }
}
