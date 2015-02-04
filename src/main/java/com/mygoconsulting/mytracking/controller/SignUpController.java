package com.mygoconsulting.mytracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mygoconsulting.mytracking.dao.MyTrackingDAO;
import com.mygoconsulting.mytracking.manager.CompanyManager;
import com.mygoconsulting.mytracking.manager.OrderManager;
import com.mygoconsulting.mytracking.model.IMY_COMPANY;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_DETAIL;
import com.mygoconsulting.mytracking.model.LoginForm;
import com.mygoconsulting.mytracking.model.User;
import com.mygoconsulting.mytracking.util.Email;
import com.mygoconsulting.mytracking.util.UserType;

//import com.mygoconsulting.mytracking.LogFactory;

@Controller
@SessionAttributes("user")
public class SignUpController {
	// private final static MygoLogger logger = LogFactory.getMygoLogger();
	
	@Autowired
	private MyTrackingDAO userDAO;

	@Autowired
	CompanyManager companyManager;

	@Autowired
	OrderManager orderManager;

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("loginForm") LoginForm loginRecord,
			Model model) {
		System.out.println("email is: " + loginRecord.getEmail());
		User user = userDAO.validateUser(loginRecord.getEmail(),
				loginRecord.getPassword());
		if (user != null) {
			List<IMY_COMPANY> companyList = companyManager.getCompanyInfo();
			model.addAttribute("companyInfo",companyList);
			
			List<IMY_MGOL_OD_DETAIL> orderDetail = orderManager.getOrderDetail();
			model.addAttribute("orderDetail",orderDetail);

			model.addAttribute("user", user);
			return "Home";
		}
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(Model model) {
		LoginForm loginForm = new LoginForm();
		model.addAttribute("loginForm", loginForm);
		return "login";
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String signUp(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("AllUserTypes", UserType.getAllUserTypes());
		return "signUp";
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User newUserInfo, Model model) {

		boolean success = userDAO.createUser(newUserInfo);
		System.out.println("Registration successful:" + success);
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
		return result;
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public String forgot(Model model) {
		User user = new User();
		model.addAttribute("forgotUser", user);
		return "forgot";
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public String forgot(@ModelAttribute("forgotUser") User forgotUser,
			Model model) {
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
		return "forgot";
	}

}
