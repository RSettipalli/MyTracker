<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MyTracking</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="./js/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript" src="./js/jquery.validate.min.js"></script>
<script type="text/javascript" src="./js/jquery-ui.min.js"></script>
<script type="text/javascript" src="./js/additional-methods.js"></script>
<script type="text/javascript" src="./js/arial.js"></script>
<script type="text/javascript" src="./js/cufon-yui.js"></script>
<script type="text/javascript" src="./js/cuf_run.js"></script>
<script type="text/javascript" src="./js/scripts.js"></script>
<script type="text/javascript" src="./js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="./js/jquery.tablesorter.pager.js"></script>
<link href="./css/style.css" rel="stylesheet" type="text/css" />
<link href="./css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script>
	/* $(document).ready(function() {
		$("table") 
		.tablesorter({widthFixed: true, widgets: ['zebra']}) 
		.tablesorterPager({container: $("#pager")}); 
	}); */ 
	function toggle_visibility(id) {
		var e = document.getElementById(id);
		if (e.style.display == 'block')
			e.style.display = 'none';
		else
			e.style.display = 'block';
	}
	function redirectToDetail(orderNumber){
		$.ajax({
			method: "GET",
			url: "./SalesOrderDetail.htm",
			data: {'OrderID': orderNumber},
			error: function(e){
				console.log(e.message);
			}
		});
	}
	function login() {
		document.loginForm.submit();
	}
	function forgotSubmit() {
		document.forgotForm.submit();
	}
	function register() {
		document.registerForm.submit();
	}	
</script>

<!-- Menu control -->
<script type="text/javascript">
	window.onload = function(){
		var menuToActivate = '${currPage}';
		if(menuToActivate == "")
			menuToActivate = 'liLogin';ShowHideLoginOption(menuToActivate);
		activateMenuOption(menuToActivate);
/* 
		if(menuToActivate == "liSignUp"){
			validateSignUpFields();
		} */
	};
	
	function activateMenuOption(menuToActivate){
		document.getElementById(menuToActivate).className = 'active';
	}
	
	function ShowHideLoginOption(menuToActivate){
		var ancestor = document.getElementById('ulMenu');
	    var descendents = ancestor.getElementsByTagName('li');
		if((menuToActivate == 'liLogin')|| (menuToActivate == 'liSignUp')){
			for(i = 2; i < descendents.length; i++){
				descendents[i].setAttribute('class', 'hide');
			}
		} else{
			descendents[0].setAttribute('class', 'hide');
			descendents[1].setAttribute('class', 'hide');
			for(i = 2; i < descendents.length; i++){
				descendents[i].removeAttribute('class');
			}
		}
	}
</script>

<!-- Material Page control -->
<script type="text/javascript">
function onChange() {
		var e = document.getElementById("materialIds");
		var selVal = e.options[e.selectedIndex].value;
		if(selVal != '-1')
			$('#btnGo').prop('disabled', false);
		else
			$('#btnGo').prop('disabled', true);
}
</script>

<!-- Tabs control -->
<script type="text/javascript">
	jQuery(document).ready(
			function() {
				jQuery('.tabs .tab-links a').on(
						'click',
						function(e) {
							var currentAttrValue = jQuery(this).attr('href');

							// Show/Hide Tabs
							jQuery(currentAttrValue).show().siblings().hide();

							// Change/remove current tab to active
							jQuery(this).parent('li').addClass('active')
									.siblings().removeClass('active');

							e.preventDefault();
						});
			});
</script>
</head>
<body>
	<div id="mainHeaderDiv" class="header main">
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
					<a href="http://mygoconsulting.com/contact"><img
						src="./images/contactusimage_sm.jpg" width="50px" border="0" /></a> 
					<a href="./signUp.htm"><img src="./images/Registeration_sm.png"
						width="50px" border="0" /></a>
				</div>
				<div class="clr"></div>
				<div class="menu_nav">
					<ul id="ulMenu">
						<li id="liLogin"><a href="./login.htm">Login</a></li>
						<li id="liSignUp"><a href="./signUp.htm">Sign Up</a></li>
						<li id="liHome" class=""><a href="./Home.htm">Home</a></li>
						<li id="liOrderInfo"><a href='./OrderInfo.htm'><span>Delivery Info</span></a></li>
						<li id="liInvoice"><a href='./Invoice.htm'><span>Invoice Info</span></a></li>
						<li id="liShipment"><a href='./Shipment.htm'><span>Sales Order Info</span></a></li>
						<li id="liMaterial"><a href='./Material.htm'><span>Material Info</span></a></li>
						<li id="liLogout"><a href="./logout.htm">Logout</a></li>
					</ul>
				</div>
				<div class="clr"></div>
			</div>
		</div>
</body>
</html>