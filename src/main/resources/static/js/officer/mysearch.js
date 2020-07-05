$(document).ready(function () {
	$("#schools-search").submit(function (event) {
		//stop submit the form, we will post it manually.
		event.preventDefault();
		$("#one").show();
		$("#two").hide();
		$("#studentinfo").hide();
		$("#schoolprofile").hide();	
		searchschools();

	});

});

function searchschools() {	
	var search = {}
	search["district"] = $("#districto").val();	
	search["combination"] = $("#combination").val();	
	$("#sss").prop("disabled", true);
	var counter;
	counter=0;
	$.ajax({
		global:false,
		type: "POST",
		contentType: "application/json",
		url: "api/search/bycombination",
		data: JSON.stringify(search),
		dataType: 'json',
		cache: false,
		timeout: 600000,
		beforeSend: function () {
			openModal();
		},
		complete: function () {
			closeModal();
		},
		success: function (data) {			

			var obj = data.result;   
			var combination=data.code;
			$('#results').DataTable(
					{
						"aaData": obj,
						"bSort": true,
						"autoWidth":false,
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
										data = '<a href="../validator/schools/edit?schoolcode=' + data + '">' + data + '</a>';
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
										data = '<a class="btn btn-info btn-xs" href="../validator/schools/edit?schoolcode=' + data + '"><span class="glyphicon glyphicon-check"></a>';
									}							            
									return data;
								}
							},
							{ "mData": "schoolcode",
								"render": function(data, type,row,meta){
									if(type === 'display'){
										data = '<a class="btn btn-success btn-xs" href="../validator/schools/edit?schoolcode=' + data + '"><span class="glyphicon glyphicon-check"></a>';
									}							            
									return data;
								}
							}]

					}			
			);
			var json =data.district;
			$('#district').html(json);
			var combi=data.combination;
			$('#combi').html(combi);
			console.log("SUCCESS : ", data);
			$("#sss").prop("disabled", false);
		},
		error: function (e) {

			var json = "<h4>System Response</h4><pre>"
				+ e.responseText + "</pre>";
			$('#feedback').html(json);

			console.log("ERROR : ", e);
			$("#sss").prop("disabled", false);

		}
	});

	counter++;

}

