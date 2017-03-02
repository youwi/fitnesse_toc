package com.qa.HouseAppServer;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;

/**
 * Created by Gavin-pc on 2017/3/2.
 */
public class HouseAppServer extends BaseServer {
    public HouseAppServer(String URL) {
        super(URL);
        setBASE_URL(ConfigConstantsTest.HOUSE_APP_SERVER_TEST_BASE_URL);

    }
    public HouseAppServer(String URL,String env) {
        super(URL,env);
        setBASE_URL(ConfigConstantsTest.HOUSE_APP_SERVER_TEST_BASE_URL);
    }
}
