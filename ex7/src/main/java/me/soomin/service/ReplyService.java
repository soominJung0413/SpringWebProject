package me.soomin.service;

import java.util.List;

import me.soomin.domain.Criteria;
import me.soomin.domain.ReplyVO;

public interface ReplyService {

	public int register(ReplyVO replyVO);

	public ReplyVO get(Long rno);

	public int modify(ReplyVO replyVO);

	public int remove(Long rno);

	public List<ReplyVO> getList(Criteria cri, Long bno);

}
