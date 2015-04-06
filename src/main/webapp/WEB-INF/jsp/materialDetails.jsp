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
						<li><a href='./Invoice.htm'><span>Invoice Info</span></a></li>
						<li><a href='./Shipment.htm'><span>Sales Order
									Info</span></a></li>
						<li class="active"><a href='./Material.htm'><span>Material
									Info</span></a></li>
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
							<span>Material Info</span>
						</h2>
						<form:form name="registerForm" method="POST" commandName="materialForm"	action="./Material.htm"> 
						<form:select id="materialIds" multiple="single"
									path="materialId">
									<form:option value="-1" selected="selected">Select</form:option>
									<form:options items="${materialForm.materialIdList}" />
								</form:select>
							<input type=submit value="go"/>
						</form:form>
					</div>
					<div class="article">
						<br />						
						<display:table name="imyMatOnlineList" cellspacing="2"
							style="border: 1px solid; width: 600px;">
							<display:column property="MATERIAL" title="Material Id" />
							<display:column property="MAT_TYPE" title="Type" />
							<display:column property="MATERIAL_GROUP" title="Group" />
							<display:column property="MAT_DESC" title="Description" />
							<display:column property="STOCK" title="Stock" />
							<display:column property="NET_WEIGHT" title="Net Weight" />
							<display:column property="UOM" title="Unit of Measure" />
							<display:column property="BOM" title="BOM" />
						</display:table>
						<br />
						<div class="article">
							<b> Material Plant Details</b> <br />
							<display:table name="imyMatPlantList" cellspacing="2"
								style="border: 1px solid; width: 600px;">
								<display:column property="PLANT" title="Plant" />
								<display:column property="MRP_CONT" title="Container" />
								<display:column property="MAINT_STATUS" title="Status" />
								<display:column property="MRP_TYPE" title="Type" />
							</display:table>
						</div>
						<div class="article">
							<b> Material Storage Details</b> <br />
							<display:table name="imyMatStorageDetailsList" cellspacing="2"
								style="border: 1px solid; width: 600px;">
								<display:column property="MAINT_STATUS" title="Status" />
								<display:column property="STO_LOCATION" title="StorageLocation" />
								<display:column property="STOC_IN_QLTY_INS" title="Quality" />
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