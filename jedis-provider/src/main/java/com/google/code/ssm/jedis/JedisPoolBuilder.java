package com.google.code.ssm.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @version 1.0
 * @author: 侯铭健
 * @email mingjian_hou@kingdee.com
 * @time 2016/8/30
 */
@Repository("jedisDataSource")
public class JedisPoolBuilder {
    Logger LOG = LoggerFactory.getLogger(JedisPoolBuilder.class);

    private JedisPoolConfig config;

    private static ShardedJedisPool shardedJedisPool = null;

    

    public ShardedJedis getRedisClient() {
        try {
            return shardedJedisPool.getResource();
        } catch (Exception e) {
            LOG.error("get reids client fail", e);
        }
        return null;
    }


}
