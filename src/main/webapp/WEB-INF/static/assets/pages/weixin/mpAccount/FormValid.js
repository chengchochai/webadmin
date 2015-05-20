var FormValid = function() {

	var handleLogin = function() {

		$('#form').validate({
			errorElement : 'p', // default input error message container
			errorClass : 'help-block', // default input error message class
			focusInvalid : false, // do not focus the last invalid input
			debug : true,
			rules : {
				accountName : {
					required : true,
				},
				appId:{
					required:true,
					remote : { 
						url : 'checkAppId/',
						type : "POST"
					}
				},
				appSecret:{
					required:true
				},
				appToken : {
					required : true
				}
			},

			messages : {
				accountName : {
					required : "公众账号名称不能为空"
				},
				appId:{
					required : "appId不能为空",
					remote : "appId已经存在"
				},
				appSecret:{
					required : "appSecret不能为空"
				},
				appToken : {
					required : "appToken不能为空"
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