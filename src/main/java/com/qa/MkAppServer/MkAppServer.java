package com.qa.MkAppServer;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;

public class MkAppServer extends BaseServer {

	public MkAppServer(String URL) {
		super(URL);
		setBASE_URL(ConfigConstantsTest.MK_APP_SERVER_BASE_URL);

	}
	public MkAppServer(String URL,String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.MK_APP_SERVER_BASE_URL);
	}
}