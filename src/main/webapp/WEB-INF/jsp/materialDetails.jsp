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
				</div>
			</div>
		</div>
		<%@include file="./footer.jsp" %>
	</div>
	
</body>
</html>