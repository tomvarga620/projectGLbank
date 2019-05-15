const mysql = require('mysql');
const TokenGenerator = require('uuid-token-generator');
const tokgen = new TokenGenerator(); 

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
				let newresult = JSON.stringify(obj);
				callback(newresult);
			}
		});
	});
}

module.exports = {getLogin};