package org.ld.bt2.organization.organization.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ld.bt2.organization.organization.jpa.OrganizationJpa;
import org.ld.bt2.organization.organization.model.OrganizationModel;
import org.ld.bt2.organization.organization.service.OrganizationService;
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
@Slf4j
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationJpa jpa;

    @CachePut(cacheNames = "organization")
    @Transactional
    @Override
    public ResponseResult<OrganizationModel> save(OrganizationModel model) {
        List<OrganizationModel> list = jpa.findByName(model.getName());
        if (list.size() > 0)
            return new ResponseResult<>(false, "名称重复", null);
        else {
            jpa.save(model);
            return new ResponseResult<>(true, "成功", null);
        }
    }

    @CacheEvict(cacheNames = "organization")
    @Transactional
    @Override
    public ResponseResult<OrganizationModel> delete(String uuid) {
        jpa.deleteById(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @CachePut(cacheNames = "organization")
    @Transactional
    @Override
    public ResponseResult<OrganizationModel> update(OrganizationModel model) {
        List<OrganizationModel> list = jpa.findByName(model.getName());
        if (list.size() > 0)
            return new ResponseResult<>(false, "名称重复", null);
        else {
            OrganizationModel one = jpa.getOne(model.getUuid());
            if (one.getName() != null) {
                one.setName(model.getName());
                one.setPatent(model.getPatent());
                return new ResponseResult<>(true, "成功", null);
            } else
                return new ResponseResult<>(false, "未查询到记录", null);
        }
    }

    @Cacheable(cacheNames = "organization")
    @Override
    public ResponseResult<List<OrganizationModel>> findByPatent(String patent) {
        List<OrganizationModel> list = jpa.findByPatent(patent);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到记录", null);
    }

    @Cacheable(cacheNames = "organization")
    @Override
    public ResponseResult<List<OrganizationModel>> findByName(String name) {
        List<OrganizationModel> list = jpa.findByName(name);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到记录", null);
    }

}
