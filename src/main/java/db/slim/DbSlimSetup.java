/*******************************************************************************
 * The MIT License
 * 
 * Copyright (c) 2010, Mark S.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 ******************************************************************************/
package main.java.db.slim;

import main.java.constants.ConfigConstants;
import main.java.db.services.DbConnectionFactory;

public class DbSlimSetup {

	public static String DEFAULT_CONNECTION_POOL_NAME = "default";
	public static int DEFAULT_CONNECTION_POOL_MIN_IDLE = 1;
	public static int DEFAULT_CONNECTION_POOL_MAX_AXTIVE = 5;
	public static int DEFAULT_WAIT_TIMEOUT = 45000;

	public DbSlimSetup() throws Exception {

		DbConnectionFactory.getDataSource(DEFAULT_CONNECTION_POOL_NAME, ConfigConstants.JDBC_DRIVER_CLASS, ConfigConstants.JDBC_CONNECT_URL, ConfigConstants.JDBC_USERNAME, ConfigConstants.JDBC_PASSWORD, DEFAULT_CONNECTION_POOL_MIN_IDLE, DEFAULT_CONNECTION_POOL_MAX_AXTIVE);
	}
	
	public DbSlimSetup(String jdbcDriverClass, String connectURI, String username, String password) throws Exception {

		DbConnectionFactory.getDataSource(DEFAULT_CONNECTION_POOL_NAME, jdbcDriverClass, connectURI, username, password, DEFAULT_CONNECTION_POOL_MIN_IDLE, DEFAULT_CONNECTION_POOL_MAX_AXTIVE);
	}

	public DbSlimSetup(String jdbcDriverClass, String connectURI, String username, String password, int minIdle, int maxActive) throws Exception {

		DbConnectionFactory.getDataSource(DEFAULT_CONNECTION_POOL_NAME, jdbcDriverClass, connectURI, username, password, minIdle, maxActive);

	}

	public DbSlimSetup(String jdbcDriverClass, String connectionPoolName, String connectURI, String username, String password, int minIdle, int maxActive) throws Exception {

		DbConnectionFactory.getDataSource(connectionPoolName, jdbcDriverClass, connectURI, username, password, minIdle, maxActive);
	}
}
