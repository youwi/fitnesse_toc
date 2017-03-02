package com.qa.RateSys;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;

/**
 * Created by Gavin-pc on 2017/3/2.
 */
public class RateSys extends BaseServer {
    public RateSys(String URL) {
        super(URL);
        setBASE_URL(ConfigConstantsTest.RATE_SYS_SERVER_TEST_BASE_URL);

    }
    public RateSys(String URL,String env) {
        super(URL,env);
        setBASE_URL(ConfigConstantsTest.RATE_SYS_SERVER_TEST_BASE_URL);
    }
}
