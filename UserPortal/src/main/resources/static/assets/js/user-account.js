jQuery(document).ready(function($) {
	function loadUserInf(){
		$.ajax({
			url: '/api/user-account/all',
			type: 'GET',
			dataType: 'JSON',
			success: function(data){
				console.log(data);
				var html = '';
				$.each(data, function(i, item) {
					html += "<tr>";
					html += '<td>' + item.username + '</td>';
					html += '<td>' + item.enabled + '</td>';
					html += '<td>' + item.accountNonExpired + '</td>';
					html += '<td>' + item.credentialsNonExpired + '</td>';
					html += '<td>' + item.accountNonLocked + '</td>';
					html += '<td>';
					var roles = item.roles;
					console.log(roles);
					$.each(roles, function(i, role){
						html += role.name + "<br>";
					})
					html += '</td>';
					html += '<td>' + 
				    			'<input type="hidden" class="id" value="' + item.id + '"/>' +
				    			'<span class="icon-detail-user" data-toggle="modal" data-target="#modalDetail"><i class="fa fa-tags" data-toggle="tooltip" data-placement="top" title="Chi tiết"></i></span> | ' + 
				    			'<span class="icon-update-user" data-toggle="modal" data-target="#modalEdit"><i class="fa fa-wrench text-primary" data-toggle="tooltip" data-placement="top" title="Chỉnh sửa"></i></span> | ' + 
					        	'<span class="icon-delete-user" data-toggle="modal" data-target="#modalDelete"><i class="fa fa-times text-danger" data-toggle="tooltip" data-placement="top" title="Xóa"></i></span>' +
					        '</td>';
					html += '</tr>';
				})
				$('#table-account tbody').html(html);
			},
			error: function (e) {
	            console.log(e);
	        }
		})
	}
	$.when(
		loadUserInf(),
		$.ajax({
			url: '/api/role/all',
			type: 'GET',
			dataType: 'JSON',
			success: function(data){
				console.log(data);
				var htmlTableAddRole = '';
				var htnmTableEditRole = '';
				$.each(data, function(i, item){
					htmlTableAddRole += '<tr>';
					htmlTableAddRole += '<td><input class="add-role" type="checkbox" value="' + item.id + '"></td>';
					htmlTableAddRole += '<td>' + item.name + '</td>';
					htmlTableAddRole += '<td>';
					var permissions = item.permissions;
					console.log(permissions);
					$.each(permissions, function(i, permission){
						htmlTableAddRole += permission.name + "&emsp;";
					})
					htmlTableAddRole += '</td>';
					
					htnmTableEditRole += '<tr>';
					htnmTableEditRole += '<td><input class="edit-role" type="checkbox" value="' + item.id + '"></td>';
					htnmTableEditRole += '<td>' + item.name + '</td>';
					htnmTableEditRole += '<td>';
					var permissions = item.permissions;
					console.log(permissions);
					$.each(permissions, function(i, permission){
						htnmTableEditRole += permission.name + "&emsp;";
					})
					htnmTableEditRole += '</td>';
				})
				$('#table-add-role tbody').html(htmlTableAddRole);
				$('#table-edit-role tbody').html(htnmTableEditRole);
			},
			error: function(e){
				console.log(e);
			}
		})
	).then(function(){
		$(".add-all").click(function(){
	        $("input[class=add-role]").prop('checked', $(this).prop('checked'));
		});
		$(".edit-all").click(function(){
	        $("input[class=edit-role]").prop('checked', $(this).prop('checked'));
		});
		
		$('.add-role').click(function(){
			var total = $('.add-role').length;
			var checked = $('input[class="add-role"]:checked').length;
			if(checked == total){
				$("input[class=add-all]").prop('checked', true);
			} else {
				$("input[class=add-all]").prop('checked', false);
			}
		});
		
		$('.edit-role').click(function(){
			var total = $('.edit-role').length;
			var checked = $('input[class="edit-role"]:checked').length;
			if(checked == total){
				$("input[class=edit-all]").prop('checked', true);
			} else {
				$("input[class=edit-all]").prop('checked', false);
			}
		});
		
		$(".register-user").click(function(){
			var user = {};
			user.username = $('.username').val().trim();
			user.password = $('.password').val();
			user.enabled = $('input[name="enabled"]:checked').val()==1?true:false;
			user.accountNonExpired = $('input[name="expired"]:checked').val()==1?true:false;
			user.credentialsNonExpired = $('input[name="credentials-expired"]:checked').val()==1?true:false;
			user.accountNonLocked = $('input[name="locked"]:checked').val()==1?true:false;
			
			user.firstName = $('.first-name').val();
			user.lastName = $('.last-name').val();
			user.birthday = $('.day').val() + "/" + $('.month').val() + "/" + $('.year').val();
			user.gender = $('input[name="gender"]:checked').val();
			user.phoneNumber = $('.phonenumber').val();
			user.email = $('.email').val();
			
			var roles = [];
			$('.add-role:checked').each(function(){
				var role = {};
				role.id = $(this).val();
				roles.push(role);
			});
			user.roles = roles
			console.log(user);
			$.ajax({
				url: '/api/user-account/add',
				type: 'POST',
				dataType: 'JSON',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(user),
				success: function(data){
					console.log(data);
					if(data != null){
						loadUserInf();
						$('.notification').html("Đăng ký thành công");
					} 
					else {
						$('.notification').html("Đăng ký thất bại");
					}
				}, 
				error: function(e){
					console.log(e);
				}
			})
		});
		
		$(document).on("click", ".icon-detail-user", function () {
			var id = $(this).closest('tr').find('.id').val();
			var url = '/api/user-account/id/' + id;
			$.ajax({
				url: url,
				type: 'GET',
				dataType: 'JSON',
				success: function(data){
					console.log(data);
					$('.detail-username').val(data.username);
					$("input[name=detail-enabled][value=" + (data.enabled==true?"1":"0") + "]").prop('checked', true);
					$("input[name=detail-expired][value=" + (data.enabled==true?"1":"0") + "]").prop('checked', true);
					$("input[name=detail-credentials-expired][value=" + (data.enabled==true?"1":"0") + "]").prop('checked', true);
					$("input[name=detail-locked][value=" + (data.enabled==true?"1":"0") + "]").prop('checked', true);
					
					$('.detail-first-name').val(data.firstName);
					$('.detail-last-name').val(data.lastName);
					var arr = data.birthday.split("/");
					$('.detail-day').val(arr[0]);
					$('.detail-month').val(arr[1]);
					$('.detail-year').val(arr[2]);
					$("input[name=detail-gender][value=" + data.gender + "]").prop('checked', true);
					$('.detail-phonenumber').val(data.phoneNumber);
					$('.detail-email').val(data.email);
					
					var html = '';
					$.each(data.roles, function(i, role){
						html += '<tr>';
						html += '<td>' + role.name + '</td>';
						html += '<td>';
						$.each(role.permissions, function(i, permission){
							html += permission.name + "&emsp;";
						})
						html += '</td>';
					})
					$('#table-detail tbody').html(html);
				},
				error: function(e){
					console.log(e);
				}
			})
        });
		
		$(document).on("click", ".icon-update-user", function () {
			$('#table-edit-role .edit-role:checkbox').removeAttr('checked');
			var id = $(this).closest('tr').find('.id').val();
			$('.id_user_select').val(id);
			var url = '/api/user-account/id/' + id;
			$.ajax({
				url: url,
				type: 'GET',
				dataType: 'JSON',
				success: function(data){
					console.log(data);
					$('.edit-username').val(data.username);
					$("input[name=edit-enabled][value=" + (data.enabled==true?"1":"0") + "]").prop('checked', true);
					$("input[name=edit-expired][value=" + (data.enabled==true?"1":"0") + "]").prop('checked', true);
					$("input[name=edit-credentials-expired][value=" + (data.enabled==true?"1":"0") + "]").prop('checked', true);
					$("input[name=edit-locked][value=" + (data.enabled==true?"1":"0") + "]").prop('checked', true);
					
					$('.edit-first-name').val(data.firstName);
					$('.edit-last-name').val(data.lastName);
					var arr = data.birthday.split("/");
					$('.edit-day').val(arr[0]);
					$('.edit-month').val(arr[1]);
					$('.edit-year').val(arr[2]);
					$("input[name=edit-gender][value=" + data.gender + "]").prop('checked', true);
					$('.edit-phonenumber').val(data.phoneNumber);
					$('.edit-email').val(data.email);
					
					$.each(data.roles, function(i, role){
						$('#table-edit-role .edit-role[value="' + role.id + '"]').prop('checked', true);
					})
				},
				error: function(e){
					console.log(e);
				}
			})
        });
		
		$('.update-user').click(function(){
			var user = {};
			user.id = $('.id_user_select').val();
			user.username = $('.edit-username').val().trim();
			user.password = $('.edit-password').val();
			user.enabled = $('input[name="edit-enabled"]:checked').val()==1?true:false;
			user.accountNonExpired = $('input[name="edit-expired"]:checked').val()==1?true:false;
			user.credentialsNonExpired = $('input[name="edit-credentials-expired"]:checked').val()==1?true:false;
			user.accountNonLocked = $('input[name="edit-locked"]:checked').val()==1?true:false;
			
			user.firstName = $('.edit-first-name').val();
			user.lastName = $('.edit-last-name').val();
			user.birthday = $('.edit-day').val() + "/" + $('.edit-month').val() + "/" + $('.edit-year').val();
			user.gender = $('input[name="edit-gender"]:checked').val();
			user.phoneNumber = $('.edit-phonenumber').val();
			user.email = $('.edit-email').val();
			
			var roles = [];
			$('.edit-role:checked').each(function(){
				var role = {};
				role.id = $(this).val();
				roles.push(role);
			});
			user.roles = roles
			console.log(user);
			$.ajax({
				url: '/api/user-account/update',
				type: 'PUT',
				dataType: 'JSON',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(user),
				success: function(data){
					console.log(data);
					if(data != null){
						loadUserInf();
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
		
		$(document).on("click", ".icon-delete-user", function () {
			var id = $(this).closest('tr').find('.id').val();
			$('.id_user_select').val(id);
        });
		
		$(".delete-user").click(function () {
            var id = $('.id_user_select').val();
            var url = "/api/user-account/delete/" + id;
            $.ajax({
            	url: url,
            	type: 'DELETE',
            	success: function(data){
					console.log(data);
					if(data != null){
						loadUserInf();
						$('.notification').html("Xóa thành công");
					} 
					else {
						$('.notification').html("Xóa thất bại");
					}
				}, 
				error: function(e){
					console.log(e);
				}
            })
        });
		
		
		$('#search-user').keyup(function() {
            var value = $(this).val().toLowerCase();
            $("#table-account tbody tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
	})
});