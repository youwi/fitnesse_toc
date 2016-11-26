package com.qa.LegalAppServer;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;


public class LegalAppServer  extends BaseServer{

	public LegalAppServer(String URL) {
		super(URL);
		setBASE_URL(ConfigConstantsTest.LEGAL_BASE_URL);
	}
	
	public LegalAppServer(String URL,String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.LEGAL_BASE_URL);
	}
}
