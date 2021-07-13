package com.fiberhome.fp;

import java.sql.*;

public class FpJdbc {

	private static final String DBDRIVER = "org.apache.hive.jdbc.HiveDriver";
	private static final String DBURL = "jdbc:hive2://27.1.23.38:10009/default";
	private static final String USER = "fenghuo";
	private static final String PASSWORD = "fiberhome@v2";

	public static void main(String[] args) {
		try {
			Class.forName(DBDRIVER);
			Connection conn=null;
			conn=DriverManager.getConnection(DBURL, USER, PASSWORD);
			Statement stmt=null;
			stmt =conn.createStatement();
			String sql= "show tables";
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData m =rs.getMetaData();
			int columns = m.getColumnCount();
			while(rs.next()){
				for(int i=1;i<columns;i++){
					System.out.print(rs.getString(i));
					System.out.print("\t\t");
				}
				System.out.println();
			}
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
