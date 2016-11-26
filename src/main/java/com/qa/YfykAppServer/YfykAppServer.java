package com.qa.YfykAppServer;


import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;

public class YfykAppServer extends BaseServer{

	public YfykAppServer(String URL) {
		super(URL);
		setBASE_URL(ConfigConstantsTest.YFYK_BASE_URL);
	}
	
	public YfykAppServer(String URL,String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.YFYK_BASE_URL);
	}

}
