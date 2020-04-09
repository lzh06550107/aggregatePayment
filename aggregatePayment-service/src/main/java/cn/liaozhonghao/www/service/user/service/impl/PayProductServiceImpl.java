package cn.liaozhonghao.www.service.user.service.impl;

import cn.liaozhonghao.www.common.core.enums.PublicEnum;
import cn.liaozhonghao.www.common.core.enums.PublicStatusEnum;
import cn.liaozhonghao.www.common.core.exception.BizException;
import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.common.core.utils.StringUtil;
import cn.liaozhonghao.www.service.user.dao.PayProductDao;
import cn.liaozhonghao.www.service.user.entity.PayProduct;
import cn.liaozhonghao.www.service.user.entity.PayWay;
import cn.liaozhonghao.www.service.user.entity.UserPayConfig;
import cn.liaozhonghao.www.service.user.exception.PayBizException;
import cn.liaozhonghao.www.service.user.service.PayProductService;
import cn.liaozhonghao.www.service.user.service.PayWayService;
import cn.liaozhonghao.www.service.user.service.UserPayConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支付产品service实现类
 */
@Service("payProductService")
public class PayProductServiceImpl implements PayProductService {

	@Autowired
	private PayProductDao payProductDao;
	
	@Autowired
	private PayWayService payWayService;
	
	@Autowired
	private UserPayConfigService userPayConfigService;
	
	@Override
	public void saveData(PayProduct payProduct) {
		payProductDao.insert(payProduct);
	}

	@Override
	public void updateData(PayProduct payProduct) {
		payProductDao.update(payProduct);
	}

	@Override
	public PayProduct getDataById(String id) {
		return payProductDao.getById(id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, PayProduct rpPayProduct) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		paramMap.put("auditStatus", rpPayProduct.getAuditStatus());
		paramMap.put("productName", rpPayProduct.getProductName());
		return payProductDao.listPage(pageParam, paramMap);
	}
	
	/**
	 * 根据产品编号获取支付产品
	 */
	@Override
	public PayProduct getByProductCode(String productCode, String auditStatus){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productCode", productCode);
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		paramMap.put("auditStatus", auditStatus);
		return payProductDao.getBy(paramMap);
	}
	
	/**
	 * 创建支付产品
	 * @param productCode
	 * @param productName
	 */
	@Override
	public void createPayProduct(String productCode, String productName) throws BizException {
		PayProduct rpPayProduct = getByProductCode(productCode, null);
		if(rpPayProduct != null){
			throw new PayBizException(PayBizException.PAY_PRODUCT_IS_EXIST,"支付产品已存在");
		}
		rpPayProduct = new PayProduct();
		rpPayProduct.setStatus(PublicStatusEnum.ACTIVE.name());
		rpPayProduct.setCreateTime(new Date());
		rpPayProduct.setId(StringUtil.get32UUID());
		rpPayProduct.setProductCode(productCode);
		rpPayProduct.setProductName(productName);
		rpPayProduct.setAuditStatus(PublicEnum.NO.name());
		saveData(rpPayProduct);
	}
	
	/**
	 * 删除支付产品
	 * @param productCode
	 */
	@Override
	public void deletePayProduct(String productCode) throws PayBizException{
		
		List<PayWay> payWayList = payWayService.listByProductCode(productCode);
		if(!payWayList.isEmpty()){
			throw new PayBizException(PayBizException.PAY_PRODUCT_HAS_DATA,"支付产品已关联支付方式，无法删除！");
		}
		
		List<UserPayConfig> payConfigList = userPayConfigService.listByProductCode(productCode);
		if(!payConfigList.isEmpty()){
			throw new PayBizException(PayBizException.PAY_PRODUCT_HAS_DATA,"支付产品已关联用户，无法删除！");
		}
		
		PayProduct rpPayProduct = getByProductCode(productCode, null);
		if(rpPayProduct.getAuditStatus().equals(PublicEnum.YES.name())){
			throw new PayBizException(PayBizException.PAY_PRODUCT_IS_EFFECTIVE,"支付产品已生效，无法删除！");
		}
		
		rpPayProduct.setStatus(PublicStatusEnum.UNACTIVE.name());
		updateData(rpPayProduct);
	}
	
	/**
	 * 获取所有支付产品
	 */
	@Override
	public List<PayProduct> listAll(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		return payProductDao.listBy(paramMap);
	}
	
	/**
	 * 审核
	 * @param productCode
	 * @param auditStatus
	 */
	@Override
	public void audit(String productCode, String auditStatus) throws PayBizException{
		PayProduct rpPayProduct = getByProductCode(productCode, null);
		if(rpPayProduct == null){
			throw new PayBizException(PayBizException.PAY_PRODUCT_IS_NOT_EXIST,"支付产品不存在！");
		}
		
		if(auditStatus.equals(PublicEnum.YES.name())){
			//检查是否已设置支付方式
			List<PayWay> payWayList = payWayService.listByProductCode(productCode);
			if(payWayList.isEmpty()){
				throw new PayBizException(PayBizException.PAY_TYPE_IS_NOT_EXIST,"支付方式未设置，无法操作！");
			}
			
		}else if(auditStatus.equals(PublicEnum.NO.name())){
			//检查是否已有支付配置
			List<UserPayConfig> payConfigList = userPayConfigService.listByProductCode(productCode);
			if(!payConfigList.isEmpty()){
				throw new PayBizException(PayBizException.USER_PAY_CONFIG_IS_EXIST,"支付产品已关联用户支付配置，无法操作！");
			}
		}
		rpPayProduct.setAuditStatus(auditStatus);
		rpPayProduct.setEditTime(new Date());
		updateData(rpPayProduct);
	}
}