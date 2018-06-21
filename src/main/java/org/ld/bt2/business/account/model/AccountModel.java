package org.ld.bt2.business.account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author ld
 * @name 账户
 * @table
 * @remarks
 * @JsonIgnore 注解用来忽略某些字段，可以用在Field或者Getter方法上，用在Setter方法时，和Filed效果一样。
 * 这个注解只能用在POJO存在的字段要忽略的情况，不能满足现在需要的情况。
 * @JsonIgnoreProperties(ignoreUnknown = true)，将这个注解写在类上之后，就会忽略类中不存在的字段，可以满足当前的需要。
 * 这个注解还可以指定要忽略的字段。使用方法如下：
 * @JsonIgnoreProperties({ "internalId", "secretKey" })
 * 指定的字段不会被序列化和反序列化。
 * https://www.cnblogs.com/softidea/p/6216722.html
 */
@Entity
@Table(name = "account_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;
    //账号
    @NotBlank(message = "账户不能为空")
    @Length(min = 6, max = 30, message = "账户长度为6-30位")
    @Column(name = "account", length = 30)
    private String account;
    //密码
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 30, message = "密码长度为6-30位")
    @Column(name = "password", length = 30)
    private String password;
    //注册时间
//    @Temporal(TemporalType.DATE)(精确到年月日)
//    @Temporal(TemporalType.TIME)(精确到时分秒)
//    @Temporal(TemporalType.TIMESTAMP)(默认年月日时分秒)
    @Column(name = "register_time", columnDefinition = "bigint")
    private long registerTime;
    //    保留字段
    @Length(max = 10, message = "标记字段不能超过10位")
    @Column(name = "flag", length = 10)
    private String flag;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public AccountModel() {
        super();
    }

    public AccountModel(String uuid, String account, String password, long registerTime, String flag) {
        this.uuid = uuid;
        this.account = account;
        this.password = password;
        this.registerTime = registerTime;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "uuid='" + uuid + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", registerTime=" + registerTime +
                ", flag='" + flag + '\'' +
                '}';
    }
}
