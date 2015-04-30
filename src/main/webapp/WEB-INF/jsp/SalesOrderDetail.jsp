<%@include file="./header.jsp"%>
<div class="mainDetail">
	<div class="content">
		<div class="content_resize">
			<table style="display: inline-block;">
				<tr>
					<td><b>Sales Order Detail Information for Sales Order #${ORDER_NBR }</b></td>
				</tr>
				<tr>
					<td colspan="2">
						<table id="soDetailInfo" class="tg">
							<thead>
								<tr>
									<th class="tg-kkr7">Product Number</th>
									<th class="tg-kkr7">Net Value</th>
									<th class="tg-kkr7">Order Number</th>
									<th class="tg-kkr7">Unit Of Measure</th>
									<th class="tg-kkr7">Product</th>
									<th class="tg-kkr7">Order Line Number</th>
									<th class="tg-kkr7">Order Quantity</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="IMY_MGOL_SO_DETAIL"
									items="${IMY_MGOL_SO_DETAIL}">
									<tr>
										<td class="tg-031e">${IMY_MGOL_SO_DETAIL.PRODUCT_NBR}</td>
										<td class="tg-031e">${IMY_MGOL_SO_DETAIL.NET_VAL}</td>
										<td class="tg-031e">${IMY_MGOL_SO_DETAIL.ORDER_NBR}</td>
										<td class="tg-031e">${IMY_MGOL_SO_DETAIL.BASE_UOM}</td>
										<td class="tg-031e">${IMY_MGOL_SO_DETAIL.OVERRIDE_PRODUCT}</td>
										<td class="tg-031e">${IMY_MGOL_SO_DETAIL.ORDER_LINE_NBR}</td>
										<td class="tg-031e">${IMY_MGOL_SO_DETAIL.ORD_QTY}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
				<tr><br/>
				</tr>
				<tr>
					<td><b>Sales Order Detail Comments</b></td>
					<td><b>Sales Order Header Comments</b></td>
				</tr>
				<tr>
					<td>
						<table id="soDetailComment" class="tg" style="float:left;">
							<thead>
								<tr>
									<th class="tg-kkr7">Order Number</th>
									<th class="tg-kkr7">Order Line Number</th>
									<th class="tg-kkr7">Line</th>
									<th class="tg-kkr7">Type</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="IMY_MGOL_SO_DETAIL"
									items="${IMY_MGOL_SO_DETAIL}">
									<c:forEach var="soDetailComment"
										items="${IMY_MGOL_SO_DETAIL.getIMY_MGOL_SO_DETAIL_COMMENT()}">
										<tr>
											<td class="tg-031e">${soDetailComment.ORDER_NBR }</td>
											<td class="tg-031e">${soDetailComment.ORDER_LINE_NBR }</td>
											<td class="tg-031e">${soDetailComment.LINE }</td>
											<td class="tg-031e">${soDetailComment.TYPE }</td>
										</tr>
									</c:forEach>
								</c:forEach>
							</tbody>
						</table>
					</td>
					<td>
						<table id="soHeaderComment" class="tg" style="float:left;">
							<thead>
								<tr>
									<th class="tg-kkr7">Order Number</th>
									<th class="tg-kkr7">Line</th>
									<th class="tg-kkr7">Type</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="IMY_MGOL_SO_HEADER_COMMENT"
									items="${IMY_MGOL_SO_HEADER_COMMENT}">
									<tr>
										<td class="tg-031e">${soDetailComment.ORDER_NBR }</td>
										<td class="tg-031e">${soDetailComment.LINE }</td>
										<td class="tg-031e">${soDetailComment.TYPE }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td><b> Sales Order Item Attachments</b></td>
				</tr>
				<tr>
					<td>
						<table id="soAttachment" class="tg" style="float:left">
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
								<c:forEach var="soDetail" items="${IMY_MGOL_SO_DETAIL3}">
									<c:if
										test="${not empty soDetail.getIMY_MGOL_SO_ITEM_ATTACHM ()}">
										<c:forEach var="soItemAttachment"
											items="${soDetail.getIMY_MGOL_SO_ITEM_ATTACHM ()}">
											<tr>
												<td class="tg-031e">${soItemAttachment.DOKAR }</td>
												<td class="tg-031e">${soItemAttachment.DOKVR }</td>
												<td class="tg-031e">${soItemAttachment.DOKTL }</td>
												<td class="tg-031e">${soItemAttachment.DOKNR }</td>
												<td class="tg-031e">${soItemAttachment.OBJKY }</td>
											</tr>
										</c:forEach>
									</c:if>
								</c:forEach>
							</tbody>
						</table>
					</td>
					<td><c:if test="${DELIV_NBR != null || INVOI_NBR != null}">
							<table class="tg" style="float: left;">
								<thead>
									<tr>
										<th class="tg-kkr7">Related Pages</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${DELIV_NBR != null}">
										<tr>
											<td class="tg-031e"><a
												href="./DeliveryDetail.htm?DeliveryID=${DELIV_NBR}">Delivery Details</a></td>
										</tr>
									</c:if>
									<c:if test="${INVOI_NBR != null}">
										<tr>
											<td class="tg-031e"><a
												href="./InvoiceDetail.htm?InvoiceID=${INVOI_NBR}">Invoice
													Details</a></td>
										</tr>
									</c:if>
								</tbody>
							</table>
						</c:if></td>
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>
			</table>
		</div>
	</div>
</div>
<%@include file="./footer.jsp"%>