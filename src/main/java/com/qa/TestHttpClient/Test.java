package com.qa.TestHttpClient;

import java.io.IOException;

import com.qa.WkzfAppServer.WkzfAppServer;

public class Test {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		System.out.println("java program :" + System.currentTimeMillis());  
//		long a1 = System.currentTimeMillis();
		WkzfAppServer legal = new WkzfAppServer("/house/commentEstateBySubEstateId.rest","test");
		try {
			legal.setHeaderParam("guestId", "19189");
			legal.setHeaderParam("token", "4ce04c27-3447-452c-9a3e-90e9107b7ce1");
			legal.setParam("subEstateId", "100803", "String");	
			legal.setParam("guestId", "19189", "int");
			legal.setParam("comment", "laokehuasdfasdgasdg12345", "String");
			legal.setParam("commentSource", "2", "String");
			legal.setParam("commentImgFile", "E:\\123.png", "fileuploadlist");
//			legal.setParam("subEstateId", "100803", "String");
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
