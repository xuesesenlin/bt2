package org.ld.bt2.jurisdiction.jurisdiction.jpa;

import org.ld.bt2.jurisdiction.jurisdiction.model.JurisdictionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface JurisdictionJpa extends JpaRepository<JurisdictionModel, String>,
        JpaSpecificationExecutor<JurisdictionModel> {

    List<JurisdictionModel> findByNameAndIdentification(String name, String identification);

}
