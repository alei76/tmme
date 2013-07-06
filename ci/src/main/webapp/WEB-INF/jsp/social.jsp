<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
<title>CI Engine</title>
</head>

<body>

	<form action="<c:url value="${request.contextPath}/social/facebook" />" method="post">
		<input type="hidden" name="scope" value="offline_access,user_activities,user_likes,user_interests,user_location,user_status" />
		<p><button type="submit">Connect to Facebook</button></p>
	</form>
	
	<form action="<c:url value="${request.contextPath}/social/facebook" />" method="post">
		<button type="submit">Disconnect from Facebook</button>	
		<input type="hidden" name="_method" value="delete" />
	</form>
	
	<a href="<c:url value="/profile"/>">FacebookProfile</a>
	
</body>

<html>	