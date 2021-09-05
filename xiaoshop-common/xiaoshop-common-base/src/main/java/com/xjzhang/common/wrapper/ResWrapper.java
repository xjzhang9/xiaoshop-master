package com.xjzhang.common.wrapper;

import org.apache.commons.lang3.StringUtils;

/**
 *  统一返回
 * @author xjzhang
 * @version 1.0
 * @date 2021/8/22 16:59
 */
public class ResWrapper {
    public ResWrapper() {
    }

    public static BaseWrapper ok() {
        return new BaseWrapper();
    }

    public static BaseWrapper ok(String msg) {
        return new BaseWrapper(BaseWrapper.SUCCESS_CODE, StringUtils.isBlank(msg)? BaseWrapper.SUCCESS_MSG : msg);
    }

    public static <T> BaseWrapper<T> ok(T data) {
        return new BaseWrapper(BaseWrapper.SUCCESS_CODE, BaseWrapper.SUCCESS_MSG, data);
    }

    public static <T> BaseWrapper<T> ok(String msg, Object data) {
        return new BaseWrapper(BaseWrapper.SUCCESS_CODE, StringUtils.isBlank(msg)? BaseWrapper.SUCCESS_MSG : msg, data);
    }

    public static BaseWrapper error() {
        return new BaseWrapper(BaseWrapper.ERROR_CODE, BaseWrapper.ERROR_MSG);
    }

    public static BaseWrapper error(int code) {
        return new BaseWrapper(code, BaseWrapper.ERROR_MSG);
    }

    public static BaseWrapper error(int code, String msg) {
        return new BaseWrapper(code, msg, null);
    }

    public static<T> BaseWrapper<T> error(int code, String msg, T data) {
        return new BaseWrapper(code, msg, data);
    }

    public static BaseWrapper error(Exception ex) {
        return new BaseWrapper(BaseWrapper.ERROR_CODE, ex.getMessage());
    }

    public static BaseWrapper illegalArgument() {
        return new BaseWrapper(BaseWrapper.ILLEGAL_ARGUMENT_CODE, BaseWrapper.ILLEGAL_ARGUMENT_MSG);
    }
}
