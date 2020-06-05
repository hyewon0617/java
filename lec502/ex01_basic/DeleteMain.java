package com.lec502.ex01_basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteMain {

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
			
			// emp 테이블에서 레코드삭제
			sql = "delete from emp where empno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 9001);
			row = pstmt.executeUpdate();
			System.out.println("emp테이블에서 " + row + "건을 삭제하였습니다!");	
		} catch (Exception e) {
			System.out.println("자료삭제실패!");
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

	}

}
