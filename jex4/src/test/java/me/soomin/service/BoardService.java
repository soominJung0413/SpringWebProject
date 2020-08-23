package me.soomin.service;

import java.util.List;

import me.soomin.domain.BoardVO;

public interface BoardService {
	public void regiseter(BoardVO boardVO);

	public BoardVO get(Long bno);

	public boolean modify(BoardVO boardVO);

	public boolean remove(Long bno);

	public List<BoardVO> getList();
}
