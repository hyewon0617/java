package com.lec502.ex02_board;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 게시판애플리케이션의 화면구성하는 클래스
public class BoardUI {

	private double ver;
	
	public BoardUI(double ver) {
		this.ver = ver;
	}
	
	private void displayBoardUI() {
		StringBuffer sb = new StringBuffer();
		sb.append("***** 게시판 프로그램 V" + ver + "*****\n");
		sb.append("==========================\n");
		sb.append("1. 새로운 글쓰기 \n");
		sb.append("2. 전체글 목록 조회 \n");
		sb.append("3. 본문글 보기 \n");
		sb.append("4. 본문글 수정 \n");
		sb.append("5. 본문글 삭제 \n");
		sb.append("6. 제목으로 게시글 조회 \n");
		sb.append("7. 작성자로 게시글 조회 \n");
		sb.append("0. 종료 \n");
		sb.append("==========================\n");
		sb.append("처리할 작업번호를 입력하세요.\n");
		System.out.println(sb.toString());
	}
	
	public void mainBoardUI() throws Exception {
		
		BoardDAOImpl bddao = BoardFactory.getInstance();
		
		while(true) {
			
			displayBoardUI();
			
			// InputStreamReader클래스는 바이트스트림으로 읽은 내용을 문자로 변환 
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			
			switch((char)br.read()) {
			case '1': makeBoard(bddao); break;
			case '2': listBoard(bddao); break;
			case '3': contentView(); break;
			case '4': updateBoard(); break;
			case '5': deleteBoard(); break;
			case '6': findBySubject(); break;
			case '7': findByWriter(); break;
			case '0': System.out.println("0"); break;
			default: System.err.println("정확한 번호를 입력하세요!!!");
			}
		}		
	}
	
	public void makeBoard(BoardDAOImpl bddao) {
		bddao.createBoard();
	}
		
	public void listBoard(BoardDAOImpl bddao) {
		ArrayList<BoardVO> bds = bddao.listBoard();
		System.out.println("======================================================");
		System.out.println("번호\t\t제목\t\t작성자\t\t본문");
		System.out.println("======================================================");
		for(BoardVO bd:bds) {
			System.out.println(bd.toString());
		}
		System.out.println("----- 출력종료 -----\n\n\n");
	}
	
	public void contentView() {}
	public void updateBoard() {}
	public void deleteBoard() {}
	public void findBySubject() {}
	public void findByWriter() {}
	
	
	
}