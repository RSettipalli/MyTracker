package com.mygoconsulting.mytracking.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.dao.MyTrackingDAO;
import com.mygoconsulting.mytracking.manager.CompanyManager;
import com.mygoconsulting.mytracking.manager.CustomerManager;
import com.mygoconsulting.mytracking.model.LoginForm;
import com.mygoconsulting.mytracking.model.User;
import com.mygoconsulting.mytracking.util.Email;
import com.mygoconsulting.mytracking.util.UserType;

@Controller
public class SignUpController {
	private final static MygoLogger logger = LogFactory.getMygoLogger();
	
	@Autowired
	private MyTrackingDAO userDAO;

	@Autowired
	@Qualifier("CompanyManager")
	CompanyManager companyManager;
	
	@Autowired
	@Qualifier("CustomerManager")
	CustomerManager customerManager;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(Model model) {
		logger.debug("BEGIN");
		LoginForm loginForm = new LoginForm();
		model.addAttribute("loginForm", loginForm);
		model.addAttribute("currPage", "liLogin");
		logger.debug("END");
		return "login";
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String signUp(Model model) {
		logger.debug("BEGIN");
		User user = new User();
		Map<String,List<String>> companyCodesMap = companyManager.getCompanyCodes();		
		for(Map.Entry<String, List<String>> entry: companyCodesMap.entrySet()) {
			List<String> companyCodes = entry.getValue();
			user.setBUKRSList(companyCodes);
		}
		Map<String,List<String>> customerCodesMap = customerManager.getCustomerCodes();		
		for(Map.Entry<String, List<String>> entry: customerCodesMap.entrySet()) {
			List<String> customerCodes = entry.getValue();
			user.setKUNNRList(customerCodes);
		}
		user.setUserType(UserType.COMPANY);
		model.addAttribute("user", user);
		model.addAttribute("AllUserTypes", UserType.getAllUserTypes());
		model.addAttribute("currPage", "liLogin");
		logger.debug("END");
		return "signUp";
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User newUserInfo, Model model) {
		logger.debug("BEGIN");
		boolean success = userDAO.createUser(newUserInfo);
		logger.debug("Registration successful:" + success);
		String result = null;
		String message = null;
		if (success) {
			message = "Registered Successfully. Please Login now";
			result = loginForm(model);
		} else {
			message = "User Registration encountered errors";
			result = signUp(model);
		}
		model.addAttribute("message", message);
		model.addAttribute("currPage", "liLogin");
		logger.debug("END");
		return result;
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public String forgot(Model model) {
		logger.debug("BEGIN");
		User user = new User();
		model.addAttribute("forgotUser", user);
		model.addAttribute("currPage", "liLogin");
		logger.debug("END");
		return "forgot";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,Model model) {
		logger.debug("BEGIN");
		request.getSession().removeAttribute("user");
		loginForm(model);
		model.addAttribute("currPage", "liLogin");
		logger.debug("END");
		return "login";
	}	

	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public String forgot(@ModelAttribute("forgotUser") User forgotUser,
			Model model) {
		logger.debug("BEGIN");
		String email = forgotUser.getEmail();
		String message = null;
		if (email != null) {
			User userDetails = userDAO.getUserDetails(email);
			if (userDetails != null) {
				String password = userDetails.getPassword();
				boolean success = Email.send(email, password);
				if (success) {
					message = "Your password details sent successfully to your mail id:"+email;
				} else {
					message = "Not able to send the mail due to system error";
				}
			} else {
				message = "Your mail id:"+email+ " does not exists in our database";
			}
		}
		model.addAttribute("forgotMsg", message);
		model.addAttribute("forgotUser", forgotUser);
		model.addAttribute("currPage", "liLogin");
		logger.debug("END");
		return "forgot";
	}
}
