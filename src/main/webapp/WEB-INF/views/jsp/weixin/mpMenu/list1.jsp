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
						<div class="panel-heading">自定义(一级)菜单</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<c:if test="${pageTip!=null && pageTip!=''}"><!-- 页面提示 -->
								<div class="alert <c:if test="${fn:contains(pageTip,'成功') }">alert-success</c:if><c:if test="${!fn:contains(pageTip,'成功') }">alert-danger</c:if> alert-dismissable">
	                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	                                ${pageTip}
	                            </div>
                            </c:if>
                            <div class="col-lg-12">
                            	<div id="searchDiv" class="form-group">
									<label>微信公众号:</label>
									<label class="radio-inline">
	                                    <select id="mpAccountId" name="mpAccountId" class="form-control">
		                            		<c:forEach var="mpAccount" items="${mpAccounts }">
		                            			<option value="${mpAccount.mpAccountId }">${mpAccount.mpAccountName }</option>
		                                    </c:forEach>
		                                </select>
	                                </label>
	                                <label class="radio-inline">
                                    	<button type="button" id="searchButton" class="btn btn-default btn-xs">搜索</button>
                                    </label>
                                    <label class="radio-inline">
                                    	<p class="help-block">请先选择微信公众号</p>
                                    </label>
                            	</div>
                            </div>
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover" id="dataTables">
									<thead>
										<tr>
											<th>#</th>
											<th>名称</th>
											<th>类型</th>
											<th>Key</th>
											<th>Url</th>
											<th>MediaId</th>
											<th>操作</th>
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
					<a href="#" onclick="javascript:addMenu()" class="btn btn-primary">添加一级菜单</a>
				</div>				
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->
	</div>

	<!-- modal -->
	<div id="identifier" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">系统提示</h4>
				</div>
				<div class="modal-body">
					<p>当前选择的公众号已经有3个一级菜单。根据微信公众平台规则，不能再添加新的一级菜单。</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">知道了</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	<!-- /#wrapper -->
	<!-- javascript -->
	<%@ include file="/WEB-INF/static/common/javascript.jsp"%>
	<script src="${contextPath}/static/assets/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	<script src="${contextPath}/static/assets/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="${contextPath}/static/assets/sbadmin/js/sb-admin-2.js"></script>
	<script src="${contextPath}/static/assets/pages/dataTable.js"></script>
	<script>
		init('${contextPath }/weixin/mpMenu/fillData');
		function addMenu(){
			var menuCount = $("#dataTables").find("tbody").find("tr").length;
			if(menuCount>=3){
				$('#identifier').modal('show');
				return;
			}
			var href= "${contextPath }/weixin/mpMenu/edit1/"+$("#mpAccountId").val()+"/0";
			window.location.href = href;
		}
	</script>
</body>

</html>
