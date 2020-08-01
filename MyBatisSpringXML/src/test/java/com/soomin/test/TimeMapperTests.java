package com.soomin.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.soomin.mapper.TImeMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private TImeMapper timeMapper;
	
	@Test
	@Order(1)
	public void testGetTime() {
		log.info("TimeMapper 클래스이름 :"+timeMapper.getClass().getName());
		log.info("TimeMapper 이용 : "+timeMapper.getTime());
	}
	
	@Test
	@Order(2)
	public void testGetTime2() {
		
		log.info("getTime2");
		log.info(timeMapper.getTime2());
		
	}
	
}
