package com.google.code.ssm.jedis;

import com.google.code.ssm.jedis.config.ShardJedisInfo;
import com.google.code.ssm.jedis.config.ShardJedisPoolConfig;
import com.google.code.ssm.providers.CacheClient;
import com.google.code.ssm.providers.CacheClientFactory;
import com.google.code.ssm.providers.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedisPool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * @version 1.0
 * @author: 侯铭健
 * @email mingjian_hou@kingdee.com
 * @time 2016/8/30
 */
@Component
public class RedisClientFactoryImpl implements CacheClientFactory {
    // 配置文件
    ShardJedisPoolConfig config;
    List<ShardJedisInfo> infoList;

    @Override
    public CacheClient create(List<InetSocketAddress> addrs, CacheConfiguration configuration) throws IOException {
        ShardedJedisPool pool = JedisPoolBuilder.getJedisPool(config, infoList);
        return new JedisClientWrapper(pool);
    }

    public ShardJedisPoolConfig getConfig() {
        return config;
    }

    public void setConfig(ShardJedisPoolConfig config) {
        this.config = config;
    }

    public List<ShardJedisInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<ShardJedisInfo> infoList) {
        this.infoList = infoList;
    }
}
