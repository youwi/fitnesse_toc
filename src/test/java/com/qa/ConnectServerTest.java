package com.qa;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/11.
 */
public class ConnectServerTest {
    @Test
    public void setBody() throws Exception {
        ConnectServer cs=new ConnectServer("empty");
        cs.setBody(" {\n" +
                "staffId: 1,//注释\n" +
                "teamId: 1,\n" +
                "page: 1,\n" +
                "size: 10\n" +
                "}","json");
    }

}