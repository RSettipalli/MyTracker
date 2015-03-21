package com.mygoconsulting.mytracking.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;

public class Email {
	private final static MygoLogger logger = LogFactory.getMygoLogger();

	   public static boolean send(String toMailId, String password){
		   logger.debug("BEGIN");
	      // Recipient's email ID needs to be mentioned.
	      //String to = "abcd@gmail.com";

	      // Sender's email ID needs to be mentioned
	      String from = "web@gmail.com";

	      // Assuming you are sending email from localhost
	      String host = "localhost";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);
	      
	      boolean emailSent = false;

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(toMailId));

	         // Set Subject: header field
	         message.setSubject("This is the Subject Line!");

	         // Now set the actual message
	         message.setText("This is your password :"+password);

	         // Send message
	         Transport.send(message);
	         emailSent = true;	         
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	      logger.debug("END");
	      return emailSent;
	   }
	
	
}
