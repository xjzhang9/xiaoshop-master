package com.xjzhang.base.wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xjzhang.base.model.PageInfo;
import com.xjzhang.base.utils.EmptyUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 统一的数据返回封装
 *
 * @author xjzhang
 * @version 1.0
 * @date 2021/7/12 18:21
 */
@JsonSerialize
@Data
public class BaseWrapper<T> implements Serializable {
    private static final long serialVersionUID = -9042417141433503027L;

    public static final int SUCCESS_CODE = 200;

    public static final String SUCCESS_MSG = "操作成功";

    public static final int ERROR_CODE = 500;

    public static final String ERROR_MSG = "内部异常";

    public static final int ILLEGAL_ARGUMENT_CODE = 100;

    public static final String ILLEGAL_ARGUMENT_MSG = "参数非法";

    /**
     * 操作码
     */
    private int code;

    /**
     * 操作信息
     */
    private String msg;

    /**
     * 携带数据
     */
    private T data;


    public BaseWrapper() {
        this(SUCCESS_CODE, SUCCESS_MSG, null);
    }

    public BaseWrapper(int code, String msg) {
        this(code, msg, null);
    }

    public BaseWrapper(int code, String msg, T data) {
        super();
        this.code(code).msg(msg).data(data);
    }

    /**
     * 设置 code
     *
     * @param code
     * @return
     */
    private BaseWrapper code(int code) {
        this.code = code;
        return this;
    }

    /**
     * 设置 msg
     *
     * @param msg
     * @return
     */
    private BaseWrapper msg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * 设置 data
     *
     * @param data
     * @return
     */
    public BaseWrapper data(T data) {
        this.data = data;
        return this;
    }


    public static BaseWrapper ok() {
        return new BaseWrapper();
    }

    public static BaseWrapper ok(String msg) {
        return new BaseWrapper(BaseWrapper.SUCCESS_CODE, StringUtils.isBlank(msg) ? BaseWrapper.SUCCESS_MSG : msg);
    }

    public static <T> BaseWrapper<T> ok(T data) {
        if (data instanceof IPage) {
            IPage page = (IPage)data;
            PageInfo.page(page);
            return new BaseWrapper(BaseWrapper.SUCCESS_CODE, BaseWrapper.SUCCESS_MSG, page);
        }

        return new BaseWrapper(BaseWrapper.SUCCESS_CODE, BaseWrapper.SUCCESS_MSG, data);
    }

    public static <T> BaseWrapper<T> ok(String msg, Object data) {
        return new BaseWrapper(BaseWrapper.SUCCESS_CODE, StringUtils.isBlank(msg) ? BaseWrapper.SUCCESS_MSG : msg, data);
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

    public static <T> BaseWrapper<T> error(int code, String msg, T data) {
        return new BaseWrapper(code, msg, data);
    }

    public static BaseWrapper error(Exception ex) {
        return new BaseWrapper(BaseWrapper.ERROR_CODE, ex.getMessage());
    }

    public static BaseWrapper illegalArgument() {
        return new BaseWrapper(BaseWrapper.ILLEGAL_ARGUMENT_CODE, BaseWrapper.ILLEGAL_ARGUMENT_MSG);
    }

    public static BaseWrapper illegalArgument(String message) {
        return new BaseWrapper(BaseWrapper.ILLEGAL_ARGUMENT_CODE, message);
    }

    public static <T> BaseWrapper<T> handleResult(T result) {
        if (isFlag(result)) {
            return BaseWrapper.ok(result);
        } else {
            return BaseWrapper.error(BaseWrapper.ERROR_CODE, "操作失败", result);
        }
    }

    public static <T> BaseWrapper<T> handleResult(T result, String errorMsg) {
        if (isFlag(result)) {
            return BaseWrapper.ok(result);
        } else {
            return BaseWrapper.error(BaseWrapper.ERROR_CODE, errorMsg, result);
        }
    }

    public static <T> BaseWrapper<T> handleResult(T result, int errorCode, String errorMsg) {
        if (isFlag(result)) {
            return BaseWrapper.ok(result);
        } else {
            return BaseWrapper.error(errorCode, errorMsg, result);
        }
    }

    public static boolean isFlag(Object result) {
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
     * 返回前台json的进行忽略，前台拿不到该数据
     *
     * @return
     */
    @JsonIgnore
    public boolean success() {
        return BaseWrapper.SUCCESS_CODE == this.code;
    }

    @JsonIgnore
    public boolean fail() {
        return BaseWrapper.SUCCESS_CODE != this.code;
    }
}
