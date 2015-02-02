package com.mygoconsulting.mytracking.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IDOC;

@Component
public class DAOFactory {
	
	@Autowired
	@Qualifier("CompanyCode")
	IDAO companyCodeDAO;
	
	@Autowired
	@Qualifier("Customer")
	IDAO customerDao;
	
	@Autowired
	@Qualifier("Material")
	IDAO materialDao;
	
	@Autowired
	@Qualifier("DeliveryDAO")
	IDAO deliveryDao;
	
	@Autowired
	@Qualifier("SalesDAO")
	IDAO salesDao;
	
	@Autowired
	@Qualifier("InvoiceDAO")
	IDAO invoiceDao;
	
	
	public void persistCompanyCodeData(IDOC doc){
		companyCodeDAO.persist(doc);
	}

	public void persistCustomerData(IDOC doc) {
		customerDao.persist(doc);
	}

	public void persistMaterialData(IDOC doc) {
		materialDao.persist(doc);
	}
	
	public void persistDeliveryData(IDOC doc) {
		deliveryDao.persist(doc);
	}
	
	public void persistSalesData(IDOC doc) {
		salesDao.persist(doc);
	}
	
	public void persistInvoiceData(IDOC doc) {
		invoiceDao.persist(doc);
	}
	
}
