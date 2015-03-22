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
						<li class="active"><a href="./Home.htm">Home</a></li>
						<li><a href='./OrderInfo.htm'><span>Delivery Info</span></a></li>
						<li><a href='./Invoice.htm'><span>Invoice Info</span></a></li>
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
					<c:if test="${companyInfo != null}">
						<h2>
							<span>Company Profile</span>
						</h2>
						<b> <u>Company Information</u>
						</b>
						<table>
							<tr>
								<td><b>Company Code</b></td>
								<td><b>:</b></td>
								<td>${companyInfo.BUKRS}</td>
								<td>&nbsp;</td>
								<td><b>Name of Company</b></td>
								<td><b>:</b></td>
								<td>${companyInfo.BUTXT}</td>
							</tr>
							<tr>
								<td><b>Language</b></td>
								<td><b>:</b></td>
								<td>${companyInfo.SPRAS}</td>
								<td>&nbsp;</td>
								<td><b>Currency</b></td>
								<td><b>:</b></td>
								<td>${companyInfo.WAERS}</td>
							</tr>
							<tr>
								<td><b>City</b></td>
								<td><b>:</b></td>
								<td>${companyInfo.ORT01}</td>
								<td>&nbsp;</td>
								<td><b>Address</b></td>
								<td><b>:</b></td>
								<td>${companyInfo.ADRNR}</td>
							</tr>
						</table>
						<div>
							<b> <u>Company Ship Points</u>
							</b> <br />
							<display:table name="shipPoint" cellspacing="2"
								style="border: 1px solid; width: 600px;">
								<display:column property="BUKRS" title="Company Code" />
								<display:column property="ADDRESS1" title="Street" />
								<display:column property="ADDRESS2" title="Street 2" />
								<display:column property="CITY" title="City" />
								<display:column property="COUNTRY" title="Country" />
								<display:column property="ZIP" title="Zip" />
								<display:column property="PHONE" title="Phone" />
								<display:column property="FAX" title="Fax" />
								<display:column property="LANGUAGE" title="Language" />
							</display:table>
						</div>
						</c:if>
						<c:if test="${customerInfo != null }">
						<h2>
							<span>Customer Profile</span>
						</h2>
						<b> <u>Customer Information</u>
						</b>
						<table>
							<tr>
								<td><b>Customer Code</b></td>
								<td><b>:</b></td>
								<td>${customerInfo.KUNNR}</td>
								<td>&nbsp;</td>
								<td><b>Name of Customer</b></td>
								<td><b>:</b></td>
								<td>${customerInfo.NAME1}</td>
							</tr>
							<tr>
								<td><b>Street Address</b></td>
								<td><b>:</b></td>
								<td>${customerInfo.STREET}</td>
								<td>&nbsp;</td>
								<td><b>City</b></td>
								<td><b>:</b></td>
								<td>${customerInfo.CITY}</td>
							</tr>
							<tr>
								<td><b>State</b></td>
								<td><b>:</b></td>
								<td>${customerInfo.REGION}</td>
								<td>&nbsp;</td>
								<td><b>Country</b></td>
								<td><b>:</b></td>
								<td>${customerInfo.COUNTRY}</td>
							</tr>
							<tr>
								<td><b>Zip</b></td>
								<td><b>:</b></td>
								<td>${customerInfo.POSTL_COD1}</td>
								<td>&nbsp;</td>
								<td><b>Phone Number</b></td>
								<td><b>:</b></td>
								<td>${customerInfo.TELEPHONE}</td>
							</tr>
						</table>
						<c:if test="${customerBank != null }">
						<div>
							<b> <u>Customer Bank</u>
							</b> <br />
							<display:table name="customerBank" cellspacing="2"
								style="border: 1px solid; width: 600px;">
								<display:column property="CUST_NUMBER" title="Customer Number" />
								<display:column property="BANK_KEY" title="Bank Key" />
								<display:column property="BANK_TYPE" title="Bank Type" />
								<display:column property="BANK_ACC" title="Bank Account" />
								<display:column property="BANK_COUNTRY" title="Bank Country" />
							</display:table>
						</div>
						</c:if>
						</c:if>
						<div class="clr"></div>
					</div>
				</div>				
			</div>			
		</div>
		<%@include file="./footer.jsp" %>
	</div>
</body>
</html>