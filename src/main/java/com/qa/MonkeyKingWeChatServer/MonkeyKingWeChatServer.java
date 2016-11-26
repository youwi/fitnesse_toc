package com.qa.MonkeyKingWeChatServer;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;

public class MonkeyKingWeChatServer extends BaseServer {
	public MonkeyKingWeChatServer(String URL) {
		super(URL);
		setBASE_URL(ConfigConstantsTest.Monkey_King_WeChat_BASE_URL);
	}
	public MonkeyKingWeChatServer(String URL,String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.Monkey_King_WeChat_BASE_URL);
	}
	public MonkeyKingWeChatServer(String URL,String env,String type) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.Monkey_King_WeChat_BASE_URL);
	}
}