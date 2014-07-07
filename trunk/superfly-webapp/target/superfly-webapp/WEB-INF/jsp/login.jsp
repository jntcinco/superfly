<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>RASuperfly Gamefarm</title>
	</head>
	<body>
		<c:url var="login" value="/tekusource/user/login" />
		<form:form modelAttribute="userSession" action="${login}" method="post">
			<c:out value="${loginMessage}"/>
			<form:errors path="username" cssClass="error"/><br/>
			<form:errors path="password" cssClass="error"/><br/><br/>
			
			<label for="username">Username:</label><form:input path="username"/>
			<label for="password">Password:</label><form:password path="password"/>
			
			<form:hidden path="lastname"/>
			<form:hidden path="firstname"/>
			<form:hidden path="email"/>
			<form:hidden path="confirmPassword"/>
			
			<input type="submit" value="login"/>
		</form:form>
	</body>
</html>