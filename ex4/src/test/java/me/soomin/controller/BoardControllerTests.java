package me.soomin.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})//Spring MVC 설정파일도 같이 로딩
@WebAppConfiguration//톰캣에 의존성을 가진 객체가 있을 경우 선언
@Log4j
public class BoardControllerTests {
	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;//Mvc의 가짜객체
	
	@Before//항상 테스트 전에 실행
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();// MockMvcBuilders 의 정적메서드 webAppContextSetup 으로 가짜 webAppCtx 객체를 만들고 MockMvc 객체로 만든다.
	}
	
//	@Test
	public void testList() throws Exception{
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))// 가짜 Request를 .get 방식으로 보내고, 
				.andReturn()//리턴값을받아
				.getModelAndView()//모델&뷰로만들고
				.getModelMap()//결과를 Map으로 만든다.
				);
		
	}
	
//	@Test
	public void testRegister() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title", "테스트 새글 제목")
				.param("content", "테스트 새글 내용")
				.param("writer", "user00")
				).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
	
//	@Test
	public void testGet() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/get")
						.param("bno", "2")).andReturn().getModelAndView().getModelMap()			
				);
	}
	
//	@Test
	public void testModify() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("bno", "2")
				.param("title", "수정된 테스트 새글 제목")
				.param("content", "수정된 테스트 새글 내용")
				.param("writer", "user00")
				).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
	
	@Test
	public void testRemove() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "25")
				).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
	
}
