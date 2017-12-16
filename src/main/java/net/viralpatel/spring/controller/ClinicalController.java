package net.viralpatel.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clinical")
public class ClinicalController {

	@RequestMapping("vital")
	public String vital(Model model) {
		System.out.println("Clinical controller");
		return "vital";
	}
	
	@RequestMapping("pdsearch")
	public String pdSearch(Model model) {
		System.out.println("Clinical controller");
		return "vital";
	}
	
}
