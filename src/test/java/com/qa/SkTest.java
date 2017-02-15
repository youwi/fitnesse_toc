package com.qa;
import com.qa.Financial2Sys.Financial2Sys;
import com.qa.TestHttpClient.HttpClientUtil;
import com.qa.TestHttpClient.HttpRequestCallback;
import com.qa.WkWapServer.WkWapServer;
import org.apache.http.Header;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yu on 2017/2/14.
 */
public class SkTest {
    @Test
   public void timeouTest() throws IOException {
       // new HttpClientUtil().httpPostRequest("http://127.0.0.1:4000", new HttpRequestCallback() {
         new BaseServer("http://127.0.0.1:4000").testRun("")  ;

    }


    @Test
    public void SDFEKK() throws Exception {
        SSOLogin  sdfk=  new SSOLogin("/login","test","post");
       // script	s s o login	/login	test	post
       String ske= sdfk.getHrWKSSO("13162108888","111111","https://hryun2.test.wkzf:8178/account/getUsers.action");
   //     $WKSSO=	getHrWKSSO;	13162108888	111111

    }
    //@Test
    public void stk() throws Exception {
        WkWapServer sk=   new WkWapServer("/subscribe/guestTipoffHouse.rest?guestId=21972&tmpHouseId=d909a491f8ebfc18&notExist=1");//,"test","get");


     //   sk.setHeaderParam("Content-Type","application/x-www-form-urlencoded");
        sk.testRun("message,status,data");

        ///subscribe/guestTipoffHouse.rest?guestId=21972&tmpHouseId=d909a491f8ebfc18&notExist=1
    }
    @Test
    public void s2tk() throws Exception {
        String sso="wksso=680429ca-0fb1-4ab5-a514-5661a6b9ea29; Path=/;";
        Financial2Sys s= new Financial2Sys("/finance/customerNameCheck.action","test","post");

        s.setHeaderParam("Host","10.0.18.79:8185");
        s.setHeaderParam("Cookie",sso);
        s.setJsonParam("{\"name\":\"zhangsan\",\"id\":16}");
        s.testRun("status,message,data");

        s.getParam("status");	//	0
        s.getParam("message");
        s.getParam("/data");

          s= new Financial2Sys("/finance/customerNameCheck.action","test","post");
        s.setHeaderParam("Host","10.0.18.79:8185");
        s.setHeaderParam("Cookie",sso);
        s.setJsonParam("{\"type\":1.5,\"name\":\"zhangsan\",\"id\":16}");
        s.testRun("status,message,data");
        s.getParam("status");	//	0
        s.getParam("message");
        s.getParam("/data");

          s= new Financial2Sys("/finance/customerNameCheck.action","test","post");
        s.setHeaderParam("Host","10.0.18.79:8185");
        s.setHeaderParam("Cookie",sso);
        s.setJsonParam("{\"type\":1#,\"name\":\"zhangsan\",\"id\":16}");
        s.testRun("status,message,data");
        s.getParam("status");	//	0
        s.getParam("message");
        s.getParam("/data");

          s= new Financial2Sys("/finance/customerNameCheck.action","test","post");
        s.setHeaderParam("Host","10.0.18.79:8185");
        s.setHeaderParam("Cookie",sso);
        s.setJsonParam("{\"type\":0,\"name\":\"zhangsan\",\"id\":16}");
        s.testRun("status,message,data");
        s.getParam("status");	//	0
        s.getParam("message");
        s.getParam("/data");


        s= new Financial2Sys("/finance/customerNameCheck.action","test","post");
        s.setHeaderParam("Host","10.0.18.79:8185");
        s.setHeaderParam("Cookie",sso);
        s.setJsonParam("{\"type\":1,\"name\":\"zhangsan\",\"id\":16}");
        s.testRun("status,message,data");
        s.getParam("status");	//	0
        s.getParam("message");
        s.getParam("/data");


        s= new Financial2Sys("/finance/customerNameCheck.action","test","post");
        s.setHeaderParam("Host","10.0.18.79:8185");
        s.setHeaderParam("Cookie",sso);
        s.setJsonParam("{\"type\":2,\"name\":\"zhangsan\",\"id\":16}");
        s.testRun("status,message,data");
        s.getParam("status");	//	0
        s.getParam("message");
        s.getParam("/data");

        //
        s= new Financial2Sys("/finance/customerNameCheck.action","test","post");
        s.setHeaderParam("Host","10.0.18.79:8185");
        s.setHeaderParam("Cookie",sso);
        s.setJsonParam("{\"type\":3,\"name\":\"zhangsan\",\"id\":16}");
        s.testRun("status,message,data");
        s.getParam("status");	//	0
        s.getParam("message");
        s.getParam("/data");

       // check not	getParam	/data	0
//        script	financial 2 sys	/finance/customerNameCheck.action	test	post
//        setHeaderParam;	Host	10.0.18.79:8185
//        setHeaderParam;	Cookie	wksso=d6fb0894-5319-4d9a-ad0c-8350ae84aa6a
//                setJsonParam;	{"name":"zhangsan","id":16}
//        ensure	testRun;	status,message,data
//        check	getParam	status	0
//        check	getParam	message	失败
//        check not	getParam	/data	0

    }
}
