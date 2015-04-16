<%@include file="./header.jsp"%>
<div>
	<div class="content">
		<div class="content_resize">
			<div>
				<div class="article">
					<div class="clr"></div>
					<form:form method="POST" class="loginform" commandName="loginForm"
						action="./login.htm">
						<table align="center">
							<tr>
								<td><label>User ID</label></td>
								<td><label>Password</label></td>
							</tr>
							<tr>
								<td><form:input path="email" value=""
										placeholder="user@domain.com" type="email" /></td>
								<td><form:password path="password" showPassword="true" /></td>
								<td><input class="button" type="submit" value="Submit"
									onclick="login()" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
							</tr>
							<tr>
								<td>${message}</td>
								<td><a href="./forgot.htm">Forgot Password</a></td>
							</tr>
						</table>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="./footer.jsp"%>
</div>