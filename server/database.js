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

	let objGetted = new Object();
	objGetted.login=login;
	objGetted.token=token;
	console.log(objGetted);
	console.log("It works"+"	"+objGetted.login+"	"+objGetted.token);

	for(let i=0;i<tokens.length;i++){
		console.log("it work")
		if(tokens[i].login==objGetted.login && tokens[i].token==objGetted.token){
			con.connect(function(err){
			if(err) throw err 
			let sql = "select * from account where AccNum like '"+accNum+"';";
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

		break;

		}else{
			console.log("bad credentials");
			let newresult = null;
			callback(newresult);
			break;
		}

	}
}

const getTransHistory = (login,idAcc,token,callback) => {
	const con = mysql.createConnection({

		host: "localhost",
		user: "root",
		password: "Dadada5",
		port: "3306",
		database: "glbank"

	});

	let objGetted = new Object();
	objGetted.login=login;
	objGetted.token=token;
	console.log(objGetted);
	console.log("It works"+"	"+objGetted.login+"	"+objGetted.token);

	for(let i=0;i<tokens.length;i++){
		if(tokens[i].login==objGetted.login && tokens[i].token==objGetted.token){
			con.connect(function(err){
			if(err) throw err 
			let sql = "select * from transaction where idAcc like '"+idAcc+"';";
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
					obj.transAmout=result[0].TransAmout;
					obj.idcAccount=result[0].idAcc;
					let newresult = JSON.stringify(obj);
					console.log(newresult);
					callback(newresult);
				}
			});
		});

		break;

		}else{
			console.log("bad credentials");
			let newresult = null;
			callback(newresult);
			break;
		}

	}
}



const getCards = (login,idAcc,token,callback) => {
	const con = mysql.createConnection({

		host: "localhost",
		user: "root",
		password: "Dadada5",
		port: "3306",
		database: "glbank"

	});

	let objGetted = new Object();
	objGetted.login=login;
	objGetted.token=token;
	console.log(objGetted);
	console.log("It works"+"	"+objGetted.login+"	"+objGetted.token);

	for(let i=0;i<tokens.length;i++){
		if(tokens[i].login==objGetted.login && tokens[i].token==objGetted.token){
			con.connect(function(err){
			if(err) throw err 
			let sql = "select * from card where ida like'"+idAcc+"';";
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
					console.log(result.length);
					for(let i = 0;i<result.length;i++){
						obj[i].id=result[i].id;
					}
					let newresult = JSON.stringify(obj);
					console.log(newresult);
					callback(newresult);
				}
			});
		});

		break;

		}else{
			console.log("bad credentials");
			let newresult = null;
			callback(newresult);
			break;
		}

	}
}


const getCardInfo = (login,idCard,token,callback) => {
	const con = mysql.createConnection({

		host: "localhost",
		user: "root",
		password: "Dadada5",
		port: "3306",
		database: "glbank"

	});

	let objGetted = new Object();
	objGetted.login=login;
	objGetted.token=token;
	console.log(objGetted);
	console.log("It works"+"	"+objGetted.login+"	"+objGetted.token);

	for(let i=0;i<tokens.length;i++){
		if(tokens[i].login==objGetted.login && tokens[i].token==objGetted.token){
			con.connect(function(err){
			if(err) throw err 
			let sql = "select * from card where ida like'"+idCard+"';";
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
					console.log(result.length);
					for(let i = 0;i<result.length;i++){
						obj[i].active=result[i].Active;
						obj[i].expireM=result[i].ExpireM;
						obj[i].expireY=result[i].ExpireY;
					}
					let newresult = JSON.stringify(obj);
					console.log(newresult);
					callback(newresult);
				}
			});
		});

		break;

		}else{
			console.log("bad credentials");
			let newresult = null;
			callback(newresult);
			break;
		}

	}
}


const getCardTransaction = (login,idCard,token,callback) => {
	const con = mysql.createConnection({

		host: "localhost",
		user: "root",
		password: "Dadada5",
		port: "3306",
		database: "glbank"

	});

	let objGetted = new Object();
	objGetted.login=login;
	objGetted.token=token;
	console.log(objGetted);
	console.log("It works"+"	"+objGetted.login+"	"+objGetted.token);

	for(let i=0;i<tokens.length;i++){
		if(tokens[i].login==objGetted.login && tokens[i].token==objGetted.token){
			con.connect(function(err){
			if(err) throw err 
			let sql = "select * from cardtrans where idCard like'"+idCard+"';";
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
					console.log(result.length);
					for(let i = 0;i<result.length;i++){
						obj[i].transamount=result[i].TransAmount;
						obj[i].transDate=result[i].TransDate;
					}
					let newresult = JSON.stringify(obj);
					console.log(newresult);
					callback(newresult);
				}
			});
		});

		break;

		}else{
			console.log("bad credentials");
			let newresult = null;
			callback(newresult);
			break;
		}

	}
}


module.exports = {getLogin,getLogout,getUserInfo,getAccInfo,getTransHistory,getCards,getCardInfo,getCardTransaction};