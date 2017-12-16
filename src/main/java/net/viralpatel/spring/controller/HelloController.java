package net.viralpatel.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.viralpatel.spring.common.DBUtil;

@Controller
public class HelloController {

	@GetMapping("/")
	public String hello(Model model) {

		model.addAttribute("name", "John Doe");
		System.out.println(DBUtil.getConnection());
		return "welcome";
	}
}
