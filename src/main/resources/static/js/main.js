$(document).ready(function () {

	$("#search-form").submit(function (event) {

		//stop submit the form, we will post it manually.
		event.preventDefault();

		fire_ajax_submit();

	});

});

function fire_ajax_submit() {

	var search = {}
	search["username"] = $("#username").val();

	$("#btn-search").prop("disabled", true);
	var counter;
	counter=0;
	$.ajax({
		global:false,
		type: "POST",
		contentType: "application/json",
		url: "api/search",
		data: JSON.stringify(search),
		dataType: 'json',
		cache: false,
		timeout: 600000,
		success: function (data) {
			var obj = data.result;                                       
			$('#mitable').DataTable(
					{
						"aaData": obj,
						"bSort": true,
						destroy: true,
						"aoColumns": [
							{ "mData": null,
								render: function (data, type, row, meta) {
									return meta.row + meta.settings._iDisplayStart + 1;
								}
							},
							{ "mData": "schoolcode",
								"render": function(data, type,row,meta){
									if(type === 'display'){
										data = '<a href="../schools/edit?schoolcode=' + data + '">' + data + '</a>';
									}							            
									return data;
								}
							},

							{ "mData": "schoolname" },
							{ "mData": "htname" },
							{ "mData": "contact" },
							{ "mData": "schoolcode",
								"render": function(data, type,row,meta){
									if(type === 'display'){
										data = '<a class="btn btn-success btn-xs" href="../schools/edit?schoolcode=' + data + '"><span class="glyphicon glyphicon-check"></a>';
									}							            
									return data;
								}
							},
							{ "mData": "schoolcode",
								"render": function(data, type,row,meta){
									if(type === 'display'){
										data = '<a class="btn btn-info btn-xs" href="../schools/candidates?schoolcode=' + data + '"><i class="fa fa-file-text-o" style="font-size:16px;"></i></a>';
									}							            
									return data;
								}
							}]
					}					
			);
			var json =data.district;
			$('#district').html(json);

			console.log("SUCCESS : ", data);
			$("#btn-search").prop("disabled", false);

		},
		error: function (e) {

			var json = "<h4>System Response</h4><pre>"
				+ e.responseText + "</pre>";
			$('#feedback').html(json);

			console.log("ERROR : ", e);
			$("#btn-search").prop("disabled", false);

		}
	});

	counter++;

}