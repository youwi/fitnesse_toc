package com.qa.RentAppServer;


import com.qa.BaseServer;
import com.qa.constants.ConfigConstants;


public class RentAppServer extends BaseServer{


	public RentAppServer(String URL) {
	 	super(URL);
		BASE_URL=ConfigConstants.RENT_APP_SERVER_TEST_BASE_URL;
	}
	public RentAppServer(String URL,String env) {
		super(URL,env);
		BASE_URL=ConfigConstants.RENT_APP_SERVER_TEST_BASE_URL;
	}

}
