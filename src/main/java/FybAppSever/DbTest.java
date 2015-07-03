package FybAppSever;

import java.util.List;

import db.slim.DbSlimSelectQuery;
import db.slim.DbSlimSetup;

public class DbTest {

    public static void main(String args[]) throws Throwable {

        DbSlimSetup set = new DbSlimSetup(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://192.168.1.14:3306/hims_test?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false",
                "root", "qa123");

        DbSlimSelectQuery sq = new DbSlimSelectQuery(
                "SELECT * FROM house WHERE houseId = 1;");

        List<Object> a = sq.query();

        System.out.println(a.get(0));
    }
}
