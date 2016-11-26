package com.qa.YfykAppServer;


import com.qa.BaseServer;
import com.qa.constants.ConfigConstants;

public class YfykAppServer extends BaseServer{

	public YfykAppServer(String URL) {
		super(URL);
		BASE_URL=  ConfigConstants.YFYK_BASE_URL;
	}
	
	public YfykAppServer(String URL,String env) {
		super(URL,env);
		BASE_URL=  ConfigConstants.YFYK_BASE_URL;
	}

}
