<div class="header-container"></div>
<c:if test="${sessionScope.userSession == null}">
	<div class="nav-wrapper">
		<ul id="menu">
			<li><a href="<c:url value='/tekusource/user'/>">About</a></li>
			<li><a href="<c:url value='/tekusource/file/video'/>">Videos</a></li>
			<li><a href="<c:url value='/tekusource/file/image'/>">Bloodlines</a></li>
			<li><a href="<c:url value='/tekusource/salePackagePrice'/>">Price List</a></li>
			<li><a href="<c:url value='/tekusource/awards'/>">Awards</a></li>
			<li><a href="#">Register</a></li>
			<li><a href="#">Contact Us</a></li>
		</ul>
	</div>
</c:if>