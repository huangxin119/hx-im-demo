package org.example.server.server.connect.starter;

import lombok.extern.slf4j.Slf4j;
import org.example.common.server.ServerNode;
import org.example.server.server.connect.util.ServerNodeUtil;
import org.example.server.server.redis.IRedisService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @desc： im服务器的启动类
 * @author: huangxin
 * @date: 2022.05.24
 */
@Component
@Slf4j
public class IMServerStarter implements InitializingBean {
    @Value("${im.server.address}")
    private String imServerAddress;
    @Value("${im.server.port}")
    private int imServerPort;

    @Autowired
    private IMServer imServer;
    @Resource
    private IRedisService iRedisService;



    //服务端启动方法
    public void start() throws Exception {
        //启动客户端连接
        new Thread(imServer).start();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        try{
            //初始化服务节点信息
            ServerNode serverNode = new ServerNode(imServerAddress+"-"+imServerPort,imServerAddress,imServerPort);
            ServerNodeUtil.setServerNode(serverNode);
            start();
        }catch (Exception e){
            log.error("IMServer start error",e);
        }
    }
}
