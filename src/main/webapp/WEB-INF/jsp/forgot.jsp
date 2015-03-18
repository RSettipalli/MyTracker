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
						<li><a href="./login.htm">Login</a></li>
					</ul>
				</div>
				<div class="clr"></div>
			</div>
		</div>
		<div class="content">
			<div class="content_resize">
				<center>
					<img src="./images/mygologo_smallsize.jpg" alt="" class="hbg_img" />
				</center>
				<div class="clr"></div>
				<div>
					<div class="article">
						<h2>
							<span>Forgot password</span>
						</h2>
						<div class="clr"></div>
						<center>
							<form:form method="POST" class="loginform" id="forgotForm"
								commandName="forgotUser" action="./forgot.htm">
								<div>${forgotMsg}</div>
								<p>
									<label>Email Id</label>
									<form:input path="email" value="" />
									<br />
									<br /> <input class="button" type="submit" value="Submit"
										onclick="forgotSubmit()" />
								</p>
							</form:form>
						</center>
					</div>
				</div>
			</div>
		</div>
		<%@include file="./footer.jsp" %>
	</div>
</body>
</html>
