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
	<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
	<div class="main-container">
		<div class="header-container"></div>
		<c:if test="${sessionScope.userSession == null}">
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
		</c:if>
		
		<div id="content-body">
				<div id="content-header">
					<a href="#"><span class="user-span">Welcome <b>${sessionScope.userSession.username}</b></span></a>
				</div>
				<div class="content-left">
					<div id="superfly-login">
						<span>Main Menu</span>
						<div class="space-break"></div>
						<ul id="navlist">
							<li id="active">
								<a href="#" id="current">User Configuration</a>
							</li>
							<li id="active">
								<a href="<c:url value='/tekusource/admin/cms_page'/>" id="current">CMS Configuration</a>
							</li>
							<li id="active">
								<a href="#" id="current">IS Configuration</a>
							</li>
							<li id="active">
								<a href="<c:url value='/tekusource/user/sign_out'/>" id="current">Sign-out</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="content-right">
					<div class="box-link">
					<ul>
						<li>
							<a href="#"><img src="${contextPath}/images/administration/user_configuration.gif" alt="" width="150" height="150"/></a>
						</li>
						<li>
							<a href="<c:url value='/tekusource/admin/cms_page'/>"><img src="${contextPath}/images/administration/cms_configuration.gif" alt="" width="150" height="150"/></a>
						</li>
						<li>
							<a href="#"><img src="${contextPath}/images/administration/is_configuration.gif" alt="" width="150" height="150"/></a>
						</li>
					</ul>
					</div>
				</div>
				<div class="space-clear"></div>
		</div>
		
		<div id="superfly-footer">
			<center>Tekusource © 2013. All Rights Reserved.</center>
		</div>
	</div>
	</body>
</html>