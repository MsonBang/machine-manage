package com.golaxy.machine.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * @author miaoxuebing
 * @Description: 通用公共类
 * @date 2021/1/31 下午2:49
 */
public class UtilsApi {

    /**
     * @Description: 将对象类型转为字符串类型
     * @Params: [obj]
     * @Return: java.lang.String
     * @Author: miaoxuebing
     * @Date: 2021/1/31 下午2:50
     **/
    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        } else if (obj instanceof String) {
            return (String) obj;
        } else {
            return obj.toString();
        }
    }

    /**
    * @Description:
    * @Params: [str]
    * @Return: boolean
    * @Author: miaoxuebing
    * @Date: 2021/2/3 下午3:45
    **/
    public static boolean isNull(String str) {
        if (null == str) {
            return true;
        }
        return "".equals(str.trim());
    }

    /**
    * @Description: 获取随机UUID字符串，去掉-
    * @Params: []
    * @Return: java.lang.String
    * @Author: miaoxuebing
    * @Date: 2021/2/3 下午4:15
    **/
    public static String getUUIDStr() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
