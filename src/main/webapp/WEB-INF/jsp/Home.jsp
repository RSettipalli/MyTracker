<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
      		<li><a href='./OrderInfo.htm'><span>Order Info</span></a></li>
      		<li><a href='./Invoice.htm'><span>Invoice Info</span></a></li>
      		<li><a href='./Shipment.htm'><span>Sales Order Info</span></a></li>
      		<li><a href='./Material.htm'><span>Material Info</span></a></li>
      		<li><a href='./CompanyProfile.htm'><span>Company Profile</span></a></li>
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
      <jsp:include page="./SearchFragment.jsp"/>
      <h2><span>Welcome ${sessionScope.user.fname}</span></h2>      
      <b> <u>Company Information</u> </b> <br/>
        BUKRS : ${companyInfo.BUKRS} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
        ORT01 : ${companyInfo.ORT01} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		BUTXT : ${companyInfo.BUTXT} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		SPRAS : ${companyInfo.SPRAS} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		WAERS : ${companyInfo.WAERS} &nbsp;&nbsp;&nbsp;&nbsp; <br/> 
		ADRNR : ${companyInfo.ADRNR} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		ADDRESS1 : ${companyInfo.ADDRESS1} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		ADDRESS2 : ${companyInfo.ADDRESS2} &nbsp;&nbsp;&nbsp;&nbsp;	 <br/>
		COUNTRY : ${companyInfo.COUNTRY} &nbsp;&nbsp;&nbsp;&nbsp;<br/> 
		ZIP : ${companyInfo.ZIP} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		PHONE : ${companyInfo.PHONE} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		FAX : ${companyInfo.FAX} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
 	   
 	  <div class="content">
 	  <b> <u>Company Ship Points</u> </b> <br/> 	  
 	  	ADDRESS1 :${shipPoint.ADDRESS1} &nbsp;&nbsp;&nbsp;&nbsp; <br/> 
 	  	ADDRESS2 : ${shipPoint.ADDRESS2} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
	  	BUKRS : ${shipPoint.BUKRS} &nbsp;&nbsp;&nbsp;&nbsp; <br/> 
	  	CITY : ${shipPoint.CITY} &nbsp;&nbsp;&nbsp;&nbsp; <br />
	  	COUNTRY : ${shipPoint.COUNTRY} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
	  	FAX : ${shipPoint.FAX} &nbsp;&nbsp;&nbsp;&nbsp; <br/>	
	  	LANGUAGE : ${shipPoint.LANGUAGE} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
	  	PHONE : ${shipPoint.PHONE} &nbsp;&nbsp;&nbsp;&nbsp;	<br />
	  	SHIP POINT : ${shipPoint.SHIP_POINT} &nbsp;&nbsp;&nbsp;&nbsp;  <br/> 
	  	ZIP : ${shipPoint.ZIP} &nbsp;&nbsp;&nbsp;&nbsp; <br/>      
   	 </div> 	  	     
      <div class="clr"></div>
      </div></div>
       <div class="clr"></div>
        <div class="gadget">
          <div class="clr"></div>
          <ul class="sb_menu">
            <li class="active"><a href="../Home.htm">Home</a></li>      		
      		<li><a href='./OrderInfo.htm'><span>Order Info</span></a></li>
      		<li><a href='./Invoice.htm'><span>Invoice Info</span></a></li>
      		<li><a href='./Shipment.htm'><span>Sales Order Info</span></a></li>
      		<li><a href='./Material.htm'><span>Material Info</span></a></li>
      		<li><a href='./CompanyProfile.htm'><span>Company Profile</span></a></li>
			<li><a href="./login.htm">Login</a></li>
          </ul>
        </div>
      <div class="clr"></div>
    </div>
  </div>
  </div>  
  </body>
</html>
