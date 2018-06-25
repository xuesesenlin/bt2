package org.ld.bt2.jurisdiction.roleAccount.model;

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
@Table(name = "role_account_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleAccountModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @Column(name = "role", length = 200)
    private String role;

    @Column(name = "account", length = 200)
    private String account;

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public RoleAccountModel() {
        super();
    }

    public RoleAccountModel(String uuid, String role, String account) {
        this.uuid = uuid;
        this.role = role;
        this.account = account;
    }

    @Override
    public String toString() {
        return "RoleAccountModel{" +
                "uuid='" + uuid + '\'' +
                ", role='" + role + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
