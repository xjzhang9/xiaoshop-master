package com.xjzhang.sys.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.sys.user.dao.UserDao;
import com.xjzhang.sys.user.model.UserDto;
import com.xjzhang.sys.user.model.User;
import com.xjzhang.sys.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统用户
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-09-19 20:45:50
 */

@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
   @Autowired
    private  UserDao userDao;
}
