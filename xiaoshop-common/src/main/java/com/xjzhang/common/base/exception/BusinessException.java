package com.xjzhang.common.base.exception;

import com.xjzhang.common.base.enums.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * 业务异常
 *
 * @author xjzhang
 * @version 1.0
 * @date 2021/7/12 20:43
 */
@Slf4j
public class BusinessException extends RuntimeException {
    private int code;

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
