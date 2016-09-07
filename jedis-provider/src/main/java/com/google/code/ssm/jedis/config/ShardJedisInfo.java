package com.google.code.ssm.jedis.config;

/**
 * Created by Avalon on 2016/9/7.
 */
public class ShardJedisInfo {
    private String port;
    private String url;
    private String auth;

    public ShardJedisInfo(String port, String url, String auth) {
        this.port = port;
        this.url = url;
        this.auth = auth;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
