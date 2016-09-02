package com.google.code.ssm.jedis.util;

import com.alibaba.fastjson.JSON;

/**
 * @version 1.0
 * @author: 侯铭健
 * @email mingjian_hou@kingdee.com
 * @time 2016/9/1
 */
public class FastJsonUtils {

    public static Object string2Object(String value) {
        return JSON.parseObject(value);
    }

    public static String object2String(Object object) {
        return JSON.toJSONString(object);
    }
}
