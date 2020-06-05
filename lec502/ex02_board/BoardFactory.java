package com.lec502.ex02_board;

public class BoardFactory {

	private static BoardDAOImpl bddao = null;
	
	// 寇何俊辑 积己阂啊
	private BoardFactory() {}
	
	// 教臂沛贸府 
	public static BoardDAOImpl getInstance() {
		if(bddao == null) bddao = new BoardDAOImpl();
		return bddao;
	}
}


