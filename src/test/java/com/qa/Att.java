package com.qa;

import com.qa.db.Delete;
import com.qa.db.InsertIgnore;
import com.qa.db.SetUp;
import org.junit.Before;
import org.junit.Test;

/**
 * fitnesse_toc
 * Created by yu on 2017/12/26.
 * 红包签到活动专项测试
 */
public class Att {

    public String userId="204705";

   // @Before
    public void login(){

        //204705,19900020002,HR自动化2002,
        new SetUp("com.mysql.jdbc.Driver",	"jdbc:mysql://172.16.52.81:3306/testing?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false","testing",	"haolie123");

        new Store("headers","{author:\"haolie\"}");

        ConnectServer cs = new ConnectServer("http://wwww.lieluobo.testing/api/biz/account/xauth");
        cs.setBody("{\"mobile\":\"19900020002\",\"password\":\"123456\",\"channel\":\"func\"}");
        cs.post();

        new Store("headers","{author:\"haolie\",authorization:\""+cs.jsonValue("body")+"\",channel:\"hr\"}");
    }
    @Test
    public void Test01At188(){

        //签到
        ConnectServer cs = new ConnectServer("http://hr.lieluobo.testing/api/activity/attendance");
        cs.get();
       // cs.jsonValue()
    }

    public void doSign(){
        ConnectServer cs = new ConnectServer("http://hr.lieluobo.testing/api/activity/attendance");
        cs.get();
    }

    public void cleanData(){
        new Delete("delete from activity_attendance where account_id=204705");//
    }
    @Test
    public void jj(){
        fackDataBy(2);
    }
    public void fackDataBy(int dayIndex){
        String time=DataMaker.today(-dayIndex);
        String sql="INSERT INTO activity_attendance ( account_id, recent_attendance_time, continuous_attendance_days, created_by, updated_by, created_at, updated_at) VALUES ( "+userId+", '"+time+"', 1, "+userId+", "+userId+", '"+time+"', '"+time+"');";
        System.out.printf(sql);
        //new InsertIgnore("INSERT INTO activity_attendance ( account_id, recent_attendance_time, continuous_attendance_days, created_by, updated_by, created_at, updated_at) VALUES ( 204705, '"+time+"', 1, 204654, 204654, '"+time+"', '"+time+"');");
    }
}
