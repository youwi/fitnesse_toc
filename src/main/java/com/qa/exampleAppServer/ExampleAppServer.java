package com.qa.exampleAppServer;

import com.qa.BaseServer;

/**
 * Created by yus on 2016/11/26.
 */
public class ExampleAppServer  extends BaseServer{

    public ExampleAppServer(String URL){
        super(URL);
    }
    public ExampleAppServer(String URL,String env){
        super(URL,env);
    }
}
