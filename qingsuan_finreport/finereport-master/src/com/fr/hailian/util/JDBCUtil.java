package com.fr.hailian.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fr.hailian.core.Constants;
/***
 * JDBC工具类
 * @author Tom
 *
 */
public class JDBCUtil {
	
	/***
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection(Constants.JDBC_HUB,
				Constants.JDBC_USER_HUB, Constants.JDBC_USER_PWD_HUB);
		return conn;
	}
	
	/***
	 * 关闭连接
	 * @param conn
	 * @throws SQLException
	 */
	public static void closeConnection(Connection conn) throws SQLException{
		conn.close();
	}

}
