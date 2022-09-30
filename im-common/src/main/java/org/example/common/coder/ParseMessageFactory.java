package org.example.common.coder;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import org.example.common.po.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.06.07
 */
public class ParseMessageFactory {
    private Map<MessageTypeEnum, Parse> parseFunctionMap;

    public ParseMessageFactory() {
        parseFunctionMap = new HashMap<>(MessageTypeEnum.values().length);
        parseFunctionMap.put(MessageTypeEnum.CHAT, Chat.ChatMessage::parseFrom);
        parseFunctionMap.put(MessageTypeEnum.HEARTBEAT, HeartBeat.HeartbeatMessage::parseFrom);
        parseFunctionMap.put(MessageTypeEnum.ONLINE, Online.OnlineMessage::parseFrom);
        parseFunctionMap.put(MessageTypeEnum.BUSINESS, Business.BusinessMessage::parseFrom);
        parseFunctionMap.put(MessageTypeEnum.ACK, Ack.AckMessage::parseFrom);
    }

    public Message getMsgByCode(int code, byte[] bytes) throws InvalidProtocolBufferException {
        MessageTypeEnum msgType = MessageTypeEnum.getByCode(code);
        Parse parseFunction = parseFunctionMap.get(msgType);
        return parseFunction.process(bytes);
    }

    @FunctionalInterface
    public interface Parse {
        /**
         * parse msg
         *
         * @param bytes msg bytes
         * @return
         * @throws InvalidProtocolBufferException
         */
        Message process(byte[] bytes) throws InvalidProtocolBufferException;
    }
}
