package org.ld.bt2.jurisdiction.roleAccount.jpa;

import org.ld.bt2.jurisdiction.roleAccount.model.RoleAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface RoleAccountJpa extends JpaRepository<RoleAccountModel, String>,
        JpaSpecificationExecutor<RoleAccountModel> {

    List<RoleAccountModel> findByRole(String role);

    List<RoleAccountModel> findByAccount(String account);

    void deleteByAccount(String account);

    void deleteByRole(String role);
}
