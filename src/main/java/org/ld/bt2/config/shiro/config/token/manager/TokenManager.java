package org.ld.bt2.config.shiro.config.token.manager;

import org.ld.bt2.config.shiro.config.token.token.StatelessToken;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface TokenManager {

    /**
     * 创建一个token关联上指定用户
     *
     * @param userCode 指定用户的id
     * @return 生成的token
     */
    StatelessToken createToken(String userCode);

    /**
     * 检查token是否有效
     *
     * @param statelessToken
     * @return 是否有效
     */
    boolean checkToken(StatelessToken statelessToken);


    /**
     * 检查身份是否有效
     *
     * @param authentication token
     * @return 是否有效
     */
    boolean check(String authentication);

    /**
     * 从字符串中解析token
     *
     * @param authentication 加密后的字符串
     * @return
     */
    StatelessToken getToken(String authentication);

    /**
     * 清除token
     *
     * @param userCode 登录用户的id
     */
    void deleteToken(String userCode);
}
