package com.qa.TestHttpClient;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
//import net.sf.json.JSONObject;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.extractor.ExcelExtractor;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpClient {

	private CloseableHttpClient httpclient = null;

	private String responseBody = null;
	private long responseTime = 999999999;

	public HttpClient() {
		SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance("TLS");

		X509TrustManager tm = new X509TrustManager()
		{
			public void checkClientTrusted(X509Certificate[] chain,  String authType) throws CertificateException
			{

			}
			public void checkServerTrusted(X509Certificate[] chain,  String authType) throws CertificateException
			{

			}
			public X509Certificate[] getAcceptedIssuers()
			{
				return null;
			}
		};
		ctx.init(null, new TrustManager[]{tm}, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		//httpClientBuilder.setSSLContext(ctx);
		httpClientBuilder.setSslcontext(ctx);
		 this.httpclient = httpClientBuilder.build();
		//this.httpclient = HttpClients.createDefault();
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

	public String httpPostRequest(String URL, HttpRequestCallback ci, File obj)
			throws IOException {
		if (null == obj) {
			return httpPostRequest(URL, ci);
		}
		try {
			HttpPost httpPost = new HttpPost(URL);
			httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
			// Before begin
			HttpEntity reqEntity = null;
			MultipartEntityBuilder reqEntityBuilder = MultipartEntityBuilder
					.create();
			// reqEntityBuilder.addBinaryBody(name, file);

			// MultipartEntity reqEntity2 = new MultipartEntity();
			// reqEntity2.addPart(KEY_FILE, obj);
			// reqEntityBuilder.addBinaryBody(name, file)
			httpPost.setEntity(reqEntity);
			// Before end
			System.out.println("executing request " + httpPost.getURI());
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
			// System.out.println("-------------------------------------------");
			// System.out.println(getResponseBody());
			// System.out.println("-------------------------------------------");
			return responseBody;
		} finally {
			httpclient.close();
		}
	}

	public String httpGetRequest(String URL, HttpRequestCallback ci)
			throws IOException {
		try {
			HttpGet httpGet = new HttpGet(URL);
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
			setResponseBody(httpclient.execute(httpGet, responseHandler));
			System.out.println("-------------------------------------------");
			System.out.println(getResponseBody());
			System.out.println("-------------------------------------------");

			return responseBody;
		} finally {
			httpclient.close();
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