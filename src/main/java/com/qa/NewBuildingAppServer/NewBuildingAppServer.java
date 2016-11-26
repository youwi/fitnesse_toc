package com.qa.NewBuildingAppServer;

import com.qa.BaseServer;

import com.qa.constants.ConfigConstants;


public class NewBuildingAppServer extends BaseServer{

	public NewBuildingAppServer(String URL) {
		super(URL);
		BASE_URL=	ConfigConstants.NEWBUILDING_APP_SERVER_TEST_BASE_URL;
	}
	public NewBuildingAppServer(String URL,String env) {
		super(URL,env);
		BASE_URL=	ConfigConstants.NEWBUILDING_APP_SERVER_TEST_BASE_URL;
	}

}
