package com.qa.NewBuildingOpSys;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import com.qa.TestHttpClient.HttpClient;
import com.qa.TestHttpClient.HttpRequestCallback;
import com.qa.constants.ConfigConstants;
import com.qa.utils.Data;
import com.qa.utils.JSONParse;

public class NewBuildingOpSys {
	Data data;
	JSONParse jp;
	String URL;
	String env = null;
	String responseBody = null;
	HttpClient testRequst = new HttpClient();

	public NewBuildingOpSys(String URL) {
		this.data = new Data();
		this.jp = new JSONParse();
		this.URL = URL;
	}
	public NewBuildingOpSys(String URL,String env) {
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
			
			
			if(null == env||"test".equals(env.toLowerCase())){
			 responseBody = testRequst.httpPostRequest(
					ConfigConstants.NEWBUILDING_OP_SYS_TEST_BASE_URL + URL,
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
			}else if("sim".equals(env.toLowerCase()))
			{
				 responseBody = testRequst.httpPostRequest(
    					ConfigConstants.NEWBUILDING_OP_SYS_SIM_BASE_URL + URL,
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
			}else{
				System.out.println("env name error");
	        	return false;
			}
			
			JSONObject objResponse = new JSONObject(responseBody);
			jp.parseJson(objResponse);
			return jp.checkParam(param);
		}
	}

	public boolean checkContainsString(String param) {
		return responseBody.contains(param);
	}
	
	public boolean checkEqualString(String param) {
		return responseBody.equals(param);
	}
	
	public boolean checkResponseTime(String param) {
		System.out.println("响应时间：  "+testRequst.getResponseTime()+"ms");
		return testRequst.getResponseTime()<Long.parseLong(param)?true:false;
	}
}