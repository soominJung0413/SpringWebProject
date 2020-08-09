package com.soomin.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice(basePackages = "com.soomin.controller")
@Log4j
@Controller
public class CommonExceptionAdvice {
	
//	@RequestMapping("/**")
//    public String handlerNotMappingRequest(HttpServletRequest request, HttpServletResponse response, HttpHeaders httpHeaders)
//            throws NoHandlerFoundException {
//        throw new NoHandlerFoundException("No handler mapping found.", request.getRequestURL().toString(), httpHeaders);
//    }
	
//	@ExceptionHandler({Exception.class})
//	public String except(Exception ex, Model model) {
//		log.error("Exception : "+ex.getMessage());
//		model.addAttribute("exception", ex);
//		log.error(model);
//		return "error_page";
//	}
	
//	@ExceptionHandler(NoHandlerFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public String handle404(NoHandlerFoundException ex) {
//		return "custom404";
//	}
}
