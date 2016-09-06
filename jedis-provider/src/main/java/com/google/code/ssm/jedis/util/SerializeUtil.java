package com.google.code.ssm.jedis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 序列化工具
 */
public class SerializeUtil {
    private static final Logger logger = LoggerFactory.getLogger(SerializeUtil.class);

    /**
     * 反序列化
     * @param serStr
     * @return
     */
    public static Object unserialize(String serStr) {
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        Object result = null;
        try {
            String redStr = java.net.URLDecoder.decode(serStr, "UTF-8");
            byteArrayInputStream = new ByteArrayInputStream(
                    redStr.getBytes("ISO-8859-1"));
            objectInputStream = new ObjectInputStream(
                    byteArrayInputStream);
            result = objectInputStream.readObject();
        } catch (Exception e) {
            logger.error("fial to unserialize the string", e);
        } finally {
            try {
                objectInputStream.close();
                byteArrayInputStream.close();
            } catch (IOException e) {
                logger.error("close inputstream fail", e);
            }
        }

        return result;
    }

    /**
     * 序列化
     * @param obj
     * @return
     */
    public static String serialize(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        String serStr = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            serStr = new String(bytes, "ISO-8859-1");
            serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
        } catch (IOException e) {
            logger.error("fail to serialize object", e);
        } finally {
            try {
                objectOutputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                logger.error("fail to close outputstream", e);
            }
        }


        return serStr;
    }
}

