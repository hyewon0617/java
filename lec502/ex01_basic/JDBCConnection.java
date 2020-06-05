package com.lec502.ex01_basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
	JDBC�� �̿��� �ڹٿ� DB����
	
	Java�� DB�� �����ϱ� ���ؼ��� �� DBMS�� ������ �´� JRE ����ȯ�� ���̺귯���� java ������Ʈ��
	�߰��ؾ� �Ѵ�. Oracle DB�� ojdbc6.jar�� �߰��ؾ� �ϰ� MSSQL, MySQL���� ���� DB�� �´� ����ȯ��
	���̺귯���� �߰��ؾ� �Ѵ�.
	
	���̺귯���� �߰��ϴ� ���
	1. java������Ʈ�� ��Ŭ��
	2. Build path > Configure Build path > (t)Libraries > (b)Add External Jars... > (b)Apply & Close
	
	A. JDBC���α׷� �ۼ��ܰ�
	
	   1. JDBC����̹��ε�(DB���������� ����̹��� �ٸ� �� ����)
	   
	      1) oracle ����̹��ε� : Class.forName("oracle.jdbc.OracleDriver");
	      2) MySQL ����̹��ε�  : Class.forName("com.mysql.jdbc.driver");
	      
	      Class.forName()�޼���� ����̹��� �б⸸ �ϸ� �ڵ����� ��ü�� �����ǰ�
	      DriverManager�� ����� �ȴ�.
	      
	   2. Connection��ü����
	   
	      Connection��ü�� �����ϴ� ������ DriverManager�� ��ϵ� �� ����̹����� getConnection()
	      �޼��带 ����ؼ� �ĺ��Ѵ�. getConnection�޼����� �Ű����� DB URL�ּ�, �����ID, Password��
	      �����Ѵ�. ������ �� url�ĺ��������� �̿��ؼ� Mapping�� �ϰ� ã�� ���� ��쿡�� no suitable
	      error�� �߻��Ѵ�. ã���� ��� getConnctiong(String url)�޼����� �������� Connection��ü�� 
	      ���ϵȴ�.
	   
	      1) Oracle����
	      
	         conn = DriverManager.getConnection(
	         	"jdbc:oracle:thin:@����ŬDB�����ּ�:��Ʈ��ȣ:����ID",
	         	"����ڰ���",
	         	"�н�����"
	         );
	         
	      2) MySQL����
	
	         conn = DriverManager.getConnection(
	         	"jdbc:mysql://MySQL DB�����ּ�/db�̸�",
	         	"����ڰ���",
	         	"�н�����"
	         );	      
	   
	   3. Statement(Statement/PreparedStatement/CallableStatement)��ü����
	   
	      SQL�� ���� �� �����ϸ� ��ȯ�� ����� �������� �� �۾������� �����Ѵ�. Statement��ü�� 
	      Connection��ü�� createStatement()�޼��带 ����Ͽ� �����Ѵ�.
	      
	      �� �ܰ���ʹ� JDBC����̹��� ���� ���� �ʴ´�. ��, DB�� ������� ������ �������
	      ����� �� �ִ�. ��, SQLǥ�ؿ� �ؼ��� ��� ������ ��������� DB���� ������ SQL���
	      �̶�� �� DB�� SQL������ �°� �ۼ��Ǿ�� �Ѵ�.
	      
	      Statement stmt = conn.createStatement();
	      PreparedStatement pstmt = conn.createStatement();
	      
	      
	   4. Query����
	   
	      Statement��ü�� �����Ǹ� Statement��ü�� executeQuery(), executeUpdate()�޼��带 ����Ͽ�
	      ó���Ѵ�.
	      
	      ResultSet rs = stmt.executeQuery("select * from table;");
	      
	   5. ResultSetó��
	   
	      executeQuery()�޼���� ����� ResultSet���� �����Ѵ�. �� ResultSet���� ���� �c�� �����͸�
	      �����ϴ� ������ ResultSetó�������̴�.
	      
	      �����͸� �����ϴ� ����� ResultSet���� ����(row)�� �̵��ϸ鼭 getXXX()�޼��带 �̿��Ͽ�
	      �����͸� �����ϴµ� �̶� rs.getString(1) �Ǵ� rs.getString("�÷���")�� ������� ���ȴ�.
	      
	   
	B. JDBC���� ���Ǵ� ��ü
	
	   1. DriverManager Ŭ����
	   
	      DriverManagerŬ������ �����Ϳ����� JDBC����̹��� ���� Ŀ�ؼ��� ����� ������ �Ѵ�. DriverManager
	      Ŭ������ Class.forName()�޼��带 ���� �����Ǵµ� �� �޼���� �������̽� ����̹��� �����ϴ� �۾���
	      �Ѵ�. 
	      
	      Class.forName()ó�� Ư�� Ŭ������ �ε��ϸ� �ڵ����� ��ü�� �����ǰ� DriverManager�� ��ϵȴ�. ���
	      �̹��� ã�� ���� ��쿡�� forName()�޼���� ClassNotFoundException���ܸ� �߻���Ű�� ������ �ݵ��
	      ����ó���� �ؾ� �Ѵ�.
	      
	      �Ϲ������� ����̹� Ŭ�������� �ε�� �� �ڽ��� �ν��Ͻ��� �����ϰ� �ڵ������� DriverManagerŬ����
	      �޼��带 ȣ���Ͽ� �� �ν��Ͻ��� ����Ѵ�. DriverManagerŬ������ ��� �޼���� static�̱� ������ 
	      ��ü�� ������ �ʿ䰡 ����.
	      
	      DriverManagerŬ������ Connection�������̽��� ������ü�� �����ϴµ� getConnection()�޼��带 ����Ѵ�.
	      
	   2. Connection �������̽�
	   
	      Ư�� �����Ϳ����� ���� Ŀ�ؼ��� Connection�������̽��� ������ Ŭ������ ��ü�� ǥ���ȴ�. Ư���� SQL
	      ���� �����Ű�� ���� �켱 Connection��ü�� �־�� �Ѵ�. Connection��ü�� Ư�� �����Ϳ����� ���ᱭ
	      Ŀ�ؼ��� ��Ÿ���� Ư���� SQL������ �����ϰ� ���� ��ų �� �ִ� Statement��ü�� ������ ���� Connection
	      ��ü�� ����Ѵ�.
	      
	      ����, Connection��ü�� �����ͺ��̽��� ���� �������� ��Ÿ�����ͺ��̽��� ���� ������ �����Ϳ����� ���Ǹ�
	      �ϴµ� ����Ѵ�. �̶����� ��밡���� ���̺��� �̸�, Ư�� ���̺��� �÷� �������� ���Եȴ�.
	      
	   3. Statement �������̽�
	   
	      Statement �������̽��� Connection��ü�� ���� ���α׷��� ���ϵǴ� ��ü�� ���� �������� ������ �޼���
	      ������ �����Ѵ�.
	      
	      Statement��ü�� Statement�������̽��� ������ ��ü�μ� �׻� �μ��� ���� Connection.createStatement()
	      �޼��带 ȣ�������μ� �������.
	      
	      try {
	      	Statement stmt = conn.createStatement();
	      	...
	      catch(SQLException e) {
	        ...
	      }
	      
	      �ϴ�, Statement��ü�� �����ϸ� Statement.executeQuery()�޼��带 ȣ���ؼ� SQL���Ǹ� ���� ��ų �� �ִ�.
	      �޼����� ���ڷ� SQL������ ���� String��ü�� �����Ѵ�. Statement��ü�� �ܼ� ���ǹ��� ����ϴ� ���� ����.
	   
	   4. PreparedStatement �������̽�
	   
	      PreparedStatement�������̽��� Connection��ü�� prepareStatement()�޼��带 ����ؼ� ��ü�� �����Ѵ�.
	      PreparedStatement��ü�� SQL������ ������ ������ �Ǿ� ����ð����� �Ķ���Ͱ��� ���� ������ Ȯ���Ѵٴ�
	      ������ Statement��ü�� �����ȴ�.
	      
	      PreparedStatement��ü�� ������ ���ǹ��� Ư������ �����ؼ� ������ �ݺ� �����ؾ��� ��, ���� �����͸� �ٷ��
	      ������ ���ǹ��� �����ؾ� �� �ʿ䰡 ���� ��, �Ķ��Ŀ���� ���Ƽ� ���ǹ��� �����ؾ��� �ʿ䰡 ���� �� ���
	      �ϸ� �����ϴ�. ����, Statement��ü�� SQL�� ����� �� ���� DB�������� �м��Ǿ�� ������ PreparedStatement
	      ��ü�� �ѹ��� �м��Ǹ� ������ �����ϴٴ� ������ �ִ�.
	      
	      PreparedStatement�������̽��� ������ �μ��� ���� ��ġȦ��(placeholder = ?)�� ����Ͽ� SQL������ ������ �� 
	      �ְ� ���ش�. ��ġȦ���� ����ǥ(?)�� ǥ���ȴ�. ��ġȦ���� SQL���忡 ��Ÿ���� ��ū(token)�ε� �̰��� SQL����
	      �� ����Ǳ� ���� ���������� ��ü�ȴ�. �̷� ����� �̿��ϸ� Ư�� ������ ���ڿ��� �����ϴ� ������� �ξ� ����
	      SQL������ ���� �� �ִ�.
	      
	      PreparedStatement��ü�� ������ SQLŸ���� ó���� �� �ִ� setXXX()�޼��带 �����Ѵ�. ���⼭ XXX�� �ش� ���̺�
	      �� �ʵ嵥����Ÿ�԰� ������ �ִ�. �ش� �ʵ��� ������Ÿ���� ���ڿ��̸� setString()�� �ǰ� ���ڰ��̸� setInt()
	      �� �ȴ�. setXXX(num, valaue)�޼���� 2���� ������ ������ �ִµ� num�� �Ķ�����ε���(placeholde��ġ)�̰� ��
	      ġȦ���� �����ȴ�.
	      
	      PreparedStatement��ü�� �����ϴ� �޼����
	      
	      setString, setInt, setLong, setDouble, setFloat, setDate, setTimestamp, setObject�� �ִ�.
	      
	      PreparedStatement��ü�� ����ϴ� ���� Statement��ü�� ����ϴ� �� ���� ����.
	      
	      PreparedStatement�� ����
	      
	      1) ������ ���ǹ��� Ư�� ���� �����ؼ� ������ ����־� �Ҷ�, ���� �����͸� �ٷ� ��� ���ǹ��� �����ؾ���
	         �ʿ䰡 ���� ��, �Ķ���Ͱ� ���Ƽ� ���ǹ��� �����ؾ� �� �ʿ䰡 ���� �� ����.
	      2) ������ �����ϵǱ� ������ ������ ����ӵ��� Statement��ü�� ���� ������. 
	      3) Statement��ü�� �������ؽ� ���� ����ǥ(')�� ��� ������ ���� ����ǥ�� 2���� ǥ���ؾ� ������ PreparedStatement
	         ��ü�� ���� ����ǥ�� ������ ��������ÿ� �ڵ����� ó���ϱ� ������ �Ű澵 �ʿ䰡 ����.
	      
	   5. CallableStatement �������̽�
	   
	   	  CallableStatement�������̽��� Connection��ü�� prepareCall()�޼��带 ����ؼ� ��ü�� �����Ѵ�. CallableStatement
	   	  ��ü�� �ַ� ���������ν���(Stored Procedure)�� ����ϱ� ���� ����Ѵ�.
	   	  
	   	  Stored Procedure�� ������ ����� ������ ����ϱ� ������ ����ӵ��� ������.
	   	  
	      try {
	      	CallableStatement sstmt = conn.prepareCall();
	      	...
	      catch(SQLException e) {
	        ...
	      }
	   	  
	   	  CallableStatement�� �����ͺ��̽��� ����� Stored Procedure�� ���� ȣ���ϴ� �͸����� ó���� �����ϴ�.
	   	  
	   	  --> conn.prepareCall("{ call stored_prodcedure�̸�}");
	   	  
	   	  CallableStatement�� ������
	   	  1) �ܼ�ȣ�� : { call prodedure�̸�[?,?....] }
	   	  2) ȣ�������� ���� :  { ? = call prodedure�̸�[?,?....] }
	   	  3) �Ķ���Ͱ� ���� ���ν��� ȣ�� :  { call prodedure�̸� }
	   	  
	   6. ResultSet �������̽�
	   
	      SQL���߿��� select���� ����� ���ǰ� �������� ��쿡 ����μ� ResultSet�� ��ȯ�Ѵ�. ResultSet��
	      SQL���ǿ� ���� ������ ���̺��� ��� �ִ�. ����, ResultSet��ü�� Ŀ��(cursor)��� �Ҹ��� ���� ��
	      ���� �ִµ� �� ������ ResultSet���� Ư���࿡ ���� ������ ������ �� �ִ�.
	      
	      Ŀ���� �ʱ⿡ ù���� ���� �ٷ� ���� ����Ű���� �Ǿ� �ִµ�, ResultSet��ü�� next()�޼��带 ���
	      �ϸ� ���������� �̵��� �� �ִ�.
	      
	      ResultSet�� �޼���
	      
	      1) first()       : Ŀ���� ù��°������ �̵�
	      2) last() 	   : Ŀ���� ������������ �̵�
	      3) beforeFirst() : Ŀ���� ù��°�� �������� �̵�
	      4) afterLast()   : Ŀ���� �������� ���ķ� �̵�
	      5) next()        : Ŀ���� ���������� �̵�
	      6) previous()    : Ŀ���� ���������� �̵�
	      
	      ResultSet���� ���� ó���ϴµ� �ݺ��� ����ϸ� next()�޼��尡 ��ȿ�� ���� ������ true, ������ false��
	      �����ϴ� ���� �̿��Ͽ� while������ ������ �� �ִ�. ResultSet��ü���� ���� ������ ��쿡 getXXX()�޼���
	      �� ����Ѵ�.
	      
	      ResultSet�� �����ϴ� �޼����
	      
	      getString(), getInt(), getLong(), getBytes(), getDouble, getDate(), getTimestamp(), getObject() ���� �ִ�.
	      	   
	   7. JDBC Ʈ�����
	   
	      Ʈ�����(Transaction)�� �����ܰ��� �۾��� �ϳ��� ó���ϴ� �����μ� �ϳ��� �νĵ� �۾��� ��� ����������
	      ������ commit�� �ǰ� �ϳ��� ������ �߻��ϸ� rollback�� �Ǿ �۾��� ���������� �ǵ�����. �̷��� ����
	      �� Transaction�� ���α׷��� �ŷڵ��� �����ϰ� �ȴ�.
	      
	      JDBC������ Transactionó���� ���� �޼��带 �����Ѵ�.
	      
	      1) commit()   : Transaction�� commit�� ����
	      2) rollback() : Transaction�� rollback�� ����
	      
	      �⺻������ Connection��ü�� setAutoCommit(boolean autoCommit)�̶� �޼��尡 �ִµ� �⺻���� true�� ������
	      �Ǿ� �ִ�. �׷��� ������ JDBC�� Auto Commit�� �ڵ����� ����Ǵµ� �ڵ��� ����Ǵ� ���� �������� 
	      conn.setAutoCommit(false)�� �����ؾ� �Ѵ�.

*/
public class JDBCConnection {

final static String DRV = "oracle.jdbc.OracleDriver";
final static String URL = "jdbc:oracle:thin:@localhost:1521:XE";
final static String USR = "scott";
final static String PWD = "tiger";

public static void main(String[] args) {
	
	Connection conn = null;
	
	try {
		
		Class.forName(DRV);
		conn = DriverManager.getConnection(URL, USR, PWD);
		System.out.println("DB������ �����Ǿ����ϴ�!");
	} catch (Exception e) {
		System.out.println("DB������ ���еǾ����ϴ�!");
		e.printStackTrace();
	} finally {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

}