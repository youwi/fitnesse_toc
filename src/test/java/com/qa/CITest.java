package com.qa;

import org.testng.annotations.Test;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/13.
 */
public class CITest {
    @Test
    public void st() throws Exception {
       new SetGlobalHeader("authorization","1fcca387ce0745f6a61c3d8b941d30b5");
        ConnectServer cs= new ConnectServer	("http://cw.lieluobo.testing/api/todo/{id}");
        cs.setParam("id","1");
        cs.delete();
    }
}
