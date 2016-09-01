package com.google.code.ssm.jedis;

/**
 * @version 1.0
 * @author: 侯铭健
 * @email mingjian_hou@kingdee.com
 * @time 2016/8/30
 */
public interface RedisDataSource<T> {
    public T getRedisClient();

    public void returnResource(T t);

    public void returnResource(T t, boolean broken);
}
