<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
	<meta charset="utf-8">
	<title>Home</title>
	<link href="ui/images/favicon.ico" rel="shortcut icon">
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
    <link href="ui/css/custom-style.css" rel="stylesheet">
</head>

<body>

<form class="form-signin" method="post" action="<c:url value="/login.do"/>">

    <div id="myModal" class="modal modal-admin hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3 id="myModalLabel">Log in</h3>
        </div>
        <div class="modal-body text-center">
            <div class="control-group">
                <div class="controls">
                    <div class="input-prepend">
                        <span class="add-on"><i class="icon-envelope-alt"></i></span>
                        <input class="span3" id="appendedPrependedInput" name='j_username' type="email" placeholder="Email">
                    </div>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <div class="input-prepend input-append">
                        <span class="add-on"><i class="icon-key"></i></span>
                        <input class="span3" type="password" id="inputPassword" name="j_password" placeholder="Password">
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <a href="#" class="pull-left">Register</a>
            <a href="#" class="btn" data-dismiss="modal">Cancel</a>
            <button type="submit" class="btn btn-primary"><i class="icon-arrow-right"></i> Go!</button>
        </div>
    </div>
</form>

<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <a class="brand" href="#">Collective Intelligence Sample</a>
        <ul class="nav">
            <li class="active"><a href="${request.contextPath}">Home</a></li>
            <li><a href="#">Login/Register</a></li>
            <li><a href="#">Connect to Social Networks</a></li>
        </ul>
    </div>
    <div class="hero">
        <h1 class="huge text-shadow text-center">Collective Intelligence</h1>
        <h2 class="huge text-shadow text-center">dummy ui for demo purposes !!!</h2>
        <div class="text-center">
            <a href="#myModal" role="button" class="btn btn-success center btn-large" data-toggle="modal">Log in</a>
        </div>
    </div>

</div>

<div id="footer">
	<p class="legal footer">
		Copyright(c) 2013
		<small>Nacho Cano. All rights reserved.</small>
	</p>
</div>





</body>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="ui/js/bootstrap.js"></script>

</html>
