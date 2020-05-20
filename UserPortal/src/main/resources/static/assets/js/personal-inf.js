jQuery(document).ready(function($) {
	$.when(
		$.ajax({
			url: '/api/personal-inf',
			type: 'GET',
			dataType: 'JSON',
			success: function(data){
				console.log(data);
				$('.firstName').text(data.firstName + " ");
				$('.lastName').text(data.lastName);
				$('.birthday').text(data.birthday);
				$('.gender').text(data.gender);
				$('.password').text("***************");
				$('.email').text(data.email);
				$('.phoneNumber').text(data.phoneNumber);
				
			},
			error: function (e) {
	            console.log(e);
	        }
		})
	).then(function(){
		
		
//		$(".delete-user").click(function () {
//            var id = $(this).closest('tr').find('.id').val();
//            $('#id-user-selected').val(id);
//        });
	})
});