package com.xjzhang.common.base;

import java.util.HashMap;

/**
 * 统一的数据返回封装
 *
 * @author xjzhang
 * @version 1.0
 * @date 2021/7/12 18:21
 */
public class BaseResponse extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public BaseResponse() {
        put("code", 0);
        put("msg", "success");
    }

    public static BaseResponse error() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.put("code", 99999999);
        baseResponse.put("msg", "未知错误");

        return baseResponse;
    }

    public static BaseResponse error(int code) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.put("code", code);
        baseResponse.put("msg", "内部异常");

        return baseResponse;
    }

    public static BaseResponse error(int code, String msg) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.put("code", code);
        baseResponse.put("msg", msg);

        return baseResponse;
    }

    public static BaseResponse ok() {
        return new BaseResponse();
    }

    public static BaseResponse ok(String msg) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.put("code", 0);
        baseResponse.put("msg", msg);

        return baseResponse;
    }

    public static BaseResponse ok(Object data, String msg) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.put("code", 0);
        baseResponse.put("data", data);
        baseResponse.put("msg", msg);

        return baseResponse;
    }

    public static BaseResponse ok(Object data) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.put("code", 0);
        baseResponse.put("data", data);

        return baseResponse;
    }
}
