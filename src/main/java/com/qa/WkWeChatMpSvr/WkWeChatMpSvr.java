package com.qa.WkWeChatMpSvr;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;

public class WkWeChatMpSvr extends BaseServer {


	public WkWeChatMpSvr(String URL) {
	 	super(URL);
		setBASE_URL(ConfigConstantsTest.WKWECHATMPSVR_TEST_BASE_URL);
	}
	public WkWeChatMpSvr(String URL, String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.WKWECHATMPSVR_TEST_BASE_URL);
	}
	public WkWeChatMpSvr(String URL, String env, String type) {
		super(URL,env,type);
		setBASE_URL(ConfigConstantsTest.WKWECHATMPSVR_TEST_BASE_URL);
	}

}
