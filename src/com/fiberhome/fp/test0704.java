package com.fiberhome.fp;

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
public class test0704 {
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
			//获取链接及对象
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			//创建sql
			String sql  = "select * from ghr2 where partition like '123' and NAME = ? and SEX = ? and PROVINCE = ?  limit 100";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			//给占位符赋值
			ps.setString(1, "萧昊乾");
			ps.setString(2, "女");
			ps.setString(3, "江苏省");
			
			//执行sql
			rs = ps.executeQuery(); //返回的rs对象包含了查询得到的数据
			//遍历结果
			ResultSetMetaData metadata = rs.getMetaData();	//得到集合元数据对象
			int column_count = metadata.getColumnCount();//得到统计的列数 
			//展示结果中的 字段名不能放在rs.next()循环里，因为rs.next()有多行数据记录，会循环多次！
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
			e.printStackTrace();
		} catch (SQLException e) {
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
