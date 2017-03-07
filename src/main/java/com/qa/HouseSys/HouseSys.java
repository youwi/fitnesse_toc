package com.qa.HouseSys;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;

/**
 * Created by Gavin-pc on 2017/3/2.
 */
public class HouseSys extends BaseServer {
    public HouseSys(String URL) {
        super(URL);
        setBASE_URL(ConfigConstantsTest.HOUSE_SYS_SERVER_TEST_BASE_URL);

    }
    public HouseSys(String URL,String env) {
        super(URL,env);
        setBASE_URL(ConfigConstantsTest.HOUSE_SYS_SERVER_TEST_BASE_URL);
    }
    public HouseSys(String URL,String env,String type) {
        super(URL,env,type);
        setBASE_URL(ConfigConstantsTest.HOUSE_SYS_SERVER_TEST_BASE_URL);
    }
}
