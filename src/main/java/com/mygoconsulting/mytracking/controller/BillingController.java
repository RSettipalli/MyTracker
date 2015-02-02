package com.mygoconsulting.mytracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mytracking/billing")
public class BillingController {
	
	public String billingInfo(Model model){
		return "billing";
	}
	
}
