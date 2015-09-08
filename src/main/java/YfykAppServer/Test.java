package main.java.YfykAppServer;

import java.io.IOException;

import main.java.JrspAppServer.JrspAppServer;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		YfykAppServer yfyk = new YfykAppServer("/houseres/uploadPic");
		try {
			yfyk.setParam("agentId", "1397", "string");
			yfyk.setParam("fileBytes", "D:\\22.jpg", "fileupload");
			yfyk.setParam("houseId", "144772", "string");
			yfyk.setParam("imgType", "1", "string");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   try {
		   yfyk.testRun("message");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	

}
