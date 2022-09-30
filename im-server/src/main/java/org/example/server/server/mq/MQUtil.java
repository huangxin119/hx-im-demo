package org.example.server.server.mq;

import com.google.protobuf.Message;
import org.example.common.po.MessageTypeEnum;


/**
 * @desc：
 * @author: huangxin
 * @date: 2022.08.30
 */
public class MQUtil {
    public static byte[] encode(Message message){
        byte[] bytes = message.toByteArray();
        byte[] type =  intTooBytes(MessageTypeEnum.getByClass(message.getClass()).getCode());
        return byteMerger(type,bytes);
    }
    public static int getMessageType(byte[] bytes){
        byte[] type = new byte[4];
        System.arraycopy(bytes,0,type,0,4);
        return bytesToInt(type);
    }

    public static byte[] getMessageBytes(byte[] bytes){
        byte[] type = new byte[bytes.length-4];
        System.arraycopy(bytes,4,type,0,type.length);
        return type;
    }


    /**
     * 合并byte[]数组 （不改变原数组）
     * @param byte_1
     * @param byte_2
     * @return 合并后的数组
     */
    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2){
        byte[] byte_3 = new byte[byte_1.length+byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }


    /**
     * int转为4长度的byte数组
     * @param n
     * @return
     */
    public static byte[] intTooBytes(int n) {
        byte[] b = new byte[4];
        b[3] = (byte) (n & 0xff);
        b[2] = (byte) (n >> 8 & 0xff);
        b[1] = (byte) (n >> 16 & 0xff);
        b[0] = (byte) (n >> 24 & 0xff);
        return b;
    }

    /**
     * byte数组转int
     * @param b
     * @return
     */
    public static int bytesToInt(byte[] b){
        int res = 0;
        for(int i=0;i<b.length;i++){
            res += (b[i] & 0xff) << ((3-i)*8);
        }
        return res;
    }

}
