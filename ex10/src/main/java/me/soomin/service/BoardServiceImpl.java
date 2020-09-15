package me.soomin.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import me.soomin.domain.BoardVO;
import me.soomin.domain.Criteria;
import me.soomin.mappers.BoardAttatchMapper;
import me.soomin.mappers.BoardMapper;

@Service
@Log4j
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardAttatchMapper attatchMapper;

	// 생성자 묵시적 자동주입 이용
	@Autowired
	private BoardMapper boardMapper;

	@Transactional(rollbackFor=SQLException.class)
	@Override
	public void register(BoardVO boardVO) {
		log.info("register >>>>>>>>>" + boardVO);

		boardMapper.insertSelectKey(boardVO);
		if(boardVO.getAttachList() == null || boardVO.getAttachList().size() == 0) {
			return;
		}
		
		boardVO.getAttachList().forEach( attach -> {
			attach.setBno(boardVO.getBno());
			attatchMapper.insert(attach);
		});
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("get...................." + bno);
		return boardMapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO boardVO) {
		log.info("modify..................... " + boardVO);

		return boardMapper.update(boardVO) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove ..................... " + bno);

		return boardMapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getList(Criteria criteria) {
		log.info("get List ......................");
		return boardMapper.getListWithPaging(criteria);
	}

	@Override
	public int getTotal(Criteria criteria) {
		log.info("get total count");
		return boardMapper.getTotalCount(criteria);
	}

}
