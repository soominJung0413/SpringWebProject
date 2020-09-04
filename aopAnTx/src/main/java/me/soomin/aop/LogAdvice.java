package me.soomin.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {

	@Before("execution( * me.soomin.service.SampleService*.*(..) )")
	public void logBefore() {
		log.info("======================================");
	}

	@Before("execution(* me.soomin.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
	public void logBeforeWithParam(String str1, String str2) {
		log.info("Str1 : " + str1);
		log.info("Str2 : " + str2);
	}

	@AfterThrowing(pointcut = "execution(* me.soomin.service.SampleService*.*(..))", throwing = "exception")
	public void logException(Exception exception) {
		log.info("Exception : " + exception);
	}

	@Around("execution( * me.soomin.service.SampleService*.*(..) )")
	public Object logTime(ProceedingJoinPoint point) {
		long start = System.currentTimeMillis();

		log.info("Target : " + point.getTarget());
		log.info("Param : " + Arrays.toString(point.getArgs()));
		Signature sig = point.getSignature();

		log.info("Sig getName : " + sig.getName());
		log.info("Sig getLongString : " + sig.toLongString());
		log.info("Sig getShrotString : " + sig.toShortString());

		Object result = null;

		try {
			result = point.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();

		log.info("Time : " + (end - start));

		return result;
	}

}
