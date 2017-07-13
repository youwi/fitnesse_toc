package com.qa;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/11.
 */
public class ConnectServerTest {


    @Test
    public void delHtmlPre() throws Exception {
        assert ConnectServer.delHtmlPre("<pre> \n" +
                "        ^ in <eval> at line number 1 at column number 8</pre>").equals("^ in <eval> at line number 1 at column number 8");
    }

    @Test
    public void setBody() throws Exception {
        ConnectServer cs = new ConnectServer("empty");
        cs.setBody(" {\n" +
                "staffId: 1,//注释\n" +
                "teamId: 1,\n" +
                "page: 1,\n" +
                "size: 10\n" +
                "}", "json");
       assert "{\"staffId\":1,\"teamId\":1,\"page\":1,\"size\":10}".equals(cs.paramData.getJsonParam() );

       cs.setBody("[1,2,3]");
       assert "[1,2,3]".equals(cs.paramData.getJsonParam() );

    }

    @Test
    public void subHttpIpPort() {


        assert "http://www.dev.haolie.cn".equals(ConnectServer.subHttpIpPort("http://www.dev.haolie.cn/api/account/xauth"));
        assert "http://10.0.18.42:80".equals(ConnectServer.subHttpIpPort("http://10.0.18.42:80/api/account/xauth"));

        assert "http://10.0.18.42:80".equals(ConnectServer.subHttpIpPort("10.0.18.42:80/api/account/xauth"));

    }
    @Test
    public void SeTES(){

        new Set("HAO_LIE_HR","http://www.dev.haolie.cn","dev");
        new Set("HAO_LIE_CW","http://CW.dev.haolie.cn","dev");

        new Set("HAO_LIE_HR","http://www.dev.haolie.test","test");
        new Set("HAO_LIE_CW","http://CW.dev.haolie.test","test");

        SetEnv.setEnv("test");
        ConnectServer cs = new ConnectServer("http://www.dev.haolie.cn/abc/abc.rest");
      //  cs.autoSetBaseUrl();
        assert "http://www.dev.haolie.test".equals(cs.BASE_URL);
        assert "http://www.dev.haolie.test/abc/abc.rest".equals(cs.BASE_URL+cs.URL);
    }





}