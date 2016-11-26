package com.qa.LegalAppServer;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstants;


public class LegalAppServer  extends BaseServer{

	public LegalAppServer(String URL) {
		super(URL);
		BASE_URL=ConfigConstants.LEGAL_BASE_URL;
	}
	
	public LegalAppServer(String URL,String env) {
		super(URL,env);
		BASE_URL=ConfigConstants.LEGAL_BASE_URL;
	}
}
