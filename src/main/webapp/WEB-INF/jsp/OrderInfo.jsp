<%@include file="./header.jsp"%>
<div>
	<div class="content">
		<div class="content_resize">
			<div class="clr"></div>
			<div>
				<c:if test="${not empty orderHeader}">
					<div class="article">
						<br />
						<h2>
							<span>Delivery Info</span>
						</h2>
						<b> Delivery Header Information </b> <br />
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
							<c:forEach var="odHeader" items="${orderHeader}">
								<tr>
									<td class="tg-031e"><a href="#"
										onclick="toggle_visibility('${odHeader.DELVI_NBR}')">${odHeader.DELVI_NBR}</a>
									</td>
									<td class="tg-031e">${odHeader.ORDER_STATUS_CD}</td>
									<td class="tg-031e">${odHeader.SHIP_TO_COMPANY_CD}</td>
									<td class="tg-031e">${odHeader.END_USER}</td>
									<td class="tg-031e">${odHeader.END_USER_COMPANY_CD}</td>
									<td class="tg-031e">${odHeader.OVERRIDE_COMPANY_NAME}</td>
									<td class="tg-031e">${odHeader.ORDER_REF_NUM}</td>
									<td class="tg-031e">${odHeader.ORDER_PLANT_CD}</td>
									<td class="tg-031e">${odHeader.CUSTOMER_PO}</td>
									<td class="tg-031e">${odHeader.OVERRIDE_ADDRESS1}</td>
									<td class="tg-031e">${odHeader.OVERRIDE_ADDRESS2}</td>
									<td class="tg-031e">${odHeader.OVERRIDE_CITY}</td>
									<td class="tg-031e">${odHeader.OVERRIDE_STATE}</td>
									<td class="tg-031e">${odHeader.OVERRIDE_ZIP}</td>
								</tr>
							</c:forEach>
						</table>
						<!--  expand collapse : start -->
						<c:forEach var="odHeader" items="${orderHeader}">
							<div id='${odHeader.DELVI_NBR}' class="filter-container">
								<div class="triggear">
									<a href="#"
										onclick="toggle_visibility('${odHeader.DELVI_NBR}')">Hide</a>
								</div>
								<div class="toggle-container">
									<b> Delivery Details</b>
									<display:table list="${odHeader.getIMY_MGOL_OD_DETAIL()}"
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

								<table>
									<tr>
										<td><div class="article">
												<b> Delivery Detail Comments</b> <br />
												<c:forEach var="odDetail"
													items="${odHeader.getIMY_MGOL_OD_DETAIL()}">
													<display:table
														name="${odDetail.get_IMY_MGOL_SO_DETAIL_COMMENT ()}"
														cellspacing="2" style="border: 1px solid; width: 600px;">
														<display:column property="ORDER_NBR" title="OrderNumber" />
														<display:column property="ORDER_LINE_NBR"
															title="OrderLineNumber" />
														<display:column property="LINE" title="Line" />
														<display:column property="TYPE" title="Type" />
													</display:table>
												</c:forEach>
											</div></td>
										<td><div class="article">
												<b> Delivery Header Comments </b> <br />
												<display:table
													list="${odHeader.getIMY_MGOL_OD_HEADER_COMMENT ()}"
													cellspacing="2" style="border: 1px solid; width: 300px;">
													<display:column property="ORDER_NBR" title="OrderNumber" />
													<display:column property="LINE" title="Line" />
													<display:column property="TYPE" title="Type" />
													<display:column property="DELVI_NBR_OD_HEADER"
														title="OrderDetailNumber" />
												</display:table>
											</div></td>
									</tr>
									<tr>
										<td>
											<div class="article">
												<b> Delivery Item Attachments</b> <br />
												<c:forEach var="odDetail"
													items="${odHeader.getIMY_MGOL_OD_DETAIL()}">
													<c:if
														test="${not empty odDetail.getIMY_MGOL_OD_ITEM_ATTACHM ()}">
														<display:table
															name="${odDetail.getIMY_MGOL_OD_ITEM_ATTACHM ()}"
															cellspacing="2" style="border:solid 1px;">
															<display:column property="DOKAR" title="DOKAR" />
															<display:column property="DOKVR" title="DOKVR" />
															<display:column property="DOKTL" title="DOKTL" />
															<display:column property="DOKNR" title="DOKNR" />
															<display:column property="OBJKY" title="OBJKY" />
														</display:table>
													</c:if>
												</c:forEach>
											</div>
										</td>
										<td></td>
									</tr>
								</table>
							</div>
						</c:forEach>
						<!--  expand collapse : end -->
						<div class="clr"></div>
					</div>
				</c:if>
				<c:if test="${empty orderHeader}">
					<div class="article">
						<br />
						<h2>
							<span>Delivery Info</span>
						</h2>
						<b> Delivery Header Information </b> <br /> ${message}
					</div>
				</c:if>
			</div>
		</div>
	</div>
	<%@include file="./footer.jsp"%>
</div>

</body>
</html>
