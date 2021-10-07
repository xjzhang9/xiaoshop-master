package com.xjzhang.base.enums;

/**
 * error enum
 *
 * @author xjzhang
 * @version 1.0
 * @date 2021/7/12 18:35
 */
public enum ErrorCodeEnum {
    /**
     * Gl 9999001 error code enum.
     */
    GL99990001(99990001, "无权限访问"),

    /*********************************************************************************************/
    /**
     * 1001 授权管理
     */
    AUTH10010001(10010001, "用户未登录"),

    /********************************************1002商品服务*****************************************/
    PRO10021001(10021001, "找不到商品分类信息, categoryId=%s"),
    PRO10021002(10021002, "父商品分类为空, categoryId=%s");

    /**
     * 错误码，code
     */
    private int code;

    /**
     * 错误描述，msg
     */
    private String msg;

    /**
     * code int
     *
     * @return
     */
    public int code() {
        return code;
    }

    /**
     * msg String
     *
     * @return
     */
    public String msg() {
        return msg;
    }

    private ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(int code) {
        for (ErrorCodeEnum ece : ErrorCodeEnum.values()) {
            if (code == ece.code) {
                return ece.msg;
            }
        }

        return null;
    }

    /**
     * get enum
     *
     * @param code
     * @return
     */
    public static ErrorCodeEnum getEnum(int code) {
        for (ErrorCodeEnum ece : ErrorCodeEnum.values()) {
            if (code == ece.code) {
                return ece;
            }
        }

        return null;
    }
}
