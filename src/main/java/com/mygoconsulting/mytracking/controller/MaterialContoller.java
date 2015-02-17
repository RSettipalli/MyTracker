package com.mygoconsulting.mytracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class MaterialContoller {
	
	@RequestMapping(value = "/material", method = RequestMethod.GET)
	public String materialInfo(Model model){
		return "material";
	}
	
}