function getStudents(xdata) {	
	var search={}	
	var me=xdata.split(",");
	search["schoolcode"]=me[0]
	search["combination"]=me[1];	
	if(me[1]=="000"){
		showAllStudentsAndCombinations(me[0]);		
	}	
	else{
		$("#one").hide();
		$("#two").show();
		$("#studentinfo").hide();
		$("#schoolprofile").hide();	
		var counter;
		counter=0;
		$.ajax({
			global:false,
			type: "POST",
			contentType: "application/json",
			url: "api/studentssearch/bycombination",
			data: JSON.stringify(search),
			dataType: 'json',
			cache: false,
			timeout: 600000,
			success: function (data) {	
				var obj = data.result;  
				$('#advtable').DataTable(
						{
							"aaData": obj,
							"bSort": true,
							"autoWidth":false,
							destroy: true,
							"aoColumns": [
								{ "mData": null,
									render: function (data, type, row, meta) {
										return meta.row + meta.settings._iDisplayStart + 1;
									}
								},
								{ "mData": "studentNumber",
									"render": function(data, type,row,meta){
										var combination=me[1];
										if(type === 'display'){
											data = '<a href="javascript:void(0);"  onclick="return getStudentDetails(\''+data+','+combination+'\');"  title="Student Details" >'+data+'</a>';
										}							            
										return data;
									}
								},
								{ "mData": "surname" },
								{ "mData": "otherNames" },
								{ "mData": "gender" },
								{ "mData": "yearOfBirth" },	
								{ "mData": "yearOfBirth" }		
								]
						}					
				);
				var json =data.schoolname;
				$('#schoolname').html(json);	

				var coco =data.combination;
				$('#coco').html(coco);
				console.log("SUCCESS : ", data);			

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

}
function showAllStudentsAndCombinations(schoolcode){
	var search={};
	search["schoolcode"]=schoolcode;
	$("#one").hide();
	$("#two").hide();
	$("#studentinfo").hide();
	$("#schoolprofile").show();	
	var counter;
	counter=0;
	$.ajax({
		global:false,
		type: "POST",
		contentType: "application/json",
		url: "api/search/school",
		data: JSON.stringify(search),
		dataType: 'json',
		cache: false,
		timeout: 600000,
		success: function (data) {	
			var sections=data.options;
			$("#mycombination").get(0).options.length = 0;
			$("#mycombination").get(0).options[0] = new Option("Select combination", "-1"); 
			$.each(sections, function(index, item) {
				$("#mycombination").get(0).options[$("#mycombination").get(0).options.length] = new Option(item.combinationcode, item.combinationname);
			});

			var json=data.school.schoolname;
			console.log("Schoolname: ", json);
			$('#tete').html(json);	

			var jsoni=data.school.schoolcode;

			$('#itye').val(jsoni);	

			console.log("SUCCESS : ", data);	
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

function getStudentDetails(xdata) {	

	var search={}	
	var me=xdata.split(",");
	search["schoolcode"]=me[0]
	search["combination"]=me[1];	

	if(search["combination"]=="OLEVEL"){
		$("#eduka").hide();
		$("#shoyisi").show();
		$("#one").hide();
		$("#two").hide();
		$("#studentinfo").show();
		$("#schoolprofile").hide();	
		$.ajax({
			global:false,
			type: "POST",
			contentType: "application/json",
			url: "api/ordinarysearch/studentDetails",
			data: JSON.stringify(search),
			dataType: 'json',
			cache: false,
			timeout: 600000,
			success: function (data) {	
				var obj = data.result;   

				//Student tab
				var student=data.individual;
				var id =student.studentNumber;
				$('#studentnumber').html(id);

				var fname =student.surname;
				$('#studentname').html(fname);

				var lname=student.otherNames;
				$('#othername').html(lname);

				var gender=student.gender;
				$('#gender').html(gender);

				var age=student.yearOfBirth;
				$('#year').html(age);

				//Choices 

				var choiceone=data.choices[0];

				var onet=choiceone.type;
				$('#onetype').html(onet);

				var onen=choiceone.school;
				$('#onename').html(onen);

				var oned=choiceone.district;
				$('#onedistrict').html(oned);	


				var choicetwo=data.choices[1];

				var twot=choicetwo.type;
				$('#twotype').html(twot);

				var twon=choicetwo.school;
				$('#twoname').html(twon);

				var twod=choicetwo.district;
				$('#twodistrict').html(twod);


				//Parent tab
				var relation=data.details.parent.studentRelationship;
				if(relation=="PARENT"){
					var parent=data.details.parent;

					var dadone=data.details.parent.parentDetails.fatherSurname;
					$('#papaname').html(dadone);

					var dadtwo=data.details.parent.parentDetails.fatherOthername;
					$('#papaother').html(dadtwo);

					var momone=data.details.parent.parentDetails.motherSurname;
					$('#mamaname').html(momone);

					var momtwo=data.details.parent.parentDetails.motherOthername;
					$('#mamaother').html(momtwo);

					var natid=data.details.parent.parentDetails.parentNationalId;
					$('#indangamuntu').html(natid);

					var disy=data.address.district;
					$('#districtu').html(disy);

					var sec=data.address.sector;
					$('#sector').html(sec);

					var phoni=data.address.phoneNumber;
					$('#phone').html(phoni);

				}			
				var json =data.school.schoolname;
				var testme=data.school.schoolcode+","+me[1];	

				$('#myschoolname').html(json);
				$('#myschoolname').click(function(e){
					getStudents(testme);
				});
				console.log("SUCCESS : ", data);			

			},
			error: function (e) {
				var json = "<h4>System Response</h4><pre>"
					+ e.responseText + "</pre>";
				$('#feedback').html(json);
				console.log("ERROR : ", e.responseText);
				$("#btn-search").prop("disabled", false);
			}
		});
	}
	else{
		$("#eduka").show();
		$("#shoyisi").hide();
		$("#one").hide();
		$("#two").hide();
		$("#studentinfo").show();
		$("#schoolprofile").hide();	
		$.ajax({
			global:false,
			type: "POST",
			contentType: "application/json",
			url: "api/advancedsearch/studentDetails",
			data: JSON.stringify(search),
			dataType: 'json',
			cache: false,
			timeout: 600000,
			success: function (data) {	
				var obj = data.result;   

				//Student tab
				var student=data.individual;
				var id =student.studentNumber;
				$('#studentnumber').html(id);

				var fname =student.surname;
				$('#studentname').html(fname);

				var lname=student.otherNames;
				$('#othername').html(lname);

				var gender=student.gender;
				$('#gender').html(gender);

				var age=student.yearOfBirth;
				$('#year').html(age);


				//Parent tab
				var relation=data.parents.studentRelationship;
				if(relation=="PARENT"){
					var parent=data.parents.parentDetails;

					var dadone=data.parents.parentDetails.fatherSurname;
					$('#papaname').html(dadone);

					var dadtwo=data.parents.parentDetails.fatherOthername;
					$('#papaother').html(dadtwo);

					var momone=data.parents.parentDetails.motherSurname;
					$('#mamaname').html(momone);

					var momtwo=data.parents.parentDetails.motherOthername;
					$('#mamaother').html(momtwo);

					var natid=data.parents.parentDetails.parentNationalId;
					$('#indangamuntu').html(natid);
				}			
				var json =data.school.schoolcode+","+me[1];
				$('#myschoolname').html(json);
				$('#myschoolname').click(function(e){
					getStudents(json);
				});
				console.log("SUCCESS : ", data);			

			},
			error: function (e) {
				var json = "<h4>System Response</h4><pre>"
					+ e.responseText + "</pre>";
				$('#feedback').html(json);
				console.log("ERROR : ", e.responseText);
				$("#btn-search").prop("disabled", false);
			}
		});
	}
}

function openModal() {	
	//document.getElementById('results').innerHTML = '';
	document.getElementById('modal').style.display = 'block';
	//document.getElementById('fade').style.display = 'block';
}

function closeModal() {
	document.getElementById('modal').style.display = 'none';
	//document.getElementById('fade').style.display = 'none';
}

