package com.qa;

import com.qa.TestHttpClient.HttpClientUtil;
import com.qa.TestHttpClient.HttpRequestCallback;
import com.qa.constants.ConfigConstantsTest;
import com.qa.utils.ParamData;
import com.qa.utils.JSONParse;
import org.apache.http.Header;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yus on 2016/11/26.
 */
public class ConnectServer {

    String BASE_URL = "";
    ParamData paramData;
    JSONParse jp;
    String URL;
    String env = null;
    String responseBody = null;
    String type = null;
    HttpClientUtil httpClientUtil;


    public ConnectServer(String URL) {
        if (httpClientUtil == null)
            httpClientUtil = new HttpClientUtil();
        this.paramData = new ParamData();
        this.paramData.getHeaderParameters().putAll(SetGlobalHeader.headersMap);
        this.jp = new JSONParse();
        this.URL = delHTMLTag(URL);
        AUTO_GET_BASE_URL();//根据配置文件自动获取IP/URL
    }

    public ConnectServer(String URL, String env) {
        if (httpClientUtil == null)
            httpClientUtil = new HttpClientUtil();
        this.paramData = new ParamData();
        this.paramData.getHeaderParameters().putAll(SetGlobalHeader.headersMap);

        this.jp = new JSONParse();
        this.URL = delHTMLTag(URL);
        this.env = env;
        AUTO_GET_BASE_URL();//根据配置文件自动获取IP/URL
    }

    public ConnectServer(String URL, String env, String type) {
        if (httpClientUtil == null)
            httpClientUtil = new HttpClientUtil();
        this.paramData = new ParamData();
        this.paramData.getHeaderParameters().putAll(SetGlobalHeader.headersMap);
        this.jp = new JSONParse();
        this.URL = delHTMLTag(URL);
        this.env = env;
        this.type = type;
        AUTO_GET_BASE_URL();//根据配置文件自动获取IP/URL
    }

    public boolean post() {
        jp.parseJson( requestForJSON(BASE_URL + URL, paramData));
        return true;
    }
    public boolean get(){
        this.type="GET";
        jp.parseJson(requestForJSON(BASE_URL+URL,paramData));
        return true;
    }


    public boolean setJsonParam(String json) {
        paramData.setJsonParam(json);
        return true;
    }

    /**
     *注:默认删除 html 标记
     */
    public boolean setBody(String bodyString) {
        paramData.setJsonParam(delHTMLTag(bodyString));
        return true;
    }

    public void setContentType(String contentType) {
        paramData.setHeaderParameters("Content-Type", contentType);
    }

    public boolean setParam(String name, String value, String type) throws Exception {
        paramData.setParameters(name, value, type);
        return true;
    }
    public boolean setParam(String name, String value) throws Exception {
        paramData.setParameters(name, value);
        return true;
    }

    //set header parameter
    public boolean setHeader(String name, String value) throws Exception {
        paramData.setHeaderParameters(name, value);
        return true;
    }

    public String getParam(String key) {
        return jp.getResult(key);
    }

