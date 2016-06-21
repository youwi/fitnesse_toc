package main.java.TestHttpClient;

import java.io.IOException;

import main.java.LegalAppServer.LegalAppServer;
import main.java.NewBuildingAppServer.NewBuildingAppServer;
import main.java.WkzfAppServer.WkzfAppServer;
import main.java.YfykAppServer.YfykAppServer;

public class Test {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		NewBuildingAppServer legal = new NewBuildingAppServer("/newHouse/getHousePrice.rest");
		try {
			legal.setParam("id", "43", "int");
			legal.setParam("type", "4", "int");
			legal.setParam("subEstateName", "陕西北路175弄", "string");
			legal.setParam("priceDesc", "30000元", "string");
//			yfyk.setParam("houseId", "84370", "string");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		legal.testRun("message");
	}
	
	

//test
}
