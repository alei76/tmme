<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
	<meta charset="utf-8">
	<title>Log in</title>
	<link href="ui/css/flat-ui.css" rel="stylesheet">
	<link href="ui/images/favicon.ico" rel="shortcut icon">
</head>

<body>

	<div>
		<c:if test="${not empty param.authentication_error}">
			<p>Your login attempt was not successful.</p>
		</c:if>
		<c:if test="${not empty param.authorization_error}">
			<p>You are not permitted to access that resource.</p>
		</c:if>
		
	    <authz:authorize ifNotGranted="ROLE_USER">
		    <form method="post" action="<c:url value="/login.do"/>">
				<div class="login-form">
					<div class="control-group">
						<input type="text" class="login-field" value="" placeholder="Email" name='j_username' id="login-email" />
						<label class="login-field-icon fui-mail" for="login-email"></label>
					</div>
					<div class="control-group">
						<input type="password" class="login-field" value="" placeholder="Password" name="j_password" id="login-pass" />
						<label class="login-field-icon fui-lock" for="login-pass"></label>
					</div>
					<input type="submit" value="Log in" class="btn btn-primary btn-large btn-block" href="#"/>
				</div>
			</form>
	    
	    </authz:authorize>
	    
	    <authz:authorize ifAllGranted="ROLE_USER">
	      <p>You have successfully logged-in</p>
	      <div><form action="<c:url value="/logout.do"/>"><input type="submit" value="Logout"></form></div>
	    </authz:authorize>		
	</div>

</body>

</html>