package com.soomin.test;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.soomin.config.RootConfig;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class JdbcTest {
	
	@Setter(onMethod_ = @Autowired)
	private DataSource dataSource;
	
	@Setter(onMethod_ = @Autowired)
	private SqlSessionFactory sqlSessionFactory;
	
	
	@Test
	@Order(0)
	public void testDataSource() {
		
		try(Connection con = dataSource.getConnection()){
			
			log.info("Datasource Connection 상태 : "+con);
			
		}catch(Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	
	@Test
	@Order(1)
	public void testMybatis() {
		
		try(SqlSession session = sqlSessionFactory.openSession(); 
				Connection con = session.getConnection();
				){
			
			log.info("SqlSession 상태 : "+session);
			log.info("Connection 상태 : "+con);
			
		}catch(Exception e) {
			fail(e.getMessage());
		}
		
	}

}
