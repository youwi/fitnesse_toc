package com.qa;

import com.qa.HouseholderAppServer.HouseholderAppServer;
import com.qa.LegalMgmtSys.LegalMgmtSys;
import com.qa.exampleAppServer.ExampleAppServer;
import org.junit.Test;

/**
 * Created by yus on 2016/11/26.
 */
public class BaseServerTest2 {

   // @Test
    public void testFun() throws Exception {
        HouseholderAppServer s2=new HouseholderAppServer("/dicArea/getCityList.rest");
        s2.setParam("searchStr","天山","String");
        s2.testRun("message,status,data");
        s2.getParam("status" );


        LegalMgmtSys s=new LegalMgmtSys("/dicArea/getCityList.rest");
        s.setHeaderParam("HOST","yun2.test.wkzf");
        s.setHeaderParam("Cookie","wksso=95add403-8d45-4cb3-b5fc-5d5678375183; Path=/; HttpOnly");
        s.setParam("cityId","43","int");
        s.testRun("message,status,data");
        s.getParam("status" );



      /*          householder app server	/dicArea/getCityList.rest
        ensure	testRun;
        check	getParam	status	[null] expected [1]*/

     /*   setParam;	cityid	43	int
        setParam;	searchStr	天山	String
        ensure	testRun;	message,status,data/*/
    }

/*    script	legalMgmtSys	/legalmgmt2/contractManagement/signJusticeGroup/getGroupList.action	test	post
            setHeaderParam;	Host	yun2.test.wkzf
            setHeaderParam;	Cookie	wksso=c713a8e3-cddd-4c4e-be8e-ae8c2b330144
            setParam;	cityId	43	int
    ensure	testRun;	status,message,data
    check	getParam	status	1
    check	getParam	message*/



}