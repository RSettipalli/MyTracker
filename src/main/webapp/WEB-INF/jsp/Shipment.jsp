<%@include file="./header.jsp" %>

<!-- Tabs control -->
<script type="text/javascript">
jQuery(document).ready(function(){
	jQuery('.tabs .tab-links a').on('click', function(e){
		var currentAttrValue = jQuery(this).attr('href');
		 
        // Show/Hide Tabs
        jQuery(currentAttrValue).show().siblings().hide();
 
        // Change/remove current tab to active
        jQuery(this).parent('li').addClass('active').siblings().removeClass('active');
        
        e.preventDefault();
	});	
});
</script>
<div>
		<div class="content">
			<div class="content_resize">
				<div class="clr"></div>
				<div>
					<div class="article">					
						<h2>
							<span>Sales Order Info</span>
						</h2>
						
						<div class="tabs">
							<ul class="tab-links">
								<li class="active"><a href="#tab1">Tab #1</a></li>
								<li><a href="#tab2">Tab #2</a></li>
								<li><a href="#tab3">Tab #3</a></li>
							</ul>
						</div>
						
						<div class="tab-content">
							<div id="tab1" class="tab active">
								<p>
									<b> Sales Order Header Information</b> <br />
									<table class="tg">
										<tr>
											<th class="tg-kkr7">Order Number</th>
											<th class="tg-t8h0">Type</th>
											<th class="tg-t8h0">Status</th>
											<th class="tg-t8h0">Sold From</th>
											<th class="tg-t8h0">Ship To</th>
											<th class="tg-t8h0">End User</th>
											<th class="tg-t8h0">Company Name</th>
											<th class="tg-t8h0">Customer PO</th>
											<th class="tg-t8h0">Address1</th>
											<th class="tg-t8h0">Address2</th>
											<th class="tg-t8h0">City</th>
											<th class="tg-t8h0">State</th>
											<th class="tg-qta8">Zip</th>
										</tr>
										<c:forEach var="soHeader" items="${salesOrderHeader}">
										<tr>
											<td class="tg-031e"><a href="#"
												onclick="toggle_visibility('${soHeader.ORDER_NBR}')">${soHeader.ORDER_NBR}</a>
											</td>
											<td class="tg-031e">${soHeader.ORDER_TYPE}</td>
											<td class="tg-031e">${soHeader.ORDER_STATUS_CD}</td>
											<td class="tg-031e">${soHeader.SOLD_FROM_COMPANY_CD}</td>
											<td class="tg-031e">${soHeader.SHIP_TO_COMPANY_CD}</td>
											<td class="tg-031e">${soHeader.END_USER}</td>								
											<td class="tg-031e">${soHeader.OVERRIDE_COMPANY_NAME}</td>
											<td class="tg-031e">${soHeader.CUSTOMER_PO}</td>
											<td class="tg-031e">${soHeader.OVERRIDE_ADDRESS1}</td>
											<td class="tg-031e">${soHeader.OVERRIDE_ADDRESS2}</td>
											<td class="tg-031e">${soHeader.OVERRIDE_CITY}</td>
											<td class="tg-031e">${soHeader.OVERRIDE_STATE}</td>
											<td class="tg-031e">${soHeader.OVERRIDE_ZIP}</td>
										</tr>
										</c:forEach>
									</table>
									<!--  expand collapse : start -->
									<c:forEach var="soHeader" items="${salesOrderHeader}">
									<div id='${soHeader.ORDER_NBR}' class="filter-container">
										<div class="triggear">
										<a href="#"
												onclick="toggle_visibility('${soHeader.ORDER_NBR}')">Hide</a>							
										</div>
										<div class="toggle-container">
											<b> Sales Order Detail Information</b>
											<display:table list="${soHeader.getIMY_MGOL_SO_DETAIL()}"
												style="border: 1px solid; width: 950px; height: 20px;">
												<display:column property="ORDER_LINE_NBR" title="Order Line Number" />
										<display:column property="PRODUCT_NBR" title="Product Number" />
										<display:column property="OVERRIDE_PRODUCT" title="Product" />
										<display:column property="ORD_QTY" title="OrderQuantity" />
										<display:column property="NET_VAL" title="Net Value" />
										<display:column property="BASE_UOM" title="Unit Of Measure" />
										<display:column property="ORDER_NBR" title="Order Number" />
											</display:table>
										</div>
										<table>
									<tr>
										<td><div class="article">
												<b> Sales Order Detail Comments </b> <br />
												<c:forEach var="soDetail"
													items="${soHeader.getIMY_MGOL_SO_DETAIL()}">
													<display:table
														name="${soDetail.getIMY_MGOL_SO_DETAIL_COMMENT ()}"
														cellspacing="2" style="border: 1px solid; width: 600px;">
														<display:column property="ORDER_LINE_NBR" title="Order Line Number" />
														<display:column property="ORDER_NBR" title="Order Number" />
														<display:column property="LINE" title="Line" />
														<display:column property="TYPE" title="Type" />
													</display:table>
												</c:forEach>
											</div></td>
										<td><div class="article">
												<b> Sales Order Header Comments</b> <br />
												<display:table
													list="${soHeader.getIMY_MGOL_SO_HEADER_COMMENT ()}"
													cellspacing="2" style="border: 1px solid; width: 300px;">
													<display:column property="ORDER_NBR" title="Order Number" />
													<display:column property="LINE" title="Line" />
													<display:column property="TYPE" title="Type" />
												</display:table>
											</div></td>
									</tr>
									<tr>
										<td>
											<div class="article">
												<b> Sales Order Item Attachments</b> <br />
												<c:forEach var="soDetail"
													items="${soHeader.getIMY_MGOL_SO_DETAIL()}">
													<c:if
														test="${not empty soDetail.getIMY_MGOL_SO_ITEM_ATTACHM ()}">
														<display:table
															name="${soDetail.getIMY_MGOL_SO_ITEM_ATTACHM ()}"
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
								</p>
							</div>
							<div id="tab2" class="tab">
								<p>
									<b> Content 2</b>
								</p>
							</div>
							<div id="tab3" class="tab">
								<p>
									<b> Content 3</b>
								</p>
							</div>
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