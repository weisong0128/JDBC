package com.fiberhome.fp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mysql.fabric.xmlrpc.base.Array;

/**
 * 普通查询SQL的JDBC用法
 * @author Administrator
 *
 */
public class DQLSample {
	
	static Properties pros = null;
	static {
		pros = new Properties();
		
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DQLSample dql = new DQLSample();
		dql.Query();
	}
	
	/**
	 * 适用于只查询一行数据情况
	 */
	private void Query() {
		Connection conn = null;			//声明数据库连接对象
		PreparedStatement ps = null;	//创建执行数据库操作对象
		ResultSet rs = null;			//创建执行SQL后的结果集对象	conn/ps/rs这三个都是接口的实例化对象！
		try {
			Class.forName(pros.getProperty("FPDRIVER"));
			conn = DriverManager.getConnection(pros.getProperty("FPURL"), pros.getProperty("FPUSER"), pros.getProperty("FPPASSWORD"));
			String sql = "select * from ghr2 where partition like '123' and NAME = ? and SEX = ? and PROVINCE = ?  limit 10";
//			String sql = "select * from TEST_PERSON_INFO where partition = '20190731' and PHONE = '13969245464' limit 10";
			ps = conn.prepareStatement(sql);
//			ps.setObject(1, "萧昊乾");
//			ps.setObject(2, "女");
//			ps.setObject(3, "江苏省");
			rs = ps.executeQuery();
			long num1 = Runtime.getRuntime().freeMemory();
			long startTime = System.currentTimeMillis(); 
			while(rs.next()){
				for(int i = 1; i < rs.getMetaData().getColumnCount()+1; i++){
					System.out.print(rs.getObject(i));
					System.out.print("\t");
				}
				System.out.println();	
			}
			long num2 = Runtime.getRuntime().freeMemory();
			System.out.println("查询占用内存 : " + (num1 - num2));
			long endTime = System.currentTimeMillis();
			System.out.println("查询总耗时为：" + (endTime - startTime) + "ms");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
