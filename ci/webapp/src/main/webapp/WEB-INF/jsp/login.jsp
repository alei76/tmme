<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title>CI Sample Login Page</title>

    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet" />
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet" />
    <link href="ui/css/custom-style.css" rel="stylesheet" />


</head>
<body>
<div class="container">
    <a href="#myModal" role="button" class="btn" data-toggle="modal">Launch demo modal</a>




</div>
</body>
</html>

<%--

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

</html>--%>
