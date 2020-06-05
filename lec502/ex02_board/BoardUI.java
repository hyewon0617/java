package com.lec502.ex02_board;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

// �Խ��Ǿ��ø����̼��� ȭ�鱸���ϴ� Ŭ����
public class BoardUI {

	private double ver;
	
	public BoardUI(double ver) {
		this.ver = ver;
	}
	
	private void displayBoardUI() {
		StringBuffer sb = new StringBuffer();
		sb.append("***** �Խ��� ���α׷� V" + ver + "*****\n");
		sb.append("==========================\n");
		sb.append("1. ���ο� �۾��� \n");
		sb.append("2. ��ü�� ��� ��ȸ \n");
		sb.append("3. ������ ���� \n");
		sb.append("4. ������ ���� \n");
		sb.append("5. ������ ���� \n");
		sb.append("6. �������� �Խñ� ��ȸ \n");
		sb.append("7. �ۼ��ڷ� �Խñ� ��ȸ \n");
		sb.append("0. ���� \n");
		sb.append("==========================\n");
		sb.append("ó���� �۾���ȣ�� �Է��ϼ���.\n");
		System.out.println(sb.toString());
	}
	
	public void mainBoardUI() throws Exception {
		
		BoardDAOImpl bddao = BoardFactory.getInstance();
		
		while(true) {
			
			displayBoardUI();
			
			// InputStreamReaderŬ������ ����Ʈ��Ʈ������ ���� ������ ���ڷ� ��ȯ 
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
			default: System.err.println("��Ȯ�� ��ȣ�� �Է��ϼ���!!!");
			}
		}		
	}
	
	public void makeBoard(BoardDAOImpl bddao) {
		bddao.createBoard();
	}
		
	public void listBoard(BoardDAOImpl bddao) {
		ArrayList<BoardVO> bds = bddao.listBoard();
		System.out.println("======================================================");
		System.out.println("��ȣ\t\t����\t\t�ۼ���\t\t����");
		System.out.println("======================================================");
		for(BoardVO bd:bds) {
			System.out.println(bd.toString());
		}
		System.out.println("----- ������� -----\n\n\n");
	}
	
	public void contentView() {}
	public void updateBoard() {}
	public void deleteBoard() {}
	public void findBySubject() {}
	public void findByWriter() {}
	
	
	
}