package me.soomin.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import me.soomin.domain.BoardVO;
import me.soomin.service.BoardService;

@Controller
@Log4j
@RequestMapping(value="/board/*")
@AllArgsConstructor
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list");
		model.addAttribute("list", boardService.getList());
	}
	
	@PostMapping("/register")
	public String register(BoardVO boardVO, RedirectAttributes attributes) {
		log.info("register..."+boardVO);
		
		log.info("................"+Arrays.toString(boardService.getClass().getMethods()));
		boardService.register(boardVO);
		
		attributes.addFlashAttribute("result", boardVO.getBno());
		
		return "redirect:/board/list";
	}
}
