package persistenceTest;

import static org.junit.Assert.assertThat;

import org.apache.ibatis.session.SqlSessionFactory;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@WebAppConfiguration
@Log4j
public class MyBatisTest {

	@Autowired
	private SqlSessionFactory sessionFactory;

	@Autowired
	private SqlSessionTemplate sessionTemplate;

	@Test
	public void testSqlSession() {
		assertThat(sessionFactory, IsNull.notNullValue());
		log.info("Sql SessionFactory <<<" + sessionFactory);
		log.info("Sql SessionTemplate <<<" + sessionTemplate);
	}
}
