package main.java.TestHttpClient;

import java.io.IOException;

import main.java.WkzfAppServer.WkzfAppServer;
import main.java.YfykAppServer.YfykAppServer;

public class Test {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WkzfAppServer yfyk = new WkzfAppServer("/house/queryHouseById.rest");
		try {
			yfyk.setParam("telPhoneNum", "telPhoneNum", "string");
			yfyk.setParam("houseId", "84370", "string");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		yfyk.testRun("message");
	}
}
