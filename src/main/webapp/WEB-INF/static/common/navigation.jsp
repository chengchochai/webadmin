<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- /.navbar-top-links -->
<div class="navbar-default sidebar" role="navigation">
	<div class="sidebar-nav navbar-collapse">
		<ul class="nav" id="side-menu">
			<li class="sidebar-search">
				<div class="input-group custom-search-form">
					<input type="text" class="form-control" placeholder="搜索...">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div> <!-- /input-group -->
			</li>
			<li><a href="${contextPath }/index"><i class="fa fa-dashboard fa-fw"></i>首页</a></li>
			<c:set value="${sessionScope.session_userAuthority}" var="menus" />
			<c:forEach var="menu" items="${menus }">
				<li>
					<c:set var="menu_module" value="${menu.key}"/>
					<a href="#"><i class="fa ${menu_module.icon } fa-fw"></i>${menu_module.moduleName }<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<c:forEach var="resource" items="${menus[menu_module]}">
							<li><a href="${contextPath}${resource.resourceUri}">${resource.resourceName}</a></li>
						</c:forEach>
					</ul> <!-- /.nav-second-level -->
				</li>
			</c:forEach>
		</ul>
	</div>
	<!-- /.sidebar-collapse -->
</div>
<!-- /.navbar-static-side -->
