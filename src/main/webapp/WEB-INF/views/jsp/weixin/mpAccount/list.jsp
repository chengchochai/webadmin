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
					<h1 class="page-header">微信管理</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-heading">公众号列表</div>
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
											<th>微信号</th>
											<th>appId</th>
											<th>appSecret</th>
											<th>appToken</th>
											<th>accessToken</th>
											<th>accessToken失效时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="mpAccount" items="${mpAccounts }">
											<tr>
												<td>${mpAccount.mpAccountId }</td>
												<td>${mpAccount.mpAccountName }</td>
												<td>${mpAccount.appId }</td>
												<td>${mpAccount.appSecret }</td>
												<td>${mpAccount.appToken }</td>
												<td>${mpAccount.accessToken }</td>
												<td>${mpAccount.accessTokenDeadTime }</td>
												<td>
													<a href="${contextPath}/weixin/mpAccount/edit/${mpAccount.mpAccountId}" class="btn btn-primary btn-xs">编辑</a>
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
					<a href="${contextPath }/weixin/mpAccount/edit/0" class="btn btn-primary">添加公众号</a>
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
