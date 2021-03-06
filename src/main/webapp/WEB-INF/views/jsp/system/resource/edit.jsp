<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/static/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<!-- css -->
<%@ include file="/WEB-INF/static/common/head.jsp"%>

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
					<h1 class="page-header">资源管理</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
	        <div class="row">
	            <div class="col-lg-12">
					<div class="panel panel-primary">
						 <div class="panel-heading">
                            资源编辑／添加
                        </div>
                        <div class="panel-body">
                            <div class="row">
                            	<div class="col-lg-8">
                            		<form role="form" id="form" action="${contextPath }/system/resource/edit" method="post">
	                            		<input type="hidden" name="resourceId" value="${resource.resourceId }"/>
                            			<div class="form-group">
                                            <label><span class="required"> * </span>资源名称:</label>
                                           	<input class="form-control" name="resourceName" value="${resource.resourceName }">
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"> * </span>资源URI:</label>
                                           	<input class="form-control" name="resourceUri" value="${resource.resourceUri }">
                                        </div>
                                        <div class="form-group">
                                            <label>资源描述:</label>
                                            <textarea name="description" class="form-control" rows="3">${resource.description }</textarea>
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"> * </span>显示级别:</label>
                                            <select name="priority" class="form-control">
                                            	<c:forEach var="i" begin="0" end="9" >
                                            		<option value="${i}" <c:if test="${resource.priority==i}">selected</c:if> >${i}</option>
                                            	</c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"> * </span>是否启用: </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="enabled" value="Y" <c:if test="${resource.enabled==true }">checked</c:if>>可用
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="enabled" value="N" <c:if test="${resource.enabled==false }">checked</c:if>>禁用
                                            </label>
                                        </div>
                                        <button type="submit" class="btn btn-primary">保存</button>
                                        <button type="button" onclick="window.history.go(-1);" class="btn btn-default">返回</button>
                            		</form>
                            	</div>
                            </div>
                        </div>
					</div>	
				</div>
	        <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
	<!-- javascript -->
  	<%@ include file="/WEB-INF/static/common/javascript.jsp"%>
  	<!-- jQuery validate -->
  	<script src="${contextPath}/static/assets/bower_components/jqueryvalidation/dist/jquery.validate.min.js"></script>
  	<!-- Custom Theme JavaScript -->
	<script src="${contextPath}/static/assets/sbadmin/js/sb-admin-2.js"></script>
	<script src="${contextPath}/static/assets/pages/system/resource/FormValid.js" type="text/javascript"></script>
	<script type="text/javascript">
  	jQuery(document).ready(function() { 
  		FormValid.init();
  	});
  	</script>
</body>

</html>
