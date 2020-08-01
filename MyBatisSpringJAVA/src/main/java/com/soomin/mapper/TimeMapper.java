package com.soomin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


public interface TimeMapper {

	@Select("SELECT sysdate FROM DUAL")
	public String getTime();
		
	
	public String getTime2();
}
