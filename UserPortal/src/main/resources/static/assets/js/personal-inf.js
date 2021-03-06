jQuery(document).ready(function($) {
	function loadData(){
		$.ajax({
			url: '/api/personal-inf',
			type: 'GET',
			dataType: 'JSON',
			success: function(data){
				console.log(data);
				$('.firstName').text(data.firstName + " ");
				$('.lastName').text(data.lastName);
				$('.birthday').text(data.birthday);
				$('.gender').text(data.gender==1?"Nữ":"Nam");
				$('.password').text("***************");
				$('.email').text(data.email);
				$('.phoneNumber').text(data.phoneNumber);
				
			},
			error: function (e) {
	            console.log(e);
	        }
		})
	}
	$.when(
		loadData()
	).then(function(){
		$('.row-fullname').click(function(){
			$('.edit-firstName').val($('.firstName').text().trim());
			$('.edit-lastName').val($('.lastName').text());
		});
		
		$('.row-birthday').click(function(){
			var arr = $('.birthday').text().split("/");
			$('.edit-day').val(arr[0]);
			$('.edit-month').val(arr[1]);
			$('.edit-year').val(arr[2]);
		});
		
		$('.row-gender').click(function(){
			var value = $('.gender').text();
			if (value == "Nam"){
				$('.edit-gender').val(0);
			} else if (value == "Nữ"){
				$('.edit-gender').val(1);
			} else {
				$('.edit-gender').val(2);
			}
			
		});
		
		$('.row-email').click(function(){
			$('.edit-email').val($('.email').text());			
		});
		
		$('.row-phonenumber').click(function(){
			$('.edit-phonenumber').val($('.phoneNumber').text());
		});
		
		$('.btn-update-fullname').click(function(){
			$.ajax({
				url: '/api/update/fullname',
				type: 'GET',
				dataType: 'JSON',
				data: {
					firstName: $('.edit-firstName').val(),
					lastName: $('.edit-lastName').val()
				},
				success: function(data){
					console.log(data);
					if(data != null){
						loadData();
						$('.notification').html("Cập nhật thành công");
					} 
					else {
						$('.notification').html("Cập nhật thất bại");
					}
				},
				error: function(e){
					console.log(e);
				}
			})
		});
		
		$('.btn-update-birthday').click(function(){
			var birthday = $('.edit-day').val() + "/" + $('.edit-month').val() + "/" + $('.edit-year').val();
			$.ajax({
				url: '/api/update/birthday',
				type: 'GET',
				dataType: 'JSON',
				data: {
					birthday: birthday
				},
				success: function(data){
					console.log(data);
					if(data != null){
						loadData();
						$('.notification').html("Cập nhật thành công");
					} 
					else {
						$('.notification').html("Cập nhật thất bại");
					}
				},
				error: function(e){
					console.log(e);
				}
			})
		});
		
		$('.btn-update-gender').click(function(){
			$.ajax({
				url: '/api/update/gender',
				type: 'GET',
				dataType: 'JSON',
				data: {
					gender: $('.edit-gender').val()
				},
				success: function(data){
					console.log(data);
					if(data != null){
						loadData();
						$('.notification').html("Cập nhật thành công");
					} 
					else {
						$('.notification').html("Cập nhật thất bại");
					}
				},
				error: function(e){
					console.log(e);
				}
			})
		});
		
		$('.btn-update-email').click(function(){
			$.ajax({
				url: '/api/update/email',
				type: 'GET',
				dataType: 'JSON',
				data: {
					email: $('.edit-email').val()
				},
				success: function(data){
					console.log(data);
					if(data != null){
						loadData();
						$('.notification').html("Cập nhật thành công");
					} 
					else {
						$('.notification').html("Cập nhật thất bại");
					}
				},
				error: function(e){
					console.log(e);
				}
			})
		});
		
		$('.btn-update-phonenumber').click(function(){
			$.ajax({
				url: '/api/update/phonenumber',
				type: 'GET',
				dataType: 'JSON',
				data: {
					phoneNumber: $('.edit-phonenumber').val()
				},
				success: function(data){
					console.log(data);
					if(data != null){
						loadData();
						$('.notification').html("Cập nhật thành công");
					} 
					else {
						$('.notification').html("Cập nhật thất bại");
					}
				},
				error: function(e){
					console.log(e);
				}
			})
		});
		
		$('.ex-password').blur(function(){
			$.ajax({
				url: '/api/check-password',
				type: 'GET',
				dataType: 'JSON',
				data: {
					exPassword: $('.ex-password').val()
				},
				success: function(data){
					if(data == true){
						$('.notify-ex-password').html("");
						$('.note-ex-password').css("height","0px");
					} 
					else {
						$('.notify-ex-password').html("Mật khẩu không chính xác");
						$('.note-ex-password').css("height","24px");
					}
				},
				error: function(e){
					console.log(e);
				}
			})
		});
		
		$('.btn-update-password').click(function(){
			$.ajax({
				url: '/api/update/password',
				type: 'GET',
				dataType: 'JSON',
				data: {
					exPassword: $('.ex-password').val(),
					newPassword: $('.edit-password').val()
				},
				success: function(data){
					if(data == true){
						$('.notify-ex-password').html("");
						$('.note-ex-password').css("height","0px");
						$('.notification').html("Cập nhật thành công");
						$('#modalChangePassword').modal('toggle');
						$('#thong-bao').modal('toggle');
					} 
					else {
						$('.notify-ex-password').html("Mật khẩu không chính xác");
						$('.note-ex-password').css("height","24px");
					}
				}, 
				error: function(e){
					console.log(e);
				}
			})
		});
		
//		$(".delete-user").click(function () {
//            var id = $(this).closest('tr').find('.id').val();
//            $('#id-user-selected').val(id);
//        });
	})
});