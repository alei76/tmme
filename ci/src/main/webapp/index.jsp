<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>CI Engine</title>
</head>

<body>

  <div>
  	<h1>CI Engine</h1>
    <authz:authorize ifNotGranted="ROLE_USER">
      <h2>Login</h2>
      <form id="loginForm" name="loginForm" action="<c:url value="/login.do"/>" method="post">
        <p><label>Username: <input type='text' name='j_username'></label></p>
        <p><label>Password: <input type='password' name='j_password'></label></p>
        <p><input name="login" value="Login" type="submit"></p>
      </form>
    </authz:authorize>
    <authz:authorize ifAllGranted="ROLE_USER">
      <p>You have successfully logged-in</p>
      <div><form action="<c:url value="/logout.do"/>"><input type="submit" value="Logout"></form></div>
      
	<form action="<c:url value="/connect/facebook" />" method="post">
		<input type="hidden" name="scope" value="publish_stream,user_photos,offline_access" />
		<div><p>You aren't connected to Facebook yet</p></div>
		<p><button type="submit">Facebook</button></p>
	</form>
	      
    </authz:authorize>
  </div>

</body>
</html>