    public String requestForString(String fullurl, final ParamData indata) {
        if (type == null) {
            type = "POST";
        }
        String responseBodyString = "{}";
        try {
            responseBodyString = httpClientUtil.httpRequest(fullurl,
                    new HttpRequestCallback() {
                        @Override
                        public String getJsonParam() {
                            if (indata.getJsonParam() != null)
                                return indata.getJsonParam();
                            else
                                return indata.getParamAsJsonString();
                        }

                        @Override
                        public String getParam() {
                            return indata.getParamAsFormString();
                        }

                        @Override
                        public Iterator<Map.Entry<String, String>> getHeaderParameters() {
                            return indata.getAddHeaderParam();
                        }

                        @Override
                        public void saveResponseHeaders(Header[] responseHeaders) {

                        }

                        @Override
                        public boolean getIsRedirect() {
                            return indata.isRedirect;
                        }
                    }, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBodyString;
    }

    /**
     * 获取返回结果中的json结果
     * @return
     */
    public String getJsonValue(String key){
       return jp.getResult(key);
    }
    public String jsonValue(String key){
        return getJsonValue(key);
    }


    public JSONObject requestForJSON(String fullurl, final ParamData indata) {
        if (type == null) {
            type = "POST";
        }
        String oriContentType = indata.getHeaderParameters().get("Content-Type");
        if (oriContentType == null && type != null) {
            if (Objects.equals(type.toLowerCase(), "get")) {
                if(indata.getParameters().size()>0){
                    indata.setHeaderParameters("Content-Type", "application/x-www-form-urlencoded");
                }
            } else if (Objects.equals(type.toLowerCase(), "post")) {
                indata.setHeaderParameters("Content-Type", "application/json;charset=UTF-8");
            }
        }

        JSONObject objResponse = new JSONObject(this.requestForString(fullurl, indata));
        return objResponse;
    }

    public String requestForXML(String fullurl, final ParamData indata) {
        if (type == null) {
            type = "POST";
        }
        return requestForString(fullurl, indata);
    }

    public String requestForExecution(String fullurl, final ParamData indata) {
        type = "post";
        return requestForString(fullurl, indata);
    }

    public String requestForwksso(String fullurl, final ParamData indata) {
        type = "get";
        return requestForString(fullurl, indata);
    }


    public boolean testRun(String param) {
        JSONObject objResponse;
        if (null == param.trim()) {
            System.out.println("null paramters!!");
            return false;
        } else {
            objResponse = requestForJSON(BASE_URL + URL, paramData);
        }
        jp.parseJson(objResponse);
        return jp.checkParam(param);
    }
    public boolean jsonStructure(String str){
        return jp.checkParam(str);
    }

    public boolean checkContainsString(String param) {
        return responseBody.contains(param);
    }

    public boolean checkEqualString(String param) {
        return responseBody.equals(param);
    }

    public boolean checkResponseTime(String param) {
        System.out.println("响应时间：  " + httpClientUtil.getResponseTime() + "ms");
        return httpClientUtil.getResponseTime() < Long.parseLong(param) ? true : false;
    }

    public Header[] getResponseHeader(String responseHeaderKey) {
        return httpClientUtil.getResponseHeader(responseHeaderKey);
    }


    public void setType(String type) {
        this.type = type;
    }

    public HttpClientUtil getHttpClientUtil() {
        return new HttpClientUtil();
    }


    public String getBASE_URL() {
        return BASE_URL;
    }

    public void setBASE_URL(String BASE_URL) {
        //if(env.equals("test"))
        this.BASE_URL = BASE_URL;
    }

    /**
     * author: aerchi@gmail.com
     */

    public static String UpStr(String str) {
        if (str == null || str.equals("")) return "";
        return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
    }

    /**
     * 根据环境清单自动得到 IP 地址
     */
    public void autoGetEnvirementIPUrl() {

    }

    /**
     * 自动获取BASE_URL
     * 如 wkzfAppServer 自动获取为
     * 测试环境为：
     * Sim 环境为：
     * (环境名首字母大写);
     */
    public void AUTO_GET_BASE_URL() {
        Class clazz = null;

        try {
            if (env == null || env.equals("dev") || env.equals(""))
                clazz = ConfigConstantsTest.class;
            else
                clazz = Class.forName("com.qa.constants." + "ConfigConstants" + UpStr(env));
            String currName = this.getClass().getSimpleName();
            // String al
            Field[] allfield = clazz.getFields();//
            for (Field tmp : allfield) {
                String oriName = tmp.getName().replace("_", "");
                String oriName2 = (tmp.getName() + "AppServer").replace("_", "");
                String toName = currName + "BASEURL";
                if (oriName.toLowerCase().equals(toName.toLowerCase())
                        || oriName2.toLowerCase().equals(toName.toLowerCase())
                        ) {
                    String base_url = (String) tmp.get(null);//静态变量,直接获取
                    this.BASE_URL = base_url;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static String delHTMLTag(String htmlStr) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签

        return htmlStr.trim(); //返回文本字符串
    }



}
