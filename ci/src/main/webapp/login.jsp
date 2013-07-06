<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<title>CI Engine</title>
</head>

<body>

	<div>
		<h1>CI Engine</h1>
		<c:if test="${not empty param.authentication_error}">
			<p>Your login attempt was not successful.</p>
		</c:if>
		<c:if test="${not empty param.authorization_error}">
			<p>You are not permitted to access that resource.</p>
		</c:if>
		
	    <authz:authorize ifNotGranted="ROLE_USER">
	      <h2>Login</h2>
	      <form id="loginForm" name="loginForm" action="<c:url value="/login.do"/>" method="post">
	        <p><label>Username: <input type='text' name='j_username'></label></p>
	        <p><label>Password: <input type='password' name='j_password'></label></p>
	        <p><input name="login" value="Login" type="submit"></p>
	      </form>
	      
	      <a href="<c:url value="/user/register"/>">Register</a>
	      
	    </authz:authorize>
	    <authz:authorize ifAllGranted="ROLE_USER">
	      <p>You have successfully logged-in</p>
	      <div><form action="<c:url value="/logout.do"/>"><input type="submit" value="Logout"></form></div>
	      
	      <a href="<c:url value="/social"/>">Connect To Social Networks</a>
	    </authz:authorize>		
	</div>

</body>

</html>