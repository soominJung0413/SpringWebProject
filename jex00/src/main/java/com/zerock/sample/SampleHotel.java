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
@AllArgsConstructor// @ AllArgsConstructor �� ��� �ʵ带 ������ �����ڸ� ����� �ش�! 4.3 ������ ������ �ڵ� �������� �����ڿ� Bean �ڵ�����!
public class SampleHotel {

	private Chef chef;
	
}
