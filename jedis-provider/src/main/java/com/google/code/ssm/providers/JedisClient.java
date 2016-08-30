package com.google.code.ssm.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClient {
    protected static Logger logger = LoggerFactory.getLogger(JedisClient.class);

    private String host;
    private int port;
    private String pwd;
    private int maxAacitve = 30;
    private int maxIdle = 8;
    private int maxWait = -1;
    private int timeout = 10000;
    private static JedisPool pool;

    public JedisClient() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(maxAacitve);
            config.setMaxIdle(maxIdle);
            config.setMaxWaitMillis(maxWait);

            pool = new JedisPool(config, host, port, timeout);
        } catch (Exception e) {
            // TODO 完善错误信息
            logger.error("create jedis pool fail", e);
        }
    }

    /**
     * 获取连接实例
     * @return
     */
    public Jedis getClient() {
        Jedis jedis = null;
        if (null == pool) {
            return null;
        }

        try {
            jedis = pool.getResource();
        } catch (Exception e) {
            logger.error("get jedis client fail", e);
        } finally {
            returnResource(jedis);
        }

        return jedis;
    }

    /**
     * 释放jedis资源
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null && pool !=null) {
            pool.returnResource(jedis);
        }
    }
}
