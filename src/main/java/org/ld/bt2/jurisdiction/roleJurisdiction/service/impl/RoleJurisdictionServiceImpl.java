package org.ld.bt2.jurisdiction.roleJurisdiction.service.impl;

import org.ld.bt2.jurisdiction.roleJurisdiction.jpa.RoleJurisdictionJpa;
import org.ld.bt2.jurisdiction.roleJurisdiction.model.RoleJurisdictionModel;
import org.ld.bt2.jurisdiction.roleJurisdiction.service.RoleJurisdictionService;
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
public class RoleJurisdictionServiceImpl implements RoleJurisdictionService {

    @Autowired
    private RoleJurisdictionJpa jpa;

    @CachePut(cacheNames = "role_Jurisdiction")
    @Transactional
    @Override
    public ResponseResult<RoleJurisdictionModel> save(RoleJurisdictionModel model) {
        jpa.save(model);
        return new ResponseResult<>(true, "成功", null);
    }

    @CacheEvict(cacheNames = "role_Jurisdiction")
    @Transactional
    @Override
    public ResponseResult<RoleJurisdictionModel> delete(String uuid) {
        jpa.deleteById(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @CachePut(cacheNames = "role_Jurisdiction")
    @Transactional
    @Override
    public ResponseResult<RoleJurisdictionModel> update(RoleJurisdictionModel model) {
        RoleJurisdictionModel one = jpa.getOne(model.getUuid());
        if (one.getJurisdiction() != null) {
            one.setJurisdiction(model.getJurisdiction());
            one.setRole(model.getRole());
            return new ResponseResult<>(true, "成功", null);
        } else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    @Cacheable(cacheNames = "role_Jurisdiction")
    @Override
    public ResponseResult<List<RoleJurisdictionModel>> findByRole(String role) {
        List<RoleJurisdictionModel> list = jpa.findByRole(role);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    @Cacheable(cacheNames = "role_Jurisdiction")
    @Override
    public ResponseResult<List<RoleJurisdictionModel>> findByJurisdiction(String jurisdiction) {
        List<RoleJurisdictionModel> list = jpa.findByJurisdiction(jurisdiction);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    @CacheEvict(cacheNames = "role_Jurisdiction")
    @Transactional
    @Override
    public ResponseResult<RoleJurisdictionModel> deleteByJurisdiction(String jurisdiction) {
        jpa.deleteByJurisdiction(jurisdiction);
        return new ResponseResult<>(true, "成功", null);
    }

    @CacheEvict(cacheNames = "role_Jurisdiction")
    @Transactional
    @Override
    public ResponseResult<RoleJurisdictionModel> deleteByRole(String role) {
        jpa.deleteByRole(role);
        return new ResponseResult<>(true, "成功", null);
    }
}
