package com.qa;
import com.qa.Financial2Sys.Financial2Sys;
import com.qa.WkWapServer.WkWapServer;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by yu on 2017/2/14.
 */
public class SkTest {

    @Test
    public void stk() throws Exception {
        WkWapServer sk=   new WkWapServer("/subscribe/guestTipoffHouse.rest?guestId=21972&tmpHouseId=d909a491f8ebfc18&notExist=1");//,"test","get");


     //   sk.setHeaderParam("Content-Type","application/x-www-form-urlencoded");
        sk.testRun("message,status,data");

        ///subscribe/guestTipoffHouse.rest?guestId=21972&tmpHouseId=d909a491f8ebfc18&notExist=1
    }
    @Test
    public void s2tk() throws Exception {
        Financial2Sys s= new Financial2Sys("/finance/customerNameCheck.action","test","post");

        s.setHeaderParam("Host","10.0.18.79:8185");
        s.setHeaderParam("Cookie","wksso=d6fb0894-5319-4d9a-ad0c-8350ae84aa6a");
        s.setJsonParam("{\"type\":0,\"name\":\"zhangsan\",\"id\":16}\n");
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
