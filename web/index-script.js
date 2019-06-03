$(document).ready(() => {

	let userName = $("#userName");
	let pass = $("#pass");
	let submitBtn = $("#submitBtn");
	let error = $("#errorWrap");

	submitBtn.click(() => {

		console.log("test");
		console.log(userName.val());
		console.log(pass.val());

		 $.ajax({
		      type: "POST",
		      contentType: "application/json; charset=utf-8",
		      url: "http://localhost:3000/login",
		      data: "{\"login\":\""+userName.val()+"\",\"password\":\""+pass.val()+"\"}",
		      success: function (result,textStatus,xhr) {
		           console.log("it works");
		           console.log(textStatus);
		           console.log(xhr.status);

		           if(xhr.status == 200){
		           	location.href = "main.html";
		           }
		           
		      },
		      error: function (xhr, textStatus, errorThrown) { 
		      	console.log(xhr.status);
		      	console.log(textStatus);
		      	
		      	if(xhr.status == 403){
		      		error.empty();
		      		error.append('<p id ="errorText">Error login account not found</p>');
		      	}

		      	if(xhr.status == 401){
		      		error.empty();
		      		error.append('<p id="errorText">Error bad login</p>');
		      	}

		      }	
		 });
	});
});