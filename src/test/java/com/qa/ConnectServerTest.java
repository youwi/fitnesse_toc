package com.qa;
import com.qa.utils.GsonJsonUtil;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
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
        assert "{\"staffId\":1,\"teamId\":1,\"page\":1,\"size\":10}".equals(cs.requestBody);
        cs.setBody("[1,2,3]");
        assert "[1,2,3]".equals(cs.requestBody);
    }
    @Test
    public void setBody2() throws Exception {
        ConnectServer cs = new ConnectServer("empty");
        cs.setBody(" {\n" +
                "staffId: 1,//注释\n" +
                "teamId: 1,\n" +
                "page: 1,\n" +
                "size: 10\n" +
                "}");
        assert "{\"staffId\":1,\"teamId\":1,\"page\":1,\"size\":10}".equals(cs.requestBody);
        cs.setBody("[1,2,3]");
        assert "[1,2,3]".equals(cs.requestBody);
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
   /*
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
    }*/
    @Test
    public void subHttpIpPort() {

        assert "http://www.dev.haolie.cn".equals(ConnectServer.subHttpIpPort("http://www.dev.haolie.cn/api/account/xauth"));
        assert "http://10.0.18.42:80".equals(ConnectServer.subHttpIpPort("http://10.0.18.42:80/api/account/xauth"));
        assert "http://10.0.18.42:80".equals(ConnectServer.subHttpIpPort("10.0.18.42:80/api/account/xauth"));
    }
    @Test
    public void SeTES() {
        new Store("HAO_LIE_HR", "http://www.dev.haolie.cn", "dev");
        new Store("HAO_LIE_CW", "http://CW.dev.haolie.cn", "dev");
        new Store("HAO_LIE_HR", "http://www.dev.haolie.test", "test");
        new Store("HAO_LIE_CW", "http://CW.dev.haolie.test", "test");
        Store.setEnv("test");
        ConnectServer cs = new ConnectServer("http://www.dev.haolie.cn/abc/abc.rest");
        //  cs.autoSetBaseUrl();
        assert "http://www.dev.haolie.test".equals(cs.BASE_URL);
        assert "http://www.dev.haolie.test/abc/abc.rest".equals(cs.BASE_URL + cs.URL);
    }
    @Test
    public void uuidTest(){
        System.out.println(UUID.randomUUID().toString() );
        System.out.println( new   SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date()));;
        System.out.println( new   SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date(1000000000)));
    }
    @Test
    public void stringCompareTest(){
        String big="{\n" +
                "    \"code\": 1,\n" +
                "    \"msg\": \"OK\",\n" +
                "    \"body\": {\n" +
                "        \"projectTarget\": {\n" +
                "            \"id\": 616,\n" +
                "            \"projectId\": \"5x5p5ps9s9vxvxzbh\",\n" +
                "            \"resumeId\": 342,\n" +
                "            \"status\": 53,\n" +
                "            \"code\": \"onboard\",\n" +
                "            \"lockStatus\": 2,\n" +
                "            \"createdBy\": 204641,\n" +
                "            \"updatedBy\": 204638,\n" +
                "            \"createdAt\": \"2017-07-27T15:12:28+08:00\",\n" +
                "            \"updatedAt\": \"2017-07-27T15:43:30+08:00\",\n" +
                "            \"created\": {\n" +
                "                \"avatar\": \"https://i.jihui.io/o_1bgq7p42q5oq1k7l163asngsvo7.png\",\n" +
                "                \"chineseName\": \"猎头-1-自动化测试预埋数据\",\n" +
                "                \"email\": \"hunter@test.com\",\n" +
                "                \"gender\": 1,\n" +
                "                \"id\": 204641,\n" +
                "                \"mobile\": \"19900030001\",\n" +
                "                \"workingEmail\": \"test@test.com\",\n" +
                "                \"workingPhone\": \"19900030001\",\n" +
                "                \"company\": {\n" +
                "                    \"id\": 101708,\n" +
                "                    \"name\": \"猎头公司-强状态-自动化预埋数据\",\n" +
                "                    \"type\": 2,\n" +
                "                    \"logoUrl\": null,\n" +
                "                    \"siteUrl\": \"\",\n" +
                "                    \"financingStatus\": 0,\n" +
                "                    \"empScale\": 0,\n" +
                "                    \"natureCode\": 0,\n" +
                "                    \"locationId\": 310000,\n" +
                "                    \"locationDetail\": \"\",\n" +
                "                    \"description\": \"\",\n" +
                "                    \"managerId\": 1088,\n" +
                "                    \"createdAt\": \"2017-07-12T14:44:29+08:00\",\n" +
                "                    \"updatedAt\": \"2017-07-31T15:56:32+08:00\",\n" +
                "                    \"createdBy\": 204628,\n" +
                "                    \"updatedBy\": 204641,\n" +
                "                    \"industryCodes\": [],\n" +
                "                    \"signStatus\": 6,\n" +
                "                    \"orderProgress\": 0,\n" +
                "                    \"projectProgress\": 0,\n" +
                "                    \"hunterOrderProgress\": 3,\n" +
                "                    \"hunterProjectProgress\": 0\n" +
                "                },\n" +
                "                \"imid\": \"149984216387509\",\n" +
                "                \"displayName\": \"猎头-1-自动化测试预埋数据\",\n" +
                "                \"phoneType\": \"\"\n" +
                "            },\n" +
                "            \"activeStatus\": 1,\n" +
                "            \"companyId\": 101708,\n" +
                "            \"hrAttentionType\": 2,\n" +
                "            \"orderNo\": 20170727000001,\n" +
                "            \"attachments\": []\n" +
                "        },\n" +
                "        \"resume\": {\n" +
                "            \"id\": 342,\n" +
                "            \"name\": \"候选A\",\n" +
                "            \"gender\": 2,\n" +
                "            \"mobile\": \"15021049947\",\n" +
                "            \"email\": \"zhangyaligazi@163.com\",\n" +
                "            \"birthday\": \"1991-09-01T00:00:00+09:00\",\n" +
                "            \"currentLocationCode\": 310000,\n" +
                "            \"startedWorkAt\": \"2012-01-01T08:00:00+08:00\",\n" +
                "            \"orgName\": \"美满电子科技（上海）有限公司\",\n" +
                "            \"position\": \"软件测试工程师\",\n" +
                "            \"annualSalary\": 100000,\n" +
                "            \"annualSalaryType\": 1,\n" +
                "            \"degree\": 2,\n" +
                "            \"skills\": [],\n" +
                "            \"certificates\": [],\n" +
                "            \"langs\": [],\n" +
                "            \"expectLocations\": [],\n" +
                "            \"expectSalary\": 200000,\n" +
                "            \"expectSalaryType\": 1,\n" +
                "            \"expectIndustries\": [],\n" +
                "            \"leftReason\": \"职业前景/潜在升职机会\",\n" +
                "            \"dimissionPeriod\": \"10\",\n" +
                "            \"evaluate\": \"第一个测试\",\n" +
                "            \"schoolCode\": 322003600,\n" +
                "            \"schoolName\": \"江苏理工学院\",\n" +
                "            \"schoolTypes\": [],\n" +
                "            \"seniority\": 6,\n" +
                "            \"age\": 25\n" +
                "        },\n" +
                "        \"project\": {\n" +
                "            \"id\": \"xjvxbee2m66r1hhk9\",\n" +
                "            \"type\": 2,\n" +
                "            \"status\": 0,\n" +
                "            \"title\": \"职位A-自动化测试\",\n" +
                "            \"orgId\": 102167,\n" +
                "            \"addressIds\": \"310000\",\n" +
                "            \"headcount\": 10,\n" +
                "            \"forecastTurnover\": 48000,\n" +
                "            \"salaryDetail\": {\n" +
                "                \"salaryType\": 2,\n" +
                "                \"welfares\": []\n" +
                "            },\n" +
                "            \"salaryLower\": 200000,\n" +
                "            \"salaryUpper\": 300000,\n" +
                "            \"contractId\": 101719,\n" +
                "            \"warrantyPeriod\": 3,\n" +
                "            \"rate\": 20,\n" +
                "            \"majorId\": 204665,\n" +
                "            \"hrId\": 204705,\n" +
                "            \"cwId\": 204638,\n" +
                "            \"ownerId\": 204665,\n" +
                "            \"createdBy\": 204665,\n" +
                "            \"updatedBy\": 204638,\n" +
                "            \"createdAt\": \"2016-07-27T13:21:13+08:00\",\n" +
                "            \"updatedAt\": \"2017-08-04T16:16:44+08:00\",\n" +
                "            \"resumeNum\": 1,\n" +
                "            \"successNum\": 1,\n" +
                "            \"major\": {\n" +
                "                \"avatar\": \"https://i.jihui.io/o_1bgq7p42q5oq1k7l163asngsvo7.png\",\n" +
                "                \"chineseName\": \"员工11-自动化测试预埋-集权\",\n" +
                "                \"email\": \"autotest11@test.com\",\n" +
                "                \"englishName\": \"autotest11\",\n" +
                "                \"gender\": 1,\n" +
                "                \"id\": 204665,\n" +
                "                \"mobile\": \"19900000011\",\n" +
                "                \"workingEmail\": \"\",\n" +
                "                \"workingPhone\": \"19900000011\",\n" +
                "                \"company\": {\n" +
                "                    \"id\": 1,\n" +
                "                    \"name\": \"猎萝卜网\",\n" +
                "                    \"type\": 3,\n" +
                "                    \"logoUrl\": null,\n" +
                "                    \"siteUrl\": \"\",\n" +
                "                    \"financingStatus\": 0,\n" +
                "                    \"empScale\": 0,\n" +
                "                    \"natureCode\": 0,\n" +
                "                    \"locationId\": 330100,\n" +
                "                    \"locationDetail\": \"\",\n" +
                "                    \"description\": \"\",\n" +
                "                    \"managerId\": 1088,\n" +
                "                    \"createdAt\": \"2017-04-24T15:14:46+08:00\",\n" +
                "                    \"updatedAt\": \"2017-07-31T15:56:32+08:00\",\n" +
                "                    \"createdBy\": 0,\n" +
                "                    \"updatedBy\": 21194,\n" +
                "                    \"industryCodes\": [],\n" +
                "                    \"signStatus\": 6,\n" +
                "                    \"orderProgress\": 0,\n" +
                "                    \"projectProgress\": 0,\n" +
                "                    \"hunterOrderProgress\": 1,\n" +
                "                    \"hunterProjectProgress\": 0\n" +
                "                },\n" +
                "                \"imid\": \"19900000011\",\n" +
                "                \"displayName\": \"员工11-自动化测试预埋-集权\",\n" +
                "                \"phoneType\": \"\"\n" +
                "            },\n" +
                "            \"hr\": {\n" +
                "                \"avatar\": \"https://i.jihui.io/o_1bgq7p42q5oq1k7l163asngsvo7.png\",\n" +
                "                \"chineseName\": \"HR自动化2\",\n" +
                "                \"email\": \"test002002@test.com\",\n" +
                "                \"gender\": 1,\n" +
                "                \"id\": 204705,\n" +
                "                \"mobile\": \"19900020002\",\n" +
                "                \"workingEmail\": \"\",\n" +
                "                \"company\": {\n" +
                "                    \"id\": 102167,\n" +
                "                    \"name\": \"签约客户公司-自动化测试预埋数据\",\n" +
                "                    \"type\": 1,\n" +
                "                    \"logoUrl\": null,\n" +
                "                    \"siteUrl\": \"\",\n" +
                "                    \"financingStatus\": 0,\n" +
                "                    \"empScale\": 0,\n" +
                "                    \"natureCode\": 4,\n" +
                "                    \"locationId\": 310000,\n" +
                "                    \"locationDetail\": \"上海\",\n" +
                "                    \"description\": \"\",\n" +
                "                    \"managerId\": 204665,\n" +
                "                    \"createdAt\": \"2017-07-27T11:18:58+08:00\",\n" +
                "                    \"updatedAt\": \"2017-07-27T13:28:58+08:00\",\n" +
                "                    \"createdBy\": 204632,\n" +
                "                    \"updatedBy\": 204638,\n" +
                "                    \"industryCodes\": [],\n" +
                "                    \"signStatus\": 2,\n" +
                "                    \"orderProgress\": 0,\n" +
                "                    \"projectProgress\": 30,\n" +
                "                    \"hunterOrderProgress\": 0,\n" +
                "                    \"hunterProjectProgress\": 0\n" +
                "                },\n" +
                "                \"imid\": \"150113265729136\",\n" +
                "                \"displayName\": \"HR自动化2\",\n" +
                "                \"phoneType\": \"\"\n" +
                "            },\n" +
                "            \"organization\": {\n" +
                "                \"id\": 102167,\n" +
                "                \"name\": \"签约客户公司-自动化测试预埋数据\",\n" +
                "                \"type\": 1,\n" +
                "                \"logoUrl\": null,\n" +
                "                \"siteUrl\": \"\",\n" +
                "                \"financingStatus\": 0,\n" +
                "                \"empScale\": 0,\n" +
                "                \"natureCode\": 4,\n" +
                "                \"locationId\": 310000,\n" +
                "                \"locationDetail\": \"上海\",\n" +
                "                \"description\": \"\",\n" +
                "                \"managerId\": 204665,\n" +
                "                \"createdAt\": \"2017-07-27T11:18:58+08:00\",\n" +
                "                \"updatedAt\": \"2017-07-27T13:28:58+08:00\",\n" +
                "                \"createdBy\": 204632,\n" +
                "                \"updatedBy\": 204638,\n" +
                "                \"industryCodes\": [\n" +
                "                    1,\n" +
                "                    44\n" +
                "                ],\n" +
                "                \"signStatus\": 2\n" +
                "            },\n" +
                "            \"passInterviewRate\": 0,\n" +
                "            \"interviewNum\": 1,\n" +
                "            \"forecastTurnoverLower\": 36000,\n" +
                "            \"forecastTurnoverUpper\": 60000,\n" +
                "            \"requiredMajorIds\": [],\n" +
                "            \"requiredOthers\": [],\n" +
                "            \"alterMajorIds\": [],\n" +
                "            \"alterOthers\": [],\n" +
                "            \"negativeMajorIds\": [],\n" +
                "            \"negativeOthers\": [],\n" +
                "            \"highlights\": [],\n" +
                "            \"functionCodes\": [],\n" +
                "            \"attentionType\": 0,\n" +
                "            \"positionClassifyCodes\": [\n" +
                "                363,\n" +
                "                366\n" +
                "            ],\n" +
                "            \"interviewAllNum\": 1,\n" +
                "            \"evaluateOrgQuality\": 0,\n" +
                "            \"evaluateOrgPeriod\": 0,\n" +
                "            \"evaluateNewReason\": 0,\n" +
                "            \"evaluateEntryTime\": 0,\n" +
                "            \"evaluateInterviewTime\": 0,\n" +
                "            \"evaluateDifficultyReason\": 0,\n" +
                "            \"evaluateBasicScore\": 100,\n" +
                "            \"evaluateOrgAttraction\": 0,\n" +
                "            \"evaluateSalaryAttraction\": 0,\n" +
                "            \"evaluateReceivables\": 0,\n" +
                "            \"evaluateResumeAchieve\": 0,\n" +
                "            \"evaluateOtherHeadhunter\": 0,\n" +
                "            \"evaluateProgressStage\": 0,\n" +
                "            \"evaluateKeyScore\": 100,\n" +
                "            \"scoreId\": 57,\n" +
                "            \"receiveType\": 0,\n" +
                "            \"resumeAllNum\": 1,\n" +
                "            \"subscriberType\": 0,\n" +
                "            \"requiredProfessionAbility\": [],\n" +
                "            \"requiredEducationExperience\": {\n" +
                "                \"schoolCodes\": [],\n" +
                "                \"majorCodes\": [],\n" +
                "                \"degreeCodes\": []\n" +
                "            },\n" +
                "            \"alterProfessionAbility\": [],\n" +
                "            \"alterEducationExperience\": {\n" +
                "                \"schoolCodes\": [],\n" +
                "                \"majorCodes\": [],\n" +
                "                \"degreeCodes\": []\n" +
                "            },\n" +
                "            \"negativeProfessionAbility\": [],\n" +
                "            \"negativeEducationExperience\": {\n" +
                "                \"schoolCodes\": [],\n" +
                "                \"majorCodes\": [],\n" +
                "                \"degreeCodes\": []\n" +
                "            },\n" +
                "            \"projectNo\": \"HI85057\",\n" +
                "            \"rateLower\": 0.18,\n" +
                "            \"rateUpper\": 0.2,\n" +
                "            \"cnum\": 1,\n" +
                "            \"cmaxNum\": 50,\n" +
                "            \"callNum\": 1,\n" +
                "            \"ccurrentReceiveNum\": 0,\n" +
                "            \"cmaxReceiveNum\": 0,\n" +
                "            \"cNum\": 1,\n" +
                "            \"cMaxNum\": 50,\n" +
                "            \"cAllNum\": 1,\n" +
                "            \"cCurrentReceiveNum\": 0,\n" +
                "            \"cMaxReceiveNum\": 0\n" +
                "        },\n" +
                "        \"projectTargetScore\": {\n" +
                "            \"id\": 510,\n" +
                "            \"projectId\": \"nhn68cgh8cghl57si\",\n" +
                "            \"resumeId\": 342,\n" +
                "            \"salary\": 2,\n" +
                "            \"address\": 0,\n" +
                "            \"requireScore\": 50,\n" +
                "            \"alterScore\": 0,\n" +
                "            \"allScore\": 100\n" +
                "        }\n" +
                "    }\n" +
                "}";
        String target="\"id\": 616,\n" +
                "            \"projectId\": \"xxx\",\n" +
                "            \"resumeId\": 342,\n" +
                "            \"status\": 53,\n" +
                "            \"code\": \"onboard\",\n" +
                "            \"lockStatus\": 2,\n" +
                "            \"createdBy\": 204641,\n" +
                "            \"updatedBy\": 204638,\n" +
                "            \"createdAt\": \"2017-07-27T15:12:28+08:00\",\n" +
                "            \"updatedAt\": \"2017-07-27T15:43:30+08:00\",";
        System.out.println(ConnectServer.jsonCompare(big,target));

        big="{\"code\":1,\"msg\":\"OK\",\"body\":[{\"id\":\"ibeef2ij4mn6qr8ca\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.19.38.24.024\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T19:38:15+08:00\",\"updatedAt\":\"2017-08-04T19:38:15+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"XC09304\"},{\"id\":\"9st99vvx2jjmm66rp\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.19.35.14.014\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T19:35:05+08:00\",\"updatedAt\":\"2017-08-04T19:35:05+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"WX04194\"},{\"id\":\"lk55t9uvt9uvab0e3\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.19.33.10.010\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T19:33:01+08:00\",\"updatedAt\":\"2017-08-04T19:33:01+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"XI28713\"},{\"id\":\"vyhkk55ppss99vvx1\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.19.28.43.043\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T19:28:34+08:00\",\"updatedAt\":\"2017-08-04T19:28:34+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"JN60754\"},{\"id\":\"af7st9uvf2ij4mn6o\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.19.17.40.040\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T19:17:31+08:00\",\"updatedAt\":\"2017-08-04T19:17:31+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"XX58221\"},{\"id\":\"480e0eijijn6n68cy\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.17.46.25.025\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T17:46:16+08:00\",\"updatedAt\":\"2017-08-04T17:46:16+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"QY47622\"},{\"id\":\"507suvyzuvyz0eijl\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.16.08.16.016\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T16:08:07+08:00\",\"updatedAt\":\"2017-08-04T16:08:07+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"ZS42949\"},{\"id\":\"unyz0eijn68cghl5u\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.15.48.16.016\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T15:48:07+08:00\",\"updatedAt\":\"2017-08-04T16:05:43+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"ZW40129\"},{\"id\":\"xz5p5ps9s9vxvxzbh\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.14.37.36.036\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T14:37:27+08:00\",\"updatedAt\":\"2017-08-04T16:05:43+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"NU59647\"},{\"id\":\"be7st9uvf2ij4mn6o\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.13.31.57.057\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T13:31:48+08:00\",\"updatedAt\":\"2017-08-04T16:05:43+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"EN12195\"},{\"id\":\"dp6rrcc1pss99vvxm\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.11.35.54.054\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T11:35:45+08:00\",\"updatedAt\":\"2017-08-04T16:05:43+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"VM85964\"},{\"id\":\"9juvab0e4mn6d1ght\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.03.20.38.57.057\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-03T20:38:48+08:00\",\"updatedAt\":\"2017-08-04T11:06:32+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"JV75341\"},{\"id\":\"j56rc1hkc1hk5ps9j\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.03.19.16.40.040\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-03T19:16:31+08:00\",\"updatedAt\":\"2017-08-04T11:06:32+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"WG34721\"},{\"id\":\"1cijijn6n68c8cgh0\",\"status\":0,\"title\":\"职位C-自动化测试\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-01-01T11:13:43+08:00\",\"updatedAt\":\"2017-08-04T11:06:33+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":2,\"projectNo\":\"HV96042\"},{\"id\":\"yxl5t9uvt9uvab0e3\",\"status\":0,\"title\":\"职位B\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-01-01T11:08:32+08:00\",\"updatedAt\":\"2017-08-04T11:06:33+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"IY59183\"},{\"id\":\"icijijn6n68c8cgh0\",\"status\":0,\"title\":\"职位A-自动化测试\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2016-07-27T13:21:13+08:00\",\"updatedAt\":\"2017-08-04T16:16:44+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":1,\"projectNo\":\"HI85057\"}],\"meta\":{\"pagination\":{\"page\":1,\"size\":100,\"total\":16,\"totalPages\":1}}}";


        target="{\"id\": \"9st99vvx2jjmm66\",\n" +
                "            \"status\": 0,\n" +
                "            \"title\": \"职位A-s自动化测试2017.08.04.19.35.14.014\",\n" +
                "            \"orgId\": 102167,\n" +
                "            \"hrId\": 204705,\n" +
                "            \"cwId\": 204638,\n" +
                "            \"createdAt\": \"2017-08-04T19:35:05+08:00\",\n" +
                "            \"updatedAt\": \"2017-08-04T19:35:05+08:00\"}";
        big=GsonJsonUtil.gsonPretty.toJson(GsonJsonUtil.gsonPretty.fromJson(big, Map.class));
        target=GsonJsonUtil.gsonPretty.toJson(GsonJsonUtil.gsonPretty.fromJson(target, Map.class));
        System.out.println(ConnectServer.jsonCompare(big,target));

    }
}