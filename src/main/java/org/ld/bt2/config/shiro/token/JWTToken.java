package org.ld.bt2.config.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public class JWTToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JWTToken(String token) {
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
