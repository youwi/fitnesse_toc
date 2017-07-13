package com.qa;

import org.testng.annotations.Test;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/13.
 */
public class CITest {
    @Test
    public void st(){
       new SetGlobalHeader("authorization","3040175684884610adcf8751a7824cdb");
        ConnectServer cs= new ConnectServer	("http://www.lieluobo.testing/api/account/team/byLeader");

        cs.setBody("{\"sbc\"}");
        cs.post();
    }
}
