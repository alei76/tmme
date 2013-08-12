<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta charset="utf-8">
	<title>Social</title>
	<link href="ui/css/flat-ui.css" rel="stylesheet">
	<link href="ui/images/favicon.ico" rel="shortcut icon">
</head>

<body>

	    <authz:authorize ifNotGranted="ROLE_USER">
		    <p>Need to login to link to your social accounts</p>
		</authz:authorize>
		
		
		<authz:authorize ifAllGranted="ROLE_USER">
			<form action="<c:url value="${request.contextPath}/social/connect/facebook" />" method="post">
				<input type="hidden" name="scope" value="offline_access,user_activities,user_likes,user_interests,user_location,user_status" />
				<p><button type="submit">Connect to Facebook</button></p>
			</form>
			<form action="<c:url value="${request.contextPath}/social/connect/facebook" />" method="post">
				<button type="submit">Disconnect from Facebook</button>	
				<input type="hidden" name="_method" value="delete" />
			</form>
		</authz:authorize>
	
</body>

<html>	