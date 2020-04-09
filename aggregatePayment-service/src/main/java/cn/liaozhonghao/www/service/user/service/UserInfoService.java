package cn.liaozhonghao.www.service.user.service;

import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.service.user.entity.UserInfo;

import java.util.List;

/**
 * 用户信息service接口
 */
public interface UserInfoService {
	
	/**
	 * 保存用户信息
	 */
	void saveData(UserInfo rpUserInfo);

	/**
	 * 更新用户信息
	 */
	void updateData(UserInfo rpUserInfo);

	/**
	 * 根据id获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	UserInfo getDataById(String id);
	

	/**
	 * 获取分页数据
	 * 
	 * @param pageParam
	 * @return
	 */
	PageBean listPage(PageParam pageParam, UserInfo rpUserInfo);
	
	/**
     * 用户线下注册
     * 
     * @param userName
     *            用户名
     * @param mobile
     *            手机号
     * @param password
     *            密码
     * @return
     */
    void registerOffline(String userName, String mobile, String password) ;

	/**
	 * 根据商户编号获取商户信息
	 * @param merchantNo
	 * @return
	 */
	UserInfo getDataByMerchentNo(String merchantNo);
	
	/**
	 * 根据手机号获取商户信息
	 * @param mobile
	 * @return
	 */
	UserInfo getDataByMobile(String mobile);
	
	/**
	 * 获取所有用户
	 * @return
	 */
	List<UserInfo> listAll();
	
}