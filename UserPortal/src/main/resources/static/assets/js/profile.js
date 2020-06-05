jQuery(document).ready(function($) {
	function loadData(){
		$.ajax({
			url: '/api/profile',
			type: 'GET',
			dataType: 'JSON',
			success: function(data){
				console.log(data);
				$('.fullname').text(data.fullName.fullName);
				$('.input-flag-fullname').val(data.fullName.flag);
				$('.icon-flag-fullname').html(data.fullName.flag==0?'<i class="fa fa-lock text-primary"></i>':'<i class="fa fa-users text-primary"></i>')
				
				$('.birthday').text(data.birthday.birthday);
				$('.input-flag-birthday').val(data.birthday.flag);
				$('.icon-flag-birthday').html(data.birthday.flag==0?'<i class="fa fa-lock text-primary"></i>':'<i class="fa fa-users text-primary"></i>')
				
				$('.gender').text(data.gender.gender==1?"Nữ":"Nam");
				$('.input-flag-gender').val(data.gender.flag);
				$('.icon-flag-gender').html(data.gender.flag==0?'<i class="fa fa-lock text-primary"></i>':'<i class="fa fa-users text-primary"></i>')
				
				$('.email').text(data.email.email);
				$('.input-flag-email').val(data.email.flag);
				$('.icon-flag-email').html(data.email.flag==0?'<i class="fa fa-lock text-primary"></i>':'<i class="fa fa-users text-primary"></i>')
				
				$('.phonenumber').text(data.phoneNumber.phoneNumber);
				$('.input-flag-phonenumber').val(data.phoneNumber.flag);
				$('.icon-flag-phonenumber').html(data.phoneNumber.flag==0?'<i class="fa fa-lock text-primary"></i>':'<i class="fa fa-users text-primary"></i>')
				
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
			$('.edit-fullname').val($('.fullname').text().trim());
			$("input[name=flag-fullname][value=" + $('.input-flag-fullname').val() + "]").prop('checked', true);
		});
		
		$('.row-birthday').click(function(){
			var arr = $('.birthday').text().split("/");
			$('.edit-day').val(arr[0]);
			$('.edit-month').val(arr[1]);
			$('.edit-year').val(arr[2]);
			$("input[name=flag-birthday][value=" + $('.input-flag-birthday').val() + "]").prop('checked', true);
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
			$("input[name=flag-gender][value=" + $('.input-flag-gender').val() + "]").prop('checked', true);
		});
		
		$('.row-email').click(function(){
			$('.edit-email').val($('.email').text());
			$("input[name=flag-email][value=" + $('.input-flag-email').val() + "]").prop('checked', true);
		});
		
		$('.row-phonenumber').click(function(){
			$('.edit-phonenumber').val($('.phonenumber').text());
			$("input[name=flag-phonenumber][value=" + $('.input-flag-phonenumber').val() + "]").prop('checked', true);
		});
		
		$('.btn-update-fullname').click(function(){
			$.ajax({
				url: '/api/inf-share/update/fullname',
				type: 'GET',
				dataType: 'JSON',
				data: {
					fullName: $('.edit-fullname').val(),
					flag: $('input[name="flag-fullname"]:checked').val()
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
				url: '/api/inf-share/update/birthday',
				type: 'GET',
				dataType: 'JSON',
				data: {
					birthday: birthday,
					flag: $('input[name="flag-birthday"]:checked').val()
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
				url: '/api/inf-share/update/gender',
				type: 'GET',
				dataType: 'JSON',
				data: {
					gender: $('.edit-gender').val(),
					flag: $('input[name="flag-gender"]:checked').val()
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
				url: '/api/inf-share/update/email',
				type: 'GET',
				dataType: 'JSON',
				data: {
					email: $('.edit-email').val(),
					flag: $('input[name="flag-email"]:checked').val()
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
				url: '/api/inf-share/update/phonenumber',
				type: 'GET',
				dataType: 'JSON',
				data: {
					phoneNumber: $('.edit-phonenumber').val(),
					flag: $('input[name="flag-phonenumber"]:checked').val()
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
		
	})
});