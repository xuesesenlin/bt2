package org.ld.bt2.config.shiro.config.token.manager.impl;

import org.ld.bt2.config.shiro.config.token.abstracts.AbstractTokenManager;
import org.ld.bt2.config.shiro.config.token.token.StatelessToken;
import org.ld.bt2.util.uuidUtil.GetUuid;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public class DefaultTokenManagerImpl extends AbstractTokenManager {

    @Override
    public String createStringToken(String userCode) {
        //创建简易的32为uuid
        return GetUuid.getUUID();
    }


    @Override
    public boolean checkToken(StatelessToken model) {
        return super.checkMemoryToken(model);
    }
}
