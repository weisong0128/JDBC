package com.fiberhome.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class test0625 {
	private static final String DRIVER="com.mysql.jdbc.Driver";
	//private static final String URL="jdbc:mysql://192.168.94.9:3306/407?characterEncoding=utf8";
	private static final String URL = "jdbc:mysql://10.0.200.221/mysql";
	private static final String USER="root";
	private static final String PASSWORD="root";
	
	public static void main(String[] args) {
		//声明jdbc对象
		Connection conn = null;
		PreparedStatement ps = null;	//预编译的SQL语句
		ResultSet rs = null;	//查询的结果集
		//声明变量
		
		try {
			//加载驱动
			Class.forName(DRIVER);
			//获取连接
			conn=DriverManager.getConnection(URL,USER,PASSWORD);
			//创建sql
			String sql="show tables from mysql";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			//执行sql
			rs=ps.executeQuery();
			ResultSetMetaData m = rs.getMetaData();
			int columns = m.getColumnCount();
			//遍历结果
			while(rs.next()){
				for(int i=1;i<=columns;i++){
					System.out.print(rs.getString(i));
					System.out.print("\t\t");
				}
				System.out.println();
			}
					
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
