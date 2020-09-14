package me.soomin.mappers;

import java.util.List;

import me.soomin.domain.BoardAttachVO;

public interface BoardAttatchMapper {

	public void insert(BoardAttachVO vo);

	public void delete(String uuid);

	public List<BoardAttachVO> findByBno(Long bno);
}
