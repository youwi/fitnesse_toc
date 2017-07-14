package com.qa.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/13.
 */
public class ScriptUtilTest {
    @Test
    public void preLoadCompileJs() throws Exception {
        ScriptUtil.preLoadCompileJs();
        assert ScriptUtil.runJavaScript("CONTAIN({\"page\":1,\"size\":10},{\"page\":1,\"size\":10})");
        assert !ScriptUtil.runJavaScript("CONTAIN({\"page\":1,\"size\":10},{\"page\":1,\"size\":11})");
        assert ScriptUtil.runJavaScript("CONTAIN({\"code\":1,\"msg\":\"OK\",\"body\":{\"industryList\":[{\"code\":1,\"amount\":1},{\"code\":18,\"amount\":2},{\"code\":56,\"amount\":1}],\"locationList\":[{\"code\":110000,\"amount\":1},{\"code\":310000,\"amount\":1}]}}\n,{\"code\":110000,\"amount\":1})");
    }


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