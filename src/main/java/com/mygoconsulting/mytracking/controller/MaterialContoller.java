package com.mygoconsulting.mytracking.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@RestController
@RequestMapping("/mytracking/material")
public class MaterialContoller {
	
	@RequestMapping(value = "/{materialId}", method = RequestMethod.GET)
	public String materialInfo(Model model){
		return "material";
	}
	
}
