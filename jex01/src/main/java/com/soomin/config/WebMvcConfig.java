package com.soomin.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.soomin.controller","com.soomin.exception"})
public class WebMvcConfig implements WebMvcConfigurer{
	
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() throws IOException {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();//파일 업로드 시 사용하는 객체
		resolver.setDefaultEncoding("utf-8");//한글깨짐현상방지
		resolver.setMaxInMemorySize(1024*1024);//해당 메모리를 넘어서면 임시저장
		resolver.setUploadTempDir(new FileSystemResource("C:\\upload\\tmp"));// 임시저장될 경로 디렉토리
		resolver.setMaxUploadSize(1024*1024*10);//하나의 Request 에 업로드할 수 있는 최대 파일 크기
		resolver.setMaxUploadSizePerFile(1024*1024*2);//하나의 파일당 업로드 할 수 있는 최대 파일 크기
		return resolver;		
	}
	
	
}
