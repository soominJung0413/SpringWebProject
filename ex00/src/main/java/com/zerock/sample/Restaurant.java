package com.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component
@Data
public class Restaurant {
	
	@Setter ( onMethod_ = @Autowired ) // onMethod_ �Ӽ��� ������ setter �޼��忡 @Autowired�� �߰����ش�!
	private Chef chef;//@Setter �������̼��� �����Ͻ� �ش��ʵ��� Setter�� �ڵ����� �����Ѵ�!
}
