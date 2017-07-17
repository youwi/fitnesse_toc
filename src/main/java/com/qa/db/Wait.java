package com.qa.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/17.
 */
public class Wait {
    private   int timeout=SetUp.DEFAULT_WAIT_TIMEOUT;
    private String connectionPoolName=SetUp.DEFAULT_CONNECTION_POOL_NAME;

    /**
     * 等到数据可以查询到才返回
     * @param sql
     * @param timeout
     */
    public Wait( String sql, int timeout) {

        sql = sql.replaceAll("\\n", " ");
        sql = sql.replaceAll("\\t", " ");
        sql = sql.replaceAll("<br/>", " ");
        sql = sql.trim();

        this.timeout = timeout;
        this.waitForRowCount(sql);
    }

    private void waitForRowCount(String sql) {
        // execute the db query until rowcount > 0 or timeout
        long start_time = System.nanoTime();



        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        int rowCount = 0;
        int sleepTime = 0; // at start the sleeptime is 0 seconds

        while (rowCount == 0 && ((System.nanoTime() - start_time) / 1000000 < timeout)) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            sleepTime += 1000; // next time we will sleep 1 second longer

            try {
                conn = SetUp.getConnection();

                stmt = conn.createStatement();
                rset = stmt.executeQuery(sql);
                rset.next();

                rowCount = rset.getInt("rowcount");
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
        }

        if (rowCount == 0 && ((System.nanoTime() - start_time) / 1000000 > timeout)) {
            throw new RuntimeException("message:<<wait for rowcount could not be completed within " + timeout + " ms>>");
        }
        System.out.println("wait time for rowcount was " + (System.nanoTime() - start_time) / 1000000 + "ms.");
    }
}
