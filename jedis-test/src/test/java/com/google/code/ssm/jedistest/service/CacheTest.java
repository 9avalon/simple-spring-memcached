package com.google.code.ssm.jedistest.service;

import com.google.code.ssm.jedis.util.SerializeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author: 侯铭健
 * @email mingjian_hou@kingdee.com
 * @time 2016/9/1
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-simplecache.xml"})
public class CacheTest {
    @Autowired
    private AopService aopService;

    @Test
    public void testReadThroughCache() {
        UserPO userPO = aopService.readFromCache(2L);
        System.out.println(userPO);
    }

    @Test
    public void testSerializeUtil() throws IOException, ClassNotFoundException {
        String str = "string";
        Object obj = str;
        String serialize = SerializeUtil.serialize(obj);
        System.out.println(serialize);
        Object unserialize = SerializeUtil.unserialize(serialize);

        System.out.println(unserialize.getClass().getName());
        String result = String.valueOf(unserialize);
        System.out.println(result);
    }

    @Test
    public void testReadThroughAssign() {
        aopService.readFromCacheAssign(8L);
        aopService.readFromCacheAssign(8L);
    }

    @Test
    public void testReadThroughMuilt() {
        List<Integer> uids = new ArrayList<Integer>();
        uids.add(1);
        uids.add(3);

        aopService.readFromMuiltCache(uids);
        aopService.readFromMuiltCache(uids);
    }

    @Test
    public void testUpdateSinge() {
        UserPO userPO = new UserPO(1, "小小", "123456678980");
        aopService.updateCache(1, userPO);
    }

    @Test
    public void tsetInvalidateSinge() {
        aopService.delCacheSimple(1);
    }
}
