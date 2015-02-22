package com.mygoconsulting.mytracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mygoconsulting.mytracking.dao.MyTrackingDAO;
import com.mygoconsulting.mytracking.manager.CompanyManager;
import com.mygoconsulting.mytracking.manager.CustomerManager;
import com.mygoconsulting.mytracking.manager.InventoryManager;
import com.mygoconsulting.mytracking.manager.MaterialManager;
import com.mygoconsulting.mytracking.manager.OrderManager;
import com.mygoconsulting.mytracking.manager.SalesOrderManager;
import com.mygoconsulting.mytracking.model.IMY_COMPANY;
import com.mygoconsulting.mytracking.model.IMY_MAT_ONLINE;
import com.mygoconsulting.mytracking.model.IMY_MAT_STORAGE_DETIALS;
import com.mygoconsulting.mytracking.model.IMY_MAT_WERKS;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_HEADER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_HEADER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_HEADER;
import com.mygoconsulting.mytracking.model.IMY_SHIP_POINT;
import com.mygoconsulting.mytracking.model.LoginForm;
import com.mygoconsulting.mytracking.model.User;
import com.mygoconsulting.mytracking.util.Email;
//import com.mygoconsulting.mytracking.LogFactory;

@Controller
@SessionAttributes("user")
public class SignUpController {
	// private final static MygoLogger logger = LogFactory.getMygoLogger();
	
	@Autowired
	private MyTrackingDAO userDAO;

	@Autowired
	@Qualifier("CompanyManager")
	CompanyManager companyManager;

	@Autowired
	@Qualifier("OrderManager")
	OrderManager orderManager;
	
	@Autowired
	@Qualifier("MaterialManager")
	MaterialManager materialManager;
	
	@Autowired
	@Qualifier("InventoryManager")
	InventoryManager inventoryManager;
	
	@Autowired
	@Qualifier("SalesOrderManager")
	SalesOrderManager salesOrderManager;
	
	@Autowired
	@Qualifier("CustomerManager")
	CustomerManager customerManager;

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("loginForm") LoginForm loginRecord,
			Model model) {
		System.out.println("email is: " + loginRecord.getEmail());
		IMY_SHIP_POINT shipPoint = null;
		User user = userDAO.validateUser(loginRecord.getEmail(),
				loginRecord.getPassword());
		if (user != null) {
			System.out.println("Company code is "+user.getUserId());
			IMY_COMPANY companyInfo = companyManager.getCompanyInfo(user.getUserId());
			model.addAttribute("companyInfo",companyInfo);
			if(companyInfo != null){
				shipPoint = companyManager.getShipPoints(user.getUserId());
				model.addAttribute("shipPoint",shipPoint);
			}
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
		model.addAttribute("user", user);
		return "forgot";
	}
	
	@RequestMapping(value = "/Material", method = RequestMethod.GET)
	public String material(@ModelAttribute("user") User userInfo,Model model) {
		if(userInfo != null){
			IMY_MAT_ONLINE imyMatOnline = materialManager.getMaterialInfo();
			IMY_MAT_WERKS imyMatPlant = materialManager.getMaterialPlantDetails();
			IMY_MAT_STORAGE_DETIALS imyMatStorageDetails = materialManager.getMaterialStorageDetails();
			
			model.addAttribute("imyMatOnline",imyMatOnline);
			model.addAttribute("imyMatPlant",imyMatPlant);
			model.addAttribute("imyMatStorageDetails",imyMatStorageDetails);
		}
		return "Material";
	}
	
	@RequestMapping(value = "/Invoice", method = RequestMethod.GET)
	public String invoice(@ModelAttribute("user") User userInfo,Model model) {
		if(userInfo != null){
			List<IMY_MGOL_INV_DETAIL> invDetail = inventoryManager.getInventoryDetail(userInfo.getUserId());
			model.addAttribute("invDetail",invDetail);
			
			IMY_MGOL_INV_HEADER invoiceHeader = inventoryManager.getInventoryHeader(userInfo.getUserId());
			model.addAttribute("invoiceHeader",invoiceHeader);
		}
		return "Invoice";
	}
	
	@RequestMapping(value = "/OrderInfo", method = RequestMethod.GET)
	public String orderInfo(@ModelAttribute("user") User userInfo,Model model) {
		if(userInfo != null){
			List<IMY_MGOL_OD_DETAIL> orderDetail = orderManager.getOrderDetail(userInfo.getUserId());
			model.addAttribute("orderDetail",orderDetail);
			
			IMY_MGOL_OD_HEADER orderHeader = orderManager.getOrderHeaderDetail(userInfo.getUserId());
			model.addAttribute("orderHeader",orderHeader);
		}
		return "OrderInfo";
	}
	
	@RequestMapping(value = "/CompanyProfile", method = RequestMethod.GET)
	public String companyProfile(@ModelAttribute("user") User userInfo,Model model) {
		IMY_SHIP_POINT shipPoint = null;
		if (userInfo != null) {
			System.out.println("Company code is "+userInfo.getUserId());
			IMY_COMPANY companyInfo = companyManager.getCompanyInfo(userInfo.getUserId());
			model.addAttribute("companyInfo",companyInfo);
			if(companyInfo != null){
				shipPoint = companyManager.getShipPoints(userInfo.getUserId());
				model.addAttribute("shipPoint",shipPoint);
			}
		}
		return "CompanyProfile";
	}
	
	@RequestMapping(value = "/Shipment", method = RequestMethod.GET)
	public String shipment(@ModelAttribute("user") User userInfo,Model model) {
		if(userInfo != null){
			List<IMY_MGOL_SO_DETAIL> soDetail = salesOrderManager.getSalesOrderDetail(userInfo.getUserId());
			model.addAttribute("soDetail",soDetail);
			
			IMY_MGOL_SO_HEADER salesOrderHeader = salesOrderManager.getSalesOrderHeader(userInfo.getUserId());
			model.addAttribute("salesOrderHeader",salesOrderHeader);
		}
		return "Shipment";
	}
	
	@RequestMapping(value = "/Home", method = RequestMethod.GET)
	public String home(@ModelAttribute("user") User user,Model model) {
		IMY_SHIP_POINT shipPoint = null;	
		if (user != null) {
			System.out.println("Company code is "+user.getUserId());
			IMY_COMPANY companyInfo = companyManager.getCompanyInfo(user.getUserId());
			model.addAttribute("companyInfo",companyInfo);
			if(companyInfo != null){
				shipPoint = companyManager.getShipPoints(user.getUserId());
				model.addAttribute("shipPoint",shipPoint);
			}
		}
		return "Home";
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
