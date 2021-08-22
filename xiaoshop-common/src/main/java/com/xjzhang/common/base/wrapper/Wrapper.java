package com.xjzhang.common.base.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一的数据返回封装
 *
 * @author xjzhang
 * @version 1.0
 * @date 2021/7/12 18:21
 */
@Data
@JsonSerialize
public class Wrapper<T> implements Serializable {

    private static final long serialVersionUID = -9042417141433503027L;

    public static final int SUCCESS_CODE = 200;

    public static final String SUCCESS_MSG = "操作成功";

    public static final int ERROR_CODE = 500;

    public static final String ERROR_MSG = "内部异常";

    public static final int ILLEGAL_ARGUMENT_CODE = 100;

    public static final String ILLEGAL_ARGUMENT_MSG  = "参数非法";

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


    public Wrapper() {
        this(SUCCESS_CODE, SUCCESS_MSG, null);
    }

    public Wrapper(int code, String msg) {
        this(code, msg, null);
    }

    public Wrapper(int code, String msg, T data) {
        super();
        this.code(code).msg(msg).data(data);
    }


    /**
     * 设置 code
     *
     * @param code
     * @return
     */
    private Wrapper code(int code) {
        this.code = code;
        return this;
    }

    /**
     * 设置 msg
     *
     * @param msg
     * @return
     */
    private Wrapper msg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * 设置 data
     *
     * @param data
     * @return
     */
    public Wrapper data(T data) {
        this.data = data;
        return this;
    }

    /**
     * 返回前台json的进行忽略，前台拿不到该数据
     * @return
     */
    @JsonIgnore
    public boolean success() {
       return Wrapper.SUCCESS_CODE == this.code;
    }

    @JsonIgnore
    public boolean error() {
        return Wrapper.SUCCESS_CODE != this.code;
    }
}
