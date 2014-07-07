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
        
        <link rel="stylesheet" type="screen" href="<c:url value='/plugins/classybox/css/documentation.css'/>"/>
		
		<script type="text/javascript" src="<c:url value='/plugins/jquery-ui-1.10.4.custom/js/jquery-1.10.2.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js'/>"></script>
        <script src="<c:url value='/plugins/classybox/js/jquery.classybox.js'/>"></script>
        <script src="<c:url value='/plugins/classybox/js/jwplayer.js'/>"></script>
        <script src="<c:url value='/plugins/classybox/js/jwplayer.html5.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/scripts/superfly.js'/>"></script>
        
        <link rel="stylesheet" type="text/css" href="<c:url value='/plugins/classybox/css/jquery.classybox.css'/>" />
		<link rel="stylesheet" type="text/css" href="<c:url value='/plugins/jquery-ui-1.10.4.custom/css/smoothness/jquery-ui-1.10.4.custom.css'/>" />
		<link rel="stylesheet" type="text/css" href="<c:url value='/styles/superfly.css'/>" />
    </head>
    <body>
		<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
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
				<div class="content-gallery">
                    <div class="gallery">
                    	<c:forEach items="${album.images}" var="image">
                    		<a class="showMe" href="<c:url value='/gallery/image/${image.fileName}'/>" title="<c:url value='${image.fileDescription}'/>">
                        		<img src="<c:url value='/gallery/image/${image.fileName}'/>" alt="" width="219" height="167"/>
                        	</a>
                    	</c:forEach>
                    </div> 
				</div>
				<div class="space-clear"></div>
			</div>
		
		
			<script type="text/javascript">
            	tekusource(document).ready(function() {
                	tekusource(".gallery a").classybox({
                    	/*ads: "ads.html"*/
                    });
                	tekusource(window).classybox({
                    	autoDetect: 1
                	});
                	tekusource(".showMe").classybox({
                    	height: 1246
                    });
                   	
                    
					superflyEffects.populateDialog("cms_page");
					
					tekusource.fn.center = function () {
		    			this.css("position", "absolute");
		    			this.css("top", (tekusource(window).height() - this.height()) / 2 + tekusource(window).scrollTop() + "px");
		    			this.css("left", (tekusource(window).width() - this.width()) / 2 + tekusource(window).scrollLeft() + "px");
		    			return this;
					}
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
