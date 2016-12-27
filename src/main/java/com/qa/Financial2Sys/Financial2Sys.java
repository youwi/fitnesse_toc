package com.qa.Financial2Sys;

import java.io.IOException;

import com.qa.BaseServer;
import com.qa.SSOLogin;
import com.qa.constants.ConfigConstantsTest;
import com.qa.utils.Data;
import org.json.JSONObject;

public class Financial2Sys  extends BaseServer{

	public Financial2Sys(String URL) {
		 super(URL);
		setBASE_URL(ConfigConstantsTest.FINANCIAL2_SYS_BASE_URL);
	}

	public Financial2Sys(String URL,String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.FINANCIAL2_SYS_BASE_URL);
	}
	
	public Financial2Sys(String URL,String env,String type) {
		super(URL,env,type);
		setBASE_URL(ConfigConstantsTest.FINANCIAL2_SYS_BASE_URL);
	}
}
