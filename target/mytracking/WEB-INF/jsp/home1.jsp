<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>


	<!-- General meta information -->
	
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta name="robots" content="index, follow" />
	<meta charset="utf-8" />
	<!-- // General meta information -->
	<title>Mygo My Portal</title>
	
	<!-- Load Javascript -->
	<script type="text/javascript" src="./js/jquery.js"></script>
	<script type="text/javascript" src="./js/jquery.query-2.1.7.js"></script>
	<script type="text/javascript" src="./js/rainbows.js"></script>
	<!-- // Load Javascipt -->

	<!-- Load stylesheets -->
	<link type="text/css" rel="stylesheet" href="./css/style.css" media="screen" />
	<link type="text/css" rel="stylesheet" href="./css/styles.css" media="screen" />
	<!-- // Load stylesheets -->
	
<script>


	$(document).ready(function(){
 
	$("#submit1").hover(
	function() {
	$(this).animate({"opacity": "0"}, "slow");
	},
	function() {
	$(this).animate({"opacity": "1"}, "slow");
	});
 	});


</script>
	
</head>
<body>
<div id='cssmenu'>
<ul>
   <li class='active'><a href='#'><span>Home</span></a></li>
   <li><a href='../mytracking/orderInfo/'><span>Order Report</span></a></li>
   <li><a href='#'><span>Invoice Report</span></a></li>
   <li><a href='#'><span>Shipment Report</span></a></li>
   <li><a href='#'><span>Product Info</span></a></li>
   <li class='last'><a href='#'><span>Profile</span></a></li>
   
   
</ul>
</div>
<div id="mainDiv"> &nbsp;&nbsp;&nbsp;&nbsp; ${sessionScope.user.fname} 

<jsp:include page="company.jsp" /> 

<div id="leftNavBar" align="left"  name="leftNavBar" style="border:solid">
I am Left
</div>

<jsp:include page="orderdetail.jsp" /> 

<div id="footer" align="justify" name="footer" style="border:solid">
I am footer
</div>
</div>
</body>
</html>