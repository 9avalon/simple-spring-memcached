package com.google.code.ssm.providers;

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
public class RedisClientFactoryImpl implements CacheClientFactory{
    @Autowired
    JedisClientWrapper jedisClientWrapper;

    @Override
    public CacheClient create(List<InetSocketAddress> addrs, CacheConfiguration configuration) throws IOException {
        return jedisClientWrapper;
    }
}
