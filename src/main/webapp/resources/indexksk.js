$(document).ready(function(){
//	alert("1.번 2번줄, ready(fn) 발동")
	var token;
	if(document.cookie.includes("accesstoken")) {
		token = document.cookie.split('token=')[1];	
//		alert("6번줄 . if tokken 아래")
	}
	
	$.ajax({
		beforeSend: function(xhr){
//			alert("beforeSend 맨 첨");
			xhr.setRequestHeader('accesstoken', token);// 서버랑 소통은 하지 않고, header에 장착한다.
//			alert("13번줄 ajax br 안에")
        },
        url: "/post"
        // post . method get
    }).then(function(data) {
//    	alert("17번줄 . $each문 안에")
    	var vint = 1;
    	$.each(data.data, function(index, e) {
//    		alert("19번줄 . $each문 안에 "+vint);
    		vint = vint + 1;
    		$('#posts').append(
    				'<div class="card mb-4"> <div class="card-body"> <h2 class="card-title">' + e.title 
    				+ '</h2> <p class="card-text">' + e.content 
    				+ '</p> <a href="/page/detail/' + e.id 
    				+ '" class="btn btn-primary">Read More &rarr;</a> </div> ' 
    				+ '<div class="card-footer text-muted"> Posted on ' + e.createdAt.split('T')[0]
    				+ ' by ' + e.user.username
    				+ '</div> </div>');
    	});
       console.log(data);
    }, function(err) {
    	alert(" 34 error");
    	console.log(err.responseJSON);
    });
	
	
	if(token) {
		$.ajax({
			beforeSend: function(xhr){
				alert("41번 . if(token) 안에 ajax");
				xhr.setRequestHeader('accesstoken', token);
	        },
	        url: "/post/my"
	        	// post/my . method get
	    }).then(function(data) {
	    	$.each(data.data, function(index, e) {
//	    		alert("48번줄 . $each문 안에");
	    		$('#myfeed').append(
	    				'<div class="card mb-4"> <div class="card-body"> <h2 class="card-title">' + e.title 
	    				+ '</h2> <p class="card-text">' + e.content 
	    				+ '</p> <a href="/page/detail/' + e.id 
	    				+ '" class="btn btn-primary">Read More &rarr;</a> </div> ' 
	    				+ '<div class="card-footer text-muted"> Posted on ' + e.createdAt.split('T')[0]
	    				+ ' by ' + e.user.username
	    				+ '</div> </div>');
	    	});
	       console.log(data);
	    }, function(err) {
	    	console.log(err.responseJSON);
//	    	alert(" 61 error");
	    });
	}
	
	$('#save_post_btn').click(function(){
		var title = $('#create_title_text').val();
		var content = $('#create_content_text').val();
		
		console.log(title);
		console.log(content);
		
		var param = {
			title: title,
			content: content
		}
		
		$.ajax({
			beforeSend: function(xhr){
				xhr.setRequestHeader('accesstoken', token);
	        },
	        url: "/post",
	        method: "POST",
	        // post . method post 
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {
	    	window.location.href = '/';
	    }, function(err) {
	    	alert(err.responseJSON);
	    });
	});
	
	$('#header_logout_btn').click(function(){
		document.cookie = "accesstoken=; expires=Thu, 01 Jan 1970 00:00:01 GMT;";
		window.location.href = '/';
	});
	
	$('body').on('click', '.follow', function($event) {
		console.log($(event.target).html());
		console.log($(this).html());
		console.log($(this).attr('value'));
		var userId = $(this).attr('value');
		
		var param = {
			followeeId: userId
		}
		
		$.ajax({
			beforeSend: function(xhr){
				xhr.setRequestHeader('accesstoken', token);
	        },
	        url: "/follow",
	        method: "POST",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {
	    	window.location.reload(); 	    
	    }, function(err) {
	    	alert(err.responseJSON);
	    });
	});
	
	$('body').on('click', '.unfollow', function() {
		console.log("unfollow clicked!!!");
		
		var userId = $(this).attr('value');
		
		var param = {
			followeeId: userId
		}
		
		$.ajax({
			beforeSend: function(xhr){
				xhr.setRequestHeader('accesstoken', token);
	        },
	        url: "/follow",
	        method: "DELETE",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {
	    	window.location.reload();
	    }, function(err) {
	    	alert(err.responseJSON);
	    });
	});
	
});
