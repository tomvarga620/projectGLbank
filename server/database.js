const mysql = require('mysql');
const TokenGenerator = require('uuid-token-generator');
const tokgen = new TokenGenerator(); 

let tokens = [];

const getLogin = (login,password,callback) => {
console.log(login,password);
	const con = mysql.createConnection({
		host: "localhost",
		user: "root",
		password: "Dadada5",
		port: "3306",
		database: "glbank"

	});

	con.connect(function(err){
		if(err) throw err 
		let sql = "SELECT id,login,password FROM loginclient "+"WHERE login LIKE '"+login+"' "+"AND password like '"+password+"';";
		console.log("It works");
		con.query(sql,(err,result) => {	 
		if(err) throw err; 
			if(result.length==0){
				console.log("User"+login+' is not defined');
				//return null;
				let rslt = null;
				callback(rslt);
			}else{
				console.log("User"+login+" is in the database");
				let token = tokgen.generate();
				let obj= new Object();
				obj.login=login;
				obj.token= token;
				tokens=tokens.filter(person => person.login != login);
				tokens.push(obj);
				console.log(tokens);
				let newresult = JSON.stringify(obj);
				callback(newresult);
			}
		});
	});
}

const getLogout = (login,token,callback) => {
if(tokens.find(person => (person.login == login && person.token==token))){
    tokens=tokens.filter(person => (person.login != login && person.token !=token));
    callback(200);
    console.log(tokens);
  }
  else{
    callback(401);
    console.log(tokens);
  }

}


const getUserInfo = (login,token,callback) => {
	const con = mysql.createConnection({
		host: "localhost",
		user: "root",
		password: "Dadada5",
		port: "3306",
		database: "glbank"

	});

	con.connect(function(err){
		if(err) throw err 
		let sql = "select client.fname,client.lname,client.email from client inner join loginclient on loginclient.id = client.id where login like'"+login+"';";
		console.log("It works");
		con.query(sql,(err,result) => {	 
		if(err) throw err; 
			if(result.length==0){
				console.log("User"+login+' is not defined');
				//return null;
				let rslt = null;
				callback(rslt);
			}else{
				// {"FirstName","LastName","Mail","ID"}
				console.log("User"+login+" is in the database");
				let obj= new Object();
				obj.FirstName=result[0].fname;
				obj.LastName=result[0].lname;
				obj.Mail=result[0].email;
				obj.ID=result[0].id;
				let newresult = JSON.stringify(obj);
				console.log(newresult);
				callback(newresult);
			}
		});
	});
}


const getAccInfo = (login,token,accNum,callback) => {
	const con = mysql.createConnection({
		host: "localhost",
		user: "root",
		password: "Dadada5",
		port: "3306",
		database: "glbank"

	});

	con.connect(function(err){
		if(err) throw err 
		let sql = "select * from account where AccNum like '"+accNum+"';";
		console.log("It works");
		con.query(sql,(err,result) => {	 
		if(err) throw err; 
			if(result.length==0){
				console.log("Account is not in the database");
				//return null;
				let rslt = null;
				callback(rslt);
			}else{
				// {"FirstName","LastName","Mail","ID"}
				console.log("Account is in the database");
				let obj= new Object();
				obj.id=result[0].id;
				obj.amount=result[0].amount;
				let newresult = JSON.stringify(obj);
				console.log(newresult);
				callback(newresult);
			}
		});
	});
}

module.exports = {getLogin,getLogout,getUserInfo,getAccInfo};