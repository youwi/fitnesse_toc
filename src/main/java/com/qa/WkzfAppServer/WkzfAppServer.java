package com.qa.WkzfAppServer;



import com.qa.BaseServer;


import com.qa.constants.ConfigConstants;


public class WkzfAppServer  extends BaseServer{

	public WkzfAppServer(String URL) {
	   	super(URL);
		BASE_URL=ConfigConstants.USER_APP_SERVER_TEST_BASE_URL;
	}
	public WkzfAppServer(String URL,String env) {
		super(URL,env);
		BASE_URL=ConfigConstants.USER_APP_SERVER_TEST_BASE_URL;
	}
}
