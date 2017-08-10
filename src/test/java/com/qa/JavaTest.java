package com.qa;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * fitnesse_toc
 * Created by yu on 2017/8/10.
 */
public class JavaTest {
    @Test
    public void run() throws Exception {
      new   Java().run("System.out.print('A')");
        new   Java("System.out.print('A')");
    }

}