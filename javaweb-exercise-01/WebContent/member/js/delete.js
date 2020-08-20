window.onload = function(){
	if(sessionStorage["role_id"] === undefined){
		window.location.href="./login.html";
	}
	
	const oldnickname = "暱稱: " + sessionStorage["nickname"];
	document.getElementById("nickname_in_session").innerText = oldnickname;
	
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
	
	fetch("../DeleteController", {
		method: "POST",
		headers: { "Content-Type": "application/json" }
	})
	.then(resp => resp.json())
	.then(body => {
		if(body !== null){
			var tbl = document.createElement('table');
			tbl.style.width = '100%';
			tbl.setAttribute('border', '1');
			tbl.style.borderCollapse = "collapse";
			var tbdy = document.createElement('tbody');
			var tr = document.createElement('tr');
			var td = document.createElement('td');
			td.appendChild(document.createTextNode('是否刪除'));
			td.style.width = '80px';
			tr.appendChild(td);
			
			td = document.createElement('td');
			td.appendChild(document.createTextNode('會員流水號'));
			td.style.width = '120px';
			tr.appendChild(td);
			
			td = document.createElement('td');
			td.appendChild(document.createTextNode('會員帳號'));
			tr.appendChild(td);
			
			td = document.createElement('td');
			td.appendChild(document.createTextNode('暱稱'));
			tr.appendChild(td);
			
			td = document.createElement('td');
			td.appendChild(document.createTextNode('最後更新日期'));
			tr.appendChild(td);
			
			td = document.createElement('td');
			td.appendChild(document.createTextNode('權限'));
			tr.appendChild(td);
			
			tbdy.appendChild(tr);
			for(var i = 0; i < body.length; i++){
				tr = document.createElement('tr');
				
				td = document.createElement('td');
				bt = document.createElement('button');
				bt.innerHTML = '刪除';
				bt.id = body[i].id;
				bt.onclick = function(){
					if(!confirm("確定刪除？")){
						return;
					}

					fetch("../DeleteByIDController", {
						method: "POST",
						headers: { "Content-Type": "application/json" },
						body: JSON.stringify({
							id: bt.id
						})
					})
					.then(resp => resp.json())
					.then(body => {
						if(body.result !== 1){
							alert("刪除失敗");
						}else{
							alert("刪除成功");
							location.reload();
						}
						
					});
				}
				td.appendChild(bt);
				tr.appendChild(td);
				
				td = document.createElement('td');
				td.appendChild(document.createTextNode(body[i].id));
				tr.appendChild(td);
				tbdy.appendChild(tr);

				td = document.createElement('td');
				td.appendChild(document.createTextNode(body[i].account));
				tr.appendChild(td);
				tbdy.appendChild(tr);

				td = document.createElement('td');
				td.appendChild(document.createTextNode(body[i].nickname));
				tr.appendChild(td);
				tbdy.appendChild(tr);

				td = document.createElement('td');
				var date = new Date(body[i].lastUpdateDate);
				date.setHours(date.getHours() - 12);
				td.appendChild(document.createTextNode(date));
				tr.appendChild(td);
				tbdy.appendChild(tr);
				
				td = document.createElement('td');
				if(body[i].role_id === 1){
					td.appendChild(document.createTextNode("管理員"));
				}else if(body[i].role_id === 2){
					td.appendChild(document.createTextNode("會員"));
				}
				tr.appendChild(td);
				tbdy.appendChild(tr);
			}
			tbl.appendChild(tbdy);
			
			var b = document.getElementsByTagName('body')[0];
			b.appendChild(tbl);
		}
		
		
	});
}