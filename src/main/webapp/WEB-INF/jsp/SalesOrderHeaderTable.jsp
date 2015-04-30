<table class="tg">
	<tr>
		<th class="tg-kkr7">Order Number</th>
		<th class="tg-t8h0">Type</th>
		<th class="tg-t8h0">Status</th>
		<th class="tg-t8h0">Sold From</th>
		<th class="tg-t8h0">Ship To</th>
		<th class="tg-t8h0">Bill To</th>
		<th class="tg-t8h0">End User</th>
		<th class="tg-t8h0">Company Name</th>
		<th class="tg-t8h0">Customer PO</th>
		<th class="tg-t8h0">Total Price</th>
		<th class="tg-t8h0">Currency</th>
		<th class="tg-t8h0">Create Date</th>
		<th class="tg-t8h0">Address1</th>
		<th class="tg-t8h0">Address2</th>
		<th class="tg-t8h0">City</th>
		<th class="tg-t8h0">State</th>
		<th class="tg-qta8">Zip</th>
	</tr>
	<c:forEach var="soHeader" items="${salesOrderHeader}">
		<tr>
			<td class="tg-031e"><a href="#"
				onclick="toggle_visibility('${soHeader.ORDER_NBR}')">${soHeader.ORDER_NBR}</a>
			</td>
			<td class="tg-031e">${soHeader.ORDER_TYPE}</td>
			<td class="tg-031e">${soHeader.ORDER_STATUS_CD}</td>
			<td class="tg-031e">${soHeader.SOLD_FROM_COMPANY_CD}</td>
			<td class="tg-031e">${soHeader.SHIP_TO_COMPANY_CD}</td>
			<td class="tg-031e">${soHeader.BILL_TO_COMPANY_CD}</td>
			<td class="tg-031e">${soHeader.END_USER}</td>
			<td class="tg-031e">${soHeader.OVERRIDE_COMPANY_NAME}</td>
			<td class="tg-031e">${soHeader.CUSTOMER_PO}</td>
			<td class="tg-031e">${soHeader.TOTAL_PRICE}</td>
			<td class="tg-031e">${soHeader.CURRENCY}</td>
			<td class="tg-031e">${soHeader.CREATE_DATE}</td>
			<td class="tg-031e">${soHeader.OVERRIDE_ADDRESS1}</td>
			<td class="tg-031e">${soHeader.OVERRIDE_ADDRESS2}</td>
			<td class="tg-031e">${soHeader.OVERRIDE_CITY}</td>
			<td class="tg-031e">${soHeader.OVERRIDE_STATE}</td>
			<td class="tg-031e">${soHeader.OVERRIDE_ZIP}</td>
		</tr>
	</c:forEach>
</table>