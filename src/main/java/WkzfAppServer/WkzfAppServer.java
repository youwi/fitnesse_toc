package main.java.WkzfAppServer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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

	public WkzfAppServer(String URL) {
		this.data = new Data();
		this.jp = new JSONParse();
		this.URL = URL;
	}

	public void setParam(String name, String value, String type)
			throws Exception {
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
					ConfigConstants.WKZF_BASE_URL + URL,
					new HttpRequestCallback() {
						@Override
						public String addParam() {
							// TODO Auto-generated method stub
							JSONObject obj = new JSONObject();
							return data.getAddParam(obj);
						}

						@Override
						public Iterator<Map.Entry<String, String>> AddHeaderParameters() {
							// TODO Auto-generated method stub
							return data.getAddHeaderParam();
						}
					});
			JSONObject objResponse = new JSONObject(responseBody);
			jp.parseJson(objResponse);
			return jp.checkParam(param);
		}
	}
}
