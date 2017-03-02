package com.qa.OpMgmt2Sys;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;

/**
 * Created by Gavin-pc on 2017/3/2.
 */
public class OpMgmt2Sys extends BaseServer {
    public OpMgmt2Sys(String URL) {
        super(URL);
        setBASE_URL(ConfigConstantsTest.OPMGMT2_SYS_SERVER_TEST_BASE_URL);
    }
    public OpMgmt2Sys(String URL,String env) {
        super(URL,env);
        setBASE_URL(ConfigConstantsTest.OPMGMT2_SYS_SERVER_TEST_BASE_URL);
    }
}
