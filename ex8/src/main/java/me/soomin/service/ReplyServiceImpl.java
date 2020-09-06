package me.soomin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;
import me.soomin.domain.Criteria;
import me.soomin.domain.ReplyPageDTO;
import me.soomin.domain.ReplyVO;
import me.soomin.mappers.BoardMapper;
import me.soomin.mappers.ReplyMapper;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper mapper;
	@Autowired
	private BoardMapper boardMapper;

	@Transactional
	@Override
	public int register(ReplyVO replyVO) {
		log.info("register ......." + replyVO);

		boardMapper.updateReplyCnt(replyVO.getBno(), 1);
		return mapper.insert(replyVO);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("get......." + rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO replyVO) {
		log.info("modify ........" + replyVO);
		return mapper.update(replyVO);
	}

	@Transactional
	@Override
	public int remove(Long rno) {
		log.info("remove........." + rno);

		ReplyVO vo = mapper.read(rno);

		boardMapper.updateReplyCnt(vo.getBno(), -1);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("get Reply List of a Board " + bno);
		return mapper.getListWithPaging(cri, bno);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		return new ReplyPageDTO(mapper.getCountByBno(bno), mapper.getListWithPaging(cri, bno));
		// 생성자로 db에서 추출한 리스트와 카운트 수를 모두 넘겨주었다.
	}

}
