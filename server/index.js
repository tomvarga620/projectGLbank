const http = require('http');
const express = require('express');
const app = express();
const database = require('./database');
const bodyParser = require("body-parser");
const TokenGenerator = require('uuid-token-generator');

let tokenArr = [];

const tokgen = new TokenGenerator(); 

app.post('/login',function(req,res,callback){

	console.log("Login Service");
	const token = tokgen.generate();
	res.status(200).send(token);

});

app.listen(3000);