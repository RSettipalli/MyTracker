<%@include file="./header.jsp"%>
<div class="mainDetail">
	<div class="content">
		<div class="content_resize">
			<table style="display: inline-block;">
				<tr>
					<td colspan="2"><b> Invoice Details for Invoice #${invNum}</b></td>
				</tr>
				<tr>
					<td colspan="2">
						<table class="tg" style="float: left;">
							<thead>
								<tr>
									<th class="tg-kkr7">Order Line Number</th>
									<th class="tg-kkr7">Product Number</th>
									<th class="tg-kkr7">Product</th>
									<th class="tg-kkr7">Unit of Measure</th>
									<th class="tg-kkr7">Net Value</th>
									<th class="tg-kkr7">Order Number</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="invDetail" items="${IMY_MGOL_INV_DETAIL}">

									<tr>
										<td class="tg-031e">${invDetail.ORDER_LINE_NBR}</td>
										<td class="tg-031e">${invDetail.PRODUCT_NBR}</td>
										<td class="tg-031e">${invDetail.OVERRIDE_PRODUCT}</td>
										<td class="tg-031e">${invDetail.BASE_UOM}</td>
										<td class="tg-031e">${invDetail.NET_VAL}</td>
										<td class="tg-031e">${invDetail.ORDER_NBR}</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td><b> Invoice Detail Comments </b></td>
					<td><b> Invoice Header Comments </b></td>
				</tr>
				<tr>
					<td>
						<table class="tg" style="float: left;">
							<thead>
								<tr>
									<th class="tg-kkr7">Order Line Number</th>
									<th class="tg-kkr7">Order Number</th>
									<th class="tg-kkr7">Line</th>
									<th class="tg-kkr7">Type</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="invDetail" items="${IMY_MGOL_INV_DETAIL}">
									<c:forEach var="soDetailComment"
										items="${invDetail.getIMY_MGOL_SO_DETAIL_COMMENT ()}">
										<tr>
											<td class="tg-031e">${soDetailComment.ORDER_LINE_NBR}</td>
											<td class="tg-031e">${soDetailComment.ORDER_NBR}</td>
											<td class="tg-031e">${soDetailComment.LINE}</td>
											<td class="tg-031e">${soDetailComment.TYPE}</td>
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
								</tr>
							</thead>
							<tbody>
								<c:forEach var="invHeaderComment"
									items="${IMY_MGOL_INV_HEADER_COMMEN}">
									<tr>
										<td class="tg-031e">${invHeaderComment.ORDER_NBR}</td>
										<td class="tg-031e">${invHeaderComment.LINE}</td>
										<td class="tg-031e">${invHeaderComment.TYPE}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>

				</tr>
				<tr>
					<td><b> Invoice Item Attachments </b></td>
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
								<c:forEach var="invDetail" items="${IMY_MGOL_INV_DETAIL}">
									<c:if
										test="${not empty invDetail.getIMY_MGOL_INV_ITEM_ATMT ()}">
										<tr>
											<td class="tg-031e">${invDetail.getIMY_MGOL_INV_ITEM_ATMT ().DOKAR}</td>
											<td class="tg-031e">${invDetail.getIMY_MGOL_INV_ITEM_ATMT ().DOKVR}</td>
											<td class="tg-031e">${invDetail.getIMY_MGOL_INV_ITEM_ATMT ().DOKTL}</td>
											<td class="tg-031e">${invDetail.getIMY_MGOL_INV_ITEM_ATMT ().DOKNR}</td>
											<td class="tg-031e">${invDetail.getIMY_MGOL_INV_ITEM_ATMT ().OBJKY}</td>
										</tr>
									</c:if>
								</c:forEach>
							</tbody>
						</table>
					</td>
					<td><c:if test="${SO_NBR != null || DELIV_NBR != null}">
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
									<c:if test="${DELIV_NBR != null}">
										<tr>
											<td class="tg-031e"><a
												href="./DeliveryDetail.htm?DeliveryID=${DELIV_NBR}">Delivery
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