jQuery(document).ready(function($) {
	function loadScopeInf(){
		$.ajax({
			url: '/api/scope/all',
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
				    			'<span class="icon-update-scope" data-toggle="modal" data-target="#modalEdit"><i class="fa fa-wrench text-primary" data-toggle="tooltip" data-placement="top" title="Chỉnh sửa"></i></span> | ' + 
					        	'<span class="icon-delete-scope" data-toggle="modal" data-target="#modalDelete"><i class="fa fa-times text-danger" data-toggle="tooltip" data-placement="top" title="Xóa"></i></span>' +
					        '</td>';
					html += '</tr>';
				})
				$('#table-scope tbody').html(html);
			},
			error: function (e) {
	            console.log(e);
	        }
		})
	}
	$.when(
			loadScopeInf()
	).then(function(){
		$(".register-scope").click(function(){
			var scope = {};
			scope.name = $('.add-name').val().trim();
			console.log(scope);
			$.ajax({
				url: '/api/scope/add',
				type: 'POST',
				dataType: 'JSON',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(scope),
				success: function(data){
					console.log(data);
					if(data != null){
						loadScopeInf();
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
		
		$(document).on("click", ".icon-update-scope", function () {
			var id = $(this).closest('tr').find('.id').val();
			$('.id_select').val(id);
			var url = '/api/scope/id/' + id;
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
		
		$('.update-scope').click(function(){
			var scope = {};
			scope.id = $('.id_select').val();
			scope.name = $('.edit-name').val().trim();
			console.log(scope);
			$.ajax({
				url: '/api/scope/update',
				type: 'PUT',
				dataType: 'JSON',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(scope),
				success: function(data){
					console.log(data);
					if(data != null){
						loadScopeInf();
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
		
		$(document).on("click", ".icon-delete-scope", function () {
			var id = $(this).closest('tr').find('.id').val();
			$('.id_select').val(id);
        });
		
		$(".delete-scope").click(function () {
            var id = $('.id_select').val();
            var url = "/api/scope/delete/" + id;
            $.ajax({
            	url: url,
            	type: 'DELETE',
            	success: function(data){
					console.log(data);
					if(data != null){
						loadScopeInf();
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
		
		
		$('#search-scope').keyup(function() {
            var value = $(this).val().toLowerCase();
            $("#table-scope tbody tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
	})
});