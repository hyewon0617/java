package com.lec502.ex01_basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateMain {

	final static String DRV = "oracle.jdbc.OracleDriver";
	final static String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	final static String USR = "scott";
	final static String PWD = "tiger";
	
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	
	public static void main(String[] args) {
		
		try {
			
			Class.forName(DRV);
			conn = DriverManager.getConnection(URL, USR, PWD);
			String sql = "";
			int row = 0;
			
			// emp테이블 수정하기
			sql = "update emp set ename=?, sal=?, comm=? where empno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "홍길동");
			pstmt.setInt(2, 9000);
			pstmt.setInt(3, 1000);
			pstmt.setInt(4, 9001);
			row = pstmt.executeUpdate();
			System.out.println("emp 테이블에 " + row + "건의 행을 수정하였습니다!");		
		} catch (Exception e) {
			System.out.println("수정실패!");
			e.getMessage();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// dummy
			}
		}

	}

}

