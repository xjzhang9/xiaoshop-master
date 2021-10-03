package com.xjzhang.auth.controller;

import com.xjzhang.common.constant.RedisConstant;
import com.xjzhang.common.wrapper.BaseWrapper;
import com.xjzhang.common.wrapper.ResWrapper;
import com.xjzhang.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/10/1 17:10
 */
@RestController
@RequestMapping("/xiaoshop/auth/")
public class LoginController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${config.refreshTokenExpireTime}")
    private String refreshTokenExpireTime;

    @RequestMapping("login")
    public BaseWrapper login(String account, String password, HttpServletResponse response) {
        // 从数据库里面获取用户名和密码进行验证
        // TODO验证成功

        // 清除授权缓存信息
        if (redisTemplate.hasKey(RedisConstant.PREFIX_SHIRO_CACHE + account)) {
            redisTemplate.delete(RedisConstant.PREFIX_SHIRO_CACHE + account);
        }

        // 设置refreshtoken, 时间为当前时间戳
        String timeStamp = System.currentTimeMillis() + "";
        redisTemplate.opsForValue().set(RedisConstant.PREFIX_SHIRO_REFRESH_TOKEN + account, timeStamp, Integer.parseInt(refreshTokenExpireTime));
        String token = JwtUtil.sign(account, timeStamp);

        response.setHeader("Authorization", token);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        return ResWrapper.ok();
    }
}
