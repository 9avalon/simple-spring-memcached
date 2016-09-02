package com.google.code.ssm.jedistest.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @version 1.0
 * @author: 侯铭健
 * @email mingjian_hou@kingdee.com
 * @time 2016/9/1
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-simplecache.xml"})
public class CacheTest{
    @Autowired
    private AopService aopService;

    @Test
    public void testCache() {
        aopService.readFromCache(12345678L);
//        aopService.readFromCache(123456789L);
    }
}
