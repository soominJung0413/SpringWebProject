package com.soomin.mapper;

import org.apache.ibatis.annotations.Select;

public interface TImeMapper {
	
	@Select("SELECT sysdate FROM dual") // 맵퍼 인터페이스 에 마이바티스 어노테이션으로 sql 문을 사용할 수 있다!
	String getTime();
	
	public String getTime2();
}
