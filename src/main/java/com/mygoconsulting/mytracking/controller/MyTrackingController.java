package com.mygoconsulting.mytracking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_HEADER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_HEADER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_HEADER;
import com.mygoconsulting.mytracking.model.IMY_SHIP_POINT;
import com.mygoconsulting.mytracking.model.LoginForm;
import com.mygoconsulting.mytracking.model.MaterialForm;
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
		if(StringUtils.isEmpty(loginRecord.getEmail()) || StringUtils.isEmpty(loginRecord.getPassword())){
			model.addAttribute("message", "Invalid User ID or Password");
			model.addAttribute("currPage", "liLogin");
			logger.debug("END");
			return "login";
		}else{
			User user = userDAO.validateUser(loginRecord.getEmail(),
				loginRecord.getPassword());
			if (user != null) {
				IMY_COMPANY companyInfo = null;
				setCompanyOrCustomerAttributes(model, user, companyInfo);
				model.addAttribute("user", user);
				model.addAttribute("currPage", "liHome");
				logger.debug("END");
				return "Home";
			}
		}
		model.addAttribute("currPage", "liLogin");
		logger.debug("END");
		return "login";
	}

	@RequestMapping(value = "/Material", method = RequestMethod.GET)
	public String material(@ModelAttribute("user") User userInfo, Model model) {
		logger.debug("BEGIN");
		if (userInfo != null) {
			List<IMY_MAT_ONLINE> imyMatOnline = materialManager
					.getMaterialInfo();
			MaterialForm materialForm = new MaterialForm();
			materialForm.setMaterialIdList(getMaterialIdList(imyMatOnline));
			model.addAttribute("materialForm", materialForm);
		}
		model.addAttribute("currPage", "liMaterial");
		logger.debug("END");
		return "Material";
	}

	@RequestMapping(value = "/Material", method = RequestMethod.POST)
	public String showMaterial(@ModelAttribute("user") User userInfo,
			@ModelAttribute("materialForm") MaterialForm materialForm,Model model) {
		logger.debug("BEGIN");
		List<IMY_MAT_ONLINE> imyMatOnline = materialManager.getMaterialInfo();
		List<IMY_MAT_WERKS> imyMatPlant = materialManager
				.getMaterialPlantDetails();
		List<IMY_MAT_STORAGE_DETIALS> imyMatStorageDetailsList = materialManager
				.getMaterialStorageDetails();
		materialForm.setMaterialIdList(getMaterialIdList(imyMatOnline));

		model.addAttribute("materialForm", materialForm);
		model.addAttribute("imyMatOnlineList", imyMatOnline);
		model.addAttribute("imyMatPlantList", imyMatPlant);
		model.addAttribute("imyMatStorageDetailsList", imyMatStorageDetailsList);
		model.addAttribute("currPage", "liMaterial");
		logger.debug("END");
		return "materialDetails";
	}	

	@RequestMapping(value = "/Invoice", method = RequestMethod.GET)
	public String invoice(@ModelAttribute("user") User userInfo, Model model) {
		logger.debug("BEGIN");
		if (userInfo != null) {
			List<IMY_MGOL_INV_HEADER> invoiceHeaderList = null;
			String customerId = userInfo.getCustomerId();
			if(customerId != null){
				invoiceHeaderList = inventoryManager.getInventoryHeader(userInfo.getCompanyId());
			} else {
				invoiceHeaderList = inventoryManager.getInventoryHeader(null);
			}
			setInvHeaderAttribute(model, invoiceHeaderList);
		}
		model.addAttribute("currPage", "liInvoice");
		logger.debug("END");
		return "Invoice";
	}

	@RequestMapping(value = "/OrderInfo", method = RequestMethod.GET)
	public String orderInfo(@ModelAttribute("user") User userInfo, Model model) {
		logger.debug("BEGIN");
		if (userInfo != null) {
			List<IMY_MGOL_OD_HEADER> orderHeaderList = null;			
			String customerId = userInfo.getCustomerId();
			if(customerId != null){
				orderHeaderList = orderManager.getOrderHeaderDetail(userInfo.getCompanyId());
			}else{
				orderHeaderList = orderManager.getOrderHeaderDetail(null);
			}
			setODHeaderAttribute(model, orderHeaderList);
		}
		model.addAttribute("currPage", "liOrderInfo");
		logger.debug("END");
		return "OrderInfo";
	}

	@RequestMapping(value = "/Shipment", method = RequestMethod.GET)
	public String shipment(@ModelAttribute("user") User userInfo, Model model) {
		logger.debug("BEGIN");
		if (userInfo != null) {
			List<IMY_MGOL_SO_HEADER> salesOrderHeaderList = null;
			String customerId = userInfo.getCustomerId();
			if(customerId != null){
				salesOrderHeaderList = salesOrderManager.getSalesOrderHeader(userInfo.getCustomerId());
			} else {
				salesOrderHeaderList = salesOrderManager.getSalesOrderHeader(null);
			}
			setSOHeaderAttribute(model, salesOrderHeaderList);
		}
		model.addAttribute("currPage", "liShipment");
		logger.debug("END");
		return "Shipment";
	}
	
	@RequestMapping(value = "/Home", method = RequestMethod.GET)
	public String home(@ModelAttribute("user") User user, Model model) {
		logger.debug("BEGIN");
		if (user != null) {
			logger.debug("User Id is " + user.getCompanyId());
			IMY_COMPANY companyInfo = companyManager.getCompanyInfo(user
					.getCompanyId());
			model.addAttribute("companyInfo", companyInfo);
			setCompanyOrCustomerAttributes(model, user, companyInfo);
			model.addAttribute("user", user);
		}
		model.addAttribute("currPage", "liHome");
		logger.debug("END");
		return "Home";
	}
	
	private void setCompanyOrCustomerAttributes(Model model, User user,
			IMY_COMPANY companyInfo) {
		List<IMY_SHIP_POINT> shipPoint;
		IMY_MGOL_CUST_BANK customerBank;
		if(user.getCompanyId() != null){
			logger.debug("Company id is " + user.getCompanyId());
			companyInfo = companyManager.getCompanyInfo(user
				.getCompanyId());
		}
		if (companyInfo != null) {
			model.addAttribute("companyInfo", companyInfo);
			model.addAttribute("customerInfo", null);
			shipPoint = companyManager.getShipPoints(user.getCompanyId());
			model.addAttribute("shipPoint", shipPoint);
			model.addAttribute("customerBank", null);
		} else {
			IMY_MGOL_CUSTOMER imyCustomer = customerManager
					.getCustomerInfo(user.getCustomerId());
			model.addAttribute("customerInfo", imyCustomer);
			model.addAttribute("companyInfo", null);
			customerBank = customerManager.getCustomerBank(user
					.getCustomerId());
			model.addAttribute("customerBank", customerBank);
			model.addAttribute("shipPoint", null);
		}
	}
	
	private void setSOHeaderAttribute(Model model,
			List<IMY_MGOL_SO_HEADER> salesOrderHeaderList) {
		if(salesOrderHeaderList != null && salesOrderHeaderList.size() > 0){
			model.addAttribute("salesOrderHeader", salesOrderHeaderList);
		} else {
			model.addAttribute("message","No SalesOrders to be displayed");
		}
	}
	
	private void setODHeaderAttribute(Model model,
			List<IMY_MGOL_OD_HEADER> orderHeaderList) {
		if(orderHeaderList != null && orderHeaderList.size() > 0){
			model.addAttribute("orderHeader", orderHeaderList);
		} else {
			model.addAttribute("message","No OrderDetails to be displayed");
		}
	}
	
	private void setInvHeaderAttribute(Model model,
			List<IMY_MGOL_INV_HEADER> invoiceHeaderList) {
		if(invoiceHeaderList != null && invoiceHeaderList.size() > 0){
			model.addAttribute("invoiceHeader", invoiceHeaderList);
		} else {
			model.addAttribute("message","No Invoice's to be displayed");
		}
	}
	
	private List<String> getMaterialIdList(List<IMY_MAT_ONLINE> imyMatOnline) {
		List<String> materialIdList = new ArrayList<String>();
		for (IMY_MAT_ONLINE matObj : imyMatOnline) {
			materialIdList.add(matObj.getMATERIAL() + ":"
					+ matObj.getMAT_DESC());
		}
		return materialIdList;
	}
	
	/*@RequestMapping(value = "/CompanyProfile", method = RequestMethod.GET)
	public String companyProfile(@ModelAttribute("user") User userInfo,
			Model model) {
		logger.debug("BEGIN");
		List<IMY_SHIP_POINT> shipPoint = null;
		if (userInfo != null) {
			logger.debug("User Id is " + userInfo.getCompanyId());
			IMY_COMPANY companyInfo = companyManager.getCompanyInfo(userInfo
					.getCompanyId());
			model.addAttribute("companyInfo", companyInfo);
			if (companyInfo != null) {
				shipPoint = companyManager.getShipPoints(userInfo
						.getCompanyId());
				model.addAttribute("shipPoint", shipPoint);
			}
		}
		logger.debug("END");
		return "CompanyProfile";
	}*/

}
