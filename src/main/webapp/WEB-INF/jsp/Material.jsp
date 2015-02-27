<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>My Tracker Material Info</title>
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
      <div class="logo"><img src="./images/mygologo_smallsize.jpg" /></div>
      <div class="logoTitle"><h1>My Tracker</h1></div>
      <div class="logoSmallTitle"><a href="#"><img src="./images/mygologo_icon_sm.jpg" width = "50px" border="0" /></a>
       <a href="#"><img src="./images/Contactusimage_sm.jpg" width = "50px" border="0" /></a>
       <a href="./signUp.htm"><img src="./images/Registeration_sm.png" width = "50px" border="0" /></a>
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
          <br />
          <br />
          <br />
          <h2><span>Material Info</span></h2>
          <display:table name="imyMatOnline" cellspacing ="15" style="border:solid 1px;">
          	<display:column property="MATERIAL" title="Material Id"/>
  			<display:column property="MAT_TYPE" title="Type"/>
  			<display:column property="MATERIAL_GROUP" title="Group"/>
  			<display:column property="MAT_DESC" title="Description"/>  			
  			<display:column property="STOCK" title="Stock"/>
  			<display:column property="NET_WEIGHT" title="Net Weight"/>
  			<display:column property="UOM" title="Unit of Measure"/>
  			<display:column property="BOM" title="BOM"/>  			  			  			
		</display:table>
 	   <br />
 	  <div class="content">
 	  <b> <u>Material Plant Details</u> </b> <br/>
 	  <display:table name="imyMatPlant" cellspacing ="15" style="border:solid 1px;">
          	<display:column property="PLANT" title="Plant"/>
  			<display:column property="MRP_CONT" title="Container"/>
  			<display:column property="MAINT_STATUS" title="Status"/>
  			<display:column property="MRP_TYPE" title="Type"/>  			
  	</display:table>
	  	<br /><br />
   	 </div>
   	 <div class="content">
 	  <b> <u>Material Storage Details</u> </b> <br/>
 	  <display:table name="imyMatStorageDetails" cellspacing ="15" style="border:solid 1px;">
          	<display:column property="PLANT" title="Plant"/>
  			<display:column property="MATERIAL" title="Material"/>
  			<display:column property="MAINT_STATUS" title="Status"/>
  			<display:column property="STO_LOCATION" title="StorageLocation"/>
  			<display:column property="STOC_IN_QLTY_INS" title="Quality"/>
  	</display:table>
 	   	<br /><br />
   	 </div>          
          <div class="clr"></div>
        </div>
      </div>     
  </div>
</div>
</div>
</body>
</html>
