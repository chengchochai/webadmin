var FormValid = function() {

	var handleLogin = function() {

		$('#form').validate({
			errorElement : 'p', // default input error message container
			errorClass : 'help-block', // default input error message class
			focusInvalid : false, // do not focus the last invalid input
			debug : true,
			rules : {
				loginName : {
					required : true,
					remote : {                     
						type : "POST"
					}
				},
				loginPass : {
					required : true,
					minlength:6
				},
				realName : {
					required : true
				},
				email : {
					required : true,
					email : true
				},
				mobile : {
					required : true
				},
				sex : {
					required : true
				},
				enabled : {
					required : true
				},
				roles : {
					required : true
				}
			},

			messages : {
				loginName : {
					required : "登录账号不能为空"
				},
				loginPass : {
					required : "登录密码不能为空",
					minlength: "密码不能低于{0}位"
				},
				realName : {
					required : "真实姓名不能为空"
				},
				email : {
					required : "邮箱不能为空",
					email : "请输入一个邮箱地址"
				},
				mobile : {
					required : "手机不能为空"
				},
				sex : {
					required : "请选择性别",
				},
				enabled : {
					required : "请选择用户状态",
				},
				roles : {
					required : "用户角色不能为空"
				}
			},

			invalidHandler : function(event, validator) { // display error
			},

			highlight : function(element) { // hightlight error inputs
				$(element).closest('.form-group').addClass('has-error'); // set
				// class
				// to
				// the
				// control
				// group
			},

			success : function(element) {
				element.closest('.form-group').removeClass('has-error');
				element.closest('.form-group').addClass('has-success');
			},

			errorPlacement : function(error, element) {
				if(element.attr("type")=="checkbox" || element.attr("type")=="radio"){
					error.appendTo(element.parent().parent());
				}else{
					error.appendTo(element.parent());
				}
			},

			submitHandler : function(form) {
				form.submit(); // form validation success, call ajax form submit
			}
		});

		$('.login-form input').keypress(function(e) {
			if (e.which == 13) {
				if ($('#login-form').validate().form()) {
					$('#login-form').submit(); // form validation success, call
					// ajax form submit
				}
				return false;
			}
		});
	};

	return {
		// main function to initiate the module
		init : function() {
			handleLogin();
		}
	};
}();