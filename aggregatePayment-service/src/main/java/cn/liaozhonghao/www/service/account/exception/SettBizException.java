package cn.liaozhonghao.www.service.account.exception;

import cn.liaozhonghao.www.common.core.exception.BizException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 结算服务业务异常类,异常代码8位数字组成,前4位固定1001打头,后4位自定义
 */
public class SettBizException extends BizException {

	private static final long serialVersionUID = 1L;
	private static final Log LOG = LogFactory.getLog(SettBizException.class);

	public static final SettBizException SETT_STATUS_ERROR = new SettBizException(10010001, "结算状态错误");

	public SettBizException() {
	}

	public SettBizException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}

	public SettBizException(int code, String msg) {
		super(code, msg);
	}

	/**
	 * 实例化异常
	 * 
	 * @param msgFormat
	 * @param args
	 * @return
	 */
	public SettBizException newInstance(String msgFormat, Object... args) {
		return new SettBizException(this.code, msgFormat, args);
	}

	public SettBizException print() {
		LOG.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
		return this;
	}
}
