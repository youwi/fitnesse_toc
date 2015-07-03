package main.java.FybAppSever;

import java.io.IOException;

import main.java.TestHttpClient.HttpClient;
import main.java.TestHttpClient.HttpRequestCallback;
import main.java.constants.ConfigConstants;
import main.java.utils.Data;
import main.java.utils.JSONParse;

import org.json.JSONObject;

public class AutomaticUpdatesTest {

	Data data;
	JSONParse jp;
	String URL;

	public AutomaticUpdatesTest(String URL) {
		this.data = new Data();
		this.jp = new JSONParse();
		this.URL = URL;
	}

	public void setParam(String name, String value) {
		data.setParameters(name, value);
	}

	public String getParam(String key) {
		return jp.getResult(key);
	}

	public void testRun() throws IOException {
		HttpClient testRequst = new HttpClient();
		String responseBody = testRequst.httpPostRequest(
				ConfigConstants.FYB_BASE_URL+URL, new HttpRequestCallback() {
					@Override
					public String addParam() {
						// TODO Auto-generated method stub
						JSONObject obj = new JSONObject();
						return data.getAddParam(obj);
					}
				});
		JSONObject objResponse = new JSONObject(responseBody);
		jp.parseJson(objResponse);
	}
}
