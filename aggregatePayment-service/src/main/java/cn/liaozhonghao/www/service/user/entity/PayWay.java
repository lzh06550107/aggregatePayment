package cn.liaozhonghao.www.service.user.entity;

import cn.liaozhonghao.www.common.core.entity.BaseEntity;

import java.io.Serializable;

/**
 * 支付方式实体类
 */
public class PayWay extends BaseEntity implements Serializable {

    // 支付方式编号
    private String payWayCode;

    // 支付方式名称
    private String payWayName;

    // 支付类型编号
    private String payTypeCode;

    // 支付类型名称
    private String payTypeName;

    // 支付产品编号
    private String payProductCode;

    // 排序(倒序排序,默认值1000)
    private Integer sorts;

    // 商户支付费率
    private Double payRate;

    private static final long serialVersionUID = 1L;


    public String getPayWayCode() {
        return payWayCode;
    }

    public void setPayWayCode(String payWayCode) {
        this.payWayCode = payWayCode == null ? null : payWayCode.trim();
    }

    public String getPayWayName() {
        return payWayName;
    }

    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName == null ? null : payWayName.trim();
    }

    public String getPayTypeCode() {
        return payTypeCode;
    }

    public void setPayTypeCode(String payTypeCode) {
        this.payTypeCode = payTypeCode == null ? null : payTypeCode.trim();
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName == null ? null : payTypeName.trim();
    }

    public String getPayProductCode() {
        return payProductCode;
    }

    public void setPayProductCode(String payProductCode) {
        this.payProductCode = payProductCode == null ? null : payProductCode.trim();
    }


    public Integer getSorts() {
        return sorts;
    }

    public void setSorts(Integer sorts) {
        this.sorts = sorts;
    }

    public Double getPayRate() {
        return payRate;
    }

    public void setPayRate(Double payRate) {
        this.payRate = payRate;
    }

}