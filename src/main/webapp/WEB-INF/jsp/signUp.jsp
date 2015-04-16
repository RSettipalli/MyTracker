<%@include file="./header.jsp"%>
<script type="text/javascript">
$(function(){
	$('#register').prop("disabled",true);
	$('.signUpInput').change(function(){
		validateEmail();
		var firstName = $('#fname').val();
		var lastName = $('#lname').val();
		var Email = $('#email').val();
		var passWord = $('#password').val();
		var selected = $("input[type='radio'][name='UserType']:checked").val();
		var custVal = $('#kunnrList').val();
		var compVal = $('#bukrsList').val();
		var compID;
		if(selected == "COMPANY")
			compID = compVal;
		else
			compID = custVal;
		if((firstName == "") ||
				(lastName == "") ||
				(Email == "") ||
				(passWord == "") ||
				(compID == '-1')){
			$('#register').prop("disabled",true);
		} else {
			$('#register').prop("disabled",false);
		}
	});
});

function onUserTypeChange(){
	$('#kunnrList').val('-1');
	$('#bukrsList').val('-1');
}

function onPasswordChange(){
	if(!validatePassword())
		$('#password').val("");
}

function validateEmail(){
	var emailExpr= /[A-Z0-9._%+-]+@(?:[A-Z0-9-]+\.)+[A-Z]{2,4}/i;
	var email = $('#email').val();
	var result = emailExpr.test(email);
	if(!result){
		$('#email').val("");
	}
}

function validatePassword(){
	var passWord = $('#password').val();
	$('#errMsg').html("");
	if (passWord.length < 6) {
        $('#errMsg').html("Password needs to be at least 6 characters.");
        return false;
    } else if (passWord.length > 16) {
    	$('#errMsg').html("Password needs to be no longer than 16 characters.");
        return false;
    } else if (passWord.search(/\d/) == -1) {
    	$('#errMsg').html("Password needs to contain at least 1 number.");
        return false;
    } else if (passWord.search(/[a-zA-Z]/) == -1) {
    	$('#errMsg').html("Password needs to contain at least 1 letter.");
        return false;
    } else if (passWord.search(/[a-z]/) == -1) {
    	$('#errMsg').html("Password needs to contain at least 1 lower case letter.");
        return false;
    } else if (passWord.search(/[A-Z]/) == -1) {
    	$('#errMsg').html("Password needs to contain at least 1 upper case letter.");
        return false;
    } else if (passWord.search(/[_\W]/) == -1) {
    	$('#errMsg').html("Password needs to contain at least 1 special character.");
        return false;
    }  else if (passWord.search(/[^a-zA-Z0-9\!\@\#\$\%\^\&\*\(\)\_\+]/) != -1) {
    	$('#errMsg').html("Password contains a bad character.");
        return false;
    }
	return true;
}
</script>
<div>
	<div class="content">
		<div class="content_resize">
			<div class="clr"></div>
			<div class="article">
				<div class="register">
					<h2>
						<span>Register</span>
					</h2>
					<div class="clr"></div>
					<form:form name="registerForm" method="POST" commandName="user"
						action="./signUp.htm" id="registerForm">
						<input style="display:none" type="email" name="fakeusernameremembered"/>
						<input style="display:none" type="password" name="fakepasswordremembered"/>
						<table>
							<tr>
								<td>
									<form:input path="fname" value="" placeholder="First Name" type="firstname" class="signUpInput"/>
								</td>
								<td>
									<form:input path="lname" value="" placeholder="Last Name" type="lastname" class="signUpInput"/>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<form:input path="email" value="" placeholder="Email" type="email"
									 style="width:99%" class="signUpInput"/>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<form:password path="password" value="" placeholder="Password" showPassword="true"
									   style="width:99%" onchange="onPasswordChange()" class="signUpInput"/> <!--  -->
								</td>
							</tr>
							<tr>
								<td>
									<form:radiobuttons path="UserType" items="${AllUserTypes}"
										onclick="showHideSelects()" onchange="onUserTypeChange()"/>
								</td>
								<td>
									<form:select id="bukrsList" multiple="single" path="companyId" style="width:100%" class="signUpInput">
										<form:option value="-1" selected="selected">Select Company ID</form:option>
										<form:options items="${user.BUKRSList}" />
									</form:select> 
									<form:select id="kunnrList" multiple="single" path="customerId" class="hide signUpInput"
										style="width:100%">
										<form:option value="-1" selected="selected">Select Customer ID</form:option>
										<form:options items="${user.KUNNRList}" />
									</form:select>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<input class="button" type="submit" value="Sign Up"
										onclick="register()" style="width:100%" id="register"/>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									${message}
									<label id="errMsg" style="width:99%"></label>
								</td>
							</tr>
						</table>
						<div class="clr">
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="./footer.jsp"%>
</div>
</body>
</html>