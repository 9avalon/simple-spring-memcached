package com.google.code.ssm.jedis;

import com.google.code.ssm.jedis.config.JedisPoolConfig;
import com.google.code.ssm.providers.CacheClient;
import com.google.code.ssm.providers.CacheClientFactory;
import com.google.code.ssm.providers.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * @version 1.0
 * @author: 侯铭健
 * @email mingjian_hou@kingdee.com
 * @time 2016/8/30
 */
@Resource
public class RedisClientFactoryImpl implements CacheClientFactory {
    @Autowired
    JedisClientWrapper jedisClientWrapper;

    JedisPoolConfig config;

    @Override
    public CacheClient create(List<InetSocketAddress> addrs, CacheConfiguration configuration) throws IOException {

        // todo 删除
        System.out.println("tset");
        return jedisClientWrapper;
    }

    public JedisPoolConfig getConfig() {
        return config;
    }

    public void setConfig(JedisPoolConfig config) {
        this.config = config;
    }
}
