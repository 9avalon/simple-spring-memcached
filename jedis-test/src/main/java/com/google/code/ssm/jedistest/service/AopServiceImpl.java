package com.google.code.ssm.jedistest.service;

import com.google.code.ssm.api.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author: 侯铭健
 * @email mingjian_hou@kingdee.com
 * @time 2016/8/26
 */
@Service("aopService")
public class AopServiceImpl implements AopService{
    public static final String AOP_SERVICE = "aoptest";

    @Override
    @ReadThroughSingleCache(namespace = AOP_SERVICE, expiration = 3600)
    public UserPO readFromCache(@ParameterValueKeyProvider Long key) {
        UserPO userPO = new UserPO();
        userPO.setUid(key.intValue());
        userPO.setUserName("小黄");
        userPO.setPassword("1234567712323");

        System.out.println("从数据库中读取");
        return userPO;
    }

    @Override
    @ReadThroughMultiCache(namespace = AOP_SERVICE, expiration = 3600)
    public List<UserPO> readFromMuiltCache(@ParameterValueKeyProvider List<Integer> uids) {
        UserPO userPO = new UserPO();
        userPO.setUid(1);
        userPO.setUserName("测试1");
        userPO.setPassword("1234567712323我是小黄");

        UserPO userPO2 = new UserPO();
        userPO2.setUid(2);
        userPO2.setUserName("测试3");
        userPO2.setPassword("12312312xiaohuang");

        List<UserPO> userPOList = new ArrayList<UserPO>();

        userPOList.add(userPO);
        userPOList.add(userPO2);

        System.out.println("从数据库中读取数据");
        return userPOList;
    }

    @Override
    @ReadThroughAssignCache(namespace = AOP_SERVICE, expiration = 3500, assignedKey = "testAssign")
    public UserPO readFromCacheAssign(Long key) {
        UserPO userPO = new UserPO();
        userPO.setUserName("小黄1");
        userPO.setPassword("1234567712323我是小黄");

        System.out.println("从数据库中读取数据");
        return userPO;
    }


    @Override
    @UpdateSingleCache(namespace = AOP_SERVICE, expiration = 3600)
    public void updateCache(@ParameterValueKeyProvider Integer uid, @ParameterDataUpdateContent UserPO userPO) {
        System.out.println("向数据库更新userPO");
    }

    @Override
    @InvalidateSingleCache(namespace = AOP_SERVICE)
    public void delCacheSimple(@ParameterValueKeyProvider Integer key) {
        System.out.println("从数据库中删除该key");
    }



}
