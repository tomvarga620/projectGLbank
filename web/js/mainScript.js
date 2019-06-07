$(document).ready(() => {

let logout = $(".logout");
let logUser = JSON.parse(sessionStorage.getItem("logUser"));
console.log(logUser);

let login = logUser.login;
let token = logUser.token;

console.log(`${login} ${token}`);

logout.click(() => {
	console.log("logout test");
		 $.ajax({
		      type: "POST",
		      contentType: "application/json; charset=utf-8",
		      url: "http://localhost:3000/logout",
		      data: "{\"login\":\""+login+"\",\"token\":\""+token+"\"}",
		      success: function (result,textStatus,xhr) {
		           console.log("it works");
		           console.log(textStatus);
		           console.log(xhr.status);

		           if(xhr.status == 200){
		           	sessionStorage.clear();
		           	location.href = "index.html";
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
});

});