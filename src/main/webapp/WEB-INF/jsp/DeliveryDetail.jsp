<%@include file="./header.jsp"%>
<div class="mainDetail">
	<div class="content">
		<div class="content_resize">
			<table style="display: inline-block;">
				<tr>
					<td colspan="2"><b>Delivery Details for Delivery
							#${delivNum }</b></td>
				</tr>
				<tr>
					<td colspan="2">
						<table class="tg" style="float: left;">
							<thead>
								<tr>
									<th class="tg-kkr7">Order Line Number</th>
									<th class="tg-kkr7">Product Number</th>
									<th class="tg-kkr7">Product</th>
									<th class="tg-kkr7">Order Quantity</th>
									<th class="tg-kkr7">Unit Of Measure</th>
									<th class="tg-kkr7">Order Unit Of Measure</th>
									<th class="tg-kkr7">Base Unit Of Measure</th>
									<th class="tg-kkr7">Base Price</th>
									<th class="tg-kkr7">Net Value</th>
									<th class="tg-kkr7">Order Number</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="deliveryDetails" items="${IMY_MGOL_OD_DETAIL}">
									<tr>
										<td class="tg-031e">${deliveryDetails.ORDER_LINE_NBR }</td>
										<td class="tg-031e">${deliveryDetails.PRODUCT_NBR }</td>
										<td class="tg-031e">${deliveryDetails.OVERRIDE_PRODUCT }</td>
										<td class="tg-031e">${deliveryDetails.ORD_QTY }</td>
										<td class="tg-031e">${deliveryDetails.BASE_UOM }</td>
										<td class="tg-031e">${deliveryDetails.ORD_UOM_DESC }</td>
										<td class="tg-031e">${deliveryDetails.BASE_UOM_DESC }</td>
										<td class="tg-031e">${deliveryDetails.BASE_PRICE }</td>
										<td class="tg-031e">${deliveryDetails.NET_VAL }</td>
										<td class="tg-031e">${deliveryDetails.ORDER_NBR }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td><b>Delivery Detail Comments</b></td>
					<td><b>Delivery Header Comments </b></td>
				</tr>
				<tr>
					<td>
						<table class="tg" style="float: left;">
							<thead>
								<tr>
									<th class="tg-kkr7">Order Line Number</th>
									<th class="tg-kkr7">Order Number</th>
									<th class="tg-kkr7">Line</th>
									<th class="tg-kkr7">Types</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="deliveryDetails" items="${IMY_MGOL_OD_DETAIL}">
									<c:forEach var="deliveryDetailComment"
										items="${deliveryDetails.get_IMY_MGOL_SO_DETAIL_COMMENT () }">
										<tr>
											<td class="tg-031e">${deliveryDetailComment.ORDER_LINE_NBR }</td>
											<td class="tg-031e">${deliveryDetailComment.ORDER_NBR }</td>
											<td class="tg-031e">${deliveryDetailComment.LINE }</td>
											<td class="tg-031e">${deliveryDetailComment.TYPE }</td>
										</tr>
									</c:forEach>
								</c:forEach>
							</tbody>
						</table>
					</td>
					<td>
						<table class="tg" style="float: left;">
							<thead>
								<tr>
									<th class="tg-kkr7">Order Number</th>
									<th class="tg-kkr7">Line</th>
									<th class="tg-kkr7">Type</th>
									<th class="tg-kkr7">Order Detail Number</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="deliverHeaderComment"
									items="${IMY_MGOL_OD_HEADER_COMMENT }">
									<tr>
										<td class="tg-031e">${deliverHeaderComment.ORDER_NBR}</td>
										<td class="tg-031e">${deliverHeaderComment.LINE}</td>
										<td class="tg-031e">${deliverHeaderComment.TYPE}</td>
										<td class="tg-031e">${deliverHeaderComment.DELVI_NBR_OD_HEADER}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td><b>Delivery Item Attachments</b></td>
				</tr>
				<tr>
					<td>
						<table class="tg" style="float: left;">
							<thead>
								<tr>
									<th class="tg-kkr7">DOKAR</th>
									<th class="tg-kkr7">DOKVR</th>
									<th class="tg-kkr7">DOKTL</th>
									<th class="tg-kkr7">DOKNR</th>
									<th class="tg-kkr7">OBJKY</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="deliveryDetails" items="${IMY_MGOL_OD_DETAIL}">
									<c:if
										test="${not empty deliveryDetails.getIMY_MGOL_OD_ITEM_ATTACHM ()}"></c:if>
									<tr>
										<td class="tg-031e">${deliveryDetails.getIMY_MGOL_OD_ITEM_ATTACHM().DOKAR}</td>
										<td class="tg-031e">${deliveryDetails.getIMY_MGOL_OD_ITEM_ATTACHM().DOKVR}</td>
										<td class="tg-031e">${deliveryDetails.getIMY_MGOL_OD_ITEM_ATTACHM().DOKTL}</td>
										<td class="tg-031e">${deliveryDetails.getIMY_MGOL_OD_ITEM_ATTACHM().DOKNR}</td>
										<td class="tg-031e">${deliveryDetails.getIMY_MGOL_OD_ITEM_ATTACHM().OBJKY}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>
					<td><c:if test="${SO_NBR != null || INVOI_NBR != null}">
							<table class="tg" style="float: left;">
								<thead>
									<tr>
										<th class="tg-kkr7">Related Pages</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${SO_NBR != null}">
										<tr>
											<td class="tg-031e"><a
												href="./SalesOrderDetail.htm?OrderID=${SO_NBR}">Sales
													Order Detail</a></td>
										</tr>
									</c:if>
									<c:if test="${INVOI_NBR != null}">
										<tr>
											<td class="tg-031e"><a
												href="./InvoiceDetail.htm?InvoiceID=${INVOI_NBR}">Invoice
													Detail</a></td>
										</tr>
									</c:if>
								</tbody>
							</table>
						</c:if></td>
				</tr>
			</table>
		</div>
	</div>
</div>
<%@include file="./footer.jsp"%>