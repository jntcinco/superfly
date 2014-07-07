<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="description" content="Official website of RA Superfly Gamefarm - Atty. Ryan Abrenica">
	<meta name="keywords" content="Gamefarm,Gamefowl,Atty. Ryan Abrenica">
	<meta name="robots" content="index,follow">
	<meta name="DC.title" content="Atty. Ryan Abrenica">
	<title>RA Superfly Gamefarm</title>
		
	<script type="text/javascript" src="<c:url value='/plugins/jquery-ui-1.10.4.custom/js/jquery-1.10.2.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/plugins/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/plugins/ckeditor/ckeditor.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/scripts/superfly.js'/>"></script>
	
	<link rel="stylesheet" type="text/css" href="<c:url value='/plugins/jquery-ui-1.10.4.custom/css/smoothness/jquery-ui-1.10.4.custom.css'/>" />
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
								<a href="<c:url value='/tekusource/admin/cms_page' />" id="current">CMS Configuration</a>
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
							<a href="#" onClick="superflyEffects.showPopupAwardsContentForm();"><img src="${contextPath}/images/administration/customize_awards.gif" alt="" width="150" height="150"/></a>
						</li>
						<li>
							<a href="#" onClick="superflyEffects.showPopupHomeContentForm();"><img src="${contextPath}/images/administration/customize_about.gif" alt="" width="150" height="150"/></a>
						</li>
						<li>
							<a href="#"><img src="${contextPath}/images/administration/is_configuration.gif" alt="" width="150" height="150"/></a>
						</li>
						<li>
							<a href="#" onClick="superflyEffects.showPopupFileUploadForm();"><img src="${contextPath}/images/administration/image_upload.gif" alt="" width="150" height="150"/></a>
						</li>
						<li>
							<a href="#" onClick="superflyEffects.showAddSalePackageForm();"><img src="${contextPath}/images/administration/add_price.gif" alt="" width="150" height="150"/></a>
						</li>
					</ul>
					</div>
					
					<!-- start dialog forms -->
					<div id="fileUploadDiv" class="ui-dialog-titlebar ui-widget-header" title="Image Upload">
						<form id="fileUploadForm" action="<c:url value='/tekusource/file/upload'/>" method="POST" enctype="multipart/form-data">
							Photo Description: <input type="text" name="fileDescription" id="fileDescription" value=""/><br/>
							Please select a file to upload : <input name="file" type="file" id="file"/><br/>
						</form>
					</div>
					<div id="contentDiv" title="Content Configuration">
						<form id="contentForm" method="get">
						<p>
							<textarea id="editor1" name="editor1">${homeContent.textContent}</textarea>
						</p>
						</form>
						<ckeditor:replace replace="editor1" basePath="${contextPath}/plugins/ckeditor/"/>
					</div>
					<div id="addSalePackageDiv" class="ui-dialog-titlebar ui-widget-header" title="Enter Bloodline Details">
						<form id="addSalePackageForm" action="<c:url value='/tekusource/salePackagePrice/addSalePackage'/>" method="POST">
							<label for="description">Package Description:</label> <input name="description" type="text"/><br/>
							<label for="classType">Class Type :</label> <input name="classType" type="text"/><br/>
							<label for="price">Price:</label> <input name="price" type="text"/><br/>
						</form>
					</div>
					<div id="msgDiv" class="msgDivClass" title="Message">
						<div class="msgBox"></div>
					</div>
					<!-- end dialog forms -->
				</div>
				<div class="space-clear"></div>
		</div>
		
		
	<script type="text/javascript">
		tekusource(document).ready(function(){
			superflyEffects.populateDialog("cms_page");
			
			/*tekusource.fn.center = function () {
    			this.css("position", "absolute");
    			this.css("top", (tekusource(window).height() - this.height()) / 2 + tekusource(window).scrollTop() + "px");
    			this.css("left", (tekusource(window).width() - this.width()) / 2 + tekusource(window).scrollLeft() + "px");
    			return this;
			}*/
		});
	</script>
		<div id="superfly-footer">
			<center>Tekusource © 2013. All Rights Reserved.</center>
		</div>
	</div>
	<div id="progress">
		<div class="ajax-loader">
			<img src="${contextPath}/images/loader/ajax-loader.gif" alt="Loading" width="220" height="19"/>
			<div class="space-clear"></div>
			In-progress...
		</div>
	</div>
</body>
</html>