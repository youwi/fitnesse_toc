package com.qa.JrspAppServer;

import com.qa.BaseServer;

import com.qa.constants.ConfigConstantsTest;


public class JrspAppServer extends BaseServer {

	public JrspAppServer(String URL) {
		super(URL);
		setBASE_URL(ConfigConstantsTest.JRSP_BASE_URL );
	}
	
	public JrspAppServer(String URL,String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.JRSP_BASE_URL);
	}

}
