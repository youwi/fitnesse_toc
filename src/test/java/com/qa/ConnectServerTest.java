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
        assert "{\"staffId\":1,\"teamId\":1,\"page\":1,\"size\":10}".equals(cs.paramData.getJsonParam());

        cs.setBody("[1,2,3]");
        assert "[1,2,3]".equals(cs.paramData.getJsonParam());

    }

    @Test
    public void jsonContain() {
        ConnectServer cs = new ConnectServer("empty");
        cs.responseBody = "{\"code\":1,\"msg\":\"OK\",\"body\":{\"industryList\":[{\"code\":1,\"amount\":1},{\"code\":18,\"amount\":2},{\"code\":56,\"amount\":1}],\"locationList\":[{\"code\":110000,\"amount\":1},{\"code\":310000,\"amount\":1}]}}\n";
        assert cs.jsonContain("{\"code\":1,\"amount\":1}");

    }

    @Test
    public void javaScript(){
        ConnectServer cs = new ConnectServer("empty");
        cs.responseBody = "{\"code\":1,\"msg\":\"OK\",\"body\":{\"industryList\":[{\"code\":1,\"amount\":1},{\"code\":18,\"amount\":2},{\"code\":56,\"amount\":1}],\"locationList\":[{\"code\":110000,\"amount\":1},{\"code\":310000,\"amount\":1}]}}\n";
        assert (Boolean) cs.javaScript("CONTAIN(response,{\"code\":1,\"amount\":1})");

    }
    @Test
    public void as(){
        ConnectServer cs = new ConnectServer("empty");
        cs.responseBody="{\"code\":1,\"msg\":\"OK\",\"body\":[{\"id\":101716,\"name\":\"客户公司-变动状态-自动化测试预埋数据\",\"orgBdId\":204632,\"orgBdName\":\"员工4-HRBD-自动化测试预埋数据\",\"bdPriority\":20,\"hunterOrderProgress\":0,\"signStatus\":4,\"industryCodes\":[1,44],\"natureCode\":1,\"locationId\":310000,\"type\":1,\"projectProgress\":0},{\"id\":101705,\"name\":\"客户公司-自动化测试预埋数据\",\"orgBdId\":204632,\"orgBdName\":\"员工4-HRBD-自动化测试预埋数据\",\"bdPriority\":10,\"hunterOrderProgress\":0,\"signStatus\":4,\"industryCodes\":[5,8,2],\"natureCode\":1,\"locationId\":310000,\"type\":1,\"projectProgress\":0}],\"meta\":{\"pagination\":{\"page\":1,\"size\":10,\"total\":2,\"totalPages\":1}}}\n";
        assert cs.jsonContain("{name:\t\"客户公司-自动化测试预埋数据\",orgBdName:\t\"员工4-HRBD-自动化测试预埋数据\"}");

    }

    @Test
    public void groovyTest(){
        ConnectServer cs = new ConnectServer("empty");
        cs.responseBody="{\"code\":1,\"msg\":\"OK\",\"body\":[{\"id\":101716,\"name\":\"客户公司-变动状态-自动化测试预埋数据\",\"orgBdId\":204632,\"orgBdName\":\"员工4-HRBD-自动化测试预埋数据\",\"bdPriority\":20,\"hunterOrderProgress\":0,\"signStatus\":4,\"industryCodes\":[1,44],\"natureCode\":1,\"locationId\":310000,\"type\":1,\"projectProgress\":0},{\"id\":101705,\"name\":\"客户公司-自动化测试预埋数据\",\"orgBdId\":204632,\"orgBdName\":\"员工4-HRBD-自动化测试预埋数据\",\"bdPriority\":10,\"hunterOrderProgress\":0,\"signStatus\":4,\"industryCodes\":[5,8,2],\"natureCode\":1,\"locationId\":310000,\"type\":1,\"projectProgress\":0}],\"meta\":{\"pagination\":{\"page\":1,\"size\":10,\"total\":2,\"totalPages\":1}}}\n";
        assert  cs.groovyScript("response.code==1").equals(true);
        assert  cs.groovyScript("response.code").equals(1);
        assert  cs.groovyScript("response.body[0].id").equals(101716);
        assert  !cs.groovyScript("response.body[0].id").equals(101715);
    }
    @Test
    public void groovyTest2(){
        ConnectServer cs = new ConnectServer("empty");
        cs.responseBody="{\"code\":1,\"msg\":\"OK\",\"body\":[{\"id\":101716,\"name\":\"客户公司-变动状态-自动化测试预埋数据\",\"orgBdId\":204632,\"orgBdName\":\"员工4-HRBD-自动化测试预埋数据\",\"bdPriority\":20,\"hunterOrderProgress\":0,\"signStatus\":4,\"industryCodes\":[1,44],\"natureCode\":1,\"locationId\":310000,\"type\":1,\"projectProgress\":0},{\"id\":101705,\"name\":\"客户公司-自动化测试预埋数据\",\"orgBdId\":204632,\"orgBdName\":\"员工4-HRBD-自动化测试预埋数据\",\"bdPriority\":10,\"hunterOrderProgress\":0,\"signStatus\":4,\"industryCodes\":[5,8,2],\"natureCode\":1,\"locationId\":310000,\"type\":1,\"projectProgress\":0}],\"meta\":{\"pagination\":{\"page\":1,\"size\":10,\"total\":2,\"totalPages\":1}}}\n";
        assert  cs.groovyScript("response.code==1").equals(true);
        assert  cs.groovyScript("response.code").equals(1);
        assert  cs.groovyScript("response.body[0].id").equals(101716);
        assert  !cs.groovyScript("response.body[0].id").equals(101715);
    }
    @Test
    public void subHttpIpPort() {


        assert "http://www.dev.haolie.cn".equals(ConnectServer.subHttpIpPort("http://www.dev.haolie.cn/api/account/xauth"));
        assert "http://10.0.18.42:80".equals(ConnectServer.subHttpIpPort("http://10.0.18.42:80/api/account/xauth"));

        assert "http://10.0.18.42:80".equals(ConnectServer.subHttpIpPort("10.0.18.42:80/api/account/xauth"));

    }

    @Test
    public void SeTES() {

        new Set("HAO_LIE_HR", "http://www.dev.haolie.cn", "dev");
        new Set("HAO_LIE_CW", "http://CW.dev.haolie.cn", "dev");

        new Set("HAO_LIE_HR", "http://www.dev.haolie.test", "test");
        new Set("HAO_LIE_CW", "http://CW.dev.haolie.test", "test");

        SetEnv.setEnv("test");
        ConnectServer cs = new ConnectServer("http://www.dev.haolie.cn/abc/abc.rest");
        //  cs.autoSetBaseUrl();
        assert "http://www.dev.haolie.test".equals(cs.BASE_URL);
        assert "http://www.dev.haolie.test/abc/abc.rest".equals(cs.BASE_URL + cs.URL);
    }


}