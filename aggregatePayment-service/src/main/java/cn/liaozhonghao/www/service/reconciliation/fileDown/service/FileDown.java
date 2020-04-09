package cn.liaozhonghao.www.service.reconciliation.fileDown.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 对账文件下载
 */
public interface FileDown {

	/**
	 * 对帐文件下载，获得文件输出
	 * 
	 * @param fileDate
	 *            账单日
	 * @param dir
	 *            账单文件存储地址
	 * @return
	 * @throws Exception
	 */
	File fileDown(Date fileDate, String dir) throws IOException, Exception;

}
