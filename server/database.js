const mysql = require('mysql');
const TokenGenerator = require('uuid-token-generator');
const tokgen = new TokenGenerator(); 

let tokens = [];

const getLogin = (login,password,callback) => {
console.log(login,password);
	const con = mysql.createConnection({
	
	host: "itsovy.sk",
    user: "glbank",
    password: "password",
    database: "glbank",
    port: "3306"

	});

	con.connect(function(err){
		if(err) throw err 
		let sql = "SELECT id,login,password FROM loginclient "+"WHERE login LIKE '"+login+"' "+"AND password like md5('"+password+"');";
		console.log("It works");
		con.query(sql,(err,result) => {	 
		if(err) throw err; 
			if(result.length==0){
				console.log("User"+login+' is not defined');
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

	host: "itsovy.sk",
    user: "glbank",
    password: "password",
    database: "glbank",
    port: "3306"

	});

	con.connect(function(err){
		if(err) throw err 
		let sql = "select client.fname,client.lname,client.email from client inner join loginclient on loginclient.idc = client.id where login like'"+login+"';";
		console.log("It works");
		con.query(sql,(err,result) => {	 
		if(err) throw err; 
			if(result.length==0){
				console.log("User"+login+' is not defined');

				let rslt = null;
				callback(rslt);
			}else{

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


const getAccounts = (login,token,callback) => {
	const con = mysql.createConnection({

	host: "itsovy.sk",
    user: "glbank",
    password: "password",
    database: "glbank",
    port: "3306"

	});

	con.connect(function(err){
		if(err) throw err 
		let sql = "SELECT AccNum,Amount from account INNER JOIN client on client.id=account.idc inner join loginclient on client.id=loginclient.idc where login like '"+login+"';";
		console.log("It works");
		con.query(sql,(err,result) => {	 
		if(err) throw err; 
			if(result.length==0){
				console.log("User"+login+' is not defined');
				let rslt = null;
				callback(rslt);
			}else{
				console.log("User"+login+" is in the database");
				let newresult = JSON.stringify(result);
				console.log(newresult);
				callback(newresult);
			}
		});
	});
}


const getAccInfo = (login,token,accNum,callback) => {
	const con = mysql.createConnection({

	host: "itsovy.sk",
    user: "glbank",
    password: "password",
    database: "glbank",
    port: "3306"

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
			let sql = "select id,amount from account where AccNum like '"+accNum+"';";
			con.query(sql,(err,result) => {	 
			if(err) throw err; 
				if(result.length==0){
					console.log("Account is not in the database");
					let rslt = null;
					callback(rslt);
				}else{
					console.log("Account is in the database");
					let newresult = JSON.stringify(result);
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

	host: "itsovy.sk",
    user: "glbank",
    password: "password",
    database: "glbank",
    port: "3306"

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
			let sql = "select * from transaction where RecAccount like '"+idAcc+"';";
			con.query(sql,(err,result) => {	 
			if(err) throw err; 
				if(result.length==0){
					console.log("Account is not in the database");
					let rslt = null;
					callback(rslt);
				}else{
					console.log("Account is in the database");
					let newresult = JSON.stringify(result);
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

	host: "itsovy.sk",
    user: "glbank",
    password: "password",
    database: "glbank",
	port: "3306"
		
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
			let sql = "select id from card where ida like'"+idAcc+"';";
			con.query(sql,(err,result) => {	 
			if(err) throw err; 
				if(result.length==0){
					console.log("Account is not in the database");
					let rslt = null;
					callback(rslt);
				}else{
					console.log("Account is in the database");
					let newresult = JSON.stringify(result);
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

		host: "itsovy.sk",
    user: "glbank",
    password: "password",
    database: "glbank",
    port: "3306"

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
			let sql = "select Active,ExpireM,ExpireY from card where id like'"+idCard+"';";
			con.query(sql,(err,result) => {	 
			if(err) throw err; 
				if(result.length==0){
					console.log("Account is not in the database");
					let rslt = null;
					callback(rslt);
				}else{
					console.log("Account is in the database");
					let newresult = JSON.stringify(result);
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

	host: "itsovy.sk",
    user: "glbank",
    password: "password",
    database: "glbank",
    port: "3306"

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
					let newresult = JSON.stringify(result);
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

const getChangePass = (login,token,oldpassword,newpassword,callback) => {
	const con = mysql.createConnection({

	host: "itsovy.sk",
    user: "glbank",
    password: "password",
    database: "glbank",
    port: "3306"

	});

	let objGetted = new Object();
	objGetted.login=login;
	objGetted.token=token;
	console.log(objGetted);
	console.log("It works"+"	"+objGetted.login+"	"+objGetted.token);
	console.log(login+"	"+token+" "+oldpassword+" "+newpassword);

	for(let i=0;i<tokens.length;i++){
		if(tokens[i].login==objGetted.login && tokens[i].token==objGetted.token){
			con.connect(function(err){
			if(err) throw err 
			let sql = "update loginclient set password = md5('"+newpassword+"') where login like '"+login+"' and password like md5('"+oldpassword+"');";
			con.query(sql,(err,result) => {	 
			if(err) throw err; 
				if(result.affectedRows == 0){
					console.log("Bad old password");
					let rslt = null;
					callback(rslt);
				}else{
					console.log(result.affectedRows);
					console.log("Account is in the database");
					let newresult = JSON.stringify("Password is changed");
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


const getBlockCard = (login,token,idCard,callback) => {
	const con = mysql.createConnection({

	host: "itsovy.sk",
    user: "glbank",
    password: "password",
    database: "glbank",
    port: "3306"

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
			let sql = "update card set Active = '"+0+"' where id like '"+idCard+"';";
			con.query(sql,(err,result) => {	 
			if(err) throw err; 
				if(result.length==0){
					console.log("Account is not in the database");
					let rslt = null;
					callback(rslt);
				}else{
					console.log("Account is in the database");
					let newresult = JSON.stringify("Card blocked");
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


module.exports = {getLogin,getLogout,getUserInfo,getAccounts,getAccInfo,getTransHistory,getCards,getCardInfo,getCardTransaction,getChangePass,getBlockCard};