<%@include file="./header.jsp"%>

<div>
	<div class="content">
		<div class="content_resize">
			<div class="clr"></div>
			<div>
				<div class="article">
					<h2>
						<span>Sales Order Info</span>
					</h2>

					<div class="tabs" style="float:left;">
						<ul class="tab-links">
							<li class="active"><a href="#tab1">Open</a></li>
							<li><a href="#tab2">Completed</a></li>
							<li><a href="#tab3">Cancelled</a></li>
						</ul>
					</div>

					<div class="tab-content">
						<div id="tab1" class="tab active">
							<p>
								<c:if test="${not empty salesOrderHeaderOpen}">
									<table id="tableOpenSOHeader" class="tg">
										<%@include file="./soTableHeader.jsp"%>
										<tbody>
											<c:forEach var="soHeader" items="${salesOrderHeaderOpen}">
												<%@include file="./soTableBodyRows.jsp"%>
											</c:forEach>
										</tbody>
									</table>
								</c:if>
								<c:if test="${empty salesOrderHeaderOpen}">
									<span style="margin-left: .5in;"><b>No data to display.</b></span>
								</c:if>
							</p>
						</div>
						<div id="tab2" class="tab article">
							<p>
								<c:if test="${not empty salesOrderHeaderClosed}">
									<table class="tg">
										<%@include file="./soTableHeader.jsp"%>
										<c:forEach var="soHeader" items="${salesOrderHeaderClosed}">
											<%@include file="./soTableBodyRows.jsp"%>
										</c:forEach>
									</table>
								</c:if>
								<c:if test="${empty salesOrderHeaderClosed}">
									<span style="margin-left: .5in;"><b>No data to display.</b></span>
								</c:if>
							</p>
						</div>
						<div id="tab3" class="tab article">
							<p>
								<c:if test="${not empty salesOrderHeaderCancelled}">
									<table class="tg">
										<%@include file="./soTableHeader.jsp"%>
										<c:forEach var="soHeader" items="${salesOrderHeaderCancelled}">
											<%@include file="./soTableBodyRows.jsp"%>
										</c:forEach>
									</table>
								</c:if>
								<c:if test="${empty salesOrderHeaderCancelled}">
									<span style="margin-left: .5in;"><b>No data to display.</b></span>
								</c:if>
							</p>
						</div>
					</div>
					<!--  expand collapse : end -->
					<div class="clr"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="./footer.jsp"%>
<!-- </body>
</html> -->