package com.qa.MonkeyKingBackstageServer;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;

public class MonkeyKingBackstageServer extends BaseServer {


	public MonkeyKingBackstageServer(String URL) {
		super(URL);
		setBASE_URL(ConfigConstantsTest.Monkey_King_Backstage_Server_BASE_URL);
	}
	public MonkeyKingBackstageServer(String URL,String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.Monkey_King_Backstage_Server_BASE_URL);
	}
	public MonkeyKingBackstageServer(String URL,String env,String type) {
		super(URL,env,type);
		setBASE_URL(ConfigConstantsTest.Monkey_King_Backstage_Server_BASE_URL);
	}

}