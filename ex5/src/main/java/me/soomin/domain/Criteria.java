package me.soomin.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criteria {
	
	private int pageNum;
	private int Amount;
	
	public Criteria() {
		this(1,10);
	}
	public Criteria(int pageNum, int Amount) {
		this.pageNum = pageNum;
		this.Amount = Amount;
	}

}
