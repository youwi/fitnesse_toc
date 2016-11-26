package com.qa.WkppAppServer;

import com.qa.BaseServer;

import com.qa.constants.ConfigConstantsTest;

public class WkppAppServer extends BaseServer {

	public WkppAppServer(String URL) {
		super(URL);
		setBASE_URL(ConfigConstantsTest.WKPP_APP_SERVER_BASE_URL);
	}
	public WkppAppServer(String URL,String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.WKPP_APP_SERVER_BASE_URL);
	}

}
