package cn.liaozhonghao.www.service.account.entity;

import cn.liaozhonghao.www.common.core.entity.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 账户信息
 */
public class Account extends BaseEntity implements Serializable {

	/** 账户编号 **/
    private String accountNo;
    
    /** 账户余额 **/
    private BigDecimal balance;

    /** 不可用余额 **/
    private BigDecimal unbalance;

    /** 保证金 **/
    private BigDecimal securityMoney;

    /** 总收益 **/
    private BigDecimal totalIncome;

    /** 总支出 **/
    private BigDecimal totalExpend;

    /** 今日收益  **/
    private BigDecimal todayIncome;

    /** 今日支出 **/
    private BigDecimal todayExpend;

    /** 账户类型 **/
    private String accountType;

    /** 可结算金额 **/
    private BigDecimal settAmount;

    /** 用户编号 **/
    private String userNo;

    private static final long serialVersionUID = 1L;
    
    /************************* just show ************************************/
	private String userName;
	/************************* just show ************************************/
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取可用余额
	 * 
	 * @return
	 */
	public BigDecimal getAvailableBalance() {
		return this.balance.subtract(unbalance);
	}

	/**
	 * 获取实际可结算金额
	 * 
	 * @return
	 */
	public BigDecimal getAvailableSettAmount() {
		BigDecimal subSettAmount = this.settAmount.subtract(unbalance);
		// 当可用余额小于实际可结算金额时，返回可用余额
		if (getAvailableBalance().compareTo(subSettAmount) == -1) {
			return getAvailableBalance();
		}
		return subSettAmount;
	}

	/**
	 * 验证可用余额是否足够
	 * 
	 * @param amount
	 * @return
	 */
	public boolean availableBalanceIsEnough(BigDecimal amount) {

		return this.getAvailableBalance().compareTo(amount) >= 0;
	}


    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getUnbalance() {
        return unbalance;
    }

    public void setUnbalance(BigDecimal unbalance) {
        this.unbalance = unbalance;
    }

    public BigDecimal getSecurityMoney() {
        return securityMoney;
    }

    public void setSecurityMoney(BigDecimal securityMoney) {
        this.securityMoney = securityMoney;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getTotalExpend() {
        return totalExpend;
    }

    public void setTotalExpend(BigDecimal totalExpend) {
        this.totalExpend = totalExpend;
    }

    public BigDecimal getTodayIncome() {
        return todayIncome;
    }

    public void setTodayIncome(BigDecimal todayIncome) {
        this.todayIncome = todayIncome;
    }

    public BigDecimal getTodayExpend() {
        return todayExpend;
    }

    public void setTodayExpend(BigDecimal todayExpend) {
        this.todayExpend = todayExpend;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    public BigDecimal getSettAmount() {
        return settAmount;
    }

    public void setSettAmount(BigDecimal settAmount) {
        this.settAmount = settAmount;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

}