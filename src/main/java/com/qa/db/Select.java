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
public class Select {

    private String connectionPoolName=SetUp.DEFAULT_CONNECTION_POOL_NAME;

    public Select(String sql) {
        List<List<List<String>>> dataTable = getDataTable(sql);
        new ArrayList<Object>(dataTable);
        getDataTable(sql);
    }

    public Select(String tableName, String condition) {
        String sql="select * from "+tableName+" where "+condition;

        sql = sql.replaceAll("\\n", " ");
        sql = sql.replaceAll("\\t", " ");
        sql = sql.replaceAll("<br/>", " ");
        sql = sql.trim();
        getDataTable(sql);
     }




    protected List<List<List<String>>> getDataTable(String sql) {



        //
        // Now, we can use JDBC DataSource as we normally would.
        //
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        ArrayList<List<List<String>>> dataTable = new ArrayList<List<List<String>>>();

        try {
            conn = SetUp.getConnection();

            stmt = conn.createStatement();
            rset = stmt.executeQuery(sql);

            ArrayList<String> columnNames = new ArrayList<String>();

            int numcols = rset.getMetaData().getColumnCount();

            for (int i = 1; i <= numcols; i++) {
                columnNames.add(rset.getMetaData().getColumnName(i));
            }

            while (rset.next()) {
                ArrayList<List<String>> dataRow = new ArrayList<List<String>>();
                for (int i = 1; i <= numcols; i++) {
                    ArrayList<String> dataItem = new ArrayList<String>();
                    dataItem.add(String.valueOf(columnNames.get(i - 1)));
                    dataItem.add(String.valueOf(rset.getString(i)));
                    dataRow.add(dataItem);
                }
                dataTable.add(dataRow);
            }
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
