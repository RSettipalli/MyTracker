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
							<tr>
								<td><b>Street</b></td>
								<td><b>:</b></td>
								<td>${companyInfo.ADDRESS1}</td>
								<td>&nbsp;</td>
								<td><b>Street 2</b></td>
								<td><b>:</b></td>
								<td>${companyInfo.ADDRESS2}</td>
							</tr>
							<tr>
								<td><b>Country</b></td>
								<td><b>:</b></td>
								<td>${companyInfo.COUNTRY}</td>
								<td>&nbsp;</td>
								<td><b>City postal code</b></td>
								<td><b>:</b></td>
								<td>${companyInfo.ZIP}</td>
							</tr>
							<tr>
								<td><b>Telephone number </b></td>
								<td><b>:</b></td>
								<td>${companyInfo.PHONE}</td>
								<td>&nbsp;</td>
								<td><b>Fax Number</b></td>
								<td><b>:</b></td>
								<td>${companyInfo.FAX}</td>
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
						<div class="clr"></div>
					</div>
				</div>				
			</div>			
		</div>
		<%@include file="./footer.jsp" %>
	</div>
</body>
</html>