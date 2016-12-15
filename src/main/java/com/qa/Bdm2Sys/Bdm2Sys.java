package com.qa.Bdm2Sys;



import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;


public class Bdm2Sys extends BaseServer{

	public Bdm2Sys(String URL) {
		super(URL);
	   setBASE_URL(ConfigConstantsTest.BDM2_SYS_SERVER_BASE_URL);
	}
	public Bdm2Sys(String URL, String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.BDM2_SYS_SERVER_BASE_URL);
	}
	public Bdm2Sys(String URL, String env, String type) {
		super(URL,env,type);
		setBASE_URL(ConfigConstantsTest.BDM2_SYS_SERVER_BASE_URL);
	}


}
