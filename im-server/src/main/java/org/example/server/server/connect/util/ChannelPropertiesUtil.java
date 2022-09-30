package org.example.server.server.connect.util;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.example.server.server.connect.heatbeat.HeartBeatTimer;

/**
 * @desc： 给channel配置属性的工具类
 * @author: huangxin
 * @date: 2022.06.09
 */
public class ChannelPropertiesUtil {

    //netty提供了一个属性，可以通过改属性记录ctx的各种自定义参数

    public static AttributeKey<Long> HEARTBEAT_LAST_TIME_KEY = AttributeKey.valueOf("heartbeatLastTime");
    public static AttributeKey<Integer> USER_ID_KEY = AttributeKey.valueOf("userId");
    public static AttributeKey<HeartBeatTimer> HEARTBEAT_TIMER_KEY = AttributeKey.valueOf("heartbeatTimer");

    public static long getLastHeartBeatTime(ChannelHandlerContext ctx){
        return ctx.channel().attr(ChannelPropertiesUtil.HEARTBEAT_LAST_TIME_KEY).get().longValue();
    }
    public static void setLastHeartBeatTime(ChannelHandlerContext ctx,Long lastHeartBeatTime){
        ctx.channel().attr(ChannelPropertiesUtil.HEARTBEAT_LAST_TIME_KEY).set(lastHeartBeatTime);
    }

    public static int getUserIdByCtx(ChannelHandlerContext ctx){
        return ctx.channel().attr(ChannelPropertiesUtil.USER_ID_KEY).get();
    }
    public static void setUserId(ChannelHandlerContext ctx,Integer userId){
        ctx.channel().attr(ChannelPropertiesUtil.USER_ID_KEY).set(userId);
    }
    public static HeartBeatTimer getHeartbeatTimer(ChannelHandlerContext ctx){
        return ctx.channel().attr(ChannelPropertiesUtil.HEARTBEAT_TIMER_KEY).get();
    }
    public static void setHeartbeatTimer(ChannelHandlerContext ctx, HeartBeatTimer heartBeatTimer){
        ctx.channel().attr(ChannelPropertiesUtil.HEARTBEAT_TIMER_KEY).set(heartBeatTimer);
    }

}
