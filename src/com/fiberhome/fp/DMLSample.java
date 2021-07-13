package com.fiberhome.fp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 增查删改查询汇总
 * @author 魏松
 *
 */
public class DMLSample {
	static Properties pros = null;
	static{
		pros = new Properties();
		
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		DMLSample dml = new DMLSample();
		dml.query();
		//dml.load();

	}
	
	/**
	 * load ["-f","/home/ws/data_100000","-t","ws_0703","-tp","-txt","-sp",",","-p","default","-fl","A,B","-local","-overwrite"];
	 * 用于FP离线数据入库使用
	 */
	private void load() {
		//声明对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//声明变量
		try {
			//加载驱动
			Class.forName(pros.getProperty("FPDRIVER")); 
			//获取链接及对象
			conn = DriverManager.getConnection(pros.getProperty("FPURL"), pros.getProperty("FPUSER"), pros.getProperty("FPPASSWORD"));
			//创建sql
			String sql_load  = "load [\"-f\",\"/home/ws/data_100000\",\"-t\",\"ws_0703\",\"-tp\",\"-txt\",\"-sp\",\",\",\"-p\",\"default\",\"-fl\",\"A,B\",\"-local\",\"-overwrite\"]";
			//String sql_truncate = "truncate table ws_0703";
			//创建sql命令对象
			ps = conn.prepareStatement(sql_load);
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
	}

	/**
	 * 用于SQL查询使用
	 */
	public void query() {
		//声明对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//声明变量
		try {
			//加载驱动
			Class.forName(pros.getProperty("FPDRIVER"));
			//获取链接及对象
			conn = DriverManager.getConnection(pros.getProperty("FPURL"), pros.getProperty("FPUSER"), pros.getProperty("FPPASSWORD"));
			//创建sql
//			String sql  = "select * from ghr2 where partition like '123' and NAME = ? and SEX = ? and PROVINCE = ?  limit 100";
			String sql  = "select * from part_kafka_0228_A where partition like '20200302' and s_high = ? limit 100";

			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			//给占位符赋值
//			ps.setString(1, "萧昊乾");
//			ps.setString(2, "女");
//			ps.setString(3, "江苏省");
			ps.setString(1, "6951065711107");

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
		
	}
}
