<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
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

	function login() {
		  document.loginForm.submit();
	}
	</script>
		
</head>
<body>
<h1 style="color:red">Mygo My Portal</h1>
	<div id="wrapper">
		<div id="wrappertop"></div>
		<form:form method="POST" id="loginForm" commandName="loginForm" action="./login.htm">
		<div id="wrappermiddle">
			<h2>Login</h2>
			
			<div id="username_input">
				${message}
				<div id="username_inputleft"></div>
				<div id="username_inputmiddle">
					<form:input id="url" path="email" value="" />
					<img id="url_user" src="./images/mailicon.png" alt="">
				</div>
				<div id="username_inputright"></div>
			</div>

			<div id="password_input">
				<div id="password_inputleft"></div>
				<div id="password_inputmiddle">
				<form:password id="url" path="password"  showPassword="true"/>
				<img id="url_password" src="./images/passicon.png" alt="">
				</div>
				<div id="password_inputright"></div>
			</div>

			<div id="submit">
				<input type="image" src="./images/submit_hover.png" id="submit1" value="Sign In" onclick="login()"/>
				<input type="image" src="./images/submit.png" id="submit2" value="Sign In" onclick="login()"/>
			</div>


			<div id="links_left">

			<a href="./forgot.htm">Forgot your Password?</a>

			</div>

			<div id="links_right"><a href="./signUp.htm">Not a Member Yet?</a></div>
		
		</div>
		
		<div id="wrapperbottom"></div>
	</form:form>
	</div>

</body>
</html>