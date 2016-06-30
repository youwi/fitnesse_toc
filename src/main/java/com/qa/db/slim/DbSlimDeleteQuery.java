package com.qa.db.slim;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import com.qa.db.services.DbConnectionFactory;

public class DbSlimDeleteQuery {
	private String connectionPoolName;
	private String sql;

	public DbSlimDeleteQuery(String sql) {
		this(DbSlimSetup.DEFAULT_CONNECTION_POOL_NAME, sql);
	}

	public DbSlimDeleteQuery(String connectionPoolName, String sql) {

		this.connectionPoolName = connectionPoolName;

		sql = sql.replaceAll("\\n", " ");
		sql = sql.replaceAll("\\t", " ");
		sql = sql.replaceAll("<br/>", " ");
		sql = sql.trim();

		this.sql = sql;
	}

	public void table(List<List<String>> table) {
		// optional function
	}

	public int delete() {
		int i = getResult();
		return i;
	}

	protected int getResult() {

		DataSource dataSource = DbConnectionFactory.getDataSource(connectionPoolName);

		//
		// Now, we can use JDBC DataSource as we normally would.
		//
		int i = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		try {
			conn = dataSource.getConnection();
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
