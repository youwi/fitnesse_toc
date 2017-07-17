package com.qa.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/17.
 */
public class Update {

    private String connectionPoolName=SetUp.DEFAULT_CONNECTION_POOL_NAME;

    public Update(String sql) {

        getDataTable(sql);
    }

    private void Update(String tableName,String values,String condition)    {
        String sql="update "+tableName+" "+values+" "+condition;

        sql = sql.replaceAll("\\n", " ");
        sql = sql.replaceAll("\\t", " ");
        sql = sql.replaceAll("<br/>", " ");
        sql = sql.trim();
        List<List<List<String>>> dataTable = getDataTable(sql);
        new ArrayList<Object>(dataTable);
    }


    protected List<List<List<String>>> getDataTable( String sql) {



        //
        // Now, we can use JDBC DataSource as we normally would.
        //
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        ArrayList<List<List<String>>> dataTable = new ArrayList<List<List<String>>>();

        try {
            conn = SetUp.getConnection();

            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            int rowsUpdated = stmt.executeUpdate(sql);

            ArrayList<List<String>> dataRow = new ArrayList<List<String>>();
            ArrayList<String> dataItem = new ArrayList<String>();
            dataItem.add(String.valueOf("rowsUpdated"));
            dataItem.add(String.valueOf(rowsUpdated));

            dataRow.add(dataItem);
            dataTable.add(dataRow);

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

        return dataTable;
    }
}
