<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/static/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<!-- css -->
<%@ include file="/WEB-INF/static/common/head.jsp"%>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Penn Admin 请登录</h3>
					</div>
					<div class="panel-body">
						<c:if test="${tip != null && tip != '' }">
							<div class="alert alert-danger alert-dismissable">
	                        	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	                        	${tip }
	                        </div>
                        </c:if>
						<form id="login-form" action="${contextPath}/login" method="post">
							<fieldset>
								<div class="form-group">
									<input type="text" class="form-control" placeholder="请输入账号" name="loginname" value="jipeng">
								</div>
								<div class="form-group">
									<input type="password" class="form-control" placeholder="请输入密码" name="password" value="123123">
								</div>
								<div class="checkbox">
									<label> <input name="remember" type="checkbox" value="1">记住我</label>
								</div>
								<!-- Change this to a button or input when using this as a form -->
								<button type="submit" class="btn btn-lg btn-success btn-block">登 &nbsp; &nbsp; 陆</button>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- javascript -->
  	<%@ include file="/WEB-INF/static/common/javascript.jsp"%>
  	<script src="${contextPath}/static/assets/bower_components/jqueryvalidation/dist/jquery.validate.min.js"></script>
  	<script src="${contextPath}/static/assets/pages/login.js"></script>
  	<script type="text/javascript">
  	jQuery(document).ready(function() { 
  		Login.init();
  	});
  	</script>
</body>
</html>
