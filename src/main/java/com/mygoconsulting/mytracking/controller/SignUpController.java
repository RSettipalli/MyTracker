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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
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
import com.mygoconsulting.mytracking.model.IMY_MGOL_CUSTOMER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_CUST_BANK;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_HEADER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_HEADER_COMMEN;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_ITEM_ATMT;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_HEADER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_HEADER_COMMENT;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_ITEM_ATTACHM;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL_COMMENT;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_HEADER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_HEADER_COMMENT;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_ITEM_ATTACHM;
import com.mygoconsulting.mytracking.model.IMY_SHIP_POINT;
import com.mygoconsulting.mytracking.model.LoginForm;
import com.mygoconsulting.mytracking.model.User;
import com.mygoconsulting.mytracking.util.Email;
import com.mygoconsulting.mytracking.util.UserType;

@Controller
@SessionAttributes("user")
public class SignUpController {
	private final static MygoLogger logger = LogFactory.getMygoLogger();
	
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
		logger.debug("BEGIN");
		logger.debug("email is: " + loginRecord.getEmail());
		IMY_SHIP_POINT shipPoint = null;
		IMY_MGOL_CUST_BANK customerBank = null;
		User user = userDAO.validateUser(loginRecord.getEmail(),
				loginRecord.getPassword());
		if (user != null) {
			logger.debug("Company id is "+user.getUserId());
			IMY_COMPANY companyInfo = companyManager.getCompanyInfo(user.getUserId());			
			if(companyInfo != null){
				model.addAttribute("companyInfo",companyInfo);
				shipPoint = companyManager.getShipPoints(user.getUserId());
				model.addAttribute("shipPoint",shipPoint);
			} else {
				IMY_MGOL_CUSTOMER imyCustomer = customerManager.getCustomerInfo(user.getUserId());
				model.addAttribute("companyInfo",imyCustomer);
				customerBank = customerManager.getCustomerBank(user.getUserId());
				model.addAttribute("shipPoint",customerBank);
			}
			model.addAttribute("user", user);
			logger.debug("END");
			return "Home";
		}
		logger.debug("END");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(Model model) {
		logger.debug("BEGIN");
		LoginForm loginForm = new LoginForm();
		model.addAttribute("loginForm", loginForm);
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
			//user.setCompanyId("BUKRS");
			user.setBUKRSList(companyCodes);
		}
		Map<String,List<String>> customerCodesMap = customerManager.getCustomerCodes();		
		for(Map.Entry<String, List<String>> entry: customerCodesMap.entrySet()) {
			List<String> customerCodes = entry.getValue();			
			//user.setCustomerId("KUNNR");
			user.setKUNNRList(customerCodes);
		}
		user.setUserType(UserType.COMPANY);
		model.addAttribute("user", user);
		model.addAttribute("AllUserTypes", UserType.getAllUserTypes());
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
		logger.debug("END");
		return result;
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public String forgot(Model model) {
		logger.debug("BEGIN");
		User user = new User();
		model.addAttribute("forgotUser", user);
		logger.debug("END");
		return "forgot";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,Model model) {
		logger.debug("BEGIN");
		request.getSession().removeAttribute("user");
		loginForm(model);
		logger.debug("END");
		return "login";
	}
	
	@RequestMapping(value = "/Material", method = RequestMethod.GET)
	public String material(@ModelAttribute("user") User userInfo,Model model) {
		logger.debug("BEGIN");
		if(userInfo != null){
			List<IMY_MAT_ONLINE> imyMatOnline = materialManager.getMaterialInfo();
			List<IMY_MAT_WERKS> imyMatPlant = materialManager.getMaterialPlantDetails();
			List<IMY_MAT_STORAGE_DETIALS> imyMatStorageDetailsList = materialManager.getMaterialStorageDetails();
			
			model.addAttribute("imyMatOnlineList",imyMatOnline);
			model.addAttribute("imyMatPlantList",imyMatPlant);
			model.addAttribute("imyMatStorageDetailsList",imyMatStorageDetailsList);
		}
		logger.debug("END");
		return "Material";
	}
	
	@RequestMapping(value = "/Invoice", method = RequestMethod.GET)
	public String invoice(@ModelAttribute("user") User userInfo,Model model) {
		logger.debug("BEGIN");
		if(userInfo != null){
			List<IMY_MGOL_INV_DETAIL> invDetail = inventoryManager.getInventoryDetail(userInfo.getUserId());
			model.addAttribute("invDetail",invDetail);
			
			IMY_MGOL_INV_HEADER invoiceHeader = inventoryManager.getInventoryHeader(userInfo.getUserId());
			model.addAttribute("invoiceHeader",invoiceHeader);
			
			List<IMY_MGOL_INV_HEADER_COMMEN> invHeaderComments = inventoryManager.getInvHeaderCommentDetails(userInfo.getUserId());
			model.addAttribute("invoiceOrderHeaderComments",invHeaderComments);
			
			List<IMY_MGOL_SO_DETAIL_COMMENT> invDetailComments = inventoryManager.getInvDetailComment(userInfo.getUserId());
			model.addAttribute("invDetailComments",invDetailComments);
			
			List<IMY_MGOL_INV_ITEM_ATMT> invItemAttachments = inventoryManager.getInvAttachement(userInfo.getUserId());
			model.addAttribute("invItemAttachments",invItemAttachments);
		}
		logger.debug("END");
		return "Invoice";
	}
	
