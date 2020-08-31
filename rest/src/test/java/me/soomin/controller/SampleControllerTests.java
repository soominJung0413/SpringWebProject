package me.soomin.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import me.soomin.domain.Ticket;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@WebAppConfiguration
@Log4j
public class SampleControllerTests {
	private MockMvc mockMvc;

	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext applicationCtx;

	@org.junit.Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(applicationCtx).build();
	}

	@Test
	public void testTicket() throws Exception {
		Ticket ticket = new Ticket();
		ticket.setTno(123);
		ticket.setOwner("Admin");
		ticket.setGrade("AAA");

		String ticketStr = new Gson().toJson(ticket);

		log.info(ticketStr);

		mockMvc.perform(MockMvcRequestBuilders.post("/sample/ticket").contentType(MediaType.APPLICATION_JSON)
				.content(ticketStr)).andExpect(MockMvcResultMatchers.status().is(200));

	}
}
