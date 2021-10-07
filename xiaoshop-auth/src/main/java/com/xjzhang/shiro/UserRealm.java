package com.xjzhang.shiro;


import com.xjzhang.base.constant.RedisConstant;
import com.xjzhang.utils.JwtToken;
import com.xjzhang.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/10/1 21:29
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private RedisTemplate<String, Object> redisTempate;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        simpleAuthorizationInfo.set
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        if (StringUtils.isBlank(token)) {
            throw new AuthenticationException("token 不能为空");
        }

        String account = JwtUtil.getClaim(token, "account");

        if (StringUtils.isBlank(account)) {
            throw new AuthenticationException("token中account 不能为空");
        }

        //Todo: 查询数据库用户是否存在
        if (JwtUtil.verify(token) && redisTempate.hasKey(RedisConstant.PREFIX_SHIRO_REFRESH_TOKEN + account)) {
              String timeStamp = (String) redisTempate.opsForValue().get(RedisConstant.PREFIX_SHIRO_REFRESH_TOKEN + account);

              String tokenTimeStamp = JwtUtil.getClaim(token, "timeStamp");

              if (tokenTimeStamp.equals(timeStamp)) {
                  return new SimpleAuthenticationInfo(token, token, "userRealm");
              }
        }

        throw new AuthenticationException("token expired or incorrect.");
    }
}
