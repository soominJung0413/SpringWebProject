package me.soomin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import me.soomin.domain.BoardVO;
import me.soomin.mappers.BoardMapper;

@Service
@Log4j
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	//생성자 묵시적 자동주입 이용
	private BoardMapper boardMapper;

	@Override
	public void register(BoardVO boardVO) {
		log.info("register >>>>>>>>>"+boardVO);
		
		boardMapper.insertSelectKey(boardVO);
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("get...................."+bno);
		return boardMapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO boardVO) {
		log.info("modify..................... "+boardVO);
		
		return boardMapper.update(boardVO) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove ..................... "+bno);
		
		return boardMapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getList() {
		log.info("get List ......................");
		return boardMapper.getList();
	}

}
