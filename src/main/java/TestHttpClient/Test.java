package main.java.TestHttpClient;

import java.io.IOException;

import main.java.WkzfAppServer.WkzfAppServer;
import main.java.YfykAppServer.YfykAppServer;

public class Test {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		YfykAppServer yfyk = new YfykAppServer("/houseres/agenthouselist");
		try {
			yfyk.setParam("start", "0", "string");
			yfyk.setParam("size", "20", "string");
			yfyk.setParam("agentHouseStateStr", "1", "string");
			yfyk.setParam("agentId", "1180", "string");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		yfyk.testRun("message");
	}
}
