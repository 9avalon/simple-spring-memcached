package com.google.code.ssm.jedis;

import com.google.code.ssm.jedis.util.FastJsonUtils;
import com.google.code.ssm.providers.AbstractRedisClientWrapper;
import com.google.code.ssm.providers.CacheException;
import com.google.code.ssm.providers.CacheTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.net.SocketAddress;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @version 1.0
 * @author: 侯铭健
 * @email mingjian_hou@kingdee.com
 * @time 2016/8/30
 */
@Repository("jedisClientWrapper")
public class JedisClientWrapper extends AbstractRedisClientWrapper {
    Logger LOG = LoggerFactory.getLogger(JedisClientWrapper.class);

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Override
    public boolean add(String key, int exp, Object value) throws TimeoutException, CacheException {
        return false;
    }

    @Override
    public <T> boolean add(String key, int exp, T value, CacheTranscoder transcoder) throws TimeoutException, CacheException {
        LOG.info("test1");
        return false;
    }

    @Override
    public long decr(String key, int by) throws TimeoutException, CacheException {
        return 0;
    }

    @Override
    public long decr(String key, int by, long def) throws TimeoutException, CacheException {
        return 0;
    }

    @Override
    public boolean delete(String key) throws TimeoutException, CacheException {
        return false;
    }

    @Override
    public void delete(Collection<String> keys) throws TimeoutException, CacheException {

    }

    @Override
    public void flush() throws TimeoutException, CacheException {

    }

    @Override
    public Object get(String key) throws TimeoutException, CacheException {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();

        try {
            String result = shardedJedis.get(key);

            // 该key没有缓存
            if (null == result) {
                return null;
            }

            return FastJsonUtils.string2Object(result);
        } catch (Exception e) {
            LOG.error("get cache fail", e);
            return null;
        } finally {
            shardedJedis.close();
        }

    }

    @Override
    public <T> T get(String key, CacheTranscoder transcoder) throws TimeoutException, CacheException {
        return null;
    }

    @Override
    public <T> T get(String key, CacheTranscoder transcoder, long timeout) throws TimeoutException, CacheException {
        return null;
    }

    @Override
    public Collection<SocketAddress> getAvailableServers() {
        return null;
    }

    @Override
    public Map<String, Object> getBulk(Collection<String> keys) throws TimeoutException, CacheException {
        return null;
    }

    @Override
    public <T> Map<String, T> getBulk(Collection<String> keys, CacheTranscoder transcoder) throws TimeoutException, CacheException {
        return null;
    }

    @Override
    public CacheTranscoder getTranscoder() {
        return null;
    }

    @Override
    public long incr(String key, int by) throws TimeoutException, CacheException {
        return 0;
    }

    @Override
    public long incr(String key, int by, long def) throws TimeoutException, CacheException {
        return 0;
    }

    @Override
    public long incr(String key, int by, long def, int exp) throws TimeoutException, CacheException {
        return 0;
    }

    @Override
    public boolean set(String key, int exp, Object value) throws TimeoutException, CacheException {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            shardedJedis.set(key, FastJsonUtils.object2String(value));
            shardedJedis.expire(key.getBytes(), exp);
            return true;
        } catch (Exception e) {
            LOG.error("add cache fail", e);
            return false;
        } finally {
            shardedJedis.close();
        }
    }

    @Override
    public <T> boolean set(String key, int exp, T value, CacheTranscoder transcoder) throws TimeoutException, CacheException {
        return false;
    }

    @Override
    public void shutdown() {

    }

    @Override
    public Object getNativeClient() {
        return null;
    }
}
