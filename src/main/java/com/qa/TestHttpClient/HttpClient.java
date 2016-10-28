package com.qa.TestHttpClient;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
//import net.sf.json.JSONObject;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpClient {

	private CloseableHttpClient httpclient = null;

	private String responseBody = null;
	private long responseTime = 999999999;
	
	@SuppressWarnings("deprecation")
	public HttpClient() {
		//this.httpclient = HttpClients.createDefault();
		
		SSLContext ctx = null;
	  try {
	  ctx = SSLContext.getInstance("TLS");

	  X509TrustManager tm = new X509TrustManager()
	  {
	  public void checkClientTrusted(X509Certificate[] chain,  String authType) throws CertificateException{}
	  public void checkServerTrusted(X509Certificate[] chain,  String authType) throws CertificateException{}
	  public X509Certificate[] getAcceptedIssuers(){return null;}
	  };
	  ctx.init(null, new TrustManager[]{tm}, null);
	  } catch (Exception e) {
	  e.printStackTrace();
	  }
	  
	//这个好像是HOST验证
	  X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
			public void verify(String arg0, SSLSocket arg1) throws IOException {}
			public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {}
			public void verify(String arg0, X509Certificate arg1) throws SSLException {}
		};
	  
	  SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
	  socketFactory.setHostnameVerifier(hostnameVerifier);
	  //通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
//	  HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
//	  this.httpclient = httpClientBuilder.build();
	  httpclient = new DefaultHttpClient();
	  httpclient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443));
	  
	  
//	  //httpClientBuilder.setSSLContext(ctx);
//	  httpClientBuilder.setSslcontext(ctx);
//	   
	}

	public String httpPostRequest(String URL, HttpRequestCallback ci) throws IOException {
		try {
			
			Iterator<Map.Entry<String, String>> iter = ci.AddHeaderParameters();
			HttpPost httpPost = new HttpPost(URL); 
			
			httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
//			httpPost.addHeader("User-ID", "0");
			int flag = 0;
			while (iter.hasNext()) {
				Map.Entry<String, String> me = iter.next();
				httpPost.addHeader(me.getKey(), me.getValue());
				System.out.println("请求头 Key： "+me.getKey()+"------请求头 Value： "+me.getValue());
				if("os".equals(me.getKey()))
				{
					flag = 1;
				}
			}
			if(0 == flag)
			{
				httpPost.addHeader("os", "monitor");
//				System.out.println("请求头 Key： "+"os"+"------请求头 Value： "+"monitor");
			}
            
			if(null != ci.addParam())
			{
			System.out.println("请求参数：  "+ci.addParam());
			httpPost.setEntity(new StringEntity(ci.addParam()));
			}
			
			if(null != ci.addJsonParam())
			{
				System.out.println("请求参数：  "+ci.addJsonParam());
				httpPost.setEntity(new StringEntity(ci.addJsonParam()));
			}
			
			long temp = System.currentTimeMillis();
			// Before end
			System.out.println("请求地址：  " + httpPost.getURI());
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				public String handleResponse(final HttpResponse response)
						throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity)
								: null;
					} else {
						throw new ClientProtocolException(
								"Unexpected response status: " + status);
					}
				}
			};

			setResponseBody(httpclient.execute(httpPost, responseHandler));
			System.out.println("-------------------------------------------");

			responseTime = System.currentTimeMillis() - temp;
			// System.out.println(getResponseBody());	
			//System.out.println("responseBody: " + new String (responseBody.getBytes("ISO-8859-1"),"utf-8"));
			System.out.println("完整响应体： " + responseBody);
//			System.out.println("responseBody: " + new String (responseBody.getBytes("ISO-8859-1"),"utf-8"));
			System.out.println("-------------------------------------------");
			return responseBody;
		} finally {
			httpclient.close();
		}
	}

	public String httpGetRequest(String URL, HttpRequestCallback ci)
			throws IOException {
		try {
			
			Iterator<Map.Entry<String, String>> iter = ci.AddHeaderParameters();
			HttpGet httpGet = new HttpGet(URL);
			
			httpGet.addHeader("Content-Type", "application/json;charset=UTF-8");
//			httpPost.addHeader("User-ID", "0");
			int flag = 0;
			while (iter.hasNext()) {
				Map.Entry<String, String> me = iter.next();
				httpGet.addHeader(me.getKey(), me.getValue());
				System.out.println("请求头 Key： "+me.getKey()+"------请求头 Value： "+me.getValue());
				if("os".equals(me.getKey()))
				{
					flag = 1;
				}
			}
			if(0 == flag)
			{
				httpGet.addHeader("os", "monitor");
//				System.out.println("请求头 Key： "+"os"+"------请求头 Value： "+"monitor");
			}
			
//			if(null != ci.addParam())
//			{
//			System.out.println("请求参数：  "+ci.addParam());
//			
//			}
			long temp = System.currentTimeMillis();
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				public String handleResponse(final HttpResponse response)
						throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity)
								: null;
					} else {
						throw new ClientProtocolException(
								"Unexpected response status: " + status);
					}
				}
			};
			responseTime = System.currentTimeMillis() - temp;
			setResponseBody(httpclient.execute(httpGet, responseHandler));
			System.out.println("-------------------------------------------");
			System.out.println(getResponseBody());
			System.out.println("-------------------------------------------");

			return responseBody;
		} finally {
			httpclient.close();
		}
	}

	public String httpRequest(String URL, HttpRequestCallback ci, String type) throws IOException
	{
		if("get".equals(type.toLowerCase()))
		{
			return httpGetRequest(URL,ci);
		}else if("post".equals(type.toLowerCase()))
		{
			return httpPostRequest(URL,ci);
		}else{
			return null;
		}
	}
	
	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public long getResponseTime() {
		return responseTime;
	}
}