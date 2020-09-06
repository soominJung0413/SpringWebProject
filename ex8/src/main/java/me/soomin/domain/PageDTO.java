package me.soomin.domain;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PageDTO {
	private int startPage;
	private int endPage;
	boolean prev, next;

	private int total;
	private Criteria criteria;

	public PageDTO(Criteria criteria, int total) {
		this.criteria = criteria;
		this.total = total;

		this.endPage = (int) (Math.ceil(criteria.getPageNum() / 10.0)) * 10; // 10
		this.startPage = this.endPage - (10 - 1); // 10 에서 끝번호,

		int realEnd = (int) (Math.ceil(((total * 1.0)) / criteria.getAmount()));//
		System.out.println("::::::::::::::::::::::::::::" + Math.ceil(123.0 / 10));
		System.out.println("::::::::::::::::::::::::::::" + realEnd);
		if (realEnd < this.endPage) {
			endPage = realEnd;
		}

		this.prev = startPage > 1;
		this.next = endPage < realEnd;
	}

}
