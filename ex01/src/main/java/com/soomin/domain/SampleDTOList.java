package com.soomin.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SampleDTOList {
	
	private List<SampleDTO> list;
	
	// 커멘드 객체는 스프링이 자동생성하므로 필드값에 미리 ArrayList를 만드는 것을 고려해 설계한다!
	public SampleDTOList() {
		this.list = new ArrayList<SampleDTO>();
	}
}
