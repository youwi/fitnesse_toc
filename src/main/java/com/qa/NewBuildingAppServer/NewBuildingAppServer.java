package com.qa.NewBuildingAppServer;

import com.qa.BaseServer;

import com.qa.constants.ConfigConstantsTest;


public class NewBuildingAppServer extends BaseServer{

	public NewBuildingAppServer(String URL) {
		super(URL);
		setBASE_URL(	ConfigConstantsTest.NEWBUILDING_APP_SERVER_BASE_URL);
	}
	public NewBuildingAppServer(String URL,String env) {
		super(URL,env);
		setBASE_URL(	ConfigConstantsTest.NEWBUILDING_APP_SERVER_BASE_URL);
	}

}
