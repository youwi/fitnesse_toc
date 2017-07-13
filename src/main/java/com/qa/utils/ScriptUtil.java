package com.qa.utils;

import com.google.gson.Gson;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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
        try {
            script = script.trim();
            JSUtil.engine.eval("var out=" + script);
             return  true;
        } catch (ScriptException e) {
            return false;
        }
    }
    public static boolean isJavascript(String json){
        try {
            json = json.trim();
            JSUtil.engine.eval(json);
            return  true;
        } catch (ScriptException e) {
            return false;
        }
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
