window.onload = function(){
	const oldnickname = "暱稱: " + sessionStorage["nickname"];
	document.getElementById("nickname_in_session").innerText = oldnickname;
	
	if(sessionStorage["nickname"] === undefined){
		document.getElementById("logout").style.visibility = "hidden";
		document.getElementById("nickname_in_session").style.visibility = "hidden";
		window.location.href="./login.html";
	}
	
	document.getElementById("submit").onclick = function(){
		const account = sessionStorage["account"];
		
		//document.getElementById("account").innerText;
		const password = document.getElementById("password").value.trim();
		const confirmPassword = document.getElementById("confirmPassword").value.trim();
		const nickname = document.getElementById("nickname").value.trim();

		if(password == "" || confirmPassword == "" || nickname == ""){
			alert("所有資料都不得為空白");
			return;
		}
		
		if(password !== confirmPassword){
			alert("確認密碼與新密碼不相符");
			return;
		}

		fetch("../UpdateController", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify({
				account: account,
				password: password,
				nickname: nickname
			})
		})
		.then(resp => resp.json())
		.then(body => {
			if(body.result === -2){
				alert("密碼長度範圍為6~12個英文或數字");
			}else if(body.result === -3){
				alert("暱稱長度範圍為1~20個字");
			}else if(body.result === -4){
				alert("帳號已被註冊");
			}else if(body.result === 1){
				alert("修改成功"+"\n帳號: "+body.account+"暱稱: "+body.nickname);
				sessionStorage["nickname"] = body.nickname;
				location.reload();
			}else{
				alert("不明原因修改失敗");
			}
			
		});
	}
	
	//登出
	document.getElementById("logout").onclick = function(){
		if(confirm("確定要登出嗎？")){
			sessionStorage.removeItem("nickname");
			sessionStorage.removeItem("account");
			sessionStorage.removeItem("role_id");
			window.location.href="./login.html";
		}else{
			return;
		}
	}
}