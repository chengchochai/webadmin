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
<!-- Custom CSS -->
<link href="${contextPath}/static/assets/sbadmin/css/sb-admin-2.css" rel="stylesheet">
</head>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
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
						<!-- /.panel-heading -->
						<div class="panel-body">
							<c:if test="${pageTip!=null && pageTip!=''}"><!-- 页面提示 -->
								<div class="alert <c:if test="${fn:contains(pageTip,'成功') }">alert-success</c:if><c:if test="${!fn:contains(pageTip,'成功') }">alert-danger</c:if> alert-dismissable">
	                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	                                ${pageTip}
	                            </div>
                            </c:if>
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover" id="dataTables">
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
									<tbody>
										<c:forEach var="log" items="${logs }">
											<tr>
												<td>${log.logId }</td>
												<td>${log.loginName }</td>
												<td>${log.loginIp }</td>
												<td>${log.loginTime }</td>
												<td><abbr title="${log.description }">${log.description }</abbr></td>
												<td>${log.logoutTime}</td>
												<td>
													<c:if test="${log.logoutType=='ACTIVELOGINOUT' }">网页登出</c:if>
													<c:if test="${log.logoutType=='PASSIVELOGOUT' }">另行登入</c:if>
													<c:if test="${log.logoutType=='SESSIONFAILURE' }">会话失效</c:if>
													<c:if test="${log.logoutType=='BROWSERCLOSED' }">浏览器关闭</c:if>
													<c:if test="${log.logoutType=='SYSTEMSHUTDOWN' }">系统关闭</c:if>
												</td>
											</tr>
										</c:forEach>
									</tbody>
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
					<a href="${contextPath }/system/module/edit/0" class="btn btn-primary">添加模块</a>
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
	<!-- Custom Theme JavaScript -->
	<script src="${contextPath}/static/assets/sbadmin/js/sb-admin-2.js"></script>
	<script>
		$(document).ready(function() {
			$('#dataTables').DataTable({
				"language": {
	                 "lengthMenu": "每页 _MENU_ 条记录",
	                 "zeroRecords": "没有找到记录",
	                 "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
	                 "infoEmpty": "没有记录",
	                 "infoFiltered": "(从 _MAX_ 条记录中过滤)",
	                 "search" : "搜索:",
	                 "loadingRecords": "加载中...",	
	                 "paginate": {
	                     "first":"首页",
	                     "last":"末页",
	                     "next":"下页",
	                     "previous":"下页"
	                 }
	             },
				responsive : true
			});
		});
	</script>
</body>

</html>
