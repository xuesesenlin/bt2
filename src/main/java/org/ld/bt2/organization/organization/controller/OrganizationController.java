package org.ld.bt2.organization.organization.controller;

import org.ld.bt2.organization.organization.model.OrganizationModel;
import org.ld.bt2.organization.organization.service.OrganizationService;
import org.ld.bt2.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService service;

    @RequestMapping(value = "/organization/patent/{uuid}", method = RequestMethod.GET)
    public ResponseResult<List<OrganizationModel>> patent(@PathVariable("uuid") String uuid) {
        return service.findByPatent(uuid);
    }

    @RequestMapping(value = "/organization", method = RequestMethod.POST)
    public ResponseResult<OrganizationModel> save(@Valid @ModelAttribute("model") OrganizationModel model) {
        return service.save(model);
    }

    @RequestMapping(value = "/organization/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<OrganizationModel> delete(@PathVariable("uuid") String uuid) {
        return service.delete(uuid);
    }

    @RequestMapping(value = "/organization", method = RequestMethod.PUT)
    public ResponseResult<OrganizationModel> update(@Valid @ModelAttribute("model") OrganizationModel model) {
        return service.update(model);
    }
}
