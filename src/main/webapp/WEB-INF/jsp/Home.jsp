<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Home</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/cufon-yui.js"></script>
<script type="text/javascript" src="./js/arial.js"></script>
<script type="text/javascript" src="./js/cuf_run.js"></script>
</head>
<body>
<div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="logo"><img src="./images/mygologo_smallsize.jpg"/></div>
      <div class="logoTitle"><h1>My Tracker</h1></div>
      <div class="logoSmallTitle"><a href="#"><img src="./images/mygologo_icon.jpg" width="50" border="0"/></a>
      <a href="#"><img src="./images/contactusimage.jpg" width="50" border="0"/></a>
      <a href="./signUp.htm"><img src="./images/Registeration.png" width="50" border="0"/></a></div>
      <div class="clr"></div>
      <div class="menu_nav">
      	<ul>
      		<li class="active"><a href="./Home.htm">Home</a></li>      		
      		<li><a href='./OrderInfo.htm'><span>Delivery Info</span></a></li>
      		<li><a href='./Invoice.htm'><span>Invoice Info</span></a></li>
      		<li><a href='./Shipment.htm'><span>Sales Order Info</span></a></li>
      		<li><a href='./Material.htm'><span>Material Info</span></a></li>
			<li><a href="./login.htm">Login</a></li>
      	</ul>
      </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="content">
  	<div class="content_resize">
      <div class="clr"></div>
      <div class="mainbar">
      <div class="article">
        <h2><span>Company Profile</span></h2>
          <b> <u>Company Information</u> </b> <br/><br/>
        Company Code : ${companyInfo.BUKRS} &nbsp;&nbsp;&nbsp;&nbsp; <br/><br/>
		Name of Company : ${companyInfo.BUTXT} &nbsp;&nbsp;&nbsp;&nbsp; <br/><br/>
		Language : ${companyInfo.SPRAS} &nbsp;&nbsp;&nbsp;&nbsp; <br/><br/>
		Currency : ${companyInfo.WAERS} &nbsp;&nbsp;&nbsp;&nbsp; <br/> <br/>
		City : ${companyInfo.ORT01} &nbsp;&nbsp;&nbsp;&nbsp; <br/><br/>
		Address : ${companyInfo.ADRNR} &nbsp;&nbsp;&nbsp;&nbsp; <br/><br/>
		Street : ${companyInfo.ADDRESS1} &nbsp;&nbsp;&nbsp;&nbsp; <br/><br/>
		Street 2 : ${companyInfo.ADDRESS2} &nbsp;&nbsp;&nbsp;&nbsp;	 <br/><br/>
		Country : ${companyInfo.COUNTRY} &nbsp;&nbsp;&nbsp;&nbsp;<br/> <br/>
		City postal code : ${companyInfo.ZIP} &nbsp;&nbsp;&nbsp;&nbsp; <br/><br/>
		Telephone number : ${companyInfo.PHONE} &nbsp;&nbsp;&nbsp;&nbsp; <br/><br/>
		Fax Number : ${companyInfo.FAX} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
 	   <br/>
 	   <br/>
 	  <div class="content">
 	  <b> <u>Company Ship Points</u> </b> <br/> 
 	  <display:table name="shipPoint" cellspacing ="15" style="border:solid 1px;">
          	<display:column property="BUKRS" title="Company Code"/>  			
  			<display:column property="ADDRESS1" title="Street"/>
  			<display:column property="ADDRESS2" title="Street 2"/>
  			<display:column property="CITY" title="City"/>
  			<display:column property="COUNTRY" title="Country"/>
  			<display:column property="ZIP" title="Zip"/>
  			<display:column property="PHONE" title="Phone"/>
  			<display:column property="FAX" title="Fax"/>
  			<display:column property="LANGUAGE" title="Language"/>
  	</display:table> 	  	      
   	 </div>
          
          <div class="clr"></div>
        </div></div>
       <div class="clr"></div>
      <div class="clr"></div>
    </div>
  </div>
  </div>  
  </body>
</html>