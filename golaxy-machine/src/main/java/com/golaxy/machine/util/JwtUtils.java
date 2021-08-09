package com.golaxy.machine.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author miaoxuebing
 * @Description: TODO[JWT用户token生成校验类]
 * @date 2021/7/16 下午1:44
 */
@Component
public class JwtUtils {

    private Logger logger = LoggerFactory.getLogger(getClass());

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
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                    .sign(Algorithm.HMAC256(getValueByKey("config", "token_secret")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: jwt解析token
     * @Params: [token]
     * @Return: java.lang.String
     * @Author: miaoxuebing
     * @Date: 2021/7/16 下午2:02
     **/
    public static String analysisJwtToken(String token) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256(getValueByKey("config", "token_secret"));
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        String userId = jwt.getClaim("uId").asString();
        return userId;

    }

    /**
     * @Description: 获取配置文件里面的参数对应值
     * @Params: [fileName, key]
     * @Return: java.lang.String
     * @Author: miaoxuebing
     * @Date: 2021/7/16 下午1:58
     **/
    public static String getValueByKey(final String fileName, final String key) {
        final PropertyResourceBundle pb = (PropertyResourceBundle) ResourceBundle.getBundle(fileName);
        return pb.getString(key);
    }

}
