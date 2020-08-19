package me.soomin.persistence;

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

import lombok.extern.log4j.Log4j;
import me.soomin.config.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class})
@WebAppConfiguration
@Log4j
public class JdbcTest {
	
	@Value("${dbcp.driver}")
	private String driver;
	
	@Value("${dbcp.url}")
	private String url;
	
	@Value("${dbcp.user}")
	private String user;
	
	@Value("${dbcp.password}")
	private String password;
	
	
	static {
		try {
			Class.forName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testJdbc() {
		try(Connection con = DriverManager.getConnection(url,user,password)){
			log.info("Connection >>>>>>>>>>>>> "+con);
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
}
