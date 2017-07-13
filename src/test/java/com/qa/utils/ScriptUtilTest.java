package com.qa.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/13.
 */
public class ScriptUtilTest {
    @Test
    public void isJson() throws Exception {
        assert ScriptUtil.isJson("{}");
        assert ScriptUtil.isJson("{}");
        assert ScriptUtil.isJson("{\"code\":401,\"msg\":\"未登录\"}");

    }

    @Test
    public void isJavascript() throws Exception {
    }
    @Test
    public void undefined(){
      assert  !ScriptUtil.runJavaScript("undefined");
    }
    @Test
    public void runBool(){
        assert  ScriptUtil.runJavaScript("true");
    }
    @Test
    public void runScript(){
        assert  ScriptUtil.runJavaScript("true==true");
    }

    @Test
    public void runScriptBinding(){
        ScriptUtil.binding("string","ss");
        assert  ScriptUtil.runJavaScript("ss==\"string\"");
    }


}