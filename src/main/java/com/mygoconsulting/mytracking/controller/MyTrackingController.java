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

@Controller
@SessionAttributes("user")
public class MyTrackingController {
	
	private final static MygoLogger logger = LogFactory.getMygoLogger();
	
	@Autowired
	private MyTrackingDAO userDAO;

	@Autowired
	@Qualifier("CustomerManager")
	CustomerManager customerManager;
	
	@Autowired
	@Qualifier("CompanyManager")
	private CompanyManager companyManager;

	@Autowired
	@Qualifier("OrderManager")
	private OrderManager orderManager;
	
	@Autowired
	@Qualifier("MaterialManager")
	private MaterialManager materialManager;
	
	@Autowired
	@Qualifier("InventoryManager")
	private InventoryManager inventoryManager;
	
	@Autowired
	@Qualifier("SalesOrderManager")
	private SalesOrderManager salesOrderManager;
	
	
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
			logger.debug("Company id is "+user.getCompanyId());
			IMY_COMPANY companyInfo = companyManager.getCompanyInfo(user.getCompanyId());			
			if(companyInfo != null){
				model.addAttribute("companyInfo",companyInfo);
				shipPoint = companyManager.getShipPoints(user.getCompanyId());
				model.addAttribute("shipPoint",shipPoint);
			} else {
				IMY_MGOL_CUSTOMER imyCustomer = customerManager.getCustomerInfo(user.getCustomerId());
				model.addAttribute("companyInfo",imyCustomer);
				customerBank = customerManager.getCustomerBank(user.getCustomerId());
				model.addAttribute("shipPoint",customerBank);
			}
			model.addAttribute("user", user);
			logger.debug("END");
			return "Home";
		}
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
			List<IMY_MGOL_INV_DETAIL> invDetail = inventoryManager.getInventoryDetail(userInfo.getCompanyId());
			model.addAttribute("invDetail",invDetail);
			
			IMY_MGOL_INV_HEADER invoiceHeader = inventoryManager.getInventoryHeader(userInfo.getCompanyId());
			model.addAttribute("invoiceHeader",invoiceHeader);
			
			List<IMY_MGOL_INV_HEADER_COMMEN> invHeaderComments = inventoryManager.getInvHeaderCommentDetails(userInfo.getCompanyId());
			model.addAttribute("invoiceOrderHeaderComments",invHeaderComments);
			
			List<IMY_MGOL_SO_DETAIL_COMMENT> invDetailComments = inventoryManager.getInvDetailComment(userInfo.getCompanyId());
			model.addAttribute("invDetailComments",invDetailComments);
			
			List<IMY_MGOL_INV_ITEM_ATMT> invItemAttachments = inventoryManager.getInvAttachement(userInfo.getCompanyId());
			model.addAttribute("invItemAttachments",invItemAttachments);
		}
		logger.debug("END");
		return "Invoice";
	}
	
	@RequestMapping(value = "/OrderInfo", method = RequestMethod.GET)
	public String orderInfo(@ModelAttribute("user") User userInfo,Model model) {
		logger.debug("BEGIN");
		if(userInfo != null){
			List<IMY_MGOL_OD_DETAIL> orderDetail = orderManager.getOrderDetail(userInfo.getCompanyId());
			model.addAttribute("orderDetail",orderDetail);
			
			IMY_MGOL_OD_HEADER orderHeader = orderManager.getOrderHeaderDetail(userInfo.getCompanyId());
			model.addAttribute("orderHeader",orderHeader);
			
			List<IMY_MGOL_OD_HEADER_COMMENT> odHeaderComments = orderManager.getOrderHeaderCommentDetails(userInfo.getCompanyId());
			model.addAttribute("orderHeaderComments",odHeaderComments);
			
			List<IMY_MGOL_SO_DETAIL_COMMENT> odDetailComments = orderManager.getOrderDetailComment(userInfo.getCompanyId());
			model.addAttribute("odDetailComments",odDetailComments);
			
			List<IMY_MGOL_OD_ITEM_ATTACHM> odDetailItemAttachments = orderManager.getOrderDetailAttachement(userInfo.getCompanyId());
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
			logger.debug("User Id is "+userInfo.getCompanyId());
			IMY_COMPANY companyInfo = companyManager.getCompanyInfo(userInfo.getCompanyId());
			model.addAttribute("companyInfo",companyInfo);
			if(companyInfo != null){
				shipPoint = companyManager.getShipPoints(userInfo.getCompanyId());
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
			List<IMY_MGOL_SO_DETAIL> soDetail = salesOrderManager.getSalesOrderDetail(userInfo.getCompanyId());
			model.addAttribute("soDetail",soDetail);
			
			IMY_MGOL_SO_HEADER salesOrderHeader = salesOrderManager.getSalesOrderHeader(userInfo.getCompanyId());
			model.addAttribute("salesOrderHeader",salesOrderHeader);
			
			List<IMY_MGOL_SO_HEADER_COMMENT> soHeaderComments = salesOrderManager.getSOHeaderCommentDetails(userInfo.getCompanyId());
			model.addAttribute("salesOrderHeaderComments",soHeaderComments);
			
			List<IMY_MGOL_SO_DETAIL_COMMENT> soDetailComments = salesOrderManager.getSODetailComment(userInfo.getCompanyId());
			model.addAttribute("soDetailComments",soDetailComments);
			
			List<IMY_MGOL_SO_ITEM_ATTACHM> soDetailItemAttachments = salesOrderManager.getSODetailAttachement(userInfo.getCompanyId());
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
			logger.debug("User Id is "+user.getCompanyId());
			IMY_COMPANY companyInfo = companyManager.getCompanyInfo(user.getCompanyId());
			model.addAttribute("companyInfo",companyInfo);
			if(companyInfo != null){
				shipPoint = companyManager.getShipPoints(user.getCompanyId());
				model.addAttribute("shipPoint",shipPoint);
			}
		}
		logger.debug("END");
		return "Home";
	}
	
	

}
