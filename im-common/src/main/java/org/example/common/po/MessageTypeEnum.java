package org.example.common.po;

import java.util.stream.Stream;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.06.07
 */
public enum MessageTypeEnum {
    BUSINESS(1,Business.BusinessMessage.class),
    CHAT(2, Chat.ChatMessage.class),
    HEARTBEAT(3,HeartBeat.HeartbeatMessage.class),
    ONLINE(4,Online.OnlineMessage.class),
    ACK(5, Ack.AckMessage.class);

    private int code;
    private Class<?> clazz;

    MessageTypeEnum(int code, Class<?> clazz) {
        this.code = code;
        this.clazz = clazz;
    }

    public static MessageTypeEnum getByCode(int code){
        return Stream.of(values()).filter(i->i.code==code).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public static MessageTypeEnum getByClass(Class<?> clazz){
        return Stream.of(values()).filter(i->i.clazz==clazz).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public int getCode(){
        return code;
    }
    public Class<?> getClazz(){
        return clazz;
    }
}
