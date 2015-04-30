<%@include file="./header.jsp"%>
<div>
	<div class="content">
		<div class="content_resize">
			<div class="clr"></div>
			<div>
				<div class="article">
					<br />
					<h2>
						<span>Invoice Info</span>
					</h2>

					<div class="tabs" style="float: left;">
						<ul class="tab-links">
							<li class="active"><a href="#tab1">Open</a></li>
							<li><a href="#tab2">Completed</a></li>
							<li><a href="#tab3">Cancelled</a></li>
						</ul>
					</div>
					<div class="tab-content">
						<div id="tab1" class="tab active">
							<p>
								<c:if test="${not empty invoiceHeader}">
									<table class="tg">
										<%@include file="./delivTableHeader.jsp"%>
										<c:forEach var="invHeader" items="${invoiceHeader}">
											<%@include file="./invTableBodyRows.jsp"%>
										</c:forEach>
									</table>
								</c:if>
								<c:if test="${empty invoiceHeader}">
									<span style="margin-left: .5in;"><b>No data to
											display.</b></span>
								</c:if>
							</p>
						</div>
						<div id="tab2" class="tab">
							<p>
								<c:if test="${not empty invoiceHeaderCompleted}">
									<table class="tg">
										<%@include file="./delivTableHeader.jsp"%>
										<c:forEach var="invHeader" items="${invoiceHeaderCompleted}">
											<%@include file="./invTableBodyRows.jsp"%>
										</c:forEach>
									</table>
								</c:if>
								<c:if test="${empty invoiceHeaderCompleted}">
									<span style="margin-left: .5in;"><b>No data to
											display.</b></span>
								</c:if>
							</p>
						</div>
						<div id="tab3" class="tab">
							<p>
								<c:if test="${not empty invoiceHeaderCancelled}">
									<table class="tg">
										<%@include file="./delivTableHeader.jsp"%>
										<c:forEach var="invHeader" items="${invoiceHeaderCancelled}">
											<%@include file="./invTableBodyRows.jsp"%>
										</c:forEach>
									</table>
								</c:if>
								<c:if test="${empty invoiceHeaderCancelled}">
									<span style="margin-left: .5in;"><b>No data to
											display.</b></span>
								</c:if>
							</p>
						</div>
					</div>
					<div class="clr"></div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="./footer.jsp"%>
</div>

</body>
</html>
