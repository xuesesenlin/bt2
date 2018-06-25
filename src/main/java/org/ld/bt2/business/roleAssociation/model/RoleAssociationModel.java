package org.ld.bt2.business.roleAssociation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
@Table(schema = "role_association_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleAssociationModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @Column(name = "role_id", length = 200)
    private String roleId;

    //    fetch=FetchType.LAZY 懒加载
    @OneToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "uuid")
    private List<RoleAssociationModel> roleAssociationModels;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<RoleAssociationModel> getRoleAssociationModels() {
        return roleAssociationModels;
    }

    public void setRoleAssociationModels(List<RoleAssociationModel> roleAssociationModels) {
        this.roleAssociationModels = roleAssociationModels;
    }

    public RoleAssociationModel() {
        super();
    }

    public RoleAssociationModel(String uuid, String roleId, List<RoleAssociationModel> roleAssociationModels) {
        this.uuid = uuid;
        this.roleId = roleId;
        this.roleAssociationModels = roleAssociationModels;
    }

    @Override
    public String toString() {
        return "RoleAssociationModel{" +
                "uuid='" + uuid + '\'' +
                ", roleId='" + roleId + '\'' +
                ", roleAssociationModels=" + roleAssociationModels +
                '}';
    }
}
