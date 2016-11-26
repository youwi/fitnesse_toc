package com.qa;

import com.qa.TestHttpClient.HttpClientUtil;
import com.qa.TestHttpClient.HttpRequestCallback;
import com.qa.constants.ConfigConstantsTest;
import com.qa.utils.Data;
import com.qa.utils.JSONParse;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yus on 2016/11/26.
 */
public class BaseServer {

    String BASE_URL="";
    Data data;
    public JSONParse jp;
    public String URL;
    String env = null;
    String responseBody = null;
    String type = null;
    static HttpClientUtil httpClientUtil;


    public BaseServer(String URL) {
        if(httpClientUtil ==null)
            httpClientUtil =new HttpClientUtil();
        this.data = new Data();
        this.jp = new JSONParse();
        this.URL = URL;
        AUTO_GET_BASE_URL();//根据配置文件自动获取IP/URL
    }
    public BaseServer(String URL,String env) {
        if(httpClientUtil ==null)
            httpClientUtil =new HttpClientUtil();
        this.data = new Data();
        this.jp = new JSONParse();
        this.URL = URL;
        this.env = env;
        AUTO_GET_BASE_URL();//根据配置文件自动获取IP/URL
    }
    public BaseServer(String URL,String env,String type) {
        if(httpClientUtil ==null)
            httpClientUtil =new HttpClientUtil();
        this.data = new Data();
        this.jp = new JSONParse();
        this.URL = URL;
        this.env = env;
        this.type = type;
        AUTO_GET_BASE_URL();//根据配置文件自动获取IP/URL
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
        if(type==null){
            type="POST";
        }
        String responseBodyString = null;
        try {
            responseBodyString = httpClientUtil.httpRequest(fullurl,
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
        System.out.println("响应时间：  "+ httpClientUtil.getResponseTime()+"ms");
        return httpClientUtil.getResponseTime()<Long.parseLong(param)?true:false;
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

    public static HttpClientUtil getHttpClientUtil() {
        return httpClientUtil;
    }

    public static void setHttpClientUtil(HttpClientUtil httpClientUtil) {
        BaseServer.httpClientUtil = httpClientUtil;
    }


    public String getBASE_URL() {
        return BASE_URL;
    }

    public void setBASE_URL(String BASE_URL) {
        //if(env.equals("test"))
        this.BASE_URL = BASE_URL;
    }
    /**
     *  author: aerchi@gmail.com
     */

    public static String UpStr(String str){
        if(str==null||str.equals("")) return "";
        return str.replaceFirst(str.substring(0, 1),str.substring(0, 1).toUpperCase()) ;
    }
    /**
     * 自动获取BASE_URL
     *  如 wkzfAppServer 自动获取为  ConfigConstantsTest.WKZF_BASE_URL
     *  测试环境为：        ConfigConstantsTest.WKZF_BASE_URL
     *  Sim 环境为：        ConfigConstantsSim.WKZF_BASE_URL
     *  (环境名首字母大写);
     */
    public void AUTO_GET_BASE_URL(){
        Class clazz=null;

        try {
        if(env==null ||env.equals("dev")|| env.equals("") )
            clazz=ConfigConstantsTest.class;
        else
            clazz=Class.forName("com.qa.constants."+"ConfigConstants"+UpStr(env));
            String currName=this.getClass().getSimpleName();
           // String al
            Field[]  allfield= clazz.getFields();//
            for(Field tmp : allfield){
                String oriName=tmp.getName().replace("_","");
                String oriName2=(tmp.getName()+"AppServer").replace("_","");
                String toName=currName+"BASEURL";
               if(  oriName.toLowerCase().equals(toName.toLowerCase())
                       || oriName2.toLowerCase().equals(toName.toLowerCase())
                      )
               {
                   String base_url= (String) tmp.get(null);//静态变量,直接获取
                   this.BASE_URL=base_url;
               }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
