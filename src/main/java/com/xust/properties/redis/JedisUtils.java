package com.xust.properties.redis;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * @author: Victor
 * @create: 2019-08-14 22:49
 **/
@Component
public class JedisUtils {

    private static JedisPool jedisPool;

    static {
        InputStream is = JedisUtils.class.getClassLoader().getResourceAsStream("application.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(properties.getProperty("redis.pool-max-total")));
        config.setMaxIdle(Integer.parseInt(properties.getProperty("redis.pool-max-idle")));
        config.setMaxWaitMillis(Integer.parseInt(properties.getProperty("spring.redis.jedis.pool.max-active")));

        jedisPool = new JedisPool(config,properties.getProperty("spring.redis.host"),Integer.parseInt(properties.getProperty("spring.redis.port")),Integer.parseInt(properties.getProperty("spring.redis.database")));
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    public static void close(Jedis jedis) {
        if (jedis!=null){
            jedis.close();
        }
    }

}
