package org.ld.bt2.config.shiro.config.token.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author ld
 * @name 令牌类
 * @table
 * @remarks
 */
public class StatelessToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    private String userCode;

    private String token;

    public StatelessToken(String userCode, String token) {
        this.userCode = userCode;
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    public String getUserCode() {
        return userCode;
    }

    public String getToken() {
        return token;
    }
}
