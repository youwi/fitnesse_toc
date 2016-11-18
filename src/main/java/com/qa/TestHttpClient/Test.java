package com.qa.TestHttpClient;

import java.io.IOException;

import com.qa.NewBuildingOpSys.NewBuildingOpSys;

public class Test {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		System.out.println("java program :" + System.currentTimeMillis());  
//		long a1 = System.currentTimeMillis();
		NewBuildingOpSys legal = new NewBuildingOpSys("signList/getSignList.action");
		try {
			legal.setHeaderParam("host", "yun2.test.wkzf");
			legal.setHeaderParam("Cookie", "JSESSIONID=D968711BF3F860170B93231D358F343F;wksso=5208dd35-5bc9-4a23-a4e0-7dfc50ec91e2");
			legal.setParam("pageId", "1", "int");
			legal.setParam("pageSize", "10", "int");
			legal.setParam("cityId", "0", "int");
			legal.setParam("subEstateId", "0", "int");
			legal.setParam("cusName", "小白", "string");
			legal.setParam("signStatus", "-1", "int");
//			String a = "{\"hostMobile\":\"13816974762\",\"type\":\"\"}";
//			legal.setJsonParam(a);
//			yfyk.setParam("houseId", "84370", "string");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		long b = System.currentTimeMillis() - a1;
//		System.out.println("java program b:" + b);
//		System.out.println("java program b:" + legal.checkResponseTime("200"));
		System.out.println("start test>>>>>>>>>>>>>>>.");
		legal.testRun("status,message,data");
		System.out.println("end test>>>>>>>>>>>>>>>.");
		
//		System.out.println("test:"+legal.getParam("status"));  
//		System.out.println(legal.getParam("message"));  
//		System.out.println(legal.getParam("/data/agent/id"));  
	}



	//test
}
