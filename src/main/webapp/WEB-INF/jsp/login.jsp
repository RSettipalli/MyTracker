<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MyTracking Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/arial.js"></script>
<script type="text/javascript" src="./js/cuf_run.js"></script>

<script>
function login() {
	  document.loginForm.submit();
}
</script>
</head>
<body>
<div class="main">
		<div class="header">
			<div class="header_resize">
				<div class="logo">
					<img src="./images/mygologo_smallsize.jpg" />
				</div>
				<div class="logoTitle">
					<h1>
						MyTracking<sup>&reg;</sup>
					</h1>
				</div>
				<div class="logoSmallTitle">
					<a href="#"><img src="./images/contactusimage_sm.jpg"
						width="50px" border="0" /></a> <a href="./signUp.htm"><img
						src="./images/Registeration_sm.png" width="50px" border="0" /></a>
				</div>
				<div class="clr"></div>
				<div class="menu_nav">      </div>
      <div class="clr"></div>

    </div>
  </div>
  <div class="content">
    <div class="content_resize"><center> <img src="./images/mygologo_smallsize.jpg" alt="" class="hbg_img" /> </center>
      <div class="clr"></div>
      <div class="mainbar">
        <div class="article">
          <h2><span>Login</span></h2>
          <div class="clr"></div>
		<center>
		 <form:form method="POST" class="loginform" commandName="loginForm" action="./login.htm">
		 	${message}
        	<p>
          	<label>User Id</label>
          	<form:input path="email" value="" /><br />
          	<label>Password</label>
          	<form:password path="password" showPassword="true"/><br/><br/>
          	<input class="button" type="submit" value="Submit" onclick="login()"/>&nbsp;&nbsp;&nbsp;&nbsp;		  	
		  	<a href="./forgot.htm">Forgot Password</a>
		  	</p>
      	</form:form>
		</center>
        </div>
      </div>  
  	</div>
  	</div>
  </div>
</body>
</html>