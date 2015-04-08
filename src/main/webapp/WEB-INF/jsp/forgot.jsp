<%@include file="./header.jsp"%>
<div>
	<div class="content">
		<div class="content_resize">
			<div class="clr"></div>
			<div>
				<div class="article">
					<table>
						<tr>
							<td><br /></td>
							<td><br /></td>
							<td><br /></td>
							<td>
								<h2>
									<span style="float: right;">Forgot Password</span>
								</h2>
							</td>
						</tr>
					</table>
					<div class="clr"></div>
					<form:form method="POST" class="loginform" id="forgotForm"
						commandName="forgotUser" action="./forgot.htm">
						<table align="center">
							<tr>
								<td><label>Email ID</label></td>
							</tr>
							<tr>
								<td><form:input path="email" value="" /></td>
								<td><input class="button" type="submit" value="Submit"
									onclick="forgotSubmit()" /></td>
							</tr>
						</table>
						<div align="center">${forgotMsg}</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="./footer.jsp"%>
</div>
</body>
</html>
