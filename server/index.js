let database = require('./database');
const app = require('express')();
const bodyParser = require("body-parser");

app.use(bodyParser.json());
app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
  });  


app.post('/login',function(req,res,callback){

	console.log("Login Service");

	let login = req.body.login;
	let password = req.body.password;

	if(login=="" || password==""){
	res.status(400).send("Error bad login");
	}

	database.getLogin(login,password,function(result) {

	if(result==null){
		console.log("null test");
		res.status(400).send("Error login account not found");
	}else{
		res.status(200).send(result);
	}

	});

});

app.post('/logout',function(req,res){

	let login = req.body.login;
	let token = req.body.token;

	//res.status(200).send();
	database.getLogout(login,token,function(result){
		res.status(result).send("");
	});

});

app.listen(3000);