package com.qa;
import org.junit.Test;

/**
 * Created by yu on 2017/1/16.
 */
public class GetWkSSOTest {
    //getWKSSO;	13162108888	111111	https://yun2.test.wkzf/nbos/promt/osnhlist.action

    @Test
    public void stcc() throws Exception {

    String s=       new SSOLogin("/login","test","post").getWKSSO("13162108888","111111","https://yun2.test.wkzf/nbos/promt/osnhlist.action");

        System.out.println(s);
    }
}
