<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>My Tracker Order Info</title>
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
          <h2><span>Order Info</span></h2>          
          <b> <u>Order Header Information</u> </b> <br/>
        ORDER_STATUS_CD : ${orderHeader.ORDER_STATUS_CD} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
        CUSTOMER_PO : ${orderHeader.CUSTOMER_PO} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		END_USER_COMPANY_CD : ${orderHeader.END_USER_COMPANY_CD} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		ORDER_REF_NUM : ${orderHeader.ORDER_REF_NUM} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		OVERRIDE_COMPANY_NAME : ${orderHeader.OVERRIDE_COMPANY_NAME} &nbsp;&nbsp;&nbsp;&nbsp; <br/> 
		OVERRIDE_STATE : ${orderHeader.OVERRIDE_STATE} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		OVERRIDE_CITY : ${orderHeader.OVERRIDE_CITY} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		OVERRIDE_ADDRESS1 : ${orderHeader.OVERRIDE_ADDRESS1} &nbsp;&nbsp;&nbsp;&nbsp;	 <br/>
		OVERRIDE_ADDRESS2 : ${orderHeader.OVERRIDE_ADDRESS2} &nbsp;&nbsp;&nbsp;&nbsp;<br/> 
		OVERRIDE_ZIP : ${orderHeader.OVERRIDE_ZIP} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		END_USER : ${orderHeader.END_USER} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		SHIP_TO_COMPANY_CD : ${orderHeader.SHIP_TO_COMPANY_CD} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		DELVI_NBR : ${orderHeader.DELVI_NBR} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		ORDER_PLANT_CD : ${orderHeader.ORDER_PLANT_CD} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
		SOLD_TO_COMPANY_CD : ${orderHeader.SOLD_TO_COMPANY_CD} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
 	   <br />
 	  <div class="content">
 	  <b> <u>Order Details</u> </b> <br/>
 	  <c:forEach items="${orderDetail}" var="orderDetail"> 
 	   	PRODUCT_NBR :${orderDetail.PRODUCT_NBR} &nbsp;&nbsp;&nbsp;&nbsp; <br/> 
 	  	NET_VAL : ${orderDetail.NET_VAL} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
	  	BASE_UOM : ${orderDetail.BASE_UOM} &nbsp;&nbsp;&nbsp;&nbsp; <br/> 
	  	ITEM_CAT : ${orderDetail.ITEM_CAT} &nbsp;&nbsp;&nbsp;&nbsp; <br />
	  	OVERRIDE_PRODUCT : ${orderDetail.OVERRIDE_PRODUCT} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
	  	ORDER_NBR : ${orderDetail.ORDER_NBR} &nbsp;&nbsp;&nbsp;&nbsp; <br/>	
	  	ORDER_LINE_NBR : ${orderDetail.ORDER_LINE_NBR} &nbsp;&nbsp;&nbsp;&nbsp; <br/>
	  	ORD_QTY : ${orderDetail.ORD_QTY} &nbsp;&nbsp;&nbsp;&nbsp;	<br />
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