	@RequestMapping(value = "/OrderInfo", method = RequestMethod.GET)
	public String orderInfo(@ModelAttribute("user") User userInfo,Model model) {
		logger.debug("BEGIN");
		if(userInfo != null){
			List<IMY_MGOL_OD_DETAIL> orderDetail = orderManager.getOrderDetail(userInfo.getUserId());
			model.addAttribute("orderDetail",orderDetail);
			
			IMY_MGOL_OD_HEADER orderHeader = orderManager.getOrderHeaderDetail(userInfo.getUserId());
			model.addAttribute("orderHeader",orderHeader);
			
			List<IMY_MGOL_OD_HEADER_COMMENT> odHeaderComments = orderManager.getOrderHeaderCommentDetails(userInfo.getUserId());
			model.addAttribute("orderHeaderComments",odHeaderComments);
			
			List<IMY_MGOL_SO_DETAIL_COMMENT> odDetailComments = orderManager.getOrderDetailComment(userInfo.getUserId());
			model.addAttribute("odDetailComments",odDetailComments);
			
			List<IMY_MGOL_OD_ITEM_ATTACHM> odDetailItemAttachments = orderManager.getOrderDetailAttachement(userInfo.getUserId());
			model.addAttribute("odDetailItemAttachments",odDetailItemAttachments);
		}
		logger.debug("END");
		return "OrderInfo";
	}
	
	@RequestMapping(value = "/CompanyProfile", method = RequestMethod.GET)
	public String companyProfile(@ModelAttribute("user") User userInfo,Model model) {
		logger.debug("BEGIN");
		IMY_SHIP_POINT shipPoint = null;
		if (userInfo != null) {
			logger.debug("User Id is "+userInfo.getUserId());
			IMY_COMPANY companyInfo = companyManager.getCompanyInfo(userInfo.getUserId());
			model.addAttribute("companyInfo",companyInfo);
			if(companyInfo != null){
				shipPoint = companyManager.getShipPoints(userInfo.getUserId());
				model.addAttribute("shipPoint",shipPoint);
			}
		}
		logger.debug("END");
		return "CompanyProfile";
	}
	
	@RequestMapping(value = "/Shipment", method = RequestMethod.GET)
	public String shipment(@ModelAttribute("user") User userInfo,Model model) {
		logger.debug("BEGIN");
		if(userInfo != null){
			List<IMY_MGOL_SO_DETAIL> soDetail = salesOrderManager.getSalesOrderDetail(userInfo.getUserId());
			model.addAttribute("soDetail",soDetail);
			
			IMY_MGOL_SO_HEADER salesOrderHeader = salesOrderManager.getSalesOrderHeader(userInfo.getUserId());
			model.addAttribute("salesOrderHeader",salesOrderHeader);
			
			List<IMY_MGOL_SO_HEADER_COMMENT> soHeaderComments = salesOrderManager.getSOHeaderCommentDetails(userInfo.getUserId());
			model.addAttribute("salesOrderHeaderComments",soHeaderComments);
			
			List<IMY_MGOL_SO_DETAIL_COMMENT> soDetailComments = salesOrderManager.getSODetailComment(userInfo.getUserId());
			model.addAttribute("soDetailComments",soDetailComments);
			
			List<IMY_MGOL_SO_ITEM_ATTACHM> soDetailItemAttachments = salesOrderManager.getSODetailAttachement(userInfo.getUserId());
			model.addAttribute("soDetailItemAttachments",soDetailItemAttachments);
		}
		logger.debug("END");
		return "Shipment";
	}
	
	@RequestMapping(value = "/Home", method = RequestMethod.GET)
	public String home(@ModelAttribute("user") User user,Model model) {
		logger.debug("BEGIN");
		IMY_SHIP_POINT shipPoint = null;	
		if (user != null) {
			logger.debug("User Id is "+user.getUserId());
			IMY_COMPANY companyInfo = companyManager.getCompanyInfo(user.getUserId());
			model.addAttribute("companyInfo",companyInfo);
			if(companyInfo != null){
				shipPoint = companyManager.getShipPoints(user.getUserId());
				model.addAttribute("shipPoint",shipPoint);
			}
		}
		logger.debug("END");
		return "Home";
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
		logger.debug("END");
		return "forgot";
	}
}
