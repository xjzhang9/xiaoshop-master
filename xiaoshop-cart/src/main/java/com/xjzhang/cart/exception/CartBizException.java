package com.xjzhang.cart.exception;

import com.xjzhang.base.enums.ErrorCodeEnum;
import com.xjzhang.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/10/6 9:44
 */
@Slf4j
public class CartBizException extends BusinessException {
    public CartBizException() {
    }

    /**
     *
     * @param code      the code
     * @param msgFormat the msg format
     * @param args      the args
     */
    public CartBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
        log.info("<== CartBizException, code:{}, message:{}", this.code, super.getMessage());
    }

    /**
     *
     * @param code the code
     * @param msg  the msg
     */
    public CartBizException(int code, String msg) {
        super(code, msg);
        log.info("<== CartBizException, code:{}, message:{}", this.code, super.getMessage());
    }

    /**
     *
     * @param codeEnum the code enum
     */
    public CartBizException(ErrorCodeEnum codeEnum) {
        super(codeEnum.code(), codeEnum.msg());
        log.info("<== CartBizException, code:{}, message:{}", this.code, super.getMessage());
    }

    /**
     *
     * @param codeEnum the code enum
     * @param args     the args
     */
    public CartBizException(ErrorCodeEnum codeEnum, Object... args) {
        super(codeEnum, args);
        log.info("<== CartBizException, code:{}, message:{}", this.code, super.getMessage());
    }
}
