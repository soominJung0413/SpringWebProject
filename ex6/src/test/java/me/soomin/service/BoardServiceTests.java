package me.soomin.service;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import me.soomin.domain.BoardVO;
import me.soomin.domain.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
@WebAppConfiguration
@Log4j
public class BoardServiceTests {

	@Setter(onMethod_ = @Autowired)
	private BoardService boardService;

//	@Test
	public void testExist() {
		log.info(boardService);
		assertThat(boardService, notNullValue());
	}

//	@Test
	public void testRegister() {
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("새로 작성하는 글");
		boardVO.setContent("새로 작성하는 내용");
		boardVO.setWriter("newbie");

		boardService.register(boardVO);

		log.info("생성된 게시물의 번호 : " + boardVO.getBno());
	}

	@Test
	public void tesgGetList() {
//		boardService.getList().forEach(board -> log.info(board));
		boardService.getList(new Criteria(2, 10)).forEach(list -> {
			log.info(list);
		});
	}

//	@Test
	public void testGet() {
		log.info(boardService.get(1L));
	}

//	@Test
	public void testDelete() {
		log.info("REMOV RESULT >>>>>>>>>>>>>>>>>>>> " + boardService.remove(1L));
	}

//	@Test
	public void testModify() {
		BoardVO boardVO = boardService.get(2L);
		if (boardVO == null) {
			return;
		}
		boardVO.setTitle("제목 수정합니다.");
		log.info("MODIFY RESULT >>>>>>>>>>>>>>>>>>" + boardService.modify(boardVO));
	}
}
