package com.xjzhang.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/10/1 17:41
 */
@Slf4j
public class JwtUtil {
    private static String accessTokenExpireTime;

    private static String encryptJWTKey;

    public static void setAccessTokenExpireTime(String accessTokenExpireTime) {
        JwtUtil.accessTokenExpireTime = accessTokenExpireTime;
    }

    public static void setEncryptJWTKey(String encryptJWTKey) {
        JwtUtil.encryptJWTKey = encryptJWTKey;
    }

    public static boolean verify(String token) {
        try {
            String secret = getClaim(token, "account") + Base64Util.decode(encryptJWTKey);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWT.require(algorithm).build().verify(token);
            return true;
        } catch (UnsupportedEncodingException ex) {
            log.error("verify异常，不支持该编码", ex);
        }

        return false;
    }

    public static String getClaim(String token, String claim) {
        try {
            return JWT.decode(token).getClaim(claim).asString();
        } catch (JWTDecodeException ex) {
            log.error("sign异常，不支持该编码", ex);
        }

        return null;
    }

    /**
     * jwt 根据用户名、时间戳、密钥生成加密token
     *
     * @param account
     * @param timeStamp
     * @return
     */
    public static String sign(String account, String timeStamp) {
        String secret = account + Base64Util.encode(encryptJWTKey);
        Date date = new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpireTime) * 1000);
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC256(secret);
        } catch (UnsupportedEncodingException ex) {
            log.error("sign异常，不支持该编码", ex);
            return null;
        }

        return JWT.create().withClaim("account", account).withClaim("timeStamp", timeStamp)
                .withExpiresAt(date).sign(algorithm);
    }
}
