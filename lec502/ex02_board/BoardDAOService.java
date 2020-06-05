package com.lec502.ex02_board;

import java.util.ArrayList;

public interface BoardDAOService {
	
	void createBoard();   // 글쓰기
	void updateBoard();   // 글수정
	void deleteBoard();   // 글삭제
	BoardVO viewBoard();  // 본문조회
	ArrayList<BoardVO> listBoard(); // 전체글목록조회
	ArrayList<BoardVO> findBySubjectBoard(); // 제목별조회
	ArrayList<BoardVO> findByWriterBoard();  // 작성자별조회

}