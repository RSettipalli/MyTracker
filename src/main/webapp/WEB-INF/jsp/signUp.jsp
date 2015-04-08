<%@include file="./header.jsp"%>

<script>
//window.onload = setDefaults;
</script>

<div>
	<div class="content">
		<div class="content_resize">
			<div class="clr"></div>
			<div class="article">
				<div class="register">
					<h2>
						<span>Register</span>
					</h2>
					<div class="clr"></div>
					<form:form name="registerForm" method="POST" commandName="user"
						action="./signUp.htm">
						<div class="clr">${message}</div>
						<p>
						<ul>
							<li><form:label path="fname">First Name</form:label> : <form:input
									path="fname" value="" /><br /></li>
							<li><form:label path="lname">Last Name</form:label> : <form:input
									path="lname" value="" /><br /></li>
							<li><form:label path="email">Email</form:label> : <form:input
									path="email" value="" /><br /></li>
							<li><form:label path="password">Password</form:label> : <form:password
									path="password" showPassword="true" /><br /></li>
							<li><form:label path="UserType">Type</form:label> :
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<form:radiobuttons path="UserType" items="${AllUserTypes}"
									onclick="showHideSelects()" /><br /></li>
							<li><label>Company Id</label> :
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<form:select id="bukrsList" multiple="single" path="companyId">
									<form:option value="-1" selected="selected">Select</form:option>
									<form:options items="${user.BUKRSList}" />
								</form:select> <form:select id="kunnrList" multiple="single" path="customerId">
									<form:option value="-1" selected="selected">Select</form:option>
									<form:options items="${user.KUNNRList}" />
								</form:select></li>
							<br />
							<br />
							<input class="button" type="submit" value="Sign In"
								onclick="register()" />
						</ul>
						</p>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="./footer.jsp"%>
</div>
</body>
</html>