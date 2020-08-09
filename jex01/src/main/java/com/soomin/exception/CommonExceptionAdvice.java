package com.soomin.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
	
	@ExceptionHandler(Exception.class)
	public void logException(Exception ex) {
		log.error(ex.getMessage());
		log.error(ex.getStackTrace());
	}
	
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String hadle404(NoHandlerFoundException ex, Model model) {
		log.error("Exception : "+ex.getMessage());
		return "exception/handle404";
	}
}
