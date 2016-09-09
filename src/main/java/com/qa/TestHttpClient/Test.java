package com.qa.TestHttpClient;

import java.io.IOException;

import com.qa.NewBuildingOpSys.NewBuildingOpSys;

public class Test {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		System.out.println("java program :" + System.currentTimeMillis());  
//		long a1 = System.currentTimeMillis();
		NewBuildingOpSys legal = new NewBuildingOpSys("/buildingDynamics/update.action","test");
		try {
//			legal.setHeaderParam("guestId", "22294");
//			legal.setHeaderParam("token", "54fe407e-636a-4e03-973f-54fc0235d705");
//			legal.setHeaderParam("version", "2.3");
//			legal.setHeaderParam("os", "android");
//			legal.setParam("houseId", "66139", "int");	
			//legal.setParam("guestId", "1895", "int");
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
