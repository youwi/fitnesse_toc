package FybAppSever;

import java.io.IOException;

import org.json.JSONObject;

import utils.Data;
import utils.JSONParse;
import TestHttpClient.HttpClient;
import TestHttpClient.HttpRequestCallback;
import constants.ConfigConstants;

public class FybUserLoginTest {
    Data data;
    JSONParse jp;

    public FybUserLoginTest() {
        data = new Data();
        jp = new JSONParse();
    }

    public void setParam(String name, String value, String type) throws Exception {
        data.setParameters(name, value, type);
    }

    public void setParam(String name, String value) {
        data.setParameters(name, value);
    }

    public String getParam(String key) {
        return jp.getResult(key);
    }

    public boolean testRun(String name) throws IOException {
        if (null == name.trim()) {
            System.out.println("null paramters!!");
            return true;
        } else {
            HttpClient testRequst = new HttpClient();
            String responseBody = testRequst.httpPostRequest(
                    ConfigConstants.FYB_USER_LOGIN_URL,
                    new HttpRequestCallback() {
                        @Override
                        public String addParam() {
                            JSONObject obj = new JSONObject();
                            return data.getAddParam(obj);
                        }
                    });
            JSONObject objResponse = new JSONObject(responseBody);
            jp.parseJson(objResponse);
            return jp.checkParam(name);
        }
    }

    public void testRun() throws IOException {
        HttpClient testRequst = new HttpClient();
        HttpRequestCallback hcb = new HttpRequestCallback() {
            @Override
            public String addParam() {
                JSONObject obj = new JSONObject();
                return data.getAddParam(obj);
            }
        };
        Object responseBody = testRequst.httpPostRequest(
                ConfigConstants.FYB_USER_LOGIN_URL, hcb);
        JSONObject objResponse = new JSONObject(responseBody.toString());
        jp.parseJson(objResponse);
    }

    public void testRun2() throws IOException {
        HttpClient testRequst = new HttpClient();
        HttpRequestCallback hcb = new HttpRequestCallback() {
            @Override
            public String addParam() {
                JSONObject obj = new JSONObject();
                return data.getAddParam(obj);
            }
        };
        String responseBody = testRequst.httpPostRequest(
                ConfigConstants.FYB_USER_LOGIN_URL, hcb);
        JSONObject objResponse = new JSONObject(responseBody);
        jp.parseJson(objResponse);
    }
}
