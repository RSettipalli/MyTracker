<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MyTracking Delivery Info</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="./js/jquery-1.9.1.js"></script>
<link href="./css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/arial.js"></script>
<script type="text/javascript" src="./js/cuf_run.js"></script>
<script>
	function toggle_visibility(id) {
		var e = document.getElementById(id);
		if (e.style.display == 'block')
			e.style.display = 'none';
		else
			e.style.display = 'block';
	}
</script>
</head>
<body>
	<div class="main">
		<div class="header">
			<div class="header_resize">
				<div class="logo">
					<img src="./images/mygologo_smallsize.jpg" />
				</div>
				<div class="logoTitle">
					<h1>
						MyTracking<sup>&reg;</sup>
					</h1>
				</div>
				<div class="logoSmallTitle">
					<a href="#"><img src="./images/contactusimage_sm.jpg"
						width="50px" border="0" /></a> <a href="./signUp.htm"><img
						src="./images/Registeration_sm.png" width="50px" border="0" /></a>
				</div>
				<div class="clr"></div>
				<div class="menu_nav">
					<ul>
						<li><a href="./Home.htm">Home</a></li>
						<li class="active"><a href='./OrderInfo.htm'><span>Delivery
									Info</span></a></li>
						<li><a href='./Invoice.htm'><span>Invoice Info</span></a></li>
						<li><a href='./Shipment.htm'><span>Sales Order
									Info</span></a></li>
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
						<br />
						<h2>
							<span>Delivery Info</span>
						</h2>
						<b> <u>Delivery Header Information</u>
						</b> <br />
						<table class="tg">
							<tr>
								<th class="tg-kkr7">DeliveryNumber</th>
								<th class="tg-t8h0">Status</th>
								<th class="tg-t8h0">Ship_To</th>
								<th class="tg-t8h0">End_User</th>
								<th class="tg-t8h0">End_User_Company</th>
								<th class="tg-t8h0">Company_Name</th>
								<th class="tg-t8h0">Customer_PO</th>
								<th class="tg-t8h0">ReferenceNum</th>
								<th class="tg-t8h0">Order Plant</th>
								<th class="tg-t8h0">Address1</th>
								<th class="tg-t8h0">Address2</th>
								<th class="tg-t8h0">City</th>
								<th class="tg-t8h0">State</th>
								<th class="tg-qta8">Zip</th>
							</tr>
							<tr>
								<td class="tg-031e"><a href="#"
									onclick="toggle_visibility('${orderHeader.DELVI_NBR}')">${orderHeader.DELVI_NBR}</a>
								</td>
								<td class="tg-031e">${orderHeader.ORDER_STATUS_CD}</td>
								<td class="tg-031e">${orderHeader.SHIP_TO_COMPANY_CD}</td>
								<td class="tg-031e">${orderHeader.END_USER}</td>
								<td class="tg-031e">${orderHeader.END_USER_COMPANY_CD}</td>
								<td class="tg-031e">${orderHeader.OVERRIDE_COMPANY_NAME}</td>
								<td class="tg-031e">${orderHeader.ORDER_REF_NUM}</td>
								<td class="tg-031e">${orderHeader.ORDER_PLANT_CD}</td>
								<td class="tg-031e">${orderHeader.CUSTOMER_PO}</td>
								<td class="tg-031e">${orderHeader.OVERRIDE_ADDRESS1}</td>
								<td class="tg-031e">${orderHeader.OVERRIDE_ADDRESS2}</td>
								<td class="tg-031e">${orderHeader.OVERRIDE_CITY}</td>
								<td class="tg-031e">${orderHeader.OVERRIDE_STATE}</td>
								<td class="tg-031e">${orderHeader.OVERRIDE_ZIP}</td>
							</tr>
						</table>
						<!--  expand collapse : start -->
						<div id='${orderHeader.DELVI_NBR}' class="filter-container">
							<div class="triggear">
								<a href="#"
									onclick="toggle_visibility('${orderHeader.DELVI_NBR}')">Hide</a>
							</div>
							<div class="toggle-container">
								<b> <u>Delivery Details</u></b>
								<display:table name="orderDetail"
									style="border: 1px solid; width: 950px; height: 20px;">
									<display:column property="PRODUCT_NBR" title="Product_Number" />
									<display:column property="NET_VAL" title="Net_Value" />
									<display:column property="ITEM_CAT" title="Item_Category" />
									<display:column property="ORDER_NBR" title="Order_Number" />
									<display:column property="BASE_UOM" title="Unit_Of_Measure" />
									<display:column property="OVERRIDE_PRODUCT" title="Product" />
									<display:column property="ORDER_LINE_NBR"
										title="OrderLineNumber" />
									<display:column property="ORD_QTY" title="OrderQuantity" />
								</display:table>
							</div>

							<table border="0">
								<tr>
									<td><div class="content">
											<b> <u>Delivery Detail Comments</u>
											</b> <br />
											<display:table name="odDetailComments" cellspacing="2"
												style="border: 1px solid; width: 600px;">
												<display:column property="ORDER_NBR" title="OrderNumber" />
												<display:column property="ORDER_LINE_NBR"
													title="OrderLineNumber" />
												<display:column property="LINE" title="Line" />
												<display:column property="TYPE" title="Type" />
											</display:table>
										</div></td>
									<td><div class="content">
											<b> <u>Delivery Header Comments</u>
											</b> <br />
											<display:table name="orderHeaderComments" cellspacing="2"
												style="border: 1px solid; width: 300px;">
												<display:column property="ORDER_NBR" title="OrderNumber" />
												<display:column property="LINE" title="Line" />
												<display:column property="TYPE" title="Type" />
												<display:column property="DELVI_NBR_OD_HEADER"
													title="OrderDetailNumber" />
											</display:table>
										</div></td>
								</tr>
							</table>
							<div class="content">
								<b> <u>Delivery Item Attachments</u>
								</b> <br />
								<display:table name="odDetailItemAttachments" cellspacing="2"
									style="border:solid 1px;">
									<display:column property="DOKAR" title="DOKAR" />
									<display:column property="DOKVR" title="DOKVR" />
									<display:column property="DOKTL" title="DOKTL" />
									<display:column property="DOKNR" title="DOKNR" />
									<display:column property="OBJKY" title="OBJKY" />
								</display:table>
							</div>
						</div>
						<!--  expand collapse : end -->
						<div class="clr"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
