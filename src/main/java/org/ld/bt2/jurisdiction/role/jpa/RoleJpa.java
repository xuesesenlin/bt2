package org.ld.bt2.jurisdiction.role.jpa;

import org.ld.bt2.jurisdiction.role.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface RoleJpa extends JpaRepository<RoleModel, String>,
        JpaSpecificationExecutor<RoleModel> {

    List<RoleModel> findByName(String name);

}
