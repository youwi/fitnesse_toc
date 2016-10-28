package com.qa.TestHttpClient;

import java.io.IOException;

import com.qa.NewBuildingOpSys.NewBuildingOpSys;

public class Test {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		System.out.println("java program :" + System.currentTimeMillis());  
//		long a1 = System.currentTimeMillis();
		NewBuildingOpSys legal = new NewBuildingOpSys("/xf/customer/list.action?cityId=43","test","get");
		try {
			legal.setHeaderParam("host", "yun2.test.wkzf");
			legal.setHeaderParam("Cookie", "jsessionid=D968711BF3F860170B93231D358F343F");
//			legal.setParam("subEstateId", "100803", "String");	
//			legal.setParam("guestId", "19189", "int");
//			legal.setParam("comment", "laokehuasdfasdgasdg12345", "String");
//			legal.setParam("commentSource", "2", "String");
//			legal.setParam("commentImgFile", "E:\\123.png", "fileuploadlist");
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
