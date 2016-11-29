package com.qa;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.qa.HouseholderAppServer.HouseholderAppServer;
import com.qa.exampleAppServer.ExampleAppServer;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by yus on 2016/11/26.
 */
public class BaseServerTest {
    @Test
    public void tt() throws Exception {
        System.out.println(this.getClass().getName());
        System.out.println(this.getClass().getSimpleName());
    }
    @Test
    public void t2222t() throws Exception {
      String ou=  BaseServer.delHTMLTag("<a href=\"https://yun2.test.wkzf\">https://yun2.test.wkzf</a>/legalmgmt2/contractManagement/signJusticeGroup/getGroupList.action</td>\n");
        System.out.println(ou);
    }



    @Test
    public void TestFor_GET_BASE_URL() throws Exception {
        ExampleAppServer ex=   new ExampleAppServer("10.0.012");
        System.out.println("BASEURL_::"+ex.getBASE_URL());

        ExampleAppServer ex1=   new ExampleAppServer("10.0.012","test");
        System.out.println("BASEURL_::"+ex1.getBASE_URL());

        ExampleAppServer ex2=   new ExampleAppServer("10.0.012","sim");
        System.out.println("BASEURL_::"+ex2.getBASE_URL());

        ExampleAppServer ex3=   new ExampleAppServer("10.0.012","prod");
        System.out.println("BASEURL_::"+ex3.getBASE_URL());
    }

    @Test
    public void testFitn() throws Exception {

        HouseholderAppServer s=new HouseholderAppServer("/dicArea/getCityList.rest");
        s.testRun("message,status,data");
        s.getParam("status" );

        HouseholderAppServer s2=new HouseholderAppServer("/dicArea/getCityList.rest");
        s2.setParam("searchStr","天山","String");
        s2.testRun("message,status,data");
        s2.getParam("status" );

      /*          householder app server	/dicArea/getCityList.rest
        ensure	testRun;
        check	getParam	status	[null] expected [1]*/

     /*   setParam;	cityid	43	int
        setParam;	searchStr	天山	String
        ensure	testRun;	message,status,data/*/
    }



}