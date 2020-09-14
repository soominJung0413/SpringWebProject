package me.soomin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import me.soomin.domain.BoardVO;
import me.soomin.domain.Criteria;
import me.soomin.domain.PageDTO;
import me.soomin.service.BoardService;

@Controller
@Log4j
@RequestMapping(value = "/board/*")
@AllArgsConstructor
public class BoardController {

	private BoardService boardService;

//	@GetMapping("/list")
//	public void list(Model model) {
//
//		log.info("list");
//		model.addAttribute("list", boardService.getList());
//	}
	@GetMapping("/list")
	public void list(Model model, Criteria criteria) {
		log.info("list" + criteria);
		model.addAttribute("list", boardService.getList(criteria));
//		model.addAttribute("pageMaker", new PageDTO(criteria, 123));

		int total = boardService.getTotal(criteria);

		model.addAttribute("pageMaker", new PageDTO(criteria, total));
	}

	@GetMapping("/register")
	public void register() {

	}

	@PostMapping("/register")
	public String register(BoardVO boardVO, RedirectAttributes rttr) {

		log.info("register :::" + boardVO);

		
		if(boardVO.getAttachList() != null) {
			boardVO.getAttachList().forEach( attach -> log.info(attach));
		}
		/*boardService.register(boardVO);

		rttr.addFlashAttribute("result", boardVO.getBno());*/

		return "redirect:/board/list";
	}

	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria criteria, Model model) {
		log.info("/get , /modify");
		model.addAttribute("board", boardService.get(bno));
	}

	@PostMapping("modify")
	public String modify(BoardVO boardVO, @ModelAttribute("cri") Criteria criteria, RedirectAttributes attributes) {
		log.info("modify :: " + boardVO);

		if (boardService.modify(boardVO)) {
			attributes.addFlashAttribute("result", "modifysuccess");// 내가 만든것
//			attributes.addFlashAttribute("result", "success");
		}
//		attributes.addAttribute("pageNum", criteria.getPageNum());
//		attributes.addAttribute("amount", criteria.getAmount());
//		attributes.addAttribute("type", criteria.getType());
//		attributes.addAttribute("keyword", criteria.getKeyword());
		return "redirect:/board/list" + criteria.getListLink();
	}

	@PostMapping(value = "/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria criteria,
			RedirectAttributes attributes) {
		log.info("remove...." + bno);
		if (boardService.remove(bno)) {
			attributes.addFlashAttribute("result", "removesuccess");// 내가 만든것
//			attributes.addFlashAttribute("result", "success");
		}
//		attributes.addAttribute("pageNum", criteria.getPageNum());
//		attributes.addAttribute("amount", criteria.getAmount());
//		attributes.addAttribute("type", criteria.getType());
//		attributes.addAttribute("keyword", criteria.getKeyword());
		return "redirect:/board/list" + criteria.getListLink();
	}

}
