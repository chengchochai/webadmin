<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
					<h1 class="page-header">角色管理</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-heading">角色列表</div>
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
											<th>名称</th>
											<th>描述</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="role" items="${roles }">
											<tr>
												<td>${role.roleId }</td>
												<td>${role.roleName }</td>
												<td><abbr title="${role.description }">${role.description }</abbr></td>
												<td>
													<a href="${contextPath}/system/role/edit/${role.roleId}" class="btn btn-primary btn-xs">编辑</a>
													<a onclick="if(!confirm('确定要删除吗?'))return false;" href="${contextPath}/system/role/del/${role.roleId}" class="btn btn-danger btn-xs">删除</a>
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
					<a href="${contextPath }/system/role/edit/0" class="btn btn-primary">添加角色</a>
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
	                 "zeroRecords": "没有找到记录",
	                 "info": "从 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
	                 "infoEmpty": "没有记录",
	                 "processing" : "加载中...",
	                 "paginate": {
	                     "first":"首页",
	                     "last":"末页",
	                     "next":"下页",
	                     "previous":"上页"
	                 }
	             },
				responsive : true,
				processing : true,
				bSort : false,// 排序
				bFilter : false,// 搜索
				searching : false,// 搜索
				bLengthChange : false,// 页面大小
				serverSide : true,// 开启异步数据加载
				pagingType : 'full_numbers',// 显示首页和尾页
				sAjaxSource : '${contextPath }/system/role/fillData',
				fnServerData : function(sSource, aDataSet, fnCallback) {
					$.ajax({
						"dataType" : 'json',
						"type" : "POST",
						"data" : {
							start:aDataSet[3].value,
							limit:aDataSet[4].value,
							sEcho:aDataSet[0].value
						},
						"url" : sSource,
						"success" : fnCallback
					});
				}
			});
		});
		
	</script>
</body>

</html>
