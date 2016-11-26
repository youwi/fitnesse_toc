package com.qa.RentAppServer;


import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;


public class RentAppServer extends BaseServer{


	public RentAppServer(String URL) {
	 	super(URL);
		setBASE_URL(ConfigConstantsTest.RENT_APP_SERVER_BASE_URL);
	}
	public RentAppServer(String URL,String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.RENT_APP_SERVER_BASE_URL);
	}

}
