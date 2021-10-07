package com.xjzhang.pro.exception;

import com.xjzhang.base.enums.ErrorCodeEnum;
import com.xjzhang.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/10/6 9:44
 */
@Slf4j
public class ProBizException extends BusinessException {
    public ProBizException() {
    }

    /**
     *
     * @param code      the code
     * @param msgFormat the msg format
     * @param args      the args
     */
    public ProBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
        log.info("<== ProBizException, code:{}, message:{}", this.code, super.getMessage());
    }

    /**
     *
     * @param code the code
     * @param msg  the msg
     */
    public ProBizException(int code, String msg) {
        super(code, msg);
        log.info("<== ProBizException, code:{}, message:{}", this.code, super.getMessage());
    }

    /**
     *
     * @param codeEnum the code enum
     */
    public ProBizException(ErrorCodeEnum codeEnum) {
        super(codeEnum.code(), codeEnum.msg());
        log.info("<== ProBizException, code:{}, message:{}", this.code, super.getMessage());
    }

    /**
     *
     * @param codeEnum the code enum
     * @param args     the args
     */
    public ProBizException(ErrorCodeEnum codeEnum, Object... args) {
        super(codeEnum, args);
        log.info("<== ProBizException, code:{}, message:{}", this.code, super.getMessage());
    }
}
