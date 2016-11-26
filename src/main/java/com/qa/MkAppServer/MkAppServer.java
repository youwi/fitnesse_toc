package com.qa.MkAppServer;

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

public class MkAppServer extends BaseServer {

	public MkAppServer(String URL) {
		super(URL);
		BASE_URL=ConfigConstants.MK_APP_SERVER_SIM_BASE_URL;

	}
	public MkAppServer(String URL,String env) {
		super(URL,env);
		BASE_URL=ConfigConstants.MK_APP_SERVER_SIM_BASE_URL;
	}
}