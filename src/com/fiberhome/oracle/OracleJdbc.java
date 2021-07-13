package com.fiberhome.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class OracleJdbc {
	private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DBURL = "jdbc:oracle:thin:@10.0.200.222:1521:orcl";
	private static final String DBUSER = "SCOTT";
	private static final String PASSWORD = "123456";

	public static void main(String[] args) throws Exception {
		Class.forName(DBDRIVER);
		Connection conn = null;
		conn = DriverManager.getConnection(DBURL, DBUSER, PASSWORD);
		Statement stmt = null;
		stmt = conn.createStatement();
		String sql = "select * from tab";
		//上面查询sql在java中已经执行了，接下来要设法把它展示在console上
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData m = rs.getMetaData();
		int columns = m.getColumnCount();
		while(rs.next()){
			for(int i=1;i <=columns;i++){
				System.out.print(rs.getString(i));
				System.out.print("\t\t");
			}
			System.out.println();
		}
		conn.close();
	}
}
