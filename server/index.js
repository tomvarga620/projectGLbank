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

	database.getLogin(login,password,function(result) {
	res.status(200).send(result);

	});

});

app.post('/logout',function(req,res){

	res.status(200).send();

});

app.listen(3000);