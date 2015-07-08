package main.java.WkzfAppServer;

import java.io.IOException;

import main.java.TestHttpClient.HttpClient;
import main.java.TestHttpClient.HttpRequestCallback;
import main.java.constants.ConfigConstants;
import main.java.utils.Data;
import main.java.utils.JSONParse;

import org.json.JSONObject;

public class WkzfAppServer {
	Data data;
	JSONParse jp;
	String URL;
	String type;
	public WkzfAppServer(String URL,String type) {
		this.data = new Data();
		this.jp = new JSONParse();
		this.URL = URL;
		this.type = type;
	}

	public void setParam(String name, String value, String type) throws Exception {
		data.setParameters(name, value, type);
	}

	public String getParam(String key) {
		return jp.getResult(key);
	}

	public boolean testRun(String param) throws IOException {
		if (null == param.trim()) {
			System.out.println("null paramters!!");
			return false;
		} else {
			String baseURL = null;
			switch (type)
			{
			case "test":
				baseURL = ConfigConstants.FYB_BASE_URL_TEST + URL;
				break;
			case "beta":
				baseURL = ConfigConstants.FYB_BASE_URL_BETA + URL;
				break;
			case "prod":
				baseURL = ConfigConstants.FYB_BASE_URL_PROD + URL;
				break;
			}
			HttpClient testRequst = new HttpClient();
			String responseBody = testRequst.httpPostRequest(
					baseURL,
					new HttpRequestCallback() {
						@Override
						public String addParam() {
							// TODO Auto-generated method stub
							JSONObject obj = new JSONObject();
							return data.getAddParam(obj);
						}
					});
			JSONObject objResponse = new JSONObject(responseBody);
			jp.parseJson(objResponse);
			return jp.checkParam(param);
		}
	}
}

