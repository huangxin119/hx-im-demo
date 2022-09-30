package org.example.server.server.mq;

import com.google.protobuf.Message;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.06.01
 */
public interface MQService {
    /**
     * 通知内部mq
     * @param tag
     * @param message
     */
    void send(String tag, Message message);

    /**
     * 通知业务系统
     * @param tag
     * @param message
     */
    void notifyBusiness(String tag, Message message);
}
