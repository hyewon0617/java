package com.lec502.ex02_board;

import java.util.ArrayList;

public interface BoardDAOService {
	
	void createBoard();   // �۾���
	void updateBoard();   // �ۼ���
	void deleteBoard();   // �ۻ���
	BoardVO viewBoard();  // ������ȸ
	ArrayList<BoardVO> listBoard(); // ��ü�۸����ȸ
	ArrayList<BoardVO> findBySubjectBoard(); // ������ȸ
	ArrayList<BoardVO> findByWriterBoard();  // �ۼ��ں���ȸ

}