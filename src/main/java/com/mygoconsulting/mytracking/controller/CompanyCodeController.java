package com.mygoconsulting.mytracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mygoconsulting.mytracking.manager.CompanyManager;
import com.mygoconsulting.mytracking.model.IMY_COMPANY;

@Controller
public class CompanyCodeController {
	@Autowired
	CompanyManager companyManager;
	@RequestMapping(value="/companyCode", method = RequestMethod.GET)
	public String companyCode(Model model){		
		//IMY_COMPANY imyCompany = companyManager.getCompanyInfo();
		//model.addAttribute("companyInfo",imyCompany);		
		return "home";
	}
}