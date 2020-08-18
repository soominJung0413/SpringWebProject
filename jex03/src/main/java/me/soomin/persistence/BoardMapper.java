package me.soomin.persistence;

import java.util.List;

import me.soomin.domain.BoardVO;

public interface BoardMapper {
	public List<BoardVO> getList();
	
	public void insert(BoardVO boardVO);
	
	public void insertSelectKey(BoardVO boardVO);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO boardVO);
}
