package com.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)//@RunWith ��  �ش� TestCode�� Spring�� �����Ѵٴ� ���� �˷��ش�!
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")//@ContextConfiguration �� �ش� ������ ������ ����Ѵٴ� ���� �˷��ش�!
//@ContextConfiguration�� �Ӽ� ���� classpath: �� file: �� ����! �� ���������� Location�� ��´�!
//��� : @ContextConfiguration �� ������ Ŭ������ ���ڿ��� �̿��Ͽ� �ʿ��� ��ü���� ���������� ��ü�� ��Ͻ�Ŵ�� �����Ѵ�!
@Log4j//@Log4j�� Lombok �� �̿��Ͽ� �α׸� �����ϴ� Logger ������ �������ش�!! Log4j ���������� �Ǿ��ִٸ� Logger��ü�� ������ �ʿ䰡 ����!!!
public class SampleTest {
	
	@Setter(onMethod_ = @Autowired) // @Setter : Lombok�� �ʵ忡 �´� setter�� ������ְ� onMethod �Ӽ����� setter�� @Autowired �� �ٿ� ��ü�� �ʿ��� ��ȣ�� ������!
	private Restaurant restaurant;
	
	@Test// @Test : Junit�� �̿��� �׽�Ʈ����� ǥ�����ش�!!
	public void testExist() {
		
		assertNotNull(restaurant);
		
		log.info(restaurant);
		log.info("-------------------------------------------------");
		log.info(restaurant.getChef());
		
	}

}
