package com.xust.properties.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;


/**
 * @author: Victor
 * @create: 2019-08-14 21:08
 **/
@Service
public class RedisService {

    @Autowired
    private JedisUtils jedisUtils;

    /**
     * 获取单个对象
     * @param prefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix prefix,String key,Class<T> clazz) {
        Jedis jedis = jedisUtils.getJedis();
        try{ String realkey = prefix.getPrefix() + key;
        String str = jedis.get(realkey);
        T t = string2Bean(str,clazz);
        return t;}
        finally {
            JedisUtils.close(jedis);
        }
    }

    @SuppressWarnings("unchecked")
    public static  <T> T string2Bean(String str, Class<T> clazz) {
        if(str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if(clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(str);
        }else if(clazz == String.class) {
            return (T)str;
        }else if(clazz == long.class || clazz == Long.class) {
            return  (T)Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    public static  <T> String bean2String(T value) {
        if(value==null) return "";
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz== Integer.class)
        {
            return ""+value;
        }else if(clazz == String.class){
            return (String)value;
        }else if(clazz == Long.class || clazz == long.class){
            return ""+value;
        }else return JSON.toJSONString(value);
    }

    /**
     * 设置对象
     * @param prefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix prefix,String key,T value) {

        Jedis jedis = jedisUtils.getJedis();

        try{
            String str = bean2String(value);
            if(str == null || str.length()<=0) return false;
            int seconds = prefix.expireSeconds();
            String realkey = prefix.getPrefix() + key;
            if(seconds <= 0){
            //不过期
            jedis.set(realkey,str);
            }else {
            jedis.setex(realkey,seconds,str);
            }
            }finally {
            jedisUtils.close(jedis);
        }
        return true;
    }

    /**
     * 判断key是否存在
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> boolean exists(KeyPrefix prefix,String key) {
        Jedis jedis = jedisUtils.getJedis();
        try{ String realkey = prefix.getPrefix() + key;
        return jedis.exists(realkey);
        } finally {
            jedisUtils.close(jedis);
        }
    }

    /**
     * 增加值
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long increase(KeyPrefix prefix,String key) {
        Jedis jedis = jedisUtils.getJedis();
        try{ String realkey = prefix.getPrefix() + key;
            return jedis.incr(realkey);
        } finally {
            jedisUtils.close(jedis);
        }
    }

    /**
     * 减少值
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long decrease(KeyPrefix prefix,String key) {
        Jedis jedis = jedisUtils.getJedis();
        try{ String realkey = prefix.getPrefix() + key;
            return jedis.decr(realkey);
        } finally {
            jedisUtils.close(jedis);
        }
    }

    public boolean del(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisUtils.getJedis();
            //生成真正的key
            String realKey  = prefix.getPrefix() + key;
            long ret =  jedis.del(realKey);
            return ret > 0;
        }finally {
            jedisUtils.close(jedis);
        }
    }
}
