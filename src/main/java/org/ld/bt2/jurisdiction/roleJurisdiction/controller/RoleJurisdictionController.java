package org.ld.bt2.jurisdiction.roleJurisdiction.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.ld.bt2.jurisdiction.roleJurisdiction.model.RoleJurisdictionModel;
import org.ld.bt2.jurisdiction.roleJurisdiction.service.RoleJurisdictionService;
import org.ld.bt2.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/roleJurisdiction")
public class RoleJurisdictionController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private RoleJurisdictionService service;

    @RequiresRoles(value = "admin")
    @RequiresPermissions(value = {"roleJurisdiction:save"})
    @RequestMapping(value = "/roleJurisdiction", method = RequestMethod.POST)
    public ResponseResult<RoleJurisdictionModel> save(@Valid @ModelAttribute("form") RoleJurisdictionModel model) {
        return service.save(model);
    }

    @RequiresRoles(value = "admin")
    @RequiresPermissions(value = {"roleJurisdiction:delete:id"})
    @RequestMapping(value = "/roleJurisdiction/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<RoleJurisdictionModel> deleteById(@PathVariable("uuid") String uuid) {
        return service.delete(uuid);
    }

    @RequiresRoles(value = "admin")
    @RequiresPermissions(value = {"roleJurisdiction:find:account"})
    @RequestMapping(value = "/roleJurisdiction/account", method = RequestMethod.GET)
    public ResponseResult<List<RoleJurisdictionModel>> findByJurisdiction(@RequestParam("jurisdiction") String jurisdiction) {
        return service.findByJurisdiction(jurisdiction);
    }

    @RequiresRoles(value = "admin")
    @RequiresPermissions(value = {"roleJurisdiction:find:role"})
    @RequestMapping(value = "/roleJurisdiction/role", method = RequestMethod.GET)
    public ResponseResult<List<RoleJurisdictionModel>> findByRole(@RequestParam("role") String role) {
        return service.findByRole(role);
    }

    @RequiresRoles(value = "admin")
    @RequiresPermissions(value = {"roleJurisdiction:update"})
    @RequestMapping(value = "/roleJurisdiction", method = RequestMethod.PUT)
    public ResponseResult<RoleJurisdictionModel> update(@Valid @ModelAttribute("form") RoleJurisdictionModel model) {
        return service.update(model);
    }
}
