package cn.liaozhonghao.www.service.user.service.impl;

import cn.liaozhonghao.www.common.core.enums.PayTypeEnum;
import cn.liaozhonghao.www.common.core.enums.PayWayEnum;
import cn.liaozhonghao.www.common.core.enums.PublicEnum;
import cn.liaozhonghao.www.common.core.enums.PublicStatusEnum;
import cn.liaozhonghao.www.common.core.exception.BizException;
import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.common.core.utils.StringUtil;
import cn.liaozhonghao.www.service.user.dao.PayWayDao;
import cn.liaozhonghao.www.service.user.entity.PayProduct;
import cn.liaozhonghao.www.service.user.entity.PayWay;
import cn.liaozhonghao.www.service.user.exception.PayBizException;
import cn.liaozhonghao.www.service.user.service.PayProductService;
import cn.liaozhonghao.www.service.user.service.PayWayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支付方式service实现类
 */
@Service("rpPayWayService")
public class PayWayServiceImpl implements PayWayService {

	@Autowired
	private PayWayDao payWayDao;
	
	@Autowired
	private PayProductService payProductService;
	
	@Override
	public void saveData(PayWay payWay) {
		payWayDao.insert(payWay);
	}

	@Override
	public void updateData(PayWay payWay) {
		payWayDao.update(payWay);
	}

	@Override
	public PayWay getDataById(String id) {
		return payWayDao.getById(id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, PayWay payWay) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		paramMap.put("payProductCode", payWay.getPayProductCode());
		paramMap.put("payWayName", payWay.getPayWayName());
		paramMap.put("payTypeName", payWay.getPayTypeName());
		return payWayDao.listPage(pageParam, paramMap);
	}
	
	@Override
	public PayWay getByPayWayTypeCode(String payProductCode, String payWayCode, String payTypeCode){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("payProductCode", payProductCode);
		paramMap.put("payTypeCode", payTypeCode);
		paramMap.put("payWayCode", payWayCode);
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		return payWayDao.getBy(paramMap);
	}
	
	/**
	 * 绑定支付费率
	 * @param payWayCode
	 * @param payTypeCode
	 * @param payRate
	 */
	@Override
	public void createPayWay(String payProductCode, String payWayCode, String payTypeCode, Double payRate) throws BizException {
		PayWay payWay = getByPayWayTypeCode(payProductCode,payWayCode,payTypeCode);
		if(payWay!=null){
			throw new PayBizException(PayBizException.PAY_TYPE_IS_EXIST,"支付渠道已存在");
		}
		
		PayProduct rpPayProduct = payProductService.getByProductCode(payProductCode, null);
		if(rpPayProduct.getAuditStatus().equals(PublicEnum.YES.name())){
			throw new PayBizException(PayBizException.PAY_PRODUCT_IS_EFFECTIVE,"支付产品已生效，无法绑定！");
		}
		
		PayWay rpPayWay = new PayWay();
		rpPayWay.setPayProductCode(payProductCode);
		rpPayWay.setPayRate(payRate);
		rpPayWay.setPayWayCode(payWayCode);
		rpPayWay.setPayWayName(PayWayEnum.getEnum(payWayCode).getDesc());
		rpPayWay.setPayTypeCode(payTypeCode);
		rpPayWay.setPayTypeName(PayTypeEnum.getEnum(payTypeCode).getDesc());
		rpPayWay.setStatus(PublicStatusEnum.ACTIVE.name());
		rpPayWay.setCreateTime(new Date());
		rpPayWay.setId(StringUtil.get32UUID());
		saveData(rpPayWay);
	}
	
	/**
	 * 根据支付产品获取支付方式
	 */
	@Override
	public List<PayWay> listByProductCode(String payProductCode){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("payProductCode", payProductCode);
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		return payWayDao.listBy(paramMap);
	}
	
	/**
	 * 获取所有支付方式
	 */
	@Override
	public List<PayWay> listAll(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		return payWayDao.listBy(paramMap);
	}
}