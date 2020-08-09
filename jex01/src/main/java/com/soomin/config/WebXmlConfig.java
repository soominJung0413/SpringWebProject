package com.soomin.config;

import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebXmlConfig extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{RootConfig.class};
		
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {WebMvcConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	@Override//web.xml 설정 변경 메서드
	protected void customizeRegistration(Dynamic registration) {
		//InitParam 추가
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}

}
