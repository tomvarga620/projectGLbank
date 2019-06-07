$(document).ready(() => {

let logout = $(".logout");
let logUser = sessionStorage.getItem("logUser");
console.log(logUser);

logout.click(() => {
	console.log("logout test");
});

});