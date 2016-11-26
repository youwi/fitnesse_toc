package com.qa.LegalMgmtSys;



import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;


public class LegalMgmtSys  extends BaseServer{

	public LegalMgmtSys(String URL) {
		super(URL);
	   setBASE_URL(ConfigConstantsTest.NEWBUILDING_OP_SYS_BASE_URL);
	}
	public LegalMgmtSys(String URL,String env) {
		super(URL,env);
		setBASE_URL(ConfigConstantsTest.NEWBUILDING_OP_SYS_BASE_URL);
	}
	public LegalMgmtSys(String URL,String env,String type) {
		super(URL,env,type);
		setBASE_URL(ConfigConstantsTest.NEWBUILDING_OP_SYS_BASE_URL);
	}


}
