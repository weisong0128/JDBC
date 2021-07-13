package com.fiberhome.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * jdbc一般步骤
 * 		//声明对象
		//声明变量
		//加载驱动
		//获取链接及对象
		//创建sql
		//创建sql命令对象
		//给占位符赋值
		//执行sql
		//遍历结果
		//关闭资源
		//返回结果
 * @author Administrator
 *
 */
public class test0703 {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://172.16.108.6:3306/collie?characterEncoding=utf8";
	private static final String USER = "collie";
	private static final String PASSWORD = "collie";
	
	public static void main(String[] args) {
		//声明对象(最后要释放资源)
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//声明变量
		try {
			//加载驱动
			Class.forName(DRIVER);
			//获取链接及对象
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			//创建sql
			String sql  = "show databases";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			//给占位符赋值
			//执行sql
			rs = ps.executeQuery(); //返回的rs对象包含了查询得到的数据
			//遍历结果
			ResultSetMetaData metadata = rs.getMetaData();	//得到集合元数据对象
			int column_count = metadata.getColumnCount();//得到统计的列数 
			//展示字段名不能放在rs.next()循环里，因为rs.next()有多行数据记录，会循环多次！
			for(int i = 1 ; i < (column_count + 1) ;i++){
				System.out.print(metadata.getColumnName(i));
				System.out.print("\t");
			}
			System.out.println(""); //换行作用
			while(rs.next()){
				for(int i = 1 ; i < column_count + 1 ; i++){
					System.out.print(rs.getString(i));
					System.out.print("\t");
				}
				System.out.println("");
			} 
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { 
			//关闭资源
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
		
		//返回结果
		
		
		
	}
}
