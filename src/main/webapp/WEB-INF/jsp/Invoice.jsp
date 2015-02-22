<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>My Tracker Invoice Info</title>
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
          <h2><span>Invoice Info</span></h2>
          <b> <u>Invoice Header Information</u> </b> <br/>
        ORDER_STATUS_CD : ${invoiceHeader.ORDER_STATUS_CD} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
        INVOI_NBR : ${invoiceHeader.INVOI_NBR} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		SOLD_FROM_COMPANY_CD : ${invoiceHeader.SOLD_FROM_COMPANY_CD} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		ORDER_PLANT_CD : ${invoiceHeader.ORDER_PLANT_CD} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		SHIP_TO_COMPANY_CD : ${invoiceHeader.SHIP_TO_COMPANY_CD} &nbsp;&nbsp;&nbsp;&nbsp; <br/> 
		CUSTOMER_PO : ${invoiceHeader.CUSTOMER_PO} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		END_USER_COMPANY_CD : ${invoiceHeader.END_USER_COMPANY_CD} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		OVERRIDE_COMPANY_NAME : ${invoiceHeader.OVERRIDE_COMPANY_NAME} &nbsp;&nbsp;&nbsp;&nbsp;	 <br/>
		OVERRIDE_CITY : ${invoiceHeader.OVERRIDE_CITY} &nbsp;&nbsp;&nbsp;&nbsp;<br/> 
		OVERRIDE_STATE : ${invoiceHeader.OVERRIDE_STATE} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		OVERRIDE_ADDRESS1 : ${invoiceHeader.OVERRIDE_ADDRESS1} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		OVERRIDE_ADDRESS2 : ${invoiceHeader.OVERRIDE_ADDRESS2} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		OVERRIDE_ZIP : ${invoiceHeader.OVERRIDE_ZIP} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		SOLD_TO_COMPANY_CD : ${invoiceHeader.SOLD_TO_COMPANY_CD} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
 	   <br />
 	  <div class="content">
 	  <b> <u>Invoice Details</u> </b> <br/>
 	  <c:forEach items="${invDetail}" var="invDetail">
 	  	PRODUCT_NBR :${invDetail.PRODUCT_NBR} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
 	  	NET_VAL :${invDetail.NET_VAL} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
 	  	ITEM_CAT : ${invDetail.ITEM_CAT} &nbsp;&nbsp;&nbsp;&nbsp; <br/> 
	  	BASE_UOM : ${invDetail.BASE_UOM} &nbsp;&nbsp;&nbsp;&nbsp; <br />
	  	OVERRIDE_PRODUCT : ${invDetail.OVERRIDE_PRODUCT} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
	  	ORDER_NBR : ${invDetail.ORDER_NBR} &nbsp;&nbsp;&nbsp;&nbsp; <br/>	
	  	ORDER_LINE_NBR : ${invDetail.ORDER_LINE_NBR} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
	  	<br /><br />
	  	</c:forEach>
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
