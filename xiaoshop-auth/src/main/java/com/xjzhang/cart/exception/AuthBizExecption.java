package com.xjzhang.cart.exception;

import com.xjzhang.base.enums.ErrorCodeEnum;
import com.xjzhang.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/10/2 10:59
 */
@Slf4j
public class AuthBizExecption extends BusinessException {
    public AuthBizExecption() {
    }

    /**
     *
     * @param code      the code
     * @param msgFormat the msg format
     * @param args      the args
     */
    public AuthBizExecption(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
        log.info("<== AuthBizExecption, code:{}, message:{}", this.code, super.getMessage());
    }

    /**
     * Instantiates a new Uac rpc exception.
     *
     * @param code the code
     * @param msg  the msg
     */
    public AuthBizExecption(int code, String msg) {
        super(code, msg);
        log.info("<== AuthBizExecption, code:{}, message:{}", this.code, super.getMessage());
    }

    /**
     * Instantiates a new Omc rpc exception.
     *
     * @param codeEnum the code enum
     */
    public AuthBizExecption(ErrorCodeEnum codeEnum) {
        super(codeEnum.code(), codeEnum.msg());
        log.info("<== AuthBizExecption, code:{}, message:{}", this.code, super.getMessage());
    }

    /**
     * Instantiates a new Omc rpc exception.
     *
     * @param codeEnum the code enum
     * @param args     the args
     */
    public AuthBizExecption(ErrorCodeEnum codeEnum, Object... args) {
        super(codeEnum, args);
        log.info("<== AuthBizExecption, code:{}, message:{}", this.code, super.getMessage());
    }
}
