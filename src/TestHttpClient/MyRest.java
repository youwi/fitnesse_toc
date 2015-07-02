package TestHttpClient;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import TestHttpClient.HttpClient;
import TestHttpClient.HttpRequestCallback;
import constants.ConfigConstants;
import utils.Data;
import utils.InfoUtils;
import utils.JSONParse;

public class MyRest {
    Data data;
    JSONParse jp;

    private HashMap<String, Object> map = new HashMap<String, Object>();

    public MyRest() {
        this.data = new Data();
        this.jp = new JSONParse();

    }

    public void setParam(String name, String value) throws Exception {
        data.setParameters(name, value);
    }

    public String getParam(String key) {
        return jp.getResult(key);
    }

    public HashMap<String, Object> getParameters() {
        return this.map;
    }

    public boolean testRun(String param) throws IOException {
      ;
        if (null == param.trim()) {
            System.out.println("null paramters!!");
            return false;
        } else {

            Map<String, Object> str = data.getAddParamMap();
            JSONObject json = InfoUtils.sendRestInter(
                    ConfigConstants.USERAPP_BASE_URL+"/ihouse/user/my.rest", str);
            jp.parseJson(json);
            return jp.checkParam(param);
        }
    }

}
