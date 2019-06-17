$(document).ready(() => {

let logUser = JSON.parse(sessionStorage.getItem("logUser"));
console.log(logUser);

let login = logUser.login;
let token = logUser.token;
let headingBox = $(".headingBox");
//let changePass = $('#changePass'); 

console.log(`${login} ${token}`);

$.ajax({
		type: "POST",
		contentType: "application/json; charset=utf-8",
		url: "http://localhost:3000/userinfo",
		data: "{\"login\":\""+login+"\",\"token\":\""+token+"\"}",
		    success: function (result,textStatus,xhr) {
		        console.log("it works");
		        console.log(textStatus);
		        console.log(xhr.status);

		        if(xhr.status == 200){
		        	console.log(result);
		        	let rslt = JSON.parse(result);
		        	console.log(rslt);

		        	fillUserInfo(rslt.FirstName,rslt.LastName,rslt.Mail,headingBox);
		         }  
		    },
		    error: function (xhr, textStatus, errorThrown) { 
		      	console.log(xhr.status);
		      	console.log(textStatus);

		    if(xhr.status == 401){
		       console.log(`Wrong credentials`);
		    }

		}	
});	


const fillUserInfo = (fname,lname,mail,wrapper) => {
	wrapper.append("<div class='headingText'><span class='greyText'>First Name: </span>"+fname+"</div>");
	wrapper.append("<div class='headingText'><span class='greyText'>Surname: </span>"+lname+"</div>");
	wrapper.append("<div class='headingText'><span class='greyText'>E-mail: </span>"+mail+"</div>");
}

/*
changePass.click(() => {
	console.log("test change pass");
});*/

let modalPass = document.getElementById('passModal');
let changePass = document.getElementById("changePass");
let span = document.getElementsByClassName("close")[0];

changePass.onclick = function() {
    $(modalPass).fadeIn("fast");
}

span.onclick = function() {
    $(modalPass).fadeOut("fast");
}

window.onclick = function(event) {
    if (event.target == modalPass) {
        $(modalPass).fadeOut("fast");
    }

}


let oldPass = $('.oldpassword');
let newPass = $('.newpassword');
let submitPass = $('#submitPass');
let error = $("#errorWrap");

submitPass.click(() => {

		console.log("test submit pass");

		if(oldPass.val().trim()==null || oldPass.val().trim()==""|| oldPass.val() ===" "){
			 error.empty();
		     error.append('<p id="errorText">Old password field is empty</p>');
		}else if(newPass.val().trim()==null || newPass.val().trim()==""|| newPass.val() ===" "){
			 error.empty();
		     error.append('<p id="errorText">New Password field is empty</p>');
		}
		else{
		error.empty();
		 $.ajax({
		      type: "POST",
		      contentType: "application/json; charset=utf-8",
		      url: "http://localhost:3000/changePassword ",
		      data: "{\"login\":\""+login+"\",\"token\":\""+token+"\",\"oldpassword\":\""+oldPass.val()+"\",\"newpassword\":\""+newPass.val()+"\"}",
		      success: function (result,textStatus,xhr) {
		           console.log("it works");
		           console.log(textStatus);
		           console.log(xhr.status);

		           if(xhr.status == 200){
		           	console.log("password is changed");
		           	oldPass.val("");
		           	newPass.val("");
		           	$(modalPass).fadeOut("fast");
		           	console.log(result);
		           }
		           
		      },
		      error: function (xhr, textStatus, errorThrown) { 
		      	console.log(xhr.status);
		      	console.log(textStatus);

		      	if(xhr.status == 400){
		      		oldPass.val("");
		           	newPass.val("");
		      		error.empty();
		      		error.append('<p id="errorText">Error wrong old password</p>');
		      	}

		      }	
		 });
		}
	});

});