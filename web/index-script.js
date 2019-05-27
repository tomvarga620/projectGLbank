$(document).ready(() => {

	let userName = $("#userName");
	let pass = $("#pass");
	let submitBtn = $("#submitBtn");


	submitBtn.click(() => {
		console.log("test");

		console.log(userName.val());
		console.log(pass.val());

		 $.ajax({
		      type: "POST",
		      contentType: "application/json; charset=utf-8",
		      url: "http://localhost:3000/login",
		      data: "{'login':'"+userName.val()+"','data2':'"+pass.val()+"'}",
		      success: function (result) {
		           console.log("it works");
		      }
		 });
	});
});