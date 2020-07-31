package com.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zerock.config.RootConfig;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class SampleTest {
	
	@Setter(onMethod_ = @Autowired) // @Setter : Lombok이 필드에 맞는 setter를 만들어주고 onMethod 속성으로 setter에 @Autowired 를 붙여 객체가 필요한 신호를 보낸다!
	private Restaurant restaurant;
	
	@Test// @Test : Junit을 이용한 테스트대상을 표시해준다!!
	public void testExist() {
		
		assertNotNull(restaurant);
		
		log.info(restaurant);
		log.info("-------------------------------------------------");
		log.info(restaurant.getChef());
		
	}
}
