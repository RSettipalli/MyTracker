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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.mygoconsulting.mytracking.util.OrderStatusTypes;

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
		User user = userDAO.validateUser(loginRecord.getEmail(),
				loginRecord.getPassword());
		if (user != null) {
			IMY_COMPANY companyInfo = null;
			setCompanyOrCustomerAttributes(model, user, companyInfo);
			model.addAttribute("user", user);
			model.addAttribute("currPage", "liHome");
			logger.debug("END");
			return "Home";
		} else {
			model.addAttribute("message", "Invalid User ID or Password");
			model.addAttribute("currPage", "liLogin");
			logger.debug("END");
			return "login";
		}
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
			@ModelAttribute("materialForm") MaterialForm materialForm,
			Model model) {
		logger.debug("BEGIN");
		String matId = materialForm.getMaterialId();
		matId = matId.substring(0, matId.indexOf(':'));

		IMY_MAT_ONLINE imyMatOnline = materialManager.getMaterialInfo(matId);
		List<IMY_MAT_ONLINE> imyMatOnlineList = new ArrayList<IMY_MAT_ONLINE>();
		imyMatOnlineList = materialManager.getMaterialInfo();/*.add(imyMatOnline);*/
		List<IMY_MAT_WERKS> imyMatPlantList = materialManager
				.getMaterialPlantDetails(imyMatOnline.getMATERIAL());

		List<IMY_MAT_STORAGE_DETIALS> imyMatStorageDetailsList = new ArrayList<IMY_MAT_STORAGE_DETIALS>();
		for (IMY_MAT_WERKS plant : imyMatPlantList) {
			imyMatStorageDetailsList.addAll(materialManager
					.getMaterialStorageDetails(plant.getPLANT()));
		}

		materialForm.setMaterialIdList(getMaterialIdList(imyMatOnlineList));

		model.addAttribute("materialForm", materialForm);
		model.addAttribute("imyMatOnlineList", imyMatOnline);
		model.addAttribute("imyMatPlantList", imyMatPlantList);
		model.addAttribute("imyMatStorageDetailsList", imyMatStorageDetailsList);
		model.addAttribute("currPage", "liMaterial");
		logger.debug("END");
		return "materialDetails";
	}

	@RequestMapping(value = "/Invoice", method = RequestMethod.GET)
	public String invoice(@ModelAttribute("user") User userInfo, Model model) {
		logger.debug("BEGIN");
		if (userInfo != null) {
			//List<IMY_MGOL_INV_HEADER> invoiceHeaderList = null;
			String customerId = userInfo.getCustomerId();
			/*if (customerId != null) {
				invoiceHeaderList = inventoryManager
						.getInventoryHeaders(userInfo.getCompanyId());
			} else {
				invoiceHeaderList = inventoryManager.getInventoryHeaders(null);
			}
			setInvHeaderAttribute(model, invoiceHeaderList);*/
			setInvHeaderAddtributeByStatus(model, customerId);
		}
		model.addAttribute("currPage", "liInvoice");
		logger.debug("END");
		return "Invoice";
	}

	@RequestMapping(value = "/InvoiceDetail", method = RequestMethod.GET)
	public String invoiceDetail(@RequestParam String InvoiceID,
			@ModelAttribute("user") User userInfo, Model model) {
		logger.debug("BEGIN");
		if (userInfo != null) {
			String customerId = userInfo.getCustomerId();
			IMY_MGOL_INV_HEADER invoiceHeader = null;
			invoiceHeader = inventoryManager.getInventoryHeader(customerId,
					InvoiceID);
			model.addAttribute("invNum", invoiceHeader.getINVOI_NBR());
			model.addAttribute("IMY_MGOL_INV_DETAIL",
					invoiceHeader.getIMY_MGOL_INV_DETAIL());
			model.addAttribute("IMY_MGOL_INV_HEADER_COMMEN",
					invoiceHeader.getIMY_MGOL_INV_HEADER_COMMEN());
			if (invoiceHeader.getORDER_REF_NUM() != null) {
				model.addAttribute("SO_NBR", invoiceHeader.getORDER_REF_NUM());
				model.addAttribute(
						"DELIV_NBR",
						orderManager.getDelivNum(customerId,
								invoiceHeader.getORDER_REF_NUM()));
			}
			model.addAttribute("currPage", "liInvoice");
			logger.debug("END");
			return "InvoiceDetail";
		}
		logger.debug("END");
		return "login";
	}

	@RequestMapping(value = "/OrderInfo", method = RequestMethod.GET)
	public String orderInfo(@ModelAttribute("user") User userInfo, Model model) {
		logger.debug("BEGIN");
		if (userInfo != null) {
			String customerId = userInfo.getCustomerId();
			setODHeaderAddtributeByStatus(model, customerId);
		}
		model.addAttribute("currPage", "liOrderInfo");
		logger.debug("END");
		return "OrderInfo";
	}

	@RequestMapping(value = "/DeliveryDetail", method = RequestMethod.GET)
	public String deliveryDetail(@RequestParam String DeliveryID,
			@ModelAttribute("user") User userInfo, Model model) {
		logger.debug("BEGIN");
		if (userInfo != null) {
			String customerId = userInfo.getCustomerId();
			IMY_MGOL_OD_HEADER orderHeader = null;
			orderHeader = orderManager.getOrderHeader(customerId, DeliveryID);
			model.addAttribute("delivNum", orderHeader.getDELVI_NBR());
			model.addAttribute("IMY_MGOL_OD_DETAIL",
					orderHeader.getIMY_MGOL_OD_DETAIL());
			model.addAttribute("IMY_MGOL_OD_HEADER_COMMENT",
					orderHeader.getIMY_MGOL_OD_HEADER_COMMENT());
			if (orderHeader.getORDER_REF_NUM() != null) {
				model.addAttribute("SO_NBR", orderHeader.getORDER_REF_NUM());
				model.addAttribute(
						"INVOI_NBR",
						inventoryManager.getInvNum(customerId,
								orderHeader.getORDER_REF_NUM()));
			}
			// if(customerId != null)
			logger.debug("BEGIN");
			model.addAttribute("currPage", "liOrderInfo");
			return "DeliveryDetail";
		}
		return "login";
	}

	@RequestMapping(value = "/Shipment", method = RequestMethod.GET)
	public String shipment(@ModelAttribute("user") User userInfo, Model model) {
		logger.debug("BEGIN");
		if (userInfo != null) {
			String customerId = userInfo.getCustomerId();
			setSOHeaderAddtributeByStatus(model, customerId);
		}
		model.addAttribute("currPage", "liShipment");
		logger.debug("END");
		return "Shipment";
	}

	@RequestMapping(value = "/SalesOrderDetail", method = RequestMethod.GET)
	public String soDetail(@RequestParam String OrderID,
			@ModelAttribute("user") User userInfo, Model model) {
		logger.debug("BEGIN");
		if (userInfo != null) {
			String customerId = userInfo.getCustomerId();
			IMY_MGOL_SO_HEADER soHeader = salesOrderManager
					.getSalesOrderHeader(customerId, OrderID);
			model.addAttribute("ORDER_NBR", soHeader.getORDER_NBR());
			model.addAttribute("IMY_MGOL_SO_DETAIL",
					soHeader.getIMY_MGOL_SO_DETAIL());
			model.addAttribute("IMY_MGOL_SO_HEADER_COMMENT",
					soHeader.getIMY_MGOL_SO_HEADER_COMMENT());
			if (OrderID != null) {
				model.addAttribute("DELIV_NBR",
						orderManager.getDelivNum(customerId, OrderID));
				model.addAttribute("INVOI_NBR",
						inventoryManager.getInvNum(customerId, OrderID));
			}
			model.addAttribute("currPage", "liShipment");
			logger.debug("END");
			return "SalesOrderDetail";
		}
		return "login";
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
			model.addAttribute("currPage", "liHome");
			logger.debug("END");
			return "Home";
		} else {
			model.addAttribute("currPage", "liLogin");
			logger.debug("END");
			return "login";
		}
	}

	private void setCompanyOrCustomerAttributes(Model model, User user,
			IMY_COMPANY companyInfo) {
		List<IMY_SHIP_POINT> shipPoint;
		IMY_MGOL_CUST_BANK customerBank;
		if (user.getCompanyId() != null) {
			logger.debug("Company id is " + user.getCompanyId());
			companyInfo = companyManager.getCompanyInfo(user.getCompanyId());
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
			customerBank = customerManager
					.getCustomerBank(user.getCustomerId());
			model.addAttribute("customerBank", customerBank);
			model.addAttribute("shipPoint", null);
		}
	}

	private void setSOHeaderAttribute(Model model,
			List<IMY_MGOL_SO_HEADER> salesOrderHeaderList) {
		if (salesOrderHeaderList != null && salesOrderHeaderList.size() > 0) {
			model.addAttribute("salesOrderHeader", salesOrderHeaderList);
		} else {
			model.addAttribute("message", "No SalesOrders to be displayed");
		}
	}

	private void setSOHeaderAddtributeByStatus(Model model, String customerId) {
		List<IMY_MGOL_SO_HEADER> salesOrderHeaderOpen = salesOrderManager
				.getSalesOrderHeadersByStatus(customerId, OrderStatusTypes.OPEN);
		if (salesOrderHeaderOpen != null && salesOrderHeaderOpen.size() > 0) {
			model.addAttribute("salesOrderHeaderOpen", salesOrderHeaderOpen);
		} else {
			model.addAttribute("message",
					"No Open Sales Orders to be displayed");
		}
		List<IMY_MGOL_SO_HEADER> salesOrderHeaderClosed = salesOrderManager
				.getSalesOrderHeadersByStatus(customerId,
						OrderStatusTypes.COMPLETED);
		if (salesOrderHeaderClosed != null && salesOrderHeaderClosed.size() > 0) {
			model.addAttribute("salesOrderHeaderClosed", salesOrderHeaderClosed);
		} else {
			model.addAttribute("message",
					"No Closed Sales Orders to be displayed");
		}
		List<IMY_MGOL_SO_HEADER> salesOrderHeaderCancelled = salesOrderManager
				.getSalesOrderHeadersByStatus(customerId,
						OrderStatusTypes.CANCELLED);
		if (salesOrderHeaderCancelled != null
				&& salesOrderHeaderCancelled.size() > 0) {
			model.addAttribute("salesOrderHeaderCancelled",
					salesOrderHeaderCancelled);
		} else {
			model.addAttribute("message",
					"No Cancelled Sales Orders to be displayed");
		}
	}

	private void setODHeaderAttribute(Model model,
			List<IMY_MGOL_OD_HEADER> orderHeaderList) {
		if (orderHeaderList != null && orderHeaderList.size() > 0) {
			model.addAttribute("orderHeader", orderHeaderList);
		} else {
			model.addAttribute("message", "No OrderDetails to be displayed");
		}
	}
	
	private void setODHeaderAddtributeByStatus(Model model, String customerId) {
		List<IMY_MGOL_OD_HEADER> deliveryHeaderOpen = orderManager
				.getOrderHeadersByStatus(customerId, OrderStatusTypes.OPEN);
		if (deliveryHeaderOpen != null && deliveryHeaderOpen.size() > 0) {
			model.addAttribute("orderHeaderOpen", deliveryHeaderOpen);
		}
		List<IMY_MGOL_OD_HEADER> deliveryHeaderClosed = orderManager
				.getOrderHeadersByStatus(customerId,
						OrderStatusTypes.COMPLETED);
		if (deliveryHeaderClosed != null && deliveryHeaderClosed.size() > 0) {
			model.addAttribute("orderHeaderCompleted", deliveryHeaderClosed);
		}
		List<IMY_MGOL_OD_HEADER> deliveryHeaderCancelled = orderManager
				.getOrderHeadersByStatus(customerId,
						OrderStatusTypes.CANCELLED);
		if (deliveryHeaderCancelled != null
				&& deliveryHeaderCancelled.size() > 0) {
			model.addAttribute("orderHeaderCancelled",
					deliveryHeaderCancelled);
		} 
	}

	private void setInvHeaderAttribute(Model model,
			List<IMY_MGOL_INV_HEADER> invoiceHeaderList) {
		if (invoiceHeaderList != null && invoiceHeaderList.size() > 0) {
			model.addAttribute("invoiceHeader", invoiceHeaderList);
		} else {
			model.addAttribute("message", "No Invoice's to be displayed");
		}
	}
	
	private void setInvHeaderAddtributeByStatus(Model model, String customerId) {
		List<IMY_MGOL_INV_HEADER> inventoryHeaderOpen = inventoryManager
				.getInventoryHeadersByStatus(customerId, OrderStatusTypes.OPEN);
		if (inventoryHeaderOpen != null && inventoryHeaderOpen.size() > 0) {
			model.addAttribute("invoiceHeaderOpen", inventoryHeaderOpen);
		}
		List<IMY_MGOL_INV_HEADER> inventoryHeaderClosed = inventoryManager
				.getInventoryHeadersByStatus(customerId,
						OrderStatusTypes.COMPLETED);
		if (inventoryHeaderClosed != null && inventoryHeaderClosed.size() > 0) {
			model.addAttribute("invoiceHeaderCompleted", inventoryHeaderClosed);
		}
		List<IMY_MGOL_INV_HEADER> deliveryHeaderCancelled = inventoryManager
				.getInventoryHeadersByStatus(customerId,
						OrderStatusTypes.CANCELLED);
		if (deliveryHeaderCancelled != null
				&& deliveryHeaderCancelled.size() > 0) {
			model.addAttribute("invoiceHeaderCancelled",
					deliveryHeaderCancelled);
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

	/*
	 * @RequestMapping(value = "/CompanyProfile", method = RequestMethod.GET)
	 * public String companyProfile(@ModelAttribute("user") User userInfo, Model
	 * model) { logger.debug("BEGIN"); List<IMY_SHIP_POINT> shipPoint = null; if
	 * (userInfo != null) { logger.debug("User Id is " +
	 * userInfo.getCompanyId()); IMY_COMPANY companyInfo =
	 * companyManager.getCompanyInfo(userInfo .getCompanyId());
	 * model.addAttribute("companyInfo", companyInfo); if (companyInfo != null)
	 * { shipPoint = companyManager.getShipPoints(userInfo .getCompanyId());
	 * model.addAttribute("shipPoint", shipPoint); } } logger.debug("END");
	 * return "CompanyProfile"; }
	 */

}
