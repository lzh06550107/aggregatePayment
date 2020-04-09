package cn.liaozhonghao.www.service.user.exception;

import cn.liaozhonghao.www.common.core.exception.BizException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 用户业务异常类
 */
public class UserBizException extends BizException {

	private static final long serialVersionUID = -6402548375645868682L;

	/** 用户不存在 **/
    public static final int USER_IS_NULL = 101;

    /** 用户支付配置有误 **/
    public static final int USER_PAY_CONFIG_ERRPR = 102;
    
    public static final UserBizException USER_BANK_ACCOUNT_IS_NULL = new UserBizException(10010002, "用户未设置银行账户信息!");

    private static final Log LOG = LogFactory.getLog(UserBizException.class);

    public UserBizException() {
    }

    public UserBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public UserBizException(int code, String msg) {
        super(code, msg);
    }

    public UserBizException print() {
        LOG.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }
}
