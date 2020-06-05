package com.lec502.ex02_board;

public class BoardFactory {

	private static BoardDAOImpl bddao = null;
	
	// �ܺο��� �����Ұ�
	private BoardFactory() {}
	
	// �̱���ó�� 
	public static BoardDAOImpl getInstance() {
		if(bddao == null) bddao = new BoardDAOImpl();
		return bddao;
	}
}


