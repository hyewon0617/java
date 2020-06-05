package com.lec502.ex01_basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectManyMain {

	final static String DRV = "oracle.jdbc.OracleDriver";
	final static String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	final static String USR = "scott";
	final static String PWD = "tiger";
	
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	
	public static void main(String[] args) {
		
		try {
			
			Class.forName(DRV);
			conn = DriverManager.getConnection(URL, USR, PWD);
			String sql = "";
			
			// emp테이블에서 복수개의 자료 읽기
			sql = "select * from emp where empno >= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 7000);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int empno = rs.getInt(1);
				String ename = rs.getString("ename");
				String job = rs.getString(3);
				int mgr = rs.getInt(4);
				String hiredate = rs.getString(5);
				int sal = rs.getInt(6);
				int comm = rs.getInt(7);
				System.out.print(empno + "\t ");
				System.out.print(ename + "\t ");
				System.out.print(job + "\t ");
				System.out.print(mgr + "\t ");
				System.out.print(hiredate + "\t ");
				System.out.print(sal + "\t ");
				System.out.println(comm);
			}

		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				// dummy
			}
		}

	}

}
















