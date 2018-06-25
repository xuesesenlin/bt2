package org.ld.bt2.organization.organization.jpa;

import org.ld.bt2.organization.organization.model.OrganizationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface OrganizationJpa extends JpaRepository<OrganizationModel, String>,
        JpaSpecificationExecutor<OrganizationModel> {

    List<OrganizationModel> findByPatent(String patent);

    List<OrganizationModel> findByName(String name);

}
