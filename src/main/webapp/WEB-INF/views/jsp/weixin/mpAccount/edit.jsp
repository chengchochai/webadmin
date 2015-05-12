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
					<h1 class="page-header">模块管理</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
	        <div class="row">
	            <div class="col-lg-12">
					<div class="panel panel-primary">
						 <div class="panel-heading">
                            模块编辑／添加
                        </div>
                        <div class="panel-body">
                            <div class="row">
                            	<div class="col-lg-8">
                            		<form role="form" id="form" action="${contextPath }/system/module/edit" method="post">
	                            		<input type="hidden" name="moduleId" value="${module.moduleId }"/>
                            			<div class="form-group">
                                            <label><span class="required"> * </span>模块名称:</label>
                                           	<input class="form-control" name="moduleName" value="${module.moduleName}"/>
                                        </div>
                                        <div class="form-group">
                                            <label>模块描述:</label>
                                            <textarea name="description" class="form-control" rows="3">${module.description }</textarea>
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"> * </span>显示级别:</label>
                                            <select name="priority" class="form-control">
                                                <option value="0" <c:if test="${module.priority==0 }">selected</c:if> >0</option>
                                                <option value="1" <c:if test="${module.priority==1 }">selected</c:if> >1</option>
                                                <option value="2" <c:if test="${module.priority==2 }">selected</c:if> >2</option>
                                                <option value="3" <c:if test="${module.priority==3 }">selected</c:if> >3</option>
                                                <option value="4" <c:if test="${module.priority==4 }">selected</c:if> >4</option>
                                                <option value="5" <c:if test="${module.priority==5 }">selected</c:if> >5</option>
                                                <option value="6" <c:if test="${module.priority==6 }">selected</c:if> >6</option>
                                                <option value="7" <c:if test="${module.priority==7 }">selected</c:if> >7</option>
                                                <option value="8" <c:if test="${module.priority==8 }">selected</c:if> >8</option>
                                                <option value="9" <c:if test="${module.priority==9 }">selected</c:if> >9</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"> * </span>是否启用: </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="enabled" value="Y" <c:if test="${module.enabled==true }">checked</c:if>>可用
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="enabled" value="N" <c:if test="${module.enabled==false }">checked</c:if>>禁用
                                            </label>
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"> * </span>模块图标: </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="icon" value="fa-cloud" <c:if test="${module.icon=='fa-cloud' }">checked</c:if>> <p class="fa fa-cloud"></p>
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="icon" value="fa-square" <c:if test="${module.icon=='fa-square' }">checked</c:if>> <p class="fa fa-square"></p>
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="icon" value="fa-table" <c:if test="${module.icon=='fa-table' }">checked</c:if>> <p class="fa fa-table"></p>
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="icon" value="fa-envelope" <c:if test="${module.icon=='fa-envelope' }">checked</c:if>> <p class="fa fa-envelope"></p>
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="icon" value="fa-comment" <c:if test="${module.icon=='fa-comment' }">checked</c:if>> <p class="fa fa-comment"></p>
                                            </label>
                                        </div>
                                        <div class="form-group">
                                            <label><span class="required"> * </span>模块资源:</label>
                                          	<c:forEach var="resource" items="${allResources }">
                                          		<c:set var="resourceIdSame" value="false"/>
                                          		<c:forEach var="_resource" items="${module.resources }">
                                          			<c:if test="${resource.resourceId == _resource.resourceId }">
                                          				<c:set var="resourceIdSame" value="true"/>
                                          			</c:if>
	                                            </c:forEach>
	                                            <label class="checkbox-inline">
	                                                <input type="checkbox" name="resources" id="${resource.resourceId }" <c:if test="${resourceIdSame==true }">checked</c:if> <c:if test="${resource.enabled==false }">disabled</c:if> value="${resource.resourceId }" > ${resource.resourceName }
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
	<script src="${contextPath}/static/assets/pages/system/module/FormValid.js" type="text/javascript"></script>
	<script type="text/javascript">
  	jQuery(document).ready(function() { 
  		FormValid.init();
  	});
  	</script>
</body>

</html>
