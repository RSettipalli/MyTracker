<%@include file="./header.jsp" %>
<div>
	<div class="content">
		<div class="content_resize">
			<div class="clr"></div>
			<div>
				<div class="article">
					<br />
					<h2>
						<span>Material Info</span>
					</h2>
					<form:form name="registerForm" method="POST"
						commandName="materialForm" action="./Material.htm">
						<form:select id="materialIds" multiple="single" path="materialId"
							onchange="onChange()">
							<form:option value="-1" selected="selected">Select</form:option>
							<form:options items="${materialForm.materialIdList}" />
						</form:select>
						<input id="btnGo" type=submit disabled="disabled" value="Go" />
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="./footer.jsp"%>
</div>

</body>
</html>