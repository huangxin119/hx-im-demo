package org.example.common.server;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.06.01
 */
public class ServerNode {

    private String serverName;

    private String ip;

    private int port;

    public ServerNode(String serverName,String ip,int port){
        this.serverName = serverName;
        this.ip = ip;
        this.port = port;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
