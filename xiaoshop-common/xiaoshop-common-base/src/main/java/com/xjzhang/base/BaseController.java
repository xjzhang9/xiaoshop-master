package com.xjzhang.base;

import com.xjzhang.base.model.LoginUserDto;
import com.xjzhang.base.utils.EmptyUtil;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 控制器基础类
 *
 * @author xjzhang
 * @version 1.0
 * @date 2021/7/12 21:05
 */
@Slf4j
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
            return ResWrapper.error(BaseWrapper.ERROR_CODE, "操作失败", result);
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
