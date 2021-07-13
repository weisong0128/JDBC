package com.fiberhome.fp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test_1105 {
	private static final String DRIVER = "org.apache.hive.jdbc.HiveDriver";
	private static final String URL = "jdbc:hive2://172.16.108.6:10009/default?characterEncoding=utf8";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	
	public static void main(String[] args) {
		//声明对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//声明变量
		
		try {
			//加载驱动
			Class.forName(DRIVER);
			//获取连接及对象
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			//创建sql
			String sql = "select * from ghr3 where partition like ? and PROVINCE = ? limit 10";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			//给占位符赋值
			ps.setObject(1, "123");
			ps.setObject(2, "河南省");
			//执行sql
			long startTime = System.currentTimeMillis();
			rs = ps.executeQuery();
			//遍历结果
			while(rs.next()){
				for(int i = 1; i < rs.getMetaData().getColumnCount(); i++){
					System.out.println(rs.getObject(i));
					System.out.println("\t");
				}
				System.out.println();
			}
			long endTime = System.currentTimeMillis();
			System.out.println("sql查询耗时为:" + (endTime - startTime) + "ms");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//释放资源
			try {
				if(null!=rs){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(null!=ps){
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(null!=conn){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		//返回结果(return 变量)
		
	}
	
}
