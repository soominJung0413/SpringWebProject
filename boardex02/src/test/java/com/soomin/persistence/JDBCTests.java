package com.soomin.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class JDBCTests {
	
	@Setter(onMethod_ = @Autowired)
	private DataSource dataSource;
	
	@Setter(onMethod_ = @Autowired)
	private SqlSessionFactory sqlSessionFactory;
	
	@Setter(onMethod_ = @Autowired)
	private  SqlSessionTemplate sqlSessionTemplate;
	
	@Value("${dbcp.url}")
	private  String url;
	
	@Value("${dbcp.driver}")
	private  String driver;
	
	@Value("${dbcp.name}")
	private  String username;
	
	@Value("${dbcp.password}")
	private  String password;
	
	static {
		try {
			Class.forName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testConnectionToJDBC() {
		try(Connection con = DriverManager.getConnection(url,username,password)){
			log.info("JDBCDriver Connection........ "+con);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testJDBC() {
		
	}
	
	@Test
	public void testDatatSource() {
			assertNotNull(dataSource);
		try(Connection con = dataSource.getConnection()){
			log.info("Conection .......... "+con);
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testMybatis() {
		assertNotNull(sqlSessionFactory);
		
		try(SqlSession session = sqlSessionFactory.openSession();Connection con = session.getConnection();
				){
			
			log.info("SqlSessionTemplate........"+sqlSessionTemplate);
			log.info(session);
			log.info(con);
			
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
}
