<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>

<head>
	<meta charset="utf-8">
	<title>Register</title>
	<link href="ui/css/flat-ui.css" rel="stylesheet">
</head>

<body>

<form:form action="register" method="post" commandName="registrationForm">
	<div class="login-form">
		<input type="hidden" name="submitted" value="true">
		<div class="control-group">
			<form:input path="email" type="text" class="login-field" value="" placeholder="Email" name="email" id="register-email" />
			<label class="login-field-icon fui-mail" for="register-email"></label>
		</div>
		<div class="control-group">
			<form:input path="password" type="password" class="login-field" placeholder="Password" name="password1" id="register-pwd" />
			<label class="login-field-icon fui-lock" for="register-pwd"></label>
		</div>
		<div class="control-group">
			<form:input path="confirmPassword" type="password" class="login-field" placeholder="Confirm Password" name="password2" id="register-pwd2" />
			<label class="login-field-icon fui-lock" for="register-pwd2"></label>
		</div>
		<input type="submit" value="Sign me up!" class="btn btn-success btn-large btn-block">
	</div>
</form:form>

</body>

</html>