package cn.liaozhonghao.www.service.user.entity;

import cn.liaozhonghao.www.common.core.entity.BaseEntity;
import cn.liaozhonghao.www.common.core.enums.PublicEnum;
import cn.liaozhonghao.www.common.core.enums.SecurityRatingEnum;
import cn.liaozhonghao.www.service.user.enums.FundInfoTypeEnum;

import java.io.Serializable;

/**
 * 用户支付配置实体类
 */
public class UserPayConfig extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // TODO 审计状态
    private String auditStatus;

    // TODO 自动结算
    private String isAutoSett;

    // 支付产品编号
    private String productCode;

    // 支付产品名称
    private String productName;

    // 用户编号
    private String userNo;

    // 用户名称
    private String userName;

    // TODO ??
    private Integer riskDay;

    // 支付账户key
    private String payKey;

    // 资金流入类型
    private String fundIntoType;

    // 支付账户密钥
    private String paySecret;

    /** 安全等级 **/
    private String securityRating = SecurityRatingEnum.MD5.name();


    /**商户服务器IP **/
    private String merchantServerIp;

    public String getPaySecret() {
        return paySecret;
    }

    public void setPaySecret(String paySecret) {
        this.paySecret = paySecret;
    }

    public String getFundIntoType() {
        return fundIntoType;
    }

    public void setFundIntoType(String fundIntoType) {
        this.fundIntoType = fundIntoType;
    }



    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public String getIsAutoSett() {
        return isAutoSett;
    }

    public void setIsAutoSett(String isAutoSett) {
        this.isAutoSett = isAutoSett == null ? null : isAutoSett.trim();
    }
    
    public String getPayKey() {
		return payKey;
	}

	public void setPayKey(String payKey) {
		this.payKey = payKey;
	}

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

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getRiskDay() {
        return riskDay;
    }

    public void setRiskDay(Integer riskDay) {
        this.riskDay = riskDay;
    }

    public String getAuditStatusDesc() {
    	return PublicEnum.getEnum(this.getAuditStatus()).getDesc();
    }
    
    public String getFundIntoTypeDesc() {
    	return FundInfoTypeEnum.getEnum(this.getFundIntoType()).getDesc();
    }

    public String getSecurityRating() {
        return securityRating;
    }

    public void setSecurityRating(String securityRating) {
        this.securityRating = securityRating;
    }

    public String getMerchantServerIp() {
        return merchantServerIp;
    }

    public void setMerchantServerIp(String merchantServerIp) {
        this.merchantServerIp = merchantServerIp;
    }
}