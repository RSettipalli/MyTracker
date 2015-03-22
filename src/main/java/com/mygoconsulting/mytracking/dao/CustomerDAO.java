package com.mygoconsulting.mytracking.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_MGOL_CUSTOMER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_CUST_BANK;

@Component("Customer")
public class CustomerDAO extends BaseDAO implements IDAO {
	private final static MygoLogger logger = LogFactory.getMygoLogger();
	@Autowired
	@Qualifier("CustBankRowMapper")
	RowMapper<IMY_MGOL_CUST_BANK> custBankRowMapper;

	@Autowired
	@Qualifier("CustomerRowMapper")
	RowMapper<IMY_MGOL_CUSTOMER> customerRowMapper;

	public void persist(IDOC doc) {
		logger.debug("BEGIN");
		List<IMY_MGOL_CUSTOMER> iMyCustomers = doc.getIMY_MGOL_CUSTOMER();

		// create customer
		createCustomer(iMyCustomers);

		// create customer bank
		createCustomerBank(iMyCustomers);
		logger.debug("END");
	}

	private void createCustomerBank(List<IMY_MGOL_CUSTOMER> iMyCustomers) {
		logger.debug("BEGIN");
		for (IMY_MGOL_CUSTOMER iMyCustomer : iMyCustomers) {
			IMY_MGOL_CUST_BANK iMyCustBank = iMyCustomer
					.getIMY_MGOL_CUST_BANK();
			String selectQuery = new String(
					"select * from CUST_BANK where CUST_NUMBER= ?");
			if (iMyCustBank != null && iMyCustBank.getCUST_NUMBER() != null) {
				Object[] selectParams = { iMyCustBank.getCUST_NUMBER() };
				if (!isExists(selectQuery, selectParams, custBankRowMapper)) {
					logger.debug("Customer Bank inserting");
					String sqlQuery = new String(
							"insert into CUST_BANK (CUST_NUMBER, BANK_COUNTRY, BANK_KEY, BANK_ACC, BANK_TYPE) Values(?,?,?,?,?)");
					Object[] params = { iMyCustBank.getCUST_NUMBER(),
							iMyCustBank.getBANK_COUNTRY(),
							iMyCustBank.getBANK_KEY(),
							iMyCustBank.getBANK_ACC(),
							iMyCustBank.getBANK_TYPE() };
					insertOrUpdate(sqlQuery, params);
				} else {
					logger.debug("Customer Bank updating");
					String sqlQuery = new String(
							"update CUST_BANK set BANK_COUNTRY=?, BANK_KEY=?, BANK_ACC=?, BANK_TYPE=? where CUST_NUMBER=? ");
					Object[] updateParams = { iMyCustBank.getBANK_COUNTRY(),
							iMyCustBank.getBANK_KEY(),
							iMyCustBank.getBANK_ACC(),
							iMyCustBank.getBANK_TYPE(),
							iMyCustBank.getCUST_NUMBER() };
					insertOrUpdate(sqlQuery, updateParams);
				}
			}
		}
		logger.debug("END");
	}

	private void createCustomer(List<IMY_MGOL_CUSTOMER> iMyCustomers) {
		logger.debug("BEGIN");
		for (IMY_MGOL_CUSTOMER iMyCustomer : iMyCustomers) {
			String selectQuery = new String(
					"select * from CUSTOMER where KUNNR= ?");
			Object[] selectParams = { iMyCustomer.getKUNNR() };
			if (!isExists(selectQuery, selectParams, customerRowMapper)) {
				logger.debug("Customer inserting");
				String sqlQuery = new String(
						"insert into CUSTOMER (KUNNR, NAME1, STREET, STR_SUPPL3, CITY, REGION, COUNTRY, POSTL_COD1, TELEPHONE, FAX) Values(?,?,?,?,?,?,?,?,?,?)");
				Object[] params = { iMyCustomer.getKUNNR(),
						iMyCustomer.getNAME1(), iMyCustomer.getSTREET(),
						iMyCustomer.getSTR_SUPPL3(), iMyCustomer.getCITY(),
						iMyCustomer.getREGION(), iMyCustomer.getCOUNTRY(),
						iMyCustomer.getPOSTL_COD1(),
						iMyCustomer.getTELEPHONE(), iMyCustomer.getFAX() };
				insertOrUpdate(sqlQuery, params);
			} else {
				logger.debug("Customer updating");
				String sqlQuery = new String(
						"update CUSTOMER SET NAME1=?, STREET=?, STR_SUPPL3=?, CITY=?, REGION=?, COUNTRY=?, POSTL_COD1=?, TELEPHONE=?, FAX=? where KUNNR=? ");
				Object[] updateParams = { iMyCustomer.getNAME1(),
						iMyCustomer.getSTREET(), iMyCustomer.getSTR_SUPPL3(),
						iMyCustomer.getCITY(), iMyCustomer.getREGION(),
						iMyCustomer.getCOUNTRY(), iMyCustomer.getPOSTL_COD1(),
						iMyCustomer.getTELEPHONE(), iMyCustomer.getFAX(),
						iMyCustomer.getKUNNR() };
				insertOrUpdate(sqlQuery, updateParams);
			}
		}
		logger.debug("END");
	}

	public IMY_MGOL_CUSTOMER getIMyCustomerByKUNNR(String kunnr) {
		logger.debug("BEGIN");
		logger.debug("KUNNR value is " + kunnr);
		String selectQuery = new String(
				"select * from CUSTOMER where KUNNR = ?");
		Object[] selectParams = { kunnr };
		IMY_MGOL_CUSTOMER iMyCustomer = (IMY_MGOL_CUSTOMER) get(selectQuery,
				selectParams, customerRowMapper);
		logger.debug("KUNNR value from DB is " + iMyCustomer.getKUNNR());
		logger.debug("END");
		return iMyCustomer;
	}

	public IMY_MGOL_CUST_BANK getCustBank(String kunnr) {
		logger.debug("BEGIN");
		logger.debug("KUNNR value is " + kunnr);
		String selectQuery = new String(
				"select * from CUST_BANK where CUST_NUMBER= ?");
		Object[] selectParams = { kunnr };
		IMY_MGOL_CUST_BANK iMyCustBank = (IMY_MGOL_CUST_BANK) get(selectQuery,
				selectParams, custBankRowMapper);
		logger.debug("END");
		return iMyCustBank;
	}

	public Map<String, List<String>> getALLKUNNR() {
		logger.debug("BEGIN");
		Map<String, List<String>> customerCodesMap = new HashMap<String, List<String>>();
		List<String> customerCodesList = new ArrayList<String>();
		String selectQuery = new String("select KUNNR from CUSTOMER");

		List<Map<String, Object>> customerCodesMapList = (List<Map<String, Object>>) get(selectQuery);
		if (!customerCodesMapList.isEmpty()) {
			for (int i = 0; i < customerCodesMapList.size(); i++) {
				for (Map.Entry<String, Object> entry : customerCodesMapList
						.get(i).entrySet()) {
					customerCodesList.add(entry.getValue().toString());
				}
			}
			customerCodesMap.put("KUNNR", customerCodesList);
		}

		logger.debug("END");
		return customerCodesMap;
	}

}
