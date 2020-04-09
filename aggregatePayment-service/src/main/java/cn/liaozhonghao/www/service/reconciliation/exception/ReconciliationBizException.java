package cn.liaozhonghao.www.service.reconciliation.exception;

import cn.liaozhonghao.www.common.core.exception.BizException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 对账业务 .
 */
public class ReconciliationBizException extends BizException {

	private static final long serialVersionUID = 1L;

	/** 根据银行订单号查询的支付记录不存在 **/
	public static final int TRADE_ORDER_NO_EXCIT = 20020001;

	private static final Log LOG = LogFactory.getLog(ReconciliationBizException.class);

	public ReconciliationBizException() {
	}

	public ReconciliationBizException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}

	public ReconciliationBizException(int code, String msg) {
		super(code, msg);
	}

	public ReconciliationBizException print() {
		LOG.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
		return this;
	}
}
