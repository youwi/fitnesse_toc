package com.qa.Financial2Sys;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.qa.BaseServer;
import org.json.JSONObject;

import com.qa.TestHttpClient.HttpClient;
import com.qa.TestHttpClient.HttpRequestCallback;
import com.qa.constants.ConfigConstants;
import com.qa.utils.Data;
import com.qa.utils.JSONParse;

public class Financial2Sys  extends BaseServer{

	public Financial2Sys(String URL) {
		 super(URL);
		BASE_URL=ConfigConstants.FINANCIAL2_SYS_TEST_BASE_URL;
	}

	public Financial2Sys(String URL,String env) {
		super(URL,env);
		BASE_URL=ConfigConstants.FINANCIAL2_SYS_TEST_BASE_URL;
	}
	
	public Financial2Sys(String URL,String env,String type) {
		super(URL,env,type);
		BASE_URL=ConfigConstants.FINANCIAL2_SYS_TEST_BASE_URL;
	}


	public boolean loginRun(String param) throws IOException {
		if (null == param.trim()) {
			System.out.println("null paramters!!");
			return false;
		} else {
			JSONObject jo=requestForJSON(ConfigConstants.SSO_TEST_BASE_URL + URL,this.getData());
			jp.parseJson(jo);
			return jp.checkParam(param);
		}
	}

}
