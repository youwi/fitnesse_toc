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
		HouseholderAppServer legal = new HouseholderAppServer("/release/getHouseFdListInfo.rest");
		try {
			legal.setParam("type", "", "int");
			legal.setParam("hostMobile", "13816974762", "string");		
//			yfyk.setParam("houseId", "84370", "string");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		legal.testRun("message");
	}
	
	

//test
}
