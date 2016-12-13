package com.qa.NBAAppServer;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;

public class NBAAppServer extends BaseServer {

	public NBAAppServer(String URL) {
		super(URL);
		setBASE_URL(ConfigConstantsTest.NBA_APP_SERVER_BASE_URL);

	}
	public NBAAppServer(String URL, String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.NBA_APP_SERVER_BASE_URL);
	}
}