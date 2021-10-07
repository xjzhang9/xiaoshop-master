package com.xjzhang.base.utils;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/10/2 10:40
 */
public class JwtToken implements AuthenticationToken {
    private static final long serialVersionUID = 5342306894833145392L;

    private String token;

     public JwtToken(String token) {
         this.token = token;
     }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
