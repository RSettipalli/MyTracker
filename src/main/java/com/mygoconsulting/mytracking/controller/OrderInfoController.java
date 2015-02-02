package com.mygoconsulting.mytracking.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class OrderInfoController {
	
	@RequestMapping(value = "/orderInfo/{orderid}", method = RequestMethod.GET)
	public String orderInfo(@RequestParam(value="20141016053309") Model model){		
		return "orderInfo";
	}	
}
