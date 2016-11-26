package com.qa.WkzfAppServer;



import com.qa.BaseServer;


import com.qa.constants.ConfigConstantsTest;


public class WkzfAppServer  extends BaseServer{

	public WkzfAppServer(String URL) {
	   	super(URL);
		setBASE_URL(ConfigConstantsTest.USER_APP_SERVER_BASE_URL);
	}
	public WkzfAppServer(String URL,String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.USER_APP_SERVER_BASE_URL);
	}
}
