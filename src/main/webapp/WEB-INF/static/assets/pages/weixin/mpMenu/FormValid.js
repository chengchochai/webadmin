var FormValid = function() {

	var handleLogin = function() {

		$('#form').validate({
			errorElement : 'p', // default input error message container
			errorClass : 'help-block', // default input error message class
			focusInvalid : false, // do not focus the last invalid input
			debug : true,
			rules : {
				mpMenuName : {
					required : true,
					maxlength : 7
				},
				priority : {
					required : true
				},
				mpMenuType : {
					required : true
				},
				mpMenuUrl : {
					url : true
				}
			},

			messages : {
				mpMenuName : {
					required : "菜单名称不能为空",
					maxlength : "一级菜单不能超过4个，二级菜单不能超过7个字符"
				},
				priority : {
					required : "显示级别不能为空"
				},
				mpMenuType : {
					required : "自定义菜单类型不能为空"
				},
				mpMenuUrl : {
					url : "需要输入一个URL"
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
				error.appendTo(element.parent());
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