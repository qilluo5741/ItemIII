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
	
	// ���õ���ģʽ�Ż�httpclient
	private static CloseableHttpClient httpClient = null;
	// config����
	private static RequestConfig config = null;

	// ���ж������ͬһ��cilent�ᵼ�¶��̴߳���
	public static synchronized CloseableHttpClient getHttpClient()
			throws IOException {
		if (httpClient == null) {
			// ���ӳع�����
			PoolingHttpClientConnectionManager manager = getPoolManager();

			// �����������
			config = RequestConfig.custom().setAuthenticationEnabled(true)// ����
					.setSocketTimeout(5000).setConnectTimeout(5000)// ���ӳ�ʱ
					.setConnectionRequestTimeout(5000)// ����ʱ
					.build();

			// �Զ���ά�����ڵĲ���
			ConnectionKeepAliveStrategy myStrategy = customStrategy();

			httpClient = HttpClients.custom().setConnectionManager(manager)// ���ӹ�����
					.setKeepAliveStrategy(myStrategy)// ά������
					.build();
		}
		return httpClient;
	}

	/**
	 * �õ����ӳع�����
	 * 
	 * @return
	 */
	public static PoolingHttpClientConnectionManager getPoolManager()
			throws IOException {
		// ���ӳع�����
		PoolingHttpClientConnectionManager manager;
		manager = new PoolingHttpClientConnectionManager();
		manager.setMaxTotal(3000);// ������󲢷���
		manager.setDefaultMaxPerRoute(200);// ����ÿ��·�����ֵ
		manager.setValidateAfterInactivity(2000);
		HttpHost host = new HttpHost("localhost", 80);
		manager.setMaxPerRoute(new HttpRoute(host), 50);
		return manager;
	}

	/**
	 * �Զ���ά��http�������ڲ���
	 * 
	 * @return
	 */
	public static ConnectionKeepAliveStrategy customStrategy()
			throws IOException {
		// �Զ���ά�����ڵĲ���
		ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {

			public long getKeepAliveDuration(HttpResponse response,
					HttpContext context) {
				// ά����������
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
					// ά��5s
					return 5 * 1000;
				} else {
					// ά��30s
					return 30 * 1000;
				}
			}
		};
		return myStrategy;
	}
	public static String doPost(String httpUrl, Map<String, String> paramsMap)
			throws IOException {
		CloseableHttpClient httpClient = getHttpClient();
		HttpPost httpPost = new HttpPost(httpUrl);// post����
		// �����������
		List<NameValuePair> nvps = getParamsList(paramsMap);
		String result = "";
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
			httpPost.setConfig(config);
			// ִ�н��
			HttpResponse response = httpClient.execute(httpPost);
			// ���ؽ��
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
			// �����������
			StringEntity params = new StringEntity(jsonStr, "utf-8");
			httpPost.setEntity(params);
			// ִ�н��
			HttpResponse response = httpClient.execute(httpPost);
			// ���ؽ��
			result = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * �õ��������
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
		 String msg="{\"Title\":\"YIXING\",\"PlateNumber\":\"��A12345\",\"Start\":\"2015/08/01 12:00:00\",\"End\":\"2017/06/01 12:00:00\",\"Type\":\"W\",\"Action\":\"add\"}";
		 System.out.println(decode(doPost(httpUrl,msg),"utf-8"));
	}
}
