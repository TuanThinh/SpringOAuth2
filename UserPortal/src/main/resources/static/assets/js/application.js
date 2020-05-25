jQuery(document).ready(function($) {
	function loadClientApproval(){
		$.ajax({
			url: '/api/oauth-approval',
			type: 'GET',
			dataType: 'JSON',
			success: function(data){
				console.log(data);
				var html = '';
				$.each(data, function(i, item) {
					html += "<tr>";
					html += '<td>' + item.clientId + '</td>';
					html += '<td>'
					var scopes = item.token.scope;
					console.log(scopes);
					$.each(scopes, function(i, scope){
						html += scope + "<br>";
					})
					html += '</td>';
					
					var dateTime = new Date(item.token.expiration);
					console.log(dateTime.toLocaleString());
//					dateTime = moment(dateTime).format("YYYY-MM-DD HH:mm:ss");
//					console.log(dateTime);
					html += '<td>' + dateTime.toLocaleString() + '</td>';
					
					html += '<td>' + 
				    			'<input type="hidden" class="id" value="' + item.id + '"/>' + 
					        	'<span class="icon-delete-client" data-toggle="modal" data-target="#modalDelete"><i class="fa fa-times text-danger" data-toggle="tooltip" data-placement="top" title="Xóa"></i></span>' +
					        '</td>';
					html += '</tr>';
				})
				$('#table-approval tbody').html(html);
			},
			error: function (e) {
	            console.log(e);
	        }
		})
	}
	$.when(
		loadClientApproval()
	).then(function(){
		
		$(document).on("click", ".icon-delete-client", function () {
			var id = $(this).closest('tr').find('.id').val();
			$('.id_select').val(id);
        });
		
		
		$(".delete-client").click(function () {
            var id = $('.id_select').val();
            var url = "/api/disapproval/" + id;
            $.ajax({
            	url: url,
            	type: 'DELETE',
            	success: function(data){
					console.log(data);
					if(data != null){
						loadClientApproval();
						$('.notification').html("Hủy thành công");
					} 
					else {
						$('.notification').html("Hủy thất bại");
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