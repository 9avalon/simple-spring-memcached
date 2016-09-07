package com.google.code.ssm.jedis;

import com.google.code.ssm.jedis.config.ShardJedisInfo;
import com.google.code.ssm.jedis.config.ShardJedisPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author: 侯铭健
 * @email mingjian_hou@kingdee.com
 * @time 2016/8/30
 */
@Repository("jedisDataSource")
public class JedisPoolBuilder {
    Logger LOG = LoggerFactory.getLogger(JedisPoolBuilder.class);

    private ShardJedisPoolConfig config;

    private static volatile ShardedJedisPool shardedJedisPool = null;

    public static ShardedJedisPool getJedisPool(ShardJedisPoolConfig config, List<ShardJedisInfo> infoList) {
        // single
        if (shardedJedisPool == null) {
            synchronized (JedisPoolBuilder.class) {
                if (shardedJedisPool == null) {
                    shardedJedisPool = initShardedJedisPool(config, infoList);
                }
            }
        }
        return shardedJedisPool;
    }

    private static ShardedJedisPool initShardedJedisPool(ShardJedisPoolConfig shardConfig, List<ShardJedisInfo> infoList) {
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxIdle(shardConfig.getMaxIdle());
        config.setMaxWaitMillis(shardConfig.getMaxWaitMinute());
        config.setMaxTotal(shardConfig.getMaxTotal());

        List<JedisShardInfo> shardInfoList = new ArrayList<JedisShardInfo>();
        for (ShardJedisInfo info : infoList) {
            JedisShardInfo jedisShardInfo = new JedisShardInfo(info.getUrl(), info.getPort());
            shardInfoList.add(jedisShardInfo);
        }

        return new ShardedJedisPool(config, shardInfoList);
    }


}
