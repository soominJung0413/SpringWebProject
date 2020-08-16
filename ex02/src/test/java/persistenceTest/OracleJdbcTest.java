package persistenceTest;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class OracleJdbcTest {

	private static String driverName = "net.sf.log4jdbc.sql.jdbcapi.DriverSpy";
	@Value("${dbcp.url}")
	private String url;
	@Value("${dbcp.user}")
	private String username;
	@Value("${dbcp.password}")
	private String userpassword;
	static {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testJdbcConnection() {

		try (Connection con = DriverManager.getConnection(url, username, userpassword)) {
			log.info("Connection >>" + con);
		} catch (SQLException e) {
			fail("Fail >>" + e.getMessage());
		}

	}

}
