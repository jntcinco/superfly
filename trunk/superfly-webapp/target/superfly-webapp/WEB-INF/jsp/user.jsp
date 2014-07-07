<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="description" content="Official website of RA Superfly Gamefarm - Atty. Ryan Abrenica">
		<meta name="keywords" content="Gamefarm,Gamefowl,Atty. Ryan Abrenica">
		<meta name="robots" content="index,follow">
		<meta name="DC.title" content="Atty. Ryan Abrenica">
		<title>RA Superfly Gamefarm</title>
		<link rel="stylesheet" type="text/css" href="<c:url value='/styles/superfly.css'/>" />
	</head>
	<body>
	<div class="main-container">
		<div class="header-container"></div>
		<div class="nav-wrapper">
			<ul id="menu">
				<li><a href="<c:url value='/tekusource/user'/>">About</a></li>
				<li><a href="<c:url value='/tekusource/file/video'/>">Videos</a></li>
				<li><a href="<c:url value='/tekusource/file/image'/>">Bloodlines</a></li>
				<!-- <li>
						<a href="#">Bloodlines</a>
						<ul>
							<li><a href="#">Lemons</a></li>
							<li><a href="#">Gilmores</a></li>
							<li><a href="#">Roundheads</a></li>
							<li><a href="#">Kelso</a></li>
							<li><a href="#">Sweater</a></li>
						</ul>
				</li> -->
				<li><a href="<c:url value='/tekusource/salePackagePrice'/>">Price List</a></li>
					<li><a href="<c:url value='/tekusource/awards'/>">Awards</a></li>
				<li><a href="#">Register</a></li>
				<li><a href="#">Contact Us</a></li>
			</ul>
		</div>
		<div id="content-body">
				<div class="content-left">
					<div id="superfly-login">
						<div class="space-break"></div>
						
						<c:url var="login" value="/tekusource/user/login" />
						<form:form modelAttribute="userSession" action="${login}" method="post">
							<div class="div-wrapper"><label for="username">Username:</label><form:input path="username" class="login-textbox"/></div>
							<div class="space-clear"></div>
							<div class="space-break"></div>
							<div class="div-wrapper"><label for="password">Password:</label><form:password path="password" class="login-password"/></div>
							<div class="space-clear"></div>
							<div class="space-break"></div>
							
							<form:hidden path="lastname"/>
							<form:hidden path="firstname"/>
							<form:hidden path="email"/>
							<form:hidden path="confirmPassword"/>
			
							<div class="div-wrapper"><input type="submit" value="" class="superfly_btn login_btn"/></div>
							<div class="space-break"></div>
							<div class="space-break"></div>
						</form:form>
					</div>
				</div>
				<div class="content-right">
					<div>
						${aboutContent.textContent}
					</div>
				</div>
				<div class="space-clear"></div>
		</div>
		
		<div id="superfly-footer">
			<center>Tekusource © 2013. All Rights Reserved.</center>
		</div>
	</div>
		<!-- 
		<c:url var="login" value="/tekusource/user/login" />
		<c:url var="register" value="/tekusource/user/register" />
		<form:form modelAttribute="userSession" action="${login}" method="post">
			<label for="username">Username:</label><form:input path="username"/>
			<label for="password">Password:</label><form:password path="password"/>
			
			<form:hidden path="lastname"/>
			<form:hidden path="firstname"/>
			<form:hidden path="email"/>
			<form:hidden path="confirmPassword"/>
			
			<input type="submit" value="login"/>
		</form:form>
		
		<form:form modelAttribute="userSession" action="${register}" method="post">
			<c:out value="${registrationMessage}"/>
			<form:errors path="lastname" cssClass="error"/><br/>
			<form:errors path="firstname" cssClass="error"/><br/>
			<form:errors path="email" cssClass="error"/><br/>
			<form:errors path="username" cssClass="error"/><br/>
			<form:errors path="password" cssClass="error"/><br/>
			<form:errors path="confirmPassword" cssClass="error"/><br/><br/>
			
			<label for="lastname">Lastname:</label><form:input path="lastname"/>
			<label for="firstname">Firstname:</label><form:input path="firstname"/>
			<label for="email">Email:</label><form:input path="email"/>
			<label for="username">Username:</label><form:input path="username"/>
			<label for="password">Password:</label><form:input path="password"/>
			<label for="confirmPassword">Confirm Password:</label><form:input path="confirmPassword"/>
			
			<input type="submit" value="register"/>
		</form:form>
		 -->
	</body>
</html>