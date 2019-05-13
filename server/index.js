const http = require('http');
const express = require('express');
const app = express();
const database = require('./database');
const bodyParser = require("body-parser");

app.post('/login',function(req,res,callback){

	console.log("Login Service");

	let login = req.body.login;
	let password = req.body.password

	database.getLogin(login,password, function(result) {
	res.status(200).send(result);

});

app.post('/logout',function(req,res){

	res.status(200).send();

});

app.listen(3000);