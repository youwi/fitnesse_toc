package com.qa.WkWapServer;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstants;


public class WkWapServer extends BaseServer {

	public WkWapServer(String URL) {
		super(URL);
		BASE_URL=ConfigConstants.WKWAP_SERVER_SIM_BASE_URL;

	}
	public WkWapServer(String URL,String env) {
		super(URL,env);
		BASE_URL=ConfigConstants.WKWAP_SERVER_SIM_BASE_URL;
	}
	public WkWapServer(String URL,String env,String type) {
		super(URL,env,type);
		BASE_URL=ConfigConstants.WKWAP_SERVER_SIM_BASE_URL;
	}

}
