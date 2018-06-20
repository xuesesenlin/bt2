package org.ld.bt2.account.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ld.bt2.account.jpa.AccountJpa;
import org.ld.bt2.account.model.AccountModel;
import org.ld.bt2.account.service.AccountService;
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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


/**
 * @author ld
 * @name
 * @table
 * @remarks http://blog.720ui.com/2017/springboot_02_data_cache_concurrenmapcache/#%E5%BC%80%E5%90%AF%E7%BC%93%E5%AD%98%E6%94%AF%E6%8C%81
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountJpa jpa;

    //    缓存更新
    @CachePut("account")
    @Transactional
    @Override
    public ResponseResult<AccountModel> save(AccountModel model) {
        List<AccountModel> list = jpa.findByAccount(model.getAccount());
        if (list.size() > 0)
            return new ResponseResult<>(false, "该账户已注册", null);
        model.setFlag("0");
        model.setRegisterTime(System.currentTimeMillis());
        jpa.save(model);
        return new ResponseResult<>(true, "成功", null);
    }

    //    缓存删除
    @CacheEvict("account")
    @Transactional
    @Override
    public ResponseResult<AccountModel> delete(String uuid) {
        jpa.deleteById(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    //    缓存更新
    @CachePut("account")
    @Transactional
    @Override
    public ResponseResult<AccountModel> update(AccountModel model) {
        AccountModel one = jpa.getOne(model.getUuid());
        one.setPassword(model.getPassword());
//        强制同步db
//        jpa.flush();
        return new ResponseResult<>(true, "成功", null);
    }

    //    缓存读取
    @Cacheable("account")
    @Override
    public ResponseResult<AccountModel> findByAccount(String account) {
        List<AccountModel> list = jpa.findByAccount(account);
        if (list.isEmpty())
            return new ResponseResult<>(true, "成功", list.get(0));
        else
            return new ResponseResult<>(false, "为查询到数据", null);
    }

    //    缓存读取
    @Cacheable("account")
    @Override
    public ResponseResult<Page<AccountModel>> page(int pageNow, int pageSize, AccountModel model) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "registerTime"));//排序信息
        orders.add(new Sort.Order(Sort.Direction.ASC, "account"));//排序信息
        Specification<AccountModel> spec = queryTj(model);
        PageRequest pageRequest = PageRequest.of(pageNow, pageSize, Sort.by(orders));
        Page<AccountModel> page = jpa.findAll(spec, pageRequest);
        if (!page.getContent().isEmpty())
            return new ResponseResult<>(true, "成功", page);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }


    //    查询条件
    private Specification<AccountModel> queryTj(AccountModel model) {
        return new Specification<AccountModel>() {//查询条件构造
            @Override
            public Predicate toPredicate(Root<AccountModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (model != null) {
                    if (model.getAccount() != null && !model.getAccount().isEmpty()) {
                        Predicate p1 = cb.like(root.get("account").as(String.class), "%" + model.getAccount() + "%");
                        predicates.add(cb.and(p1));
                    }
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
