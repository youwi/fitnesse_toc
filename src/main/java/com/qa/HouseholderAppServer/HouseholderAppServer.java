package com.qa.HouseholderAppServer;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstants;

public class HouseholderAppServer extends BaseServer {

	public HouseholderAppServer(String URL) {
		super(URL);
		BASE_URL= ConfigConstants.HOUSEHOLDER_APP_SERVER_TEST_BASE_URL;

	}
	public HouseholderAppServer(String URL,String env) {
		super(URL,env);
		BASE_URL= ConfigConstants.HOUSEHOLDER_APP_SERVER_TEST_BASE_URL;
	}

}
