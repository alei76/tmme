<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
  <title>CI Engine</title>
  <link type="text/css" rel="stylesheet" href="<c:url value="/style.css"/>"/>
</head>

<body>

  <div id="content">
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
      <div style="text-align: center"><form action="<c:url value="/logout.do"/>"><input type="submit" value="Logout"></form></div>
    </authz:authorize>
  </div>

</body>
</html>
