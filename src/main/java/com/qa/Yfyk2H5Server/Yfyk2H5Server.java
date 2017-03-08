package com.qa.Yfyk2H5Server;


import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;

public class Yfyk2H5Server extends BaseServer{

	public Yfyk2H5Server(String URL) {
		super(URL);
		setBASE_URL(ConfigConstantsTest.YFYK2_H5_BASE_URL);
	}

	public Yfyk2H5Server(String URL, String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.YFYK2_H5_BASE_URL);
	}
	public Yfyk2H5Server(String URL, String env,String type) {
		super(URL,env,type);
		setBASE_URL(ConfigConstantsTest.YFYK2_H5_BASE_URL);
	}


}
