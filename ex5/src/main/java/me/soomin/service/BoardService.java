package me.soomin.service;

import java.util.List;

import me.soomin.domain.BoardVO;
import me.soomin.domain.Criteria;

public interface BoardService {

	public int getTotal(Criteria criteria);

	public void register(BoardVO boardVO);

	public BoardVO get(Long bno);

	public boolean modify(BoardVO boardVO);

	public boolean remove(Long bno);

	public List<BoardVO> getList(Criteria criteria);
}
