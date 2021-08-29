package com.xjzhang.common.wrapper;

import org.apache.commons.lang.StringUtils;

/**
 *  统一返回
 * @author xjzhang
 * @version 1.0
 * @date 2021/8/22 16:59
 */
public class ResWrapper {
    public ResWrapper() {
    }

    public static Wrapper ok() {
        return new Wrapper();
    }

    public static Wrapper ok(String msg) {
        return new Wrapper(Wrapper.SUCCESS_CODE, StringUtils.isBlank(msg)? Wrapper.ILLEGAL_ARGUMENT_MSG : msg );
    }

    public static <T> Wrapper<T> ok(T data) {
        return new Wrapper(Wrapper.SUCCESS_CODE, null, data);
    }

    public static <T> Wrapper<T> ok(String msg,  Object data) {
        return new Wrapper(Wrapper.SUCCESS_CODE, null, data);
    }

    public static Wrapper error() {
        return new Wrapper(Wrapper.ERROR_CODE, Wrapper.ERROR_MSG);
    }


    public static Wrapper error(int code) {
        return new Wrapper(code, Wrapper.ERROR_MSG);
    }

    public static Wrapper error(int code, String msg) {
        return new Wrapper(code, msg, null);
    }

    public static Wrapper error(Exception ex) {
        return new Wrapper(Wrapper.ERROR_CODE, ex.getMessage());
    }

    public static Wrapper illegalArgument() {
        return new Wrapper(Wrapper.ILLEGAL_ARGUMENT_CODE, Wrapper.ILLEGAL_ARGUMENT_MSG);
    }
}
