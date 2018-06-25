package org.ld.bt2.jurisdiction.roleAccount.service.impl;

import org.ld.bt2.jurisdiction.roleAccount.jpa.RoleAccountJpa;
import org.ld.bt2.jurisdiction.roleAccount.model.RoleAccountModel;
import org.ld.bt2.jurisdiction.roleAccount.service.RoleAccountService;
import org.ld.bt2.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
public class RoleAccountServiceImpl implements RoleAccountService {

    @Autowired
    private RoleAccountJpa jpa;

    @CachePut(cacheNames = "role_account")
    @Transactional
    @Override
    public ResponseResult<RoleAccountModel> save(RoleAccountModel model) {
        jpa.save(model);
        return new ResponseResult<>(true, "成功", null);
    }

    @CacheEvict(cacheNames = "role_account")
    @Transactional
    @Override
    public ResponseResult<RoleAccountModel> delete(String uuid) {
        jpa.deleteById(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @CachePut(cacheNames = "role_account")
    @Transactional
    @Override
    public ResponseResult<RoleAccountModel> update(RoleAccountModel model) {
        RoleAccountModel one = jpa.getOne(model.getUuid());
        if (one.getAccount() != null) {
            one.setAccount(model.getAccount());
            one.setRole(model.getRole());
            return new ResponseResult<>(true, "成功", null);
        } else
            return new ResponseResult<>(false, "未查询到记录", null);
    }

    @Cacheable(cacheNames = "role_account")
    @Override
    public ResponseResult<List<RoleAccountModel>> findByAccount(String account) {
        List<RoleAccountModel> one = jpa.findByAccount(account);
        if (one.size() > 0)
            return new ResponseResult<>(true, "成功", one);
        else
            return new ResponseResult<>(false, "为查询到记录", null);
    }

    @Cacheable(cacheNames = "role_account")
    @Override
    public ResponseResult<List<RoleAccountModel>> findByRole(String role) {
        List<RoleAccountModel> one = jpa.findByRole(role);
        if (one.size() > 0)
            return new ResponseResult<>(true, "成功", one);
        else
            return new ResponseResult<>(false, "为查询到记录", null);
    }

    @CacheEvict(cacheNames = "role_account")
    @Transactional
    @Override
    public ResponseResult<RoleAccountModel> deleteByAccount(String account) {
        jpa.deleteByAccount(account);
        return new ResponseResult<>(true, "成功", null);
    }

    @CacheEvict(cacheNames = "role_account")
    @Transactional
    @Override
    public ResponseResult<RoleAccountModel> deleteByRole(String role) {
        jpa.deleteByRole(role);
        return new ResponseResult<>(true, "成功", null);
    }
}
