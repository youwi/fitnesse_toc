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

}