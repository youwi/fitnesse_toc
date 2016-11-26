package com.qa;

import com.qa.TestHttpClient.HttpClient;
import com.qa.TestHttpClient.HttpRequestCallback;
import com.qa.constants.ConfigConstants;
import com.qa.utils.Data;
import com.qa.utils.JSONParse;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yus on 2016/11/26.
 */
public class BaseServer {




    public String BASE_URL="";
    Data data;
    public JSONParse jp;
    public String URL;
    String env = null;
    String responseBody = null;
    String type = null;
    static HttpClient testRequst ;


    public BaseServer(String URL) {
        if(testRequst==null)
            testRequst=new HttpClient();
        this.data = new Data();
        this.jp = new JSONParse();
        this.URL = URL;
    }
    public BaseServer(String URL,String env) {
        if(testRequst==null)
            testRequst=new HttpClient();
        this.data = new Data();
        this.jp = new JSONParse();
        this.URL = URL;
        this.env = env;
    }
    public BaseServer(String URL,String env,String type) {
        if(testRequst==null)
            testRequst=new HttpClient();
        this.data = new Data();
        this.jp = new JSONParse();
        this.URL = URL;
        this.env = env;
        this.type = type;
    }

    public void setJsonParam(String json)
            throws Exception {
        data.setJsonParam(json);
    }

    public void setParam(String name, String value, String type)
            throws Exception {
        data.setParameters(name, value, type);
    }

    //set header parameter
    public void setHeaderParam(String name, String value)
            throws Exception {
        data.setHeaderParameters(name, value);
    }

    public String getParam(String key) {
        return jp.getResult(key);
    }

    public JSONObject requestForJSON(String fullurl, final Data indata){
        String responseBodyString = null;
        try {
            responseBodyString = testRequst.httpRequest(fullurl,
                     new HttpRequestCallback() {
                         @Override
                         public String addParam() {
                             return indata.getAddParam();
                         }
                         @Override
                         public Iterator<Map.Entry<String, String>> AddHeaderParameters() {
                             return indata.getAddHeaderParam();
                         }
                         @Override
                         public String addJsonParam() {
                             return indata.getJsonParam();
                         }
                     },type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject objResponse = new JSONObject(responseBodyString);
        return objResponse;
    }
    public boolean testRun(String param) throws IOException {
        JSONObject objResponse;
        if (null == param.trim()) {
            System.out.println("null paramters!!");
            return false;
        } else {
            objResponse=requestForJSON(BASE_URL+URL,data);
        }
        jp.parseJson(objResponse);
        return jp.checkParam(param);
    }

    public boolean checkContainsString(String param) {
        return responseBody.contains(param);
    }

    public boolean checkEqualString(String param) {
        return responseBody.equals(param);
    }

    public boolean checkResponseTime(String param) {
        System.out.println("响应时间：  "+testRequst.getResponseTime()+"ms");
        return testRequst.getResponseTime()<Long.parseLong(param)?true:false;
    }
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static HttpClient getTestRequst() {
        return testRequst;
    }

    public static void setTestRequst(HttpClient testRequst) {
        BaseServer.testRequst = testRequst;
    }
}
