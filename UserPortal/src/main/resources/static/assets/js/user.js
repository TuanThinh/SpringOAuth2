jQuery(document).ready(function($) {
	function generateHtmlData(data){
		var html = '';
		$.each(data, function(i, item){
			var roles = item.roles;
			html += '<tr>';
			html += '<td class="firstname" style="display: none;">' + item.firstName + ' ' + item.lastName + '</td>';
			html += '<td class="username">' + item.username + '</td>';
			html += '<td class="rolename">';
			for(var i = 0; i < roles.length; i++){
				if(i == roles.length - 1){
					html += roles[i].rolename;
				} else {
					html += roles[i].rolename + ', ';
				}
			}
			html += '</td>';
		    html += '<td><input class="enable" type="checkbox" ' + (item.enabled==true?"checked":"") + ' disabled></td>';
		    //html += '<td><button class="grantrole btn btn-danger")>Role</button></td>';
		    html += '<td>' + 
		    			'<input type="hidden" class="id" value="' + item.id + '"/>' +
//		    			'<span data-toggle="modal" data-target="#modalDetail"><i class="fa fa-eye detail-user" data-toggle="tooltip" title="Chi tiết"></i></span> | ' + 
		    			'<span data-toggle="modal" data-target="#modalEdit"><i class="fa fa-pencil-square-o update-user" data-toggle="tooltip" title="Chỉnh sửa"></i></span> | ' + 
		    			'<span data-toggle="modal" data-target="#modalDelete"><i class="fa fa-trash-o delete-user" data-toggle="tooltip" title="Xóa"></i></span>' +
		        		'</td>';
		    html += '</tr>';
		});
		$('#table-user tbody').html(html);
	}
	$.when(
		$.ajax({
			url: '/user/all',
			type: 'GET',
			dataType: 'JSON',
			success: function(data){
				var availableTutorials = [];
		    	  for(var i in data){
		    		  availableTutorials.push(data[i].username);
		    	  }
		    	  console.log(availableTutorials);
		          $( "#search" ).autocomplete({
		             source: availableTutorials
		          });
				generateHtmlData(data);
			},
			error: function (e) {
	            console.log(e);
	        }
		}),
		
		$.ajax({
			url: '/role/all',
			type: 'GET',
			dataType: 'JSON',
			success: function(data){
				var html = '';
				$.each(data, function(i, item){
					html += '<div class="form-check">';
					html += '<input class="form-check-input" type="checkbox" name="add-user-role" value="' + item.id + '">';
					html += '<label class="form-check-label">' + item.rolename +'</label>';
					html += '</div>';
				});
				$('.user-role').html(html);
			},
			error: function (e) {
	            console.log(e);
	        }
		})
	).then(function(){
		$("#btn-add-user").click(function(){
//			even.preventDefault();
			var data = {};
			var roles = [];
			data.username = $("#add-username").val();
			data.password = $("#add-password").val();
			$('#modalAdd input[name="add-user-role"]:checked').each(function() {
			   var role = {};
			   role.id = this.value;
			   roles.push(role);
			});
			data.roles = roles;
			data.enabled = $("#add-enabled").is(":checked")==true?true:false;
			console.log(data);
			$.ajax({
	            url: '/user/add',
	            type: 'POST',
	            contentType: 'application/json; charset=utf-8',
	            dataType: 'JSON',
	            data: JSON.stringify(data),
	            success: function (data) {
                    if (data != null){
                        alert("Thêm thành công user " + data.username);
                        location.reload();
                    } else {
                        alert("Thêm thất bại");
                    }
                },
                error: function (e) {
                    console.log(e);
                }
	        });
		});
		
		$("#btn-edit-user").click(function(even){
//			even.preventDefault();
			var data = {};
			var roles = [];
			var password = $("#edit-password").val();
			data.id = $("#id-user-selected").val();
			data.username = $("#edit-username").val();
			data.password = password.trim();
			$('#modalEdit input[name="add-user-role"]:checked').each(function() {
			   var role = {};
			   role.id = this.value;
			   roles.push(role);
			});
			data.roles = roles;
			data.enabled = $("#edit-enabled").is(":checked")==true?true:false;
			$.ajax({
	            url: '/user/edit',
	            type: 'PUT',
	            contentType: 'application/json; charset=utf-8',
	            dataType: 'JSON',
	            data: JSON.stringify(data),
	            success: function (data) {
                    if (data != null){
                        alert("Cập nhật thành công");
                        location.reload();
                    } else {
                        alert("Thêm thất bại");
                    }
                },
                error: function (e) {
                    console.log(e);
                }
	        });
		});
		
		$("#btn-delete-user").click(function(){
			var id = $("#id-user-selected").val();
			var url = '/user/delete/' + id;
			$.ajax({
	            url: url,
	            type: 'DELETE',
	            data: {
	            	id: id
	            },
	            success: function (check) {
                    if (check != null){
                        alert("Xóa thành công");
                        location.reload();
                    } else {
                        alert("Xóa thất bại");
                    }
                },
                error: function (e) {
                    console.log(e);
                }
	        });
		});
		
		$(".update-user").click(function () {
            var id = $(this).closest('tr').find('.id').val();
            $('input[name="add-user-role"]').prop("checked", false);
            var url = '/user/id/' + id;
            $.ajax({
	            url: url,
	            type: 'GET',
	            data: {
	            	id: id
	            },
	            success: function (data) {
	            	$('#id-user-selected').val(data.id);
	                $("#edit-username").val(data.username);
//	                $("#edit-password").val(password);
	                $("#edit-enabled").prop("checked", data.enabled);
	                
	                $.each(data.roles, function(i, item){
//	                	$("input[value='" + val + "']").prop('checked', true);
	                	$('#modalEdit input[name="add-user-role"][value="' + item.id + '"]').prop("checked", true);
	                })
                },
                error: function (e) {
                    console.log(e);
                }
	        });
        });
		
		$(".delete-user").click(function () {
            var id = $(this).closest('tr').find('.id').val();
            $('#id-user-selected').val(id);
        });
	})
});