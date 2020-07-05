$(document).ready(function(){	
	$("#districto").change(function(){
		$('#restable').dataTable().fnClearTable();
		var search = {}
		search["district"] = $("#districto").val();	
		$.ajax({
			global:false,
			type: "POST",
			contentType: "application/json",
			url:"verification/schools",
			data:$("#districto").val(),
			dataType: 'json',
			cache: false,
			timeout: 600000,
			success:function(response){
				var len = response.result.length;
				$("#schools").empty();
				for( var i = 0; i<len; i++){
					var id = response.result[i]['schoolcode'];
					var name = response.result[i]['schoolcode']+"-"+response.result[i]['schoolname'];
					$("#schools").append("<option value='"+id+"'>"+name+"</option>");
				}
				console.log("SUCCESS : ", response);
			},
			error: function (e) {

				var json = "<h4>System Response</h4><pre>"
					+ e.responseText + "</pre>";
				$('#feedback').html(json);

				console.log("ERROR : ", e);
				$("#btn-search").prop("disabled", false);

			}
		});
	});
	//For gettig combinations
	$("#schools").change(function(){
		$('#restable').dataTable().fnClearTable();
		var search = {}
		search["district"] = $("#districto").val();
		$.ajax({
			global:false,
			type: "POST",
			contentType: "application/json",
			url: "verification/combinations",
			data:$("#schools").val(),
			dataType: 'json',
			cache: false,
			timeout: 600000,
			success:function(response){
				var len = response.options.length;
				$("#combinations").empty();
				for( var i = 0; i<len; i++){
					var id = response.options[i]['combinationcode'];
					var name = response.options[i]['combinationcode'];
					$("#combinations").append("<option value='"+id+"'>"+name+"</option>");
				}
				console.log("SUCCESS : ", response);
			},
			error: function (e) {

				var json = "<h4>System Response</h4><pre>"
					+ e.responseText + "</pre>";
				$('#feedback').html(json);

				console.log("ERROR : ", e);
				$("#btn-search").prop("disabled", false);

			}
		});
	});

	//Results sasa
	$("#combinations").change(function(){
		var search = {
				"combination": $("#combinations").val(),
				"schoolcode": $("#schools").val()
		};
		$.ajax({
			global:false,
			type: "POST",
			contentType: "application/json",
			url: "api/studentssearch/bycombination",
			data:JSON.stringify(search),
			dataType: 'json',
			cache: false,
			timeout: 600000,
			success:function(response){				
				var obj = response.result;  
				$('#restable').DataTable(
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
										if(type === 'display'){
											data = '<a href="javascript:void(0);"  onclick="return getStudentDetails(\''+data+'\');"  title="Student Details" >'+data+'</a>';
										}							            
										return data;
									}
								},
								{ "mData": "surname" },
								{ "mData": "otherNames" },
								{ "mData": "gender" },
								{ "mData": "yearOfBirth" },	
								{ "mData": "studentNumber",
									"render": function(data, type,row,meta){	
										var combination=search["combination"];
										if(type === 'display'){
											data = '<a href="javascript:void(0);"  class="btn btn-primary btn-xs" onclick="return getStudentDetails(\''+data+','+combination+'\');"  title="Student Details" ><i class="fa fa-pencil">View</i></a>'+
											'       <a href="javascript:void(0);"  class="btn btn-info btn-xs" onclick="return getStudentDetails(\''+data+'\');"  title="Student Details" ><i class="fa fa-folder">Edit</i></a>'+
											'       <a href="javascript:void(0);"  class="btn btn-danger btn-xs" onclick="return getStudentDetails(\''+data+'\');"  title="Student Details" ><i class="fa fa-pencil">Print</i></a>';;
										}							            
										return data;
									}
								},
								]
						}	
				);

				console.log("SUCCESS : ", response);
			},
			error: function (e) {

				var json = "<h4>System Response</h4><pre>"
					+ e.responseText + "</pre>";
				$('#feedback').html(json);

				console.log("ERROR : ", e);
				$("#btn-search").prop("disabled", false);

			}
		});
	});
});

//Search for a student details

function getStudentDetails(xdata) {	
	$('#studentinfo').modal('toggle');
	var search={}	
	var me=xdata.split(",");
	search["schoolcode"]=me[0]
	search["combination"]=me[1];	
	if(search["combination"]=="OLEVEL"){			
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
				$('#eduka').hide();
				$('#shoyisi').show();
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
				$('#shoyisi').hide();
				$('#eduka').show();
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


