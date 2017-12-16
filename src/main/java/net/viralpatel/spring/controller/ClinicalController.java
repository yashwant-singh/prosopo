package net.viralpatel.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.viralpatel.spring.service.ClinicalService;

@Controller
@RequestMapping("/clinical")
public class ClinicalController {

	@Autowired private ClinicalService clinicalService;
	@RequestMapping("vital")
	public String vital(Model model) {
		return "vital";
	}
	
	@RequestMapping("pdsearch")
	public ModelAndView pdSearch(String mrNo) {
		System.out.println("Clinical controller : "+mrNo);
		@SuppressWarnings("rawtypes")
		List patientList = clinicalService.getPatientDetails(mrNo);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("vital");
		mav.addObject("pd", patientList);
		return mav;
	}
	
}
