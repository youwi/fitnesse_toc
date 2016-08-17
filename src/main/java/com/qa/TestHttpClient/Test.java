package com.qa.TestHttpClient;

import java.io.IOException;

import com.qa.WkzfAppServer.WkzfAppServer;

public class Test {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		System.out.println("java program :" + System.currentTimeMillis());  
//		long a1 = System.currentTimeMillis();
		WkzfAppServer legal = new WkzfAppServer("/register/loginOut.rest","test");
		try {
			legal.setHeaderParam("guestId", "24296");
			legal.setHeaderParam("token", "a9b85b4d-cf80-4e5c-bcc9-fa3e86fe9297");
			legal.setHeaderParam("deviceId", "6C53D072E00307CC54BC4A9555DC7658");
			legal.setHeaderParam("version", "2.3");
			legal.setParam("guestId", "24296", "string");	
			legal.setParam("guestTelPhoneNum", "18588238644", "number");
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
		legal.testRun("status,message,data");
		
//		System.out.println("test:"+legal.getParam("status"));  
//		System.out.println(legal.getParam("message"));  
//		System.out.println(legal.getParam("/data/agent/id"));  
	}
	
	

//test
}
