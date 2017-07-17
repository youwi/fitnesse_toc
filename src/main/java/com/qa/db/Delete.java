package com.qa.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/17.
 */
public class Delete {
    private String connectionPoolName=SetUp.DEFAULT_CONNECTION_POOL_NAME;
    public  Delete(String tableName,String where,String condition){
        String sql="delete from "+tableName+" where "+condition;
        sql = sql.replaceAll("\\n", " ");
        sql = sql.replaceAll("\\t", " ");
        sql = sql.replaceAll("<br/>", " ");
        sql = sql.trim();
        getResult(sql);
    }
    public  Delete(String sql){
        sql = sql.replaceAll("\\n", " ");
        sql = sql.replaceAll("\\t", " ");
        sql = sql.replaceAll("<br/>", " ");
        sql = sql.trim();
        getResult(sql);
    }

    protected int getResult(String sql) {


        int i = 0;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            conn = SetUp.getConnection();
            conn.setAutoCommit(true);
            stmt = conn.createStatement();

            i = stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (rset != null)
                    rset.close();
            } catch (Exception e) {
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
            }
        }

        return i;
    }
}
