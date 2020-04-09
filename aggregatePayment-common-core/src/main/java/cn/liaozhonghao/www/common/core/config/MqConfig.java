package cn.liaozhonghao.www.common.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * activemq配置，读取配置文件中的配置
 * TODO 不是Spring配置文件
 */
public class MqConfig {

	protected static final Logger LOG = LoggerFactory.getLogger(MqConfig.class);
	
	/** 商户通知队列 **/
	public static String MERCHANT_NOTIFY_QUEUE = "";

	/** 订单通知队列 **/
	public static String ORDER_NOTIFY_QUEUE = "";
	
	private static Properties properties = null;
	
	static{
		if(null == properties){
			properties  = new Properties();
		}
		InputStream proFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("mq_config.properties");
		try {
			properties.load(proFile);
			init(properties);
		} catch (IOException e) {
			LOG.error("=== load and init mq exception:" + e);
		}
	}

	// 初始化mq_config.properties中配置
	private static void init(Properties properties){
		MERCHANT_NOTIFY_QUEUE = properties.getProperty("tradeQueueName.notify");
		ORDER_NOTIFY_QUEUE = properties.getProperty("orderQueryQueueName.query");
	}
}
