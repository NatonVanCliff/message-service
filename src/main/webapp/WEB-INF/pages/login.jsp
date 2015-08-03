<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="src/main/webapp/resources/css/style.css"/>
	<title>Log In</title>
	<link type="text/css" href="resources/css/style.css" rel="stylesheet"/>
	<link type="text/css" href="resources/css/bootstrap.min.css" rel="stylesheet"/>
	<link type="text/css" href="resources/css/bootstrap-theme.min.css" rel="stylesheet"/>
</head>
<body class="body">

<div class="container">
	<%--main panel--%>
	<div class="panel panel-default loginPanel">
		<div clas="panel-body">
			<div class="loginForm">
				<form method="POST" action="<c:url value='/j_spring_security_check'/>">
					<div class="form-group" >
						<input class="form-control" type="text" placeholder="Login" name='username'>
					</div>
					<div class="form-group" >
						<input class="form-control" type="password" placeholder="Password" name='password'>
					</div>
					<div class="form-group" >
						<button class="btn btn-default loginButton" type="submit" name='submit'>Log In</button>
					</div>
					<c:url var="registration" value='/registration'/>
					<a href="${registration}">Sign Up</a>
					<div class="errorMessage">${message}</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>




