package org.example.client.client.api;


import org.example.common.po.Ack;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.05.26
 */
public interface IMClient {
    /**
     *
     * @param imServerAddress
     * @param imServerPort
     * @return
     */
    boolean init(String imServerAddress, Integer imServerPort, Integer userId);

    /**
     *
     * @param userId
     * @return
     */
    boolean login(int userId);

    /**
     *
     * @param userId
     * @return
     */
    boolean loginOut(int userId);

    void sendBusinessMessage(int userId, String message);

    /**
     * 发送消息，不关注返回值
     *
     * @param receiveId
     */
    void sendChatMsg(Integer userId, Integer receiveId, String content);


    /**
     * 发送自定义确认消息,Timeout未返回视为失败
     *
     */
    boolean sendChatMsgNeedAck(Integer userId, Integer receiveId, String content, long timeout);

    boolean sendAckMessage(Ack.AckMessage ackMessage);
}
