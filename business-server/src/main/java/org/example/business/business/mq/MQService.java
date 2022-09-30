package org.example.business.business.mq;

import com.google.protobuf.Message;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.06.01
 */
public interface MQService {
    void send(String tag, Message message);
}
