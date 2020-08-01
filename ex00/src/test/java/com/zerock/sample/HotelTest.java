package com.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
//Lombok 으로 로그객체 생성
public class HotelTest {
	
	@Setter(onMethod_ = @Autowired)
	private SampleHotel hotel;
	
	@Test
	public void TestExist() {
		
		assertNotNull(hotel);
		
		log.info(hotel);
		log.info("---------------------------------------");
		log.info(hotel.getChef());
	}
	
}
