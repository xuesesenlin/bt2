package org.ld.bt2.business.role.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ld.bt2.business.role.jpa.RoleJpa;
import org.ld.bt2.business.role.model.RoleModel;
import org.ld.bt2.business.role.service.RoleService;
import org.ld.bt2.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleJpa jpa;

    @CachePut(cacheNames = "role")
    @Override
    public ResponseResult<RoleModel> save(RoleModel model) {
        List<RoleModel> list = jpa.findByName(model.getName());
        if (list.size() > 0)
            return new ResponseResult<>(false, "名称重复", null);
        else {
            jpa.save(model);
            return new ResponseResult<>(true, "成功", null);
        }
    }

    @CacheEvict(cacheNames = "role")
    @Override
    public ResponseResult<RoleModel> delete(String uuid) {
        jpa.deleteById(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @CachePut(cacheNames = "role")
    @Override
    public ResponseResult<RoleModel> update(RoleModel model) {
        List<RoleModel> list = jpa.findByName(model.getName());
        if (list.size() > 0)
            return new ResponseResult<>(false, "名称重复", null);
        else {
//            findone数据库返回数据，并不是数据库实例，更改后需要手动调用flus刷新，并且当数据返回为空的时候实体为空
//            getone为数据库实时数据实例，更改后不需手动刷新，但当数据为空的时候实体不为空，但实体属性为空
            RoleModel one = jpa.getOne(model.getUuid());
            if (one.getName() != null) {
                one.setAccount(model.getAccount());
                one.setName(model.getName());
                return new ResponseResult<>(true, "成功", null);
            } else
                return new ResponseResult<>(false, "未查询到记录", null);
        }
    }

    @Cacheable(cacheNames = "role")
    @Override
    public ResponseResult<RoleModel> findByUuid(String uuid) {
        Optional<RoleModel> one = jpa.findById(uuid);
        if (one.isPresent())
            return new ResponseResult<>(true, "成功", one.get());
        else
            return new ResponseResult<>(false, "未查询到记录", null);
    }

    @Cacheable(cacheNames = "role")
    @Override
    public ResponseResult<Page<RoleModel>> page(int pageNow, int pageSize, RoleModel model) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "name"));//排序信息
        Specification<RoleModel> spec = queryTj(model);
        PageRequest pageRequest = PageRequest.of(pageNow, pageSize, Sort.by(orders));
        Page<RoleModel> page = jpa.findAll(spec, pageRequest);
        if (!page.getContent().isEmpty())
            return new ResponseResult<>(true, "成功", page);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    //    查询条件
    private Specification<RoleModel> queryTj(RoleModel model) {
        return new Specification<RoleModel>() {//查询条件构造
            @Override
            public Predicate toPredicate(Root<RoleModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (model != null) {
                    if (model.getName() != null && !model.getName().isEmpty()) {
                        Predicate p1 = cb.like(root.get("name").as(String.class), "%" + model.getName() + "%");
                        predicates.add(cb.and(p1));
                    }
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
