$(document).ready(function(){
	$('#signup_btn').click(function(){
		console.log("signup clicked!!!");
		
		var username = $('#signup_username').val();
		var password = $('#signup_password').val();
		
		if(!username || !password) {
			alert("필수 항목을 채워주세요.");
			return;
		}
		
		var param = {
				username: username,
				password: password
		}
		
		$.ajax({
	        url: "/user",
	        method: "POST",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {	    	
	    	console.log("sssssㅣㅣㅣㅣㅣㅣ")
	    	alert("회원가입되었습니다.")
	    	window.location.href = '/';
	    }, function(err) {
	    	console.log("ffffff")
	    	alert("Username이 중복되었습니다.");
	    	window.location.reload();
	    });
		
		return false;
	});
});