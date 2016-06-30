package com.qa.TestHttpClient;

import java.io.IOException;

import com.qa.WkzfAppServer.WkzfAppServer;

public class Test {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		System.out.println("java program :" + System.currentTimeMillis());  
//		long a1 = System.currentTimeMillis();
		WkzfAppServer legal = new WkzfAppServer("/agent/findAgentByIdOrPhoneNum.rest","test");
		try {
			legal.setHeaderParam("guestId", "15224");
			legal.setHeaderParam("token", "07301ae3-1fd0-48b8-805c-7ba3edf90297");
			legal.setParam("guestId", "15224", "number");	
			legal.setParam("agentId", "13810", "number");
			legal.setParam("cityId", "43", "number");
			String a = "{\"hostMobile\":\"13816974762\",\"type\":\"\"}";
//			legal.setJsonParam(a);
//			yfyk.setParam("houseId", "84370", "string");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		long b = System.currentTimeMillis() - a1;
//		System.out.println("java program b:" + b);  
//		System.out.println("java program b:" + legal.checkResponseTime("200")); 
		legal.testRun("status,message,data");
		
//		System.out.println("test:"+legal.getParam("status"));  
//		System.out.println(legal.getParam("message"));  
		System.out.println(legal.getParam("/data/agent/id"));  
	}
	
	

//test
}
