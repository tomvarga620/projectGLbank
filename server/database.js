const mysql = require('mysql');

const conn = mysql.createConnection({
	host: "localhost",
	user: "root",
	password: "Dadada5"

});

conn.connect(function(err) {
	if (err) throw err;
	console.log("Database connected");
	let sql = "SELECT id,login,password FROM loginclient"+"WHERE login LIKE '"+name+"'	"+"AND password like"+password"'";
});