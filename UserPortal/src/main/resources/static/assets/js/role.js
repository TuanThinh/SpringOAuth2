jQuery(document).ready(function($) {
	function loadRoleInf(){
		$.ajax({
			url: '/api/role/all',
			type: 'GET',
			dataType: 'JSON',
			success: function(data){
				console.log(data);
				var html = '';
				$.each(data, function(i, item) {
					html += "<tr>";
					html += '<td>' + item.name + '</td>';
					html += '<td>';
					var permissions = item.permissions;
					console.log(permissions);
					$.each(permissions, function(i, permission){
						html += permission.name + "&emsp;";
					})
					html += '</td>';
					html += '<td>' + 
				    			'<input type="hidden" class="id" value="' + item.id + '"/>' +
				    			'<span class="icon-detail-role" data-toggle="modal" data-target="#modalDetail"><i class="fa fa-tags" data-toggle="tooltip" data-placement="top" title="Chi tiết"></i></span> | ' + 
				    			'<span class="icon-update-role" data-toggle="modal" data-target="#modalEdit"><i class="fa fa-wrench text-primary" data-toggle="tooltip" data-placement="top" title="Chỉnh sửa"></i></span> | ' + 
					        	'<span class="icon-delete-role" data-toggle="modal" data-target="#modalDelete"><i class="fa fa-times text-danger" data-toggle="tooltip" data-placement="top" title="Xóa"></i></span>' +
					        '</td>';
					html += '</tr>';
				})
				$('#table-role tbody').html(html);
			},
			error: function (e) {
	            console.log(e);
	        }
		})
	}
	$.when(
		loadRoleInf(),
		$.ajax({
			url: '/api/permission/all',
			type: 'GET',
			dataType: 'JSON',
			success: function(data){
				console.log(data);
				var htmlTableAddPermission = '';
				var htnmTableEditPermission = '';
				$.each(data, function(i, item){
					htmlTableAddPermission += '<tr>';
					htmlTableAddPermission += '<td><input class="add-permission" type="checkbox" value="' + item.id + '"></td>';
					htmlTableAddPermission += '<td>' + item.name + '</td>';
					
					htnmTableEditPermission += '<tr>';
					htnmTableEditPermission += '<td><input class="edit-permission" type="checkbox" value="' + item.id + '"></td>';
					htnmTableEditPermission += '<td>' + item.name + '</td>';
					
				})
				$('#table-add-permission tbody').html(htmlTableAddPermission);
				$('#table-edit-permission tbody').html(htnmTableEditPermission);
			},
			error: function(e){
				console.log(e);
			}
		})
	).then(function(){
		$(".add-all").click(function(){
	        $("input[class=add-permission]").prop('checked', $(this).prop('checked'));
		});
		$(".edit-all").click(function(){
	        $("input[class=edit-permission]").prop('checked', $(this).prop('checked'));
		});
		
		$('.add-permission').click(function(){
			var total = $('.add-permission').length;
			var checked = $('input[class="add-permission"]:checked').length;
			if(checked == total){
				$("input[class=add-all]").prop('checked', true);
			} else {
				$("input[class=add-all]").prop('checked', false);
			}
		});
		
		$('.edit-permission').click(function(){
			var total = $('.edit-permission').length;
			var checked = $('input[class="edit-permission"]:checked').length;
			if(checked == total){
				$("input[class=edit-all]").prop('checked', true);
			} else {
				$("input[class=edit-all]").prop('checked', false);
			}
		});
		
		$(".register-role").click(function(){
			var role = {};
			role.name = $('.role-name').val().trim();
			var permissions = [];
			$('.add-permission:checked').each(function(){
				var permission = {};
				permission.id = $(this).val();
				permissions.push(permission);
			});
			role.permissions = permissions
			console.log(role);
			$.ajax({
				url: '/api/role/add',
				type: 'POST',
				dataType: 'JSON',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(role),
				success: function(data){
					console.log(data);
					if(data != null){
						loadRoleInf();
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
		
		$(document).on("click", ".icon-detail-role", function () {
			var id = $(this).closest('tr').find('.id').val();
			var url = '/api/role/id/' + id;
			$.ajax({
				url: url,
				type: 'GET',
				dataType: 'JSON',
				success: function(data){
					console.log(data);
					$('.detail-role-name').val(data.name);
					
					var html = '';
					$.each(data.permissions, function(i, permission){
						html += permission.name + "&emsp;";
					})
					$('.detail-role-name').html(html);
				},
				error: function(e){
					console.log(e);
				}
			})
        });
		
		$(document).on("click", ".icon-update-role", function () {
			$('#table-edit-permission .edit-permission:checkbox').removeAttr('checked');
			var id = $(this).closest('tr').find('.id').val();
			$('.id_role_select').val(id);
			var url = '/api/role/id/' + id;
			$.ajax({
				url: url,
				type: 'GET',
				dataType: 'JSON',
				success: function(data){
					console.log(data);
					$('.edit-role-name').val(data.name);
					
					$.each(data.permissions, function(i, permission){
						$('#table-edit-permission .edit-permission[value="' + permission.id + '"]').prop('checked', true);
					})
				},
				error: function(e){
					console.log(e);
				}
			})
        });
		
		$('.update-role').click(function(){
			var role = {};
			role.id = $('.id_role_select').val();
			role.name = $('.edit-role-name').val().trim();
			var permissions = [];
			$('.edit-permission:checked').each(function(){
				var permission = {};
				permission.id = $(this).val();
				permissions.push(permission);
			});
			role.permissions = permissions
			console.log(role);
			$.ajax({
				url: '/api/role/update',
				type: 'PUT',
				dataType: 'JSON',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(role),
				success: function(data){
					console.log(data);
					if(data != null){
						loadRoleInf();
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
		
		$(document).on("click", ".icon-delete-role", function () {
			var id = $(this).closest('tr').find('.id').val();
			$('.id_role_select').val(id);
        });
		
		$(".delete-role").click(function () {
            var id = $('.id_role_select').val();
            var url = "/api/role/delete/" + id;
            $.ajax({
            	url: url,
            	type: 'DELETE',
            	success: function(data){
					console.log(data);
					if(data != null){
						loadRoleInf();
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
		
		
		$('#search-role').keyup(function() {
            var value = $(this).val().toLowerCase();
            $("#table-role tbody tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
	})
});