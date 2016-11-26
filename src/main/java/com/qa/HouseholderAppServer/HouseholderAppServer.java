package com.qa.HouseholderAppServer;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;

public class HouseholderAppServer extends BaseServer {

	public HouseholderAppServer(String URL) {
		super(URL);
		setBASE_URL(ConfigConstantsTest.HOUSEHOLDER_APP_SERVER_TEST_BASE_URL);

	}
	public HouseholderAppServer(String URL,String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.HOUSEHOLDER_APP_SERVER_TEST_BASE_URL);
	}

}
