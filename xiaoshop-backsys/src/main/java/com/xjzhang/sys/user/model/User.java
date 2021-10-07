package com.xjzhang.sys.user.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjzhang.base.model.BaseEntity;
import lombok.Data;
import java.util.Date;

/**
 * 系统用户
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-09-19 21:40:11
 */

@Data
@TableName("tb_pu_user")
public class User extends BaseEntity {
    /**
     * 用户id
     */
    @TableId
    @TableField("user_id")
    private Long userId;
    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 密码
     */
    @TableField("password")
    private String password;
    /**
     * 盐
     */
    @TableField("salt")
    private String salt;
    /**
     * 邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;
    /**
     * 状态  0：禁用   1：正常
     */
    @TableField("status")
    private Integer status;
    /**
     * 部门id
     */
    @TableField("dep_id")
    private Long depId;
    /**
     * 最后登录ip
     */
    @TableField("last_login_ip")
    private String lastLoginIp;
    /**
     * 最后登录位置
     */
    @TableField("last_login_location")
    private String lastLoginLocation;
    /**
     * 最后登录时间
     */
    @TableField("last_login_time")
    private Date lastLoginTime;
    /**
     * 是否修改密码
     */
    @TableField("is_changed_pwd")
    private Integer isChangedPwd;
    /**
     * 连续密码错误次数
     */
    @TableField("pwd_error_count")
    private Integer pwdErrorCount;
    /**
     * 最后修改密码时间
     */
    @TableField("pwd_error_time")
    private Date pwdErrorTime;
}
