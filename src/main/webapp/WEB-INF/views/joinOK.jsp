<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ page session="false"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>Home</title>
<!-- <link rel="stylesheet"
	href="./css/bootstrap.min.css">
<script
	src="./jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="./js/bootstrap.min.js"></script> -->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</head>

<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark navbar-fixed-top">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link" href="#">CNS</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a>
				</li>
			</ul>
		</nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h1>C.N.S</h1>
			<p>Thank you for comming!  ;)
			This is CODEPRESSO Social Network Service. CNS
			freely make a post if you are in dev-ops course.
			I hope you injoy~
			</p>
			<p>
				<a class="btn btn-primary btn-lg" href="#" role="button">Learn
					more &raquo;</a>
			</p>
			<!-- 내가 넣은 코드 -->
			<h3>Login</h3>
			<h5>insert your ID/PWD</h5>
			<form action="http://localhost:8080/pro01/login" method="get">
				testValue : <br> <input type="text" name="testValue" value="${testV}"><br>
				id : <br> <input type="text" name="id" value="${id}"><br>
				username : <br> <input type="text" name="username" value="${membervo.username}"><br>
				password : <br> <input type="text" name="password" value="${password}"><br>
				<!--  : <br> <input type="text" name="department" value="컴퓨터공학과"><br> -->
				<!-- <br> <input class="btn btn-primary btn-sm" type="submit" value="LOGIN"> -->
			</form>

		</div>
	</div>

	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="../../dist/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
