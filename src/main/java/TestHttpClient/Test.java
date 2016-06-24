package main.java.TestHttpClient;

import java.io.IOException;

import main.java.HouseholderAppServer.HouseholderAppServer;
import main.java.LegalAppServer.LegalAppServer;
import main.java.NewBuildingAppServer.NewBuildingAppServer;
import main.java.WkzfAppServer.WkzfAppServer;
import main.java.YfykAppServer.YfykAppServer;

public class Test {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("java program :" + System.currentTimeMillis());  
//		long a1 = System.currentTimeMillis();
		WkzfAppServer legal = new WkzfAppServer("/personConter/collect.rest","test");
		try {
			legal.setParam("type", "", "int");
			legal.setParam("hostMobile", "13816974762", "string");	
			String a = "{\"hostMobile\":\"13816974762\",\"type\":\"\"}";
//			legal.setJsonParam(a);
//			yfyk.setParam("houseId", "84370", "string");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		long b = System.currentTimeMillis() - a1;
//		System.out.println("java program b:" + b);  
		System.out.println("java program b:" + legal.checkResponseTime("200")); 
		legal.testRun("message");
		
	}
	
	

//test
}
