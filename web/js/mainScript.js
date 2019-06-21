$(document).ready(() => {

let logout = $(".logout");
let logUser = JSON.parse(sessionStorage.getItem("logUser"));
console.log(logUser);
console.log("all keys = "+Object.keys(sessionStorage));

let login = logUser.login;
let token = logUser.token;
let paymentBtn = $(".payment");

console.log(`${login} ${token}`);

if(sessionStorage['accNum']){
	console.log(typeof(login));
	console.log(typeof(token));
	console.log(sessionStorage.getItem("accNum"));
	let accNum = sessionStorage.getItem("accNum");
	let money = sessionStorage.getItem("money");
	console.log(typeof(accNum));

	let accWrap = $(".accText");
	let moneyWrap = $(".money");
	let balanceWrap = $(".balanceHeading"); 

	balanceWrap.text("Current Balance:");
	accWrap.text("Account Number: "+accNum);
	moneyWrap.text(money+" €");
	paymentBtn.css('display','block');

	$.ajax({
		      type: "POST",
		      contentType: "application/json; charset=utf-8",
		      url: "http://localhost:3000/transhistory",
		      data: "{\"login\":\""+login+"\",\"idAcc\":\""+accNum+"\",\"token\":\""+token+"\"}",
		      success: function (result,textStatus,xhr) {
		           console.log("it works");
		           console.log(textStatus);
		           console.log(xhr.status);

		           if(xhr.status == 200){
		           	let rslt = JSON.parse(result);
		           	console.log(rslt);
		           	generateTransactions(rslt);

		           }
		           
		      },
		      error: function (xhr, textStatus, errorThrown) { 
		      	console.log(xhr.status);
		      	console.log(textStatus);

		      	if(xhr.status == 400){
		      		console.log(`Wrong credentials`);
		      	}

		      }	
	});	
}
else{
	console.log("wrong accNum");
}

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

const generateTransactions = (arr) => {
	for(let i=0;i<arr.length;i++){
		let transWrap = $('.transactionWrap');
		transWrap.append("<div class='trans'><div class='transId'>ID:<span class='value'>"+arr[i].id+"</span></div><div class='recNum'>Recipient:<span class='value'>"+arr[i].RecAccount+"</span></div><div class='amount'>"+"€ -"+arr[i].TransAmount+"</div></div>")
	}
}

});