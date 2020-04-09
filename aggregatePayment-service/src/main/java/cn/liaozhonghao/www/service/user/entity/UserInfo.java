package cn.liaozhonghao.www.service.user.entity;

import cn.liaozhonghao.www.common.core.entity.BaseEntity;
import cn.liaozhonghao.www.common.core.enums.PublicStatusEnum;

import java.io.Serializable;

/**
 * 用户信息
 */
public class UserInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // 用户编号
    private String userNo;

    // 用户名称
    private String userName;

    // TODO ??
    private String accountNo;

    // 手机号码
    private String mobile;

    // 用户密码
    private String password;

    /** 支付密码 */
	private String payPwd;

    public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }
    
    public String getStatusDesc() {
        return PublicStatusEnum.getEnum(this.getStatus()).getDesc();
    }

}