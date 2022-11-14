package com.ssafy.happyhouse.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBUtil {
	
	public static Connection getConnection() throws SQLException{
		DataSource dataSource=null;
		try {
			Context iCtx = new InitialContext();
			Context ctx = (Context) iCtx.lookup("java:comp/env");
			dataSource = (DataSource) ctx.lookup("jdbc/ssafy");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSource.getConnection();
	}
	public static void close(AutoCloseable c) {
		if(c!=null) {
			try {
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}










