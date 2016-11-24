package com.sharebo.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
public class HttpClientUtil {

//	private static final String FORM_CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8";
//	private static final String JSON_CONTENT_TYPE = "application/json;charset=utf-8";
//	private static final String XML_CONTENT_TYPE = "application/xml;charset=utf-8";
	
	// 采用单例模式优化httpclient
	private static CloseableHttpClient httpClient = null;
	// config对象
	private static RequestConfig config = null;

	// 所有对象管理同一个cilent会导致多线程错误
	public static synchronized CloseableHttpClient getHttpClient()
			throws IOException {
		if (httpClient == null) {
			// 连接池管理器
			PoolingHttpClientConnectionManager manager = getPoolManager();

			// 相关连接设置
			config = RequestConfig.custom().setAuthenticationEnabled(true)// 激活
					.setSocketTimeout(5000).setConnectTimeout(5000)// 连接超时
					.setConnectionRequestTimeout(5000)// 请求超时
					.build();

			// 自定义维持周期的策略
			ConnectionKeepAliveStrategy myStrategy = customStrategy();

			httpClient = HttpClients.custom().setConnectionManager(manager)// 连接管理器
					.setKeepAliveStrategy(myStrategy)// 维持周期
					.build();
		}
		return httpClient;
	}

	/**
	 * 得到连接池管理器
	 * 
	 * @return
	 */
	public static PoolingHttpClientConnectionManager getPoolManager()
			throws IOException {
		// 连接池管理器
		PoolingHttpClientConnectionManager manager;
		manager = new PoolingHttpClientConnectionManager();
		manager.setMaxTotal(3000);// 设置最大并发数
		manager.setDefaultMaxPerRoute(200);// 设置每个路由最大值
		manager.setValidateAfterInactivity(2000);
		HttpHost host = new HttpHost("localhost", 80);
		manager.setMaxPerRoute(new HttpRoute(host), 50);
		return manager;
	}

	/**
	 * 自定义维持http生命周期策略
	 * 
	 * @return
	 */
	public static ConnectionKeepAliveStrategy customStrategy()
			throws IOException {
		// 自定义维持周期的策略
		ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {

			public long getKeepAliveDuration(HttpResponse response,
					HttpContext context) {
				// 维持连接周期
				HeaderElementIterator it = new BasicHeaderElementIterator(
						response.headerIterator(HTTP.CONN_KEEP_ALIVE));
				while (it.hasNext()) {
					HeaderElement he = it.nextElement();
					String param = he.getName();
					String value = he.getValue();
					if (value != null && param.equalsIgnoreCase("timeout")) {
						try {
							return Long.parseLong(value) * 1000;
						} catch (NumberFormatException ignore) {
						}
					}
				}
				HttpHost target = (HttpHost) context
						.getAttribute(HttpClientContext.HTTP_TARGET_HOST);
				if ("www.naughty-server.com".equalsIgnoreCase(target
						.getHostName())) {
					// 维持5s
					return 5 * 1000;
				} else {
					// 维持30s
					return 30 * 1000;
				}
			}
		};
		return myStrategy;
	}
	public static String doPost(String httpUrl, Map<String, String> paramsMap)
			throws IOException {
		CloseableHttpClient httpClient = getHttpClient();
		HttpPost httpPost = new HttpPost(httpUrl);// post请求
		// 传递请求参数
		List<NameValuePair> nvps = getParamsList(paramsMap);
		String result = "";
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
			httpPost.setConfig(config);
			// 执行结果
			HttpResponse response = httpClient.execute(httpPost);
			// 返回结果
			result = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return result;
	}
	public static String doGet(String httpUrl,String params) throws IOException{
		String result = "";
		CloseableHttpClient httpClient = getHttpClient();
		String uri = httpUrl + "?" + params;
		HttpGet httpGet = new HttpGet(uri);
		httpGet.setConfig(config);
		HttpResponse response = httpClient.execute(httpGet);
		result = EntityUtils.toString(response.getEntity(), "utf-8");
		return result;
	}
	/**
	 * 
	 * <p>
	 * Title: doPost
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpUrl
	 * @param jsonStr
	 * @return
	 * @throws IOException 
	 */
	public static String doPost(String httpUrl, String jsonStr) throws IOException {
		CloseableHttpClient httpClient = getHttpClient();
		HttpPost httpPost = new HttpPost(httpUrl);
		String result = "";
		try {
			// 传递请求参数
			StringEntity params = new StringEntity(jsonStr, "utf-8");
			httpPost.setEntity(params);
			// 执行结果
			HttpResponse response = httpClient.execute(httpPost);
			// 返回结果
			result = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 得到请求参数
	 * 
	 * @param paramsMap
	 * @return
	 */
	public static List<NameValuePair> getParamsList(
			Map<String, String> paramsMap) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (paramsMap == null || paramsMap.size() == 0) {
			return null;
		}
		for (Map.Entry<String, String> map : paramsMap.entrySet()) {
			params.add(new BasicNameValuePair(map.getKey(), map.getValue()));
		}
		return params;
	}
	
	public static String decode(String url,String decode) throws UnsupportedEncodingException{
		return URLDecoder.decode(url,decode);
	}
	
	public static void main(String[] args) throws Exception {
		 String httpUrl = "http://101.82.251.53:8888";
		 String msg="{\"Title\":\"YIXING\",\"PlateNumber\":\"京A12345\",\"Start\":\"2015/08/01 12:00:00\",\"End\":\"2017/06/01 12:00:00\",\"Type\":\"W\",\"Action\":\"add\"}";
		 System.out.println(decode(doPost(httpUrl,msg),"utf-8"));
	}
}
