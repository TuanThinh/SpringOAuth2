jQuery(document).ready(function($) {
	function loadPermissionInf(){
		$.ajax({
			url: '/api/permission/all',
			type: 'GET',
			dataType: 'JSON',
			success: function(data){
				console.log(data);
				var html = '';
				$.each(data, function(i, item) {
					html += "<tr>";
					html += '<td>' + item.name + '</td>';
					html += '<td>' + 
				    			'<input type="hidden" class="id" value="' + item.id + '"/>' +
				    			'<span class="icon-update-permission" data-toggle="modal" data-target="#modalEdit"><i class="fa fa-wrench text-primary" data-toggle="tooltip" data-placement="top" title="Chỉnh sửa"></i></span> | ' + 
					        	'<span class="icon-delete-permission" data-toggle="modal" data-target="#modalDelete"><i class="fa fa-times text-danger" data-toggle="tooltip" data-placement="top" title="Xóa"></i></span>' +
					        '</td>';
					html += '</tr>';
				})
				$('#table-permission tbody').html(html);
			},
			error: function (e) {
	            console.log(e);
	        }
		})
	}
	$.when(
			loadPermissionInf()
	).then(function(){
		$(".register-permission").click(function(){
			var permission = {};
			permission.name = $('.add-name').val().trim();
			console.log(permission);
			$.ajax({
				url: '/api/permission/add',
				type: 'POST',
				dataType: 'JSON',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(permission),
				success: function(data){
					console.log(data);
					if(data != null){
						loadPermissionInf();
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
		
		$(document).on("click", ".icon-update-permission", function () {
			var id = $(this).closest('tr').find('.id').val();
			$('.id_select').val(id);
			var url = '/api/permission/id/' + id;
			$.ajax({
				url: url,
				type: 'GET',
				dataType: 'JSON',
				success: function(data){
					console.log(data);
					$('.edit-name').val(data.name);
				},
				error: function(e){
					console.log(e);
				}
			})
        });
		
		$('.update-permission').click(function(){
			var permission = {};
			permission.id = $('.id_select').val();
			permission.name = $('.edit-name').val().trim();
			console.log(permission);
			$.ajax({
				url: '/api/permission/update',
				type: 'PUT',
				dataType: 'JSON',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(permission),
				success: function(data){
					console.log(data);
					if(data != null){
						loadPermissionInf();
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
		
		$(document).on("click", ".icon-delete-permission", function () {
			var id = $(this).closest('tr').find('.id').val();
			$('.id_select').val(id);
        });
		
		$(".delete-permission").click(function () {
            var id = $('.id_select').val();
            var url = "/api/permission/delete/" + id;
            $.ajax({
            	url: url,
            	type: 'DELETE',
            	success: function(data){
					console.log(data);
					if(data != null){
						loadPermissionInf();
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
		
		
		$('#search-permission').keyup(function() {
            var value = $(this).val().toLowerCase();
            $("#table-permission tbody tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
	})
});