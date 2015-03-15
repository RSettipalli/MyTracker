package com.mygoconsulting.mytracking.batch.processor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.dao.DAOFactory;
import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.parse.ParserFactory;
import com.mygoconsulting.mytracking.util.MyTrackerProperty;

@Component("syncXml")
public class SyncXMLDataTask extends TimerTask {
	private static final MygoLogger LOG = LogFactory.getMygoLogger();
	
	//private String yearNow = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());

	private File inboundfolder = new File(MyTrackerProperty.getProperty("mytracker.sourcefolder"));
	//private File outboundfolder = new File(MyTrackerProperty.getProperty("outputfolder"));

	@Autowired
	private ParserFactory parserFactory;

	@Autowired
	private DAOFactory daoFactory;

	@Override
	public void run() {
		LOG.debug("BEGIN");
		LOG.debug("SyncXMLDataTask--> run()");
		boolean isTaskSuccess = false;
		try{
			LOG.debug("input folder: "+inboundfolder.getName());
			List<String> files = listFilesForFolder(inboundfolder);
			for (String file : files) {
				String[] fileName = file.split("_");
				LOG.debug("file name is : "+file);
				if(fileName[0].equals("COMP")){//file.contains("CompanyCode.xml") 
					LOG.debug("This is Company code.xml");
					IDOC doc = parserFactory.parseCompanyCodeXML(inboundfolder.getAbsolutePath()+"\\"+file);					
					daoFactory.persistCompanyCodeData(doc);
					LOG.debug("Company Code data is saved to DB");
					isTaskSuccess = true;
				} else if(fileName[0].equals("CUST")){//file.contains("Customer.xml")
					LOG.debug("This is Customer.xml");
					IDOC doc = parserFactory.parseCustomerXML(inboundfolder.getAbsolutePath()+"\\"+file);
					daoFactory.persistCustomerData(doc);
					LOG.debug("Customer Info is saved to DB");
					isTaskSuccess = true;
				} else if(fileName[0].equals("MAT")){//file.contains("Material.xml")
					LOG.debug("This is Material.xml");
					IDOC doc = parserFactory.parseMaterialXML(inboundfolder.getAbsolutePath()+"\\"+file);					
					daoFactory.persistMaterialData(doc);
					LOG.debug("Material details are saved to DB");
					isTaskSuccess = true;
				} else if(fileName[0].equals("DEL")){//file.contains("Delivery.xml")
					LOG.debug("This is Delivery.xml");
					IDOC doc = parserFactory.parseDeliveryXML(inboundfolder.getAbsolutePath()+"\\"+file);					
					daoFactory.persistDeliveryData(doc);
					LOG.debug("Delivery Order details are saved to DB");
					isTaskSuccess = true;
				} else if(fileName[0].equals("SORD")){ //file.contains("Sales.xml")
					LOG.debug("This is Sales.xml");
					IDOC doc = parserFactory.parseSalesXML(inboundfolder.getAbsolutePath()+"\\"+file);
					daoFactory.persistSalesData(doc);
					LOG.debug("Sales Order details are saved to DB");
					isTaskSuccess = true;
				} else if(fileName[0].equals("INV")){//file.contains("Invoice.xml")
					LOG.debug("This is Invoice.xml");
					IDOC doc = parserFactory.parseInvoiceXML(inboundfolder.getAbsolutePath()+"\\"+file);
					daoFactory.persistInvoiceData(doc);
					LOG.debug("Invoice details are saved to DB");
					isTaskSuccess = true;
				}
				//if(isTaskSuccess)
					//backupFile(file);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		LOG.debug("BEGIN");
	}
	
	private void backupFile(String file) {
		LOG.debug("BEGIN");		
		File srcFile = new File(inboundfolder + "/" + file);
		//srcFile.renameTo(new File(outboundfolder, srcFile.getName()));
		LOG.debug(file+" file moved to output folder");		
		LOG.debug("END");
	}

	private List<String> listFilesForFolder(final File folder) {
		LOG.debug("BEGIN");
		List<String> files = new ArrayList<String>();
		if (folder != null) {
			for (final File fileEntry : folder.listFiles()) {
				if (fileEntry.isDirectory()) {
					listFilesForFolder(fileEntry);
				} else {
					files.add(fileEntry.getName());
				}
			}
		}
		LOG.debug("END");
		return files;
	}
}
