package com.lec502.ex02_board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class BoardDAOImpl implements BoardDAOService {
	
	// ����ڰ� ����� ���� �Է¹޴� ����ڸ޼���
	public BoardVO inputBoard() {
		
		BoardVO bd = new BoardVO();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("�������� �Է��ϼ���=>");
		bd.setSubject(sc.nextLine());
		
		System.out.println("�ۼ��ڸ� �Է��ϼ���=>");
		bd.setWriter(sc.nextLine());
		
		System.out.println("�۳����� �Է��ϼ���=>");
		bd.setContent(sc.nextLine());

		return bd;
	}
	

	@Override // ����ڰ� �Է��� �Խù��� board���̺� ����
	public void createBoard() {
		
		BoardVO bd = inputBoard();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		ConnectionFactory cf = new ConnectionFactory();
		conn = cf.getConnection();
		String sql = cf.getInsert();
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bd.getSubject());
			pstmt.setString(2, bd.getWriter());
			pstmt.setString(3, bd.getContent());
			pstmt.executeUpdate();
			// System.out.println("�Խñ��� ���������� ��ϵǾ����ϴ�!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch (Exception e2) {
				// dummy
			}
		}
	}

	@Override // ����ڰ� �Է��� �Խù��� board���̺� ����
	public void updateBoard() {
		// TODO Auto-generated method stub
		
	}

	@Override // ����ڰ� �Է��� �Խù��� board���̺� ����
	public void deleteBoard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BoardVO viewBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BoardVO> listBoard() {
		
		ArrayList<BoardVO> boardList = new ArrayList<BoardVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			ConnectionFactory cf = new ConnectionFactory();

			conn = cf.getConnection();
			String sql = cf.getSelect() + " where rownum between 1 and 10";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				BoardVO bd = new BoardVO();
				bd.setBno(rs.getInt("bno"));
				bd.setSubject(rs.getString("subject"));
				bd.setWriter(rs.getString("writer"));
				bd.setContent(rs.getString("content"));
				boardList.add(bd);				
			}			
		} catch (Exception e) {
			System.out.println("�����ȸ����");
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				// dummy
			}
		}
		
		return boardList;
	}

	@Override
	public ArrayList<BoardVO> findBySubjectBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BoardVO> findByWriterBoard() {
		// TODO Auto-generated method stub
		return null;
	}

} 