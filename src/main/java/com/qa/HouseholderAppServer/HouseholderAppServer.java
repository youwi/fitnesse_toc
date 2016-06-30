package com.qa.HouseholderAppServer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import com.qa.TestHttpClient.HttpClient;
import com.qa.TestHttpClient.HttpRequestCallback;
import com.qa.constants.ConfigConstants;
import com.qa.utils.Data;
import com.qa.utils.JSONParse;

public class HouseholderAppServer {
	Data data;
	JSONParse jp;
	String URL;
	String env = null;

	public HouseholderAppServer(String URL) {
		this.data = new Data();
		this.jp = new JSONParse();
		this.URL = URL;
	}
	
	public HouseholderAppServer(String URL,String env) {
		this.data = new Data();
		this.jp = new JSONParse();
		this.URL = URL;
		this.env = env;
	}
	
	public void setJsonParam(String json)
			throws Exception {
		data.setJsonParam(json);
	}
	
	public void setParam(String name, String value, String type)
			throws Exception {
		data.setParameters(name, value, type);
	}
	 
	//set header parameter 
	public void setHeaderParam(String name, String value)
			throws Exception {
		data.setHeaderParameters(name, value);
	}
		

	public String getParam(String key) {
		return jp.getResult(key);
	}

	public boolean testRun(String param) throws IOException {
		if (null == param.trim()) {
			System.out.println("null paramters!!");
			return false;
		} else {
			HttpClient testRequst = new HttpClient();
			String responseBody = testRequst.httpPostRequest(
					ConfigConstants.WKZF_HOUSEHOLDER_BASE_URL + URL,
					new HttpRequestCallback() {
						@Override
						public String addParam() {
							// TODO Auto-generated method stub
							return data.getAddParam();
						}

						@Override
						public Iterator<Map.Entry<String, String>> AddHeaderParameters() {
							// TODO Auto-generated method stub
							return data.getAddHeaderParam();
						}

						@Override
						public String addJsonParam() {
							// TODO Auto-generated method stub
							return data.getJsonParam();
						}
					});
			JSONObject objResponse = new JSONObject(responseBody);
			jp.parseJson(objResponse);
			return jp.checkParam(param);
		}
	}
}
