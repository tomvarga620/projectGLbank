$(document).ready(() => {
	let mainWrapper = $('.mainWrapper');

	let logUser = JSON.parse(sessionStorage.getItem("logUser"));
	console.log(logUser);

	let login = logUser.login;
	let token = logUser.token;

	 $.ajax({
		      type: "POST",
		      contentType: "application/json; charset=utf-8",
		      url: "http://localhost:3000/accounts",
		      data: "{\"login\":\""+login+"\",\"password\":\""+token+"\"}",
		      success: function (result,textStatus,xhr) {
		           console.log("it works");
		           console.log(textStatus);
		           console.log(xhr.status);

		           if(xhr.status == 200){
		           	console.log(result);
		           	let rslt = JSON.parse(result);
		            generateAccounts(rslt);
		           }
		           
		      },
		      error: function (xhr, textStatus, errorThrown) { 
		      	console.log(xhr.status);
		      	console.log(textStatus);
		      
		      	if(xhr.status == 401){
		      		console.log("Wrong credentials");
		      	}

		      }	
	});

	const generateAccounts = (arr) => {
		for(let i=0;i<arr.length;i++){
			let mainWrapper = $('.mainWrapper');
			//mainWrapper.append("<div class='accWrapper'><div class='headingBox'><span class='headingText'>Account number: "+arr[i].AccNum+"</span></div></div>");
			mainWrapper.append("<div class='accWrapper'><div class='headingBox'><span class='headingText'><span class='greyText'>Account number: </span>"+arr[i].AccNum+"</span></div><div class='balanceWrapper'><div class='left'><div class='balance'><span class='balanceHeading'>Current Balance:</span><br><span class='money'>"+arr[i].Amount+" €"+"</span></div></div><div class='right'></div></div></div></div>");
		}
	}


	$('.mainWrapper').delegate('div.accWrapper', 'click', function() {
		
		let text = $(this).text();
		console.log(text);

		var regex = /: /;
		var regex2 = / €/;

		let accNum = text.substr(text.indexOf(':'),12);
		accNum = accNum.replace(regex,'');
		console.log(accNum);

		let text2 = text.split(':');
		console.log(text2);
		let money = text2[2];
		money = money.replace(regex2,'');
		console.log(money);

		sessionStorage.setItem("accNum", accNum);
		sessionStorage.setItem("money",money);
		location.href = "main.html";

	});

	let paymentBtn = $('.payment');

	paymentBtn.click(()=> {
		console.log("test pica");
	});

});