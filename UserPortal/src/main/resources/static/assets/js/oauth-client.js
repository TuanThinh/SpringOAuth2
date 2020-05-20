jQuery(document).ready(function($) {
	function loadClientRegister(){
		$.ajax({
			url: '/api/client',
			type: 'GET',
			dataType: 'JSON',
			success: function(data){
				console.log(data);
				var html = '';
				$.each(data, function(i, item) {
					html += "<tr>";
					html += '<td>' + item.clientId + '</td>';
					html += '<td>****************</td>';
					html += '<td>' + item.redirectUris + '</td>';
					html += '<td>'
					var scopes = item.scopes;
					console.log(scopes);
					$.each(scopes, function(i, scope){
						html += scope.name + "<br>";
					})
					html += '</td>';
					html += '<td>' + 
				    			'<input type="hidden" class="id" value="' + item.id + '"/>' +
				    			'<span class="icon-update-client" data-toggle="modal" data-target="#modalEdit"><i class="fa fa-wrench text-primary" data-toggle="tooltip" data-placement="top" title="Chỉnh sửa"></i></span> | ' + 
					        	'<span class="icon-delete-client" data-toggle="modal" data-target="#modalDelete"><i class="fa fa-times text-danger" data-toggle="tooltip" data-placement="top" title="Xóa"></i></span>' +
					        '</td>';
					html += '</tr>';
				})
				$('#table-client tbody').html(html);
			},
			error: function (e) {
	            console.log(e);
	        }
		})
	}
	$.when(
		loadClientRegister(),
		$.ajax({
			url: '/api/scope/all',
			type: 'GET',
			dataType: 'JSON',
			success: function(data){
				console.log(data);
				var html = '';
				$.each(data, function(i, item){
					html += '<label class="checkbox-inline col-lg-4">';
					html += '<input class="mr-2 scope" type="checkbox" value="' + item.id + '">' + item.name;
					html += '</label>';
					
				})
				$('.check-scope').html(html);
			},
			error: function(e){
				console.log(e);
			}
		})
	).then(function(){
		
		$('.register-web').click(function(){
			var client = {};
			var scopes = [];
			client.clientId = $('.web-name').val();
			client.clientSecret = $('.web-code').val();
			client.redirectUris = $('.web-url').val();
			$('.web-scopes .scope:checked').each(function(){
				var scope = {}
				scope.id = $(this).val();
				scopes.push(scope);
			});
			client.scopes = scopes;
			console.log(client);
			$.ajax({
				url: '/api/client-register',
				type: 'POST',
				dataType: 'JSON',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(client),
				success: function(data){
					console.log(data);
					if(data != null){
						loadClientRegister();
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
		
		$(document).on("click", ".icon-update-client", function () {
			$('.edit-scopes .scope:checkbox').removeAttr('checked');
			var id = $(this).closest('tr').find('.id').val();
			var url = '/api/oauth-client/id/' + id;
			$.ajax({
				url: url,
				type: 'GET',
				dataType: 'JSON',
				success: function(data){
					console.log(data);
					$('.id_select').val(data.id);
					$('.edit-name').val(data.clientId);
					$('.edit-url').val(data.redirectUris);					
					var scopes = data.scopes;
					console.log(scopes);
					$.each(scopes, function(i, scope){
						$('.edit-scopes .scope[value="' + scope.id + '"]').prop('checked', true);
					})
				},
				error: function(e){
					console.log(e);
				}
			})
        });
		
		$('.update-client').click(function(){
			var client = {};
			var scopes = [];
			client.id = $('.id_select').val();
			client.clientId = $('.edit-name').val();
			client.clientSecret = $('.edit-code').val();
			client.redirectUris = $('.edit-url').val();
			$('.edit-scopes .scope:checked').each(function(){
				var scope = {}
				scope.id = $(this).val();
				scopes.push(scope);
			});
			client.scopes = scopes;
			console.log(client);
			$.ajax({
				url: '/api/client-update',
				type: 'PUT',
				dataType: 'JSON',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(client),
				success: function(data){
					console.log(data);
					if(data != null){
						loadClientRegister();
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
		
		$(document).on("click", ".icon-delete-client", function () {
			var id = $(this).closest('tr').find('.id').val();
			$('.id_select').val(id);
        });
		
		
		$(".delete-client").click(function () {
            var id = $('.id_select').val();
            var url = "/api/client-delete/" + id;
            $.ajax({
            	url: url,
            	type: 'DELETE',
            	success: function(data){
					console.log(data);
					if(data != null){
						loadClientRegister();
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
		
		
		$('#search-client').keyup(function() {
            var value = $(this).val().toLowerCase();
            $("#table-client tbody tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
	})
});