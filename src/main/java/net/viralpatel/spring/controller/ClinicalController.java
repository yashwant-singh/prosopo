package net.viralpatel.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clinical")
public class ClinicalController {

	@RequestMapping("pdsearch")
	public String patientSearch(Model model) {
		System.out.println("Clinical controller");
		return "vital";
	}
	
}
