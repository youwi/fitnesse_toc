package com.qa.WkWapServer;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;


public class WkWapServer extends BaseServer {

	public WkWapServer(String URL) {
		super(URL);
		setBASE_URL(ConfigConstantsTest.WKWAP_SERVER_BASE_URL);
	}
	public WkWapServer(String URL,String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.WKWAP_SERVER_BASE_URL);
	}
	public WkWapServer(String URL,String env,String type) {
		super(URL,env,type);
		setBASE_URL(ConfigConstantsTest.WKWAP_SERVER_BASE_URL);
	}

}
