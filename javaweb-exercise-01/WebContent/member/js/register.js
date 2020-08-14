window.onload = function(){
	document.getElementById("submit").onclick = function(){
		const account = document.getElementById("account").value.trim();
		const password = document.getElementById("password").value.trim();
		const confirmPassword = document.getElementById("confirmPassword").value.trim();
		const nickname = document.getElementById("nickname").value.trim();

		if(account == "" || password == "" || confirmPassword == "" || nickname == ""){
			alert("所有資料都不得為空白");
			return;
		}
		
		if(password !== confirmPassword){
			alert("確認密碼與密碼不相符");
			return;
		}

		fetch("../RegisterController", {
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
			// 接收到Controller回應後，要做的事寫在這!!
			if(body.result === -1){
				alert("帳號長度範圍為5~50個英文或數字");
			}else if(body.result === -2){
				alert("密碼長度範圍為6~12個英文或數字");
			}else if(body.result === -3){
				alert("暱稱長度範圍為1~20個字");
			}else if(body.result === -4){
				alert("帳號已被註冊");
			}else if(body.result === 1){
				alert("註冊成功");
			}else{
				alert("不明原因註冊失敗");
			}
			
		});
	}
	// document.getElementById('id')
}
