package me.soomin.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import me.soomin.domain.BoardVO;
import me.soomin.domain.Criteria;


public interface BoardMapper {
	
	/*@Select("SELECT * FROM TBL_BOARD where bno > 0")*/
	public List<BoardVO> getList();
	
	public void insert(BoardVO boardVO);
	
	public void insertSelectKey(BoardVO boardVO);
	
	public BoardVO read(Long bno);

	public int delete(Long bno);
	
	public int update(BoardVO boardVO);
	
	public List<BoardVO> getListWithPaging(Criteria cri);
}
