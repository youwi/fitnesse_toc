package com.qa.MonkeyKingWeChatServer;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstants;

public class MonkeyKingWeChatServer extends BaseServer {
	public MonkeyKingWeChatServer(String URL) {
		super(URL);
		BASE_URL=ConfigConstants.Monkey_King_WeChat_BASE_URL;
	}
	public MonkeyKingWeChatServer(String URL,String env) {
		super(URL,env);
		BASE_URL=ConfigConstants.Monkey_King_WeChat_BASE_URL;
	}
	public MonkeyKingWeChatServer(String URL,String env,String type) {
		super(URL,env);
		BASE_URL=ConfigConstants.Monkey_King_WeChat_BASE_URL;
	}
}