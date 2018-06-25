package org.ld.bt2.jurisdiction.roleJurisdiction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Entity
@Table(name = "role_jurisdiction_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleJurisdictionModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @Column(name = "role", length = 200)
    private String role;

    @Column(name = "jurisdiction", length = 200)
    private String jurisdiction;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public RoleJurisdictionModel() {
        super();
    }

    public RoleJurisdictionModel(String uuid, String role, String jurisdiction) {
        this.uuid = uuid;
        this.role = role;
        this.jurisdiction = jurisdiction;
    }

    @Override
    public String toString() {
        return "RoleJurisdictionModel{" +
                "uuid='" + uuid + '\'' +
                ", role='" + role + '\'' +
                ", jurisdiction='" + jurisdiction + '\'' +
                '}';
    }
}
