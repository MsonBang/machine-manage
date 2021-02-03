package com.golaxy.machine.miaoshao.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

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
    * @Description: 生成token
    * @Params: [userId, password]
    * @Return: java.lang.String
    * @Author: miaoxuebing
    * @Date: 2021/1/31 下午2:57
    **/
    public static String createToken(String userId, String password) {
        try {
            // 将 user id 保存到 token 里面
            return JWT.create()
                    .withClaim("uId", userId)
                    .withClaim("ps", password)
                    //token有效时间保持14天
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 336L))
                    .sign(Algorithm.HMAC256(getValueByKey("config", "token_secret")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getValueByKey(final String fileName, final String key) {
        final PropertyResourceBundle pb = (PropertyResourceBundle) ResourceBundle.getBundle(fileName);
        return pb.getString(key);
    }

}
