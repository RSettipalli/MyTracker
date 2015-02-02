package com.mygoconsulting.mytracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mytracking/customer")
public class CustomerController {
	
	public String customerInfo(Model model){
		return "customer";
	}
	
}
