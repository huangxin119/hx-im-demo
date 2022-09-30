package org.example.client.client.controller;

import org.example.client.client.api.IMClient;
import org.example.common.po.Ack;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.08.22
 */
@RestController
@RequestMapping("/im")
public class OperateController {
    @Resource
    private IMClient imClient;

    @PostMapping("/init")
    public String login(@RequestParam("userId") Integer userId,@RequestParam("imServerAddress") String imServerAddress
    ,@RequestParam("imServerPort")Integer imServerPort){
        //初始化长连接
        imClient.init(imServerAddress,imServerPort,userId);
        imClient.login(userId);
        //发送登录消息
        return "im初始化成功";
    }

    @PostMapping("/offline")
    public String logout(@RequestParam("userId") Integer userId){
        imClient.loginOut(userId);
        //发送登录消息
        return "im下线成功";
    }

    @PostMapping("/send-test-message")
    public String SendTestMessage(@RequestParam("userId") Integer userId,@RequestParam("message") String message){
        imClient.sendBusinessMessage(userId,message);
        return "发送消息成功";
    }

    @PostMapping("/send-chat-message")
    public String SendChatMessage(@RequestParam("userId") Integer userId,@RequestParam("receiveId") Integer receiveId,
                                  @RequestParam("message") String message){
        imClient.sendChatMsg(userId,receiveId,message);
        return "发送消息成功";
    }

    @PostMapping("/send-chat-message-need-ack")
    public String SendChatMessageNeedAck(@RequestParam("userId") Integer userId,@RequestParam("receiveId") Integer receiveId,
                                  @RequestParam("message") String message,@RequestParam("timeout") Long timeout){
        imClient.sendChatMsgNeedAck(userId,receiveId,message,timeout);
        return "发送消息成功";
    }

    @PostMapping("/send-ack-message")
    public String SendAckMessage(@RequestParam("userId") Integer userId,@RequestParam("receiveId") Integer receiveId){
        Ack.AckMessage ackMessage = Ack.AckMessage.newBuilder()
                .setUserId(userId)
                .setReceiveId(receiveId)
                .setId("ack").build();
        imClient.sendAckMessage(ackMessage);
        return "发送消息成功";
    }

}
