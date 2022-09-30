package org.example.server.server.redis;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.05.31
 */
public interface IRedisService {
    // 加入元素
    void setValue(String key, String value);
    // 获取元素
    String getValue(String key);
    //删除元素
    void deleteKey(String key);
}
