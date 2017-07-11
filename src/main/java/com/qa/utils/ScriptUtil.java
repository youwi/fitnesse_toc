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


    public static String buildScript(String script, String type) {
        if (type == null) {
            return script;
        } else {
            if (type.equals("groovy")) {

            } else if (type.equals("json")) {

                try {
                    script = script.trim();
                    JSUtil.engine.eval("var out=" + script);
                    Object obj=JSUtil.engine.get("out");
                    return  gson.toJson(obj);
                 } catch (ScriptException e) {
                    e.printStackTrace();
                    return "{\"error\":\"fitnesse script Parse Error\"}";
                }
            } else if (type.equals("js")) {
                try {
                    script = script.trim();
                    JSUtil.engine.eval(script);
                    Object obj=JSUtil.engine.get("out");
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
