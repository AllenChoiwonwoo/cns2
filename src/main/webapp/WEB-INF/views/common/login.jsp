<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored="false" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <!-- jstl을 위한 설정 -->
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="/">Code Presso SNS</a>
		<#if user??>
		<div class="row">
			<button class="btn btn-secondary mx-3" id="create_btn" data-toggle="modal"
			data-target="#create_post_modal">Create Post</button>
			<button class="btn" id="header_logout_btn">Logout</button>
		</div>
		<#else>
		<div class="row">
			<a href="/signup"><button class="btn btn-secondary mr-3" id="header_signup_btn">Sign Up</button></a>
			<a href="/login"><button class="btn btn-secondary" id="header_login_btn">Login</button></a>
		</div>
		</#if>
	</div>
</nav>

</body>
</html>