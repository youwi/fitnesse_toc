package com.qa.WkWebServer;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;

public class WkWebServer extends BaseServer {


	public WkWebServer(String URL) {
	 	super(URL);
		setBASE_URL(ConfigConstantsTest.WKWEB_SERVER_TEST_BASE_URL);
	}
	public WkWebServer(String URL,String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.WKWEB_SERVER_TEST_BASE_URL);
	}
	public WkWebServer(String URL,String env,String type) {
		super(URL,env,type);
		setBASE_URL(ConfigConstantsTest.WKWEB_SERVER_TEST_BASE_URL);
	}

}
