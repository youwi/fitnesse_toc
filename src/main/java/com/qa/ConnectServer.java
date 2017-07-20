package com.qa;

import com.qa.http.*;
import com.qa.utils.*;
import com.qa.constants.ConfigConstantsTest;
import com.qa.exceptions.HttpStatusException;

import org.json.JSONObject;
import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qa.Set.getEnv;
import static com.qa.utils.StringURLUtil.buildFromByMap;
import static com.qa.utils.StringURLUtil.urlParamMatcher;

/**
 * Created by yus on 2016/11/26.
 */
public class ConnectServer {

    String BASE_URL = "";
    JsonUtil jsonUtil=new JsonUtil();
    String URL;
    String env = null;
    String responseBody = null;
    String requestBody=null;
    Map requestMap=new HashMap();
    Map requestHeaderMap=new HashMap();
    Map responseHeaderMap=new HashMap();
    String type = null;

    public ConnectServer(String URL) {
        this.URL = delHTMLTag(URL);
        this.autoSetBaseUrl();
        addOneUrlCount(URL);
        AUTO_GET_BASE_URL();//根据配置文件自动获取IP/URL
    }

    public ConnectServer(String URL, String env) {

        this.URL = delHTMLTag(URL);
        this.env = env;
        this.autoSetBaseUrl();
        addOneUrlCount(URL);

        AUTO_GET_BASE_URL();//根据配置文件自动获取IP/URL
    }

    public ConnectServer(String URL, String env, String type) {

        this.URL = delHTMLTag(URL);
        this.env = env;
        this.type = type;
        this.autoSetBaseUrl();
        addOneUrlCount(URL);
        AUTO_GET_BASE_URL();//根据配置文件自动获取IP/URL
    }

    public boolean post() {
        jsonUtil.parseJson( requestForJSON(BASE_URL + URL));
        return true;
       // return "message:<<OK>>";
    }
    public boolean delete(){
        this.type="DELETE";
        jsonUtil.parseJson(requestForJSON(BASE_URL+URL));
        return true;
    }
    public boolean get(){
        this.type="GET";
        jsonUtil.parseJson(requestForJSON(BASE_URL+URL));
        return true;
    }

    /**
     *注:默认删除 html 标记
     */
    public boolean setBody(String bodyString) {
        bodyString=delHtmlPre(bodyString);
        if(isGson(bodyString)){
            this.requestBody=GsonJsonUtil.gson.toJson(gsonParse(bodyString));
        }else{
            this.requestBody=bodyString;
        }
        return true;
    }

    /**
     * 判断是不是一个普通json,并可以用Gson 来解析
     * @return
     */
    boolean isGson(String maybeJson){
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
            this.requestBody=bodyString;
        }else {
            this.requestBody=ScriptUtil.buildScript(delHtmlPre(bodyString), type);
        }
        return true;
    }


    /**
     * 设置http请求参数和 URL 参数
     * @param name
     * @param value
     * @return
     * @throws Exception
     */
    public boolean setParam(String name, String value){
        requestMap.put(name, value);
        return true;
    }
    public boolean setHeader(String name, String value) {
        requestHeaderMap.put(name, value);
        return true;
    }


    public String requestForString(String fullUrl) {
        if (type == null) {
            type = "POST";
        }
        fullUrl=urlParamMatcher(fullUrl, buildFromByMap(requestMap));
        if("GET".equals(type)){
            Get get=Http.get(fullUrl);
            fillRequestHeader(get,requestHeaderMap);
            responseBody= get.text();
            responseHeaderMap=get.headers();
        }
        if("POST".equals(type)){
            Post post = Http.post(fullUrl, requestBody);
            fillRequestHeader(post,requestHeaderMap);
            responseBody= post.text();
            responseHeaderMap=post.headers();
        }
        if("DELETE".equals(type)){
            Delete delete=Http.delete(fullUrl);
            fillRequestHeader(delete,requestHeaderMap);
            responseBody= delete.text();
            responseHeaderMap=delete.headers();
        }
        return responseBody;
    }
    /**
     * 填充请求 header,包括全局 header
    * */
    public void fillRequestHeader(Request request,Map<String,String> headerMap){
        for(String key:SetGlobalHeader.headersMap.keySet()){
            request.header(key,SetGlobalHeader.headersMap.get(key));
        }
        if(headerMap==null) return;

        for(String key:headerMap.keySet()){
            request.header(key,headerMap.get(key));
        }

    }

    /**
     * 获取返回结果中的json结果
     * @return
     */
    public String jsonValue(String key){
        try {
            return jsonUtil.getResult(key);
        }catch (Exception e){
            throw new RuntimeException("message:<< response is not json "+responseBody+">>");
        }
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



    public JSONObject requestForJSON(String fullurl) {
        if (type == null) {
            type = "POST";
        }
        //.header("Accept", "application/json")

        requestHeaderMap.put("Content-Type", "application/json;charset=UTF-8");
      //  requestHeaderMap.put("Content-Type", "application/x-www-form-urlencoded");
        requestForString(fullurl);
        if(isGson(responseBody)){
            JSONObject objResponse = new JSONObject(responseBody);
            return objResponse;
        }
        return new JSONObject("{}");
    }

    public String requestForXML(String fullurl) {
        if (type == null) {
            type = "POST";
        }
        return requestForString(fullurl);
    }



    public boolean jsonStructure(String str){
        return jsonUtil.checkParam(str);
    }

    public String responseHeader(String responseHeaderKey) {
        return "";
    }


    public void setType(String type) {
        this.type = type;
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
        if(isNotEmpty( httpIpPort)) {
            this.BASE_URL = Set.getValueSibling(httpIpPort, Set.getEnv());
            this.URL=this.URL.replace(httpIpPort,"");
        }
    }
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
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

    /**
     *     统计 URL 访问次数

     */
    public static Map<String,Integer> _url_count_=new HashMap();

    /**
     * 添加统计数据,增加一个值
     */
    static public void  addOneUrlCount( String url){
        Integer i=_url_count_.get(url);
        if(i==null) i=0;
        i++;
        _url_count_.put(url,i);
        Get get = Http.get("http://baidu.com");
        System.out.println(get.text());
    }
    static{
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("请求所有的 URL 个数为:"+ConnectServer._url_count_.size());
            }
        });
    }


}
