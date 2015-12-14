package main.java.TestHttpClient;

import java.io.IOException;

import main.java.LegalAppServer.LegalAppServer;
import main.java.WkzfAppServer.WkzfAppServer;
import main.java.YfykAppServer.YfykAppServer;

public class Test {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LegalAppServer legal = new LegalAppServer("/law/getBusinessDetail.action");
		try {
			legal.setParam("businessId", "SHS201512080003", "string");
			//legal.setParam("uId", "87", "string");
//			yfyk.setParam("houseId", "84370", "string");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		legal.testRun("message");
	}
	
	
}
