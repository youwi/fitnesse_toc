package FybAppSever;

import java.io.IOException;

import org.json.JSONObject;

import utils.Data;
import utils.JSONParse;
//import utils.UploaderYouku;
import TestHttpClient.HttpClient;
import TestHttpClient.HttpRequestCallback;
import constants.ConfigConstants;

public class UploadYoukuVideoFileTest {
    private Data data;
    private JSONParse jp;
    private String URL;
    private String newUrl;

    public UploadYoukuVideoFileTest() {
        data = new Data();
        jp = new JSONParse();
    }

    public UploadYoukuVideoFileTest(String URL) {
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

//    public void testRun(String dir) throws IOException {
//        UploaderYouku u = new UploaderYouku();
//        u.uploader(dir);
//    }
}
