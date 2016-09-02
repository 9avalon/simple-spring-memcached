package com.google.code.ssm.jedistest.service;

import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.google.code.ssm.api.counter.ReadCounterFromCache;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @author: 侯铭健
 * @email mingjian_hou@kingdee.com
 * @time 2016/8/26
 */
@Service("aopService")
public class AopServiceImpl implements AopService{

    @Override
    @ReadThroughSingleCache(namespace = "aoptest", expiration = 3600)
    public String readFromCache(@ParameterValueKeyProvider Long key) {
        System.out.println("从数据库中读取:" + key);
        return "string";
    }

}
