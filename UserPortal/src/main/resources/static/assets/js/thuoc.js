jQuery(document).ready(function($) {
	function generateHtmlData(data){
		var html = '';
		$.each(data, function(i, item){
			var roles = item.roles;
			html += '<tr>';
			html += '<td>' + item.tenThuoc + '</td>';
			html += '<td>' + item.chucNang + '</td>';
			html += '<td>' + item.ngaySanXuat + '</td>';
			html += '<td>' + item.hanSuDung + '</td>';
		    html += '<td>' + item.nhaCungCap.tenNCC + '</td>';
		    html += '<td>' + item.soLuongTon + '</td>';
		    //html += '<td><button class="grantrole btn btn-danger")>Role</button></td>';
		    html += '<td>' + 
		    			'<input type="hidden" class="id" value="' + item.id + '"/>' +
//		    			'<span data-toggle="modal" data-target="#modalDetail"><i class="fa fa-eye detail-user" data-toggle="tooltip" title="Chi tiết"></i></span> | ' + 
		    			'<span data-toggle="modal" data-target="#modalEdit"><i class="fa fa-pencil-square-o update-thuoc" data-toggle="tooltip" title="Chỉnh sửa"></i></span> | ' + 
		    			'<span data-toggle="modal" data-target="#modalDelete"><i class="fa fa-trash-o delete-thuoc" data-toggle="tooltip" title="Xóa"></i></span>' +
		        		'</td>';
		    html += '</tr>';
		});
		$('#table-thuoc tbody').html(html);
	}
	$.when(
		$.ajax({
			url: '/api/thuoc/all',
			type: 'GET',
			dataType: 'JSON',
			success: function(data){
				var availableTutorials = [];
		    	  for(var i in data){
		    		  availableTutorials.push(data[i].tenThuoc);
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
			url: '/api/nha-cung-cap/all',
			type: 'GET',
			dataType: 'JSON',
			success: function(data){
				var html = '';
				$.each(data, function (i, item) {
                    $('.select-nhacungcap').append($('<option>', {
                        value: item.id,
                        text: item.tenNCC
                    }))
                })
			},
			error: function (e) {
	            console.log(e);
	        }
		})
	).then(function(){
		$("#btn-add-thuoc").click(function(){
//			even.preventDefault();
			var thuoc = {};
			var nhaCungCap = {};
			thuoc.tenThuoc = $("#add-tenthuoc").val();
			thuoc.chucNang = $("#add-chucnang").val();
			thuoc.ngaySanXuat = $("#add-ngaysanxuat").val();
			thuoc.hanSuDung = $("#add-hansudung").val();
			thuoc.soLuongTon = $("#add-soluong").val();
			thuoc.ghiChu = $("#add-ghichu").val();
			
			nhaCungCap.id = $("#add-nhacungcap").val();
			thuoc.nhaCungCap = nhaCungCap;

			console.log(thuoc);
			$.ajax({
	            url: '/api/thuoc/add',
	            type: 'POST',
	            contentType: 'application/json; charset=utf-8',
	            dataType: 'JSON',
	            data: JSON.stringify(thuoc),
	            success: function (data) {
                    if (data != null){
                        alert("Thêm thành công ");
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
		
		$("#btn-edit-thuoc").click(function(even){
//			even.preventDefault();
			var thuoc = {};
			var nhaCungCap = {};
			thuoc.id = $("#id-thuoc-selected").val();
			thuoc.tenThuoc = $("#edit-tenthuoc").val();
			thuoc.chucNang = $("#edit-chucnang").val();
			thuoc.ngaySanXuat = $("#edit-ngaysanxuat").val();
			thuoc.hanSuDung = $("#edit-hansudung").val();
			thuoc.soLuongTon = $("#edit-soluong").val();
			thuoc.ghiChu = $("#edit-ghichu").val();
			
			nhaCungCap.id = $("#edit-nhacungcap").val();
			thuoc.nhaCungCap = nhaCungCap;

			console.log(thuoc);
			$.ajax({
	            url: '/api/thuoc/edit',
	            type: 'PUT',
	            contentType: 'application/json; charset=utf-8',
	            dataType: 'JSON',
	            data: JSON.stringify(thuoc),
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
		
		$("#btn-delete-thuoc").click(function(){
			var id = $("#id-thuoc-selected").val();
			var url = '/api/thuoc/delete/' + id;
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
		
		$(".update-thuoc").click(function () {
            var id = $(this).closest('tr').find('.id').val();
            var url = '/api/thuoc/id/' + id;
            $.ajax({
	            url: url,
	            type: 'GET',
	            data: {
	            	id: id
	            },
	            success: function (data) {
	            	$('#id-thuoc-selected').val(data.id);
	                $("#edit-tenthuoc").val(data.tenThuoc);
	                $("#edit-chucnang").val(data.chucNang);
	                $("#edit-ngaysanxuat").val(data.ngaySanXuat);
	                $("#edit-hansudung").val(data.hanSuDung);
	                $("#edit-soluong").val(data.soLuongTon);
	                $("#edit-nhacungcap").val(data.nhaCungCap.id);
	                $("#edit-ghichu").val(data.ghiChu);
                },
                error: function (e) {
                    console.log(e);
                }
	        });
        });
		
		$(".delete-thuoc").click(function () {
            var id = $(this).closest('tr').find('.id').val();
            $('#id-thuoc-selected').val(id);
        });
		
		$('#search').keyup(function() {
            var value = $(this).val().toLowerCase();
            $("#table-thuoc tbody tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
	})
});