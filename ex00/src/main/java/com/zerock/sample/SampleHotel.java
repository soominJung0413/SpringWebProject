package com.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Component
@ToString
@Getter
@RequiredArgsConstructor  // @RequiredArgsConstructor 는 @NonNull 과 final이 붙은 인스턴스 변수를 가지는 생성자를 만들어 낸다!
public class SampleHotel {
	
	@NonNull
	private Chef chef;
	
	// 스프링 4.3 이후 버전 부터는 묵시적 자동주입으로 인해 생성자에서 Bean이 자동주입된다!!
//	public SampleHotel(Chef chef) {
//		this.chef = chef;
//	}
}
