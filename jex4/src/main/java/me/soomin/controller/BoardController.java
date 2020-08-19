package me.soomin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import me.soomin.service.BoardService;

@Controller
@Log4j
@RequestMapping(value="/board/*")
@AllArgsConstructor
public class BoardController {
	private BoardService boardService;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list");
		model.addAttribute("list", boardService.getList());
	}
}
