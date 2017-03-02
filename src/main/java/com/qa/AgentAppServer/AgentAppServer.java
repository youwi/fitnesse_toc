package com.qa.AgentAppServer;

import com.qa.BaseServer;
import com.qa.constants.ConfigConstantsTest;

/**
 * Created by Gavin-pc on 2017/3/2.
 */
public class AgentAppServer extends BaseServer{
    public AgentAppServer(String URL) {
        super(URL);
        setBASE_URL(ConfigConstantsTest.AGENT_APP_SERVER_TEST_BASE_URL);

    }
    public AgentAppServer(String URL,String env) {
        super(URL,env);
        setBASE_URL(ConfigConstantsTest.AGENT_APP_SERVER_TEST_BASE_URL);
    }
}
