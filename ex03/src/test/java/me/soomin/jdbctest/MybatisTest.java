package me.soomin.jdbctest;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/root-context.xml")
@WebAppConfiguration
@Log4j
public class MybatisTest {
	
	@Autowired
	private SqlSessionFactory sessionFactory;
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	@Test
	public void testMyBaits() {
			
		assertThat(sessionFactory, notNullValue());
		assertThat(sessionTemplate, notNullValue());
		
		log.info("SqlSession Factory >>>>>>>>>>>>>>>>>> "+sessionFactory);
		log.info("SqlSessionTemplate >>>>>>>>>>>>>>>>>>> "+sessionTemplate);
	}
}
