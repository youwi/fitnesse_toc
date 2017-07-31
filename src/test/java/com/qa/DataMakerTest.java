package com.qa;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * fitnesse_toc
 * Created by yu on 2017/7/25.
 * 测试数据构造可以使用专业的工具,也可以使用开源的工具,
 * 还可以跟数据库结合,这里只显示一些简单的方法.
 */
public class DataMakerTest {
    @Test
    public void testUuid1() throws Exception {
    }

    @Test
    public void testTid2() throws Exception {
    }

    @Test
    public void testTid3() throws Exception {
    }

    @Test
    public void testIntArray1() throws Exception {
    }

    @Test
    public void testIntArray2() throws Exception {
    }

    @Test
    public void testPend() throws Exception {
    }

    @Test
    public void testPendArray() throws Exception {
    }

    @Test
    public void testUuid() throws Exception {
    }

    @Test
    public void testTid() throws Exception {
    }

    @Test
    public void testTid1() throws Exception {
        DataMaker.telephone();
    }

    @Test
    public void testIntArray() throws Exception {
        System.out.println( DataMaker.intArray());
    }

}