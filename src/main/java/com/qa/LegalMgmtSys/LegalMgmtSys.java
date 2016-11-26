package com.qa.LegalMgmtSys;



import com.qa.BaseServer;
import com.qa.constants.ConfigConstants;


public class LegalMgmtSys  extends BaseServer{

	public LegalMgmtSys(String URL) {
		super(URL);
	   BASE_URL=ConfigConstants.NEWBUILDING_OP_SYS_TEST_BASE_URL;
	}
	public LegalMgmtSys(String URL,String env) {
		super(URL,env);
		BASE_URL=ConfigConstants.NEWBUILDING_OP_SYS_TEST_BASE_URL;
	}
	public LegalMgmtSys(String URL,String env,String type) {
		super(URL,env,type);
		BASE_URL=ConfigConstants.NEWBUILDING_OP_SYS_TEST_BASE_URL;
	}


}
