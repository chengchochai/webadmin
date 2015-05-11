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
					<h1 class="page-header">角色管理</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
	        <div class="row">
	            <div class="col-lg-12">
					<div class="panel panel-primary">
						 <div class="panel-heading">
                            角色编辑／添加
                        </div>
                        <div class="panel-body">
                            <div class="row">
                            	<div class="col-lg-8">
                            		<form role="form" id="form" action="${contextPath }/system/role/edit" method="post">
	                            		<input type="hidden" name="roleId" value="${role.roleId }"/>
                            			<div class="form-group">
                                            <label><span class="required"> * </span>角色名称:</label>
                                           	<input class="form-control" name="roleName" value="${role.roleName }">
                                        </div>
                                        <div class="form-group">
                                            <label>角色描述:</label>
                                            <textarea name="description" class="form-control" rows="3">${role.description }</textarea>
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"> * </span>角色权限:</label>
                                          	<c:forEach var="module" items="${allModules }">
                                          		<c:set var="moduleIdSame" value="false"/>
                                          		<c:forEach var="_module" items="${role.modules }">
                                          			<c:if test="${module.moduleId==_module.moduleId }">
                                          				<c:set var="moduleIdSame" value="true"/>
                                          			</c:if>
	                                            </c:forEach>
	                                            <label class="checkbox-inline">
	                                                <input type="checkbox" name="modules" id="${module.moduleId }" <c:if test="${moduleIdSame==true }">checked</c:if> <c:if test="${module.enabled==false }">disabled</c:if> value="${module.moduleId }" > ${module.moduleName }
	                                            </label>
                                           	</c:forEach>
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
	<script src="${contextPath}/static/assets/pages/system/role/FormValid.js" type="text/javascript"></script>
	<script type="text/javascript">
  	jQuery(document).ready(function() { 
  		FormValid.init();
  	});
  	</script>
</body>

</html>
