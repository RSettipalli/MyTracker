<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>My Tracker Invoice Info</title>
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
      		<li class="active"><a href='./Invoice.htm'><span>Invoice Info</span></a></li>
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
        <jsp:include page="./SearchFragment.jsp"/><br/><br/>
        <br/>       
          <h2><span>Invoice Info</span></h2>
          <b> <u>Invoice Header Information</u> </b> <br/>
          <display:table name="invoiceHeader" cellspacing ="12" style="border:solid 1px;"  id="current_row">
          	<display:column property="INVOI_NBR" title="InvoiceNumber"/>
  			<display:column property="ORDER_STATUS_CD" title="Status"/>
  			<display:column property="SOLD_FROM_COMPANY_CD" title="Sold_From"/>
  			<display:column property="SHIP_TO_COMPANY_CD" title="Ship_To"/>  			
  			<display:column property="END_USER_COMPANY_CD" title="End_User_Company"/>
  			<display:column property="OVERRIDE_COMPANY_NAME" title="Company_Name"/>
  			<display:column property="CUSTOMER_PO" title="Customer_PO"/>
  			<display:column property="SOLD_TO_COMPANY_CD" title="Sold_To"/>
  			<display:column property="ORDER_PLANT_CD" title="Order Plant"/>  			
  			<display:column property="OVERRIDE_ADDRESS1" title="Address1"/>  			
  			<display:column property="OVERRIDE_ADDRESS2" title="Address2"/>
  			<display:column property="OVERRIDE_CITY"  title="City"/>
  			<display:column property="OVERRIDE_STATE" title="State"/>
  			<display:column property="OVERRIDE_ZIP" title="Zip"/>  			  			
		</display:table>          
         <br/>
 	   <br />
 	   <div class="content">
 	   <b> <u>Invoice Header Comments</u> </b> <br/>
 	   <display:table name="invoiceOrderHeaderComments" cellspacing ="12" style="border:solid 1px;" >
          	<display:column property="ORDER_NBR" title="OrderNumber"/>
  			<display:column property="LINE" title="Line"/>
  			<display:column property="TYPE" title="Type"/>
		</display:table>
 	   </div>
 	    <br/>
 	   <br />
 	  <div class="content">
 	  <b> <u>Invoice Details</u> </b> <br/>
 	  <display:table name="invDetail" style="border:solid 1px;" cellspacing="10">
  			<display:column property="PRODUCT_NBR" title="Product_Number"/>
  			<display:column property="NET_VAL" title="Net_Value"/>
  			<display:column property="ITEM_CAT" title="Item_Category"/>
  			<display:column property="ORDER_NBR" title="Order_Number"/>
  			<display:column property="BASE_UOM" title="Unit_Of_Measure"/>
  			<display:column property="OVERRIDE_PRODUCT" title="Product"/>  			
  			<display:column property="ORDER_LINE_NBR" title="OrderLineNumber"/>
		</display:table> 	  
   	 </div>
   	 <br/>
 	   <br />
   	 <div class="content">
   	 <b> <u>Invoice Detail Comments</u> </b> <br/>
 	   <display:table name="invDetailComments" cellspacing ="12" style="border:solid 1px;" >
          	<display:column property="ORDER_NBR" title="OrderNumber"/>
          	<display:column property="ORDER_LINE_NBR" title="OrderLineNumber"/>
  			<display:column property="LINE" title="Line"/>
  			<display:column property="TYPE" title="Type"/>
		</display:table>
 	   </div>
 	    <br/>
 	   <br />
       <div class="content">
       <b> <u>Invoice Item Attachments</u> </b> <br/>
 	   <display:table name="invItemAttachments" cellspacing ="12" style="border:solid 1px;" >
          	<display:column property="DOKAR" title="DOKAR"/>
  			<display:column property="DOKVR" title="DOKVR"/>
  			<display:column property="DOKTL" title="DOKTL"/>
  			<display:column property="DOKNR" title="DOKNR"/>
  			<display:column property="OBJKY" title="OBJKY"/>  			
		</display:table>
 	   </div>
 	    <br/>
 	   <br />    
          <div class="clr"></div>
        </div>
      </div>
  </div>
</div>
</div>
</body>
</html>
