package me.soomin.persistence;

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
import me.soomin.config.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class})
@WebAppConfiguration
@Log4j
public class MybatisTest {

	@Autowired
	private  SqlSessionFactory sessionFactory;
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	@Test
	public void testSqlsession() {
		assertThat(sessionFactory, notNullValue());
		assertThat(sessionTemplate, notNullValue());
		
		log.info("SqlSessionFactoryBean >>>>>>>>>>>"+sessionFactory);
		log.info("SqlSessionTemplate >>>>>>>>>>>>>>"+sessionTemplate);
	}
}
