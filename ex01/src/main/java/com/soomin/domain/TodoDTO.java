package com.soomin.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TodoDTO {
	@NonNull
	private String title;
	
	//해당 패턴을 가지는 문자열을 인스턴스 변수로 객체화한다.
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dueDate;
}
