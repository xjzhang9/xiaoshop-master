package com.xjzhang.sys.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjzhang.sys.user.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-09-19 20:45:50
 */

@Mapper
public interface  UserDao extends BaseMapper<User> {

}
