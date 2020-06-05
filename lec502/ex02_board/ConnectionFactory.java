package com.lec502.ex02_board;

import java.io.FileReader;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {

	private String path
	   = ConnectionFactory.class.getResource("jdbc.properties").getPath();
	
	private String DRV = null;
	private String URL = null;
	private String USR = null;
	private String PWD = null;
	
	private String insert = null;
	private String select = null;
	private String update = null;
	private String delete = null;
	
	public ConnectionFactory() {
	
		try {
			setUp();
		} catch (Exception e) {
			e.printStackTrace();
		} // jdbc.properties�� �б�
		
	}

	// setUp()�޼���� jdbc.properties���Ͽ� ����� DB����������
	// ���α׷������� �������� ����� ���� �޼���()
	public void setUp() throws Exception {
		
		Properties p = new Properties();
		path = URLDecoder.decode(path, "utf-8");
		// System.out.println(path);
		p.load(new FileReader(path));
		
		// DB��������
		DRV = p.getProperty("jdbc.drv");
		URL = p.getProperty("jdbc.url");
		USR = p.getProperty("jdbc.usr");
		PWD = p.getProperty("jdbc.pwd");
		
		// SQL����
		insert = p.getProperty("jdbc.sql.insert");
		select = p.getProperty("jdbc.sql.select");
		update = p.getProperty("jdbc.sql.update");
		delete = p.getProperty("jdbc.sql.delete");
		
		//System.out.println(DRV);
		Class.forName(DRV);
	}

	// DB����
	public Connection getConnection() {
		try {
			System.out.println("DB���Ӽ���");
			return DriverManager.getConnection(URL, USR, PWD);		
		} catch (Exception e) {
			System.out.println("DB���ӽ���");
			return null;
		}
	}

	public String getInsert() {
		return insert;
	}

	public void setInsert(String insert) {
		this.insert = insert;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}
	
}

