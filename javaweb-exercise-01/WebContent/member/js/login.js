window.onload = function(){
	document.getElementById("submit").onclick = function(){
		const account = document.getElementById("account").value.trim();
		const password = document.getElementById("password").value.trim();

		if(account == "" || password == ""){
			alert("帳號密碼不得為空白");
			return;
		}

		fetch("../LoginController", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify({
				account: account,
				password: password
			})
		})
		.then(resp => resp.json())
		.then(body => {
			if(body === null){
				alert("登入失敗");
			}else if(body.role_id === 1){
				sessionStorage["nickname"] = body.nickname;
				sessionStorage["account"] = body.account;
				sessionStorage["role_id"] = body.role_id;
				window.location.href="./delete.html";
			}else{
				sessionStorage["nickname"] = body.nickname;
				sessionStorage["account"] = body.account;
				window.location.href="./update.html";
			}
			
		});
	}
}
