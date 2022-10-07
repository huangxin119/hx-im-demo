package org.example.client.client.api.impl;

import com.google.protobuf.ByteString;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.example.client.client.api.IMClient;
import org.example.client.client.connect.ImClientStarter;
import org.example.common.po.Ack;
import org.example.common.po.Business;
import org.example.common.po.Chat;
import org.example.common.po.Online;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.05.26
 */
@Service
@Slf4j
public class IMClientImpl implements IMClient {

    //定义长连接相关的各个属性
    private Channel channel;
    private String imServerAddress;
    private Integer imServerPort;
    private Integer userId;

    @Resource
    private ImClientStarter imClientStarter;

    @Override
    public boolean init(String imServerAddress, Integer imServerPort,Integer userId) {
        try {
            closeConnect();
            this.channel = imClientStarter.connect(imServerAddress,imServerPort,userId);
            this.imServerAddress = imServerAddress;
            this.imServerPort = imServerPort;
            this.userId = userId;
        }catch (Exception e){
            log.error("客户端连接im服务端初始化失败，e is ",e);
            return false;
        }
        return true;
    }

    @Override
    public boolean login(int userId) {
        if(channel==null){
            log.error("通道未初始化，发送登录消息失败");
            return false;
        }
        try {
            Online.OnlineMessage onlineMessage = Online.OnlineMessage.newBuilder()
                    .setId("test-online")
                    .setMsgType(Online.OnlineMessage.MsgType.ONLINE)
                    .setTimeStamp(new Date().getTime())
                    .setUserId(userId).build();
            channel.writeAndFlush(onlineMessage);
        } catch (Exception ex) {
            log.error("发送登录消息失败，usrId is {},e is {}",userId,ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean loginOut(int userId) {
        if(channel==null){
            log.error("通道未初始化，发送登录消息失败");
            return false;
        }
        try {
            Online.OnlineMessage onlineMessage = Online.OnlineMessage.newBuilder()
                    .setId("test-offline")
                    .setMsgType(Online.OnlineMessage.MsgType.OFFLINE)
                    .setTimeStamp(new Date().getTime())
                    .setUserId(userId).build();
            channel.writeAndFlush(onlineMessage);
        } catch (Exception ex) {
            log.error("发送登录消息失败，usrId is {},e is {}",userId,ex);
            return false;
        }
        return true;
    }

    @Override
    public void sendBusinessMessage(int userId, String message) {
        if(channel==null){
            log.error("通道未初始化，发送登录消息失败");
            return;
        }
        try {
            Business.BusinessMessage businessMessage = Business.BusinessMessage.newBuilder()
                    .setId("test-bus")
                    .setUserId(userId)
                    .setMsgBody(message).build();
            channel.writeAndFlush(businessMessage);
        } catch (Exception ex) {
            log.error("发送登录消息失败，usrId is {},e is {}",userId,ex);
        }
    }

    @Override
    public void sendChatMsg(Integer userId,Integer receiveId, String content) {
        if(channel==null){
            log.error("通道未初始化，发送登录消息失败");
            return;
        }
        try {
            Chat.ChatMessage chatMessage = Chat.ChatMessage.newBuilder()
                    .setId(String.valueOf(new Date()))
                    .setUserId(userId)
                    .setReceiveId(receiveId)
                    .setMsgType(Chat.ChatMessage.MsgType.TEXT)
                    .setMsgBody(ByteString.copyFromUtf8(content))
                    .setTimeStamp(new Date().getTime())
                    .build();
            channel.writeAndFlush(chatMessage);
        } catch (Exception ex) {
            log.error("发送聊天消息失败，usrId is {},e is {}",userId,ex);
        }
    }

    @Override
    public boolean sendChatMsgNeedAck(Integer userId, Integer receiveId, String content, long timeout) {
        if(channel==null){
            log.error("通道未初始化，发送登录消息失败");
            return false;
        }
        try {
            Chat.ChatMessage chatMessage = Chat.ChatMessage.newBuilder()
                    .setId("test-chat-ack")
                    .setUserId(userId)
                    .setReceiveId(receiveId)
                    .setMsgType(Chat.ChatMessage.MsgType.TEXT)
                    .setMsgBody(ByteString.copyFromUtf8(content))
                    .setTimeStamp(new Date().getTime())
                    .setNeedAck(true)
                    .build();
            channel.writeAndFlush(chatMessage);
        } catch (Exception ex) {
            log.error("发送聊天消息失败，usrId is {},e is {}",userId,ex);
        }
        return true;
    }

    @Override
    public boolean sendAckMessage(Ack.AckMessage ackMessage) {
        channel.writeAndFlush(ackMessage);
        return true;
    }


    private void closeConnect(){
        //关闭channel
        try {
            if (channel != null) {
                channel.close();
                channel.eventLoop().shutdownGracefully();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("关闭channel出错，reason:" + e.getMessage());
        } finally {
            channel = null;
        }
        channel = null;
    }
}
