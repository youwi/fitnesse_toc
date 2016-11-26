package com.qa.JrspAppServer;

import com.qa.BaseServer;

import com.qa.constants.ConfigConstants;


public class JrspAppServer extends BaseServer {

	public JrspAppServer(String URL) {
		super(URL);
		BASE_URL=		ConfigConstants.JRSP_BASE_URL ;
	}
	
	public JrspAppServer(String URL,String env) {
		super(URL,env);
		BASE_URL=		ConfigConstants.JRSP_BASE_URL ;
	}

}
