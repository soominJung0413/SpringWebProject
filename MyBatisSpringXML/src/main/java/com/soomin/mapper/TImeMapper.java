package com.soomin.mapper;

import org.apache.ibatis.annotations.Select;

public interface TImeMapper {
	
	@Select("SELECT sysdate FROM dual") // ���� �������̽� �� ���̹�Ƽ�� ������̼����� sql ���� ����� �� �ִ�!
	String getTime();
	
	public String getTime2();
}
