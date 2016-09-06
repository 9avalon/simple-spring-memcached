package com.google.code.ssm.jedistest.service;

import java.util.List;

/**
 * @version 1.0
 * @author: 侯铭健
 * @email mingjian_hou@kingdee.com
 * @time 2016/8/26
 */
public interface AopService {

    public UserPO readFromCache(Long key);

    public UserPO readFromCacheAssign(Long key);

    public List<UserPO> readFromMuiltCache(List<Integer> uids);

    public void updateCache(Integer key, UserPO userPO);

    public void delCacheSimple(Integer key);
}
