package org.ld.bt2.business.jurisdiction.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.ld.bt2.business.jurisdiction.model.JurisdictionModel;
import org.ld.bt2.business.jurisdiction.service.JurisdictionService;
import org.ld.bt2.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Slf4j
@RestController
@RequestMapping("/jurisdiction")
public class JurisdictionController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private JurisdictionService service;

    @RequiresAuthentication
    @RequiresRoles(value = {"admin"}, logical = Logical.AND)
    @RequiresPermissions(value = {"jurisdiction:page"}, logical = Logical.AND)
    @RequestMapping(value = "/account/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<Page<JurisdictionModel>> page(@PathVariable("pageNow") int pageNow,
                                                        @ModelAttribute(value = "account") JurisdictionModel model) {
        return service.page(pageNow, pageSize, model);
    }

    @RequiresAuthentication
    @RequiresRoles(value = {"admin"}, logical = Logical.AND)
    @RequiresPermissions(value = {"jurisdiction:save"}, logical = Logical.AND)
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseResult<JurisdictionModel> save(@Valid @ModelAttribute("form") JurisdictionModel model,
                                                  BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getFieldError().getDefaultMessage(), null);
        return service.save(model);
    }

    @RequiresAuthentication
    @RequiresRoles(value = {"admin"}, logical = Logical.AND)
    @RequiresPermissions(value = {"jurisdiction:delete"}, logical = Logical.AND)
    @RequestMapping(value = "/account/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<JurisdictionModel> delete(@PathVariable("uuid") String uuid) {
        return service.delete(uuid);
    }

    @RequiresAuthentication
    @RequiresRoles(value = {"admin"}, logical = Logical.AND)
    @RequiresPermissions(value = {"jurisdiction:update"}, logical = Logical.AND)
    @RequestMapping(value = "/account", method = RequestMethod.PUT)
    public ResponseResult<JurisdictionModel> update(@Valid @ModelAttribute("form") JurisdictionModel model,
                                                    BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getFieldError().getDefaultMessage(), null);
        return service.update(model);
    }

}
