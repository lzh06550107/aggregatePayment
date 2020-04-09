package cn.liaozhonghao.www.service.reconciliation.fileDown.impl;

import cn.liaozhonghao.www.service.reconciliation.fileDown.service.FileDown;
import cn.liaozhonghao.www.service.reconciliation.fileDown.service.ReconciliationFactory;
import cn.liaozhonghao.www.service.reconciliation.utils.ReconciliationConfigUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

/**
 * 文件下载factory
 */
@Service("reconciliationFactory")
public class ReconciliationFactoryImpl implements ReconciliationFactory, ApplicationContextAware {

	private static ApplicationContext applicationContext;

	/**
	 * 去Spring容器中根据beanName获取对象（也可以直接根据名字创建实例，可以参考后面流程中的parser）
	 * 
	 * @param payInterface
	 * @return
	 */
	public Object getService(String payInterface) {
		return applicationContext.getBean(payInterface);
	}

	/**
	 * 账单下载
	 * 
	 * @param payInterface
	 *            支付渠道
	 * 
	 * @param billDate
	 *            账单日
	 */
	public File fileDown(String payInterface, Date billDate) throws Exception {
		// 找到具体的FileDown实现，做向上转型
		FileDown fileDown = (FileDown) this.getService(payInterface);
		// 加载配置文件，获取下载的对账文件保存路径
		String dir = ReconciliationConfigUtil.readConfig("dir") + payInterface.toLowerCase();
		return fileDown.fileDown(billDate, dir);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
