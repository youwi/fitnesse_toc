package com.qa.NewCommunityAppServer;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;

/**
 * Created by Gavin-pc on 2017/3/2.
 */
public class NewCommunityAppServer extends BaseServer{
    public NewCommunityAppServer(String URL) {
        super(URL);
        setBASE_URL(ConfigConstantsTest.NEW_COMMUNITY_APP_SERVER_TEST_BASE_URL);

    }
    public NewCommunityAppServer(String URL,String env) {
        super(URL,env);
        setBASE_URL(ConfigConstantsTest.NEW_COMMUNITY_APP_SERVER_TEST_BASE_URL);
    }
}
