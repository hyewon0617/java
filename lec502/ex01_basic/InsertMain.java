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
			
			// emp���̺� ���ڵ� �߰�(����)
			sql = " insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno) "
				+ " values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 9001);
			pstmt.setString(2, "����");
			pstmt.setString(3, "����");
			pstmt.setInt(4, 7839);
			pstmt.setString(5, "2020-02-20");
			pstmt.setInt(6, 10000);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 10);
			row = pstmt.executeUpdate();		
			System.out.println("emp���̺� " + row + "���� �߰� �Ǿ����ϴ�!");		
		} catch (Exception e) {
			System.out.println("���ڵ��߰� ����");
			e.getMessage();
		} finally {
			if(pstmt != null) { try { pstmt.close(); } catch(Exception e) {} };
			if(conn != null)  { try { conn.close();  } catch(Exception e) {} };
		}

	}

}