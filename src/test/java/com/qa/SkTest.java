package com.qa;
import com.qa.WkWapServer.WkWapServer;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by yu on 2017/2/14.
 */
public class SkTest {

    @Test
    public void stk() throws Exception {
        WkWapServer sk=   new WkWapServer("/subscribe/guestTipoffHouse.rest?guestId=21972&tmpHouseId=d909a491f8ebfc18&notExist=1");//,"test","get");


     //   sk.setHeaderParam("Content-Type","application/x-www-form-urlencoded");
        sk.testRun("message,status,data");

        ///subscribe/guestTipoffHouse.rest?guestId=21972&tmpHouseId=d909a491f8ebfc18&notExist=1
    }
}
