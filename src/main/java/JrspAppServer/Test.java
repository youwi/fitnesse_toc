package main.java.JrspAppServer;

import java.io.IOException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JrspAppServer jrsp = new JrspAppServer("/housePublishRecord/getPublishSummary.rest");
		try {
			jrsp.setHeaderParam("Content-Type", "application/json;charset=UTF-8");
			jrsp.setHeaderParam("os", "monitor");
			jrsp.setHeaderParam("User-ID", "0");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   try {
		jrsp.testRun("message");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
