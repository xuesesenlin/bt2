package org.ld.bt2.organization.organization.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Slf4j
@Entity
@Table(schema = "organization_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganizationModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @NotBlank(message = "所属父级不能为空")
    @Column(name = "patent", length = 200)
    private String patent;

    @NotBlank(message = "名称不能为空")
    @Size(max = 200, message = "名称长度为200位")
    @Column(name = "name", length = 200)
    private String name;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPatent() {
        return patent;
    }

    public void setPatent(String patent) {
        this.patent = patent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrganizationModel() {
        super();
    }

    public OrganizationModel(String uuid, @NotBlank(message = "所属父级不能为空") String patent, @NotBlank(message = "名称不能为空") @Size(max = 200, message = "名称长度为200位") String name) {
        this.uuid = uuid;
        this.patent = patent;
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrganizationModel{" +
                "uuid='" + uuid + '\'' +
                ", patent='" + patent + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
