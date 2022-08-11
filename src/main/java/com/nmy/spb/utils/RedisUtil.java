package com.nmy.spb.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @author nmy
 * @title: RedisUtil
 * @date 2022-08-06 11:47
 */
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
public class RedisUtil {

    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 缓存基本对象，数字，字符，实体等
     *
     * @param key   键
     * @param value 值
     * @param <T>   值类型
     */
    public <T> void setEasyObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * @param key     键
     * @param value   值
     * @param timeout 超时时间
     * @param uid     时间颗粒度
     * @param <T>     值类型
     */
    public <T> void setEasyObject(final String key, final T value, final Integer timeout, final TimeUnit uid) {
        redisTemplate.opsForValue().set(key, value, timeout, uid);
    }

    /**
     * 设置超时
     *
     * @param key   键
     * @param timeS 值
     * @return
     */
    public boolean timeOut(final String key, final long timeS) {
        return timeOut(key, timeS, TimeUnit.SECONDS);
    }

    public boolean timeOut(final String key, final long time, final TimeUnit uid) {
        return redisTemplate.expire(key, time, uid);
    }

    /**
     * 获取基本数据
     * @param key 键
     * @param <T> 需要返回的类型
     * @return
     */
    public <T> T getEasyObject(final String key){
        ValueOperations<String,T> operations= redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 删除单个对象
     * @param key 键
     * @return
     */
    public boolean deleteObject(final String key){
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     * @param c 集合
     * @return
     */
    public long deleteObject(final Collection c){
        return redisTemplate.delete(c);
    }

    /**
     * 获取缓存的对象列表
     * @param val 字符串前缀
     * @return
     */
    public Collection<String> getKeys(final String val){
        return redisTemplate.keys(val);
    }
}
