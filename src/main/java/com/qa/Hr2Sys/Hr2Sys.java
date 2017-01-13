package com.qa.Hr2Sys;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;

/**
 * Created by Gavin-pc on 2017/1/11.
 */
public class Hr2Sys extends BaseServer {
    public Hr2Sys(String URL) {
        super(URL);
        setBASE_URL(ConfigConstantsTest.HR2_SYS_SERVER_BASE_URL);
    }

    public Hr2Sys(String URL,String env) {
        super(URL,env);
        setBASE_URL(ConfigConstantsTest.HR2_SYS_SERVER_BASE_URL);
    }

    public Hr2Sys(String URL,String env,String type) {
        super(URL,env,type);
        setBASE_URL(ConfigConstantsTest.HR2_SYS_SERVER_BASE_URL);
    }
}
