package com.soomin.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value = "/sample/*")
@Log4j
public class SampleController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/file")
	public void uploadFileView() {		
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/file")
	public void uploadFile(ArrayList<MultipartFile> files) {
		files.forEach( f -> {
			log.info("----------------------------------");
			log.info("file : "+f.getOriginalFilename());
			log.info("size : "+f.getSize());
		});
	}
	
	
}
