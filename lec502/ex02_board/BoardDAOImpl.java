package com.lec502.ex02_board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class BoardDAOImpl implements BoardDAOService {
	
	// 사용자가 등록한 글을 입력받는 사용자메서드
	public BoardVO inputBoard() {
		
		BoardVO bd = new BoardVO();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("글제목을 입력하세요=>");
		bd.setSubject(sc.nextLine());
		
		System.out.println("작성자를 입력하세요=>");
		bd.setWriter(sc.nextLine());
		
		System.out.println("글내용을 입력하세요=>");
		bd.setContent(sc.nextLine());

		return bd;
	}
	

	@Override // 사용자가 입력한 게시물을 board테이블에 저장
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
			// System.out.println("게시글이 성공적으로 등록되었습니다!");
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

	@Override // 사용자가 입력한 게시물을 board테이블에 수정
	public void updateBoard() {
		// TODO Auto-generated method stub
		
	}

	@Override // 사용자가 입력한 게시물을 board테이블에 삭제
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
			System.out.println("목록조회실패");
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