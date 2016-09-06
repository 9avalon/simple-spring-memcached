package com.google.code.ssm.jedis;

import com.google.code.ssm.jedis.util.SerializeUtil;
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
import java.util.HashMap;
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
    Logger logger = LoggerFactory.getLogger(JedisClientWrapper.class);

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    // 构造函数，传入shardJedisPool

    @Override
    public boolean add(String key, int exp, Object value) throws TimeoutException, CacheException {
        return false;
    }

    @Override
    public <T> boolean add(String key, int exp, T value, CacheTranscoder transcoder) throws TimeoutException, CacheException {
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
        ShardedJedis shardedJedis = shardedJedisPool.getResource();

        try {
            shardedJedis.del(key);
        } catch (Exception e) {
            logger.error("fail to delete the cache", e);
            return false;
        } finally {
            shardedJedis.close();
        }
        return true;
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
            String strValue = shardedJedis.get(key);

            if (null == strValue) {
                return null;
            }

            // 反序列化
            Object value = SerializeUtil.unserialize(strValue);
            return value;
        } catch (Exception e) {
            logger.error("get cache fail", e);
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
        ShardedJedis shardedJedis = shardedJedisPool.getResource();

        Map<String, Object> resultMap = null;
        try {
            resultMap = new HashMap<String, Object>();
            for (String s : keys) {
                Object o = shardedJedis.get(s);
                if (null != o) {
                    resultMap.put(s, o);
                }
            }
        } catch (Exception e) {
            logger.error("fail to get mulit cache value", e);
        } finally {
            shardedJedis.close();
        }

        return resultMap;
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
            String str = SerializeUtil.serialize(value);
            shardedJedis.set(key, str);
//            shardedJedis.set(key, Base64.getBase64(str));
            shardedJedis.expire(key, exp);
            return true;
        } catch (Exception e) {
            logger.error("add cache fail", e);
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
