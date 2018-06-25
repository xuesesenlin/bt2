package org.ld.bt2.business.jurisdiction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Slf4j
@Entity
@Table(schema = "jurisdiction_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class JurisdictionModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @NotBlank(message = "权限名称不能为空")
    @Column(name = "name", length = 200)
    private String name;

    @NotBlank(message = "权限标识不能为空")
    @Column(name = "identification", length = 200)
    private String identification;

    //    1:菜单 2：按钮
    @NotBlank(message = "请指定权限类型")
    @Min(value = 1, message = "最小限制1")
    @Column(name = "type", length = 10)
    private String type;

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

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JurisdictionModel() {
        super();
    }

    public JurisdictionModel(String uuid, @NotBlank(message = "权限名称不能为空") String name, @NotBlank(message = "权限标识不能为空") String identification, @NotBlank(message = "请指定权限类型") @Min(value = 1, message = "最小限制1") String type) {
        this.uuid = uuid;
        this.name = name;
        this.identification = identification;
        this.type = type;
    }

    @Override
    public String toString() {
        return "JurisdictionModel{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", identification='" + identification + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
