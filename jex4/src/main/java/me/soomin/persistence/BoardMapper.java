package me.soomin.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import me.soomin.domain.BoardVO;
@Mapper
public interface BoardMapper {
	public List<BoardVO> getList();
	
	public void insert(BoardVO boardVO);
	
	public void insertSelectKey(BoardVO boardVO);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO boardVO);
}
