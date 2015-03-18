<%@include file="./header.jsp" %>
<div class="main">
		<div class="header">
			<div class="header_resize">
				<div class="logo">
					<a href="http://mygoconsulting.com/"><img
						src="./images/mygologo_smallsize.jpg" /></a>
				</div>
				<div class="logoTitle">
					<h1>
						MyTracking<sup>&reg;</sup>
					</h1>
				</div>
				<div class="logoSmallTitle">
					<a href="http://mygoconsulting.com/contact-us"><img
						src="./images/contactusimage_sm.jpg" width="50px" border="0" /></a> <a
						href="./signUp.htm"><img src="./images/Registeration_sm.png"
						width="50px" border="0" /></a>
				</div>
				<div class="clr"></div>

		<div class="menu_nav">
					<ul>
						<li><a href="./Home.htm">Home</a></li>
						<li><a href='./OrderInfo.htm'><span>Delivery Info</span></a></li>
						<li class="active"><a href='./Invoice.htm'><span>Invoice
									Info</span></a></li>
						<li><a href='./Shipment.htm'><span>Sales Order
									Info</span></a></li>
						<li><a href='./Material.htm'><span>Material Info</span></a></li>
						<li><a href="./logout.htm">SignOut</a></li>
					</ul>
				</div>

				<div class="clr"></div>

			</div>
		</div>
		<div class="content">
			<div class="content_resize">
				<div class="clr"></div>
				<div>
					<div class="article">
						<br />
						<h2>
							<span>Invoice Info</span>
						</h2>
						<b> <u>Invoice Header Information</u>
						</b> <br />
						<table class="tg">
							<tr>
								<th class="tg-kkr7">InvoiceNumber</th>
								<th class="tg-t8h0">Status</th>
								<th class="tg-t8h0">Sold_From</th>
								<th class="tg-t8h0">Ship_To</th>
								<th class="tg-t8h0">End_User_Company</th>
								<th class="tg-t8h0">Company_Name</th>
								<th class="tg-t8h0">Customer_PO</th>
								<th class="tg-t8h0">Sold_To</th>
								<th class="tg-t8h0">Order Plant</th>
								<th class="tg-t8h0">Address1</th>
								<th class="tg-t8h0">Address2</th>
								<th class="tg-t8h0">City</th>
								<th class="tg-t8h0">State</th>
								<th class="tg-qta8">Zip</th>
							</tr>
							<tr>
								<td class="tg-031e"><a href="#"
									onclick="toggle_visibility('${invoiceHeader.INVOI_NBR}')">${invoiceHeader.INVOI_NBR}</a>
								</td>
								<td class="tg-031e">${invoiceHeader.ORDER_STATUS_CD}</td>
								<td class="tg-031e">${invoiceHeader.SOLD_FROM_COMPANY_CD}</td>
								<td class="tg-031e">${invoiceHeader.SHIP_TO_COMPANY_CD}</td>
								<td class="tg-031e">${invoiceHeader.END_USER_COMPANY_CD}</td>
								<td class="tg-031e">${invoiceHeader.OVERRIDE_COMPANY_NAME}</td>
								<td class="tg-031e">${invoiceHeader.ORDER_PLANT_CD}</td>
								<td class="tg-031e">${invoiceHeader.CUSTOMER_PO}</td>
								<td class="tg-031e">${invoiceHeader.SOLD_TO_COMPANY_CD}</td>
								<td class="tg-031e">${invoiceHeader.OVERRIDE_ADDRESS1}</td>
								<td class="tg-031e">${invoiceHeader.OVERRIDE_ADDRESS2}</td>
								<td class="tg-031e">${invoiceHeader.OVERRIDE_CITY}</td>
								<td class="tg-031e">${invoiceHeader.OVERRIDE_STATE}</td>
								<td class="tg-031e">${invoiceHeader.OVERRIDE_ZIP}</td>
							</tr>
						</table>

						<!--  expand collapse : start -->
						<div id='${invoiceHeader.INVOI_NBR}' class="filter-container">
							<div class="triggear">
								<a href="#"
									onclick="toggle_visibility('${invoiceHeader.INVOI_NBR}')">Hide</a>
							</div>
							<div class="toggle-container">
								<b> <u>Invoice Details</u></b>
								<display:table name="invDetail"
									style="border: 1px solid; width: 950px; height: 20px;">
									<display:column property="PRODUCT_NBR" title="Product_Number" />
									<display:column property="NET_VAL" title="Net_Value" />
									<display:column property="ITEM_CAT" title="Item_Category" />
									<display:column property="ORDER_NBR" title="Order_Number" />
									<display:column property="BASE_UOM" title="Unit_Of_Measure" />
									<display:column property="OVERRIDE_PRODUCT" title="Product" />
									<display:column property="ORDER_LINE_NBR"
										title="OrderLineNumber" />
								</display:table>
							</div>

							<table border="0">
								<tr>
									<td><div>
											<b> <u>Invoice Detail Comments</u>
											</b> <br />
											<display:table name="invDetailComments" cellspacing="2"
												style="border: 1px solid; width: 600px;">
												<display:column property="ORDER_NBR" title="OrderNumber" />
												<display:column property="ORDER_LINE_NBR"
													title="OrderLineNumber" />
												<display:column property="LINE" title="Line" />
												<display:column property="TYPE" title="Type" />
											</display:table>
										</div></td>
									<td><div>
											<b> <u>Invoice Header Comments</u>
											</b> <br />
											<display:table name="invoiceOrderHeaderComments"
												cellspacing="2" style="border: 1px solid; width: 300px;">
												<display:column property="ORDER_NBR" title="OrderNumber" />
												<display:column property="LINE" title="Line" />
												<display:column property="TYPE" title="Type" />
											</display:table>
										</div></td>
								</tr>
								<tr><td><div>
								<b> <u>Invoice Item Attachments</u>
								</b> <br />
								<display:table name="invItemAttachments" cellspacing="2"
									style="border:solid 1px;">
									<display:column property="DOKAR" title="DOKAR" />
									<display:column property="DOKVR" title="DOKVR" />
									<display:column property="DOKTL" title="DOKTL" />
									<display:column property="DOKNR" title="DOKNR" />
									<display:column property="OBJKY" title="OBJKY" />
								</display:table>
							</div></td><td></td></tr>
							</table>
						</div>
						<!--  expand collapse : end -->


						<div class="clr"></div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="./footer.jsp" %>
	</div>
	
</body>
</html>
