package com.mygoconsulting.mytracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mytracking/invoice")
public class InvoiceController {
	
	public String invoiceInfo(Model model){
		return "invoice";		
	}
}
