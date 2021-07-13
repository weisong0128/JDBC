package com.fiberhome.mysql;

import java.sql.*;


public class MysqlJdbc {

	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://10.0.200.221:3306/mysql";
	private static final String DBUSER = "root";
	private static final String PASSWORD = "root";
	public static void main(String[] args) {
		try {
			Class.forName(DBDRIVER);
			Connection conn = null;
			conn = DriverManager.getConnection(DBURL, DBUSER, PASSWORD);
			Statement stmt = null;
			stmt = conn.createStatement();
			String sql = "show databases";
			//�����ѯsql��java���Ѿ�ִ���ˣ�������Ҫ�跨����չʾ��console��
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

}
