package com.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component
@Data
public class Restaurant {
	
	@Setter ( onMethod_ = @Autowired ) // onMethod_ 속성은 생성한 setter 메서드에 @Autowired를 추가해준다!
	private Chef chef;//@Setter 에노테이션은 컴파일시 해당필드의 Setter를 자동으로 생성한다!
}
