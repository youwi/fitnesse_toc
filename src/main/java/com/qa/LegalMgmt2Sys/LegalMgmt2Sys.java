package com.qa.LegalMgmt2Sys;



import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;


public class LegalMgmt2Sys extends BaseServer{

	public LegalMgmt2Sys(String URL) {
		super(URL);
	   setBASE_URL(ConfigConstantsTest.LEGALMGMT2SYS_SERVER_TEST_BASE_URL);
	}
	public LegalMgmt2Sys(String URL, String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.LEGALMGMT2SYS_SERVER_TEST_BASE_URL);
	}
	public LegalMgmt2Sys(String URL, String env, String type) {
		super(URL,env,type);
		setBASE_URL(ConfigConstantsTest.LEGALMGMT2SYS_SERVER_TEST_BASE_URL);
	}


}
