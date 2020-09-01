package me.soomin.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import me.soomin.domain.Criteria;
import me.soomin.domain.ReplyVO;
import me.soomin.mappers.ReplyMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

	private Long[] bnoArr = { 5767188L, 5767187L, 5767186L, 5767185L, 5767184L };

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;

//	@Test
	public void testExists() {
		log.info(mapper);
	}

//	@Test
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO vo = new ReplyVO();

			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글 테스트" + i);
			vo.setReplyer("replyer" + i);

			mapper.insert(vo);
		});
	}

//	@Test
	public void testRead() {
		Long bno = 5L;

		ReplyVO vo = mapper.read(bno);

		log.info(vo);

	}

//	@Test
	public void testDelete() {
		Long rno = 1L;

		mapper.delete(rno);
	}

//	@Test
	public void testUpdate() {
		Long rno = 10L;

		ReplyVO vo = mapper.read(rno);

		vo.setReply("update Reply Content");

		int result = mapper.update(vo);

		log.info("REsult ::::: " + result);
	}

	@Test
	public void testListWithPaging() {
		Criteria criteria = new Criteria();

		List<ReplyVO> replies = mapper.getListWithPaging(criteria, bnoArr[0]);

		replies.forEach(r -> log.info(r));
	}
}
