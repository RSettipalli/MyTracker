<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>My Tracker Material Info</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="./js/cufon-yui.js"></script>
	<script type="text/javascript" src="./js/arial.js"></script>
	<script type="text/javascript" src="./js/cuf_run.js"></script>
</head>
<body>
<div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="logo"><img src="images/mygologo_smallsize.jpg" /></div>
      <div class="logoTitle"><h1>My Tracker</h1></div>
      <div class="logoSmallTitle"><a href="#"><img src="images/mygologo_icon_sm.jpg" width = "50px" border="0" /></a>
       <a href="#"><img src="images/Contactusimage_sm.jpg" width = "50px" border="0" /></a>
       <a href="./signUp.htm"><img src="images/Registeration_sm.png" width = "50px" border="0" /></a>
      </div>
      <div class="clr"></div>
      <div class="menu_nav">
        <ul>
        	<li><a href="./Home.htm">Home</a></li>      		
      		<li><a href='./OrderInfo.htm'><span>Order Info</span></a></li>
      		<li><a href='./Invoice.htm'><span>Invoice Info</span></a></li>
      		<li><a href='./Shipment.htm'><span>Sales Order Info</span></a></li>
      		<li class="active"><a href='./Material.htm'><span>Material Info</span></a></li>
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
        
          <h2><span>Material Info</span></h2>
          
          
          	MAT_DESC : ${imyMatOnline.MAT_DESC} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
        	MATERIAL : ${imyMatOnline.MATERIAL} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
			NET_WEIGHT : ${imyMatOnline.NET_WEIGHT} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
			MAT_TYPE : ${imyMatOnline.MAT_TYPE} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
			UOM : ${imyMatOnline.UOM} &nbsp;&nbsp;&nbsp;&nbsp; <br/> 
			BOM : ${imyMatOnline.BOM} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
			STOCK : ${imyMatOnline.STOCK} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
			MATERIAL_GROUP : ${imyMatOnline.MATERIAL_GROUP} &nbsp;&nbsp;&nbsp;&nbsp; <br/>		
 	   <br />
 	  <div class="content">
 	  <b> <u>Material Plant Details</u> </b> <br/>
 	   	PLANT :${imyMatPlant.PLANT} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
 	  	MRP_CONT :${imyMatPlant.MRP_CONT} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
 	  	MAINT_STATUS :${imyMatPlant.MAINT_STATUS} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
 	  	MRP_TYPE : ${imyMatPlant.MRP_TYPE} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
	  	<br /><br />
   	 </div>
   	 <div class="content">
 	  <b> <u>Material Storage Details</u> </b> <br/>
 	   	PLANT :${imyMatStorageDetails.PLANT} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
 	  	MATERIAL :${imyMatStorageDetails.MATERIAL} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
 	  	MAINT_STATUS :${imyMatStorageDetails.MAINT_STATUS} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
 	  	STO_LOCATION : ${imyMatStorageDetails.STO_LOCATION} &nbsp;&nbsp;&nbsp;&nbsp; <br/> 
	  	STOC_IN_QLTY_INS : ${imyMatStorageDetails.STOC_IN_QLTY_INS} &nbsp;&nbsp;&nbsp;&nbsp; <br />
	  	<br /><br />
   	 </div>
          
          <div class="clr"></div>
        </div>
      </div>
      <div class="sidebar">
        <div class="clr"></div>
        <div class="gadget">
          <div class="clr"></div>
          <ul class="sb_menu">
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
</div>
</div>
</body>
</html>
