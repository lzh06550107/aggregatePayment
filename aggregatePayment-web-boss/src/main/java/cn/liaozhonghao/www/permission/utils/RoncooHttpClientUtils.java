package cn.liaozhonghao.www.permission.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * httpClientUtils
 */
public class RoncooHttpClientUtils {

	/**
	 * 调用 API
	 * 
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings({ "resource", "deprecation" })
	public static String post(String url, String parameters) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost method = new HttpPost(url);
		String body = null;

		if (method != null & parameters != null && !"".equals(parameters.trim())) {
			try {

				// 建立一个NameValuePair数组，用于存储欲传送的参数
				method.addHeader("Content-type", "application/json; charset=utf-8");
				method.setHeader("Accept", "application/json");
				method.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));

				HttpResponse response = httpClient.execute(method);

				int statusCode = response.getStatusLine().getStatusCode();

				if (statusCode != HttpStatus.SC_OK) {
					return "1";// 返回1表示请求失败
				}

				// Read the response body
				body = EntityUtils.toString(response.getEntity());

			} catch (IOException e) {
				// 网络错误
				return "2";
			} finally {
			}

		}
		return body;
	}

}
