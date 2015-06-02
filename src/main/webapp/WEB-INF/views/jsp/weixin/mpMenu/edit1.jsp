<%@page import="com.jipengblog.webadmin.entity.constant.MpMenuType"%>
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
					<h1 class="page-header">微信管理</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
	        <div class="row">
	            <div class="col-lg-12">
					<div class="panel panel-primary">
						 <div class="panel-heading">
                            自定义菜单编辑／添加
                        </div>
                        <div class="panel-body">
                            <div class="row">
                            	<div class="col-lg-8">
                            		<form role="form" id="form" action="${contextPath }/weixin/mpMenu/edit" method="post">
	                            		<input type="hidden" name="mpAccountId" value="${mpAccount.mpAccountId }"/>
	                            		<input type="hidden" name="mpMenuId" value="${mpMenu.mpMenuId }"/>
                            			<div class="form-group">
                                        	<label><span class="required"> * </span>菜单级别:</label>
                                        	一级菜单(${mpAccount.mpAccountName })	
                                        </div>
                            			<div class="form-group">
                                            <label><span class="required"> * </span>菜单名称:</label>
                                           	<input class="form-control" name="mpMenuName" value="${mpMenu.mpMenuName}"/>
                                           	<p class="help-block">一级菜单最多4个汉字，多出来的部分将会以“...”代替。</p>
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"> * </span>显示级别:</label>
                                            <select name="priority" class="form-control">
                                            	<c:forEach var="i" begin="0" end="4" >
                                            		<option value="${i}" <c:if test="${mpMenu.priority==i}">selected</c:if> >${i}</option>
                                            	</c:forEach>
                                            </select>
                                            <p class="help-block">说明:级别由高到低分别对应由左到右(一级菜单)或由上到下(二级菜单)</p>
                                        </div>
                                        <div class="form-group">
                                        	<label><span class="required"> * </span>菜单类型:</label>
                                            <c:set var="enumValues" value="<%=MpMenuType.values()%>"/>
                                            <select name="mpMenuType" class="form-control">
	                                            <c:forEach var="enumValue" items="${enumValues }">
	                                            	<option value="${enumValue }" <c:if test="${enumValue == mpMenu.mpMenuType }">selected</c:if> >${enumValue.value }</option>
	                                            </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>URL:</label>
                                           	<input class="form-control" name="mpMenuUrl" value="${mpMenu.mpMenuUrl}"/>
                                        </div>
                                        <div class="form-group">
                                            <label>KEY:</label>
                                           	<input class="form-control" name="mpMenuKey" value="${mpMenu.mpMenuKey}"/>
                                        </div>
                                        <div class="form-group">
                                            <label>MediaId:</label>
                                           	<input class="form-control" name="mpMenuMediaId" value="${mpMenu.mpMenuMediaId}"/>
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
	<script src="${contextPath}/static/assets/pages/weixin/mpMenu/FormValid.js" type="text/javascript"></script>
	<script type="text/javascript">
  	jQuery(document).ready(function() { 
  		FormValid.init();
  	});
  	</script>
</body>

</html>
