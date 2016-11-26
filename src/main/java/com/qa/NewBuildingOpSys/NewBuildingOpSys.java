package com.qa.NewBuildingOpSys;

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

public class NewBuildingOpSys extends BaseServer{
	
	public NewBuildingOpSys(String URL) {
		super(URL);
		BASE_URL=	ConfigConstants.NEWBUILDING_OP_SYS_TEST_BASE_URL;
	}
	public NewBuildingOpSys(String URL,String env) {
		super(URL,env);
		BASE_URL=	ConfigConstants.NEWBUILDING_OP_SYS_TEST_BASE_URL;
	}
	public NewBuildingOpSys(String URL,String env,String type) {
		super(URL,env,type);
		BASE_URL=	ConfigConstants.NEWBUILDING_OP_SYS_TEST_BASE_URL;
	}

}
