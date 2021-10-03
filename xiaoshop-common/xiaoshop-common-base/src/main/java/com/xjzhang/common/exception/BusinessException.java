package com.xjzhang.common.exception;

import com.xjzhang.common.enums.ErrorCodeEnum;

/**
 * 业务异常
 *
 * @author xjzhang
 * @version 1.0
 * @date 2021/7/12 20:43
 */
public class BusinessException extends RuntimeException {
    protected int code;

    public BusinessException() {}

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessException(int code) {
        super(ErrorCodeEnum.getMsg(code));
        this.code = code;
    }

    public BusinessException(int code, String format, Object... args) {
        super(String.format(format, args));
        this.code = code;
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum, Object... args) {
        super(String.format(errorCodeEnum.msg(), args));
        this.code = errorCodeEnum.code();
    }
}
