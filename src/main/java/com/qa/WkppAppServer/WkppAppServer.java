package com.qa.WkppAppServer;

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

public class WkppAppServer extends BaseServer {

	public WkppAppServer(String URL) {
		super(URL);
		BASE_URL=ConfigConstants.WKPP_APP_SERVER_TEST_BASE_URL;
	}
	public WkppAppServer(String URL,String env) {
		super(URL,env);
		BASE_URL=ConfigConstants.WKPP_APP_SERVER_TEST_BASE_URL;
	}

}
