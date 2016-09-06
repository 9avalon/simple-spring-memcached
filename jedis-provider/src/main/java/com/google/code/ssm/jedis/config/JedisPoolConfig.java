package com.google.code.ssm.jedis.config;

import org.springframework.stereotype.Repository;

/**
 * Jedis连接池的配置
 *
 * Created by Avalon on 2016/9/4.
 */
@Repository("jedisPoolConfig")
public class JedisPoolConfig {

    private Integer maxTotal;

    private Integer maxIdle;

    private Integer maxWaitMinute;

    private Boolean testOnBorrow;

    private Boolean testOnReturn;

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMaxWaitMinute() {
        return maxWaitMinute;
    }

    public void setMaxWaitMinute(Integer maxWaitMinute) {
        this.maxWaitMinute = maxWaitMinute;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }
}
