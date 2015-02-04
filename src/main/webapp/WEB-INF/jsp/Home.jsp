<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Home</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/cufon-yui.js"></script>
<script type="text/javascript" src="./js/arial.js"></script>
<script type="text/javascript" src="./js/cuf_run.js"></script>
</head>
<body>
<div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="logo"><img src="./images/mygologo_smallsize.jpg"/></div>
      <div class="logoTitle"><h1>My Tracker</h1></div>
      <div class="logoSmallTitle"><a href="#"><img src="./images/mygologo_icon.jpg" width="50" border="0"/></a><a href="#"><img src="./images/contactusimage.jpg" width="50" border="0"/></a><a href="#"><img src="./images/Registeration.png" width="50" border="0"/></a></div>
      <div class="clr"></div>
      <div class="menu_nav">
      	<ul>
      	<li class="active"><a href="../Home.htm">Home</a></li>
      	<li><a href='../mytracking/orderInfo/'><span>Order Report</span></a></li>
      	<li><a href='#'><span>Invoice Report</span></a></li>
      	<li><a href='#'><span>Shipment Report</span></a></li>
      	<li><a href='#'><span>Product Info</span></a></li>
      	<li><a href='#'><span>Profile</span></a></li>
      	<li class='last'><a href="Resources.jsp">Resources</a></li>
      	</ul>
      </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="content">
  	<div class="content_resize"><!--<center> <img src="./images/mygologo_smallsize.jpg" alt="" class="hbg_img" /> </center>-->
      <div class="clr"></div>
      <div class="mainbar">
      <div class="article">
      <h2><span>Welcome ${sessionScope.user.fname}</span></h2>
      <div class="clr"></div>
      </div><p class="pages"><small>Page 1 of 2 &nbsp;&nbsp;&nbsp;</small> <span>1</span> <a href="#">2</a> <a href="#">&raquo;</a></p></div>
      <div class="sidebar">
        <div class="searchform">
          <form id="formsearch" name="formsearch" method="post" action="#">
            <span>
            <input name="editbox_search" class="editbox_search" maxlength="80" value="" type="text" />
            </span>
            <input name="button_search" src="./images/search_btn.gif" type="text" class="button_search" type="image" />
          </form>
        </div>
        <div class="clr"></div>
        <div class="gadget">
          <h2 class="star"><span>Sidebar</span> Menu</h2>
          <div class="clr"></div>
          <ul class="sb_menu">
            <li><a href="../Home.htm">Home</a></li>
            <li><a href="#">Shipping</a></li>
            <li><a href="#">Orders</a></li>
            <li><a href="#">Tracker</a></li>
            <li><a href="#">Resources</a></li>
          </ul>
        </div>
      <div class="clr"></div>
    </div>
  </div>
  </div>
  </div>
  </body>
</html>
