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
                            公众号编辑／添加
                        </div>
                        <div class="panel-body">
                            <div class="row">
                            	<div class="col-lg-8">
                            		<form role="form" id="form" action="${contextPath }/weixin/mpAccount/edit" method="post">
	                            		<input type="hidden" name="mpAccountId" value="${mpAccount.mpAccountId }"/>
                            			<div class="form-group">
                                            <label><span class="required"> * </span>公众号名称:</label>
                                           	<input class="form-control" name="mpAccountName" value="${mpAccount.mpAccountName}"/>
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"> * </span>appId:</label>
                                           	<input class="form-control"  name="appId" value="${mpAccount.appId}" <c:if test="${mpAccount!=null }">disabled</c:if>/>
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"> * </span>appSecret:</label>
                                           	<input class="form-control" name="appSecret" value="${mpAccount.appSecret}"/>
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"> * </span>appToken:</label>
                                           	<input class="form-control" name="appToken" value="${mpAccount.appToken}"/>
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"></span>encodingAESKey:</label>
                                           	<input class="form-control" name="encodingAESKey" value="${mpAccount.encodingAESKey}"/>
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"></span>accessToken:</label>
                                           	<input class="form-control" name="accessToken" value="${mpAccount.accessToken}" disabled/>
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"></span>accessToken截至日期:</label>
                                           	<input class="form-control" name="accessTokenDeadTime" value="${mpAccount.accessTokenDeadTime}" disabled/>
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
	<script src="${contextPath}/static/assets/pages/weixin/mpAccount/FormValid.js" type="text/javascript"></script>
	<script type="text/javascript">
  	jQuery(document).ready(function() { 
  		FormValid.init();
  	});
  	</script>
</body>

</html>
