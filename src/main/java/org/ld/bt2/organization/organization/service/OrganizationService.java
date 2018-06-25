package org.ld.bt2.organization.organization.service;

import org.ld.bt2.organization.organization.model.OrganizationModel;
import org.ld.bt2.util.resultJson.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface OrganizationService {

    ResponseResult<OrganizationModel> save(OrganizationModel model);

    ResponseResult<OrganizationModel> delete(String uuid);

    ResponseResult<OrganizationModel> update(OrganizationModel model);

    ResponseResult<List<OrganizationModel>> findByPatent(String patent);

    ResponseResult<List<OrganizationModel>> findByName(String name);

}
