<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
	<meta charset="utf-8">
	<title>Home</title>
	<link href="ui/css/flat-ui.css" rel="stylesheet">
	<link href="ui/images/favicon.ico" rel="shortcut icon">
</head>

<body>

<div class="navbar-inner">
		<div class="nav-container">
			<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li><a href="register">Register</a></li>
					<li><a href="login">Log in</a></li>
					<authz:authorize ifAllGranted="ROLE_USER">
						<li><a href="social/connect">Social</a></li>
					</authz:authorize>
				</ul>
			</div>
		</div>
</div>

<div class="home">

	<h1 class="huge text-shadow text-center">Collective Intelligence</h1>
	<h2 class="huge text-shadow text-center">dummy ui for demo purposes !!!</h2>

	<a href="register" class="btn btn-huge btn-danger mbs">Register</a>
	<a href="login" class="btn btn-huge btn-danger mbs">Log in</a>

</div>

<div id="footer">
	<p class="legal">
		Copyright © 2013
		<small><p>Nacho Cano. All rights reserved.</p></small>
	</p>
</div>

</body>

</html>
