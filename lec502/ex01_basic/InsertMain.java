package com.lec502.ex01_basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertMain {

	final static String DRV = "oracle.jdbc.OracleDriver";
	final static String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	final static String USR = "scott";
	final static String PWD = "tiger";
		
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try {
			
			Class.forName(DRV);
			conn = DriverManager.getConnection(URL, USR, PWD);
			int row = 0;
			
			// emp테이블에 레코드 추가(삽입)
			sql = " insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno) "
				+ " values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 9001);
			pstmt.setString(2, "소향");
			pstmt.setString(3, "가수");
			pstmt.setInt(4, 7839);
			pstmt.setString(5, "2020-02-20");
			pstmt.setInt(6, 10000);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 10);
			row = pstmt.executeUpdate();		
			System.out.println("emp테이블에 " + row + "건이 추가 되었습니다!");		
		} catch (Exception e) {
			System.out.println("레코드추가 실패");
			e.getMessage();
		} finally {
			if(pstmt != null) { try { pstmt.close(); } catch(Exception e) {} };
			if(conn != null)  { try { conn.close();  } catch(Exception e) {} };
		}

	}

}