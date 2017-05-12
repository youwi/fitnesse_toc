package com.qa.WkWeChatMPServer;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;

public class WkWeChatMPServer extends BaseServer {


	public WkWeChatMPServer(String URL) {
	 	super(URL);
		setBASE_URL(ConfigConstantsTest.WKWeChatMP_SVR_SERVER_TEST_BASE_URL);
	}
	public WkWeChatMPServer(String URL, String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.WKWeChatMP_SVR_SERVER_TEST_BASE_URL);
	}
	public WkWeChatMPServer(String URL, String env, String type) {
		super(URL,env,type);
		setBASE_URL(ConfigConstantsTest.WKWeChatMP_SVR_SERVER_TEST_BASE_URL);
	}

}
