package com.qa;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.qa.utils.*;
import com.qa.constants.ConfigConstantsTest;
import com.qa.exceptions.HttpStatusException;
/*
import groovy.json.JsonSlurper;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.Eval;
*/
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.rmi.server.ExportException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qa.Set.getEnv;

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
    static  HttpClientUtil httpClientUtil= new HttpClientUtil();
    boolean responseBodyIsJson;
    //统计 URL 访问次数
     public static Map<String,Integer> _url_count_=new HashMap();

    /**
     * 添加统计数据,增加一个值
     */
    static public void  addOneUrlCount( String url){
        Integer i=_url_count_.get(url);
        if(i==null) i=0;
        i++;
        _url_count_.put(url,i);
    }
    static{
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("请求所有的 URL 个数为:"+ConnectServer._url_count_.size());
            }
        });
    }




    public ConnectServer(String URL) {
        if (httpClientUtil == null)
            httpClientUtil = new HttpClientUtil();
        this.paramData = new ParamData();
        this.paramData.getHeaderParameters().putAll(SetGlobalHeader.headersMap);
        this.jp = new JSONParse();
        this.URL = delHTMLTag(URL);
        this.autoSetBaseUrl();
        addOneUrlCount(URL);
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
        this.autoSetBaseUrl();
        addOneUrlCount(URL);

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
        this.autoSetBaseUrl();
        addOneUrlCount(URL);
        AUTO_GET_BASE_URL();//根据配置文件自动获取IP/URL
    }

    public boolean post() {
        jp.parseJson( requestForJSON(BASE_URL + URL, paramData));
        return true;
       // return "message:<<OK>>";
    }
    public boolean delete(){
        this.type="DELETE";
        jp.parseJson(requestForJSON(BASE_URL+URL,paramData));
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
        bodyString=delHtmlPre(bodyString);
        if(canGsonParse(bodyString)){
            paramData.setJsonParam(GsonJsonUtil.gson.toJson(gsonParse(bodyString)));
        }else{
            paramData.setJsonParam(bodyString);
        }
        return true;
    }

    /**
     * 判断是不是一个普通json,并可以用Gson 来解析
     * @return
     */
    boolean canGsonParse(String maybeJson){
        Object ob=gsonParse(maybeJson);
        if(ob==null)return false;
        return true;
    }
    Object gsonParse(String mayBeJson){
        try{
            Map map= GsonJsonUtil.gson.fromJson(mayBeJson,Map.class);
            return map;
        }catch (Exception e){
            try{
                List list= GsonJsonUtil.gson.fromJson(mayBeJson,List.class);
                return list;
            }catch (Exception e2){
                System.out.println("debug:> json parse ERROR (语法错误或可能多余的逗号这些)");
                return null;
            }
        }

    }



    /**
     * 支持书写 js 转换
     * @param bodyString 脚本
     * @param type 实现有 js,json,groovy
     * @return
     */
    public boolean setBody(String bodyString,String type){
        if("raw".equals(type)){
            paramData.setJsonParam(bodyString);
        }else {
            paramData.setJsonParam(ScriptUtil.buildScript(delHtmlPre(bodyString), type));
        }
        return true;
    }

    public void setContentType(String contentType) {
        paramData.setHeaderParameters("Content-Type", contentType);
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
            this.responseBody=responseBodyString;
        } catch (RuntimeException e) {
            if(e instanceof HttpStatusException){
                this.responseBody=httpClientUtil.getResponseBody();
                throw e;
            }
            return responseBody;

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return responseBodyString;
    }

    /**
     * 获取返回结果中的json结果
     * @return
     */
    public String jsonValue(String key){
        if(responseBodyIsJson)
            return jp.getResult(key);
        else throw new RuntimeException("message:<< response is not json "+responseBody+">>");
    }
    public boolean jsonLike(String json){
        return true;
    }
    public Boolean jsonContain(String json){
        json=delHtmlPre(json);
        ScriptUtil.preLoadCompileJs();
        ScriptUtil.runJavaScript("response=null");
        ScriptUtil.runJavaScript("response="+responseBody);
        //ScriptUtil.binding(responseBody,"response"); //绑定为直接的字符串
        //ScriptUtil.runJavaScript("response=JSON.parse(response)");
        return (Boolean) ScriptUtil.runJavaScript("CONTAIN(response,"+json+")");
    }
    public Object javaScript(String js){
        js=delHtmlPre(js);
        ScriptUtil.preLoadCompileJs();
        ScriptUtil.runJavaScript("response=null");
        ScriptUtil.runJavaScript("response="+responseBody);
        return ScriptUtil.runJavaScript(js);

    }
  /*  public Object groovyScript(String groovyString) {
        groovyString=delHtmlPre(groovyString);
        Binding sharedData = new Binding();
        Object object =new JsonSlurper().parseText(responseBody);
        sharedData.setProperty("response",object);
        GroovyShell shell = new GroovyShell()  ;
        shell.setProperty("response",object);
        Object result2 = shell.evaluate(groovyString)  ;

        return result2;
    }*/



    public JSONObject requestForJSON(String fullurl, final ParamData indata) {
        if (type == null) {
            type = "POST";
        }
        String oriContentType = indata.getHeaderParameters().get("Content-Type");
        if (oriContentType == null && type != null) {
            if (Objects.equals(type.toLowerCase(), "get")) {
                if(indata.getParameters().size()>0){
                    //indata.setHeaderParameters("Content-Type", "application/x-www-form-urlencoded");
                }
            } else if (Objects.equals(type.toLowerCase(), "post")) {
                indata.setHeaderParameters("Content-Type", "application/json;charset=UTF-8");
            }else if(Objects.equals(type.toLowerCase(), "delete")){
                indata.setHeaderParameters("Content-Type", "application/json;charset=UTF-8");
            }
        }
        String responseString=this.requestForString(fullurl, indata);
        if(ScriptUtil.isJson(responseString)){
            JSONObject objResponse = new JSONObject(responseString);
            this.responseBodyIsJson=true;
            return objResponse;
        };
        return new JSONObject("{}");
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
     * 根据服务名称自动从配置文件中获取BASE_URL
     * 如 自动获取为
     * 测试环境为：
     * Sim 环境为：
     * (环境名首字母大写);
     */
    public void AUTO_GET_BASE_URL() {
        Class clazz = null;
        this.env=getEnv();

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

            ExceptionUtil.printlnSo(e);
        } catch (IllegalAccessException e) {
            ExceptionUtil.printlnSo(e);
        }
    }

    /**
     * 根据环境信息自动设置 URL 前缀
     * 如  BAIDU/api.rest=> http://baidu.com/api.rest
     */
    public void autoSetBaseUrl(){
        String httpIpPort=subHttpIpPort(this.URL);
        if(StringUtils.isNotEmpty( httpIpPort)) {
            this.BASE_URL = Set.getValueSibling(httpIpPort, Set.getEnv());
            this.URL=this.URL.replace(httpIpPort,"");
        }
    }

    /**
     * 从 URL 中提取 IP:端口
     * @param url
     * @return
     */
    public static String subHttpIpPort(String url){
        if(url!=null){
           String[] tmp1= url.split("http://");
           String  tmpString=tmp1.length>1?tmp1[1]:tmp1[0];
           String[] tmp2= tmpString.split("/");
           return "http://"+tmp2[0];
        }
        return "";
    }

    /**
     * 只删除少量的 Html,如前缀和后缀,只去除最外层一个标签标签
     * @param maybeHtml
     * @return
     */
    public static String delSimpleHtmlTag(String maybeHtml){
         return maybeHtml;
         //TODO
    }
    public static String delHtmlPre(String htmlStr){
        return htmlStr.trim().replace("<pre>","").replace("</pre>","").trim();
    }

    /**
     * 去除 html 中所有的标签和脚本,只保留文本
     * @param htmlString 源文本
     * @return
     */
    public static String delHTMLTag(String htmlString) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlString);
        htmlString = m_script.replaceAll(""); //过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlString);
        htmlString = m_style.replaceAll(""); //过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlString);
        htmlString = m_html.replaceAll(""); //过滤html标签

        return htmlString.trim(); //返回文本字符串
    }



}
