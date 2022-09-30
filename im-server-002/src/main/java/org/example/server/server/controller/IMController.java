package org.example.server.server.controller;

import com.google.protobuf.ByteString;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.example.common.po.Business;
import org.example.common.po.Chat;
import org.example.server.server.connect.util.SessionConnectPool;
import org.example.server.server.mq.MQService;
import org.example.server.server.mysql.UserAccount;
import org.example.server.server.mysql.mapper.UserAccountMapper;
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
public class IMController {

    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private MQService mqService;

    @RequestMapping("/login")
    public String login(@RequestParam("userName") String userName, @RequestParam("password") String password){
        UserAccount userAccount = userAccountMapper.selectValidAccount(userName,password);
        if(userAccount==null){
            return "账号名或者密码错误";
        }else {
            return userAccount.getUserId().toString();
        }
    }

    @RequestMapping("get-ip-list")
    public String getIpList(@RequestParam("userId") Integer userId, @RequestParam("ip") String ip){
        return "175.178.29.54:9090";
    }

    @PostMapping("/send-test-message")
    public String SendTestMessage(@RequestParam("userId") Integer userId,@RequestParam("message") String message){
        NioSocketChannel channel = SessionConnectPool.getChannelByUserId(userId.toString());
        if(channel==null){
            return "用户不在线";
        }
        Business.BusinessMessage messageDo = Business.BusinessMessage.newBuilder()
                .setUserId(userId)
                .setMsgBody(message)
                .build();
        channel.writeAndFlush(messageDo);
        return "发送消息成功";
    }
    @PostMapping("/test-mq")
    public String SendTestMqMessage(@RequestParam("message") String message){
        Chat.ChatMessage chatMessage = Chat.ChatMessage.newBuilder()
                .setUserId(-1)
                .setReceiveId(-1)
                .setMsgBody(ByteString.copyFromUtf8(message)).build();
        mqService.send("",chatMessage);
        return "发送消息成功";
    }


}
