package me.soomin.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.soomin.domain.Criteria;
import me.soomin.domain.ReplyVO;

public interface ReplyMapper {

	public int insert(ReplyVO replyVO);

	public ReplyVO read(Long rno);

	public int delete(Long rno);

	public int update(ReplyVO replyVO);

	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria criteria, @Param("bno") Long bno);

	public int getCountByBno(Long bno);
}
