package me.soomin.jdbctest;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import junit.framework.Assert;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/root-context.xml")
@WebAppConfiguration
@Log4j
public class OracleJdbcTest {
	
	@Value("${dbcp.url}")
	private String url;
	
	@Value("${dbcp.user}")
	private String username;
	
	@Value("${dbcp.password}")
	private String password;
	
	

	static {
		try {
			Class.forName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testJdbc() {
		try(Connection con = DriverManager.getConnection(url,username,password)){
			assertThat(con, notNullValue());
			log.info("Connection >>>>> "+con);
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
}
