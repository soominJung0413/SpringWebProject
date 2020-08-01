package com.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Component
@ToString
@Getter
@RequiredArgsConstructor  // @RequiredArgsConstructor �� @NonNull �� final�� ���� �ν��Ͻ� ������ ������ �����ڸ� ����� ����!
public class SampleHotel {
	
	@NonNull
	private Chef chef;
	
	// ������ 4.3 ���� ���� ���ʹ� ������ �ڵ��������� ���� �����ڿ��� Bean�� �ڵ����Եȴ�!!
//	public SampleHotel(Chef chef) {
//		this.chef = chef;
//	}
}
