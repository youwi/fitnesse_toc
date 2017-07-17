package com.qa.db;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/17.
 */
public class SetUp {

    public static String DEFAULT_CONNECTION_POOL_NAME = "default";
    public static int DEFAULT_WAIT_TIMEOUT = 45000;
    static Map<String, Connection> mapConnection = new HashMap();


    public  SetUp(String jdbcDriverClass, String connectURI, String username, String password) {
        try {
            String key = jdbcDriverClass + connectURI + username + password;
            if (mapConnection.get(key) != null) {
                Connection conn = mapConnection.get(key);
                if (!conn.isClosed()) {
                    return ;//mapConnection.get(key);
                }
            }
            Class.forName(jdbcDriverClass);

            Connection conn = DriverManager.getConnection(connectURI, username, password);
            mapConnection.put(key, conn);
            mapConnection.put(DEFAULT_CONNECTION_POOL_NAME, conn);
            return ;
        } catch (ClassNotFoundException e) {
            System.err.println("找不到驱动类！" + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL连接异常！" + e.getMessage());
        }
        return ;
    }

    public static Connection getConnection(){
        return   mapConnection.get(DEFAULT_CONNECTION_POOL_NAME);
    }

    public static int runStandSql(String sql){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        int _count=0;

        try {
            conn = SetUp.getConnection();

            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            _count = stmt.executeUpdate(sql);
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

        }

        return _count;
    }
}
