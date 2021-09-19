package com.xjzhang.sys.user.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-09-19 21:40:11
 */

@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    *  用户id
    */
    private Long userId;
    /**
    *  用户名
    */
    private String userName;
    /**
    *  密码
    */
    private String password;
    /**
    *  盐
    */
    private String salt;
    /**
    *  邮箱
    */
    private String email;
    /**
    *  手机号
    */
    private String mobile;
    /**
    *  状态  0：禁用   1：正常
    */
    private Integer status;
    /**
    *  部门id
    */
    private Long depId;
    /**
    *  最后登录ip
    */
    private String lastLoginIp;
    /**
    *  最后登录位置
    */
    private String lastLoginLocation;
    /**
    *  最后登录时间
    */
    private Date lastLoginTime;
    /**
    *  是否修改密码
    */
    private Integer isChangedPwd;
    /**
    *  连续密码错误次数
    */
    private Integer pwdErrorCount;
    /**
    *  最后修改密码时间
    */
    private Date pwdErrorTime;
    /**
    *  版本号
    */
    private Integer version;
    /**
    *  创建者
    */
    private String creator;
    /**
    *  创建者ID
    */
    private Long creatorId;
    /**
    *  创建时间
    */
    private Date createTime;
    /**
    *  最后操作者
    */
    private String lastOperator;
    /**
    *  最后操作者ID
    */
    private Long lastOperatorId;
    /**
    *  最后操作时间
    */
    private Date lastOperatorTime;
}
