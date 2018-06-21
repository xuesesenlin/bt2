package org.ld.bt2.config.shiro.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.ld.bt2.config.shiro.config.token.manager.TokenManager;
import org.ld.bt2.config.shiro.config.token.token.StatelessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Slf4j
public class StatelessAuthcFilter extends AccessControlFilter {


    @Autowired
    private TokenManager tokenManager;

    public TokenManager getTokenManager() {
        return tokenManager;
    }

    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        log.info("拦截到的url:" + httpRequest.getRequestURL().toString());
        // 前段token授权信息放在请求头中传入  
//        String authorization = RequestUtil.newInstance().getRequestHeader(
//                (HttpServletRequest) request, "authorization");
        String authorization = httpRequest.getHeader("authorization");
        if (StringUtils.isEmpty(authorization)) {
            onLoginFail(response, "验证失败，请登录");
            return false;
        }
        // 获取无状态Token  
        StatelessToken accessToken = tokenManager.getToken(authorization);
        try {
            // 委托给Realm进行登录  
            getSubject(request, response).login(accessToken);
        } catch (Exception e) {
            log.error("auth error:" + e.getMessage(), e);
            onLoginFail(response, "auth error:" + e.getMessage()); // 6、登录失败  
            return false;
        }
        // 通过isPermitted 才能调用doGetAuthorizationInfo方法获取权限信息  
        getSubject(request, response).isPermitted(httpRequest.getRequestURI());
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request,
                                     ServletResponse response) throws Exception {
        return false;
    }

    //登录失败时默认返回401状态码    
    private void onLoginFail(ServletResponse response, String errorMsg) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.setContentType("text/html");
        httpResponse.setCharacterEncoding("utf-8");
        httpResponse.getWriter().write(errorMsg);
        httpResponse.getWriter().close();
    }
}
