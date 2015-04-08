<%@include file="./header.jsp"%>
<div>
	<div class="content">
		<div class="content_resize">
			<div class="clr"></div>
			<div>
				<c:if test="${not empty invoiceHeader}">
					<div class="article">
						<br />
						<h2>
							<span>Invoice Info</span>
						</h2>
						<b> Invoice Header Information</b> <br />
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
							<c:forEach var="invHeader" items="${invoiceHeader}">
								<tr>
									<td class="tg-031e"><a href="#"
										onclick="toggle_visibility('${invHeader.INVOI_NBR}')">${invHeader.INVOI_NBR}</a>
									</td>
									<td class="tg-031e">${invHeader.ORDER_STATUS_CD}</td>
									<td class="tg-031e">${invHeader.SOLD_FROM_COMPANY_CD}</td>
									<td class="tg-031e">${invHeader.SHIP_TO_COMPANY_CD}</td>
									<td class="tg-031e">${invHeader.END_USER_COMPANY_CD}</td>
									<td class="tg-031e">${invHeader.OVERRIDE_COMPANY_NAME}</td>
									<td class="tg-031e">${invHeader.ORDER_PLANT_CD}</td>
									<td class="tg-031e">${invHeader.CUSTOMER_PO}</td>
									<td class="tg-031e">${invHeader.SOLD_TO_COMPANY_CD}</td>
									<td class="tg-031e">${invHeader.OVERRIDE_ADDRESS1}</td>
									<td class="tg-031e">${invHeader.OVERRIDE_ADDRESS2}</td>
									<td class="tg-031e">${invHeader.OVERRIDE_CITY}</td>
									<td class="tg-031e">${invHeader.OVERRIDE_STATE}</td>
									<td class="tg-031e">${invHeader.OVERRIDE_ZIP}</td>
								</tr>
							</c:forEach>
						</table>

						<!--  expand collapse : start -->
						<c:forEach var="invHeader" items="${invoiceHeader}">
							<div id='${invHeader.INVOI_NBR}' class="filter-container">
								<div class="triggear">
									<a href="#"
										onclick="toggle_visibility('${invHeader.INVOI_NBR}')">Hide</a>
								</div>
								<div class="toggle-container">
									<b> Invoice Details</b>
									<display:table list="${invHeader.getIMY_MGOL_INV_DETAIL()}"
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

								<table>
									<tr>
										<td><div>
												<b> Invoice Detail Comments </b> <br />
												<c:forEach var="invDetail"
													items="${invHeader.getIMY_MGOL_INV_DETAIL()}">
													<display:table
														name="${invDetail.getIMY_MGOL_SO_DETAIL_COMMENT ()}"
														cellspacing="2" style="border: 1px solid; width: 600px;">
														<display:column property="ORDER_NBR" title="OrderNumber" />
														<display:column property="ORDER_LINE_NBR"
															title="OrderLineNumber" />
														<display:column property="LINE" title="Line" />
														<display:column property="TYPE" title="Type" />
													</display:table>
												</c:forEach>
											</div></td>
										<td><div>
												<b> Invoice Header Comments </b> <br />
												<display:table
													list="${invHeader.getIMY_MGOL_INV_HEADER_COMMEN ()}"
													cellspacing="2" style="border: 1px solid; width: 300px;">
													<display:column property="ORDER_NBR" title="OrderNumber" />
													<display:column property="LINE" title="Line" />
													<display:column property="TYPE" title="Type" />
												</display:table>
											</div></td>
									</tr>
									<tr>
										<td><div>
												<b> Invoice Item Attachments </b> <br />
												<c:forEach var="invDetail"
													items="${invHeader.getIMY_MGOL_INV_DETAIL()}">
													<c:if
														test="${not empty invDetail.getIMY_MGOL_INV_ITEM_ATMT ()}">
														<display:table name="invItemAttachments" cellspacing="2"
															style="border:solid 1px;">
															<display:column property="DOKAR" title="DOKAR" />
															<display:column property="DOKVR" title="DOKVR" />
															<display:column property="DOKTL" title="DOKTL" />
															<display:column property="DOKNR" title="DOKNR" />
															<display:column property="OBJKY" title="OBJKY" />
														</display:table>
													</c:if>
												</c:forEach>
											</div></td>
										<td></td>
									</tr>
								</table>
							</div>
						</c:forEach>
						<!--  expand collapse : end -->


						<div class="clr"></div>
					</div>
				</c:if>
				<c:if test="${empty invoiceHeader}">
					<div class="article">
						<br />
						<h2>
							<span>Invoice Info</span>
						</h2>
						<b> Invoice Header Information</b> <br /> ${message}
					</div>
				</c:if>
			</div>
		</div>
	</div>
	<%@include file="./footer.jsp"%>
</div>

</body>
</html>
