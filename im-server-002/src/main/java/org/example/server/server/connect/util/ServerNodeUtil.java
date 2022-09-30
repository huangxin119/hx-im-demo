package org.example.server.server.connect.util;

import org.example.common.server.ServerNode;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.06.01
 */
public class ServerNodeUtil {
    private static ServerNode serverNode;

    public static void setServerNode(ServerNode serverNode){
        ServerNodeUtil.serverNode = serverNode;
    }
    public static ServerNode getServerNode(){
        return ServerNodeUtil.serverNode;
    }
}
