package com.fiberhome.fp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


/**
 * 资源配置文件-->db连接
 * @author Administrator
 *
 */
public class DQL_DML {
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
		DQL_DML obj = new DQL_DML();
		obj.query();
//		obj.load();
		
	}

	private void load() {
		Connection conn = null;
		PreparedStatement ps = null ;
		ResultSet rs = null;
		
		try {
			Class.forName(pros.getProperty("FPDRIVER"));
			conn=DriverManager.getConnection(pros.getProperty("FPURL"), pros.getProperty("FPUSER"), pros.getProperty("FPPASSWORD"));
			String sql_load  = "load [\"-f\",\"/home/ws/data_100000\",\"-t\",\"ws_0703\",\"-tp\",\"-txt\",\"-sp\",\",\",\"-p\",\"default\",\"-fl\",\"A,B\",\"-local\",\"-overwrite\"]";
			ps = conn.prepareStatement(sql_load);
			int temp = ps.executeUpdate();
			System.out.println(temp);
			
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

	private void query() {
		Connection conn = null;
		PreparedStatement ps = null ;
		ResultSet rs = null;
		
		try {
			Class.forName(pros.getProperty("FPDRIVER"));
			conn=DriverManager.getConnection(pros.getProperty("FPURL"), pros.getProperty("FPUSER"), pros.getProperty("FPPASSWORD"));
			String sql = "select * from part_test where partition like ? limit 10";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, 2019);
			rs=ps.executeQuery();
			while(rs.next()){
				for(int i=1; i <rs.getMetaData().getColumnCount()+1; i++){
					System.out.print(rs.getString(i));
					System.out.print("\t");
				}
				System.out.println();
			}
			
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
