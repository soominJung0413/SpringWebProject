package me.soomin.mapper;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.log4j.Log4j;
import me.soomin.domain.BoardVO;
import me.soomin.mappers.BoardMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/root-context.xml")
@WebAppConfiguration
@Log4j
public class TestBoardMapper {
	
	@Autowired
	private BoardMapper boardMapper;
	
//	@Test
	public void testMapper() {
		assertThat(boardMapper, notNullValue());
	}
	
//	@Test
	public void testInsert() {
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("새로 작성하는 글");
		boardVO.setContent("새로 작성하는 내용");
		boardVO.setWriter("newbie");
		boardMapper.insert(boardVO);
		
		log.info(boardVO);
	}
	
//	@Test
	public void testInsertSelectKey() {
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("새로 작성하는 글 selectKey");
		boardVO.setContent("새로 작성하는 내용 selectKey");
		boardVO.setWriter("newbie selectKey");
		
		boardMapper.insertSelectKey(boardVO);
		
		log.info(boardVO);
		
	}
	
//	@Test
	public void testRead() {
		BoardVO boardVO = boardMapper.read(5L);
		
		log.info(boardVO);
	}
	
//	@Test
	public void testDelete() {
		log.info("DELETE COUNT : "+boardMapper.delete(3L));
	}
	
	@Test
	public void testUpdate() {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(4L);
		boardVO.setTitle("수정된 제목");
		boardVO.setContent("수정된 내용");
		boardVO.setWriter("수정된 작성자");
		
		int count = boardMapper.update(boardVO);
		
		log.info("UPDATE COUNT : "+count);
	}
}
