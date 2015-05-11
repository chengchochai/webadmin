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
					<h1 class="page-header">用户管理</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
	        <div class="row">
	            <div class="col-lg-12">
					<div class="panel panel-primary">
						 <div class="panel-heading">
                            用户编辑／添加
                        </div>
                        <div class="panel-body">
                            <div class="row">
                            	<div class="col-lg-8">
                            		<form role="form" id="form" action="${contextPath }/system/user/edit" method="post">
	                            		<input type="hidden" name="userId" value="${user.userId }"/>
                            			<div class="form-group">
                                            <label><span class="required"> * </span>登录名:</label>
                                           	<input class="form-control" name="loginName" <c:if test="${user!=null }">disabled</c:if> value="${user.loginName }">
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"> * </span>密码:</label>
                                           	<input class="form-control" name="loginPass"  value="${user.loginPass }">
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"> * </span>真实姓名:</label>
                                            <input class="form-control" name="realName" value="${user.realName }">
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"> * </span>邮箱:</label>
                                            <input class="form-control" name="email" value="${user.email }">
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"> * </span>手机:</label>
                                            <input class="form-control" name="mobile" value="${user.mobile }">
                                        </div>
                                        <div class="form-group">
                                            <label>创建时间:</label>
                                            <input class="form-control" name="createTime" value="${user.createTime }" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"> * </span>性别: </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="sex" value="MALE" <c:if test="${user.sex=='MALE' }">checked</c:if>>男
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="sex" value="FEMALE" <c:if test="${user.sex=='FEMALE' }">checked</c:if>>女
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="sex" value="UNKNOWN" <c:if test="${user.sex=='UNKNOWN' }">checked</c:if>>保密
                                            </label>
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"> * </span>状态: </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="enabled" value="Y" <c:if test="${user.enabled==true }">checked</c:if>>可用
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="enabled" value="N" <c:if test="${user.enabled==false }">checked</c:if>>禁用
                                            </label>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label><span class="required"> * </span>用户角色:</label>
                                          	<c:forEach var="role" items="${allRoles }">
                                          		<c:set var="roleIdSame" value="false"/>
                                          		<c:forEach var="_role" items="${user.roles }">
                                          			<c:if test="${role.roleId==_role.roleId }">
                                          				<c:set var="roleIdSame" value="true"/>
                                          			</c:if>
	                                            </c:forEach>
	                                            <label class="checkbox-inline">
	                                                <input type="checkbox" name="roles" id="${role.roleId }" <c:if test="${roleIdSame==true }">checked</c:if>  value="${role.roleId }" > ${role.roleName }
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
	<script src="${contextPath}/static/assets/pages/system/user/FormValid.js" type="text/javascript"></script>
	<script type="text/javascript">
  	jQuery(document).ready(function() { 
  		FormValid.init();
  	});
  	</script>
</body>

</html>
