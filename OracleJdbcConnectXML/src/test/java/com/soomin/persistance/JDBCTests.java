package com.soomin.persistance;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//패키지에서 드라이버 호출
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:XE","book_ex","1234")){// try-wtih-resource 블록으로 커넥션 생성
			
			log.info(con); // 객체의 로그를 찍어봄
			
		}catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());// 실패시 에러로그를 확인
		}
	}
	
}
