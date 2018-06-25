package org.ld.bt2.jurisdiction.jurisdiction.service;

import org.ld.bt2.jurisdiction.jurisdiction.model.JurisdictionModel;
import org.ld.bt2.util.resultJson.ResponseResult;
import org.springframework.data.domain.Page;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface JurisdictionService {

    ResponseResult<JurisdictionModel> save(JurisdictionModel model);

    ResponseResult<JurisdictionModel> delete(String uuid);

    ResponseResult<JurisdictionModel> update(JurisdictionModel model);

    ResponseResult<JurisdictionModel> findByUuid(String uuid);

    ResponseResult<Page<JurisdictionModel>> page(int pageNow, int pageSize, JurisdictionModel model);
}
