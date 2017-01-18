package com.qa.Report2Sys;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;

public class Report2Sys extends BaseServer {


	public Report2Sys(String URL) {
	 	super(URL);
		setBASE_URL(ConfigConstantsTest.YFRS_SERVER_TEST_BASE_URL);
	}
	public Report2Sys(String URL, String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.YFRS_SERVER_TEST_BASE_URL);
	}
	public Report2Sys(String URL, String env, String type) {
		super(URL,env,type);
		setBASE_URL(ConfigConstantsTest.YFRS_SERVER_TEST_BASE_URL);
	}

}
