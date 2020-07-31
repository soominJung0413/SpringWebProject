package com.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)//@RunWith 은  해당 TestCode가 Spring을 실핸한다는 것을 알려준다!
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")//@ContextConfiguration 은 해당 스프링 설정을 사용한다는 것을 알려준다!
//@ContextConfiguration의 속성 값은 classpath: 나 file: 을 쓴다! 즉 설정파일의 Location을 잡는다!
//결론 : @ContextConfiguration 은 지정된 클래스나 문자열을 이용하여 필요한 객체들을 스프링내에 객체로 등록시킴을 선언한다!
@Log4j//@Log4j는 Lombok 을 이용하여 로그를 생성하는 Logger 변수를 생성해준다!! Log4j 의존설정이 되어있다면 Logger객체를 선언할 필요가 없다!!!
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
