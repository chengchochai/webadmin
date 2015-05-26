<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/static/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<!-- css -->
<%@ include file="/WEB-INF/static/common/head.jsp"%>
<!-- DataTables CSS -->
<link href="${contextPath}/static/assets/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">
<!-- DataTables Responsive CSS -->
<link href="${contextPath}/static/assets/bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">
<!-- Bootstrap-datetimepicker CSS -->
<link href="${contextPath}/static/assets/bower_components/bootstrap/dist/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="${contextPath}/static/assets/sbadmin/css/sb-admin-2.css" rel="stylesheet">
</head>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<!-- header -->
			<%@ include file="/WEB-INF/static/common/header.jsp"%>
			<!-- navigation -->
			<%@ include file="/WEB-INF/static/common/navigation.jsp"%>
		</nav>
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">模块管理</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-heading">模块列表</div>
						<div class="panel-body">
							<form id="searchForm" role="form">
								&nbsp;&nbsp;登录时间:
								<input name="startTime" size="18" type="text" value="" class="form_datetime" readonly>到<input name="endTime" size="18" type="text" value="" class="form_datetime" readonly>
								&nbsp;&nbsp;登录名:
								<input name="loginName" size="12"/>
								&nbsp;&nbsp;登录IP:
								<input name="loginIp" size="14"/>
								<button type="button" id="searchButton" class="btn btn-default btn-xs">搜索</button>
							</form>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<c:if test="${pageTip!=null && pageTip!=''}">
								<!-- 页面提示 -->
								<div
									class="alert <c:if test="${fn:contains(pageTip,'成功') }">alert-success</c:if><c:if test="${!fn:contains(pageTip,'成功') }">alert-danger</c:if> alert-dismissable">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									${pageTip}
								</div>
							</c:if>
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables">
									<thead>
										<tr>
											<th>#</th>
											<th>登录名</th>
											<th>登录IP</th>
											<th>登录时间</th>
											<th>登陆描述</th>
											<th>登出时间</th>
											<th>登出方式</th>
										</tr>
									</thead>
								</table>
							</div>
							<!-- /.table-responsive -->
						</div>
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="row">
				<div class="col-lg-2">
					<a href="${contextPath }/system/module/edit/0"
						class="btn btn-primary">添加模块</a>
				</div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
	<!-- javascript -->
	<%@ include file="/WEB-INF/static/common/javascript.jsp"%>
	<script src="${contextPath}/static/assets/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	<script src="${contextPath}/static/assets/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
	<script src="${contextPath}/static/assets/bower_components/bootstrap/dist/js/bootstrap-datetimepicker.js"></script>
	<script src="${contextPath}/static/assets/bower_components/bootstrap/dist/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="${contextPath}/static/assets/sbadmin/js/sb-admin-2.js"></script>
	<script src="${contextPath}/static/assets/pages/dataTable.js"></script>
	<script>
		init('${contextPath }/log/login/fillData/');
		$('.form_datetime').datetimepicker({
			format: 'yyyy-mm-dd hh:ii:ss',
			language: 'zh-CN', //汉化 
			autoclose:true //选择日期后自动关闭 
		});
		
	</script>
	
</body>

</html>
