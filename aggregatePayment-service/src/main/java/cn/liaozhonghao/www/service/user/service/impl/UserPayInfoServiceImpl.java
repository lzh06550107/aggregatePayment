package cn.liaozhonghao.www.service.user.service.impl;

import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.service.user.dao.UserPayInfoDao;
import cn.liaozhonghao.www.service.user.entity.UserPayInfo;
import cn.liaozhonghao.www.service.user.service.UserPayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户第三方支付信息service实现类
 */
@Service("rpUserPayInfoService")
public class UserPayInfoServiceImpl implements UserPayInfoService {

	@Autowired
	private UserPayInfoDao rpUserPayInfoDao;
	
	@Override
	public void saveData(UserPayInfo rpUserPayInfo) {
		rpUserPayInfoDao.insert(rpUserPayInfo);
	}

	@Override
	public void updateData(UserPayInfo rpUserPayInfo) {
		rpUserPayInfoDao.update(rpUserPayInfo);
	}

	@Override
	public UserPayInfo getDataById(String id) {
		return rpUserPayInfoDao.getById(id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, UserPayInfo rpUserPayInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return rpUserPayInfoDao.listPage(pageParam, paramMap);
	}

	/**
	 * 通过商户编号获取商户支付配置信息
	 *
	 * @param userNo
	 * @param payWayCode
	 * @return
	 */
	@Override
	public UserPayInfo getByUserNo(String userNo, String payWayCode) {
		return rpUserPayInfoDao.getByUserNo(userNo, payWayCode);
	}
}