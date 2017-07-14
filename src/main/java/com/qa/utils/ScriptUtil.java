package com.qa.utils;

import com.google.gson.Gson;

import javax.script.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


/**
 * Created by yu on 16/8/23.
 */
public class ScriptUtil {


    static ScriptEngineManager manager = new ScriptEngineManager();
    public static ScriptEngine engine = manager.getEngineByName("javascript");
    static Gson gson=new Gson();


    /**
     * 判断是否能运行为 js
     * @return
     */
    public static boolean isJson(String script){
        if(script==null) return  false;
        try {

            script = script.trim();
            JSUtil.engine.eval("var out=" + script);
             return  true;
        } catch (ScriptException e) {
            return false;
        }
    }
    public static boolean isJavascript(String json){
        if(json==null) return  false;
        try {
            json = json.trim();
            JSUtil.engine.eval(json);
            return  true;
        } catch (ScriptException e) {
            return false;
        }
    }
    public static boolean preLoadCompileJs()  {
        try{
            String jsScript=  inputStream2String( ScriptUtil.class.getResourceAsStream("/jsonUtil.js"));// Charset.forName("utf-8"));
            JSUtil.engine.eval(jsScript);

        }catch (IOException e ){
            System.out.println(e.getMessage());
        }catch (ScriptException e){
            System.out.println(e.getMessage());
        }
        return  true;
    }
   public static String  inputStream2String(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null){
            buffer.append(line);
            buffer.append("\n");
        }
        return buffer.toString();
    }

    /**
     * 绑定到 js 变量的值
     * @param obj 变量
     * @param name 变量名
     * @return
     */
    public static boolean binding(Object obj,String name){
        Bindings bindings = engine.createBindings(); //Local级别的Binding
        bindings.put(name,obj);
        engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
        return true;
    }
    public static boolean runJavaScript(String script){
        if(script==null) return false;
        try {
            script = script.trim();
            Object object=JSUtil.engine.eval(script);
            if(object instanceof Boolean){
                return (Boolean) object;
            }
            if(object==null){
                return false;
            }
            if(object instanceof String){
                return !object.equals("undefined");
            }
        } catch (ScriptException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
    public static String buildScript(String script, String type) {
        if (type == null) {
            return script;
        } else {
            if (type.equals("groovy")) {

            } else if (type.equals("json")) {

                try {
                    script = script.trim();
                    JSUtil.engine.eval("var out=" + script+";out=JSON.stringify(out);");
                    Object obj=JSUtil.engine.get("out");
                    if(obj instanceof String)
                        return obj.toString();
                    return  gson.toJson(obj);
                 } catch (ScriptException e) {
                    e.printStackTrace();
                    return "{\"error\":\"fitnesse script Parse Error\"}";
                }
            } else if (type.equals("js")) {
                try {
                    script = script.trim();
                    JSUtil.engine.eval(script+";typeof(out)!=\"undefined\"?out=JSON.stringify(out):true;");
                    Object obj=JSUtil.engine.get("out");

                    if(obj instanceof String)
                        return obj.toString();
                    return  gson.toJson(obj);
                } catch (ScriptException e) {
                    e.printStackTrace();
                    return "{\"error\":\"fitnesse script Parse Error\"}";
                }
            }
        }
        return script;
    }


}
