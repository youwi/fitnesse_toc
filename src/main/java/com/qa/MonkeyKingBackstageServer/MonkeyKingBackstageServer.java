package com.qa.MonkeyKingBackstageServer;

import com.qa.BaseServer;
import com.qa.TestHttpClient.HttpClient;
import com.qa.TestHttpClient.HttpRequestCallback;
import com.qa.constants.ConfigConstants;
import com.qa.utils.Data;
import com.qa.utils.JSONParse;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class MonkeyKingBackstageServer extends BaseServer {


	public MonkeyKingBackstageServer(String URL) {
		super(URL);
		BASE_URL=ConfigConstants.Monkey_King_Backstage_Server_BASE_URL;
	}
	public MonkeyKingBackstageServer(String URL,String env) {
		super(URL,env);
		BASE_URL=ConfigConstants.Monkey_King_Backstage_Server_BASE_URL;
	}
	public MonkeyKingBackstageServer(String URL,String env,String type) {
		super(URL,env,type);
		BASE_URL=ConfigConstants.Monkey_King_Backstage_Server_BASE_URL;
	}

}