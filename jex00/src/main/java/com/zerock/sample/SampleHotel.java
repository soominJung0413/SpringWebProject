package com.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Setter
@Getter
@ToString
@AllArgsConstructor// @ AllArgsConstructor 는 모든 필드를 가지고 생성자를 만들어 준다! 4.3 이후의 묵시적 자동 주입으로 생성자에 Bean 자동주입!
public class SampleHotel {

	private Chef chef;
	
}
