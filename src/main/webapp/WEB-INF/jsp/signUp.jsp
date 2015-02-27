<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>My Tracker Registration Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/cufon-yui.js"></script>
<script type="text/javascript" src="./js/arial.js"></script>
<script type="text/javascript" src="./js/cuf_run.js"></script>
<script type="text/javascript">
	function register() {
		  document.registerForm.submit();
	}
</script>
</head>
<body>
<div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="logo"><img src="./images/mygologo_smallsize.jpg"/></div>
      <div class="logoTitle"><h1>My Tracker</h1></div>
      <div class="logoSmallTitle"><a href="#"><img src="./images/mygologo_icon_sm.jpg" width = "50px" border="0"/></a>
       <a href="#"><img src="./images/contactusimage_sm.jpg" width = "50px" border="0"/></a>
       <a href="./signUp.htm"><img src="./images/Registeration_sm.png" width = "50px" border="0"/></a>
      </div>
      <div class="clr"></div>
      <div class="menu_nav">
      <ul>
       	<li><a href="./login.htm">Login</a></li>
      </ul>
      </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="content">
    <div class="content_resize">
      <div class="clr"></div>
      <div class="mainbar">
        <div class="article">
          <h2><span>Register</span></h2>
          <div class="clr"></div>
 		<form:form name="registerForm" class="foorm" method="POST" commandName="user" action="./signUp.htm">
 		   <div class="clr">${message}</div>
        	<p>
          <label>First Name</label>
          <form:input path="fname"  value="" /><br />
          <label>Last Name</label>
          <form:input path="lname" value="" /><br />
          <label>Email</label>
          <form:input path="email"  value="" /><br />
		  <label>Password</label>
		  <form:password path="password" showPassword="true"/><br />
          <label>Company/Consumer Id</label>
          <form:input path="userId"  value="" /><br />
          <br />
          <input class="button" type="submit" value="Sign In" onclick="register()"/>
        </p>
      </form:form>
        </div>
      </div>
     
  </div>
  </div>
  </div>
  </body>

</html>
