package com.xjzhang.common;

import com.xjzhang.common.model.LoginUserDto;
import com.xjzhang.common.wrapper.BaseWrapper;
import com.xjzhang.common.wrapper.ResWrapper;
import com.xjzhang.utils.EmptyUtil;
/**
 * 控制器基础类
 *
 * @author xjzhang
 * @version 1.0
 * @date 2021/7/12 21:05
 */
public class BaseController {
    /**
     * 获取用户登录信息
     *
     * @return
     */
    protected LoginUserDto getLoginUserDto() {

        return null;
    }

    protected <T> BaseWrapper<T> handleResult(T result) {
        if (isFlag(result)) {
            return ResWrapper.ok(result);
        } else {
            return ResWrapper.error(BaseWrapper.ERROR_CODE, BaseWrapper.ERROR_MSG, result);
        }
    }

    protected <T> BaseWrapper<T> handleResult(T result, String errorMsg) {
        if (isFlag(result)) {
            return ResWrapper.ok(result);
        } else {
            return ResWrapper.error(BaseWrapper.ERROR_CODE, errorMsg, result);
        }
    }

    protected <T> BaseWrapper<T> handleResult(T result, int errorCode, String errorMsg) {
        if (isFlag(result)) {
            return ResWrapper.ok(result);
        } else {
            return ResWrapper.error(errorCode, errorMsg, result);
        }
    }

    private boolean isFlag(Object result) {
        boolean flag;

        if (result instanceof Integer) {
            flag = (Integer) result > 0;
        } else if (result instanceof Boolean) {
            flag = (Boolean) result;
        } else {
            flag = EmptyUtil.isNotEmpty(result);
        }

        return flag;
    }

    /**
     * 生成唯一id
     *
     * @return
     */
    protected long generateId() {
        return -1;
    }
}
