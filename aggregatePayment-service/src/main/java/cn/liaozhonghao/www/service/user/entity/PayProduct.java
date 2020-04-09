package cn.liaozhonghao.www.service.user.entity;

import cn.liaozhonghao.www.common.core.entity.BaseEntity;
import cn.liaozhonghao.www.common.core.enums.PublicEnum;

import java.io.Serializable;

/**
 * 支付产品实体类
 */
public class PayProduct extends BaseEntity implements Serializable {

    // 支付产品编号
    private String productCode;

    // 支付产品名称
    private String productName;

    // TODO 审计状态
    private String auditStatus;

    private static final long serialVersionUID = 1L;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }
    
    public String getAuditStatusDesc() {
    	return PublicEnum.getEnum(this.getAuditStatus()).getDesc();
    }
}