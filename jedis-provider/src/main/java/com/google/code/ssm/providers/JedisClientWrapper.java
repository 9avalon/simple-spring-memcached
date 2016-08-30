package com.google.code.ssm.providers;

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
public class JedisClientWrapper extends AbstractRedisClientWrapper {

    private final JedisClient jedisClient;

    public JedisClientWrapper(JedisClient jedisClient) {
        this.jedisClient = jedisClient;
    }

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
        return null;
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
        return false;
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
