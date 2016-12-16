package com.qa.NbaAppServer;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;

public class NbaAppServer extends BaseServer {

	public NbaAppServer(String URL) {
		super(URL);
		setBASE_URL(ConfigConstantsTest.NBA_APP_SERVER_BASE_URL);

	}
	public NbaAppServer(String URL, String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.NBA_APP_SERVER_BASE_URL);
	}
}