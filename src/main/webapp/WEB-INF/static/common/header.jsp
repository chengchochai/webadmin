<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="navbar-header">
	<button type="button" class="navbar-toggle" data-toggle="collapse"
		data-target=".navbar-collapse">
		<span class="sr-only">Toggle navigation</span> 
		<span class="icon-bar"></span>
		<span class="icon-bar"></span> 
		<span class="icon-bar"></span>
	</button>
	<a class="navbar-brand" href="${contextPath }/index">Penn Admin</a>
</div>
<!-- /.navbar-header -->
<ul class="nav navbar-top-links navbar-right">
	<li class="dropdown"><a class="dropdown-toggle"
		data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
			<i class="fa fa-caret-down"></i>
	</a>
		<ul class="dropdown-menu dropdown-user">
			<li><a href="#"><i class="fa fa-user fa-fw"></i> 用户信息 </a></li>
			<li><a href="#"><i class="fa fa-gear fa-fw"></i> 系统设置 </a></li>
			<li class="divider"></li>
			<li><a href="${contextPath}/loginout"><i class="fa fa-sign-out fa-fw"></i>完全退出</a></li>
		</ul> <!-- /.dropdown-user --></li>
	<!-- /.dropdown -->
</ul>
