package com.lec502.ex01_basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
	JDBC를 이용한 자바와 DB연동
	
	Java와 DB를 연동하기 위해서는 각 DBMS의 버전에 맞는 JRE 실행환경 라이브러리를 java 프로젝트에
	추가해야 한다. Oracle DB는 ojdbc6.jar을 추가해야 하고 MSSQL, MySQL등은 각각 DB에 맞는 실행환경
	라이브러리를 추가해야 한다.
	
	라이브러리를 추가하는 방법
	1. java프로젝트를 우클릭
	2. Build path > Configure Build path > (t)Libraries > (b)Add External Jars... > (b)Apply & Close
	
	A. JDBC프로그램 작성단계
	
	   1. JDBC드라이버로드(DB버전에따라 드라이버가 다를 수 있음)
	   
	      1) oracle 드라이버로딩 : Class.forName("oracle.jdbc.OracleDriver");
	      2) MySQL 드라이버로딩  : Class.forName("com.mysql.jdbc.driver");
	      
	      Class.forName()메서드는 드라이버를 읽기만 하면 자동으로 객체가 생성되고
	      DriverManager에 등록이 된다.
	      
	   2. Connection객체생성
	   
	      Connection객체에 연결하는 것으로 DriverManager에 등록된 각 드라이버들을 getConnection()
	      메서드를 사용해서 식별한다. getConnection메서드의 매개값은 DB URL주소, 사용자ID, Password를
	      전달한다. 연결할 때 url식별자정보를 이용해서 Mapping을 하고 찾지 못할 경우에는 no suitable
	      error가 발생한다. 찾았을 경우 getConnctiong(String url)메서드의 수행결과가 Connection객체에 
	      리턴된다.
	   
	      1) Oracle사용시
	      
	         conn = DriverManager.getConnection(
	         	"jdbc:oracle:thin:@오라클DB서버주소:포트번호:서비스ID",
	         	"사용자계정",
	         	"패스워드"
	         );
	         
	      2) MySQL사용시
	
	         conn = DriverManager.getConnection(
	         	"jdbc:mysql://MySQL DB서버주소/db이름",
	         	"사용자계정",
	         	"패스워드"
	         );	      
	   
	   3. Statement(Statement/PreparedStatement/CallableStatement)객체생성
	   
	      SQL을 생성 및 실행하며 반환된 결과를 가져오게 할 작업영역을 제공한다. Statement객체는 
	      Connection객체의 createStatement()메서드를 사용하여 생성한다.
	      
	      이 단계부터는 JDBC드라이버에 구애 받지 않는다. 즉, DB와 상관없이 동일한 명령으로
	      사용할 수 있다. 단, SQL표준에 준수할 경우 동일한 명령이지만 DB마다 고유한 SQL명령
	      이라면 각 DB의 SQL문법에 맞게 작성되어야 한다.
	      
	      Statement stmt = conn.createStatement();
	      PreparedStatement pstmt = conn.createStatement();
	      
	      
	   4. Query실행
	   
	      Statement객체가 생성되면 Statement객체의 executeQuery(), executeUpdate()메서드를 사용하여
	      처리한다.
	      
	      ResultSet rs = stmt.executeQuery("select * from table;");
	      
	   5. ResultSet처리
	   
	      executeQuery()메서드는 결과를 ResultSet으로 리턴한다. 이 ResultSet으로 부터 웒는 데이터를
	      추출하는 과정이 ResultSet처리과정이다.
	      
	      데이터를 추출하는 방법은 ResultSet에서 한행(row)씩 이동하면서 getXXX()메서드를 이용하여
	      데이터를 추출하는데 이때 rs.getString(1) 또는 rs.getString("컬럼명")의 방법으로 사용된다.
	      
	   
	B. JDBC에서 사용되는 객체
	
	   1. DriverManager 클래스
	   
	      DriverManager클래스는 데이터원본에 JDBC드라이버를 통해 커넥션을 만드는 역할을 한다. DriverManager
	      클래스는 Class.forName()메서드를 통해 생성되는데 이 메서드는 인터페이스 드라이버를 구현하는 작업을
	      한다. 
	      
	      Class.forName()처럼 특정 클래스를 로드하면 자동으로 객체가 생성되고 DriverManager에 등록된다. 드라
	      이버를 찾지 못할 경우에는 forName()메서드는 ClassNotFoundException예외를 발생시키지 때문에 반드시
	      예외처리를 해야 한다.
	      
	      일반적으로 드라이버 클래스들은 로드될 때 자신의 인스턴스를 생성하고 자동적으로 DriverManager클래스
	      메서드를 호출하여 그 인스턴스를 등록한다. DriverManager클래스의 모든 메서드는 static이기 때문에 
	      객체를 생성할 필요가 없다.
	      
	      DriverManager클래스는 Connection인터페이스의 구현객체를 생성하는데 getConnection()메서드를 사용한다.
	      
	   2. Connection 인터페이스
	   
	      특정 데이터원본에 대한 커넥션은 Connection인터페이스가 구현된 클래스의 객체로 표현된다. 특정의 SQL
	      문을 실행시키지 전에 우선 Connection객체가 있어야 한다. Connection객체는 특정 데이터원본과 연결괸
	      커넥션을 나타내고 특정한 SQL문장을 정의하고 실행 시킬 수 있는 Statement객체를 생성할 때도 Connection
	      객체를 사용한다.
	      
	      또한, Connection객체는 데이터베이스에 대한 데이터인 메타데이터베이스에 관한 정보를 데이터원본에 질의를
	      하는데 사용한다. 이때에는 사용가능한 테이블의 이름, 특정 테이블의 컬럼 정보등이 포함된다.
	      
	   3. Statement 인터페이스
	   
	      Statement 인터페이스는 Connection객체에 의해 프로그램에 리턴되는 객체에 의해 구혀뇌는 일종의 메서드
	      집합을 정의한다.
	      
	      Statement객체는 Statement인터페이스를 구현한 객체로서 항상 인수가 없는 Connection.createStatement()
	      메서드를 호출함으로서 얻어진다.
	      
	      try {
	      	Statement stmt = conn.createStatement();
	      	...
	      catch(SQLException e) {
	        ...
	      }
	      
	      일단, Statement객체를 생성하면 Statement.executeQuery()메서드를 호출해서 SQL질의를 실행 시킬 수 있다.
	      메서드의 인자로 SQL문장을 담은 String객체를 전달한다. Statement객체는 단순 질의문을 사용하는 것이 좋다.
	   
	   4. PreparedStatement 인터페이스
	   
	      PreparedStatement인터페이스는 Connection객체의 prepareStatement()메서드를 사요해서 객체를 생성한다.
	      PreparedStatement객체는 SQL문장이 사전에 컴파일 되어 실행시간동안 파라미터값을 위한 공간을 확보한다는
	      점에서 Statement객체와 구별된다.
	      
	      PreparedStatement객체는 동일한 질의문을 특정값만 변경해서 여러번 반복 실행해야할 때, 많은 데이터를 다루기
	      때문에 질의문을 정리해야 할 필요가 있을 때, 파라미커값이 많아서 질의문을 정리해야할 필요가 있을 때 사용
	      하면 유용하다. 또한, Statement객체의 SQL은 실행될 때 마다 DB서버에서 분석되어야 하지만 PreparedStatement
	      객체는 한번만 분석되면 재사용이 용이하다는 장점이 있다.
	      
	      PreparedStatement인터페이스는 각각의 인수에 대한 위치홀더(placeholder = ?)를 사용하여 SQL문장을 정의할 수 
	      있게 해준다. 위치홀더는 물음표(?)로 표현된다. 위치홀더는 SQL문장에 나타나는 토큰(token)인데 이것은 SQL문장
	      이 실행되기 전에 실제값으로 대체된다. 이런 방법을 이용하면 특정 값으로 문자열을 연결하는 방법보다 훨씬 쉽게
	      SQL문장을 만들 수 있다.
	      
	      PreparedStatement객체는 각각의 SQL타입을 처리할 수 있는 setXXX()메서드를 제공한다. 여기서 XXX는 해당 테이블
	      의 필드데이터타입과 관련이 있다. 해당 필드의 데이터타입이 문자열이면 setString()이 되고 숫자값이면 setInt()
	      가 된다. setXXX(num, valaue)메서드는 2개의 변수를 가지고 있는데 num은 파라미터인덱스(placeholde위치)이고 위
	      치홀더와 대응된다.
	      
	      PreparedStatement객체가 제공하는 메서드는
	      
	      setString, setInt, setLong, setDouble, setFloat, setDate, setTimestamp, setObject가 있다.
	      
	      PreparedStatement객체를 사용하는 것이 Statement객체를 사용하는 것 보다 좋다.
	      
	      PreparedStatement의 장점
	      
	      1) 동일한 질의문을 특정 값만 변경해서 여러번 실행애야 할때, 많은 데이터를 다룰 경우 질의문을 정리해야할
	         필요가 있을 때, 파라미터가 많아서 질의문을 정리해야 할 필요가 있을 때 좋다.
	      2) 사전에 컴파일되기 때무에 쿼리의 실행속도가 Statement객체에 비해 빠르다. 
	      3) Statement객체는 쿼리실해시 작은 따옴표(')가 들어 있으면 작은 따옴표를 2개로 표시해야 하지만 PreparedStatement
	         객체는 작은 따옴표의 문제를 쿼리실행시에 자동으로 처리하기 때문에 신경쓸 필요가 없다.
	      
	   5. CallableStatement 인터페이스
	   
	   	  CallableStatement인터페이스는 Connection객체의 prepareCall()메서드를 사용해서 객체를 생성한다. CallableStatement
	   	  객체는 주로 스토어드프로시저(Stored Procedure)를 사용하기 위해 사용한다.
	   	  
	   	  Stored Procedure는 사전에 저장된 쿼리를 사용하기 때문에 수행속도가 빠르다.
	   	  
	      try {
	      	CallableStatement sstmt = conn.prepareCall();
	      	...
	      catch(SQLException e) {
	        ...
	      }
	   	  
	   	  CallableStatement는 데이터베이스에 저장된 Stored Procedure를 단지 호출하는 것만으로 처리가 가능하다.
	   	  
	   	  --> conn.prepareCall("{ call stored_prodcedure이름}");
	   	  
	   	  CallableStatement의 종류는
	   	  1) 단순호출 : { call prodedure이름[?,?....] }
	   	  2) 호출결과값을 리턴 :  { ? = call prodedure이름[?,?....] }
	   	  3) 파라미터가 없는 프로시저 호출 :  { call prodedure이름 }
	   	  
	   6. ResultSet 인터페이스
	   
	      SQL문중에서 select문을 사용한 질의가 성공했을 경우에 결과로서 ResultSet을 반환한다. ResultSet은
	      SQL질의에 의해 생성된 테이블을 담고 있다. 또한, ResultSet객체는 커서(cursor)라고 불리는 것을 가
	      지고 있는데 그 것으로 ResultSet에서 특정행에 대한 참조를 조작할 수 있다.
	      
	      커서는 초기에 첫번쨰 행의 바로 앞을 가리키도록 되어 있는데, ResultSet객체의 next()메서드를 사용
	      하면 다음행으로 이동할 수 있다.
	      
	      ResultSet의 메서드
	      
	      1) first()       : 커서를 첫번째행으로 이동
	      2) last() 	   : 커서를 마지막행으로 이동
	      3) beforeFirst() : 커서를 첫번째행 이전으로 이동
	      4) afterLast()   : 커서를 마지막행 이후로 이동
	      5) next()        : 커서를 다음행으로 이동
	      6) previous()    : 커서를 이전행으로 이동
	      
	      ResultSet에서 행을 처리하는데 반복을 사용하며 next()메서드가 유효한 행이 있으면 true, 없으면 false를
	      리턴하는 것을 이용하여 while문으로 제어할 수 있다. ResultSet객체에서 값을 가져올 경우에 getXXX()메서드
	      를 사용한다.
	      
	      ResultSet이 제공하는 메서드는
	      
	      getString(), getInt(), getLong(), getBytes(), getDouble, getDate(), getTimestamp(), getObject() 등이 있다.
	      	   
	   7. JDBC 트랜잭션
	   
	      트랜잭션(Transaction)은 여러단계의 작업을 하나로 처리하는 것으로서 하나로 인식된 작업이 모두 성공적으로
	      끝나면 commit가 되고 하나라도 문제가 발생하면 rollback이 되어서 작업을 수행전으로 되돌린다. 이렇기 때문
	      에 Transaction은 프로그램의 신뢰도를 보장하게 된다.
	      
	      JDBC에서도 Transaction처리에 대한 메서드를 제공한다.
	      
	      1) commit()   : Transaction의 commit를 수행
	      2) rollback() : Transaction의 rollback을 수행
	      
	      기본적으로 Connection객체에 setAutoCommit(boolean autoCommit)이란 메서드가 있는데 기본값이 true로 설정이
	      되어 있다. 그렇기 때문에 JDBC는 Auto Commit이 자동으로 수행되는데 자동을 수행되는 것을 막으려면 
	      conn.setAutoCommit(false)로 지정해야 한다.

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
		System.out.println("DB연결이 성공되었습니다!");
	} catch (Exception e) {
		System.out.println("DB연결이 실패되었습니다!");
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