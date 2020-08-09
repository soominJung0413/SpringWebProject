package com.soomin.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.soomin.domain.SampleDTO;
import com.soomin.domain.SampleDTOList;
import com.soomin.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller  // 해당 클래스를 스프링에서 관리하여 Controller 로 사용해달라는 요청표시
@RequestMapping(value = "/sample/*", method = RequestMethod.GET) // 클래스 선언부에 @RequestMapping 을 붙이면 클래스 안의 모든 메서드들의 기본 URL 경로가 된다!
@Log4j
public class SampleController {
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(format, false));
//		}
	@PostMapping(value = "/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		files.forEach( f -> {
			log.info("------------------------------------------");
			log.info("name : " + f.getOriginalFilename());
			log.info("size : " + f.getSize() );
		});
	}
	
	@GetMapping(value = "/exUpload")
	public void exUpload() {
		log.info("exUpload...............");
	}
	
	
	@GetMapping(value = "/ex07")
	public ResponseEntity<String> ex07(){
		log.info("/ex07........");
		
		String message = "{\"name\" : \"홍길동\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json; charset=UTF-8");

		//body header status
		return new ResponseEntity<String>(message, header, HttpStatus.OK);
	}
	
	@GetMapping(value = "/ex06")//@ResponseBody 는 리턴타입에 붙이고 VO 나 DTO를 리턴타입으로 넘겨준다.
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06.........");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		return dto;
	}
	
	// url 경로의 jsp 파일을 뷰로 리턴하는 것과 동일.
	@GetMapping(value = "/ex05")
	public void ex05() {// 컨트롤러 리턴타입이 void라면 그대로 jsp파일 호출
		log.info("/ex05......");
	}
	
	@GetMapping(value = "/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("dto : "+dto);
		log.info("page : "+page);
		return "/sample/ex04";
	}
	
	@GetMapping(value = "/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo : "+todo);
		return "ex03";
	}
	
	@RequestMapping(value = "/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dto : " +list);
		return "ex02Bean";
	}
	
	@RequestMapping(value = "/ex02Array")
	public String ex02Array(@RequestParam(name = "ids") String[] ids) {
		log.info("array ids : "+Arrays.toString(ids));
		return "ex02Array";
	}
	
	@RequestMapping(value = "/ex02List")
	public String ex02List(@RequestParam(name = "ids") ArrayList<String> ids) {
		
		log.info("ids : "+ids);
		
		return "exList";
	}
	
	@RequestMapping(method = RequestMethod.GET ,value = "/ex01")
	public String ex01(SampleDTO dto) {
		
		log.info(dto);
		
		return "ex01";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/ex02")
	public String ex02(@RequestParam(name = "name",required = true) String name, @RequestParam(name = "age",defaultValue = "0", required = false) int age) {
			//객체가 아니라 원하는 파라미터를 따로 받아내고 싶다면 @RequestParam 에노테이션 으로 이름을 주어 받아낼 수 있다 required 속성의 기본값은 true 이니 조심한다.
			// 폼에서 넘어오는 파라미터 이름과 데이터를 받아내는 파라미터 이름이 다를경우에 유용하게 사용됨!
			log.info(name);
			log.info(age);
			return "ex02";
	}
	
	
	@RequestMapping(value = "")
	public void basic() {
		
		log.info("basic.........................");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet() {
		log.info("basic get only....");
	}
}
