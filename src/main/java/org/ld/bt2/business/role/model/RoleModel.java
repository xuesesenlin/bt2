package org.ld.bt2.business.role.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.ld.bt2.business.roleAssociation.model.RoleAssociationModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Slf4j
@Entity
@Table(schema = "role_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @NotBlank(message = "角色名称不能为空")
    @Size(max = 200, message = "角色名称最大长度为200")
    @Column(name = "name", length = 200)
    private String name;

    @NotBlank(message = "人员不能为空")
    @Column(name = "account", length = 200)
    private String account;

    //    fetch=FetchType.LAZY 懒加载
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private List<RoleAssociationModel> roleAssociationModels;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<RoleAssociationModel> getRoleAssociationModels() {
        return roleAssociationModels;
    }

    public void setRoleAssociationModels(List<RoleAssociationModel> roleAssociationModels) {
        this.roleAssociationModels = roleAssociationModels;
    }

    public RoleModel() {
        super();
    }

    public RoleModel(String uuid, @NotBlank(message = "角色名称不能为空") @Size(max = 200, message = "角色名称最大长度为200") String name, @NotBlank(message = "人员不能为空") String account, List<RoleAssociationModel> roleAssociationModels) {
        this.uuid = uuid;
        this.name = name;
        this.account = account;
        this.roleAssociationModels = roleAssociationModels;
    }

    @Override
    public String toString() {
        return "RoleModel{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
