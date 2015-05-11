var FormValid = function() {

	var handleLogin = function() {

		$('#form').validate({
			errorElement : 'p', // default input error message container
			errorClass : 'help-block', // default input error message class
			focusInvalid : false, // do not focus the last invalid input
			debug : true,
			rules : {
				roleName : {
					required : true
				},
				modules : {
					required : true
				}
			},

			messages : {
				roleName : {
					required : "角色名称不能为空"
				},
				modules : {
					required : "角色权限不能为空"
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
				if(element.attr("type")=="checkbox"){
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