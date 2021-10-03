package com.xjzhang.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/10/1 17:43
 */
@Data
@Slf4j
public class Base64Util {
    /**
     * 加密
     */
    public static String encode(String str) {
        try{
            byte[] encodeBytes = Base64.getEncoder().encode(str.getBytes("utf-8"));
            return new String(encodeBytes);
        } catch (UnsupportedEncodingException ex) {
           log.error("Base64 加密异常，不支持该编码", ex);
        }

        return null;
    }

    /**
     * 解密
     */
    public static String decode(String str) {
        try {
            byte[] decodeBytes = Base64.getDecoder().decode(str.getBytes("utf-8"));
            return new String(decodeBytes);
        } catch (UnsupportedEncodingException ex) {
            log.error("Base64 解密异常，不支持该编码", ex);
        }

        return null;
    }
}
