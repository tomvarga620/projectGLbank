$(document).ready(() => {

let logUser = JSON.parse(sessionStorage.getItem("logUser"));
console.log(logUser);

let login = logUser.login;
let token = logUser.token;
let headingBox = $(".headingBox");
let changePass = $('#changePass'); 

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

let modalPass = document.getElementById('modalPass');
let span = document.getElementsByClassName("close")[0];

changePass.onclick = function() {
	console.log("test");
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

});