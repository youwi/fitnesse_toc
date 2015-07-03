package main.java.FybAppSever;

import java.io.IOException;

import org.json.JSONObject;

import utils.Data;
import utils.JSONParse;
import TestHttpClient.HttpClient;
import TestHttpClient.HttpRequestCallback;
import constants.ConfigConstants;

public class UploadSingleFileTest {
	private Data data;
	private JSONParse jp;
	private String URL;
	private String newUrl;

	public UploadSingleFileTest() {
		data = new Data();
		jp = new JSONParse();
	}

	public UploadSingleFileTest(String URL) {
		this.data = new Data();
		this.jp = new JSONParse();
		this.URL = URL;
	}

	public void setParam(String name, String value, String type)
			throws Exception {
		data.setParameters(name, value, type);
	}

	public void setParam(String name, String value) {
		data.setParameters(name, value);
	}

	public String getParam(String key) {
		return jp.getResult(key);
	}

	public void testRun2() throws IOException {
		HttpClient testRequst = new HttpClient();
		newUrl = ConfigConstants.FYB_BASE_URL + URL;
		HttpRequestCallback hcb = new HttpRequestCallback() {
			@Override
			public String addParam() {
				JSONObject obj = new JSONObject();
				return data.getAddParam(obj);
			}
		};
		String responseBody = testRequst.httpPostRequest(newUrl, hcb);
		JSONObject objResponse = new JSONObject(responseBody);
		jp.parseJson(objResponse);
	}
}
