package com.mygoconsulting.mytracking.batch.processor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.dao.DAOFactory;
import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.parse.ParserFactory;
import com.mygoconsulting.mytracking.util.MyTrackerProperty;

@Component("syncXml")
public class SyncXMLDataTask extends TimerTask {

	String yearNow = new SimpleDateFormat("yyyy").format(Calendar.getInstance()
			.getTime());

	File inboundfolder;
	File outboundfolder;

	@Autowired
	private ParserFactory parserFactory;

	@Autowired
	private DAOFactory daoFactory;

	@Override
	public void run() {
		System.out.println("SyncXMLDataTask--> run()");
		try{
			inboundfolder = new File(MyTrackerProperty.getProperty("sourcefolder"));
			System.out.println("input folder: "+inboundfolder.getName());
			List<String> files = listFilesForFolder(inboundfolder);
			for (String file : files) {
				System.out.println("file name is : "+file);
				if(file.contains("CompanyCode.xml")){ 
					System.out.println("This is Company code.xml");
					IDOC doc = parserFactory.parseCompanyCodeXML(file);
					System.out.println("IDOC: "+doc);
					//store in DB
					daoFactory.persistCompanyCodeData(doc);
					System.out.println("doc saved in DB");
				} else if(file.contains("Customer.xml")){
					System.out.println("This is Customer.xml");
					IDOC doc = parserFactory.parseCustomerXML(file);
					System.out.println("IDOC: "+doc);
					//store in DB
					daoFactory.persistCustomerData(doc);
					System.out.println("doc saved in DB");
				} else if(file.contains("Material.xml")){
					System.out.println("This is Material.xml");
					IDOC doc = parserFactory.parseMaterialXML(file);
					System.out.println("IDOC: "+doc);
					//store in DB
					daoFactory.persistMaterialData(doc);
					System.out.println("doc saved in DB");
				} else if(file.contains("Delivery.xml")){
					System.out.println("This is Delivery.xml");
					IDOC doc = parserFactory.parseDeliveryXML(file);
					System.out.println("IDOC: "+doc);
					//store in DB
					daoFactory.persistDeliveryData(doc);
					System.out.println("doc saved in DB");
				} else if(file.contains("Sales.xml")){
					System.out.println("This is Sales.xml");
					IDOC doc = parserFactory.parseSalesXML(file);
					System.out.println("IDOC: "+doc);
					//store in DB
					daoFactory.persistSalesData(doc);
					System.out.println("doc saved in DB");
				} else if(file.contains("Invoice.xml")){
					System.out.println("This is Invoice.xml");
					IDOC doc = parserFactory.parseInvoiceXML(file);
					System.out.println("IDOC: "+doc);
					//store in DB
					daoFactory.persistInvoiceData(doc);
					System.out.println("doc saved in DB");
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}

	// @Override
	// public void run() {
	// System.out.println( "Converting XML to JSON and storing to MongoDB" );
	// inboundfolder = new File(MyTrackerProperty.getProperty("sourcefolder"));
	// outboundfolder = new File(MyTrackerProperty.getProperty("outputfolder"));
	// //System.out.println(MyTrackerProperty.getProperty("sourcefolder"));
	// //System.out.println(MyTrackerProperty.getProperty("outputfolder"));
	// //System.out.println("input folder: "+inboundfolder.getName());
	// //System.out.println("output folder: "+outboundfolder.getName());
	// boolean isTaskSuccess = syncXmlDataToMySQL();
	// if(isTaskSuccess)
	// backupFiles();
	// System.out.println( "Completed XML to JSON and stored to MongoDB" );
	// }

	private boolean syncXmlDataToMySQL() {
		boolean isTaskSuccess = false;
		try {
			// System.out.println(inboundfolder.getName());
			List<String> files = listFilesForFolder(inboundfolder);

			for (String file : files) {
				// String jsonString = xmlToJSONObject(inboundfolder+"/"+file);
				// String fileName = file.substring(0, file.indexOf('.'));
				// DBCollection table = db.getCollection(fileName);
				// BasicDBObject document = new BasicDBObject();
				// System.out.println(jsonString);
				// document.put(fileName, jsonString);
				// table.insert(document);
			}
			isTaskSuccess = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isTaskSuccess;
	}

	private void backupFiles() {
		List<String> files = listFilesForFolder(inboundfolder);
		for (String file : files) {
			File srcFile = new File(inboundfolder + "/" + file);
			srcFile.renameTo(new File(outboundfolder, srcFile.getName()));
			System.out.println("File moved");
		}

	}

	private List<String> listFilesForFolder(final File folder) {
		List<String> files = new ArrayList<String>();
		if (folder != null) {
			for (final File fileEntry : folder.listFiles()) {
				if (fileEntry.isDirectory()) {
					listFilesForFolder(fileEntry);
				} else {
					//System.out.println(fileEntry.getName());
					//files.add(fileEntry.getName());
					files.add(fileEntry.getAbsolutePath());
				}
			}
		}
		return files;
	}
}
