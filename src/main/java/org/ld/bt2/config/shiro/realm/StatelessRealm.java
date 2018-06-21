package org.ld.bt2.config.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.ld.bt2.business.account.service.AccountService;
import org.ld.bt2.config.shiro.config.token.manager.TokenManager;
import org.ld.bt2.config.shiro.config.token.token.StatelessToken;

/**
 * @author ld
 * @name 无状态域
 * @table
 * @remarks
 */
public class StatelessRealm extends AuthorizingRealm {

    private TokenManager tokenManager;

    @SuppressWarnings("rawtypes")
    private AccountService accountService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessToken;
    }

//    private AuthorizationService authorizationService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //根据用户名查找角色，请根据需求实现
        String userCode = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        List<String> selectRoles = authorizationService.selectRoles(userCode);
//        authorizationInfo.addRoles(selectRoles);
        authorizationInfo.addRole("admin");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        StatelessToken statelessToken = (StatelessToken) token;
        String userCode = (String) statelessToken.getPrincipal();
        checkUserExists(userCode);
        String credentials = (String) statelessToken.getCredentials();
        boolean checkToken = tokenManager.checkToken(statelessToken);
        if (checkToken) {
            return new SimpleAuthenticationInfo(userCode, credentials, super.getName());
        } else {
            throw new AuthenticationException("token认证失败");
        }
    }

    private void checkUserExists(String userCode) throws AuthenticationException {
        Object principal = accountService.findByAccount(userCode);
        if (principal == null) {
            throw new UnknownAccountException("账户不存在");
        }
    }

    public TokenManager getTokenManager() {
        return tokenManager;
    }

    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @SuppressWarnings("rawtypes")
    public AccountService getPrincipalService() {
        return accountService;
    }

    @SuppressWarnings("rawtypes")
    public void setPrincipalService(AccountService accountService) {
        this.accountService = accountService;
    }

//    public AuthorizationService getAuthorizationService() {
//        return authorizationService;
//    }

//    public void setAuthorizationService(AuthorizationService authorizationService) {
//        this.authorizationService = authorizationService;
//    }
}
