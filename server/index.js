let database = require('./database');
const app = require('express')();
const bodyParser = require("body-parser");

var cors = require('cors');

app.use(cors());

app.use(bodyParser.json());
app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
  });  

//funguje
app.post('/login',function(req,res,callback){

	console.log("Login Service");

	let login = req.body.login;
	let password = req.body.password;

	if(login=="" || password==""){
	res.status(401).send("Error bad login");
	}

	database.getLogin(login,password,function(result) {

	if(result==null){
		console.log("null test");
		res.status(403).send("Error login account not found");
	}else{
		res.status(200).send(result);
	}

	});

});

//funguje
app.post('/logout',function(req,res){

	let login = req.body.login;
	let token = req.body.token;

	//res.status(200).send();
	database.getLogout(login,token,function(result){
		console.log(result);
		res.status(result).send("");
	});

});


//funguje
app.post('/userinfo',function(req,res){

	let login = req.body.login;
	let token = req.body.token;

	//res.status(200).send();
	database.getUserInfo(login,token,function(result){
		res.status(200).send(result);
	});

});

//funguje
app.post('/accounts',function(req,res){

	let login = req.body.login;
	let id=req.body.id;
	let token = req.body.token;

	//res.status(200).send();
	database.getAccounts(login,id,token,function(result){
		if(result==null){
			res.status(400).send("Wrong credentials");
		}else{
			res.status(200).send(result);
		}
	});

});

//funguje
app.post('/accinfo',function(req,res){

	let login = req.body.login;
	let token = req.body.token;
	let accNum = req.body.accNum;

	//res.status(200).send();
	database.getAccInfo(login,token,accNum,function(result){

		if(result==null){
			res.status(400).send("Wrong credentials");
		}else{
			res.status(200).send(result);
		}
	});

});

//funguje
app.post('/transhistory',function(req,res){

	let login = req.body.login;
	let idAcc = req.body.idAcc;
	let token = req.body.token;

	//res.status(200).send();
	database.getTransHistory(login,idAcc,token,function(result){

		if(result==null){
			res.status(400).send("Wrong credentials");
		}else{
			res.status(200).send(result);
		}
	});

});

//funguje
app.post('/cards',function(req,res){

	let login = req.body.login;
	let idAcc = req.body.idAcc;
	let token = req.body.token;

	//res.status(200).send();
	database.getCards(login,idAcc,token,function(result){

		if(result==null){
			res.status(400).send("Wrong credentials");
		}else{
			res.status(200).send(result);
		}
	});

});

//funguje
app.post('/cardinfo',function(req,res){

	let login = req.body.login;
	let idCard = req.body.idCard;
	let token = req.body.token;

	//res.status(200).send();
	database.getCardInfo(login,idCard,token,function(result){

		if(result==null){
			res.status(400).send("Wrong credentials");
		}else{
			res.status(200).send(result);
		}
	});

});

//funguje
app.post('/cardtrans',function(req,res){

	let login = req.body.login;
	let idCard = req.body.idCard;
	let token = req.body.token;

	//res.status(200).send();
	database.getCardTransaction(login,idCard,token,function(result){

		if(result==null){
			res.status(400).send("Wrong credentials");
		}else{
			res.status(200).send(result);
		}
	});

});

/*
/changePassword 
prijma: JSON{"username","token","oldpassword","newpassword"}
vracia: 200 -
	401 - JSON
  403 -
  
/blockcard
prijma: JSON{"username","token","idCard"}
vracia: 200 
  401 - JSON{"wrong token"}
  403 - nenajdena karta
*/

app.post('/changepassword',function(req,res){

	let login = req.body.login;
	let token = req.body.token;
	let oldpassword = req.body.oldpassword;
	let newpassword = req.body.newpassword;

	//res.status(200).send();
	database.getChangePass(login,token,oldpassword,newpassword,function(result){

		if(result==null){
			res.status(400).send("Wrong credentials");
		}else{
			res.status(200).send(result);
		}
	});

});


app.post('/blockcard',function(req,res){

	let login = req.body.login;
	let token = req.body.token;
	let idCard = req.body.idCard;

	//res.status(200).send();
	database.getBlockCard(login,token,idCard,function(result){

		if(result==null){
			res.status(400).send("Wrong credentials");
		}else{
			res.status(200).send(result);
		}
	});

});

app.listen(3000);