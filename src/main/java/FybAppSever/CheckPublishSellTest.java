package main.java.FybAppSever;

import java.io.IOException;

import org.json.JSONObject;

import utils.Data;
import utils.JSONParse;
import TestHttpClient.HttpClient;
import TestHttpClient.HttpRequestCallback;
import constants.ConfigConstants;

public class CheckPublishSellTest {
	Data data;
	JSONParse jp;
	String URL;

	public CheckPublishSellTest(String URL) {
		this.data = new Data();
		this.jp = new JSONParse();
		this.URL = URL;
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
			HttpClient testRequst = new HttpClient();
			String responseBody = testRequst.httpPostRequest(
					ConfigConstants.FYB_BASE_URL + URL,
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
