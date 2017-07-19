package com.qa;


/**
 * IAT @wkzf
 * Created by yu on 2017/7/13.
 */
public class CITest {
   // @Ignore
   // @Test
    public void st() throws Exception {
       new SetGlobalHeader("authorization","e7701df281864c28a58319fa6afce73f");
        ConnectServer cs= new ConnectServer	("http://cw.lieluobo.testing/api/bd/list/developing");
        cs.setParam("id","1");
        cs.setBody("  var out={\n" +
                "    staffId: \"204632\",\n" +
                "   // teamId: 1,\n" +
                "    page: 1,\n" +
                "    size: 10\n" +
                "}\n" +
                "out.bb=\"SS\"","js");
        cs.post();
      assert   cs.jsonContain("\t{name:\t\"客户公司-自动化测试预埋数据\",orgBdName:\t\"员工4-HRBD-自动化测试预埋数据\"}\t");
      //  cs.delete();
    }
}
